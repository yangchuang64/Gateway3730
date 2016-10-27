package com.ourselec.gateway.process;

import java.io.IOException;
import java.nio.BufferOverflowException;

import android.os.Handler;
import android.os.Message;

import com.ourselec.gateway.message.MessageResolver;
import com.ourselec.gateway.pc.message.resolver.DataMessageResolverFactory;
import com.ourselec.gateway.presenter.GatewayPresenter.MHandler;
import com.ourselec.gateway.process.ProcessThread.IProcess;
import com.ourselec.gateway.util.Utile;

/**
 * Udp处理类
 * 
 * @author yangtianfei(ytf2737179@163.com)
 * 
 */
public class UdpProcess implements IProcess {
	private static final String TAG = "UdpProcess";
	private Handler handler;

	public UdpProcess(Handler handler) {
		this.handler = handler;
	}

	@Override
	public void process() {
		try {
			byte[] buffer = UdpManager.getInstance().receive();
			if (buffer != null)
				parseData(buffer, buffer.length);
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void parseData(byte[] pStream, int size) {
		Utile.printHexString(TAG, pStream, size);
		int iProcessed = 0;
		int iRemain = size;
		while (iProcessed < size && iRemain >= 3) {

			try {
				int header = 0xff & pStream[iProcessed];
				if (header == 0xFE) {
					int length = 0xff & pStream[iProcessed + 1];
					if (length > 0) {
						if (length <= iRemain) {
							if (Utile.ort(pStream, iProcessed, length - 1,
									iProcessed + length - 1)) {
								byte[] buffer = new byte[length];
								System.arraycopy(pStream, iProcessed, buffer,
										0, length);
								processData(buffer, length);
								iProcessed += length;
								iRemain -= length;

							} else {
								iProcessed++;
								iRemain--;
							}
						} else
							break;
					} else {
						iProcessed += 2;
						iRemain -= 2;
					}
				} else {
					iProcessed++;
					iRemain--;
				}
			} catch (BufferOverflowException e) {

				// } catch (InterruptedException e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
			}
		}
	}

	public void processData(byte[] buffer, int length) {
		MessageResolver msgResolver = DataMessageResolverFactory
				.getDataMessageResolver(buffer, length);
		if (msgResolver == null)
			return;
		Object message = msgResolver.parsorMessage();
		// DataMessageResolverFactroy.getDataMessageResolver(source, dataLen,
		// code, buffer, length)
		// ZnpVartMessageResolver messageResolver = new ZnpVartMessageResolver(
		// buffer, length);
		// ZnpUartSerialMessage message = messageResolver.parsorMessage();
		// if (message == null)
		// return;
		// MessageFrame frame = message.getMessageFrame();
		Message msg = handler.obtainMessage(MHandler.UDP_DATA, message);
		handler.sendMessage(msg);
	}
}

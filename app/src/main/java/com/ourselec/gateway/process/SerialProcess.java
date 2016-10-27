package com.ourselec.gateway.process;

import java.nio.BufferOverflowException;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.ourselec.gateway.presenter.GatewayPresenter.MHandler;
import com.ourselec.gateway.process.ProcessThread.IProcess;
import com.ourselec.gateway.serial.message.MessageFrame;
import com.ourselec.gateway.serial.message.ZnpUartSerialMessage;
import com.ourselec.gateway.serial.message.resolver.ZnpUartMessageResolver;
import com.ourselec.gateway.util.Utile;

/**
 * 串口处理类
 * 
 * @author yangtianfei(ytf2737179@163.com)
 * 
 */
public class SerialProcess implements IProcess {
	private static final String TAG = "SerialProcess";
	SerialPortManager portManager;
	Handler handler;
	private final int DELAYEDTIME = 500;

	public SerialProcess(Handler handler) {
		portManager = SerialPortManager.getInstance();
		this.handler = handler;
	}

	@Override
	public void process() {
		int size;
		try {
			byte[] buffer = new byte[1024];
			size = portManager.readSerial(buffer);
			Log.i(TAG, "size ----------->" + size);
			if (size > 0) {
				pickupFrame(buffer, size);
			}
			Thread.sleep(DELAYEDTIME);
		} catch (BufferOverflowException e) {

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void pickupFrame(byte[] pStream, int iStreamLength) {
//		Utile.printHexString(TAG, pStream, iStreamLength);
		int iProcessed = 0;
		int iRemain = iStreamLength;
		while (iProcessed < iStreamLength && iRemain >= 5) {
			try {
				int sof = 0xff & pStream[iProcessed];
				if (sof == 0xFE) {
					int length = 0xff & pStream[iProcessed + 1] + 5;
					if (length > 5) {
						if (length <= iRemain) {
							if (Utile.ort(pStream, iProcessed + 1, length - 2,
									iProcessed + length - 1)) {
								byte[] buffer = new byte[length];
								System.arraycopy(pStream, iProcessed, buffer,
										0, buffer.length);
								processFrame(buffer, buffer.length);

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

	// public boolean validateCheckSum(byte[] stream, int process, int length) {
	// return Utile.ort(stream, process + 1, length) == (0xff & stream[process
	// + length - 1]);
	// }

	public void processFrame(byte[] buffer, int length) {
		Utile.printHexString("serialProcess	processFrame", buffer, length);
		ZnpUartMessageResolver messageResolver = new ZnpUartMessageResolver(
				buffer, length);
		ZnpUartSerialMessage message = messageResolver.parsorMessage();
		if (message == null)
			return;
		MessageFrame frame = message.getMessageFrame();
		Message msg = handler.obtainMessage(MHandler.SERIAL_DATA, frame);
		handler.sendMessage(msg);
		// switch (frame.getCommand()) {
		// case MessageFrameInfo.ZB_READ_CONFIGURATION_RESPONSE:
		//
		// break;
		// case MessageFrameInfo.ZB_WRITE_CONFIGURATION_RESPONSE:
		// processWriteConfigUrat(frame);
		// break;
		// case MessageFrameInfo.SYS_RESET_IND:
		// processSysResetInd(frame);
		// break;
		// case MessageFrameInfo.ZB_APP_REGISTER_RESPONSE:
		// processAppRegister(frame);
		// break;
		// case MessageFrameInfo.ZB_START_RESPONSE:
		// // processStartZb(fram);
		// break;
		// case MessageFrameInfo.ZB_START_CHANGE_IND:
		// processStartChangeInd(frame);
		// break;
		// case MessageFrameInfo.ZB_START_CONFIRM:
		// processStartConfirm(frame);
		// break;
		// case MessageFrameInfo.ZDO_END_DEVICE_ANNCE_IND:
		// processEndDeviceAnnceInd(frame);
		// break;
		// case MessageFrameInfo.ZB_RECEIVE_DATA_INDICATION:
		// processReceiveDataIndcation(frame);
		// break;
		// }
	}

	// public void processReadConfigUrat(MessageFrame frame) {
	// ReadResponseMessageFrame readFrame = (ReadResponseMessageFrame) frame;
	// int status = readFrame.getStatus();
	// if (status == 0) {
	// // succes
	// } else {
	// // fail
	// }
	// }
	//
	// public void processWriteConfigUrat(MessageFrame fram) {
	// WriteResponseMessageFrame writeFrame = (WriteResponseMessageFrame) fram;
	// int status = writeFrame.getStatus();
	// if (status == 0) {
	// // handler.sendEmptyMessage(WRITE_CONFIGURATION_SUCCESS);
	// } else {
	// // handler.sendEmptyMessage(WRITE_CONFIGURATION_FAIL);
	// }
	// }
	//
	// public void processSysResetInd(MessageFrame frame) {
	// SysResetResMessageFrame sysRestFrame = (SysResetResMessageFrame) frame;
	//
	// }
	//
	// public void processAppRegister(MessageFrame frame) {
	//
	// }
	//
	// public void processStartZb(MessageFrame frame) {
	//
	// }
	//
	// public void processStartChangeInd(MessageFrame frame) {
	// ZdoStartChangeIdoMessageFrame startChangeFrame =
	// (ZdoStartChangeIdoMessageFrame) frame;
	//
	// }
	//
	// public void processStartConfirm(MessageFrame frame) {
	// ZbStartConfirmMessageFrame startConfirmFrame =
	// (ZbStartConfirmMessageFrame) frame;
	//
	// }
	//
	// public void processEndDeviceAnnceInd(MessageFrame frame) {
	// ZdoEndDeviceAnnceIndMessageFrame endDeviceFrame =
	// (ZdoEndDeviceAnnceIndMessageFrame) frame;
	//
	// }
	//
	// public void processReceiveDataIndcation(MessageFrame frame) {
	// ZbReceiveDataIndicationMessageFrame receoveData =
	// (ZbReceiveDataIndicationMessageFrame) frame;
	//
	// }

}

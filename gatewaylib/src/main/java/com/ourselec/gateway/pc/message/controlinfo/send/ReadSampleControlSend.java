package com.ourselec.gateway.pc.message.controlinfo.send;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.ourselec.gateway.message.MessageSend;
import com.ourselec.gateway.pc.message.controlinfo.ReadSampleControl;
/**
 * 读取采样控制发送器
 * @author yangtianfei(ytf2737179@163.com)
 */
public class ReadSampleControlSend extends MessageSend {

	public ReadSampleControlSend(Object message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte[] getMessageBuffer() {
		ReadSampleControl readSampleDataControl = (ReadSampleControl) message;
		ByteBuffer bBuffer = ByteBuffer.allocate(3);
		bBuffer.order(ByteOrder.LITTLE_ENDIAN);
		bBuffer.put(Integer.valueOf(readSampleDataControl.getCommand())
				.byteValue());
		bBuffer.put(Integer.valueOf(readSampleDataControl.getDevInx())
				.byteValue());
		bBuffer.put(Integer.valueOf(readSampleDataControl.getOpCode())
				.byteValue());

		bBuffer.rewind();
		byte[] buffer = new byte[bBuffer.remaining()];
		bBuffer.get(buffer);
		// this.mOutputStream.write(buffer);
		// this.mOutputStream.flush();
		return buffer;
	}

}
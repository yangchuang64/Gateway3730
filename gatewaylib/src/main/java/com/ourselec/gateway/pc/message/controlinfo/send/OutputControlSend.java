package com.ourselec.gateway.pc.message.controlinfo.send;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.ourselec.gateway.message.MessageSend;
import com.ourselec.gateway.pc.message.controlinfo.OutputControl;
/**
 * 输入控制发送器
 * @author yangtianfei(ytf2737179@163.com)
 */
public class OutputControlSend extends MessageSend {

	public OutputControlSend(Object message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte[] getMessageBuffer() {
		OutputControl outputControl = (OutputControl) message;
		ByteBuffer bBuffer = ByteBuffer.allocate(8);
		bBuffer.order(ByteOrder.LITTLE_ENDIAN);
		bBuffer.put(Integer.valueOf(outputControl.getCommand()).byteValue());
		bBuffer.put(Integer.valueOf(outputControl.getDevInx()).byteValue());
		bBuffer.putShort(Integer.valueOf(outputControl.getDevParam())
				.shortValue());
		bBuffer.put(outputControl.getData());

		bBuffer.rewind();
		byte[] buffer = new byte[bBuffer.remaining()];
		bBuffer.get(buffer);
		// this.mOutputStream.write(buffer);
		// this.mOutputStream.flush();
		return buffer;
	}

}
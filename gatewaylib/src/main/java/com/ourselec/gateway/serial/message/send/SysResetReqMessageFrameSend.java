package com.ourselec.gateway.serial.message.send;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.ourselec.gateway.serial.message.MessageFrame;
import com.ourselec.gateway.serial.message.SysResetReqMessageFrame;
/**
 * reset请求消息Frame发送器
 *
 */
public class SysResetReqMessageFrameSend extends MessageFrameSend {

	public SysResetReqMessageFrameSend(MessageFrame messageFrame) {
		super(messageFrame);
	}

	@Override
	public byte[] getMessageBuffer() {
		SysResetReqMessageFrame sysResetMf = (SysResetReqMessageFrame) message;
		ByteBuffer bBuffer = ByteBuffer.allocate(3 + sysResetMf.getLength());
		bBuffer.order(ByteOrder.LITTLE_ENDIAN);
		bBuffer.put((byte) sysResetMf.getLength());
		bBuffer.putShort(Integer.valueOf(sysResetMf.getCommand()).shortValue());
		bBuffer.put(Integer.valueOf(sysResetMf.getType()).byteValue());
		bBuffer.rewind();
		byte[] buffer = new byte[bBuffer.remaining()];
		bBuffer.get(buffer);
		// this.mOutputStream.write(buffer);
		// this.mOutputStream.flush();
		return buffer;
	}
}
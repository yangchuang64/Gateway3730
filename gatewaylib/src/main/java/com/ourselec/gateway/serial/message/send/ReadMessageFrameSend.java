package com.ourselec.gateway.serial.message.send;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.ourselec.gateway.serial.message.MessageFrame;
import com.ourselec.gateway.serial.message.ReadMessageFrame;
/**
 * 读取消息Frame发送器
 *
 */
public class ReadMessageFrameSend extends MessageFrameSend {

	public ReadMessageFrameSend(MessageFrame messageFrame) {
		super(messageFrame);
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte[] getMessageBuffer() {
		ReadMessageFrame readFrame = (ReadMessageFrame) message;
		ByteBuffer bBuffer = ByteBuffer.allocate(3 + readFrame.getLength());
		bBuffer.order(ByteOrder.LITTLE_ENDIAN);
		bBuffer.put((byte) readFrame.getLength());
		bBuffer.putShort(Integer.valueOf(readFrame.getCommand()).shortValue());
		bBuffer.put(Integer.valueOf(readFrame.getConfigId()).byteValue());

		bBuffer.rewind();
		byte[] buffer = new byte[bBuffer.remaining()];
		bBuffer.get(buffer);
		return buffer;
	}
}
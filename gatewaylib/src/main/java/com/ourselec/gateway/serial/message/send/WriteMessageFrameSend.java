package com.ourselec.gateway.serial.message.send;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.ourselec.gateway.serial.message.MessageFrame;
import com.ourselec.gateway.serial.message.WriteMessageFrame;
/**
 * 写入消息Frame发送器
 *
 */
public class WriteMessageFrameSend extends MessageFrameSend {

	public WriteMessageFrameSend(MessageFrame messageFrame) {
		super(messageFrame);
	}

	@Override
	public byte[] getMessageBuffer() {
		WriteMessageFrame writeMf = (WriteMessageFrame) message;
		ByteBuffer bBuffer = ByteBuffer.allocate(3 + writeMf.getLength());
		bBuffer.order(ByteOrder.LITTLE_ENDIAN);
		bBuffer.put((byte) writeMf.getLength());
		bBuffer.putShort(Integer.valueOf(writeMf.getCommand()).shortValue());
		bBuffer.put(Integer.valueOf(writeMf.getConfigId()).byteValue());
		bBuffer.put(Integer.valueOf(writeMf.getValueLen()).byteValue());
		bBuffer.put(writeMf.getValue());
		bBuffer.rewind();
		byte[] buffer = new byte[bBuffer.remaining()];
		bBuffer.get(buffer);
		// this.mOutputStream.write(buffer);
		// this.mOutputStream.flush();
		return buffer;
	}
}
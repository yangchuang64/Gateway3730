package com.ourselec.gateway.serial.message.send;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.ourselec.gateway.message.MessageSend;
import com.ourselec.gateway.serial.message.MessageFrame;
/**
 * 消息Frame发送器
 *
 */
public class MessageFrameSend extends MessageSend {

	public MessageFrameSend(MessageFrame messageFrame) {
		super(messageFrame);
	}

	public byte[] getMessageBuffer() {
		MessageFrame messageFrame = (MessageFrame) message;
		ByteBuffer bBuffer = ByteBuffer.allocate(3 + messageFrame.getLength());
		bBuffer.order(ByteOrder.LITTLE_ENDIAN);
		bBuffer.put((byte) messageFrame.getLength());
		bBuffer.putShort(Integer.valueOf(messageFrame.getCommand())
				.shortValue());
		bBuffer.rewind();
		byte[] buffer = new byte[bBuffer.remaining()];
		bBuffer.get(buffer);
		// this.mOutputStream.write(buffer);
		// this.mOutputStream.flush();
		return buffer;
	}
}
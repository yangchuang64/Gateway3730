package com.ourselec.gateway.serial.message.resolver;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.ourselec.gateway.message.MessageResolver;
import com.ourselec.gateway.serial.message.MessageFrame;
/**
 * Frame解析器
 *
 */
public class MessageFrameResolver extends MessageResolver {
	int command;

	public MessageFrameResolver(int command, byte[] buffer, int length) {
		super(buffer, length);
		this.command = command;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object parsorMessage() {
		ByteBuffer bb = ByteBuffer.allocate(length);
		bb.put(buffer);
		bb.order(ByteOrder.LITTLE_ENDIAN);
		bb.flip();
		return new MessageFrame(length, command);
	}

}
package com.ourselec.gateway.serial.message.resolver;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.ourselec.gateway.message.MessageResolver;
import com.ourselec.gateway.serial.message.ResponseMessageFrame;
/**
 * 回复消息Frame解析器
 *
 */
public class ResponseMessageFrameResolver extends MessageFrameResolver {

	public ResponseMessageFrameResolver(int command, byte[] buffer,
										int length) {
		super(command, buffer, length);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object parsorMessage() {
		ByteBuffer bb = ByteBuffer.allocate(length);
		bb.put(buffer);
		bb.order(ByteOrder.LITTLE_ENDIAN);
		bb.flip();
		int status = 0xff & bb.get();
		return new ResponseMessageFrame(length, command, status);
	}

}
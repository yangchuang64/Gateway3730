package com.ourselec.gateway.serial.message.resolver;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.ourselec.gateway.message.MessageResolver;
import com.ourselec.gateway.serial.message.ReadResponseMessageFrame;
/**
 * 读取回复消息Frame解析器
 *
 */
public class ReadResponseMessageFrameResolver extends MessageFrameResolver {

	public ReadResponseMessageFrameResolver(int command, byte[] buffer,
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
		int configId = 0xff & bb.get();
		int len = 0xff & bb.get();
		byte[] value = new byte[bb.remaining()];
		bb.get(value);
		return new ReadResponseMessageFrame(length, command, status, configId,
				len, value);
	}

}
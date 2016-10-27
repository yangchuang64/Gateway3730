package com.ourselec.gateway.serial.message.resolver;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.ourselec.gateway.message.MessageResolver;
import com.ourselec.gateway.serial.message.MessageFrame;
import com.ourselec.gateway.serial.message.ZnpUartSerialMessage;
/**
 * Uart消息解析器
 *
 */
public class ZnpUartMessageResolver extends MessageResolver {

	public ZnpUartMessageResolver(byte[] buffer, int length) {
		super(buffer, length);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ZnpUartSerialMessage parsorMessage() {
		ByteBuffer bb = ByteBuffer.allocate(length);
		bb.put(buffer);
		bb.order(ByteOrder.LITTLE_ENDIAN);
		bb.flip();

		int sof = 0xff & bb.get();
		int length = 0xff & bb.get();
		int command = 0xffff & bb.getShort();
		byte[] data = new byte[length];
		bb.get(data);

		MessageResolver messageResovler = MessageFrameResolverFactory
				.getMessageResolver(command, data, length);
		if (messageResovler == null)
			return null;
		MessageFrame frame = (MessageFrame) messageResovler.parsorMessage();
		byte fcs = bb.get();
		return new ZnpUartSerialMessage(frame, fcs);
	}
}
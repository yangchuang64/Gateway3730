package com.ourselec.gateway.serial.message.resolver;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.ourselec.gateway.message.MessageResolver;
import com.ourselec.gateway.pc.message.DataBaseMessage;
import com.ourselec.gateway.pc.message.serial.resolver.SerialDataMessageResolverFactroy;
import com.ourselec.gateway.serial.message.ZbReceiveDataIndicationMessageFrame;

/**
 * 接受数据消息Frame解析器
 *
 */
public class ZbReceiveDataIndicationMessageFrameResolver extends
		MessageFrameResolver {

	public ZbReceiveDataIndicationMessageFrameResolver(int command,
													   byte[] buffer, int length) {
		super(command, buffer, length);
	}

	@Override
	public Object parsorMessage() {
		ByteBuffer bb = ByteBuffer.allocate(length);
		bb.put(buffer);
		bb.order(ByteOrder.LITTLE_ENDIAN);
		bb.flip();
		int source = 0xffff & bb.getShort();
		int dataCommand = 0xffff & bb.getShort();
		int dataLen = 0xffff & bb.getShort();
		int dataHead = 0xff & bb.get();
		int dataLength = 0xff & bb.get();
		int dataCode = 0xffff & bb.getShort();
		byte[] surplusData = new byte[dataLength - 4];
		bb.get(surplusData);
		MessageResolver messageResolver = SerialDataMessageResolverFactroy
				.getDataMessageResolver(source, dataLen, dataCode, surplusData,
						surplusData.length);
		if (messageResolver == null)
			return null;
		DataBaseMessage dataMessage = (DataBaseMessage) messageResolver
				.parsorMessage();
		return new ZbReceiveDataIndicationMessageFrame(length, command, source,
				dataCommand, dataLen, dataMessage);
	}
}
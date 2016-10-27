package com.ourselec.gateway.serial.message.resolver;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.ourselec.gateway.message.MessageResolver;
import com.ourselec.gateway.serial.message.ReadResponseMessageFrame;
import com.ourselec.gateway.serial.message.SysResetResMessageFrame;
/**
 * reset回复消息Frame解析器
 *
 */
public class SysResetResMessageFrameResolver extends MessageFrameResolver {

	public SysResetResMessageFrameResolver(int command, byte[] buffer,
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
		int reason = 0xff & bb.get();
		int transportRev = 0xff & bb.get();
		int productId = 0xff & bb.get();
		int majorRel = 0xff & bb.get();
		int minorRel = 0xff & bb.get();
		int hwRev = 0xff & bb.get();
		return new SysResetResMessageFrame(length, command, reason,
				transportRev, productId, majorRel, minorRel, hwRev);
	}

}
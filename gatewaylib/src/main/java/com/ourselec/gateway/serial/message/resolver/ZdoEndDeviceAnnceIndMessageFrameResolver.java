package com.ourselec.gateway.serial.message.resolver;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.ourselec.gateway.serial.message.MessageFrame;
import com.ourselec.gateway.serial.message.ZdoEndDeviceAnnceIndMessageFrame;
/**
 * 建网消息Frame解析器
 *
 */
public class ZdoEndDeviceAnnceIndMessageFrameResolver extends MessageFrameResolver {

	public ZdoEndDeviceAnnceIndMessageFrameResolver(int command, byte[] buffer,
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
		int srcAddr = 0xffff & bb.getShort();
		int nwkAddr = 0xffff & bb.getShort();
		byte[] ieeeAddr = new byte[8];
		bb.get(ieeeAddr);
		int capabilites = 0xff & bb.get();
		return new ZdoEndDeviceAnnceIndMessageFrame(length, command, srcAddr,
				nwkAddr, ieeeAddr, capabilites);
	}
}
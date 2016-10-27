package com.ourselec.gateway.pc.message.serial.resolver;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.ourselec.gateway.pc.message.SensorBaseInfoResponseMessage;

/**
 * 传感器基本信息回复消息解析器
 * @author yangtianfei(ytf2737179@163.com)
 */
public class SensorBaseInfoResponseMessageResolver extends
		SerialDataMessageResovler {

	public SensorBaseInfoResponseMessageResolver(int source, int dataLen,
												 int code, byte[] buffer, int length) {
		super(source, dataLen, code, buffer, length);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object parsorMessage() {
		// TODO Auto-generated method stub
		ByteBuffer bb = ByteBuffer.allocate(length);
		bb.put(buffer);
		bb.order(ByteOrder.LITTLE_ENDIAN);
		bb.flip();
		int pandId = 0xffff & bb.getShort();
		int deviceType = 0xff & bb.get();
		int channel = 0xff & bb.get();
		byte[] ieeeAddr = new byte[8];
		bb.get(ieeeAddr);
		byte[] fIeeeAddr = new byte[8];
		bb.get(fIeeeAddr);
		int checkNum = 0xff & bb.get();
		return new SensorBaseInfoResponseMessage(dataLen + 5, code, checkNum,
				pandId, deviceType, channel, ieeeAddr, fIeeeAddr, 0x00, source);
	}
}
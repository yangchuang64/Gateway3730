package com.ourselec.gateway.pc.message.resolver;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.ourselec.gateway.pc.message.PcControlSensorMessage;
import com.ourselec.gateway.pc.message.PcTedsInfoRequestMessage;
/**
 * PC控制传感器消息解析器
 * @author yangtianfei(ytf2737179@163.com)
 */
public class PcControlSensorMessageResovler extends DataBaseMessageResovler {

	public PcControlSensorMessageResovler(int msgLen, int code, int checkNum,
										  byte[] buffer, int length) {
		super(msgLen, code, checkNum, buffer, length);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object parsorMessage() {
		ByteBuffer bb = ByteBuffer.allocate(length);
		bb.put(buffer);
		bb.order(ByteOrder.LITTLE_ENDIAN);
		bb.flip();

		int deviceId = bb.getShort();
		byte[] controlInfo = new byte[length - 6];
		bb.get(controlInfo);
		int noteTransferType = bb.getShort();
		int noteAddress = bb.getShort();
		return new PcControlSensorMessage(msgLen, code, checkNum, deviceId,
				controlInfo, noteTransferType, noteAddress);

	}
}
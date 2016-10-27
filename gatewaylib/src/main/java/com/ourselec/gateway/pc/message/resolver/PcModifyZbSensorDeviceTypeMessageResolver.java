package com.ourselec.gateway.pc.message.resolver;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.ourselec.gateway.pc.message.PcModifyZbSensorDeviceTypeMessage;
/**
 * PC修改传感器设备类型消息解析器
 * @author yangtianfei(ytf2737179@163.com)
 */
public class PcModifyZbSensorDeviceTypeMessageResolver extends
		DataBaseMessageResovler {

	public PcModifyZbSensorDeviceTypeMessageResolver(int msgLen, int code,
													 int checkNum, byte[] buffer, int length) {
		super(msgLen, code, checkNum, buffer, length);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object parsorMessage() {
		ByteBuffer bb = ByteBuffer.allocate(length);
		bb.put(buffer);
		bb.order(ByteOrder.LITTLE_ENDIAN);
		bb.flip();

		int deviceType = bb.get();
		int noteAddress = bb.getShort();
		return new PcModifyZbSensorDeviceTypeMessage(msgLen, code, checkNum,
				deviceType, noteAddress);

	}
}
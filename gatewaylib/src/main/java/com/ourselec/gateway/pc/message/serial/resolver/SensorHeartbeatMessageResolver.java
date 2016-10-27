package com.ourselec.gateway.pc.message.serial.resolver;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.ourselec.gateway.message.MessageResolver;
import com.ourselec.gateway.pc.message.SensorHeartbeatMessage;
/**
 * 传感器心跳消息解析器
 * @author yangtianfei(ytf2737179@163.com)
 */
public class SensorHeartbeatMessageResolver extends SerialDataMessageResovler {

	public SensorHeartbeatMessageResolver(int source, int dataLen, int code,
										  byte[] buffer, int length) {
		super(source, dataLen, code, buffer, length);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object parsorMessage() {
		ByteBuffer bb = ByteBuffer.allocate(length);
		bb.put(buffer);
		bb.order(ByteOrder.LITTLE_ENDIAN);
		bb.flip();

		int heartbeatInfo = 0xffff & bb.getShort();
		int sensorFlag = 0xffff & bb.getShort();
		int checkNum = 0xff & bb.get();
		return new SensorHeartbeatMessage(dataLen + 7, code, checkNum,
				heartbeatInfo, sensorFlag, 0x00, 0x00, source);
		// int head = 0xff & bb.get();
		// int length =
		//
		// int[] data = new int[length / 2];
		// for (int i = 0; i < data.length; i++)
		// data[i] = 0xffff & bb.getShort();
		// return new ADSampleData(resourceCode, data);
	}
}
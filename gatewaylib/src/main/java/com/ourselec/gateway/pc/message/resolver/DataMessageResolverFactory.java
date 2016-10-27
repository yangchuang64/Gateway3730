package com.ourselec.gateway.pc.message.resolver;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.ourselec.gateway.message.MessageResolver;
import com.ourselec.gateway.pc.message.DataMessageInfo;
import com.ourselec.gateway.pc.message.PcModifyZbSensorChannelMessage;

public class DataMessageResolverFactory {

	public static MessageResolver getDataMessageResolver(byte[] buffer,
			int length) {
		ByteBuffer bb = ByteBuffer.allocate(length);
		bb.put(buffer);
		bb.order(ByteOrder.LITTLE_ENDIAN);
		bb.flip();

		int head = 0xff & bb.get();
		int msgLen = 0xff & bb.get();
		int code = 0xffff & bb.getShort();
		// System.out.println(Integer.toHexString(code));
		byte[] data = new byte[0];
		if (msgLen - 5 > 0) {
			data = new byte[msgLen - 5];
			// System.out.println("msgLen:" + (msgLen - 5));
			bb.get(data);
		}
		int checkNum = bb.get();
		switch (code) {
		case DataMessageInfo.PcHeartbeatCode:
			return new DataBaseMessageResovler(msgLen, code, checkNum, data,
					data.length);
		case DataMessageInfo.PcReqTedsCode:
			return new PcTedsInfoRequestMessageResovler(msgLen, code, checkNum,
					data, data.length);
		case DataMessageInfo.PcControlCode:
			return new PcControlSensorMessageResovler(msgLen, code, checkNum,
					data, data.length);
		case DataMessageInfo.PcRequestZbBaseInfoCode:
			return new PcRequestSensorBaseInfoMessageResolver(msgLen, code,
					checkNum, data, data.length);
		case DataMessageInfo.PcModifyZbSensorDeviceTypeCode:
			return new PcModifyZbSensorDeviceTypeMessageResolver(msgLen, code,
					checkNum, data, data.length);
		case DataMessageInfo.PcModifyZbPanIdCode:
			return new PcModifyZbSensorPanIdMessageResolver(msgLen, code,
					checkNum, data, data.length);
		case DataMessageInfo.PcModifyZbChannelCode:
			return new PcModifyZbSensorChannelMessageResolver(msgLen, code,
					checkNum, data, data.length);
		}
		return null;
	}
}

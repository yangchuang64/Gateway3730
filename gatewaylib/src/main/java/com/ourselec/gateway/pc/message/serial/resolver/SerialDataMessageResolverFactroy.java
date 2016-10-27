package com.ourselec.gateway.pc.message.serial.resolver;

import com.ourselec.gateway.message.MessageResolver;
import com.ourselec.gateway.pc.message.DataMessageInfo;
import com.ourselec.gateway.pc.message.SensorModifyPanIdResponseMessage;

public class SerialDataMessageResolverFactroy {

	public static MessageResolver getDataMessageResolver(int source,
			int dataLen, int code, byte[] buffer, int length) {
		switch (code) {
		case DataMessageInfo.SensorHeartbeatCode:
			return new SensorHeartbeatMessageResolver(source, dataLen, code,
					buffer, length);
		case DataMessageInfo.SensorRspTedsCode:
			return new SensorTedsInfoResponseResolver(source, dataLen, code,
					buffer, length);
		case DataMessageInfo.SensorRspControlCode:
			return new SensorControlResponseMessageResolver(source, dataLen,
					code, buffer, length);
		case DataMessageInfo.SensorUploadDataCode:
			return new SensorUploadDataMessageResolver(source, dataLen, code,
					buffer, length);
		case DataMessageInfo.SensorSendEndCode:
			return new SensorSendTranferEndMessageResolver(source, dataLen,
					code, buffer, length);
		case DataMessageInfo.SensorBaseInfoRspCode:
			return new SensorBaseInfoResponseMessageResolver(source, dataLen,
					code, buffer, length);
		case DataMessageInfo.sensorModifyDeviceTypeRspCode:
			return new SensorModifyDeviceTypeRspMessageResovler(source,
					dataLen, code, buffer, length);
		case DataMessageInfo.SensorModifyPanIdRspCode:
			return new SensorModifyPanIdRspMessageResolver(source, dataLen,
					code, buffer, length);
		case DataMessageInfo.SensorModifyChannelRspCode:
			return new SensorModifyChannelRspMessageResolver(source, dataLen,
					code, buffer, length);
		}
		return null;
	}
}

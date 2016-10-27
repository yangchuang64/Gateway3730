package com.ourselec.gateway.pc.message.send;

import com.ourselec.gateway.message.MessageSend;
import com.ourselec.gateway.pc.message.DataBaseMessage;
import com.ourselec.gateway.pc.message.DataMessageInfo;
import com.ourselec.gateway.pc.message.serial.send.PcControlSensorMessageSend;
import com.ourselec.gateway.pc.message.serial.send.PcTedsInfoRequestMessageSend;
public class DataMessageSendFactroy {
	public static MessageSend getDataMessageSend(DataBaseMessage message) {
		switch (message.getCommand()) {
		case DataMessageInfo.GateWayHeartbeatCode:
			return new GatewayHeartbeatMessageSend(message);
		case DataMessageInfo.SensorHeartbeatCode:
			return new SensorHeartbeatMessageSend(message);
		case DataMessageInfo.SensorRspTedsCode:
			return new SensorTedsInfoResponseSend(message);
		case DataMessageInfo.SensorRspControlCode:
			return new SensorControlResponseMessageSend(message);
		case DataMessageInfo.SensorSendEndCode:
			return new SensorSendTranferEndMessageSend(message);
		case DataMessageInfo.sensorModifyDeviceTypeRspCode:
		case DataMessageInfo.SensorModifyPanIdRspCode:
		case DataMessageInfo.SensorModifyChannelRspCode:
			return null;
		case DataMessageInfo.SensorUploadDataCode:
			return new SensorUploadDataMessageSend(message);
		}
		return null;
	}
}

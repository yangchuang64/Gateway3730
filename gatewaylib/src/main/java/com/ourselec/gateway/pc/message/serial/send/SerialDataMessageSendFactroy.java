package com.ourselec.gateway.pc.message.serial.send;

import com.ourselec.gateway.message.MessageSend;
import com.ourselec.gateway.pc.message.DataBaseMessage;
import com.ourselec.gateway.pc.message.DataMessageInfo;
import com.ourselec.gateway.pc.message.send.GatewayHeartbeatMessageSend;
import com.ourselec.gateway.pc.message.send.SensorControlResponseMessageSend;
import com.ourselec.gateway.pc.message.send.SensorHeartbeatMessageSend;
import com.ourselec.gateway.pc.message.send.SensorTedsInfoResponseSend;
import com.ourselec.gateway.pc.message.send.SensorUploadDataMessageSend;

public class SerialDataMessageSendFactroy {

	public static MessageSend getMessageSend(DataBaseMessage message) {
		switch (message.getCommand()) {
		// case DataMessageInfo.GateWayHeartbeatCode:
		// return new HeartbeatMessageSend(message);
		// case DataMessageInfo.SensorHeartbeatCode:
		// return new SensorHeartbeatMessageSend(message);
		case DataMessageInfo.PcReqTedsCode:
			return new PcTedsInfoRequestMessageSend(message);
			// case DataMessageInfo.SensorRspTedsCode:
			// return new SensorTedsInfoResponseSend(message);
		case DataMessageInfo.PcControlCode:
			return new PcControlSensorMessageSend(message);
			// case DataMessageInfo.SensorRspControlCode:
			// return new SensorControlResponseMessageSend(message);
			// case DataMessageInfo.SensorUploadDataCode:
			// return new SensorUploadDataMessageSend(message);

		case DataMessageInfo.PcRequestZbBaseInfoCode:
			return new PcRequestSensorBaseInfoMessageSend(message);
		case DataMessageInfo.PcModifyZbSensorDeviceTypeCode:
			return new PcModifyZbSensorDeviceTypeMessageSend(message);
		case DataMessageInfo.PcModifyZbPanIdCode:
			return new PcModifyZbSensorPanIdMessageSend(message);
		case DataMessageInfo.PcModifyZbChannelCode:
			return new PcModifyZbSensorChannelMessageSend(message);
		}
		return null;
	}
}

package com.ourselec.gateway.serial.message.send;

import com.ourselec.gateway.serial.message.MessageFrame;
import com.ourselec.gateway.serial.message.MessageFrameInfo;

public class MessageFrameSendFactory {
	public static MessageFrameSend getMessageFrameSend(MessageFrame messageFrame) {
		switch (messageFrame.getCommand()) {
		case MessageFrameInfo.ZB_READ_CONFIGURATION:
			return new ReadMessageFrameSend(messageFrame);
		case MessageFrameInfo.ZB_WRITE_CONFIGURATION:
			return new WriteMessageFrameSend(messageFrame);
		case MessageFrameInfo.SYS_RESET_REQ:
			return new SysResetReqMessageFrameSend(messageFrame);
		case MessageFrameInfo.ZB_APP_REGISTER_REQUEST:
			return new AppRegisterRequestMessageFrameSend(messageFrame);
		case MessageFrameInfo.ZB_START_REQUEST:
			return new MessageFrameSend(messageFrame);
		case MessageFrameInfo.ZB_SEND_DATA_REQUEST:
			return new ZbSendDataRequestMessageFrameSend(messageFrame);
		}
		return null;
	}
}

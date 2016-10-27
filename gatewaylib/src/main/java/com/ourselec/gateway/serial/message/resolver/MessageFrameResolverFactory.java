package com.ourselec.gateway.serial.message.resolver;

import com.ourselec.gateway.message.MessageResolver;
import com.ourselec.gateway.serial.message.MessageFrameInfo;

public class MessageFrameResolverFactory {

	public static MessageResolver getMessageResolver(int command,
			byte[] buffer, int length) {
		switch (command) {
		case MessageFrameInfo.ZB_READ_CONFIGURATION_RESPONSE:
			return new ReadResponseMessageFrameResolver(command, buffer,
					buffer.length);
		case MessageFrameInfo.ZB_WRITE_CONFIGURATION_RESPONSE:
			return new ResponseMessageFrameResolver(command, buffer,
					buffer.length);
		case MessageFrameInfo.SYS_RESET_IND:
			return new SysResetResMessageFrameResolver(command, buffer,
					buffer.length);

		case MessageFrameInfo.ZB_APP_REGISTER_RESPONSE:
			return new ResponseMessageFrameResolver(command, buffer,
					buffer.length);
		case MessageFrameInfo.ZB_START_RESPONSE:
			return new MessageFrameResolver(command, buffer, buffer.length);
		case MessageFrameInfo.ZD0_START_CHANGE_IND:
			return new ResponseMessageFrameResolver(command, buffer,
					buffer.length);
		case MessageFrameInfo.ZB_START_CONFIRM:
			return new ResponseMessageFrameResolver(command, buffer,
					buffer.length);
		case MessageFrameInfo.ZDO_END_DEVICE_ANNCE_IND:
			return new ZdoEndDeviceAnnceIndMessageFrameResolver(command,
					buffer, buffer.length);
		case MessageFrameInfo.ZB_SEND_DATA_RESPONSE:
			return new MessageFrameResolver(command, buffer, buffer.length);
		case MessageFrameInfo.ZB_SEND_DATA_CONFIRM:
			return new ZbSendDataConfirmMessageFrameResolver(command, buffer,
					buffer.length);
		case MessageFrameInfo.ZB_RECEIVE_DATA_INDICATION:
			return new ZbReceiveDataIndicationMessageFrameResolver(command,
					buffer, buffer.length);
		}
		return null;
	}
}

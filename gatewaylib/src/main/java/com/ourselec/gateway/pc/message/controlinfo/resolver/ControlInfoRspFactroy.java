package com.ourselec.gateway.pc.message.controlinfo.resolver;

import com.ourselec.gateway.message.MessageResolver;
import com.ourselec.gateway.pc.message.controlinfo.ControlModuleCode;

public class ControlInfoRspFactroy {

	public static MessageResolver getControlInfoRspResolver(int command,
			byte[] buffer, int length) {
		switch (command) {
		case ControlModuleCode.SampleCode:
		case ControlModuleCode.SampleStateCode:
		case ControlModuleCode.ReadSampleCode:
		case ControlModuleCode.OutputCode:
			return new ControlInfoRspResolver(command, buffer, length);
			// case ControlModuleCode.
		}
		return null;
	}
}

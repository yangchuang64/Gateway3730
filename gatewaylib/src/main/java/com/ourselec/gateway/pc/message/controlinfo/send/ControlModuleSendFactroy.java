package com.ourselec.gateway.pc.message.controlinfo.send;

import com.ourselec.gateway.message.MessageSend;
import com.ourselec.gateway.pc.message.controlinfo.ControlModule;
import com.ourselec.gateway.pc.message.controlinfo.ControlModuleCode;

public class ControlModuleSendFactroy {

	public static MessageSend getMontrolModuleSend(ControlModule controlModule) {
		switch (controlModule.getCommand()) {
		case ControlModuleCode.SampleCode:
			return new SampleControlSend(controlModule);
		case ControlModuleCode.SampleStateCode:
		case ControlModuleCode.ReadSampleCode:
			return new ReadSampleControlSend(controlModule);
		case ControlModuleCode.OutputCode:
			return new OutputControlSend(controlModule);
		}
		return null;
	}
}

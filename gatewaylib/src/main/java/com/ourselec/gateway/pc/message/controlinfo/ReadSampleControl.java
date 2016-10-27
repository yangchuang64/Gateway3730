package com.ourselec.gateway.pc.message.controlinfo;

/**
 * 读取采样控制
 * @author yangtianfei(ytf2737179@163.com)
 */
public class ReadSampleControl extends ControlModule {
	private int opCode;

	public ReadSampleControl(int command, int devInx, int opCode) {
		super(command, devInx);
		this.opCode = opCode;
	}

	public int getOpCode() {
		return opCode;
	}
}
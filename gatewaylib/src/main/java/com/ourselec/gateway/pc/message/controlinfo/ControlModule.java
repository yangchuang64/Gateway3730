package com.ourselec.gateway.pc.message.controlinfo;

/**
 * 控制模板
 * @author yangtianfei(ytf2737179@163.com)
 */
public class ControlModule {
	private int command;
	private int devInx;

	public ControlModule(int command, int devInx) {
		this.command = command;
		this.devInx = devInx;
	}

	public int getCommand() {
		return command;
	}

	public int getDevInx() {
		return devInx;
	}

}
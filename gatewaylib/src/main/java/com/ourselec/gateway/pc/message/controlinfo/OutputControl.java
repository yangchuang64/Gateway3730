package com.ourselec.gateway.pc.message.controlinfo;

/**
 * 输入控制信息
 * @author yangtianfei(ytf2737179@163.com)
 */
public class OutputControl extends ControlModule {

	private int devParam;
	private byte[] data;

	public OutputControl(int command, int devInx, int devParam, byte[] data) {
		super(command, devInx);
		this.devParam = devParam;
		this.data = data;
	}

	public int getDevParam() {
		return devParam;
	}

	public byte[] getData() {
		return data;
	}

}
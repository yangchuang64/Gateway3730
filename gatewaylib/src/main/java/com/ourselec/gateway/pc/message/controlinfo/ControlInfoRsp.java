package com.ourselec.gateway.pc.message.controlinfo;

/**
 * 控制信息回复
 *
 * @author yangtianfei(ytf2737179@163.com)
 */
public class ControlInfoRsp extends ControlModule {
	private int result;

	public ControlInfoRsp(int command, int devInx, int result) {
		super(command, devInx);
		this.result = result;
	}

	public int getResult() {
		return result;
	}
}
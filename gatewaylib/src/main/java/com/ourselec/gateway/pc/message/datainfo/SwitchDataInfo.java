package com.ourselec.gateway.pc.message.datainfo;

/**
 * 开关量输入
 * @author yangtianfei(ytf2737179@163.com)
 */
public class SwitchDataInfo {
	private int inputPos;
	private int inputData;

	public SwitchDataInfo(int inputPos, int inputData) {
		this.inputPos = inputPos;
		this.inputData = inputData;
	}

	public int getInputPos() {
		return inputPos;
	}

	public int getInputData() {
		return inputData;
	}

}
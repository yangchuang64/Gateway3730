package com.ourselec.gateway.pc.message;

/**
 * 修改传感器PanId回复消息
 *
 * @author yangtianfei(ytf2737179@163.com)
 */
public class SensorModifyPanIdResponseMessage extends DataBaseMessage {

	private int revResult;
	private int gatewayProductNum;
	private int noteAddress;

	public SensorModifyPanIdResponseMessage(int length, int command,
											int checkNum, int revResult, int gatewayProductNum, int noteAddress) {
		super(length, command, checkNum);
		this.revResult = revResult;
		this.gatewayProductNum = gatewayProductNum;
		this.noteAddress = noteAddress;
	}

	public int getRevResult() {
		return revResult;
	}

	public int getGatewayProductNum() {
		return gatewayProductNum;
	}

	public void setGatewayProductNum(int gatewayProductNum) {
		this.gatewayProductNum = gatewayProductNum;
	}

	public int getNoteAddress() {
		return noteAddress;
	}

}
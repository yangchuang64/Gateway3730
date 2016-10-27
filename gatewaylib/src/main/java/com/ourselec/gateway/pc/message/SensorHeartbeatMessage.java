package com.ourselec.gateway.pc.message;

/**
 * 传感器心跳消息
 *
 * @author yangtianfei(ytf2737179@163.com)
 */
public class SensorHeartbeatMessage extends DataBaseMessage {

	private int heartbeatInfo;
	private int sensorFlag;
	private int gatewayProductNum;
	private int noteTransferType;
	private int noteAddress;

	public SensorHeartbeatMessage(int length, int command, int checkNum,
								  int heartbeatInfo, int sensorFlag, int gatewayProductNum,
								  int noteTransferType, int noteAddress) {
		super(length, command, checkNum);
		this.heartbeatInfo = heartbeatInfo;
		this.sensorFlag = sensorFlag;
		this.gatewayProductNum = gatewayProductNum;
		this.noteTransferType = noteTransferType;
		this.noteAddress = noteAddress;
	}

	public int getHeartbeatInfo() {
		return heartbeatInfo;
	}

	public int getSensorFlag() {
		return sensorFlag;
	}

	public int getGatewayProductNum() {
		return gatewayProductNum;
	}

	public void setGatewayProductNum(int gatewayProductNum) {
		this.gatewayProductNum = gatewayProductNum;
	}

	public int getNoteTransferType() {
		return noteTransferType;
	}

	public void setNoteTransferType(int noteTransferType) {
		this.noteTransferType = noteTransferType;
	}

	public int getNoteAddress() {
		return noteAddress;
	}

}
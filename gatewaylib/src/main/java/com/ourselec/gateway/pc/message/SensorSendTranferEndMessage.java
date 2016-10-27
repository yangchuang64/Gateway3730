package com.ourselec.gateway.pc.message;

/**
 * 传感器数据发送结束消息
 *
 * @author yangtianfei(ytf2737179@163.com)
 */
public class SensorSendTranferEndMessage extends DataBaseMessage {

	private int deviceId;
	private int gatewayProductNum;
	private int noteTransferType;
	private int noteAddress;

	public SensorSendTranferEndMessage(int length, int command, int checkNum,
									   int deviceId, int gatewayProductNum, int noteTransferType,
									   int noteAddress) {
		super(length, command, checkNum);
		this.deviceId = deviceId;
		this.gatewayProductNum = gatewayProductNum;
		this.noteTransferType = noteTransferType;
		this.noteAddress = noteAddress;
	}

	public int getDeviceId() {
		return deviceId;
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
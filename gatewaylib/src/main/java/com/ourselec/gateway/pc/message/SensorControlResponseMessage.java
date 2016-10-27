package com.ourselec.gateway.pc.message;

/**
 * 传感器控制回复消息
 *
 * @author yangtianfei(ytf2737179@163.com)
 */
import com.ourselec.gateway.pc.message.controlinfo.ControlModule;

public class SensorControlResponseMessage extends DataBaseMessage {
	private int deviceId;
	private ControlModule rspInfo;
	private int gatewayProductNum;
	private int noteTransferType;
	private int noteAddress;

	public SensorControlResponseMessage(int length, int command, int checkNum,
										int deviceId, ControlModule rspInfo, int gatewayProductNum,
										int noteTransferType, int noteAddress) {
		super(length, command, checkNum);
		this.deviceId = deviceId;
		this.rspInfo = rspInfo;
		this.gatewayProductNum = gatewayProductNum;
		this.noteTransferType = noteTransferType;
		this.noteAddress = noteAddress;
	}

	public int getDeviceId() {
		return deviceId;
	}

	public ControlModule getRspInfo() {
		return rspInfo;
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
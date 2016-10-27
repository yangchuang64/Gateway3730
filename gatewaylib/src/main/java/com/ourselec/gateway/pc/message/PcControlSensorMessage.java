package com.ourselec.gateway.pc.message;

import com.ourselec.gateway.pc.message.controlinfo.ControlModule;

/**
 * PC控制传感器消息
 *
 * @author yangtianfei(ytf2737179@163.com)
 */
public class PcControlSensorMessage extends DataBaseMessage {

	private int deviceId;
	// private ControlModule controInfo;
	byte[] controInfo;
	private int noteTransferType;
	private int noteAddress;

	public PcControlSensorMessage(int length, int command, int checkNum,
								  int deviceId, byte[] controInfo, int noteTransferType,
								  int noteAddress) {
		super(length, command, checkNum);
		this.deviceId = deviceId;
		this.controInfo = controInfo;
		this.noteTransferType = noteTransferType;
		this.noteAddress = noteAddress;
	}

	public int getDeviceId() {
		return deviceId;
	}

	public byte[] getControInfo() {
		return controInfo;
	}

	public int getNoteTransferType() {
		return noteTransferType;
	}

	public int getNoteAddress() {
		return noteAddress;
	}

}
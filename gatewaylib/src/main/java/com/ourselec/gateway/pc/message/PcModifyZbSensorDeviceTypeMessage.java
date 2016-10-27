package com.ourselec.gateway.pc.message;

/**
 * PC修改传感器设备类型消息
 *
 * @author yangtianfei(ytf2737179@163.com)
 */
public class PcModifyZbSensorDeviceTypeMessage extends DataBaseMessage {

	private int deviceType;
	private int noteAddress;

	public PcModifyZbSensorDeviceTypeMessage(int length, int command,
											 int checkNum, int deviceType, int noteAddress) {
		super(length, command, checkNum);
		this.deviceType = deviceType;
		this.noteAddress = noteAddress;
	}

	public int getDeviceType() {
		return deviceType;
	}

	public int getNoteAddress() {
		return noteAddress;
	}

}
package com.ourselec.gateway.pc.message;

/**
 * 传感器基本信息回复消息
 *
 * @author yangtianfei(ytf2737179@163.com)
 */
public class SensorBaseInfoResponseMessage extends DataBaseMessage {

	private int panId;
	private int deviceType;
	private int channel;
	private byte[] ieeeAddr;
	private byte[] fIEEEAddr;
	private int gatewayProductNum;
	private int noteAddress;

	public SensorBaseInfoResponseMessage(int length, int command, int checkNum,
										 int panId, int deviceType, int channel, byte[] ieeeAddr,
										 byte[] fIEEEAddr, int gatewayProductNum, int noteAddress) {
		super(length, command, checkNum);
		this.deviceType = deviceType;
		this.channel = channel;
		this.ieeeAddr = ieeeAddr;
		this.fIEEEAddr = fIEEEAddr;
		this.gatewayProductNum = gatewayProductNum;
		this.noteAddress = noteAddress;
	}

	public int getPanId() {
		return panId;
	}

	public int getDeviceType() {
		return deviceType;
	}

	public int getChannel() {
		return channel;
	}

	public byte[] getIeeeAddr() {
		return ieeeAddr;
	}

	public byte[] getfIEEEAddr() {
		return fIEEEAddr;
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
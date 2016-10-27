package com.ourselec.gateway.pc.message;
/**
 * 传感器上传数据消息
 * @author yangtianfei(ytf2737179@163.com)
 *
 */
public class SensorUploadDataMessage extends DataBaseMessage {

	private int deviceId;
	private Object dataInfo;
	private int gatewayProductNum;
	private int noteTransferType;
	private int noteAddress;

	public SensorUploadDataMessage(int length, int command, int checkNum,
								   int deviceId, Object dataInfo, int gatewayProductNum,
								   int noteTransferType, int noteAddress) {
		super(length, command, checkNum);
		this.deviceId = deviceId;
		this.dataInfo = dataInfo;
		this.gatewayProductNum = gatewayProductNum;
		this.noteTransferType = noteTransferType;
		this.noteAddress = noteAddress;
	}

	public int getDeviceId() {
		return deviceId;
	}

	public Object getDataInfo() {
		return dataInfo;
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
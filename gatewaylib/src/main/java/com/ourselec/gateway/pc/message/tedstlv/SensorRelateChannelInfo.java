package com.ourselec.gateway.pc.message.tedstlv;

/**
 * 传感器与通道相关信息
 *
 * @author yangtianfei(ytf2737179@163.com)
 */
public class SensorRelateChannelInfo {
	private int channelNo;
	private SensorPhysicalUnit physicalUnit;
	private SensorMeasureRange messureRange;
	private SensorSensitivity sensitivity;
	private OutputRange outputRange;
	// private SensorTranFunc tranFunc;
	private byte[] tranFunc;

	public SensorRelateChannelInfo(int channelNo,
								   SensorPhysicalUnit physicalUnit, SensorMeasureRange messureRange,
								   SensorSensitivity sensitivity, OutputRange outputRange,
								   byte[] tranFunc) {
		this.channelNo = channelNo;
		this.physicalUnit = physicalUnit;
		this.messureRange = messureRange;
		this.sensitivity = sensitivity;
		this.outputRange = outputRange;
		this.tranFunc = tranFunc;
	}

	public int getChannelNo() {
		return channelNo;
	}

	public SensorPhysicalUnit getPhysicalUnit() {
		return physicalUnit;
	}

	public SensorMeasureRange getMessureRange() {
		return messureRange;
	}

	public SensorSensitivity getSensitivity() {
		return sensitivity;
	}

	public OutputRange getOutputRange() {
		return outputRange;
	}

	public byte[] getTranFunc() {
		return tranFunc;
	}

}
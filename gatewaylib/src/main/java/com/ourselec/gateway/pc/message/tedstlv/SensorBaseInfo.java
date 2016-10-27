package com.ourselec.gateway.pc.message.tedstlv;
/**
 * @author yangtianfei(ytf2737179@163.com)
 */
public class SensorBaseInfo {
	private SensorVersionNumber version;
	private SensorDeviceId deviceId;
	private SensorManufacInfo manufacInfo;
	private SensorProductNum productNum;
	private SensorChannelNo channelNo;

	public SensorBaseInfo(SensorVersionNumber version, SensorDeviceId deviceId,
			SensorManufacInfo manufacInfo, SensorProductNum productNum,
			SensorChannelNo channelNo) {
		this.version = version;
		this.deviceId = deviceId;
		this.manufacInfo = manufacInfo;
		this.productNum = productNum;
		this.channelNo = channelNo;
	}

	public SensorVersionNumber getVersion() {
		return version;
	}

	public SensorDeviceId getDeviceId() {
		return deviceId;
	}

	public SensorManufacInfo getManufacInfo() {
		return manufacInfo;
	}

	public SensorProductNum getProductNum() {
		return productNum;
	}

	public SensorChannelNo getChannelNo() {
		return channelNo;
	}

}

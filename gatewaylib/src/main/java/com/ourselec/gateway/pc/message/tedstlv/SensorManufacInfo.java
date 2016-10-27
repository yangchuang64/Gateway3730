package com.ourselec.gateway.pc.message.tedstlv;
/**
 * 传感器厂商信息
 * @author yangtianfei(ytf2737179@163.com)
 */
public class SensorManufacInfo {
	private byte[] name;

	public SensorManufacInfo(byte[] name) {
		this.name = name;
	}

	public byte[] getName() {
		return name;
	}

}
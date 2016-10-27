package com.ourselec.gateway.pc.message.tedstlv;

/**
 * 传感器设备Id
 * @author yangtianfei(ytf2737179@163.com)
 */
public class SensorDeviceId {

	private int deviceId;

	public SensorDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}

	public int getDeviceId() {
		return deviceId;
	}

	public static class SersorDeviceIdInfo {
		public static final int VoltageDouble = 0x01 << 8 + 0x01;
		public static final int CurrentDouble = 0x01 << 8 + 0x02;
		public static final int Press = 0x01 << 8 + 0x03;
		public static final int Magnetism = 0x01 << 8 + 0x04;
		public static final int Photosensitive = 0x01 << 8 + 0x05;
		public static final int TemperaturHumidity = 0x02 << 8 + 0x01;
		public static final int TemperaturHumidityLight = 0x03 << 8 + 0x01;
		public static final int VoltageOutput = 0x40 << 8 + 0x01;
		public static final int Gpio = 0x41 << 8 + 0x01;
	}
}
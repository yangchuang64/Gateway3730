package com.ourselec.gateway.pc.message.tedstlv;
/**
 * 传感器通道
 * @author yangtianfei(ytf2737179@163.com)
 */
public class SensorChannelNo {
	private int channel;

	public SensorChannelNo(int channel) {
		this.channel = channel;
	}

	public int getChannel() {
		return channel;
	}

	public static class SersorChannelNoInfo {
		public static final int VoltageDouble = 0x03;
		public static final int CurrentDouble = 0x03;
		public static final int Press = 0x01;
		public static final int Magnetism = 0x01;
		public static final int TemperaturHumidityLight = 0x03;
		public static final int VoltageOutput = 0x0F;
		public static final int Gpio = 0x0F;
	}
}
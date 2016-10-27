package com.ourselec.gateway.pc.message.tedstlv;

import java.util.List;
/**
 * 灵敏度
 * @author yangtianfei(ytf2737179@163.com)
 */
public class SensorSensitivity {
	List<Sensitivity> sensitivities;

	public SensorSensitivity(List<Sensitivity> sensitivities) {
		this.sensitivities = sensitivities;
	}

	public List<Sensitivity> getSensitivities() {
		return sensitivities;
	}

	public static class Sensitivity {
		int channelNo;
		byte[] value;

		public Sensitivity(int channelNo, byte[] value) {
			this.channelNo = channelNo;
			this.value = value;
		}

		public int getChannelNo() {
			return channelNo;
		}

		public byte[] getValue() {
			return value;
		}

	}
}
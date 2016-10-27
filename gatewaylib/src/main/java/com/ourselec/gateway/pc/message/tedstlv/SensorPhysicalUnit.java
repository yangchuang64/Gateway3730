package com.ourselec.gateway.pc.message.tedstlv;

import java.util.List;
/**
 * 物理单位
 * @author yangtianfei(ytf2737179@163.com)
 */
public class SensorPhysicalUnit {
	List<Unit> value;

	public SensorPhysicalUnit(List<Unit> value) {
		this.value = value;
	}

	public List<Unit> getValue() {
		return value;
	}

	public static class Unit {
		int channelNo;
		int frist;
		int second;
		int third;
		int forth;

		public Unit(int channelNo, int frist, int second, int third, int forth) {
			this.channelNo = channelNo;
			this.frist = frist;
			this.second = second;
			this.third = third;
			this.forth = forth;
		}

		public int getChannelNo() {
			return channelNo;
		}

		public int getFrist() {
			return frist;
		}

		public int getSecond() {
			return second;
		}

		public int getThird() {
			return third;
		}

		public int getForth() {
			return forth;
		}

	}
}
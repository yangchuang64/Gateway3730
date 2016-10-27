package com.ourselec.gateway.pc.message.tedstlv;

public class Range {
	int channelNo;
	int max;
	int min;

	public Range(int channelNo, int max, int min) {
		this.channelNo = channelNo;
		this.max = max;
		this.min = min;
	}

	public int getChannelNo() {
		return channelNo;
	}

	public int getMax() {
		return max;
	}

	public int getMin() {
		return min;
	}

}

package com.ourselec.gateway.pc.message.datainfo;

public class ChannelModule {
	private int channelPos;
	private int dataCount;
	private byte[] data;

	public ChannelModule(int channelPos, int dataCount, byte[] data) {
		this.channelPos = channelPos;
		this.dataCount = dataCount;
		this.data = data;
	}

	public int getChannelPos() {
		return channelPos;
	}

	public int getDataCount() {
		return dataCount;
	}

	public byte[] getData() {
		return data;
	}

}

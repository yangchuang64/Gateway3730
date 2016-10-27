package com.ourselec.gateway.pc.message.datainfo;
/**
 * 上传采样数据的信息
 * @author yangtianfei(ytf2737179@163.com)
 */
public class SampleDataInfo {
	private int length;
	private ChannelModule[] channelModules;

	public SampleDataInfo(int length, ChannelModule[] channelModules) {
		this.length = length;
		this.channelModules = channelModules;
	}

	public int getLength() {
		return length;
	}

	public ChannelModule[] getChannelModules() {
		return channelModules;
	}

}
package com.ourselec.gateway.pc.message;

/**
 * PC修改传感器通道消息
 *
 * @author yangtianfei(ytf2737179@163.com)
 */
public class PcModifyZbSensorChannelMessage extends DataBaseMessage {

	private int channel;
	private int noteAddress;

	public PcModifyZbSensorChannelMessage(int length, int command,
										  int checkNum, int channel, int noteAddress) {
		super(length, command, checkNum);
		this.channel = channel;
		this.noteAddress = noteAddress;
	}

	public int getChannel() {
		return channel;
	}

	public int getNoteAddress() {
		return noteAddress;
	}

}
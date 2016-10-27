package com.ourselec.gateway.pc.message;

/**
 * PC请求传感器基本信息消息
 *
 * @author yangtianfei(ytf2737179@163.com)
 */
public class PcRequestSensorBaseInfoMessage extends DataBaseMessage {
	private int noteAddress;

	public PcRequestSensorBaseInfoMessage(int length, int command,
										  int checkNum, int noteAddress) {
		super(length, command, checkNum);
		this.noteAddress = noteAddress;
	}

	public int getNoteAddress() {
		return noteAddress;
	}

}
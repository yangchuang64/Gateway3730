package com.ourselec.gateway.pc.message;

/**
 * PC修改传感器PanId消息
 *
 * @author yangtianfei(ytf2737179@163.com)
 */
public class PcModifyZbSensorPanIdMessage extends DataBaseMessage {

	private int panId;
	private int noteAddress;

	public PcModifyZbSensorPanIdMessage(int length, int command, int checkNum, int panId,
										int noteAddress) {
		super(length, command, checkNum);
		this.panId = panId;
		this.noteAddress = noteAddress;
	}

	public int getPanId() {
		return panId;
	}

	public int getNoteAddress() {
		return noteAddress;
	}

}
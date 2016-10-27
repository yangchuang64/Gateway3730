package com.ourselec.gateway.pc.message;

/**
 * Pc请求Teds信息消息
 *
 * @author yangtianfei(ytf2737179@163.com)
 */
public class PcTedsInfoRequestMessage extends DataBaseMessage {
	private int reqType;
	private int sensorCh;
	private int noteTransferType;
	private int noteAddress;

	public PcTedsInfoRequestMessage(int length, int command, int checkNum,
									int reqType, int sensorCh, int noteTransferType, int noteAddress) {
		super(length, command, checkNum);
		this.reqType = reqType;
		this.sensorCh = sensorCh;
		this.noteTransferType = noteTransferType;
		this.noteAddress = noteAddress;
	}

	public int getReqType() {
		return reqType;
	}

	public int getSensorCh() {
		return sensorCh;
	}

	public int getNoteTransferType() {
		return noteTransferType;
	}

	public int getNoteAddress() {
		return noteAddress;
	}

}
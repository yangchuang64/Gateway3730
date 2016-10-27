package com.ourselec.gateway.pc.message;

import com.ourselec.gateway.pc.message.tedstlv.TedsRspInfo;
/**
 * 传感器Teds信息回复消息
 * @author yangtianfei(ytf2737179@163.com)
 *
 */
public class SensorTedsInfoResponseMessage extends DataBaseMessage {
	private TedsRspInfo rspInfo;
	private int gatewayProductNum;
	private int noteTransferType;
	private int noteAddress;

	public SensorTedsInfoResponseMessage(int length, int command, int checkNum,
										 TedsRspInfo rspInfo, int gatewayProductNum, int noteTransferType,
										 int noteAddress) {
		super(length, command, checkNum);
		this.rspInfo = rspInfo;
		this.gatewayProductNum = gatewayProductNum;
		this.noteTransferType = noteTransferType;
		this.noteAddress = noteAddress;
	}

	public TedsRspInfo getRspInfo() {
		return rspInfo;
	}

	public int getGatewayProductNum() {
		return gatewayProductNum;
	}

	public void setGatewayProductNum(int gatewayProductNum) {
		this.gatewayProductNum = gatewayProductNum;
	}

	public int getNoteTransferType() {
		return noteTransferType;
	}

	public void setNoteTransferType(int noteTransferType) {
		this.noteTransferType = noteTransferType;
	}

	public int getNoteAddress() {
		return noteAddress;
	}

}
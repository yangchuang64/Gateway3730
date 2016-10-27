package com.ourselec.gateway.serial.message;
/**
 * rest请求消息frame
 *
 */
public class SysResetReqMessageFrame extends MessageFrame {

	private int type;

	public SysResetReqMessageFrame(int length, int command, int type) {
		super(length, command);
		this.type = type;
	}

	public int getType() {
		return type;
	}

}
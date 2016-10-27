package com.ourselec.gateway.serial.message;
/**
 * 相应消息frame
 *
 */
public class ResponseMessageFrame extends MessageFrame {

	private int status;

	public ResponseMessageFrame(int length, int command, int status) {
		super(length, command);
		this.status = status;
	}

	public int getStatus() {
		return status;
	}

}
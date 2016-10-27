package com.ourselec.gateway.serial.message;
/**
 * frame消息
 *
 */
public class MessageFrame {
	private int command;
	private int length;

	public MessageFrame(int length, int command) {
		this.length = length;
		this.command = command;
	}

	public int getCommand() {
		return command;
	}

	public int getLength() {
		return length;
	}

}
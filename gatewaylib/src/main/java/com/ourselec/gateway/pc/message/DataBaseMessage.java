package com.ourselec.gateway.pc.message;

/**
 * 网关与PC消息基类
 *
 * @author yangtianfei(ytf2737179@163.com)
 */
public class DataBaseMessage {
	private final byte head = (byte) 0xFE;
	private int length;
	private int command;
	private int checkNum;

	public DataBaseMessage(int length, int command, int checkNum) {
		this.length = length;
		this.command = command;
		this.checkNum = checkNum;
	}

	public byte getHead() {
		return head;
	}

	public int getLength() {
		return length;
	}

	public int getCommand() {
		return command;
	}

	public int getCheckNum() {
		return checkNum;
	}

}
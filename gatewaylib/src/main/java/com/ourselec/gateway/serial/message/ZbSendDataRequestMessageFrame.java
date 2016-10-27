package com.ourselec.gateway.serial.message;

import com.ourselec.gateway.pc.message.DataBaseMessage;
/**
 * 发送数据请求消息Frame
 *
 */
public class ZbSendDataRequestMessageFrame extends MessageFrame {

	private int destination;
	private int commandId;
	private int handle;
	private int ack;
	private int radius;
	private int dataLen;
	private DataBaseMessage data;

	public ZbSendDataRequestMessageFrame(int length, int command,
										 int destination, int commandId, int handle, int ack, int radius,
										 int dataLen, DataBaseMessage data) {
		super(length, command);
		this.destination = destination;
		this.commandId = commandId;
		this.handle = handle;
		this.ack = ack;
		this.radius = radius;
		this.dataLen = dataLen;
		this.data = data;
	}

	public int getDestination() {
		return destination;
	}

	public int getCommandId() {
		return commandId;
	}

	public int getHandle() {
		return handle;
	}

	public int getAck() {
		return ack;
	}

	public int getRadius() {
		return radius;
	}

	public int getDataLen() {
		return dataLen;
	}

	public DataBaseMessage getData() {
		return data;
	}

}
package com.ourselec.gateway.serial.message;

import com.ourselec.gateway.pc.message.DataBaseMessage;
/**
 * 接受数据消息Frame
 *
 */
public class ZbReceiveDataIndicationMessageFrame extends MessageFrame {

	private int source;
	private int dataCommand;
	private int valueLen;
	// private byte[] value;
	DataBaseMessage value;

	public ZbReceiveDataIndicationMessageFrame(int length, int command,
											   int source, int dataCommand, int valueLen, DataBaseMessage value) {
		super(length, command);
		this.source = source;
		this.dataCommand = dataCommand;
		this.valueLen = valueLen;
		this.value = value;
	}

	public int getSource() {
		return source;
	}

	public int getDataCommand() {
		return dataCommand;
	}

	public int getValueLen() {
		return valueLen;
	}

	public DataBaseMessage getData() {
		return value;
	}

}
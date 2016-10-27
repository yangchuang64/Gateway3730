package com.ourselec.gateway.serial.message;
/**
 * 写入消息frame
 *
 */
public class WriteMessageFrame extends MessageFrame {

	private int configId;
	private int valueLen;
	private byte[] value;

	public WriteMessageFrame(int length, int command, int configId,
							 int valueLen, byte[] value) {
		super(length, command);
		this.configId = configId;
		this.valueLen = valueLen;
		this.value = value;
	}

	public int getConfigId() {
		return configId;
	}

	public int getValueLen() {
		return valueLen;
	}

	public byte[] getValue() {
		return value;
	}

}
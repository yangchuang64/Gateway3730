package com.ourselec.gateway.serial.message;
/**
 * 读取回复消息frame
 *
 */
public class ReadResponseMessageFrame extends MessageFrame {

	private int status;
	private int configId;
	private int valueLen;
	private byte[] value;

	public ReadResponseMessageFrame(int length, int command, int status,
									int configId, int len, byte[] value) {
		super(length, command);
		this.status = status;
		this.configId = configId;
		this.valueLen = len;
		this.value = value;
	}

	public int getStatus() {
		return status;
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
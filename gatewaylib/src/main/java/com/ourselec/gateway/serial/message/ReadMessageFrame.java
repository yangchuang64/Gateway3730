package com.ourselec.gateway.serial.message;
/**
 * 读取消息frame
 *
 */
public class ReadMessageFrame extends MessageFrame {

	private int configId;

	public ReadMessageFrame(int length, int command, int configId) {
		super(length, command);
		this.configId = configId;
	}

	public int getConfigId() {
		return configId;
	}

}
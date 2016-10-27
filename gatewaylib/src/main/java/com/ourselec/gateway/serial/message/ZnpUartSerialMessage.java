package com.ourselec.gateway.serial.message;
/**
 * Uart串口消息
 *
 */
public class ZnpUartSerialMessage {

	final int length = 2;
	byte sof = (byte) 0xFE;
	MessageFrame messageFrame;
	byte fcs;

	public ZnpUartSerialMessage(MessageFrame frameData, byte fcs) {
		this.messageFrame = frameData;
		this.fcs = fcs;
	}

	public byte getSOF() {
		return sof;
	}

	public byte getFCS() {
		return fcs;
	}

	public MessageFrame getMessageFrame() {
		return messageFrame;
	}

	public int getLength() {
		return length + 3 + messageFrame.getLength();
	}
}
package com.ourselec.gateway.serial.message.send;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.ourselec.gateway.serial.message.ZnpUartSerialMessage;
import com.ourselec.gateway.util.Utile;
/**
 * Uart消息发送器
 *
 */
public class ZnpUartMessageSend {

	private ZnpUartSerialMessage message;

	public ZnpUartMessageSend(ZnpUartSerialMessage message) {
		this.message = message;
	}

	public byte[] getMessageBuffer() {
		ZnpUartSerialMessage znpMessage = (ZnpUartSerialMessage) message;
		MessageFrameSend messageFrameSend = MessageFrameSendFactory
				.getMessageFrameSend(znpMessage.getMessageFrame());
//		if (messageFrameSend == null)
//			return null;

		ByteBuffer bBuffer = ByteBuffer.allocate(znpMessage.getLength());
		bBuffer.order(ByteOrder.LITTLE_ENDIAN);
		bBuffer.put(znpMessage.getSOF());
		bBuffer.put(messageFrameSend.getMessageBuffer());
		bBuffer.put(Utile.ort(messageFrameSend.getMessageBuffer()));
		bBuffer.rewind();
		byte[] buffer = new byte[bBuffer.remaining()];
		bBuffer.get(buffer);
		return buffer;
	}

}
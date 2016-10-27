package com.ourselec.gateway.serial.message.send;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.ourselec.gateway.message.MessageSend;
import com.ourselec.gateway.pc.message.DataBaseMessage;
import com.ourselec.gateway.pc.message.send.DataMessageSendFactroy;
import com.ourselec.gateway.pc.message.serial.send.SerialDataMessageSendFactroy;
import com.ourselec.gateway.serial.message.MessageFrame;
import com.ourselec.gateway.serial.message.ZbSendDataRequestMessageFrame;
/**
 * 发送数据请求消息Frame发送器
 *
 */
public class ZbSendDataRequestMessageFrameSend extends MessageFrameSend {

	public ZbSendDataRequestMessageFrameSend(MessageFrame messageFrame) {
		super(messageFrame);
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte[] getMessageBuffer() {
		ZbSendDataRequestMessageFrame zbSendDataRequestFrame = (ZbSendDataRequestMessageFrame) message;
		ByteBuffer bBuffer = ByteBuffer.allocate(3 + zbSendDataRequestFrame
				.getLength());
		bBuffer.order(ByteOrder.LITTLE_ENDIAN);
		bBuffer.put((byte) zbSendDataRequestFrame.getLength());
		bBuffer.putShort(Integer.valueOf(zbSendDataRequestFrame.getCommand())
				.shortValue());
		bBuffer.putShort(Integer.valueOf(
				zbSendDataRequestFrame.getDestination()).shortValue());
		bBuffer.putShort(Integer.valueOf(zbSendDataRequestFrame.getCommandId())
				.shortValue());
		bBuffer.put(Integer.valueOf(zbSendDataRequestFrame.getHandle())
				.byteValue());
		bBuffer.put(Integer.valueOf(zbSendDataRequestFrame.getAck())
				.byteValue());
		bBuffer.put(Integer.valueOf(zbSendDataRequestFrame.getRadius())
				.byteValue());
		bBuffer.put(Integer.valueOf(zbSendDataRequestFrame.getDataLen())
				.byteValue());

		DataBaseMessage data = zbSendDataRequestFrame.getData();
		MessageSend messageSend = SerialDataMessageSendFactroy
				.getMessageSend(data);
		// if (messageSend == null)
		// return null;
		bBuffer.put(messageSend.getMessageBuffer());
		bBuffer.rewind();
		byte[] buffer = new byte[bBuffer.remaining()];
		bBuffer.get(buffer);
		return buffer;
	}
}
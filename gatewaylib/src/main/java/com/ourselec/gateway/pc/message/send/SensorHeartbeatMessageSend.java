package com.ourselec.gateway.pc.message.send;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.ourselec.gateway.message.MessageSend;
import com.ourselec.gateway.pc.message.SensorHeartbeatMessage;
import com.ourselec.gateway.util.Utile;
/**
 * 传感器心跳消息发送器
 * @author yangtianfei(ytf2737179@163.com)
 */
public class SensorHeartbeatMessageSend extends MessageSend {

	public SensorHeartbeatMessageSend(Object message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte[] getMessageBuffer() {
		SensorHeartbeatMessage sensorHeartbeatMessage = (SensorHeartbeatMessage) message;

		ByteBuffer bBuffer = ByteBuffer.allocate(sensorHeartbeatMessage
				.getLength());
		bBuffer.order(ByteOrder.LITTLE_ENDIAN);
		bBuffer.put(sensorHeartbeatMessage.getHead());
		bBuffer.put(Integer.valueOf(sensorHeartbeatMessage.getLength())
				.byteValue());
		bBuffer.putShort(Integer.valueOf(sensorHeartbeatMessage.getCommand())
				.shortValue());
		bBuffer.putShort(Integer.valueOf(
				sensorHeartbeatMessage.getHeartbeatInfo()).shortValue());
		bBuffer.putShort(Integer
				.valueOf(sensorHeartbeatMessage.getSensorFlag()).shortValue());
		bBuffer.put(Utile.gatewayProductNum2Bytes(sensorHeartbeatMessage
				.getGatewayProductNum()));
		bBuffer.putShort(Integer.valueOf(
				sensorHeartbeatMessage.getNoteTransferType()).shortValue());
		bBuffer.putShort(Integer.valueOf(
				sensorHeartbeatMessage.getNoteAddress()).shortValue());
		byte[] data = bBuffer.array();
		bBuffer.put(Utile.ort(data, 0, data.length - 1));
		bBuffer.rewind();
		byte[] buffer = new byte[bBuffer.remaining()];
		bBuffer.get(buffer);
		return buffer;
	}

}
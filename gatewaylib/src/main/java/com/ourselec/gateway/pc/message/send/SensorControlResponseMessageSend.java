package com.ourselec.gateway.pc.message.send;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.ourselec.gateway.message.MessageSend;
import com.ourselec.gateway.pc.message.SensorControlResponseMessage;
import com.ourselec.gateway.pc.message.controlinfo.send.ControlInfoRspSend;
import com.ourselec.gateway.util.Utile;
/**
 * 传感器控制回复消息发送器
 * @author yangtianfei(ytf2737179@163.com)
 */
public class SensorControlResponseMessageSend extends MessageSend {

	public SensorControlResponseMessageSend(Object message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte[] getMessageBuffer() {
		SensorControlResponseMessage sensorControlMessage = (SensorControlResponseMessage) message;
		ByteBuffer bBuffer = ByteBuffer.allocate(sensorControlMessage
				.getLength());
		bBuffer.order(ByteOrder.LITTLE_ENDIAN);
		bBuffer.put(sensorControlMessage.getHead());
		bBuffer.put(Integer.valueOf(sensorControlMessage.getLength())
				.byteValue());
		bBuffer.putShort(Integer.valueOf(sensorControlMessage.getCommand())
				.shortValue());
		bBuffer.putShort(Integer.valueOf(sensorControlMessage.getDeviceId())
				.shortValue());
		// rspInfo
		ControlInfoRspSend controlSend = new ControlInfoRspSend(
				sensorControlMessage.getRspInfo());
		bBuffer.put(controlSend.getMessageBuffer());

		bBuffer.put(Utile.gatewayProductNum2Bytes(sensorControlMessage
				.getGatewayProductNum()));
		bBuffer.putShort(Integer.valueOf(
				sensorControlMessage.getNoteTransferType()).shortValue());
		bBuffer.putShort(Integer.valueOf(sensorControlMessage.getNoteAddress())
				.shortValue());
		byte[] data = bBuffer.array();
		bBuffer.put(Utile.ort(data, 0, data.length - 1));

		bBuffer.rewind();
		byte[] buffer = new byte[bBuffer.remaining()];
		bBuffer.get(buffer);
		// this.mOutputStream.write(buffer);
		// this.mOutputStream.flush();
		return buffer;
	}

}
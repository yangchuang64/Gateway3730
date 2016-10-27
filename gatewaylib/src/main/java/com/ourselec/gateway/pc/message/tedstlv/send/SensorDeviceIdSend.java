package com.ourselec.gateway.pc.message.tedstlv.send;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.ourselec.gateway.pc.message.tedstlv.SensorDeviceId;
/**
 * 传感器设备Id发送器
 *
 */
public class SensorDeviceIdSend extends ValueSend {

	public SensorDeviceIdSend(int length, Object message) {
		super(length, message);
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte[] getMessageBuffer() {
		SensorDeviceId sensorChannelNo = (SensorDeviceId) message;
		ByteBuffer bBuffer = ByteBuffer.allocate(length);
		bBuffer.order(ByteOrder.LITTLE_ENDIAN);
		bBuffer.putShort(Integer.valueOf(sensorChannelNo.getDeviceId())
				.shortValue());

		bBuffer.rewind();
		byte[] buffer = new byte[bBuffer.remaining()];
		bBuffer.get(buffer);
		return buffer;
	}

}
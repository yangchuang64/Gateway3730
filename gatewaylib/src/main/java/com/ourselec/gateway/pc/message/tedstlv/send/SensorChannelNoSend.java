package com.ourselec.gateway.pc.message.tedstlv.send;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.ourselec.gateway.pc.message.tedstlv.SensorChannelNo;
/**
 * 传感器通道发送器
 *
 */
public class SensorChannelNoSend extends ValueSend {

	public SensorChannelNoSend(int length, Object message) {
		super(length, message);
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte[] getMessageBuffer() {
		SensorChannelNo sensorChannelNo = (SensorChannelNo) message;
		ByteBuffer bBuffer = ByteBuffer.allocate(length);
		bBuffer.order(ByteOrder.LITTLE_ENDIAN);
		bBuffer.putShort(Integer.valueOf(sensorChannelNo.getChannel())
				.shortValue());

		bBuffer.rewind();
		byte[] buffer = new byte[bBuffer.remaining()];
		bBuffer.get(buffer);
		return buffer;
	}

}
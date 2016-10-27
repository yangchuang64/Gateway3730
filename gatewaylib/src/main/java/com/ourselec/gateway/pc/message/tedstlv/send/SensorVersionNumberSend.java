package com.ourselec.gateway.pc.message.tedstlv.send;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.ourselec.gateway.pc.message.tedstlv.SensorVersionNumber;
/**
 * 版本号发送器
 *
 */
public class SensorVersionNumberSend extends ValueSend {

	public SensorVersionNumberSend(int length, Object message) {
		super(length, message);
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte[] getMessageBuffer() {
		SensorVersionNumber sensorVersionNumber = (SensorVersionNumber) message;
		ByteBuffer bBuffer = ByteBuffer.allocate(length);
		bBuffer.order(ByteOrder.LITTLE_ENDIAN);
		bBuffer.put(Integer.valueOf(sensorVersionNumber.getIeeeNumber())
				.byteValue());
		bBuffer.put(Integer.valueOf(sensorVersionNumber.getTedsVersionNumber())
				.byteValue());
		bBuffer.put(Integer.valueOf(sensorVersionNumber.getSersorModule())
				.byteValue());
		bBuffer.put(Integer.valueOf(sensorVersionNumber.getReserved())
				.byteValue());

		bBuffer.rewind();
		byte[] buffer = new byte[bBuffer.remaining()];
		bBuffer.get(buffer);
		return buffer;
	}

}
package com.ourselec.gateway.pc.message.tedstlv.send;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.ourselec.gateway.pc.message.tedstlv.SensorProductNum;
/**
 * 产品序列号发送器
 *
 */
public class SensorProductNumSend extends ValueSend {

	public SensorProductNumSend(int length, Object message) {
		super(length, message);
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte[] getMessageBuffer() {
		SensorProductNum sensorProductNum = (SensorProductNum) message;
		ByteBuffer bBuffer = ByteBuffer.allocate(length);
		bBuffer.order(ByteOrder.LITTLE_ENDIAN);
		bBuffer.put(sensorProductNum.getProductNum());

		bBuffer.rewind();
		byte[] buffer = new byte[bBuffer.remaining()];
		bBuffer.get(buffer);
		return buffer;
	}

}
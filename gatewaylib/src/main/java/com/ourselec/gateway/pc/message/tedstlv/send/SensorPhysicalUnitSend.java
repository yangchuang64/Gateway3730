package com.ourselec.gateway.pc.message.tedstlv.send;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;

import com.ourselec.gateway.pc.message.tedstlv.SensorPhysicalUnit;
import com.ourselec.gateway.pc.message.tedstlv.SensorPhysicalUnit.Unit;
/**
 * 物理单位发送器
 *
 */
public class SensorPhysicalUnitSend extends ValueSend {

	public SensorPhysicalUnitSend(int length, Object message) {
		super(length, message);
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte[] getMessageBuffer() {
		SensorPhysicalUnit sensorPhysicalUnit = (SensorPhysicalUnit) message;
		ByteBuffer bBuffer = ByteBuffer.allocate(length);
		bBuffer.order(ByteOrder.LITTLE_ENDIAN);
		// bBuffer.put(sensorPhysicalUnit.getProductNum());
		List<Unit> value = sensorPhysicalUnit.getValue();
		for (Unit unit : value) {
			bBuffer.put(Integer.valueOf(unit.getChannelNo()).byteValue());
			bBuffer.put(Integer.valueOf(unit.getFrist()).byteValue());
			bBuffer.put(Integer.valueOf(unit.getSecond()).byteValue());
			bBuffer.put(Integer.valueOf(unit.getThird()).byteValue());
			bBuffer.put(Integer.valueOf(unit.getForth()).byteValue());
		}

		bBuffer.rewind();
		byte[] buffer = new byte[bBuffer.remaining()];
		bBuffer.get(buffer);
		return buffer;
	}

}
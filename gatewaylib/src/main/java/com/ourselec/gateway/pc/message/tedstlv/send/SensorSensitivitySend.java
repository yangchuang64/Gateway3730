package com.ourselec.gateway.pc.message.tedstlv.send;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;

import com.ourselec.gateway.pc.message.tedstlv.SensorSensitivity;
import com.ourselec.gateway.pc.message.tedstlv.SensorSensitivity.Sensitivity;
/**
 * 灵敏度发送器
 *
 */
public class SensorSensitivitySend extends ValueSend {

	public SensorSensitivitySend(int length, Object message) {
		super(length, message);
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte[] getMessageBuffer() {
		SensorSensitivity sensorSensitivity = (SensorSensitivity) message;
		ByteBuffer bBuffer = ByteBuffer.allocate(length);
		bBuffer.order(ByteOrder.LITTLE_ENDIAN);
		List<Sensitivity> sensitivities = sensorSensitivity.getSensitivities();
		for (Sensitivity sensitivity : sensitivities) {
			bBuffer.put(Integer.valueOf(sensitivity.getChannelNo()).byteValue());
			bBuffer.put(sensitivity.getValue());
		}

		bBuffer.rewind();
		byte[] buffer = new byte[bBuffer.remaining()];
		bBuffer.get(buffer);
		return buffer;
	}

}
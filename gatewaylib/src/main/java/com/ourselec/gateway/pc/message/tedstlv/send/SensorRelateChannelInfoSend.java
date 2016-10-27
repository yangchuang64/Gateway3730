package com.ourselec.gateway.pc.message.tedstlv.send;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.ourselec.gateway.pc.message.tedstlv.OutputRange;
import com.ourselec.gateway.pc.message.tedstlv.SensorMeasureRange;
import com.ourselec.gateway.pc.message.tedstlv.SensorPhysicalUnit;
import com.ourselec.gateway.pc.message.tedstlv.SensorRelateChannelInfo;
import com.ourselec.gateway.pc.message.tedstlv.SensorSensitivity;
import com.ourselec.gateway.pc.message.tedstlv.SensorTranFunc;

/**
 * 与通道相关信息发送器
 *
 */
public class SensorRelateChannelInfoSend extends ValueSend {

	public SensorRelateChannelInfoSend(int length, Object message) {
		super(length, message);
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte[] getMessageBuffer() {
		SensorRelateChannelInfo sensorRelateChannelInfo = (SensorRelateChannelInfo) message;

		ByteBuffer bBuffer = ByteBuffer.allocate(length);
		bBuffer.order(ByteOrder.LITTLE_ENDIAN);
		bBuffer.put(Integer.valueOf(sensorRelateChannelInfo.getChannelNo())
				.byteValue());
		SensorPhysicalUnit sensorPhysicalUnit = sensorRelateChannelInfo
				.getPhysicalUnit();
		bBuffer.put(new SensorPhysicalUnitSend(4, sensorPhysicalUnit)
				.getMessageBuffer());
		SensorMeasureRange sensorMeasureRange = sensorRelateChannelInfo
				.getMessureRange();
		bBuffer.put(new SensorMeasureRangeSend(4, sensorMeasureRange)
				.getMessageBuffer());
		SensorSensitivity sensorSensitivity = sensorRelateChannelInfo
				.getSensitivity();
		bBuffer.put(new SensorSensitivitySend(4, sensorSensitivity)
				.getMessageBuffer());
		OutputRange outputRange = sensorRelateChannelInfo.getOutputRange();
		bBuffer.put(new SensorOutputRangeSend(4, outputRange)
				.getMessageBuffer());
		// SensorTranFunc sensorTranFunc =
		// sensorRelateChannelInfo.getTranFunc();
		// bBuffer.put(new SensorTranFuncSend(1, sensorTranFunc)
		// .getMessageBuffer());
		bBuffer.put(sensorRelateChannelInfo.getTranFunc());

		bBuffer.rewind();
		byte[] buffer = new byte[bBuffer.remaining()];
		bBuffer.get(buffer);
		return buffer;
	}

}
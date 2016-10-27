package com.ourselec.gateway.pc.message.tedstlv.resolver;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.ourselec.gateway.message.MessageResolver;
import com.ourselec.gateway.pc.message.tedstlv.OutputRange;
import com.ourselec.gateway.pc.message.tedstlv.SensorMeasureRange;
import com.ourselec.gateway.pc.message.tedstlv.SensorPhysicalUnit;
import com.ourselec.gateway.pc.message.tedstlv.SensorRelateChannelInfo;
import com.ourselec.gateway.pc.message.tedstlv.SensorSensitivity;

/**
 * 传感器与通道相关信息解析器
 *
 * @author yangtianfei(ytf2737179@163.com)
 */
public class SensorRelateChannelInfoResovler extends MessageResolver {

	public SensorRelateChannelInfoResovler(byte[] buffer, int length) {
		super(buffer, length);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object parsorMessage() {
		// TODO Auto-generated method stub
		ByteBuffer bb = ByteBuffer.allocate(length);
		bb.put(buffer);
		bb.order(ByteOrder.LITTLE_ENDIAN);
		bb.flip();
		int channelNo = 0xff & bb.get();
		byte[] physicalUnit = new byte[4];
		bb.get(physicalUnit);
		SensorPhysicalUnit sensorPhysicalUnit = (SensorPhysicalUnit) new SensorPhysicalUnitResolver(
				physicalUnit, physicalUnit.length).parsorMessage();
		byte[] measureRange = new byte[4];
		bb.get(measureRange);
		SensorMeasureRange sensorMeasureRange = (SensorMeasureRange) new SensorMeasureRangeResolver(
				measureRange, measureRange.length).parsorMessage();
		byte[] sensitivity = new byte[4];
		bb.get(sensitivity);
		SensorSensitivity sensorSensitivity = (SensorSensitivity) new SensorSensitivityResolver(
				sensitivity, sensitivity.length).parsorMessage();
		byte[] output = new byte[4];
		bb.get(output);
		OutputRange outputRange = (OutputRange) new OutputRangeResolver(output,
				output.length).parsorMessage();
		byte[] tranFunc = new byte[1];
		tranFunc[0] = bb.get();

		return new SensorRelateChannelInfo(channelNo, sensorPhysicalUnit,
				sensorMeasureRange, sensorSensitivity, outputRange, tranFunc);
	}
}
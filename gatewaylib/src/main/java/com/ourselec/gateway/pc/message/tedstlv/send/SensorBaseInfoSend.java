package com.ourselec.gateway.pc.message.tedstlv.send;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.ourselec.gateway.pc.message.tedstlv.SensorBaseInfo;
import com.ourselec.gateway.pc.message.tedstlv.SensorChannelNo;
import com.ourselec.gateway.pc.message.tedstlv.SensorDeviceId;
import com.ourselec.gateway.pc.message.tedstlv.SensorManufacInfo;
import com.ourselec.gateway.pc.message.tedstlv.SensorProductNum;
import com.ourselec.gateway.pc.message.tedstlv.SensorVersionNumber;
/**
 * 传感器基本信息发送器
 *
 */
public class SensorBaseInfoSend extends ValueSend {

	public SensorBaseInfoSend(int length, Object message) {
		super(length, message);
	}

	@Override
	public byte[] getMessageBuffer() {
		SensorBaseInfo sensorBaseInfo = (SensorBaseInfo) message;
		ByteBuffer bBuffer = ByteBuffer.allocate(length);
		bBuffer.order(ByteOrder.LITTLE_ENDIAN);
		SensorVersionNumber sensorVersionNumber = sensorBaseInfo.getVersion();
		bBuffer.put(new SensorVersionNumberSend(4, sensorVersionNumber)
				.getMessageBuffer());
		SensorDeviceId sensorDeviceId = sensorBaseInfo.getDeviceId();
		bBuffer.put(new SensorDeviceIdSend(2, sensorDeviceId)
				.getMessageBuffer());
		SensorManufacInfo sensorManufacInfo = sensorBaseInfo.getManufacInfo();
		bBuffer.put(new SensorManufacInfoSend(4, sensorManufacInfo)
				.getMessageBuffer());
		SensorProductNum sensorProductNum = sensorBaseInfo.getProductNum();
		bBuffer.put(new SensorProductNumSend(4, sensorProductNum)
				.getMessageBuffer());
		SensorChannelNo channelNo = sensorBaseInfo.getChannelNo();
		bBuffer.put(new SensorChannelNoSend(2, channelNo).getMessageBuffer());

		bBuffer.rewind();
		byte[] buffer = new byte[bBuffer.remaining()];
		bBuffer.get(buffer);
		return buffer;
	}

}
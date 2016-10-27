package com.ourselec.gateway.pc.message.tedstlv.resolver;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.ourselec.gateway.message.MessageResolver;
import com.ourselec.gateway.pc.message.tedstlv.SensorBaseInfo;
import com.ourselec.gateway.pc.message.tedstlv.SensorChannelNo;
import com.ourselec.gateway.pc.message.tedstlv.SensorDeviceId;
import com.ourselec.gateway.pc.message.tedstlv.SensorManufacInfo;
import com.ourselec.gateway.pc.message.tedstlv.SensorProductNum;
import com.ourselec.gateway.pc.message.tedstlv.SensorVersionNumber;
import com.ourselec.gateway.util.Utile;
/**
 * 传感器基本信息解析器
 * @author yangtianfei(ytf2737179@163.com)
 */
public class SensorBaseInfoResolver extends MessageResolver {

	public SensorBaseInfoResolver(byte[] buffer, int length) {
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
		byte[] version = new byte[4];
		bb.get(version);
//		Utile.printHexString("sensor version", version);
		SensorVersionNumber sensorVersionNum = (SensorVersionNumber) new SensorVersionNumberResolver(
				version, version.length).parsorMessage();
		byte[] deviceId = new byte[2];
		bb.get(deviceId);
		SensorDeviceId sensordeviceId = (SensorDeviceId) new SensorDeviceIdResolver(
				deviceId, deviceId.length).parsorMessage();
		byte[] manufacInfo = new byte[4];
		bb.get(manufacInfo);
		SensorManufacInfo sensorManufacInfo = (SensorManufacInfo) new SensorManufacInfoResolver(
				manufacInfo, manufacInfo.length).parsorMessage();
		byte[] productNum = new byte[4];
		bb.get(productNum);
		SensorProductNum sensorProductNum = (SensorProductNum) new SensorProductNumResolver(
				productNum, productNum.length).parsorMessage();
		byte[] channelNo = new byte[2];
		bb.get(channelNo);
		SensorChannelNo sensorChannelNo = (SensorChannelNo) new SensorChannelNoResolver(
				channelNo, channelNo.length).parsorMessage();
		return new SensorBaseInfo(sensorVersionNum, sensordeviceId,
				sensorManufacInfo, sensorProductNum, sensorChannelNo);
	}

}
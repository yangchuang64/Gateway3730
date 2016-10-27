package com.ourselec.gateway.pc.message.tedstlv.resolver;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.ourselec.gateway.message.MessageResolver;
import com.ourselec.gateway.pc.message.tedstlv.SensorVersionNumber;
/**
 * 传感器设备编码解析器
 * @author yangtianfei(ytf2737179@163.com)
 */
public class SensorVersionNumberResolver extends MessageResolver {

	public SensorVersionNumberResolver(byte[] buffer, int length) {
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
		int ieee = 0xff & bb.get();
		int tedsVersionNum = 0xff & bb.get();
		int sensorModule = 0xff & bb.get();
		int reserved = 0xff & bb.get();
		return new SensorVersionNumber(ieee, tedsVersionNum, sensorModule,
				reserved);
	}

}
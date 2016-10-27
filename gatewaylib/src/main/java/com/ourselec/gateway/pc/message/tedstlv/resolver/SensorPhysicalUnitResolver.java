package com.ourselec.gateway.pc.message.tedstlv.resolver;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

import com.ourselec.gateway.message.MessageResolver;
import com.ourselec.gateway.pc.message.tedstlv.SensorPhysicalUnit;
import com.ourselec.gateway.pc.message.tedstlv.SensorPhysicalUnit.Unit;
/**
 * 传感器物理单位解析器
 * @author yangtianfei(ytf2737179@163.com)
 */
public class SensorPhysicalUnitResolver extends MessageResolver {

	public SensorPhysicalUnitResolver(byte[] buffer, int length) {
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
		List<Unit> units = new ArrayList<Unit>();
		for (int i = 0; i < length / 5; i++) {
			int channelNo = 0xff & bb.get();
			int frist = 0xff & bb.get();
			int second = 0xff & bb.get();
			int third = 0xff & bb.get();
			int forth = 0xff & bb.get();
			units.add(new Unit(channelNo, frist, second, third, forth));
		}
		return new SensorPhysicalUnit(units);
	}
}
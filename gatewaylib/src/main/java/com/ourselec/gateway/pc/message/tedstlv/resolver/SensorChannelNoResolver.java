package com.ourselec.gateway.pc.message.tedstlv.resolver;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.ourselec.gateway.message.MessageResolver;
import com.ourselec.gateway.pc.message.tedstlv.SensorChannelNo;
/**
 * 传感器通道解析器
 * @author yangtianfei(ytf2737179@163.com)
 */
public class SensorChannelNoResolver extends MessageResolver {

	public SensorChannelNoResolver(byte[] buffer, int length) {
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
		int channelNo = 0xffff & bb.getShort();
		return new SensorChannelNo(channelNo);
	}

}
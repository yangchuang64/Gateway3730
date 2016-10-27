package com.ourselec.gateway.pc.message.tedstlv.resolver;

import com.ourselec.gateway.message.MessageResolver;
import com.ourselec.gateway.pc.message.tedstlv.SensorManufacInfo;
/**
 * 传感器厂商信息解析器
 * @author yangtianfei(ytf2737179@163.com)
 */
public class SensorManufacInfoResolver extends MessageResolver {

	public SensorManufacInfoResolver(byte[] buffer, int length) {
		super(buffer, length);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object parsorMessage() {
		// TODO Auto-generated method stub
//		ByteBuffer bb = ByteBuffer.allocate(length);
//		bb.put(buffer);
//		bb.order(ByteOrder.LITTLE_ENDIAN);
//		bb.flip();
//		StringBuffer nameB = new StringBuffer();
//		for (int b : buffer) {
//			nameB.append(b);
//		}
		return new SensorManufacInfo(buffer);
	}

}
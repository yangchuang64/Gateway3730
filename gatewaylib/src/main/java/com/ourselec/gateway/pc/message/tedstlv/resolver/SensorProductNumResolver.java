package com.ourselec.gateway.pc.message.tedstlv.resolver;

import com.ourselec.gateway.message.MessageResolver;
import com.ourselec.gateway.pc.message.tedstlv.SensorProductNum;
/**
 * 传感器产品序号解析器
 * @author yangtianfei(ytf2737179@163.com)
 */
public class SensorProductNumResolver extends MessageResolver {

	public SensorProductNumResolver(byte[] buffer, int length) {
		super(buffer, length);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object parsorMessage() {
		return new SensorProductNum(buffer);
	}

}
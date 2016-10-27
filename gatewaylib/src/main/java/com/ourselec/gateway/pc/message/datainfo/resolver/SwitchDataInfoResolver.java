package com.ourselec.gateway.pc.message.datainfo.resolver;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.ourselec.gateway.message.MessageResolver;
import com.ourselec.gateway.pc.message.datainfo.SwitchDataInfo;
/**
 * 开关数据信息解析器
 * @author yangtianfei(ytf2737179@163.com)
 */
public class SwitchDataInfoResolver extends MessageResolver {

	public SwitchDataInfoResolver(byte[] buffer, int length) {
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
		int inputPos = 0xff & bb.get();
		int inputData = 0xff & bb.get();
		return new SwitchDataInfo(inputPos, inputData);
	}

}
package com.ourselec.gateway.pc.message.tedstlv.resolver;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

import com.ourselec.gateway.message.MessageResolver;
import com.ourselec.gateway.pc.message.tedstlv.SensorTranFunc;
import com.ourselec.gateway.pc.message.tedstlv.SensorTranFunc.TranFunc;
/**
 * 传感器传输功能解析器
 * @author yangtianfei(ytf2737179@163.com)
 */
public class SensorTranFuncResolver extends MessageResolver {

	public SensorTranFuncResolver(byte[] buffer, int length) {
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
		List<TranFunc> tranFuncs = new ArrayList<TranFunc>();
		for (int i = 0; i < length / 2; i++) {
			int channelNo = 0xff & bb.get();
			int type = 0xff & bb.get();
			tranFuncs.add(new TranFunc(channelNo, type));
		}
		return new SensorTranFunc(tranFuncs);
	}

}
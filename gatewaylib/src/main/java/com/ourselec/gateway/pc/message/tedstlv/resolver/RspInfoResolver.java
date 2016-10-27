package com.ourselec.gateway.pc.message.tedstlv.resolver;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.ourselec.gateway.message.MessageResolver;
import com.ourselec.gateway.pc.message.tedstlv.TedsRspInfoType;
import com.ourselec.gateway.pc.message.tedstlv.SensorVersionNumber;
/**
 * 回复信息解析器
 * @author yangtianfei(ytf2737179@163.com)
 */
public class RspInfoResolver extends MessageResolver {

	public RspInfoResolver(byte[] buffer, int length) {
		super(buffer, length);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object parsorMessage() {
		ByteBuffer bb = ByteBuffer.allocate(length);
		bb.put(buffer);
		bb.order(ByteOrder.LITTLE_ENDIAN);
		bb.flip();
		int type = 0xff & bb.get();
		int length = 0xffff & bb.getShort();
		Object value = null;
		switch (type) {
			case TedsRspInfoType.SensorVersionNumber:
				value = new SensorVersionNumber(0xff & bb.get(), 0xff & bb.get(),
						0xff & bb.get(), 0xff & bb.get());
			case TedsRspInfoType.SensorDeviceId:

		}
		return null;
	}
}
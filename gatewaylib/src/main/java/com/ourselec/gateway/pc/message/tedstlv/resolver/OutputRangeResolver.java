package com.ourselec.gateway.pc.message.tedstlv.resolver;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

import com.ourselec.gateway.message.MessageResolver;
import com.ourselec.gateway.pc.message.tedstlv.OutputRange;
import com.ourselec.gateway.pc.message.tedstlv.Range;
/**
 * 输入范围发送器
 * @author yangtianfei(ytf2737179@163.com)
 */
public class OutputRangeResolver extends MessageResolver {

	public OutputRangeResolver(byte[] buffer, int length) {
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
		List<Range> rangs = new ArrayList<Range>();
		for (int i = 0; i < length / 5; i++) {
			int channelNo = 0xff & bb.get();
			int max = 0xffff & bb.getShort();
			int min = 0xffff & bb.getShort();
			rangs.add(new Range(channelNo, max, min));
		}
		return new OutputRange(rangs);
	}

}
package com.ourselec.gateway.pc.message.datainfo.resolver;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

import com.ourselec.gateway.message.MessageResolver;
import com.ourselec.gateway.pc.message.datainfo.ChannelModule;
import com.ourselec.gateway.pc.message.datainfo.SampleDataInfo;

/**
 * 采样数据信息解析器
 *
 * @author yangtianfei(ytf2737179@163.com)
 */
public class SampleDataInfoResovler extends MessageResolver {

	public SampleDataInfoResovler(byte[] buffer, int length) {
		super(buffer, length);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object parsorMessage() {
		System.out.println("sampleDataInfoResovler:" + length);
		ByteBuffer bb = ByteBuffer.allocate(length);
		bb.put(buffer);
		bb.order(ByteOrder.LITTLE_ENDIAN);
		bb.flip();
		int Len = 0xff & bb.get();
		int remain = Len;

		List<ChannelModule> channels = new ArrayList<ChannelModule>();
		int channel = 0;
		int dataCount = 0;
		int dataLen = 0;
		byte[] data;
		while (remain > 1) {
			channel = 0xff & bb.get();
			dataCount = 0xff & bb.get();
			dataLen = dataCount * 4;
			System.out.println("dataLen" + dataLen);
			data = new byte[dataLen];
			if (remain < dataLen)
				break;
			bb.get(data);
			remain -= dataLen + 2;
			System.out.println("remain" + remain);
			channels.add(new ChannelModule(channel, dataCount, data));
		}

		// int num = (length - 1) / 6;
		// ChannelModule[] channel = null;
		// if (num > 0) {
		// channel = new ChannelModule[num];
		// for (int i = 0; i < num; i++) {
		// int channelPos = 0xff & bb.get();
		// int dataCount = 0xff & bb.get();
		// byte[] data = new byte[dataCount * 4];
		// bb.get(data);
		// channel[i] = new ChannelModule(channelPos, dataCount, data);
		// }
		// }
		ChannelModule[] channelss = new ChannelModule[channels.size()];
		for (int i = 0; i < channels.size(); i++) {
			channelss[i] = channels.get(i);
		}
		return new SampleDataInfo(Len, channelss);
	}
}
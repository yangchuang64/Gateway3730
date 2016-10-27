package com.ourselec.gateway.pc.message.datainfo.send;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.ourselec.gateway.message.MessageSend;
import com.ourselec.gateway.pc.message.datainfo.ChannelModule;
import com.ourselec.gateway.pc.message.datainfo.SampleDataInfo;
import com.ourselec.gateway.util.Utile;
/**
 * 采样数据信息发送器
 * @author yangtianfei(ytf2737179@163.com)
 */
public class SampleDataInfoSend extends MessageSend {

	public SampleDataInfoSend(Object message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte[] getMessageBuffer() {
		SampleDataInfo sensorControlMessage = (SampleDataInfo) message;
		ByteBuffer bBuffer = ByteBuffer.allocate(sensorControlMessage
				.getLength());
		System.out.println("SampleDataInfoSend"
				+ sensorControlMessage.getLength());
		bBuffer.order(ByteOrder.LITTLE_ENDIAN);

		bBuffer.put(Integer.valueOf(sensorControlMessage.getLength())
				.byteValue());
		ChannelModule[] channelModules = sensorControlMessage
				.getChannelModules();
		for (ChannelModule channelModule : channelModules) {
			bBuffer.put(Integer.valueOf(channelModule.getChannelPos())
					.byteValue());
			bBuffer.put(Integer.valueOf(channelModule.getDataCount())
					.byteValue());
			bBuffer.put(channelModule.getData());
		}

		bBuffer.rewind();
		byte[] buffer = new byte[bBuffer.remaining()];
		bBuffer.get(buffer);
		// this.mOutputStream.write(buffer);
		// this.mOutputStream.flush();
		return buffer;
	}

}
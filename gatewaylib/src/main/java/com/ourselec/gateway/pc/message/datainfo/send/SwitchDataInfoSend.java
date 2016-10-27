package com.ourselec.gateway.pc.message.datainfo.send;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.ourselec.gateway.message.MessageSend;
import com.ourselec.gateway.pc.message.datainfo.ChannelModule;
import com.ourselec.gateway.pc.message.datainfo.SampleDataInfo;
import com.ourselec.gateway.pc.message.datainfo.SwitchDataInfo;
/**
 * 开关数据信息解析器
 * @author yangtianfei(ytf2737179@163.com)
 */
public class SwitchDataInfoSend extends MessageSend {

	public SwitchDataInfoSend(Object message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte[] getMessageBuffer() {
		SwitchDataInfo sensorControlMessage = (SwitchDataInfo) message;
		ByteBuffer bBuffer = ByteBuffer.allocate(2);
		bBuffer.order(ByteOrder.LITTLE_ENDIAN);
		bBuffer.put(Integer.valueOf(sensorControlMessage.getInputPos())
				.byteValue());
		bBuffer.put(Integer.valueOf(sensorControlMessage.getInputData())
				.byteValue());
		bBuffer.rewind();
		byte[] buffer = new byte[bBuffer.remaining()];
		bBuffer.get(buffer);
		// this.mOutputStream.write(buffer);
		// this.mOutputStream.flush();
		return buffer;
	}
}
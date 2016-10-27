package com.ourselec.gateway.pc.message.serial.resolver;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.ourselec.gateway.message.MessageResolver;
import com.ourselec.gateway.pc.message.SensorControlResponseMessage;
import com.ourselec.gateway.pc.message.SensorUploadDataMessage;
import com.ourselec.gateway.pc.message.controlinfo.ControlModule;
import com.ourselec.gateway.pc.message.datainfo.resolver.DataInfoResovlerFactroy;

/**
 * 传感器上传数据消息解析器
 * @author yangtianfei(ytf2737179@163.com)
 */
public class SensorUploadDataMessageResolver extends SerialDataMessageResovler {

	public SensorUploadDataMessageResolver(int source, int dataLen, int code,
										   byte[] buffer, int length) {
		super(source, dataLen, code, buffer, length);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object parsorMessage() {
		System.out.println("SensorUploadDataMessageResolver:" + length);
		ByteBuffer bb = ByteBuffer.allocate(length);
		bb.put(buffer);
		bb.order(ByteOrder.LITTLE_ENDIAN);
		bb.flip();

		int deviceId = 0xffff & bb.getShort();
		byte[] data = new byte[length - 3];
		bb.get(data);
		MessageResolver messageResolver = DataInfoResovlerFactroy
				.getDataInfoResolver(deviceId, data, data.length);
		if (messageResolver == null)
			return null;
		Object dataInfo = messageResolver.parsorMessage();
		int checkNum = 0xff & bb.get();
		return new SensorUploadDataMessage(dataLen + 7, code, checkNum,
				deviceId, dataInfo, 0x00, 0x00, source);
	}

}
package com.ourselec.gateway.pc.message.serial.resolver;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.ourselec.gateway.message.MessageResolver;
import com.ourselec.gateway.pc.message.SensorControlResponseMessage;
import com.ourselec.gateway.pc.message.controlinfo.ControlModule;
import com.ourselec.gateway.pc.message.controlinfo.resolver.ControlInfoRspFactroy;
/**
 * 传感器控制回复消息解析器
 * @author yangtianfei(ytf2737179@163.com)
 */
public class SensorControlResponseMessageResolver extends
		SerialDataMessageResovler {

	public SensorControlResponseMessageResolver(int source, int dataLen,
												int code, byte[] buffer, int length) {
		super(source, dataLen, code, buffer, length);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object parsorMessage() {
		ByteBuffer bb = ByteBuffer.allocate(length);
		bb.put(buffer);
		bb.order(ByteOrder.LITTLE_ENDIAN);
		bb.flip();

		int deviceId = 0xffff & bb.getShort();
		// ControlModule controModule =
		int command = 0xff & bb.get();
		byte[] params = new byte[2];
		bb.get(params);
		MessageResolver messageResolver = ControlInfoRspFactroy
				.getControlInfoRspResolver(command, params, params.length);
		ControlModule controlModule = (ControlModule) messageResolver
				.parsorMessage();
		int checkNum = 0xff & bb.get();
		return new SensorControlResponseMessage(dataLen + 7, code, checkNum,
				deviceId, controlModule, 0x00, 0x00, source);
	}
}
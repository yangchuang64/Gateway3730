package com.ourselec.gateway.pc.message.controlinfo.resolver;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.ourselec.gateway.message.MessageResolver;
import com.ourselec.gateway.pc.message.controlinfo.ControlInfoRsp;
/**
 * 控制信息回复解析器
 * @author yangtianfei(ytf2737179@163.com)
 */
public class ControlInfoRspResolver extends MessageResolver {

	int command;

	public ControlInfoRspResolver(int command, byte[] buffer, int length) {
		super(buffer, length);
		this.command = command;
	}

	@Override
	public Object parsorMessage() {
		// TODO Auto-generated method stub
		ByteBuffer bb = ByteBuffer.allocate(length);
		bb.put(buffer);
		bb.order(ByteOrder.LITTLE_ENDIAN);
		bb.flip();
		int devInx = 0xff & bb.get();
		int result = 0xff & bb.get();
		return new ControlInfoRsp(command, devInx, result);
	}

}
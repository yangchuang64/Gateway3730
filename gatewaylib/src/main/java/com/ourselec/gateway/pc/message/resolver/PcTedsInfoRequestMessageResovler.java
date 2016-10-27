package com.ourselec.gateway.pc.message.resolver;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.ourselec.gateway.pc.message.PcTedsInfoRequestMessage;
/**
 * PC请求Teds信息消息解析器
 * @author yangtianfei(ytf2737179@163.com)
 */
public class PcTedsInfoRequestMessageResovler extends DataBaseMessageResovler {

	public PcTedsInfoRequestMessageResovler(int msgLen, int code, int checkNum,
											byte[] buffer, int length) {
		super(msgLen, code, checkNum, buffer, length);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object parsorMessage() {
		ByteBuffer bb = ByteBuffer.allocate(length);
		bb.put(buffer);
		bb.order(ByteOrder.LITTLE_ENDIAN);
		bb.flip();

		int reqType = bb.get();
		int sensorCh = bb.get();
		int noteTransferType = bb.getShort();
		int noteAddress = bb.getShort();
		return new PcTedsInfoRequestMessage(msgLen, code, checkNum, reqType,
				sensorCh, noteTransferType, noteAddress);

	}
}
package com.ourselec.gateway.pc.message.serial.resolver;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.ourselec.gateway.pc.message.SensorSendTranferEndMessage;
/**
 * 传感器发送数据传输结束消息解析器
 * @author yangtianfei(ytf2737179@163.com)
 */
public class SensorSendTranferEndMessageResolver extends
		SerialDataMessageResovler {

	public SensorSendTranferEndMessageResolver(int source, int dataLen,
											   int code, byte[] buffer, int length) {
		super(source, dataLen, code, buffer, length);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object parsorMessage() {
		// TODO Auto-generated method stub
		ByteBuffer bb = ByteBuffer.allocate(length);
		bb.put(buffer);
		bb.order(ByteOrder.LITTLE_ENDIAN);
		bb.flip();

		int deviceId = 0xfffff & bb.getShort();
		int checkNum = 0xff & bb.get();

		return new SensorSendTranferEndMessage(dataLen + 7, code, checkNum,
				deviceId, 0x00, 0x00, source);
	}

}
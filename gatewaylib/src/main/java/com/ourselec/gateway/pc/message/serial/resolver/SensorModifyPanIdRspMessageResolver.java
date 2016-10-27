package com.ourselec.gateway.pc.message.serial.resolver;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.ourselec.gateway.pc.message.SensorModifyDeviceTypeResponseMessage;
import com.ourselec.gateway.pc.message.SensorModifyPanIdResponseMessage;
/**
 * 传感器修改PanId回复消息解析器
 * @author yangtianfei(ytf2737179@163.com)
 */
public class SensorModifyPanIdRspMessageResolver extends
		SerialDataMessageResovler {

	public SensorModifyPanIdRspMessageResolver(int source, int dataLen,
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

		int revResult = 0xff & bb.get();
		int checkNum = 0xff & bb.get();
		return new SensorModifyPanIdResponseMessage(dataLen + 5, code,
				checkNum, revResult, 0x00, source);
	}

}
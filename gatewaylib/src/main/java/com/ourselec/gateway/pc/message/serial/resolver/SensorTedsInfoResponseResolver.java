package com.ourselec.gateway.pc.message.serial.resolver;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.ourselec.gateway.message.MessageResolver;
import com.ourselec.gateway.pc.message.SensorTedsInfoResponseMessage;
import com.ourselec.gateway.pc.message.tedstlv.TedsRspInfo;
import com.ourselec.gateway.pc.message.tedstlv.resolver.ValueResolverFactroy;
import com.ourselec.gateway.util.Utile;
/**
 * 传感器回复Teds信息消息解析器
 * @author yangtianfei(ytf2737179@163.com)
 */
public class SensorTedsInfoResponseResolver extends SerialDataMessageResovler {

	public SensorTedsInfoResponseResolver(int source, int dataLen, int code,
										  byte[] buffer, int length) {
		super(source, dataLen, code, buffer, length);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object parsorMessage() {
		ByteBuffer bb = ByteBuffer.allocate(length);
		bb.put(buffer);
		bb.order(ByteOrder.LITTLE_ENDIAN);
		bb.flip();

		int type = 0xff & bb.get();
//		System.out.println("sensorTedsInfoResponseResolver type" + type);
		int valueLen = 0xff & bb.get();
//		System.out
//				.println("sensorTedsInfoResponseResolver valueLen" + valueLen);
		byte[] values = new byte[valueLen];
		bb.get(values);
//		Utile.printHexString("sensor Values", values);
		MessageResolver valueResolver = ValueResolverFactroy.getValueResolver(
				type, values, values.length);
		Object value = valueResolver.parsorMessage();
		TedsRspInfo rspInfo = new TedsRspInfo(type, valueLen, value);
		int checkNum = 0xff & bb.get();
		return new SensorTedsInfoResponseMessage(dataLen + 7, code, checkNum, rspInfo,
				0x00, 0x00, source);
	}
}
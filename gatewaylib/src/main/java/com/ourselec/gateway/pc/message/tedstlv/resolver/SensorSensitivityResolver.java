package com.ourselec.gateway.pc.message.tedstlv.resolver;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

import com.ourselec.gateway.message.MessageResolver;
import com.ourselec.gateway.pc.message.tedstlv.SensorSensitivity;
import com.ourselec.gateway.pc.message.tedstlv.SensorSensitivity.Sensitivity;
/**
 * 传感器灵敏度解析器
 * @author yangtianfei(ytf2737179@163.com)
 */
public class SensorSensitivityResolver extends MessageResolver {

	public SensorSensitivityResolver(byte[] buffer, int length) {
		super(buffer, length);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object parsorMessage() {
		// TODO Auto-generated method stub
		ByteBuffer bb = ByteBuffer.allocate(length);
		bb.put(buffer);
		bb.order(ByteOrder.LITTLE_ENDIAN);
		bb.flip();
		List<Sensitivity> sensitivities = new ArrayList<Sensitivity>();
		for (int i = 0; i < length / 5; i++) {
			int channelNo = 0xff & bb.get();
			byte[] value = new byte[4];
			bb.get(value);
			sensitivities.add(new Sensitivity(channelNo, value));
		}
		return new SensorSensitivity(sensitivities);
	}

}
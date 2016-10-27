package com.ourselec.gateway.pc.message.tedstlv.send;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;

import com.ourselec.gateway.pc.message.tedstlv.SensorTranFunc;
import com.ourselec.gateway.pc.message.tedstlv.SensorTranFunc.TranFunc;
/**
 * 传输范围发送器
 *
 */
public class SensorTranFuncSend extends ValueSend {

	public SensorTranFuncSend(int length, Object message) {
		super(length, message);
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte[] getMessageBuffer() {
		SensorTranFunc sensorSensitivity = (SensorTranFunc) message;
		ByteBuffer bBuffer = ByteBuffer.allocate(length);
		bBuffer.order(ByteOrder.LITTLE_ENDIAN);
//		List<TranFunc> tranFuncs = sensorSensitivity.getTranFuncs();
//		for (TranFunc tranFunc : tranFuncs) {
//			bBuffer.put(Integer.valueOf(tranFunc.getChannelNo()).byteValue());
//			bBuffer.put(Integer.valueOf(tranFunc.getType()).byteValue());
//		}

		bBuffer.rewind();
		byte[] buffer = new byte[bBuffer.remaining()];
		bBuffer.get(buffer);
		return buffer;
	}

}
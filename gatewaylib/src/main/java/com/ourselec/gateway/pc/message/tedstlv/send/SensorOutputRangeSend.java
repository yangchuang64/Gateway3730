package com.ourselec.gateway.pc.message.tedstlv.send;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;

import com.ourselec.gateway.pc.message.tedstlv.OutputRange;
import com.ourselec.gateway.pc.message.tedstlv.Range;
/**
 * 输出范围发送器
 *
 */
public class SensorOutputRangeSend extends ValueSend {

	public SensorOutputRangeSend(int length, Object message) {
		super(length, message);
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte[] getMessageBuffer() {
		OutputRange sensorPhysicalUnit = (OutputRange) message;
		ByteBuffer bBuffer = ByteBuffer.allocate(length);
		bBuffer.order(ByteOrder.LITTLE_ENDIAN);
		List<Range> ranges = sensorPhysicalUnit.getRanges();
		for (Range range : ranges) {
			bBuffer.put(Integer.valueOf(range.getChannelNo()).byteValue());
			bBuffer.putShort(Integer.valueOf(range.getMax()).shortValue());
			bBuffer.putShort(Integer.valueOf(range.getMin()).shortValue());
		}

		bBuffer.rewind();
		byte[] buffer = new byte[bBuffer.remaining()];
		bBuffer.get(buffer);
		return buffer;
	}

}
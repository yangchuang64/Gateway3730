package com.ourselec.gateway.pc.message.tedstlv.send;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;

import com.ourselec.gateway.pc.message.tedstlv.Range;
import com.ourselec.gateway.pc.message.tedstlv.SensorMeasureRange;
/**
 * 测量乏味发送器
 *
 */
public class SensorMeasureRangeSend extends ValueSend {

	public SensorMeasureRangeSend(int length, Object message) {
		super(length, message);
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte[] getMessageBuffer() {
		SensorMeasureRange sensorMeasureRange = (SensorMeasureRange) message;
		ByteBuffer bBuffer = ByteBuffer.allocate(length);
		bBuffer.order(ByteOrder.LITTLE_ENDIAN);
		List<Range> ranges = sensorMeasureRange.getRangs();
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
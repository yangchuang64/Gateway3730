package com.ourselec.gateway.pc.message.controlinfo.send;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.ourselec.gateway.message.MessageSend;
import com.ourselec.gateway.pc.message.controlinfo.SampleControl;

/**
 * 采样控制发送器
 * @author yangtianfei(ytf2737179@163.com)
 */
public class SampleControlSend extends MessageSend {

	public SampleControlSend(Object message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte[] getMessageBuffer() {
		SampleControl sampleControl = (SampleControl) message;
		ByteBuffer bBuffer = ByteBuffer.allocate(24);
		bBuffer.order(ByteOrder.LITTLE_ENDIAN);
		bBuffer.put(Integer.valueOf(sampleControl.getCommand()).byteValue());
		bBuffer.put(Integer.valueOf(sampleControl.getDevInx()).byteValue());
		bBuffer.putShort(Integer.valueOf(sampleControl.getChEn()).shortValue());
		bBuffer.put(Integer.valueOf(sampleControl.getSmpPara()).byteValue());

		// bBuffer.putInt(Double.valueOf(sampleControl.getSrate()).floatValue());
		bBuffer.putInt(Float.floatToIntBits(sampleControl.getSrate()));
		bBuffer.putShort(Integer.valueOf(sampleControl.getDivRate())
				.shortValue());
		bBuffer.putShort(Integer.valueOf(sampleControl.getgGNum()).shortValue());
		bBuffer.putShort(Integer.valueOf(sampleControl.getaATime())
				.shortValue());
		bBuffer.put(Integer.valueOf(sampleControl.getsAvr()).byteValue());
		bBuffer.putInt(Float.floatToIntBits(sampleControl.getfVmax()));
		bBuffer.put(sampleControl.getfUnit().getBytes());

		bBuffer.rewind();
		byte[] buffer = new byte[bBuffer.remaining()];
		bBuffer.get(buffer);
		// this.mOutputStream.write(buffer);
		// this.mOutputStream.flush();
		return buffer;
	}

}
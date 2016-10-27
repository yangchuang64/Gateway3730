package com.ourselec.gateway.serial.message.send;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.ourselec.gateway.serial.message.AppRegisterRequestMessageFrame;
import com.ourselec.gateway.serial.message.MessageFrame;
/**
 * 注册应用端口消息Frame发送器
 *
 */
public class AppRegisterRequestMessageFrameSend extends MessageFrameSend {

	public AppRegisterRequestMessageFrameSend(MessageFrame message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte[] getMessageBuffer() {
		AppRegisterRequestMessageFrame messageFrame = (AppRegisterRequestMessageFrame) message;
		ByteBuffer bBuffer = ByteBuffer.allocate(3 + messageFrame.getLength());
		bBuffer.order(ByteOrder.LITTLE_ENDIAN);
		bBuffer.put((byte) messageFrame.getLength());
		bBuffer.putShort(Integer.valueOf(messageFrame.getCommand())
				.shortValue());
		bBuffer.put(Integer.valueOf(messageFrame.getAppEndPoint()).byteValue());
		bBuffer.putShort(Integer.valueOf(messageFrame.getAppProfileId())
				.shortValue());
		bBuffer.putShort(Integer.valueOf(messageFrame.getDeviceId())
				.shortValue());
		bBuffer.put(Integer.valueOf(messageFrame.getDeviceVersion())
				.byteValue());
		bBuffer.put(Integer.valueOf(messageFrame.getUnused()).byteValue());
		bBuffer.put(Integer.valueOf(messageFrame.getInputCommandsNum())
				.byteValue());
		bBuffer.put(messageFrame.getInputCommandsList());
		bBuffer.put(Integer.valueOf(messageFrame.getOutputCommandsNum())
				.byteValue());
		bBuffer.put(messageFrame.getOutputCommandsList());
		bBuffer.rewind();
		byte[] buffer = new byte[bBuffer.remaining()];
		bBuffer.get(buffer);
		// this.mOutputStream.write(buffer);
		// this.mOutputStream.flush();
		return buffer;
	}

}
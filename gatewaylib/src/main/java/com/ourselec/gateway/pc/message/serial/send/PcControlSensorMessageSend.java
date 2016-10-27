package com.ourselec.gateway.pc.message.serial.send;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.ourselec.gateway.message.MessageSend;
import com.ourselec.gateway.pc.message.PcControlSensorMessage;
import com.ourselec.gateway.pc.message.controlinfo.ControlModule;
import com.ourselec.gateway.pc.message.controlinfo.send.ControlInfoRspSend;
import com.ourselec.gateway.pc.message.controlinfo.send.ControlModuleSendFactroy;
import com.ourselec.gateway.util.Utile;
/**
 * PC控制传感器消息发送器
 * @author yangtianfei(ytf2737179@163.com)
 */
public class PcControlSensorMessageSend extends MessageSend {

	public PcControlSensorMessageSend(Object message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte[] getMessageBuffer() {
		PcControlSensorMessage controlMessage = (PcControlSensorMessage) message;
		ByteBuffer bBuffer = ByteBuffer.allocate(controlMessage.getLength());
		bBuffer.order(ByteOrder.LITTLE_ENDIAN);
		bBuffer.put(controlMessage.getHead());
		bBuffer.put(Integer.valueOf(controlMessage.getLength()).byteValue());
		bBuffer.putShort(Integer.valueOf(controlMessage.getCommand())
				.shortValue());
		bBuffer.putShort(Integer.valueOf(controlMessage.getDeviceId())
				.shortValue());

		// ControlModule controlModule = controlMessage.getControInfo();
		// MessageSend controlSend = ControlModuleSendFactroy
		// .getMontrolModuleSend(controlModule);
		// bBuffer.put(controlSend.getMessageBuffer());
		bBuffer.put(controlMessage.getControInfo());

		byte[] data = bBuffer.array();
		bBuffer.put(Utile.ort(data, 0, data.length - 1));

		bBuffer.rewind();
		byte[] buffer = new byte[bBuffer.remaining()];
		bBuffer.get(buffer);
		// this.mOutputStream.write(buffer);
		// this.mOutputStream.flush();
		return buffer;
	}

}
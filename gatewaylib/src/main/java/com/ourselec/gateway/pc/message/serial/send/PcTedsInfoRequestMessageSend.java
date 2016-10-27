package com.ourselec.gateway.pc.message.serial.send;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.ourselec.gateway.message.MessageSend;
import com.ourselec.gateway.pc.message.PcTedsInfoRequestMessage;
import com.ourselec.gateway.util.Utile;
/**
 * PC请求Teds信息消息发送器
 * @author yangtianfei(ytf2737179@163.com)
 */
public class PcTedsInfoRequestMessageSend extends MessageSend {

	public PcTedsInfoRequestMessageSend(Object message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte[] getMessageBuffer() {
		PcTedsInfoRequestMessage tedsInfoMessage = (PcTedsInfoRequestMessage) message;

//		System.out.println("PCTedsInfoRequestMessageSend"
//				+ tedsInfoMessage.getLength());
		ByteBuffer bBuffer = ByteBuffer.allocate(tedsInfoMessage.getLength());
		bBuffer.order(ByteOrder.LITTLE_ENDIAN);
		bBuffer.put(tedsInfoMessage.getHead());
		bBuffer.put(Integer.valueOf(tedsInfoMessage.getLength()).byteValue());
		bBuffer.putShort(Integer.valueOf(tedsInfoMessage.getCommand())
				.shortValue());
		// dataInfo

		bBuffer.put(Integer.valueOf(tedsInfoMessage.getReqType()).byteValue());
		bBuffer.put(Integer.valueOf(tedsInfoMessage.getSensorCh()).byteValue());
		byte[] data = bBuffer.array();
		bBuffer.put(Utile.ort(data, 0, data.length - 1));
		bBuffer.rewind();
		byte[] buffer = new byte[bBuffer.remaining()];
		bBuffer.get(buffer);
		return buffer;
	}

}
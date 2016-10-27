package com.ourselec.gateway.pc.message.tedstlv.send;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.ourselec.gateway.message.MessageSend;
import com.ourselec.gateway.pc.message.tedstlv.TedsRspInfo;
/**
 * 回复信息发送器
 *
 */
public class RspInfoSend extends MessageSend {

	public RspInfoSend(Object message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte[] getMessageBuffer() {
		TedsRspInfo rspInfo = (TedsRspInfo) message;
		ByteBuffer bBuffer = ByteBuffer.allocate(rspInfo.getLength() + 2);
		bBuffer.order(ByteOrder.LITTLE_ENDIAN);
		bBuffer.put(Integer.valueOf(rspInfo.getType()).byteValue());
		bBuffer.put(Integer.valueOf(rspInfo.getLength()).byteValue());

//		System.out.println("RspInfoSend" + rspInfo.getLength());
		ValueSend valueSend = ValueSendFactroy.getValueSend(rspInfo.getType(),
				rspInfo.getLength(), rspInfo.getValue());
		bBuffer.put(valueSend.getMessageBuffer());
		bBuffer.rewind();
		byte[] buffer = new byte[bBuffer.remaining()];
		bBuffer.get(buffer);
		return buffer;
	}
}
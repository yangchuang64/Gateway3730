package com.ourselec.gateway.pc.message.controlinfo.send;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.ourselec.gateway.message.MessageSend;
import com.ourselec.gateway.pc.message.controlinfo.ControlInfoRsp;
/**
 * 控制信息回复发送器
 * @author yangtianfei(ytf2737179@163.com)
 */
public class ControlInfoRspSend extends MessageSend {

	public ControlInfoRspSend(Object message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte[] getMessageBuffer() {
		ControlInfoRsp controlInfoResponse = (ControlInfoRsp) message;
		ByteBuffer bBuffer = ByteBuffer.allocate(3);
		bBuffer.order(ByteOrder.LITTLE_ENDIAN);
		bBuffer.put(Integer.valueOf(controlInfoResponse.getCommand())
				.byteValue());
		bBuffer.put(Integer.valueOf(controlInfoResponse.getDevInx())
				.byteValue());
		bBuffer.put(Integer.valueOf(controlInfoResponse.getResult())
				.byteValue());
		bBuffer.rewind();
		byte[] buffer = new byte[bBuffer.remaining()];
		bBuffer.get(buffer);
		// this.mOutputStream.write(buffer);
		// this.mOutputStream.flush();
		return buffer;
	}

}
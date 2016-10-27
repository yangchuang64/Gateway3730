package com.ourselec.gateway.pc.message.send;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.ourselec.gateway.message.MessageSend;
import com.ourselec.gateway.pc.message.GatewayHeartbeatMessage;
import com.ourselec.gateway.pc.message.controlinfo.ControlModule;
import com.ourselec.gateway.pc.message.controlinfo.send.ControlModuleSendFactroy;
import com.ourselec.gateway.util.Utile;
/**
 * 网关心跳消息发送器
 * @author yangtianfei(ytf2737179@163.com)
 */
public class GatewayHeartbeatMessageSend extends MessageSend {

	public GatewayHeartbeatMessageSend(Object message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte[] getMessageBuffer() {
		GatewayHeartbeatMessage controlMessage = (GatewayHeartbeatMessage) message;
		ByteBuffer bBuffer = ByteBuffer.allocate(controlMessage.getLength());
		bBuffer.order(ByteOrder.LITTLE_ENDIAN);
		bBuffer.put(controlMessage.getHead());
		bBuffer.put(Integer.valueOf(controlMessage.getLength()).byteValue());
		bBuffer.putShort(Integer.valueOf(controlMessage.getCommand())
				.shortValue());

		bBuffer.put(Integer.valueOf(controlMessage.getGatewayType())
				.byteValue());
		bBuffer.put(Utile.gatewayProductNum2Bytes(controlMessage
				.getGatewayProductNum()));
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
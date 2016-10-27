package com.ourselec.gateway.pc.message.send;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.ourselec.gateway.message.MessageSend;
import com.ourselec.gateway.pc.message.SensorSendTranferEndMessage;
import com.ourselec.gateway.pc.message.SensorTedsInfoResponseMessage;
import com.ourselec.gateway.pc.message.tedstlv.send.RspInfoSend;
import com.ourselec.gateway.util.Utile;
/**
 * 传感器发送数据传输结束消息发送器
 * @author yangtianfei(ytf2737179@163.com)
 */
public class SensorSendTranferEndMessageSend extends MessageSend {

	public SensorSendTranferEndMessageSend(Object message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte[] getMessageBuffer() {
		SensorSendTranferEndMessage sensorSendTranferEndMsg = (SensorSendTranferEndMessage) message;

		ByteBuffer bBuffer = ByteBuffer.allocate(sensorSendTranferEndMsg
				.getLength());
		bBuffer.order(ByteOrder.LITTLE_ENDIAN);
		bBuffer.put(sensorSendTranferEndMsg.getHead());
		bBuffer.put(Integer.valueOf(sensorSendTranferEndMsg.getLength())
				.byteValue());
		bBuffer.putShort(Integer.valueOf(sensorSendTranferEndMsg.getCommand())
				.shortValue());
		bBuffer.putShort(Integer.valueOf(sensorSendTranferEndMsg.getDeviceId())
				.shortValue());

		bBuffer.put(Utile.gatewayProductNum2Bytes(sensorSendTranferEndMsg
				.getGatewayProductNum()));
		bBuffer.putShort(Integer.valueOf(
				sensorSendTranferEndMsg.getNoteTransferType()).shortValue());
		bBuffer.putShort(Integer.valueOf(
				sensorSendTranferEndMsg.getNoteAddress()).shortValue());
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
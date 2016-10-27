package com.ourselec.gateway.pc.message.send;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.ourselec.gateway.message.MessageSend;
import com.ourselec.gateway.pc.message.SensorTedsInfoResponseMessage;
import com.ourselec.gateway.pc.message.tedstlv.TedsRspInfoType;
import com.ourselec.gateway.pc.message.tedstlv.send.RspInfoSend;
import com.ourselec.gateway.util.Utile;

/**
 * 传感器回复Teds信息发送器
 *
 * @author yangtianfei(ytf2737179@163.com)
 */
public class SensorTedsInfoResponseSend extends MessageSend {

	public SensorTedsInfoResponseSend(Object message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte[] getMessageBuffer() {
		SensorTedsInfoResponseMessage sensorTedsInfoResponse = (SensorTedsInfoResponseMessage) message;

		int length = sensorTedsInfoResponse.getLength();
		if (sensorTedsInfoResponse.getRspInfo().getType() == TedsRspInfoType.SensorBaseInfo) {
			length--;
		}
		ByteBuffer bBuffer = ByteBuffer.allocate(length);
		bBuffer.order(ByteOrder.LITTLE_ENDIAN);
		bBuffer.put(sensorTedsInfoResponse.getHead());
		bBuffer.put(Integer.valueOf(length).byteValue());
		bBuffer.putShort(Integer.valueOf(sensorTedsInfoResponse.getCommand())
				.shortValue());
		// rspInfo
		RspInfoSend rspInfoSend = new RspInfoSend(
				sensorTedsInfoResponse.getRspInfo());
		bBuffer.put(rspInfoSend.getMessageBuffer());

		bBuffer.put(Utile.gatewayProductNum2Bytes(sensorTedsInfoResponse
				.getGatewayProductNum()));
		bBuffer.putShort(Integer.valueOf(
				sensorTedsInfoResponse.getNoteTransferType()).shortValue());
		bBuffer.putShort(Integer.valueOf(
				sensorTedsInfoResponse.getNoteAddress()).shortValue());
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
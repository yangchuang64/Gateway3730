package com.ourselec.gateway.pc.message.send;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.ourselec.gateway.message.MessageSend;
import com.ourselec.gateway.pc.message.DataMessageInfo;
import com.ourselec.gateway.pc.message.SensorUploadDataMessage;
import com.ourselec.gateway.pc.message.datainfo.SampleDataInfo;
import com.ourselec.gateway.pc.message.datainfo.SwitchDataInfo;
import com.ourselec.gateway.pc.message.datainfo.send.SampleDataInfoSend;
import com.ourselec.gateway.pc.message.datainfo.send.SwitchDataInfoSend;
import com.ourselec.gateway.util.Utile;

/**
 * 传感器上传数据消息发送器
 *
 * @author yangtianfei(ytf2737179@163.com)
 */
public class SensorUploadDataMessageSend extends MessageSend {

	private final String TAG = "SensorUploadDatamessageSend";

	public SensorUploadDataMessageSend(Object message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte[] getMessageBuffer() {
		SensorUploadDataMessage sensorUploadData = (SensorUploadDataMessage) message;

		ByteBuffer bBuffer = ByteBuffer.allocate(sensorUploadData.getLength());
		bBuffer.order(ByteOrder.LITTLE_ENDIAN);
		bBuffer.put(sensorUploadData.getHead());
		bBuffer.put(Integer.valueOf(sensorUploadData.getLength()).byteValue());
		bBuffer.putShort(Integer.valueOf(sensorUploadData.getCommand())
				.shortValue());
		// dataInfo
		int deviceId = sensorUploadData.getDeviceId();
		System.out.println(TAG + ":" + deviceId);
		bBuffer.putShort(Integer.valueOf(deviceId).shortValue());
		MessageSend dataInfoSend = null;
		if (DataMessageInfo.isSmaple(deviceId)) {
			SampleDataInfo sampleDataInfo = (SampleDataInfo) sensorUploadData
					.getDataInfo();
			dataInfoSend = new SampleDataInfoSend(sampleDataInfo);
		} else {
			SwitchDataInfo switchDataInfo = (SwitchDataInfo) sensorUploadData
					.getDataInfo();
			dataInfoSend = new SwitchDataInfoSend(switchDataInfo);
		}
		bBuffer.put(dataInfoSend.getMessageBuffer());
		bBuffer.put(Utile.gatewayProductNum2Bytes(sensorUploadData
				.getGatewayProductNum()));
		bBuffer.putShort(Integer
				.valueOf(sensorUploadData.getNoteTransferType()).shortValue());
		bBuffer.putShort(Integer.valueOf(sensorUploadData.getNoteAddress())
				.shortValue());
		byte[] data = bBuffer.array();
		bBuffer.put(Utile.ort(data, 0, data.length - 1));
		bBuffer.rewind();
		byte[] buffer = new byte[bBuffer.remaining()];
		bBuffer.get(buffer);
		return buffer;
	}

}
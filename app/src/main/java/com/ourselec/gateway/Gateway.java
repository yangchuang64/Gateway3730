package com.ourselec.gateway;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Handler;

import com.ourselec.gateway.message.MessageSend;
import com.ourselec.gateway.pc.message.DataBaseMessage;
import com.ourselec.gateway.pc.message.DataMessageInfo;
import com.ourselec.gateway.pc.message.GatewayHeartbeatMessage;
import com.ourselec.gateway.pc.message.send.DataMessageSendFactroy;
import com.ourselec.gateway.process.ProcessThread;
import com.ourselec.gateway.process.SerialPortManager;
import com.ourselec.gateway.process.SerialProcess;
import com.ourselec.gateway.process.UdpManager;
import com.ourselec.gateway.process.UdpProcess;
import com.ourselec.gateway.serial.message.AppRegisterRequestMessageFrame;
import com.ourselec.gateway.serial.message.MessageFrame;
import com.ourselec.gateway.serial.message.MessageFrameInfo;
import com.ourselec.gateway.serial.message.ReadMessageFrame;
import com.ourselec.gateway.serial.message.SysResetReqMessageFrame;
import com.ourselec.gateway.serial.message.WriteMessageFrame;
import com.ourselec.gateway.serial.message.ZnpUartSerialMessage;
import com.ourselec.gateway.serial.message.send.ZnpUartMessageSend;
import com.ourselec.gateway.util.Utile;

/**
 * 网关类
 * 
 * @author yangtianfei(ytf2737179@163.com)
 * 
 */
public class Gateway {
	private static final String TAG = "Gateway";
	ProcessThread serialThread, udpThread;
	Timer timer;
	TimerTask timerTask;
	GatewayHeartbeatMessage gatewayHeartbeatMsg;

	// SerialPortManager portManager;
	// UdpManager udpManager;

	public static int gatewayType = 0x01;
	private int gatewayProductNum = 0x0111f4;
	private int initChannel;
	private int panId;

	public final static int StartUpOptionConfigId = 0x03;
	public final static int LogicalTypeConfigId = 0x87;
	public final static int ChanListConfigId = 0x84;
	public final static int PanIdCode = 0x83;
	public final static int ZdoDirectCb = 0x8F;

	public Gateway(Handler handler) {
		// portManager = SerialPortManager.getInstance();
		// try {
		// portManager.setSerialPort(new SerialPort(new File(
		// SerialPortManager.path), SerialPortManager.baudrate, 0));
		// /* Create a receiving thread */
		// } catch (SecurityException e) {
		// handler.sendEmptyMessage(MHandler.SerialPortSecurityException);
		// // DialogAlert.showDialog(this, getString(R.string.error_security));
		// } catch (IOException e) {
		// handler.sendEmptyMessage(MHandler.SerialFileException);
		// // DialogAlert.showDialog(this, getString(R.string.error_unknown));
		// }

		// udpManager = UdpManager.getInstance();
		if (serialThread == null) {
			serialThread = new ProcessThread(new SerialProcess(handler), true);
		}
		if (!serialThread.isAlive())
			serialThread.start();

		if (udpThread == null) {
			UdpProcess udpProcess = new UdpProcess(handler);
			udpThread = new ProcessThread(udpProcess, true);
		}
		if (!udpThread.isAlive()) {
			udpThread.start();
		}
		gatewayHeartbeatMsg = new GatewayHeartbeatMessage(9,
				DataMessageInfo.GateWayHeartbeatCode, 0, gatewayType,
				gatewayProductNum);
		timer = new Timer();
		timerTask = new TimerTask() {

			@Override
			public void run() {
				sendUdpData(gatewayHeartbeatMsg);
			}
		};
		timer.schedule(timerTask, 0, 5 * 1000);
	}

	// public void setSerialPort() {
	// portManager.setSerialPort();
	// }

	public void destroy() {
		if (serialThread != null)
			serialThread.stop();
		if (udpThread != null)
			udpThread.stop();
	}

	public int getGatewayType() {
		return gatewayType;
	}

	public void setGatewayProductNum(int productNum) {
		this.gatewayProductNum = productNum;
		gatewayHeartbeatMsg.setGatewayProductNum(productNum);
	}

	public int getGatewayProducaNum() {
		return gatewayProductNum;
	}

	public void setInitChannel(int initChannel) {
		this.initChannel = initChannel;
	}

	public int getInitChannel() {
		return this.initChannel;
	}

	public void setPanId(int panId) {
		this.panId = panId;
	}

	public int getPanId() {
		return this.panId;
	}

	public void readDeviceStartOption(int configId) {
		sendMessage(new ReadMessageFrame(1,
				MessageFrameInfo.ZB_READ_CONFIGURATION, configId));
	}

	// // ZVD_NV_START_OPTION
	// public void configDeviceStartOption() {
	// sendMessage(new WriteMessageFrame(
	// MessageFrameInfo.ZB_WRITE_CONFIGURATION, 0x03, 0x01,
	// new byte[] { 0x03 }));
	// }
	//
	// // ZCD_NV_LOGICAL_TYPE
	// public void configDeviceType() {
	// sendMessage(new WriteMessageFrame(
	// MessageFrameInfo.ZB_WRITE_CONFIGURATION, 0x87, 0x01,
	// new byte[] { 0x00 }));
	// }
	//
	// // ZCD_NV_CHANLIST
	// public void configInitialChannel(int channel) {
	// sendMessage(new WriteMessageFrame(
	// MessageFrameInfo.ZB_WRITE_CONFIGURATION, 0x84, 0x04,
	// Utile.tranferChannel(channel)));
	// }
	//
	// // ZCD_NV_PANID
	// public void configPanId(int panid) {
	// sendMessage(new WriteMessageFrame(
	// MessageFrameInfo.ZB_WRITE_CONFIGURATION, 0x83, 0x02,
	// Utile.tranferInt2Bytes(panid)));
	// }
	//
	// // ZCD_NV_ZD0_DIRECT_CB
	// public void configZDODirect() {
	// sendMessage(new WriteMessageFrame(
	// MessageFrameInfo.ZB_WRITE_CONFIGURATION, 0x8f, 0x01,
	// new byte[] { 0x01 }));
	// }

	public void configGateway(int configId, int valueLen, byte[] value) {
		sendMessage(new WriteMessageFrame(2 + value.length,
				MessageFrameInfo.ZB_WRITE_CONFIGURATION, configId, valueLen,
				value));
	}

	// SYS_RESET_REQ
	public void resetSys() {
		sendMessage(new SysResetReqMessageFrame(0x01,
				MessageFrameInfo.SYS_RESET_REQ, 0x00));
	}

	public void register() {
		sendMessage(new AppRegisterRequestMessageFrame(13,
				MessageFrameInfo.ZB_APP_REGISTER_REQUEST, 0x02, 0xD7D7, 0x4567,
				0x01, 0x00, 0x01, new byte[] { 0x01, 0x00 }, 0x01, new byte[] {
						0x02, 0x00 }));
	}

	public void start() {
		sendMessage(new MessageFrame(0, MessageFrameInfo.ZB_START_REQUEST));
	}

	public void sendMessage(MessageFrame messageFrame) {
		ZnpUartSerialMessage message = new ZnpUartSerialMessage(messageFrame,
				(byte) 0);
		byte[] data = new ZnpUartMessageSend(message).getMessageBuffer();
		Utile.printHexString(TAG + "serial ", data);
		SerialPortManager.getInstance().sendSerial(data);
	}

	public void sendUdpData(DataBaseMessage message) {
		MessageSend messageSend = DataMessageSendFactroy
				.getDataMessageSend(message);
		// try {
		byte[] data = messageSend.getMessageBuffer();
		Utile.printHexString(TAG + "udp ", data);
		UdpManager.getInstance().send(data);
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
	}
}

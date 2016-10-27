package com.ourselec.gateway.presenter;

import java.lang.ref.WeakReference;

import android.os.Handler;
import android.os.Message;

import com.ourselec.gateway.Gateway;
import com.ourselec.gateway.node.Node;
import com.ourselec.gateway.node.NodeManager;
import com.ourselec.gateway.pc.message.DataBaseMessage;
import com.ourselec.gateway.pc.message.DataMessageInfo;
import com.ourselec.gateway.pc.message.PcControlSensorMessage;
import com.ourselec.gateway.pc.message.PcTedsInfoRequestMessage;
import com.ourselec.gateway.pc.message.SensorControlResponseMessage;
import com.ourselec.gateway.pc.message.SensorHeartbeatMessage;
import com.ourselec.gateway.pc.message.SensorTedsInfoResponseMessage;
import com.ourselec.gateway.pc.message.SensorUploadDataMessage;
import com.ourselec.gateway.pc.message.tedstlv.SensorBaseInfo;
import com.ourselec.gateway.pc.message.tedstlv.TedsRspInfo;
import com.ourselec.gateway.pc.message.tedstlv.TedsRspInfoType;
import com.ourselec.gateway.serial.message.MessageFrame;
import com.ourselec.gateway.serial.message.MessageFrameInfo;
import com.ourselec.gateway.serial.message.ResponseMessageFrame;
import com.ourselec.gateway.serial.message.ZbReceiveDataIndicationMessageFrame;
import com.ourselec.gateway.serial.message.ZbSendDataConfirmMessageFrame;
import com.ourselec.gateway.serial.message.ZbSendDataRequestMessageFrame;
import com.ourselec.gateway.util.Utile;

/**
 * presenter控制类
 * 
 * @author yangtianfei(ytf2737179@163.com)
 * 
 */
public class GatewayPresenter {

	private GatewayPresenterInterface nodeInterface;

	private Gateway gateway;

	NodeManager nodeManager;
	boolean isConnect = false;
	MHandler handler;

	public GatewayPresenter(GatewayPresenterInterface activity) {
		nodeInterface = activity;
		handler = new MHandler(this);
		gateway = new Gateway(handler);
		nodeManager = NodeManager.getInstance();
		handler.sendEmptyMessage(MHandler.CheckNodes);
	}

	public void initGateway() {
		gateway.readDeviceStartOption(Gateway.StartUpOptionConfigId);
	}

	// public void readGateway(int configId) {
	// gateway.readDeviceStartOption(configId);
	// }

	// public void configGateway(int process) {
	// switch (process) {
	// case 0x03:// ZVD_NV_START_OPTION
	// gateway.configGateway(0x03, 0x01, new byte[] { 0x01 });
	// break;
	// case 0x087:// ZCD_NV_LOGICAL_TYPE
	// gateway.configGateway(0x87, 0x01, new byte[] { 0x00 });
	// break;
	// case 0x84:// ZCD_NV_CHANLIST
	// gateway.configGateway(0x84, 0x04, Utile.tranferChannel(25));
	// break;
	// case 0x83:// ZCD_NV_PANID
	// gateway.configGateway(0x83, 0x02, Utile.Int22Bytes(2013));
	// break;
	// case 0x8F:// ZCD_NV_ZD0_DIRECT_CB
	// gateway.configGateway(0x8f, 0x01, new byte[] { 0x01 });
	// break;
	// default:
	// // gateway.resetSys();
	// break;
	// }
	// }

	public void configGateway(int initChannel, int panId, int productNum) {
		gateway.setInitChannel(initChannel);
		gateway.setPanId(panId);
		gateway.setGatewayProductNum(productNum);
	}

	public void resetGateWay() {
		handler.sendEmptyMessage(MHandler.ResetControl);
	}

	public void reStartGateWay() {
		handler.sendEmptyMessage(MHandler.RestartControl);
	}

	public void sendDataRequest(MessageFrame messageFrame) {
		gateway.sendMessage(messageFrame);
	}

	/*
	 * public void registerGateWay() { gateway.register(); }
	 * 
	 * public void startGateWay() { gateway.start(); }
	 */
	public interface GatewayPresenterInterface {
		// public void logout(String mes);
		public void configGatewayOver();

		public void updateSensorInfo(int noteAddress);

		public void updateSensorData(int source, DataBaseMessage data);

		public void controlRsp(int source, DataBaseMessage data);

		public void updateNodes(int noteAddress);

	}

	public static class MHandler extends Handler {
		public final static int SERIAL_DATA = 0x01;
		public final static int ResetControl = 0x02;
		public final static int RestartControl = 0x03;
		public final static int UDP_DATA = 0x04;
		// public final static int SerialPortSecurityException = 0x04;
		// public final static int SerialFileException = 0x05;
		public final static int CheckNodes = 0x07;

		int configState;
		final int reConfig = 0x00;
		final int configing = 0x01;
		// final int configCheck = 0x02;
		final int configed = 0x03;
		final int restartConfig = 0x04;

		WeakReference<GatewayPresenter> presenterReference;
		private int currentProcess = Gateway.StartUpOptionConfigId;

		public MHandler(GatewayPresenter pressenter) {
			presenterReference = new WeakReference<GatewayPresenter>(pressenter);
		}

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (presenterReference.get() == null)
				return;
			switch (msg.what) {
			case SERIAL_DATA:
				processSerialMessage(msg.obj);
				break;
			case ResetControl:
				setGateway();
				break;
			case RestartControl:
				// if (configState == configed
				// && !presenterReference.get().isConnect)
				if (!presenterReference.get().isConnect) {
					configState = restartConfig;
					presenterReference.get().gateway.register();
				}
				break;
			case UDP_DATA:
				processPcMessage(msg.obj);
				break;
			case CheckNodes:
				int noteAddress = NodeManager.getInstance().checkNodesState();
				if (noteAddress > 0) {
					presenterReference.get().nodeInterface
							.updateNodes(noteAddress);
				}
				sendEmptyMessageDelayed(CheckNodes, 5 * 1000);
				break;
			}
		}

		private void processSerialMessage(Object object) {
			MessageFrame frame = (MessageFrame) object;
			switch (frame.getCommand()) {
			case MessageFrameInfo.ZB_READ_CONFIGURATION_RESPONSE:
				processSerialReadConfigUrat(frame);
				break;
			case MessageFrameInfo.ZB_WRITE_CONFIGURATION_RESPONSE:
				processSerialWriteConfigUrat(frame);
				break;
			case MessageFrameInfo.SYS_RESET_IND:
				processSerialSysResetInd(frame);
				break;
			case MessageFrameInfo.ZB_APP_REGISTER_RESPONSE:
				processSerialAppRegister(frame);
				break;
			case MessageFrameInfo.ZB_START_RESPONSE:
				// processStartZb(fram);
				break;
			case MessageFrameInfo.ZD0_START_CHANGE_IND:
				processSerialStartChangeInd(frame);
				break;
			case MessageFrameInfo.ZB_START_CONFIRM:
				processSerialStartConfirm(frame);
				break;
			case MessageFrameInfo.ZDO_END_DEVICE_ANNCE_IND:
				processSerialEndDeviceAnnceInd(frame);
				break;
			case MessageFrameInfo.ZB_SEND_DATA_RESPONSE:
				break;
			case MessageFrameInfo.ZB_SEND_DATA_CONFIRM:
				processSerialDataConfirm(frame);
				break;
			case MessageFrameInfo.ZB_RECEIVE_DATA_INDICATION:
				// if (presenterReference.get().isConnect)
				processSerialReceiveDataIndcation(frame);
				break;
			}
		}

		private void processPcMessage(Object object) {
			DataBaseMessage dataMsg = (DataBaseMessage) object;
			switch (dataMsg.getCommand()) {
			case DataMessageInfo.PcHeartbeatCode:
				break;
			case DataMessageInfo.PcReqTedsCode:
				processPcRequestSensorTeds(dataMsg);
				break;
			case DataMessageInfo.PcControlCode:
				processPcControSensor(dataMsg);
				break;
			case DataMessageInfo.PcRequestZbBaseInfoCode:
				break;
			case DataMessageInfo.PcModifyZbSensorDeviceTypeCode:
				break;
			case DataMessageInfo.PcModifyZbPanIdCode:
				break;
			case DataMessageInfo.PcModifyZbChannelCode:
				break;
			}
		}

		private void configGateway(int process) {
			switch (process) {
			case 0x03:// ZVD_NV_START_OPTION
				presenterReference.get().gateway.configGateway(0x03, 0x01,
						new byte[] { 0x03 });
				break;
			case 0x087:// ZCD_NV_LOGICAL_TYPE
				presenterReference.get().gateway.configGateway(0x87, 0x01,
						new byte[] { 0x00 });
				break;
			case 0x84:// ZCD_NV_CHANLIST
				presenterReference.get().gateway.configGateway(0x84, 0x04,
						Utile.tranferChannel(presenterReference.get().gateway
								.getInitChannel()));
				break;
			case 0x83:// ZCD_NV_PANID
				presenterReference.get().gateway.configGateway(0x83, 0x02,
						Utile.Int22Bytes(presenterReference.get().gateway
								.getPanId()));
				break;
			case 0x8F:// ZCD_NV_ZD0_DIRECT_CB
				presenterReference.get().gateway.configGateway(0x8f, 0x01,
						new byte[] { 0x01 });
				break;
			default:
				// gateway.resetSys();
				break;
			}
		}

		private void processSerialReadConfigUrat(MessageFrame frame) {
			// ReadResponseMessageFrame readFrame = (ReadResponseMessageFrame)
			// frame;
			// if (readFrame.getStatus() == 0x00) {
			// if (readFrame.getConfigId() == 0x03) {
			// int len = readFrame.getValueLen();
			// if (len > 0) {
			// byte[] value = readFrame.getValue();
			// if (configState == configCheck) {
			// if (value[0] > 0)
			// presenterReference.get().gateway.resetSys();
			// else
			// configState = configed;
			// } else {
			// setGateway();
			// }
			// }
			// }
			// }
		}

		private void setGateway() {
			configGateway(Gateway.StartUpOptionConfigId);
			currentProcess = Gateway.StartUpOptionConfigId;
			configState = reConfig;
		}

		private void processSerialWriteConfigUrat(MessageFrame frame) {
			if (configState == configed)
				return;
			ResponseMessageFrame writeFrame = (ResponseMessageFrame) frame;
			if (writeFrame.getStatus() == 0x00) {
				switch (currentProcess) {
				case Gateway.StartUpOptionConfigId:
					presenterReference.get().gateway.resetSys();
					// configState = reConfig;
					break;
				case Gateway.LogicalTypeConfigId:
					configGateway(Gateway.ChanListConfigId);
					currentProcess = Gateway.ChanListConfigId;
					break;
				case Gateway.ChanListConfigId:
					configGateway(Gateway.PanIdCode);
					currentProcess = Gateway.PanIdCode;
					break;
				case Gateway.PanIdCode:
					configGateway(Gateway.ZdoDirectCb);
					currentProcess = Gateway.ZdoDirectCb;
					break;
				case Gateway.ZdoDirectCb:
					presenterReference.get().gateway.resetSys();
					break;
				default:

				}
				// System.out.println("process:  " + process);
			}
		}

		public void processSerialSysResetInd(MessageFrame frame) {
			// SysResetResMessageFrame sysRestFrame = (SysResetResMessageFrame)
			// frame;
			presenterReference.get().gateway.register();
		}

		public void processSerialAppRegister(MessageFrame frame) {
			presenterReference.get().gateway.start();
		}

		public void processStartZb(MessageFrame frame) {
			// 启动成功

		}

		public void processSerialStartChangeInd(MessageFrame frame) {
			ResponseMessageFrame startChangeFrame = (ResponseMessageFrame) frame;

		}

		public void processSerialStartConfirm(MessageFrame frame) {
			ResponseMessageFrame startConfirmFrame = (ResponseMessageFrame) frame;
			if (startConfirmFrame.getStatus() == 0x00) {
				if (configState == reConfig) {
					configGateway(Gateway.LogicalTypeConfigId);
					currentProcess = Gateway.LogicalTypeConfigId;
					configState = configing;
				} else if (configState == configing) {
					// configState = configCheck;
					// presenterReference.get().gateway
					// .readDeviceStartOption(Gateway.StartUpOptionConfigId);
					configState = configed;
					presenterReference.get().nodeInterface.configGatewayOver();
					// } else if (configState == configCheck) {
					// presenterReference.get().gateway
					// .readDeviceStartOption(Gateway.StartUpOptionConfigId);
				} else if (configState == configed) {

				} else if (configState == restartConfig) {
					// configState = configed;
					presenterReference.get().nodeInterface.configGatewayOver();
				}
			}
		}

		public void processSerialEndDeviceAnnceInd(MessageFrame frame) {

			// sendEmptyMessage(CheckNodes);
		}

		public void processSerialDataConfirm(MessageFrame frame) {
			ZbSendDataConfirmMessageFrame dataConfirmFrame = (ZbSendDataConfirmMessageFrame) frame;

		}

		public void processSerialReceiveDataIndcation(MessageFrame frame) {
			if (presenterReference.get().isConnect == false)
				presenterReference.get().isConnect = true;
			ZbReceiveDataIndicationMessageFrame receiveData = (ZbReceiveDataIndicationMessageFrame) frame;
			int source = receiveData.getSource();
			DataBaseMessage data = receiveData.getData();

			if (data == null)
				return;
			switch (data.getCommand()) {
			case 0x0000:
				processSensorHeartbeatMessage(source, data);
				break;
			case 0x8010:
				processSensorResponseMessage(source, data);
				break;
			case 0x8011:
				processSensorUploadSensorInfoMessage(source, data);
				break;
			case 0x4011:
				processSensorResponseControlMessage(source, data);
				break;
			default:
				break;
			}
		}

		public void processSensorHeartbeatMessage(int source,
				DataBaseMessage data) {
			SensorHeartbeatMessage sensorHeartbeatMessage = (SensorHeartbeatMessage) data;
			sensorHeartbeatMessage.setGatewayProductNum(presenterReference
					.get().gateway.getGatewayProducaNum());
			sensorHeartbeatMessage.setNoteTransferType(Gateway.gatewayType);

			presenterReference.get().gateway.sendUdpData(data);

			int noteAddress = sensorHeartbeatMessage.getNoteAddress();
			Node node = presenterReference.get().nodeManager
					.getNode(noteAddress);
			if (node == null) {
				node = new Node(sensorHeartbeatMessage.getHeartbeatInfo(),
						sensorHeartbeatMessage.getSensorFlag(),
						sensorHeartbeatMessage.getGatewayProductNum(),
						sensorHeartbeatMessage.getNoteTransferType(),
						sensorHeartbeatMessage.getNoteAddress());
				presenterReference.get().nodeManager.addNode(node);
			}
			node.setLifeTime(System.currentTimeMillis());
			if (!node.getModifyState()) {
				sendTedsInfoRequest(TedsRspInfoType.SensorBaseInfo, 0,
						node.getNoteTransferType(), node.getNoteAddress());
			}
		}

		private void sendTedsInfoRequest(int reqType, int sensorCh,
				int noteTransferType, int noteAddress) {
			PcTedsInfoRequestMessage message = new PcTedsInfoRequestMessage(7,
					DataMessageInfo.PcReqTedsCode, 0, reqType, sensorCh,
					noteTransferType, noteAddress);
			presenterReference.get().gateway
					.sendMessage(new ZbSendDataRequestMessageFrame(8 + message
							.getLength(),
							MessageFrameInfo.ZB_SEND_DATA_REQUEST, noteAddress,
							0x0200, 0, 0, 0, message.getLength(), message));
		}

		public void processSensorResponseMessage(int source,
				DataBaseMessage data) {
			SensorTedsInfoResponseMessage tedsInfoMessage = (SensorTedsInfoResponseMessage) data;
			tedsInfoMessage
					.setGatewayProductNum(presenterReference.get().gateway
							.getGatewayProducaNum());
			tedsInfoMessage.setNoteTransferType(Gateway.gatewayType);

			presenterReference.get().gateway.sendUdpData(data);

			// int noteAddress = tedsInfoMessage.getNoteAddress();
			Node node = presenterReference.get().nodeManager.getNode(source);
			if (node != null && !node.getModifyState()) {
				TedsRspInfo rspInfo = tedsInfoMessage.getRspInfo();
				if (rspInfo.getType() == TedsRspInfoType.SensorBaseInfo) {
					SensorBaseInfo value = (SensorBaseInfo) rspInfo.getValue();
					node.updateBaseInfo(value.getVersion(),
							value.getDeviceId(), value.getManufacInfo(),
							value.getProductNum(), value.getChannelNo());
					presenterReference.get().nodeInterface
							.updateSensorInfo(source);
				}
			}
		}

		public void processSensorUploadSensorInfoMessage(int source,
				DataBaseMessage data) {
			SensorUploadDataMessage sensorHeartbeatMessage = (SensorUploadDataMessage) data;
			sensorHeartbeatMessage.setGatewayProductNum(presenterReference
					.get().gateway.getGatewayProducaNum());
			sensorHeartbeatMessage.setNoteTransferType(Gateway.gatewayType);

			presenterReference.get().gateway.sendUdpData(data);
			presenterReference.get().nodeInterface.updateSensorData(source,
					data);
		}

		public void processSensorResponseControlMessage(int source,
				DataBaseMessage data) {
			SensorControlResponseMessage sensorHeartbeatMessage = (SensorControlResponseMessage) data;
			sensorHeartbeatMessage.setGatewayProductNum(presenterReference
					.get().gateway.getGatewayProducaNum());
			sensorHeartbeatMessage.setNoteTransferType(Gateway.gatewayType);

			presenterReference.get().gateway.sendUdpData(data);
			presenterReference.get().nodeInterface.controlRsp(source, data);
		}

		private void processPcRequestSensorTeds(DataBaseMessage dataMsg) {
			PcTedsInfoRequestMessage tedsReqMsg = (PcTedsInfoRequestMessage) dataMsg;
			sendTedsInfoRequest(tedsReqMsg.getReqType(),
					tedsReqMsg.getSensorCh(), tedsReqMsg.getNoteTransferType(),
					tedsReqMsg.getNoteAddress());
		}

		private void processPcControSensor(DataBaseMessage dataMsg) {
			PcControlSensorMessage controlMsg = (PcControlSensorMessage) dataMsg;
			byte[] controlB = controlMsg.getControInfo();
			PcControlSensorMessage controlMessage = new PcControlSensorMessage(
					7 + controlB.length, DataMessageInfo.PcControlCode, 0,
					controlMsg.getDeviceId(), controlB, 0, 0);
			ZbSendDataRequestMessageFrame fram = new ZbSendDataRequestMessageFrame(
					8 + controlMessage.getLength(),
					MessageFrameInfo.ZB_SEND_DATA_REQUEST,
					controlMsg.getNoteAddress(), 0x02, 0, 0, 0x0f,
					controlMessage.getLength(), controlMessage);
			presenterReference.get().sendDataRequest(fram);
		}
	}

}

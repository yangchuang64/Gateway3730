package com.ourselec.gateway;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Gallery;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android_serialport_api.SerialPort;

import com.ourselec.gateway.node.Node;
import com.ourselec.gateway.node.NodeManager;
import com.ourselec.gateway.pc.message.DataBaseMessage;
import com.ourselec.gateway.pc.message.DataMessageInfo;
import com.ourselec.gateway.pc.message.PcControlSensorMessage;
import com.ourselec.gateway.pc.message.SensorUploadDataMessage;
import com.ourselec.gateway.pc.message.controlinfo.ControlModule;
import com.ourselec.gateway.pc.message.controlinfo.send.ControlModuleSendFactroy;
import com.ourselec.gateway.presenter.GatewayPresenter;
import com.ourselec.gateway.presenter.GatewayPresenter.GatewayPresenterInterface;
import com.ourselec.gateway.process.SerialPortManager;
import com.ourselec.gateway.process.UdpManager;
import com.ourselec.gateway.serial.message.MessageFrameInfo;
import com.ourselec.gateway.serial.message.ZbSendDataRequestMessageFrame;
import com.ourselec.gateway.util.Utile;
import com.ourselec.gateway.view.NodeAdapter;
import com.ourselec.gateway.view.SensorControlCallback;
import com.ourselec.gateway.view.SensorControlView;
import com.ourselec.gateway.view.SensorIrView;
import com.ourselec.gateway.view.SensorSampleDisView;

/**
 * 网关界面
 * 
 * @author yangtianfei(ytf2737179@163.com)
 * 
 */
public class GatewayActivity extends Activity implements
		GatewayPresenterInterface, OnClickListener, SensorControlCallback,
		OnItemClickListener {

	private GatewayPresenter gatewayPresenter;
	TextView sensorNameTx, sensorVersionTx, sensorDeviceIdTx,
			sensorManufacInfoTx, sensorProductNumTx, sensorChannelNoTx;
	LinearLayout sensorInfoLt, sensorControlLt, sensorDisplayLt;
	// RelativeLayout sensorSampleRt;
	SensorControlView sampleControlView;
	SensorSampleDisView sampleDisView;
	SensorIrView sensorIrView;
	Gallery nodeGl;
	NodeAdapter nodeAdapter;
	int currentSensor = -1;

	SerialPortManager portManager;
	UdpManager udpManager;

	private ProgressDialog mProgDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		PreferenceManager.setDefaultValues(this, R.xml.gateway_preference,
				false);
		setContentView(R.layout.main);
		// ((Button) findViewById(R.id.restart_bt)).setOnClickListener(this);
		sensorNameTx = (TextView) findViewById(R.id.sensor_name_tx);
		sensorVersionTx = (TextView) findViewById(R.id.sensor_version_tx);
		sensorDeviceIdTx = (TextView) findViewById(R.id.sensor_device_id_tx);
		sensorManufacInfoTx = (TextView) findViewById(R.id.sensor_manufac_info_tx);
		sensorProductNumTx = (TextView) findViewById(R.id.sensor_product_num_tx);
		sensorChannelNoTx = (TextView) findViewById(R.id.sensor_channel_no_tx);
		sensorControlLt = (LinearLayout) findViewById(R.id.sensor_control_lt);
		// sensorSampleRt = (RelativeLayout)
		// findViewById(R.id.sensor_sample_lt);
		sensorDisplayLt = (LinearLayout) findViewById(R.id.sensor_display_lt);
		nodeGl = (Gallery) findViewById(R.id.node_gl);
		nodeAdapter = new NodeAdapter(this);
		nodeGl.setAdapter(nodeAdapter);
		nodeGl.setOnItemClickListener(this);
		init();
	}

	private void init() {
		initPortManager();
		SharedPreferences sharedPref = PreferenceManager
				.getDefaultSharedPreferences(this);
		String hostIpAddr = sharedPref.getString("host_ip_address", "");
		String hostPort = sharedPref.getString("host_port", "");
		String localPort = sharedPref.getString("local_port", "");
		initUdpManager(hostIpAddr, Integer.valueOf(hostPort),
				Integer.valueOf(localPort));

		String initChannel = sharedPref.getString("init_channel_num", "");
		String panId = sharedPref.getString("panid", "");
		String productNum = sharedPref.getString("product_num", "");
		initGatewayPresenter(Integer.valueOf(initChannel),
				Integer.valueOf(panId), Utile.convertToHex(productNum));
	}

	private void initPortManager() {
		portManager = SerialPortManager.getInstance();
		try {
			portManager.setSerialPort(new SerialPort(new File(
					SerialPortManager.path), SerialPortManager.baudrate, 0));
			/* Create a receiving thread */
		} catch (SecurityException e) {
			// handler.sendEmptyMessage(MHandler.SerialPortSecurityException);
			// DialogAlert.showDialog(this, getString(R.string.error_security));
			Toast.makeText(this, getString(R.string.error_security),
					Toast.LENGTH_SHORT).show();
		} catch (IOException e) {
			// handler.sendEmptyMessage(MHandler.SerialFileException);
			// DialogAlert.showDialog(this, getString(R.string.error_unknown));
			Toast.makeText(this, getString(R.string.error_unknown),
					Toast.LENGTH_SHORT).show();
		}
	}

	private void initUdpManager(String hostIpAddr, int hostPort, int localPort) {
		udpManager = UdpManager.getInstance();
		// if (isIpAddress("host ip", hostIpAddr))
		udpManager.setHostIp(hostIpAddr);
		// if (isDigit("host port", hostPort))
		udpManager.setHostPort(hostPort);
		// if (isDigit("local port", localPort))
		try {
			udpManager.setLocalPort(localPort);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void initGatewayPresenter(int channel, int panId, int productNum) {
		gatewayPresenter = new GatewayPresenter(this);
		// gatewayPresenter.initGateway();
		// if (isDigit("channel", initChannel) && isDigit("panid", panId)
		// && isHexDigit("product num", productNum)) {
		gatewayPresenter.configGateway(channel, panId, productNum);
		// gatewayPresenter.resetGateWay();
		// }
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == Activity.RESULT_OK) {
			nodeAdapter.clear();
			currentSensor = -1;
			updateNodeLayout(null);

			SharedPreferences sharedPref = PreferenceManager
					.getDefaultSharedPreferences(this);
			String initChannel = sharedPref.getString("init_channel_num", "");
			String panId = sharedPref.getString("panid", "");
			String productNum = sharedPref.getString("product_num", "");
			// if (isDigit("Channel", initChannel) && isDigit("PanId", panId)
			// && isHexDigit("Product Num", productNum)) {
			gatewayPresenter.configGateway(Integer.valueOf(initChannel),
					Integer.valueOf(panId), Utile.convertToHex(productNum));
			gatewayPresenter.resetGateWay();
			showProgressDialog();
			// }
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gateway, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_reset:
			gatewayPresenter.resetGateWay();
			showProgressDialog();
			break;
		case R.id.action_restart:
			gatewayPresenter.reStartGateWay();
			showProgressDialog();
			break;
		case R.id.action_settings:
			Intent intent = new Intent();
			intent.setClass(this, GatewayPreferenceActivity.class);
			startActivityForResult(intent, 0);
			break;
		case R.id.action_help:
			Intent hIntent = new Intent();
			hIntent.setClass(this, GatewayHelpAcitivity.class);
			startActivity(hIntent);
			break;
		case R.id.action_exit:
			System.exit(0);
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	private void showProgressDialog() {
		if (mProgDialog == null) {
			mProgDialog = new ProgressDialog(this);
			mProgDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			mProgDialog.setIndeterminate(false);
			mProgDialog.setCancelable(true);
			mProgDialog.setMessage(getString(R.string.progress_dialog_info));
		}
		mProgDialog.show();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK)
			System.exit(0);
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		// case R.id.restart_bt:
		// gatewayPresenter.reStartGateWay();
		// break;
		}
	}

	// @Override
	// public void logout(String mes) {
	// // TODO Auto-generated method stub
	// Toast.makeText(this, mes, Toast.LENGTH_LONG).show();
	// }

	public void configGatewayOver() {
		mProgDialog.dismiss();
	}

	@Override
	public void updateSensorInfo(int source) {
		nodeAdapter.addItems(source);
		if (currentSensor == -1 || currentSensor == source) {

			Node node = NodeManager.getInstance().getNode(source);
			if (node != null) {
				updateNodeLayout(node);
				currentSensor = source;
			}
		}
	}

	private void updateNodeLayout(Node node) {
		if (node == null) {
			sensorNameTx.setText(null);
			sensorVersionTx.setText(null);
			sensorDeviceIdTx.setText(null);
			sensorManufacInfoTx.setText(null);
			sensorProductNumTx.setText(null);
			sensorChannelNoTx.setText(null);
		} else {
			sensorNameTx.setText(String.valueOf(node.getLable()));
			sensorVersionTx.setText("[ieee:"
					+ node.getVersion().getIeeeNumber() + "] [teds:"
					+ node.getVersion().getTedsVersionNumber() + "]");
			// sensorDeviceIdTx.setText(String.valueOf(node.getDeviceId()
			// .getDeviceId()));
			sensorDeviceIdTx.setText("0x"
					+ Integer.toHexString(node.getDeviceId().getDeviceId())
							.toUpperCase());
			sensorManufacInfoTx.setText(Utile.getManufacInfo(node
					.getManufacInfo().getName()));
			sensorProductNumTx.setText(Utile.getProductNum(
					node.getProductNum().getProductNum()).toUpperCase());
			sensorChannelNoTx.setText(String.valueOf(node.getChannelNo()
					.getChannel()));
		}

		if (sampleControlView == null)
			sampleControlView = new SensorControlView(this, sensorControlLt,
					this);
		sampleControlView.setNode(node);

		if (sampleDisView == null)
			sampleDisView = new SensorSampleDisView(this, sensorDisplayLt);
		sampleDisView.setNode(node);
		if (sensorIrView == null)
			sensorIrView = new SensorIrView(this, sensorDisplayLt, this);
		sensorIrView.setNode(node);
	}

	@Override
	public void updateSensorData(int source, DataBaseMessage data) {
		if (currentSensor == source) {
			SensorUploadDataMessage message = (SensorUploadDataMessage) data;
			Object dataInfo = message.getDataInfo();
			// if (dataInfo instanceof SampleDataInfo) {
			// } else if (dataInfo instanceof SwitchDataInfo) {
			//
			// }
			sampleDisView.updateSample(dataInfo);
		}
	}

	@Override
	public void controlRsp(int source, DataBaseMessage data) {
		if (currentSensor == source) {
			sampleControlView.updateControlLayout(data);
			sensorIrView.updateControlLayout(data);
			// sampleDisView = new SensorSampleDisView(this, sensorDisplayLt);
		}
	}

	@Override
	public void updateNodes(int noteAddress) {
		nodeAdapter.remove(noteAddress);
		if (currentSensor == noteAddress) {
			currentSensor = -1;
			updateNodeLayout(null);
		}
	}

	@Override
	public void sensorControl(ControlModule control, int length) {
		byte[] controlB = ControlModuleSendFactroy
				.getMontrolModuleSend(control).getMessageBuffer();
		Node node = NodeManager.getInstance().getNode(currentSensor);
		if (node != null) {
			PcControlSensorMessage controlMessage = new PcControlSensorMessage(
					7 + length, DataMessageInfo.PcControlCode, 0, node
							.getDeviceId().getDeviceId(), controlB, 0, 0);
			ZbSendDataRequestMessageFrame fram = new ZbSendDataRequestMessageFrame(
					8 + controlMessage.getLength(),
					MessageFrameInfo.ZB_SEND_DATA_REQUEST,
					node.getNoteAddress(), 0x02, 0, 0, 0x0f,
					controlMessage.getLength(), controlMessage);
			gatewayPresenter.sendDataRequest(fram);
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		int noteAddress = (Integer) nodeAdapter.getItem(arg2);
		if (noteAddress != currentSensor) {
			Node node = NodeManager.getInstance().getNode(noteAddress);
			if (node != null) {
				updateNodeLayout(node);
				currentSensor = noteAddress;
			}
		}
	}
}

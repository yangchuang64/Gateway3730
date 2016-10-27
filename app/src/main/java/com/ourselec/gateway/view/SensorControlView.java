package com.ourselec.gateway.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ourselec.gateway.R;
import com.ourselec.gateway.node.Node;
import com.ourselec.gateway.pc.message.DataBaseMessage;
import com.ourselec.gateway.pc.message.DataMessageInfo;
import com.ourselec.gateway.pc.message.SensorControlResponseMessage;
import com.ourselec.gateway.pc.message.controlinfo.ControlInfoRsp;
import com.ourselec.gateway.pc.message.controlinfo.ControlModuleCode;
import com.ourselec.gateway.pc.message.controlinfo.OutputControl;
import com.ourselec.gateway.pc.message.controlinfo.SampleControl;
import com.ourselec.gateway.util.Utile;

/**
 * 控制控件
 * 
 * @author yangtianfei(ytf2737179@163.com)
 * 
 */
public class SensorControlView implements OnClickListener {

	Context context;
	LinearLayout sampleControlLt;
	View gpoutView, sampleView, daView, gpinView, irView;
	SensorControlCallback callback;
	Node node;

	private int gpOutPosition;
	EditText da1Et, da2Et;

	public SensorControlView(Context context, LinearLayout lt,
			SensorControlCallback callback) {
		this.context = context;
		this.sampleControlLt = lt;
		this.callback = callback;
	}

	public void setNode(Node node) {
		this.node = node;
		if (node == null) {
			this.sampleControlLt.removeAllViews();
		} else
			updateControlLayout();
	}

	private void updateControlLayout() {
		LayoutInflater layoutInflater = LayoutInflater.from(context);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		int deviceId = node.getDeviceId().getDeviceId();
		if (DataMessageInfo.isSmaple(deviceId)) {
			if (sampleControlLt.findViewById(R.id.sensor_sample_start_bt) == null
					&& sampleControlLt.findViewById(R.id.sensor_sample_stop_bt) == null) {
				sampleControlLt.removeAllViews();
				if (sampleView == null) {
					sampleView = layoutInflater.inflate(R.layout.sensor_sample,
							null);
					// Button sampleStartBt = new Button(context);
					Button sampleStartBt = (Button) sampleView
							.findViewById(R.id.sensor_sample_start_bt);
					// sampleStartBt.setText(context
					// .getString(R.string.sensor_sample_start));
					// sampleStartBt.setId(R.id.sensor_sample_start_bt);
					sampleStartBt.setOnClickListener(this);
					// sampleControlLt.addView(sampleStartBt, params);

					// Button sampleStopBt = new Button(context);
					Button sampleStopBt = (Button) sampleView
							.findViewById(R.id.sensor_sample_stop_bt);
					// sampleStopBt.setText(context
					// .getString(R.string.sensor_sample_start));
					// sampleStopBt.setId(R.id.sensor_sample_stop_bt);
					sampleStopBt.setOnClickListener(this);
					// sampleControlLt.addView(sampleStopBt, params);
				}
				sampleControlLt.addView(sampleView, params);
			}
		} else {
			sampleControlLt.removeAllViews();
			switch (deviceId) {
			case DataMessageInfo.DADoubleModuleCode:
				if (sampleControlLt.findViewById(R.id.sensor_output_da_lt) == null) {
					if (daView == null) {
						daView = layoutInflater.inflate(
								R.layout.sensor_output_da, null);
						da1Et = (EditText) daView
								.findViewById(R.id.sensor_da1_et);
						da2Et = (EditText) daView
								.findViewById(R.id.sensor_da2_et);
						((Button) daView.findViewById(R.id.sensor_da1_bt))
								.setOnClickListener(this);
						((Button) daView.findViewById(R.id.sensor_da2_bt))
								.setOnClickListener(this);
					}
					sampleControlLt.addView(daView, params);
				}
				break;
			case DataMessageInfo.GPoutFourModuleCode:
				if (sampleControlLt.findViewById(R.id.sensor_output_gp_lt) == null) {
					if (gpoutView == null) {
						gpoutView = layoutInflater.inflate(
								R.layout.sensor_output_gp, null);
						((Button) gpoutView.findViewById(R.id.sensor_gpin1))
								.setOnClickListener(this);
						((Button) gpoutView.findViewById(R.id.sensor_gpin2))
								.setOnClickListener(this);
						((Button) gpoutView.findViewById(R.id.sensor_gpin3))
								.setOnClickListener(this);
						((Button) gpoutView.findViewById(R.id.sensor_gpin4))
								.setOnClickListener(this);
					}
					sampleControlLt.addView(gpoutView, params);

				}
				break;
			case DataMessageInfo.GPinFourModuleCode:
				// if (sampleControlLt.findViewById(R.id.sensor_output_gp_lt) ==
				// null) {
				// if (gpinView == null) {
				// gpinView = layoutInflater.inflate(R.layout.sensor_gp_in,
				// null);
				// }
				// sampleControlLt.addView(gpinView, params);
				// }
				break;
			case DataMessageInfo.InfraredModuleCode:
				break;
			case DataMessageInfo.MegneticApproachModuleCode:
				break;
			case DataMessageInfo.MetalApproachModuleCode:
				break;
//			case DataMessageInfo.IRControlModuleCode:
//				if (sampleControlLt.findViewById(R.id.sensor_output_ir_lt) == null) {
//					if (irView == null) {
//						irView = layoutInflater.inflate(
//								R.layout.sensor_output_ir, null);
//						((Button) irView
//								.findViewById(R.id.sensor_ir_television_switch))
//								.setOnClickListener(this);
//						((Button) irView
//								.findViewById(R.id.sensor_ir_television_signal))
//								.setOnClickListener(this);
//						((Button) irView
//								.findViewById(R.id.sensor_ir_television_vol_decrease))
//								.setOnClickListener(this);
//						((Button) irView
//								.findViewById(R.id.sensor_ir_television_menu))
//								.setOnClickListener(this);
//						((Button) irView
//								.findViewById(R.id.sensor_ir_television_vol_increase))
//								.setOnClickListener(this);
//						((Button) irView
//								.findViewById(R.id.sensor_ir_television_ch_decrease))
//								.setOnClickListener(this);
//						((Button) irView
//								.findViewById(R.id.sensor_ir_television_silence))
//								.setOnClickListener(this);
//						((Button) irView
//								.findViewById(R.id.sensor_ir_television_ch_increase))
//								.setOnClickListener(this);
//						((Button) irView
//								.findViewById(R.id.sensor_ir_dvd_player_switch))
//								.setOnClickListener(this);
//						((Button) irView
//								.findViewById(R.id.sensor_ir_dvd_player_play))
//								.setOnClickListener(this);
//						((Button) irView
//								.findViewById(R.id.sensor_ir_dvd_player_stop))
//								.setOnClickListener(this);
//						((Button) irView
//								.findViewById(R.id.sensor_ir_dvd_player_silence))
//								.setOnClickListener(this);
//						((Button) irView
//								.findViewById(R.id.sensor_ir_dvd_player_ch_increase))
//								.setOnClickListener(this);
//						((Button) irView
//								.findViewById(R.id.sensor_ir_dvd_player_ch_decrease))
//								.setOnClickListener(this);
//						((Button) irView
//								.findViewById(R.id.sensor_ir_amplifier_silence))
//								.setOnClickListener(this);
//						((Button) irView
//								.findViewById(R.id.sensor_ir_amplifier_voice_increase))
//								.setOnClickListener(this);
//						((Button) irView
//								.findViewById(R.id.sensor_ir_amplifier_voice_decrease))
//								.setOnClickListener(this);
//						((Button) irView
//								.findViewById(R.id.sensor_ir_ari_condition_cool))
//								.setOnClickListener(this);
//						((Button) irView
//								.findViewById(R.id.sensor_ir_ari_condition_comfort))
//								.setOnClickListener(this);
//						((Button) irView
//								.findViewById(R.id.sensor_ir_ari_condition_warn))
//								.setOnClickListener(this);
//						((Button) irView
//								.findViewById(R.id.sensor_ir_ari_condition_switch))
//								.setOnClickListener(this);
//						// ((Button) irView.findViewById(R.id.sensor_ir2))
//						// .setOnClickListener(this);
//						// ((Button) irView.findViewById(R.id.sensor_ir3))
//						// .setOnClickListener(this);
//						// ((Button) irView.findViewById(R.id.sensor_ir4))
//						// .setOnClickListener(this);
//					}
//					sampleControlLt.addView(irView, params);
//				}
//				break;
			}
		}
		// switch (node.getDeviceId().getDeviceId()) {
		// case DataMessageInfo.VoltageFourModuleCode:// 四路电压采集
		// case DataMessageInfo.CurrentDoubleModuleCode:// 2路电流采集
		// case DataMessageInfo.PressModuleCode:// 压力
		// case DataMessageInfo.MagneticModuleCode:// 磁通道
		// case DataMessageInfo.LightModuleCode:// 光照
		// case DataMessageInfo.CarbonMonoxideModuleCode:// 一氧化碳
		// case DataMessageInfo.MethaneModuleCode:// 甲烷
		// case DataMessageInfo.SmokeModuleCode:// 烟雾
		// case DataMessageInfo.FormaldehydeModuleCode:// 甲醛
		// case DataMessageInfo.AlcoholModuleCode:// 酒精
		// case DataMessageInfo.NaturalGasModuleCode:// 天然气
		// case DataMessageInfo.AmmoniaGasModuleCode:// 氨气
		// case DataMessageInfo.ButaneModuleCode:// 丁烷
		// case DataMessageInfo.OzoneModuleCode:// 臭氧
		// case DataMessageInfo.OrganicSolventModuleCode:// 有机溶剂
		// case DataMessageInfo.FreonModuleCode:// 氟利昂
		// case DataMessageInfo.HydrogenSulfideModuleCode:// 硫化氢
		// case DataMessageInfo.HydrogenModuleCode:// 氢气
		// case DataMessageInfo.LiquefiedGasModuleCode:// 液化气
		// case DataMessageInfo.AirPolluteModuleCode:// 空气污染
		// case DataMessageInfo.CombustibleGasModuleCode:// 可燃气体
		// case DataMessageInfo.OxygenModuleCode:// 氧气
		// case DataMessageInfo.CarbonDioxideModuleCode:// 二氧化碳
		//
		// case DataMessageInfo.TemperatureHumidityModuleCode:// 温湿度
		// case DataMessageInfo.TriaxialModuleCode:// 三轴加速度
		//
		// case DataMessageInfo.TemperatureHumidityHightModuleCode:// 温湿度加光照
		// if (sampleControlLt.findViewById(R.id.sensor_sample_start_bt) == null
		// && sampleControlLt.findViewById(R.id.sensor_sample_stop_bt) == null)
		// {
		// sampleControlLt.removeAllViews();
		// View sampleLt = layoutInflater.inflate(R.layout.sample, null);
		// // Button sampleStartBt = new Button(context);
		// Button sampleStartBt = (Button) sampleLt
		// .findViewById(R.id.sensor_sample_start_bt);
		// // sampleStartBt.setText(context
		// // .getString(R.string.sensor_sample_start));
		// // sampleStartBt.setId(R.id.sensor_sample_start_bt);
		// sampleStartBt.setOnClickListener(this);
		// // sampleControlLt.addView(sampleStartBt, params);
		//
		// // Button sampleStopBt = new Button(context);
		// Button sampleStopBt = (Button) sampleLt
		// .findViewById(R.id.sensor_sample_stop_bt);
		// // sampleStopBt.setText(context
		// // .getString(R.string.sensor_sample_start));
		// // sampleStopBt.setId(R.id.sensor_sample_stop_bt);
		// sampleStopBt.setOnClickListener(this);
		// // sampleControlLt.addView(sampleStopBt, params);
		// sampleControlLt.addView(sampleLt, params);
		// }
		// break;
		// case DataMessageInfo.DADoubleModuleCode:
		// break;
		// case DataMessageInfo.GPoutFourModuleCode:
		// if (sampleControlLt.findViewById(R.id.sensor_gpout1) == null) {
		// sampleControlLt.removeAllViews();
		// View gpoutLt = layoutInflater.inflate(R.layout.gpout, null);
		// ((ToggleButton) gpoutLt.findViewById(R.id.sensor_gpout1))
		// .setOnCheckedChangeListener(this);
		// ((ToggleButton) gpoutLt.findViewById(R.id.sensor_gpout2))
		// .setOnCheckedChangeListener(this);
		// ((ToggleButton) gpoutLt.findViewById(R.id.sensor_gpout3))
		// .setOnCheckedChangeListener(this);
		// ((ToggleButton) gpoutLt.findViewById(R.id.sensor_gpout4))
		// .setOnCheckedChangeListener(this);
		// sampleControlLt.addView(gpoutLt, params);
		// }
		// break;
		// case DataMessageInfo.GPinFourModuleCode:
		// if (sampleControlLt.findViewById(R.id.sensor_gpin1) == null) {
		// sampleControlLt.removeAllViews();
		// View gpinLt = layoutInflater.inflate(R.layout.gpin, null);
		// // ImageButton gpin1 = (ImageButton) gpinLt
		// // .findViewById(R.id.sensor_gpout1);
		// // gpin1.setOnClickListener(this);
		// // ImageButton gpin2 = (ImageButton) gpinLt
		// // .findViewById(R.id.sensor_gpout2);
		// // gpin1.setOnClickListener(this);
		// // ImageButton gpin3 = (ImageButton) gpinLt
		// // .findViewById(R.id.sensor_gpout3);
		// // gpin3.setOnClickListener(this);
		// // ImageButton gpin4 = (ImageButton) gpinLt
		// // .findViewById(R.id.sensor_gpout4);
		// // gpin4.setOnClickListener(this);C
		// sampleControlLt.addView(gpinLt, params);
		// }
		// break;
		// case DataMessageInfo.InfraredModuleCode:
		// break;
		// case DataMessageInfo.MegneticApproachModuleCode:
		// break;
		// case DataMessageInfo.MetalApproachModuleCode:
		// break;
		// case DataMessageInfo.IRControlModuleCode:
		// break;
		// }

	}

	public void updateControlLayout(DataBaseMessage data) {
		if (node == null)
			return;
		int deviceId = node.getDeviceId().getDeviceId();
		ControlInfoRsp controlInfoRsp = (ControlInfoRsp) ((SensorControlResponseMessage) data)
				.getRspInfo();
		if (DataMessageInfo.isSmaple(deviceId)) {

		} else {
			switch (deviceId) {
			case DataMessageInfo.DADoubleModuleCode:
				break;
			case DataMessageInfo.GPoutFourModuleCode:
				if ((sampleControlLt.findViewById(R.id.sensor_output_gp_lt) != null)
						&& (controlInfoRsp.getResult() == 0)) {
					Button currentBt = null;
					switch (gpOutPosition) {
					case 0:
						currentBt = ((Button) sampleControlLt
								.findViewById(R.id.sensor_gpin1));
						break;
					case 1:
						currentBt = ((Button) sampleControlLt
								.findViewById(R.id.sensor_gpin2));
						break;
					case 2:
						currentBt = ((Button) sampleControlLt
								.findViewById(R.id.sensor_gpin3));
						break;
					case 3:
						currentBt = ((Button) sampleControlLt
								.findViewById(R.id.sensor_gpin4));
						break;
					}
					if (currentBt != null && currentBt.getTag() != null
							&& currentBt.getTag().toString().equals("true")) {
						currentBt.setTag("false");
						// currentBt.setBackgroundResource(R.drawable.gp_out_off);
						// currentBt.setText(R.string.sensor_gpout1_off);
					} else {
						currentBt.setTag("true");
						// currentBt.setBackgroundResource(R.drawable.gp_out_on);
						// currentBt.setText(R.string.sensor_gpout1_on);
					}

				}
				break;
			case DataMessageInfo.GPinFourModuleCode:
				if ((sampleControlLt.findViewById(R.id.sensor_gp_in_lt) != null)) {

				}
				break;
			case DataMessageInfo.InfraredModuleCode:

				break;
			case DataMessageInfo.MegneticApproachModuleCode:
				break;
			case DataMessageInfo.MetalApproachModuleCode:
				break;
			case DataMessageInfo.IRControlModuleCode:
				break;
			}
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.sensor_sample_start_bt:
			callback.sensorControl(
					new SampleControl(ControlModuleCode.SampleCode, 0, node
							.getChannelNo().getChannel(), 0x52, 1.0f, 1, 1, 0,
							1, 0.0f, "ABCD"), 24);
			break;
		case R.id.sensor_sample_stop_bt:
			callback.sensorControl(
					new SampleControl(ControlModuleCode.SampleCode, 0, node
							.getChannelNo().getChannel(), 0x12, 1.0f, 1, 1, 0,
							1, 0.0f, "ABCD"), 24);
			break;
		case R.id.sensor_gpin1:
			gpOutPosition = 0;
			gpoutControl(v);
			break;
		case R.id.sensor_gpin2:
			gpOutPosition = 1;
			gpoutControl(v);
			break;
		case R.id.sensor_gpin3:
			gpOutPosition = 2;
			gpoutControl(v);
			break;
		case R.id.sensor_gpin4:
			gpOutPosition = 3;
			gpoutControl(v);
			break;
		case R.id.sensor_da1_bt:
			daControl(0, da1Et.getText().toString());
			break;
		case R.id.sensor_da2_bt:
			daControl(1, da2Et.getText().toString());
			break;
		case R.id.sensor_ir_television_switch:// 电视开关
			irControl(0);
			break;
		case R.id.sensor_ir_television_signal:// 电视信号源
			irControl(2);
			break;
		case R.id.sensor_ir_television_vol_decrease:// 电视Vol-
			irControl(3);
			break;
		case R.id.sensor_ir_television_menu:// 电视菜单
			irControl(4);
			break;
		case R.id.sensor_ir_television_vol_increase:// 电视Vol+
			irControl(5);
			break;
		case R.id.sensor_ir_television_ch_decrease:// 电视Ch-
			irControl(6);
			break;
		case R.id.sensor_ir_television_silence:// 电视静音
			irControl(7);
			break;
		case R.id.sensor_ir_television_ch_increase:// 电视CH+
			irControl(8);
			break;
		case R.id.sensor_ir_dvd_player_switch:// 影碟机进出仓
			irControl(9);
			break;
		case R.id.sensor_ir_dvd_player_play:// 影碟机播放
			irControl(10);
			break;
		case R.id.sensor_ir_dvd_player_stop:// 影碟机停止
			irControl(11);
			break;
		case R.id.sensor_ir_dvd_player_silence:// 影碟机静音
			irControl(12);
			break;
		case R.id.sensor_ir_dvd_player_ch_increase:// 影碟机CH+
			irControl(13);
			break;
		case R.id.sensor_ir_dvd_player_ch_decrease:// 影碟机CH-
			irControl(14);
			break;
		case R.id.sensor_ir_amplifier_silence:// 功放机静音
			irControl(15);
			break;
		case R.id.sensor_ir_amplifier_voice_increase:// 功放机音量加
			irControl(16);
			break;
		case R.id.sensor_ir_amplifier_voice_decrease:// 功放机音量减
			irControl(17);
			break;
		case R.id.sensor_ir_ari_condition_cool:// 空调凉爽
			irControl(18);
			break;
		case R.id.sensor_ir_ari_condition_comfort:// 空调舒服
			irControl(19);
			break;
		case R.id.sensor_ir_ari_condition_warn:// 空调暖和
			irControl(20);
			break;
		case R.id.sensor_ir_ari_condition_switch:// 空调开关
			irControl(21);
			break;
		// case R.id.sensor_ir1:
		// irControl(1);
		// break;
		// case R.id.sensor_ir2:
		// irControl(2);
		// break;
		// case R.id.sensor_ir3:
		// irControl(3);
		// break;
		// case R.id.sensor_ir4:
		// irControl(4);
		// break;
		}
	}

	private void gpoutControl(View v) {
		boolean isOn = false;
		if (v.getTag() != null && v.getTag().toString().equals("true")) {
			isOn = true;
		}
		int data1 = 1 << gpOutPosition;
		int data2 = 0;
		if (isOn == true) {
			data2 = 1 << gpOutPosition;
		} else
			data2 = ~(1 << gpOutPosition);
		byte[] data = new byte[4];
		System.arraycopy(Utile.Int22Bytes(data1), 0, data, 0, 2);
		System.arraycopy(Utile.Int22Bytes(data2), 0, data, 2, 2);
		callback.sensorControl(new OutputControl(ControlModuleCode.OutputCode,
				0x02, 0x00, data), 8);
	}

	private void daControl(int ChannelIndex, String valueS) {
		if (valueS == null || Utile.isDigit(valueS)) {
			int value = Integer.valueOf(valueS);
			if (value > -1 || value < 1096) {
				int devInx = 0;
				int data1 = 0;
				int data2 = 0;
				if (ChannelIndex == 0) {
					devInx = 0x01;
					data1 = (int) value;
					data2 = 0;
				} else {
					devInx = 0x02;
					data1 = 0;
					data2 = (int) value;
				}
				byte[] paramData = new byte[4];
				System.arraycopy(Utile.Int22Bytes(data1), 0, paramData, 0, 2);
				System.arraycopy(Utile.Int22Bytes(data2), 0, paramData, 2, 2);
				callback.sensorControl(new OutputControl(
						ControlModuleCode.OutputCode, 0x01, devInx, paramData),
						8);
			} else {
				Toast.makeText(context, "输入必须为0到4096之间的数字", Toast.LENGTH_LONG)
						.show();
			}
		} else {
			Toast.makeText(context, "输入必须为数字", Toast.LENGTH_LONG).show();
		}
	}

	private void irControl(int number) {
		int devParm = (number << 8) | 0x07;
		callback.sensorControl(new OutputControl(ControlModuleCode.OutputCode,
				0x03, devParm, new byte[4]), 8);
	}
}

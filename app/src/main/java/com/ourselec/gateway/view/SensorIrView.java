package com.ourselec.gateway.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.ourselec.gateway.R;
import com.ourselec.gateway.node.Node;
import com.ourselec.gateway.pc.message.DataBaseMessage;
import com.ourselec.gateway.pc.message.DataMessageInfo;
import com.ourselec.gateway.pc.message.SensorControlResponseMessage;
import com.ourselec.gateway.pc.message.controlinfo.ControlInfoRsp;
import com.ourselec.gateway.pc.message.controlinfo.ControlModuleCode;
import com.ourselec.gateway.pc.message.controlinfo.OutputControl;
import com.ourselec.gateway.util.Utile;

public class SensorIrView implements OnItemClickListener,
		OnItemLongClickListener, DialogInterface.OnClickListener,
		OnCheckedChangeListener {
	private final String TAG = "SensorIrView";
	Context context;
	LinearLayout sampleLt;
	View irView;
	AppsAdapter gridAdapter;
	SensorControlCallback callback;
	Node node;

	CheckBox irChannelCb1, irChannelCb2, irChannelCb3;
	RadioGroup irModelRg;
	TextView studyStateTv;
	boolean isStudy;
	final int studing = 0x01;
	final int studyOver = 0x02;
	int studyState = studyOver;

	AlertDialog dialog;
	View textEntryView;
	SharedPreferences sharedPref;

	int currentPosition;

	// RadioButton irOutputModelRb, irStudyModelRb;

	public SensorIrView(Context context, LinearLayout lt,
			SensorControlCallback callback) {
		this.context = context;
		this.sampleLt = lt;
		this.callback = callback;
	}

	public void setNode(Node node) {
		this.node = node;
		if (node == null) {
			this.sampleLt.removeAllViews();
		} else {
			sharedPref = context.getSharedPreferences(
					Utile.getProductNum(node.getProductNum().getProductNum()),
					0);
			updateControlLayout();
		}
	}

	private void updateControlLayout() {
		LayoutInflater layoutInflater = LayoutInflater.from(context);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.FILL_PARENT);
		if (node.getDeviceId().getDeviceId() == DataMessageInfo.IRControlModuleCode) {
			sampleLt.removeAllViews();
			if (sampleLt.findViewById(R.id.sensor_output_ir_lt2) == null) {
				if (irView == null) {
					irView = layoutInflater.inflate(R.layout.sensor_output_ir2,
							null);
					GridView gridView = (GridView) irView
							.findViewById(R.id.sensor_output_ir_gv);
					gridAdapter = new AppsAdapter(context);
					gridView.setAdapter(gridAdapter);
					gridView.setOnItemClickListener(this);
					gridView.setOnItemLongClickListener(this);
					irChannelCb1 = (CheckBox) irView
							.findViewById(R.id.sensor_output_ir_channel1_cb);
					irChannelCb2 = (CheckBox) irView
							.findViewById(R.id.sensor_output_ir_channel2_cb);

					irChannelCb3 = (CheckBox) irView
							.findViewById(R.id.sensor_output_ir_channel3_cb);
					irModelRg = (RadioGroup) irView
							.findViewById(R.id.sensor_ir_model_rg);
					irModelRg.setOnCheckedChangeListener(this);
					studyStateTv = (TextView) irView
							.findViewById(R.id.sensor_output_ir_state);
					// RadioButton irOutputModelRb = (RadioButton) irView
					// .findViewById(R.id.sensor_ir_output_model_rb);
					// irStudyModelRb = (RadioButton) irView
					// .findViewById(R.id.sensor_ir_study_model_rb);
				}
				gridAdapter.notifyDataSetChanged();
				irView.postInvalidate();
				studyStateTv.setText(String.valueOf(0));
				sampleLt.addView(irView, params);
			}
		}
	}

	public void updateControlLayout(DataBaseMessage data) {
		if (node == null)
			return;
		int deviceId = node.getDeviceId().getDeviceId();
		ControlInfoRsp controlInfoRsp = (ControlInfoRsp) ((SensorControlResponseMessage) data)
				.getRspInfo();
		if (deviceId == DataMessageInfo.IRControlModuleCode) {
			if ((sampleLt.findViewById(R.id.sensor_output_ir_lt2) == null)
					|| !isStudy)
				return;
			Toast toast = null;
			switch (controlInfoRsp.getResult()) {
			case 0:
				toast = Toast.makeText(context,
						R.string.sensor_output_ir_study_start,
						Toast.LENGTH_SHORT);
				break;
			case 1:
				toast = Toast.makeText(context,
						R.string.sensor_output_ir_study_success,
						Toast.LENGTH_SHORT);
				studyState = studyOver;
				studyStateTv.setText(String.valueOf(0));
				sharedPref
						.edit()
						.putBoolean(String.valueOf(currentPosition) + "state",
								true).commit();
				gridAdapter.notifyDataSetChanged();
				irView.postInvalidate();
				break;
			case 2:
				toast = Toast.makeText(context,
						R.string.sensor_output_ir_study_fail,
						Toast.LENGTH_SHORT);
				studyState = studyOver;
				studyStateTv.setText(String.valueOf(0));
				break;
			}
			if (toast != null) {
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
			}
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		irControl(arg2);
		currentPosition = arg2;
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// Log.i(TAG, "onItemLongClick" + arg2);
		// if (!isStudy)
		// return false;
		if (textEntryView == null) {
			LayoutInflater factory = LayoutInflater.from(context);
			textEntryView = factory.inflate(R.layout.alert_dialog_text_entry,
					null);
		}
		if (dialog == null) {
			dialog = new AlertDialog.Builder(context)
					.setIcon(R.drawable.alert_dialog_icon).setTitle("请输入新名称")
					.setView(textEntryView).setPositiveButton("确定", this)
					.setNegativeButton("取消", this).create();
		}
		dialog.show();
		currentPosition = arg2;
		return true;
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		// Log.i(TAG, "onClick" + which);
		switch (which) {
		case DialogInterface.BUTTON_POSITIVE:
			EditText changeEt = (EditText) textEntryView
					.findViewById(R.id.sensor_ir_study_change_name_et);
			sharedPref
					.edit()
					.putString(String.valueOf(currentPosition),
							changeEt.getText().toString()).commit();
			gridAdapter.notifyDataSetChanged();
			irView.postInvalidate();
			break;
		case DialogInterface.BUTTON_NEGATIVE:
			break;
		}
	}

	@Override
	public void onCheckedChanged(RadioGroup arg0, int arg1) {
		switch (arg1) {
		case R.id.sensor_ir_output_model_rb:
			isStudy = false;
			studyState = studyOver;
			break;
		case R.id.sensor_ir_study_model_rb:
			isStudy = true;
			break;
		}

	}

	private void irControl(int number) {
		// int devParm = (number << 8) | 0xC7;
		int devParm = 0;
		if (irChannelCb1.isChecked())
			devParm |= 0x01;
		if (irChannelCb2.isChecked())
			devParm |= 0x02;
		if (irChannelCb3.isChecked())
			devParm |= 0x04;
		if (!isStudy) {
			devParm |= 0x00;
		} else {
			if (studyState == studing) {
				showToast(R.string.sensor_output_ir_studing);
				return;
			} else {
				studyState = studing;
				handler.sendEmptyMessage(0);
			}
			devParm |= 0x80;
		}
		devParm |= (number << 8);
		Log.i(TAG, "irControl" + Integer.toHexString(devParm));
		callback.sensorControl(new OutputControl(ControlModuleCode.OutputCode,
				0x03, devParm, new byte[4]), 8);
	}

	private void showToast(int resource) {
		Toast toast = Toast.makeText(context, resource, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}

	public class AppsAdapter extends BaseAdapter {
		Context context;

		private final int COUNT = 20;

		public AppsAdapter(Context context) {
			this.context = context;

		}

		public View getView(int position, View convertView, ViewGroup parent) {
			// Log.i(TAG, "getView" + position);
			TextView labelTv;

			if (convertView == null) {
				labelTv = new TextView(context);
				labelTv.setLayoutParams(new GridView.LayoutParams(
						GridView.LayoutParams.FILL_PARENT,
						GridView.LayoutParams.FILL_PARENT));
				labelTv.setGravity(Gravity.CENTER);
				labelTv.setTextSize(20f);
			} else {
				labelTv = (TextView) convertView;
			}

			// String label = (String) getItem(position)[0];
			// Log.i(TAG, "getView " + label);
			// if (label != null && label.length() > 0)
			Object[] infos = getItem(position);
			labelTv.setText((String) infos[0]);
			if ((Boolean) infos[1])
				labelTv.setBackgroundResource(R.drawable.ir_press);
			else
				labelTv.setBackgroundResource(R.drawable.ir_narmol);
			return labelTv;
		}

		public final int getCount() {
			return COUNT;
		}

		public final Object[] getItem(int position) {
			// Log.i(TAG, "getItem" + String.valueOf(position));
			Object[] infos = new Object[2];
			infos[0] = sharedPref.getString(String.valueOf(position), null);
			infos[1] = Boolean.valueOf(sharedPref.getBoolean(
					String.valueOf(position) + "state", false));
			return infos;
		}

		public final long getItemId(int position) {
			return position;
		}
	}

	private int count = 0;
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (studyState == studing) {
				if (count > 20) {
					showToast(R.string.sensor_output_ir_study_fail);
					studyStateTv.setText(String.valueOf(0));
					studyState = studyOver;
					count = 0;
				} else {
					studyStateTv.setText(String.valueOf(count++));
					handler.sendEmptyMessageDelayed(0, 1 * 1000);
				}
			} else {
				studyStateTv.setText(String.valueOf(0));
				count = 0;
			}
		};
	};
}

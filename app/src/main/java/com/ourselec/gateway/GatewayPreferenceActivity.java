package com.ourselec.gateway;

/**
 * 网关配置界面
 * @author yangtianfei(ytf2737179@163.com)
 * 
 */
import java.net.SocketException;

import android.app.Activity;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.view.KeyEvent;
import android.widget.Toast;

import com.ourselec.gateway.process.UdpManager;
import com.ourselec.gateway.util.Utile;

public class GatewayPreferenceActivity extends PreferenceActivity implements
		OnPreferenceChangeListener {

	int resultCode = Activity.RESULT_CANCELED;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		addPreferencesFromResource(R.xml.gateway_preference);

		EditTextPreference hostIpAddressPf = ((EditTextPreference) findPreference("host_ip_address"));
		hostIpAddressPf.setSummary(hostIpAddressPf.getText());
		hostIpAddressPf.setOnPreferenceChangeListener(this);

		EditTextPreference hostPortPf = ((EditTextPreference) findPreference("host_port"));
		hostPortPf.setSummary(hostPortPf.getText());
		hostPortPf.setOnPreferenceChangeListener(this);

		EditTextPreference localPortPf = ((EditTextPreference) findPreference("local_port"));
		localPortPf.setSummary(localPortPf.getText());
		localPortPf.setOnPreferenceChangeListener(this);

		EditTextPreference initChannelNumPf = ((EditTextPreference) findPreference("init_channel_num"));
		initChannelNumPf.setSummary(initChannelNumPf.getText());
		initChannelNumPf.setOnPreferenceChangeListener(this);

		EditTextPreference panIdPf = ((EditTextPreference) findPreference("panid"));
		panIdPf.setSummary(panIdPf.getText());
		panIdPf.setOnPreferenceChangeListener(this);

		EditTextPreference productNumPf = ((EditTextPreference) findPreference("product_num"));
		productNumPf.setSummary(productNumPf.getText());
		productNumPf.setOnPreferenceChangeListener(this);
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK)
			setResult(resultCode);
		return super.onKeyUp(keyCode, event);
	}

	@Override
	public boolean onPreferenceChange(Preference arg0, Object arg1) {
		String key = arg0.getKey();
		String value = (String) arg1;
		arg0.setSummary(value);
		if (key.equals("host_ip_address")) {
			if (isIpAddress(value)) {
				UdpManager.getInstance().setHostIp(value);
				return true;
			}
			return false;
		} else if (key.equals("host_port")) {
			if (isDigit(value) && isInPortRange(Integer.valueOf(value))) {
				UdpManager.getInstance().setHostPort(Integer.valueOf(value));
				return true;
			}
			return false;
		} else if (key.equals("local_port")) {
			if (isDigit(value) && isInPortRange(Integer.valueOf(value))) {
				try {
					UdpManager.getInstance().setLocalPort(
							Integer.valueOf(value));
				} catch (NumberFormatException e) {
					showToast(R.string.preference_err_digit);
					e.printStackTrace();
				}
				return true;
			}
			return false;
		} else if (key.equals("init_channel_num")) {
			if (isDigit(value) && isInChannelRange(Integer.valueOf(value))) {
				resultCode = Activity.RESULT_OK;
				return true;
			}
		} else if (key.equals("panid")) {
			if (isDigit(value) && isInPanIdRange(Integer.valueOf(value))) {
				resultCode = Activity.RESULT_OK;
				return true;
			}
		} else if (key.equals("product_num")) {
			if (isHexDigit(value)) {
				resultCode = Activity.RESULT_OK;
				return true;
			}
		}
		resultCode = Activity.RESULT_CANCELED;
		return false;
	}

	private boolean isIpAddress(String str) {
		if (!Utile.isIpAddress(str)) {
			showToast(R.string.preference_err_ip);
			return false;
		}
		return true;
	}

	private boolean isDigit(String str) {
		if (!Utile.isDigit(str)) {
			showToast(R.string.preference_err_digit);
			return false;
		}
		return true;
	}

	private boolean isInPortRange(int port) {
		if (!Utile.isInPortRange(port)) {
			showToast(R.string.preference_err_port);
			return false;
		}
		return true;
	}

	private boolean isInChannelRange(int channel) {
		if (!Utile.isInChannelRange(channel)) {
			showToast(R.string.preference_err_channel);
			return false;
		}
		return true;
	}

	private boolean isInPanIdRange(int panId) {
		if (!Utile.isInPanIdRange(panId)) {
			showToast(R.string.preference_err_panid);
			return false;
		}
		return true;
	}

	private boolean isHexDigit(String str) {
		if (!Utile.isHexDigit(str)) {
			showToast(R.string.preference_err_digit);
			return false;
		}
		return true;
	}

	private void showToast(int resId) {
		Toast.makeText(GatewayPreferenceActivity.this, getString(resId),
				Toast.LENGTH_LONG).show();
	}
}

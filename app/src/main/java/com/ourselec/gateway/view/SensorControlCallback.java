package com.ourselec.gateway.view;

import com.ourselec.gateway.pc.message.controlinfo.ControlModule;

/**
 * 界面回调类
 * @author yangtianfei(ytf2737179@163.com)
 * 
 */
public interface SensorControlCallback {
	public void sensorControl(ControlModule control, int length);
}

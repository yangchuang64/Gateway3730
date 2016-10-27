package com.ourselec.gateway.pc.message.controlinfo.resolver;

import com.ourselec.gateway.pc.message.controlinfo.ReadSampleControl;

/**
 * 读取采样控制解析器
 * @author yangtianfei(ytf2737179@163.com)
 */
public class ReadSampleControlResolver extends ControlInfoResolver {

	public ReadSampleControlResolver(int command, int devInx, byte[] buffer,
									 int length) {
		super(command, devInx, buffer, length);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object parsorMessage() {
		// TODO Auto-generated method stub
		return new ReadSampleControl(command, devInx, (int) buffer[0]);
	}

}
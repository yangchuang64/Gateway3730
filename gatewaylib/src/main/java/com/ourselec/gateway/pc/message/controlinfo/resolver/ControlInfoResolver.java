package com.ourselec.gateway.pc.message.controlinfo.resolver;

import com.ourselec.gateway.message.MessageResolver;

/**
 * 控制信息解析器
 * @author yangtianfei(ytf2737179@163.com)
 */
public abstract class ControlInfoResolver extends MessageResolver {

	int command;
	int devInx;

	public ControlInfoResolver(int command, int devInx, byte[] buffer,
							   int length) {
		super(buffer, length);
		this.command = command;
		this.devInx = devInx;
	}

}
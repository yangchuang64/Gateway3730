package com.ourselec.gateway.pc.message.serial.resolver;

import com.ourselec.gateway.message.MessageResolver;
/**
 * 串口消息解析器
 * @author yangtianfei(ytf2737179@163.com)
 */
public abstract class SerialDataMessageResovler extends MessageResolver {

	protected int source;
	int dataLen;
	int code;

	public SerialDataMessageResovler(int source, int dataLen, int code,
									 byte[] buffer, int length) {
		super(buffer, length);
		this.source = source;
		this.dataLen = dataLen;
		this.code = code;
		// TODO Auto-generated constructor stub
	}

}
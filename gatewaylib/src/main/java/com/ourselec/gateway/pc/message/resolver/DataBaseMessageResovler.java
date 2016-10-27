package com.ourselec.gateway.pc.message.resolver;

import com.ourselec.gateway.message.MessageResolver;
import com.ourselec.gateway.pc.message.DataBaseMessage;
/**
 * 基本信息解析器
 * @author yangtianfei(ytf2737179@163.com)
 */
public class DataBaseMessageResovler extends MessageResolver {

	int msgLen;
	int code;
	int checkNum;

	public DataBaseMessageResovler(int msgLen, int code, int checkNum,
								   byte[] buffer, int length) {
		super(buffer, length);
		this.msgLen = msgLen;
		this.code = code;
		this.checkNum = checkNum;
	}

	@Override
	public Object parsorMessage() {
		return new DataBaseMessage(msgLen, code, checkNum);
	}

}
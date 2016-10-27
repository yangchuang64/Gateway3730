package com.ourselec.gateway.message;

/**
 * 消息解析基类
 *
 * @author yangtianfei(ytf2737179@163.com)
 */
public abstract class MessageResolver {
	protected byte[] buffer;
	protected int length;

	public MessageResolver(byte[] buffer, int length) {
		this.buffer = buffer;
		this.length = length;
	}

	/**
	 * 解析消息字符数组
	 *
	 * @return 消息类
	 */
	public abstract Object parsorMessage();
}
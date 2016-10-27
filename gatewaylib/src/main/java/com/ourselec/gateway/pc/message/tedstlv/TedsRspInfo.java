package com.ourselec.gateway.pc.message.tedstlv;
/**
 * Teds信息
 * @author yangtianfei(ytf2737179@163.com)
 */
public class TedsRspInfo {
	private int type;
	private int length;
	private Object value;

	public TedsRspInfo(int type, int length, Object value) {
		this.type = type;
		this.length = length;
		this.value = value;
	}

	public int getType() {
		return type;
	}

	public int getLength() {
		return length;
	}

	public Object getValue() {
		return value;
	}
}
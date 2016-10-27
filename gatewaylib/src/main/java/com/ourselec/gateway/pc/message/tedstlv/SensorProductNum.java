package com.ourselec.gateway.pc.message.tedstlv;
/**
 * 产品序列号
 * @author yangtianfei(ytf2737179@163.com)
 */
public class SensorProductNum {
	private byte[] productNum;

	public SensorProductNum(byte[] productNum) {
		this.productNum = productNum;
	}

	public byte[] getProductNum() {
		return productNum;
	}

}
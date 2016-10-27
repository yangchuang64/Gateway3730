package com.ourselec.gateway.pc.message.tedstlv;
/**
 * 版本号
 * @author yangtianfei(ytf2737179@163.com)
 */
public class SensorVersionNumber {

	private int ieeeNumber;
	private int tedsVersionNumber;
	private int sersorModule;
	private int reserved;

	public SensorVersionNumber(int ieeeNumber, int tedsVersionNumber,
							   int sersorModule, int reserved) {
		this.ieeeNumber = ieeeNumber;
		this.tedsVersionNumber = tedsVersionNumber;
		this.sersorModule = sersorModule;
		this.reserved = reserved;
	}

	public int getIeeeNumber() {
		return ieeeNumber;
	}

	public int getTedsVersionNumber() {
		return tedsVersionNumber;
	}

	public int getSersorModule() {
		return sersorModule;
	}

	public int getReserved() {
		return reserved;
	}

}
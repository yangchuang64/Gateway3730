package com.ourselec.gateway.pc.message.controlinfo;

/**
 * 读取采样数据回复
 * @author yangtianfei(ytf2737179@163.com)
 */
public class ReadSampleDataRsp extends ControlModule {

	private int dmod;
	private int dNum;
	private int dUnit;
	private byte[] data;

	public ReadSampleDataRsp(int command, int devInx, int dmod, int dNum,
							 int dUnit, byte[] data) {
		super(command, devInx);
		this.dmod = dmod;
		this.dNum = dNum;
		this.dUnit = dUnit;
		this.data = data;
	}

	public int getDmod() {
		return dmod;
	}

	public int getdNum() {
		return dNum;
	}

	public int getdUnit() {
		return dUnit;
	}

	public byte[] getData() {
		return data;
	}

}
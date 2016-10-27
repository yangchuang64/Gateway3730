package com.ourselec.gateway.pc.message.controlinfo;

/**
 * 采样控制
 * @author yangtianfei(ytf2737179@163.com)
 */
public class SampleControl extends ControlModule {

	private int chEn;
	private int smpPara;
	private float srate;
	private int divRate;
	private int gGNum;
	private int aATime;
	private int sAvr;
	private float fVmax;
	private String fUnit;

	public SampleControl(int command, int devInx, int chEn, int smpPara,
						 float srate, int divRate, int dGNum, int dATime, int sAvr,
						 float fVmax, String fUnit) {
		super(command, devInx);
		this.chEn = chEn;
		this.smpPara = smpPara;
		this.srate = srate;
		this.divRate = divRate;
		this.gGNum = dGNum;
		this.aATime = dATime;
		this.sAvr = sAvr;
		this.fVmax = fVmax;
		this.fUnit = fUnit;
	}

	public int getChEn() {
		return chEn;
	}

	public int getSmpPara() {
		return smpPara;
	}

	public float getSrate() {
		return srate;
	}

	public int getDivRate() {
		return divRate;
	}

	public int getgGNum() {
		return gGNum;
	}

	public int getaATime() {
		return aATime;
	}

	public int getsAvr() {
		return sAvr;
	}

	public float getfVmax() {
		return fVmax;
	}

	public String getfUnit() {
		return fUnit;
	}

}
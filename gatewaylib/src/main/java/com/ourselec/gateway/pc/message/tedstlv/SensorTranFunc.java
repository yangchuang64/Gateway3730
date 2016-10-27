package com.ourselec.gateway.pc.message.tedstlv;

import java.util.List;
/**
 * 传输功能
 * @author yangtianfei(ytf2737179@163.com)
 */
public class SensorTranFunc {
	List<TranFunc> tranFuncs;

	public SensorTranFunc(List<TranFunc> tranFuncs) {
		this.tranFuncs = tranFuncs;
	}

	public List<TranFunc> getTranFuncs() {
		return tranFuncs;
	}

	public static class TranFunc {
		int channelNo;
		int type;

		public TranFunc(int channelNo, int type) {
			this.channelNo = channelNo;
			this.type = type;
		}

		public int getChannelNo() {
			return channelNo;
		}

		public int getType() {
			return type;
		}

	}
}
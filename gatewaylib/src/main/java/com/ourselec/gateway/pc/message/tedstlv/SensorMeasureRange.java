package com.ourselec.gateway.pc.message.tedstlv;

import java.util.List;
/**
 * 传感器测量范围
 * @author yangtianfei(ytf2737179@163.com)
 */
public class SensorMeasureRange {

	List<Range> ranges;

	public SensorMeasureRange(List<Range> ranges) {
		this.ranges = ranges;
	}

	public List<Range> getRangs() {
		return ranges;
	}

}
package com.ourselec.gateway.pc.message.tedstlv;

import java.util.List;
/**
 * 输出范围
 * @author yangtianfei(ytf2737179@163.com)
 */
public class OutputRange {
	List<Range> ranges;

	public OutputRange(List<Range> ranges) {
		this.ranges = ranges;
	}

	public List<Range> getRanges() {
		return ranges;
	}
}
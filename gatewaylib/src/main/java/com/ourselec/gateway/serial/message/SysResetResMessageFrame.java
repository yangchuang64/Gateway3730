package com.ourselec.gateway.serial.message;
/**
 * reset回复消息frame
 *
 */
public class SysResetResMessageFrame extends MessageFrame {
	private int reason;
	private int transportRev;
	private int productId;
	private int majorRel;
	private int minorRel;
	private int hwRev;

	public SysResetResMessageFrame(int length, int command, int reason,
								   int transportRev, int productId, int majorRel, int minorRel,
								   int hwRev) {
		super(length, command);
		this.reason = reason;
		this.transportRev = transportRev;
		this.productId = productId;
		this.majorRel = majorRel;
		this.minorRel = minorRel;
		this.hwRev = hwRev;
	}

	public int getReason() {
		return reason;
	}

	public int getTransportRev() {
		return transportRev;
	}

	public int getProductId() {
		return productId;
	}

	public int getMajorRel() {
		return majorRel;
	}

	public int getMinorRel() {
		return minorRel;
	}

	public int getHwRev() {
		return hwRev;
	}

}
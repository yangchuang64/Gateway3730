package com.ourselec.gateway.serial.message;
/**
 * 建网消息Frame
 *
 */
public class ZdoEndDeviceAnnceIndMessageFrame extends MessageFrame {
	private int srcAddr;
	private int nwkAddr;
	private byte[] iEEEAddr;
	private int capabilites;

	public ZdoEndDeviceAnnceIndMessageFrame(int length, int command,
											int srcAddr, int nwkAddr, byte[] iEEEAddr, int capabilites) {
		super(length, command);
		this.srcAddr = srcAddr;
		this.nwkAddr = nwkAddr;
		this.iEEEAddr = iEEEAddr;
		this.capabilites = capabilites;
	}

	public int getSrcAddr() {
		return srcAddr;
	}

	public int getNwkAddr() {
		return nwkAddr;
	}

	public byte[] getiEEEAddr() {
		return iEEEAddr;
	}

	public int getCapabilites() {
		return capabilites;
	}

}
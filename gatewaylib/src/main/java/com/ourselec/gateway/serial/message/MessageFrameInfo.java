package com.ourselec.gateway.serial.message;
/**
 * frame消息信息
 *
 */
public class MessageFrameInfo {
	public final static int ZB_READ_CONFIGURATION = (0x04 << 8) + 0x26;
	public final static int ZB_READ_CONFIGURATION_RESPONSE = (0x04 << 8) + 0x66;
	public final static int ZB_WRITE_CONFIGURATION = (0x05 << 8) + 0x26;
	public final static int ZB_WRITE_CONFIGURATION_RESPONSE = (0x05 << 8) + 0x66;
	public final static int SYS_RESET_REQ = (0x00 << 8) + 0x41;
	public final static int SYS_RESET_IND = (0x80 << 8) + 0x41;
	public final static int ZB_APP_REGISTER_REQUEST = (0x0A << 8) + 0x26;
	public final static int ZB_APP_REGISTER_RESPONSE = (0x0A << 8) + 0x66;
	public final static int ZB_START_REQUEST = (0x00 << 8) + 0x26;
	public final static int ZB_START_RESPONSE = (0x00 << 8) + 0x66;
	public final static int ZD0_START_CHANGE_IND = (0xC0 << 8) + 0x45;
	public final static int ZB_START_CONFIRM = (0x80 << 8) + 0x46;
	public final static int ZDO_END_DEVICE_ANNCE_IND = (0xC1 << 8) + 0x45;
	public final static int ZB_SEND_DATA_REQUEST = (0x03 << 8) + 0x26;
	public final static int ZB_SEND_DATA_RESPONSE = (0x03 << 8) + 0x66;
	public final static int ZB_SEND_DATA_CONFIRM = (0x83 << 8) + 0x46;
	public final static int ZB_RECEIVE_DATA_INDICATION = (0x87 << 8) + 0x46;
}
package com.ourselec.gateway.serial.message;
/**
 * 发送消息完成消息Frame
 *
 */
public class ZbSendDataConfirmMessageFrame extends MessageFrame {
	//
	private int handle;
	// 锟斤拷莘锟斤拷慕锟斤拷为0x00时锟斤拷锟斤拷示锟斤拷锟酵成癸拷锟斤拷锟斤拷锟斤拷值锟斤拷示失锟斤拷
	private int status;

	public ZbSendDataConfirmMessageFrame(int length, int command, int handle,
										 int status) {
		super(length, command);
		this.handle = handle;
		this.status = status;
	}

	public int getHandle() {
		return handle;
	}

	public int getStatus() {
		return status;
	}

}
package com.ourselec.gateway.serial.message;
/**
 * 注册应用端口frame
 *
 */
public class AppRegisterRequestMessageFrame extends MessageFrame {
	private int appEndPoint;
	private int appProfileId;
	private int deviceId;
	private int deviceVersion;
	private int unused;
	private int inputCommandsNum;
	private byte[] inputCommandsList;
	private int outputCommandsNum;
	private byte[] outputCommandsList;

	public AppRegisterRequestMessageFrame(int length, int command,
										  int appEndPoint, int appProfileId, int deviceId, int deviceVersion,
										  int unused, int inputCommandsNum, byte[] inputCommandsList,
										  int outputCommandsNum, byte[] outputCommandsList) {
		super(length, command);
		this.appEndPoint = appEndPoint;
		this.appProfileId = appProfileId;
		this.deviceId = deviceId;
		this.deviceVersion = deviceVersion;
		this.unused = unused;
		this.inputCommandsNum = inputCommandsNum;
		this.inputCommandsList = inputCommandsList;
		this.outputCommandsNum = outputCommandsNum;
		this.outputCommandsList = outputCommandsList;
	}

	public int getAppEndPoint() {
		return appEndPoint;
	}

	public int getAppProfileId() {
		return appProfileId;
	}

	public int getDeviceId() {
		return deviceId;
	}

	public int getDeviceVersion() {
		return deviceVersion;
	}

	public int getUnused() {
		return unused;
	}

	public int getInputCommandsNum() {
		return inputCommandsNum;
	}

	public byte[] getInputCommandsList() {
		return inputCommandsList;
	}

	public int getOutputCommandsNum() {
		return outputCommandsNum;
	}

	public byte[] getOutputCommandsList() {
		return outputCommandsList;
	}

}
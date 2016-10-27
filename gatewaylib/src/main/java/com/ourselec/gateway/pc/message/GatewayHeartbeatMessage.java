package com.ourselec.gateway.pc.message;

/**
 * 网关心跳信息
 *
 * @author yangtianfei(ytf2737179@163.com)
 */
public class GatewayHeartbeatMessage extends DataBaseMessage {

	private int gatewayType;
	private int gatewayProductNum;

	public GatewayHeartbeatMessage(int length, int command, int checkNum,
								   int gatewayType, int gatewayProductNum) {
		super(length, command, checkNum);
		this.gatewayType = gatewayType;
		this.gatewayProductNum = gatewayProductNum;
	}

	public int getGatewayType() {
		return gatewayType;
	}

	public int getGatewayProductNum() {
		return gatewayProductNum;
	}

	public void setGatewayProductNum(int productNum) {
		this.gatewayProductNum = productNum;
	}

}
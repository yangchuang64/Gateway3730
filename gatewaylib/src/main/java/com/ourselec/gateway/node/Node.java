package com.ourselec.gateway.node;

import com.ourselec.gateway.pc.message.DataMessageInfo;
import com.ourselec.gateway.pc.message.tedstlv.SensorChannelNo;
import com.ourselec.gateway.pc.message.tedstlv.SensorDeviceId;
import com.ourselec.gateway.pc.message.tedstlv.SensorManufacInfo;
import com.ourselec.gateway.pc.message.tedstlv.SensorProductNum;
import com.ourselec.gateway.pc.message.tedstlv.SensorVersionNumber;

/**
 * 节点类
 *
 * @author yangtianfei(ytf2737179@163.com)
 */
public class Node {

	private String lable;
	private int heartbeatInfo;
	private int sensorFlag;
	private int gatewayProductNum;
	private int noteTransferType;
	private int noteAddress;

	private SensorVersionNumber version;
	private SensorDeviceId deviceId;
	private SensorManufacInfo manufacInfo;
	private SensorProductNum productNum;
	private SensorChannelNo channelNo;
	private long nLifeTime;
	private boolean modifyState;

	public Node(int heartbeatInfo, int sensorFlag, int gatewayProductNum,
				int noteTransferType, int noteAddress) {
		this.heartbeatInfo = heartbeatInfo;
		this.sensorFlag = sensorFlag;
		this.gatewayProductNum = gatewayProductNum;
		this.noteTransferType = noteTransferType;
		this.noteAddress = noteAddress;
	}

	public String getLable() {
		return lable;
	}

	public int getHeartbeatInfo() {
		return heartbeatInfo;
	}

	public int getSensorFlag() {
		return sensorFlag;
	}

	public int getGatewayProductNum() {
		return gatewayProductNum;
	}

	public int getNoteTransferType() {
		return noteTransferType;
	}

	public int getNoteAddress() {
		return noteAddress;
	}

	public void setLifeTime(long time) {
		this.nLifeTime = time;
	}

	public long getLifeTime() {
		return nLifeTime;
	}

	public void updateBaseInfo(SensorVersionNumber version,
							   SensorDeviceId deviceId, SensorManufacInfo manufacInfo,
							   SensorProductNum productNum, SensorChannelNo channelNo) {
		this.version = version;
		this.deviceId = deviceId;
		this.manufacInfo = manufacInfo;
		this.productNum = productNum;
		this.channelNo = channelNo;
		this.modifyState = true;
		modifyNodeMark(deviceId.getDeviceId());
	}

	private void modifyNodeMark(int deviceId) {
		switch (deviceId) {
			case DataMessageInfo.VoltageFourModuleCode:
				lable = DataMessageInfo.VoltageFourModuleName;
				break;
			case DataMessageInfo.CurrentDoubleModuleCode:
				lable = DataMessageInfo.CurrentDoubleModuleName;
				break;
			case DataMessageInfo.PressModuleCode:
				lable = DataMessageInfo.PressModuleName;
				break;
			case DataMessageInfo.MagneticModuleCode:
				lable = DataMessageInfo.MagneticModuleName;
				break;
			case DataMessageInfo.LightModuleCode:
				lable = DataMessageInfo.LightModuleName;
				break;
			case DataMessageInfo.CarbonMonoxideModuleCode:// 一氧化碳
				lable = DataMessageInfo.CarbonMonoxideModuleName;
				break;
			case DataMessageInfo.MethaneModuleCode:// 甲烷
				lable = DataMessageInfo.MethaneModuleName;
				break;
			case DataMessageInfo.SmokeModuleCode:// 烟雾
				lable = DataMessageInfo.SmokeModuleName;
				break;
			case DataMessageInfo.FormaldehydeModuleCode:// 甲醛
				lable = DataMessageInfo.FormaldehydeModuleName;
				break;
			case DataMessageInfo.AlcoholModuleCode:// 酒精
				lable = DataMessageInfo.AlcoholModuleName;
				break;
			case DataMessageInfo.NaturalGasModuleCode:// 天然气
				lable = DataMessageInfo.NaturalGasModuleName;
				break;
			case DataMessageInfo.AmmoniaGasModuleCode:// 氨气
				lable = DataMessageInfo.AmmoniaGasModuleName;
				break;
			case DataMessageInfo.ButaneModuleCode:// 丁烷
				lable = DataMessageInfo.ButaneModuleName;
				break;
			case DataMessageInfo.OzoneModuleCode:// 臭氧
				lable = DataMessageInfo.OzoneModuleModuleName;
				break;
			case DataMessageInfo.OrganicSolventModuleCode:// 有机溶剂
				lable = DataMessageInfo.OrganicSolventModuleName;
				break;
			case DataMessageInfo.FreonModuleCode:// 氟利昂
				lable = DataMessageInfo.FreonModuleName;
				break;
			case DataMessageInfo.HydrogenSulfideModuleCode:// 硫化氢
				lable = DataMessageInfo.HydrogenSulfideModuleName;
				break;
			case DataMessageInfo.HydrogenModuleCode:// 氢气
				lable = DataMessageInfo.HydrogenModuleName;
				break;
			case DataMessageInfo.LiquefiedGasModuleCode:// 液化气
				lable = DataMessageInfo.LiquefiedGasModuleName;
				break;
			case DataMessageInfo.AirPolluteModuleCode:// 空气污染
				lable = DataMessageInfo.AriPolluteModuleName;
				break;
			case DataMessageInfo.CombustibleGasModuleCode:// 可燃气体
				lable = DataMessageInfo.CombustibleGasModuleName;
				break;
			case DataMessageInfo.OxygenModuleCode:// 氧气
				lable = DataMessageInfo.OxygenModuleModuleName;
				break;
			case DataMessageInfo.CarbonDioxideModuleCode:// 二氧化碳
				lable = DataMessageInfo.CarbonDioxideModuleName;
				break;
			case DataMessageInfo.TemperatureHumidityModuleCode:// 温湿度
				lable = DataMessageInfo.TemperatureHumidityModuleName;
				break;
			case DataMessageInfo.TriaxialModuleCode:// 三轴加速度
				lable = DataMessageInfo.TriaxialModuleName;
				break;
			case DataMessageInfo.DADoubleModuleCode:// 2路DA输出
				lable = DataMessageInfo.DADoubleModuleName;
				break;
			case DataMessageInfo.GPoutFourModuleCode:// 四路GPOUT
				lable = DataMessageInfo.GPoutFourModuleName;
				break;
			case DataMessageInfo.GPinFourModuleCode:// 四路GPIN
				lable = DataMessageInfo.GPinFourModuleName;
				break;
			case DataMessageInfo.InfraredModuleCode:// 红外接近开关
				lable = DataMessageInfo.InfraredModuleModuleName;
				break;
			case DataMessageInfo.MegneticApproachModuleCode:// 磁接近开关
				lable = DataMessageInfo.MegneticApproachModuleName;
				break;
			case DataMessageInfo.MetalApproachModuleCode:// 金属接近开关
				lable = DataMessageInfo.MetalApproachModuleName;
				break;
			case DataMessageInfo.IRControlModuleCode:// IR控制
				lable = DataMessageInfo.IRControlModuleName;
				break;
			case DataMessageInfo.TemperatureHumidityHightModuleCode:// 温湿度加光照
				lable = DataMessageInfo.TemperatureHumidityHightModuleName;
				break;
		}
	}

	public SensorVersionNumber getVersion() {
		return version;
	}

	public SensorDeviceId getDeviceId() {
		return deviceId;
	}

	public SensorManufacInfo getManufacInfo() {
		return manufacInfo;
	}

	public SensorProductNum getProductNum() {
		return productNum;
	}

	public SensorChannelNo getChannelNo() {
		return channelNo;
	}

	public boolean getModifyState() {
		return modifyState;
	}
}
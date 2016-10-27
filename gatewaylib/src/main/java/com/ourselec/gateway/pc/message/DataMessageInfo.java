package com.ourselec.gateway.pc.message;

/**
 * 消息信息
 *
 * @author yangtianfei(ytf2737179@163.com)
 */
public class DataMessageInfo {
	public final static int GateWayHeartbeatCode = 0x0001;
	public final static int PcHeartbeatCode = 0x9001;
	public final static int SensorHeartbeatCode = 0x0000;
	public final static int PcReqTedsCode = 0x0010;
	public final static int SensorRspTedsCode = 0x8010;
	public final static int PcControlCode = 0x0011;
	public final static int SensorRspControlCode = 0x4011;
	public final static int SensorUploadDataCode = 0x8011;
	public final static int SensorSendEndCode = 0xF011;
	public final static int PcRequestZbBaseInfoCode = 0x0100;
	public final static int SensorBaseInfoRspCode = 0x8100;
	public final static int PcModifyZbSensorDeviceTypeCode = 0x0101;
	public final static int sensorModifyDeviceTypeRspCode = 0x8101;
	public final static int PcModifyZbPanIdCode = 0x0102;
	public final static int SensorModifyPanIdRspCode = 0x8102;
	public final static int PcModifyZbChannelCode = 0x0103;
	public final static int SensorModifyChannelRspCode = 0x8103;

	public final static int VoltageFourModuleCode = (0x01 << 8) + 0x01;// 四路电压采集
	public final static int CurrentDoubleModuleCode = (0x01 << 8) + 0x02;// 2路电流采集
	public final static int PressModuleCode = (0x01 << 8) + 0x03;// 压力
	public final static int MagneticModuleCode = (0x01 << 8) + 0x04;// 磁通道
	public final static int LightModuleCode = (0x01 << 8) + 0x05;// 光照
	public final static int CarbonMonoxideModuleCode = (0x01 << 8) + 0x06;// 一氧化碳
	public final static int MethaneModuleCode = (0x01 << 8) + 0x07;// 甲烷
	public final static int SmokeModuleCode = (0x01 << 8) + 0x08;// 烟雾
	public final static int FormaldehydeModuleCode = (0x01 << 8) + 0x09;// 甲醛
	public final static int AlcoholModuleCode = (0x01 << 8) + 0x0A;// 酒精
	public final static int NaturalGasModuleCode = (0x01 << 8) + 0x0B;// 天然气
	public final static int AmmoniaGasModuleCode = (0x01 << 8) + 0x0C;// 氨气
	public final static int ButaneModuleCode = (0x01 << 8) + 0x0D;// 丁烷
	public final static int OzoneModuleCode = (0x01 << 8) + 0x0E;// 臭氧
	public final static int OrganicSolventModuleCode = (0x01 << 8) + 0x0F;// 有机溶剂
	public final static int FreonModuleCode = (0x01 << 8) + 0x010;// 氟利昂
	public final static int HydrogenSulfideModuleCode = (0x1 << 8) + 0x11;// 硫化氢
	public final static int HydrogenModuleCode = (0x01 << 8) + 0x12;// 氢气
	public final static int LiquefiedGasModuleCode = (0x01 << 8) + 0x13;// 液化气
	public final static int AirPolluteModuleCode = (0x01 << 8) + 0x14;// 空气污染
	public final static int CombustibleGasModuleCode = (0x01 << 8) + 0x15;// 可燃气体
	public final static int OxygenModuleCode = (0x01 << 8) + 0x16;// 氧气
	public final static int CarbonDioxideModuleCode = (0x01 << 8) + 0x17;// 二氧化碳

	public final static int TemperatureHumidityModuleCode = (0x02 << 8) + 0x01;// 温湿度
	public final static int TriaxialModuleCode = (0x02 << 8) + 0x02;// 三轴加速度

	public final static int DADoubleModuleCode = (0x03 << 8) + 0x01;// 2路DA输出

	public final static int GPoutFourModuleCode = (0x04 << 8) + 0x01;// 四路GPOUT
	public final static int GPinFourModuleCode = (0x04 << 8) + 0x02;// 四路GPIN
	public final static int InfraredModuleCode = (0x04 << 8) + 0x03;// 红外接近开关
	public final static int MegneticApproachModuleCode = (0x04 << 8) + 0x04;// 磁接近开关
	public final static int MetalApproachModuleCode = (0x04 << 8) + 0x05;// 金属接近开关

	public final static int IRControlModuleCode = (0x05 << 8) + 0x01;// IR控制

	public final static int TemperatureHumidityHightModuleCode = (0x06 << 8) + 0x01;// 温湿度加光照

	// public final static int TemperatureHumidityModuleCode = (0x02 << 8) +
	// 0x01;
	// public final static int TemperatureHumidityHightModuleCode = (0x03 << 8)
	// + 0x01;
	// public final static int ValtageOutputModuleCode = (0x40 << 8) + 0x01;
	// public final static int GpioModuleCode = (0x41 << 8) + 0x01;
	/**
	 * 消息信息
	 *
	 * @return 是否为采样code
	 */
	public static boolean isSmaple(int moduleCode) {
		if (((0xff00 & moduleCode) > 0 && (0xff00 & moduleCode) < 0x0300)
				|| (0xff00 & moduleCode) == 0x0600)
			return true;
		return false;
	}

	/**
	 * 判断传感器是否为气体
	 *
	 * @return
	 */
	public static boolean isGasSensor(int moduleCode) {
		if (((0xff00 & moduleCode) == 0x0100) && ((0xff & moduleCode) != 0x01)
				&& ((0xff & moduleCode) != 0x02)
				&& ((0xff & moduleCode) != 0x03)
				&& ((0xff & moduleCode) != 0x05))
			return true;
		return false;
	}

	public final static String VoltageFourModuleName = "四路电压传感器";
	public final static String CurrentDoubleModuleName = "二路电流传感器";
	public final static String PressModuleName = "压力传感器";
	public final static String MagneticModuleName = "磁传感器";
	public final static String LightModuleName = "光敏传感器";
	public final static String CarbonMonoxideModuleName = "一氧化碳传感器";
	public final static String MethaneModuleName = "甲烷传感器";
	public final static String SmokeModuleName = "烟雾传感器";
	public final static String FormaldehydeModuleName = "甲醛传感器";
	public final static String AlcoholModuleName = "酒精传感器";
	public final static String NaturalGasModuleName = "天然气传感器";
	public final static String AmmoniaGasModuleName = "氨气传感器";
	public final static String ButaneModuleName = "丁烷传感器";
	public final static String OzoneModuleModuleName = "臭氧传感器";
	public final static String OrganicSolventModuleName = "有机溶剂传感器";
	public final static String FreonModuleName = "氟利昂传感器";
	public final static String HydrogenSulfideModuleName = "硫化氢传感器";
	public final static String HydrogenModuleName = "氢气传感器";
	public final static String LiquefiedGasModuleName = "液化气传感器";
	public final static String AriPolluteModuleName = "空气污染传感器";
	public final static String CombustibleGasModuleName = "可燃气体传感器";
	public final static String OxygenModuleModuleName = "氧气传感器";
	public final static String CarbonDioxideModuleName = "二氧化碳传感器";
	public final static String TemperatureHumidityModuleName = "温湿度传感器";
	public final static String TriaxialModuleName = "三轴加速度传感器";
	public final static String DADoubleModuleName = "二路DA输出传感器";
	public final static String GPoutFourModuleName = "四路GPOUT传感器";
	public final static String GPinFourModuleName = "四路GPIN传感器";
	public final static String InfraredModuleModuleName = "红外接近开关传感器";
	public final static String MegneticApproachModuleName = "磁接近开关传感器";
	public final static String MetalApproachModuleName = "金属接近开关传感器";
	public final static String IRControlModuleName = "IR控制传感器";
	public final static String TemperatureHumidityHightModuleName = "温湿度光照传感器";

	// public final static String VhpModuleName = "氧化氢传感器";
	public final static String ValtageOutputModuleName = "电压输出控制器";
	public final static String GpioModuleName = "GPIO模块";
}
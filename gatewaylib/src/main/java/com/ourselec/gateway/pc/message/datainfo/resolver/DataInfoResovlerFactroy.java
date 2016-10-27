package com.ourselec.gateway.pc.message.datainfo.resolver;

import com.ourselec.gateway.message.MessageResolver;
import com.ourselec.gateway.pc.message.DataMessageInfo;

public class DataInfoResovlerFactroy {
	public static MessageResolver getDataInfoResolver(int deviceId,
			byte[] buffer, int length) {
		if (DataMessageInfo.isSmaple(deviceId)) {
//			if (deviceId == DataMessageInfo.TriaxialModuleCode)
//				return new TriaxialSampleDataInfoResovler(buffer, length);
//			else
				return new SampleDataInfoResovler(buffer, length);
		} else {
			switch (deviceId) {
			case DataMessageInfo.InfraredModuleCode:
			case DataMessageInfo.MegneticApproachModuleCode:
			case DataMessageInfo.MetalApproachModuleCode:
			case DataMessageInfo.GPinFourModuleCode:
				return new SwitchDataInfoResolver(buffer, length);
			}
		}
		// switch (deviceId) {
		// case DataMessageInfo.VoltageFourModuleCode:
		// case DataMessageInfo.CurrentDoubleModuleCode:
		// case DataMessageInfo.PressModuleCode:
		// case DataMessageInfo.MagneticModuleCode:
		// case DataMessageInfo.LightModuleCode:
		// case DataMessageInfo.TemperatureHumidityModuleCode:
		// case DataMessageInfo.TemperatureHumidityHightModuleCode:
		// case DataMessageInfo.HydrogenSulfideModuleCode:
		// return new SampleDataInfoResovler(buffer, length);
		// // case DataMessageInfo.ValtageOutputModuleCode:
		// // case DataMessageInfo.GpioModuleCode:
		// // return new SwitchDataInfoResolver(buffer, length);
		// }
		return null;
	}
}

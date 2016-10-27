package com.ourselec.gateway.pc.message.tedstlv.send;

import com.ourselec.gateway.pc.message.tedstlv.TedsRspInfoType;

public class ValueSendFactroy {
	public static ValueSend getValueSend(int type, int length, Object value) {
		switch (type) {
		case TedsRspInfoType.SensorVersionNumber:
			return new SensorVersionNumberSend(length, value);
		case TedsRspInfoType.SensorDeviceId:
			return new SensorDeviceIdSend(length, value);
		case TedsRspInfoType.SensorManufacInfo:
			return new SensorManufacInfoSend(length, value);
		case TedsRspInfoType.SensorProductNum:
			return new SensorProductNumSend(length, value);
		case TedsRspInfoType.SensorChannelNo:
			return new SensorChannelNoSend(length, value);
		case TedsRspInfoType.SensorPhysicalUnit:
			return new SensorPhysicalUnitSend(length, value);
		case TedsRspInfoType.SensorMeasureRange:
			return new SensorMeasureRangeSend(length, value);
		case TedsRspInfoType.SensorSensitivity:
			return new SensorSensitivitySend(length, value);
		case TedsRspInfoType.SensorOutputRange:
			return new SensorOutputRangeSend(length, value);
		case TedsRspInfoType.TranFunc:
			return new SensorTranFuncSend(length, value);
		case TedsRspInfoType.SensorBaseInfo:
			return new SensorBaseInfoSend(length, value);
		case TedsRspInfoType.SensorRelateWithChannel:
			return new SensorRelateChannelInfoSend(length, value);
		}
		return null;
	}
}

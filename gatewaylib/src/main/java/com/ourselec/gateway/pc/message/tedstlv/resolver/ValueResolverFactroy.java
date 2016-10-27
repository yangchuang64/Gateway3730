package com.ourselec.gateway.pc.message.tedstlv.resolver;

import com.ourselec.gateway.message.MessageResolver;
import com.ourselec.gateway.pc.message.tedstlv.TedsRspInfoType;

public class ValueResolverFactroy {
	public static MessageResolver getValueResolver(int type, byte[] buffer,
			int length) {
		switch (type) {
		case TedsRspInfoType.SensorVersionNumber:
			return new SensorVersionNumberResolver(buffer, length);
		case TedsRspInfoType.SensorDeviceId:
			return new SensorDeviceIdResolver(buffer, length);
		case TedsRspInfoType.SensorManufacInfo:
			return new SensorManufacInfoResolver(buffer, length);
		case TedsRspInfoType.SensorProductNum:
			return new SensorProductNumResolver(buffer, length);
		case TedsRspInfoType.SensorChannelNo:
			return new SensorChannelNoResolver(buffer, length);
		case TedsRspInfoType.SensorPhysicalUnit:
			return new SensorPhysicalUnitResolver(buffer, length);
		case TedsRspInfoType.SensorMeasureRange:
			return new SensorManufacInfoResolver(buffer, length);
		case TedsRspInfoType.SensorSensitivity:
			return new SensorSensitivityResolver(buffer, length);
		case TedsRspInfoType.SensorOutputRange:
			return new OutputRangeResolver(buffer, length);
		case TedsRspInfoType.TranFunc:
			return new SensorTranFuncResolver(buffer, length);
		case TedsRspInfoType.SensorBaseInfo:
			return new SensorBaseInfoResolver(buffer, length);
		case TedsRspInfoType.SensorRelateWithChannel:
			return new SensorRelateChannelInfoResovler(buffer, length);
		}
		return null;
	}
}

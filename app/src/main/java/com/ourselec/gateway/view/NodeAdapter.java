package com.ourselec.gateway.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ourselec.gateway.R;
import com.ourselec.gateway.R.drawable;
import com.ourselec.gateway.node.Node;
import com.ourselec.gateway.node.NodeManager;
import com.ourselec.gateway.pc.message.DataMessageInfo;

/**
 * 节点适配器
 * 
 * @author yangtianfei(ytf2737179@163.com)
 * 
 */
public class NodeAdapter extends BaseAdapter {

	Context context;
	List<Integer> nodes;

	public NodeAdapter(Context context) {
		this.context = context;
		nodes = new ArrayList<Integer>();
	}

	public void addItems(Integer item) {
		nodes.add(item);
		notifyDataSetChanged();
	}

	public void remove(Integer noteAddress) {
		nodes.remove(noteAddress);
		notifyDataSetChanged();
	}

	public void clear() {
		nodes.clear();
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return nodes.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return nodes.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		NodeViewHodler viewHodler;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.node,
					null);
			viewHodler = new NodeViewHodler();
			viewHodler.imagIv = (ImageView) convertView
					.findViewById(R.id.node_image);
			viewHodler.lableTx = (TextView) convertView
					.findViewById(R.id.node_lable);
			convertView.setTag(viewHodler);
		} else
			viewHodler = (NodeViewHodler) convertView.getTag();
		Node node = NodeManager.getInstance().getNode(nodes.get(position));
		viewHodler.imagIv.setImageResource(getNodeIcon(node.getDeviceId()
				.getDeviceId()));
		viewHodler.lableTx.setText(node.getLable());

		return convertView;
	}

	private int getNodeIcon(int deviceId) {
		switch (deviceId) {
		case DataMessageInfo.VoltageFourModuleCode:// 四路电压采集
			return R.drawable.voltage;
		case DataMessageInfo.CurrentDoubleModuleCode:// 2路电流采集
			return R.drawable.current_double;
		case DataMessageInfo.PressModuleCode:// 压力
			return R.drawable.press;
		case DataMessageInfo.MagneticModuleCode:// 磁通道
			return R.drawable.magnetic;
		case DataMessageInfo.LightModuleCode:// 光照
			return R.drawable.light;
		case DataMessageInfo.CarbonMonoxideModuleCode:// 一氧化碳
			return R.drawable.carbon_monoxide;
		case DataMessageInfo.MethaneModuleCode:// 甲烷
			return R.drawable.methane;
		case DataMessageInfo.SmokeModuleCode:// 烟雾
			return R.drawable.smoke;
		case DataMessageInfo.FormaldehydeModuleCode:// 甲醛
			return R.drawable.formalde_hyde;
		case DataMessageInfo.AlcoholModuleCode:// 酒精
			return R.drawable.alcohol;
		case DataMessageInfo.NaturalGasModuleCode:// 天然气
			return R.drawable.natural_gas;
		case DataMessageInfo.AmmoniaGasModuleCode:// 氨气
			return R.drawable.gas;
		case DataMessageInfo.ButaneModuleCode:// 丁烷
			return R.drawable.gas;
		case DataMessageInfo.OzoneModuleCode:// 臭氧
			return R.drawable.gas;
		case DataMessageInfo.OrganicSolventModuleCode:// 有机溶剂
			return R.drawable.organic_solvent;
		case DataMessageInfo.FreonModuleCode:// 氟利昂
			return R.drawable.gas;
		case DataMessageInfo.HydrogenSulfideModuleCode:// 硫化氢
			return R.drawable.gas;
		case DataMessageInfo.HydrogenModuleCode:// 氢气
			return R.drawable.gas;
		case DataMessageInfo.LiquefiedGasModuleCode:// 液化气
			return R.drawable.gas;
		case DataMessageInfo.AirPolluteModuleCode:// 空气污染
			return R.drawable.gas;
		case DataMessageInfo.CombustibleGasModuleCode:// 可燃气体
			return R.drawable.gas;
		case DataMessageInfo.OxygenModuleCode:// 氧气
			return R.drawable.gas;
		case DataMessageInfo.CarbonDioxideModuleCode:// 二氧化碳
			return R.drawable.gas;
		case DataMessageInfo.TemperatureHumidityModuleCode:// 温湿度
			return R.drawable.temperature_humidity;
		case DataMessageInfo.TriaxialModuleCode:// 三轴加速度
			return R.drawable.triaxial_acceleration;
		case DataMessageInfo.DADoubleModuleCode:// 2路DA输出
			return R.drawable.da_output;
		case DataMessageInfo.GPoutFourModuleCode:// 四路GPOUT
			return R.drawable.gpio_output;
		case DataMessageInfo.GPinFourModuleCode:// 四路GPIN
			return R.drawable.gpio_output;
		case DataMessageInfo.InfraredModuleCode:// 红外接近开关
			return R.drawable.infrared_switch;
		case DataMessageInfo.MegneticApproachModuleCode:// 磁接近开关
			return R.drawable.megnetic_close;
		case DataMessageInfo.MetalApproachModuleCode:// 金属接近开关
			return R.drawable.close_sensor;
		case DataMessageInfo.IRControlModuleCode:// IR控制
			return R.drawable.ir;
		case DataMessageInfo.TemperatureHumidityHightModuleCode:// 温湿度加光照
			return R.drawable.temperature_humidity;
		default:
			return R.drawable.sensor;
		}

	}

	class NodeViewHodler {
		ImageView imagIv;
		TextView lableTx;
	}
}

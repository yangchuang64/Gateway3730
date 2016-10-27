package com.ourselec.gateway.view;

import java.util.Date;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ourselec.gateway.R;
import com.ourselec.gateway.node.Node;
import com.ourselec.gateway.pc.message.DataMessageInfo;
import com.ourselec.gateway.pc.message.datainfo.ChannelModule;
import com.ourselec.gateway.pc.message.datainfo.SampleDataInfo;
import com.ourselec.gateway.pc.message.datainfo.SwitchDataInfo;
import com.ourselec.gateway.util.Utile;

/**
 * 采样控件类
 * 
 * @author yangtianfei(ytf2737179@163.com)
 * 
 */
public class SensorSampleDisView implements OnClickListener {
	private final String TAG = "SensorSampleDisView";
	Context context;
	LinearLayout sampleLt;
	View sampleDisplayView, closeDisplayView, gpinView;
	LinearLayout sampleExactLt;
	// LinearLayout sampleChartLt, sampleExactLt, closeLt;
	GraphicalView mChartView;
	Node node;

	/**
	 * The main dataset that includes all the series that go into a chart.
	 */
	protected XYMultipleSeriesDataset mDataset;
	/**
	 * The main renderer that includes all the renderers customizing a chart.
	 */
	protected XYMultipleSeriesRenderer mRenderer;
	/** The most recently added series. */
	// protected TimeSeries mCurrentSeries;
	protected TimeSeries[] mCurrentSeries;
	/**
	 * The most recently created renderer, customizing the current series.
	 */
	// protected XYSeriesRenderer mCurrentRenderer;
	protected final int length = 10;

	// private String seriesName;
	// private int yAxisMin, yAxisMax;
	int currentPos;

	public SensorSampleDisView(Context context, LinearLayout lt) {
		this.context = context;
		this.sampleLt = lt;
		// sampleChartLt = (LinearLayout) lt
		// .findViewById(R.id.sensor_sample_chart_lt);
		// sampleExactLt = (LinearLayout) lt
		// .findViewById(R.id.sensor_sample_exact_lt);
	}

	public void setNode(Node node) {
		this.node = node;
		if (node == null) {
			sampleLt.removeAllViews();
		} else {
			this.currentPos = 0;
			updateSampleLayout();
		}
	}

	public void updateSampleLayout() {
		LayoutInflater layoutInflater = LayoutInflater.from(context);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		int deviceId = node.getDeviceId().getDeviceId();
		if (DataMessageInfo.isSmaple(deviceId)) {
			if (sampleLt.findViewById(R.id.sensor_sample_lt) == null) {
				sampleLt.removeAllViews();
				if (sampleDisplayView == null)
					sampleDisplayView = layoutInflater.inflate(
							R.layout.sensor_sample_display, null);
				if (sampleExactLt == null)
					sampleExactLt = (LinearLayout) sampleDisplayView
							.findViewById(R.id.sensor_sample_exact_lt);
				// LinearLayout sampleExactLt = (LinearLayout) sampleLt
				// .findViewById(R.id.sensor_sample_exact_lt);
				sampleLt.addView(sampleDisplayView, params);
			}
			sampleExactLt.removeAllViews();
			switch (deviceId) {
			case DataMessageInfo.VoltageFourModuleCode:// 四路电压采集
				updateChartView(new String[] { "V1", "V2", "V3", "V4" }, 0, 0,
						0, 36, "Time", "Voltage", new int[] { Color.BLUE,
								Color.GREEN, Color.RED, Color.BLACK },
						new PointStyle[] { PointStyle.CIRCLE,
								PointStyle.TRIANGLE, PointStyle.DIAMOND,
								PointStyle.SQUARE });
				// updataVoltageFourLayout(new LinearLayout.LayoutParams(
				// LinearLayout.LayoutParams.WRAP_CONTENT,
				// LinearLayout.LayoutParams.WRAP_CONTENT));
				break;
			case DataMessageInfo.CurrentDoubleModuleCode:// 2路电流采集
				updateChartView(new String[] { "Current1", "Current2" }, 0, 0,
						-2, 10, "Time", "Current 1", new int[] { Color.BLUE,
								Color.GREEN }, new PointStyle[] {
								PointStyle.CIRCLE, PointStyle.TRIANGLE });
				// updateCurrentDoubleLayout(new LinearLayout.LayoutParams(
				// LinearLayout.LayoutParams.WRAP_CONTENT,
				// LinearLayout.LayoutParams.WRAP_CONTENT));
				break;
			case DataMessageInfo.PressModuleCode:// 压力
				updateChartView(new String[] { "Press" }, 0, 0, 0, 300, "Time",
						"Press", new int[] { Color.BLUE },
						new PointStyle[] { PointStyle.CIRCLE });
				break;
			case DataMessageInfo.MagneticModuleCode:// 磁通道
				updateChartView(new String[] { "Magnetic" }, 0, 0, 0, 300,
						"Time", "Magnetic", new int[] { Color.BLUE },
						new PointStyle[] { PointStyle.CIRCLE });
				break;
			case DataMessageInfo.LightModuleCode:// 光照
				updateChartView(new String[] { "Light" }, 0, 0, 0, 300, "Time",
						"Light", new int[] { Color.BLUE },
						new PointStyle[] { PointStyle.CIRCLE });
				break;
			case DataMessageInfo.CarbonMonoxideModuleCode:// 一氧化碳
				updateChartView(new String[] { "CarbonMonoxide" }, 0, 0, 0, 10,
						"Time", "Carbon Monoxide", new int[] { Color.BLUE },
						new PointStyle[] { PointStyle.CIRCLE });
				break;
			case DataMessageInfo.MethaneModuleCode:// 甲烷
				updateChartView(new String[] { "Methane" }, 0, 0, 0, 10,
						"Time", "Methane", new int[] { Color.BLUE },
						new PointStyle[] { PointStyle.CIRCLE });
				break;
			case DataMessageInfo.SmokeModuleCode:// 烟雾
				updateChartView(new String[] { "Smoke" }, 0, 0, 0, 10, "Time",
						"Smoke", new int[] { Color.BLUE },
						new PointStyle[] { PointStyle.CIRCLE });
				break;
			case DataMessageInfo.FormaldehydeModuleCode:// 甲醛
				updateChartView(new String[] { "FormaldeHyde" }, 0, 0, 0, 10,
						"Time", "Formaldehyde", new int[] { Color.BLUE },
						new PointStyle[] { PointStyle.CIRCLE });
				break;
			case DataMessageInfo.AlcoholModuleCode:// 酒精
				updateChartView(new String[] { "Alcohol" }, 0, 0, 0, 10,
						"Time", "Alcohol", new int[] { Color.BLUE },
						new PointStyle[] { PointStyle.CIRCLE });
				break;
			case DataMessageInfo.NaturalGasModuleCode:// 天然气
				updateChartView(new String[] { "NaturalGas" }, 0, 0, 0, 300,
						"Time", "Natural gas", new int[] { Color.BLUE },
						new PointStyle[] { PointStyle.CIRCLE });
				break;
			case DataMessageInfo.AmmoniaGasModuleCode:// 氨气
				updateChartView(new String[] { "AmmoniaGas" }, 0, 0, 0, 10,
						"Time", "Ammonia gas", new int[] { Color.BLUE },
						new PointStyle[] { PointStyle.CIRCLE });
				break;
			case DataMessageInfo.ButaneModuleCode:// 丁烷
				updateChartView(new String[] { "Butane" }, 0, 0, 0, 10, "Time",
						"Butane", new int[] { Color.BLUE },
						new PointStyle[] { PointStyle.CIRCLE });
				break;
			case DataMessageInfo.OzoneModuleCode:// 臭氧
				updateChartView(new String[] { "Ozone" }, 0, 0, 0, 10, "Time",
						"Ozone", new int[] { Color.BLUE },
						new PointStyle[] { PointStyle.CIRCLE });
				break;
			case DataMessageInfo.OrganicSolventModuleCode:// 有机溶剂
				updateChartView(new String[] { "OrganicSolvent" }, 0, 0, 0,
						300, "Time", "Organic Solvent",
						new int[] { Color.BLUE },
						new PointStyle[] { PointStyle.CIRCLE });
				break;
			case DataMessageInfo.FreonModuleCode:// 氟利昂
				updateChartView(new String[] { "Freon" }, 0, 0, 0, 10, "time",
						"light", new int[] { Color.BLUE },
						new PointStyle[] { PointStyle.CIRCLE });
				break;
			case DataMessageInfo.HydrogenSulfideModuleCode:// 硫化氢
				updateChartView(new String[] { "HydrogenSulfide" }, 0, 0, 0,
						10, "Time", "Hydrogen Sulfide",
						new int[] { Color.BLUE },
						new PointStyle[] { PointStyle.CIRCLE });
				break;
			case DataMessageInfo.HydrogenModuleCode:// 氢气
				updateChartView(new String[] { "Hydrogen" }, 0, 0, 0, 10,
						"Time", "Hydrogen", new int[] { Color.BLUE },
						new PointStyle[] { PointStyle.CIRCLE });
				break;
			case DataMessageInfo.LiquefiedGasModuleCode:// 液化气
				updateChartView(new String[] { "LiquefiedGas" }, 0, 0, 0, 10,
						"time", "light", new int[] { Color.BLUE },
						new PointStyle[] { PointStyle.CIRCLE });
				break;
			case DataMessageInfo.AirPolluteModuleCode:// 空气污染
				updateChartView(new String[] { "AirPollute" }, 0, 0, 0, 10,
						"Time", "Air Pollute", new int[] { Color.BLUE },
						new PointStyle[] { PointStyle.CIRCLE });
				break;
			case DataMessageInfo.CombustibleGasModuleCode:// 可燃气体
				updateChartView(new String[] { "CombustibleGas" }, 0, 0, 0, 10,
						"Time", "Combustible Gas", new int[] { Color.BLUE },
						new PointStyle[] { PointStyle.CIRCLE });
				break;
			case DataMessageInfo.OxygenModuleCode:// 氧气
				updateChartView(new String[] { "Oxygen" }, 0, 0, 0, 300,
						"Time", "Oxygen", new int[] { Color.BLUE },
						new PointStyle[] { PointStyle.CIRCLE });
				break;
			case DataMessageInfo.CarbonDioxideModuleCode:// 二氧化碳
				updateChartView(new String[] { "CarbonDioxide" }, 0, 0, 0, 10,
						"Time", "Carbon Dioxide", new int[] { Color.BLUE },
						new PointStyle[] { PointStyle.CIRCLE });
				break;
			case DataMessageInfo.TemperatureHumidityModuleCode:// 温湿度
				updateChartView(new String[] { "Temperature" }, 0, 0, 0, 100,
						"Time", "Temperature", new int[] { Color.BLUE },
						new PointStyle[] { PointStyle.CIRCLE });
				updateTemHumLayout(new LinearLayout.LayoutParams(
						LinearLayout.LayoutParams.WRAP_CONTENT,
						LinearLayout.LayoutParams.WRAP_CONTENT));
				break;
			case DataMessageInfo.TriaxialModuleCode:// 三轴加速度
				updateChartView(new String[] { "X", "Y", "Z" }, 0, 0, -2, 2,
						"Time", "Triaxial", new int[] { Color.BLUE,
								Color.GREEN, Color.RED }, new PointStyle[] {
								PointStyle.CIRCLE, PointStyle.TRIANGLE,
								PointStyle.DIAMOND });
				// updateTriaxialLayout(new LinearLayout.LayoutParams(
				// LinearLayout.LayoutParams.WRAP_CONTENT,
				// LinearLayout.LayoutParams.WRAP_CONTENT));
				break;
			case DataMessageInfo.TemperatureHumidityHightModuleCode:// 温湿度加光照
				updateChartView(new String[] { "Temperature" }, 0, 0, 0, 100,
						"Time", "Temperature", new int[] { Color.BLUE },
						new PointStyle[] { PointStyle.CIRCLE });
				updateTemHumLigLayout(new LinearLayout.LayoutParams(
						LinearLayout.LayoutParams.WRAP_CONTENT,
						LinearLayout.LayoutParams.WRAP_CONTENT));
				break;
			}
			// sampleLt.setVisibility(View.VISIBLE);
		} else {
			switch (deviceId) {
			case DataMessageInfo.InfraredModuleCode:
			case DataMessageInfo.MegneticApproachModuleCode:
			case DataMessageInfo.MetalApproachModuleCode:
				if (sampleLt.findViewById(R.id.sensor_close_lt) == null) {
					sampleLt.removeAllViews();
					// LinearLayout sampleExactLt = (LinearLayout) sampleLt
					// .findViewById(R.id.sensor_sample_exact_lt);
					if (closeDisplayView == null)
						closeDisplayView = layoutInflater.inflate(
								R.layout.sensor_close_display, null);
					sampleLt.addView(closeDisplayView, params);
				}
				break;
			case DataMessageInfo.GPinFourModuleCode:
				if (sampleLt.findViewById(R.id.sensor_output_gp_lt) == null) {
					sampleLt.removeAllViews();
					if (gpinView == null)
						gpinView = layoutInflater.inflate(
								R.layout.sensor_gp_in, null);
					sampleLt.addView(gpinView, params);
				}
				break;
			case DataMessageInfo.IRControlModuleCode:
				if (sampleLt.findViewById(R.id.sensor_output_ir_lt) == null) {
					// sampleLt.removeAllViews();
				}
				break;
			default:
				sampleLt.removeAllViews();
				// sampleLt.setVisibility(View.INVISIBLE);
			}
		}
	}

	public void updateChartView(String[] title, int xMin, int xMax, int yMin,
			int yMax, String xTitle, String yTitle, int[] colors,
			PointStyle[] styles) {
		updateSeries(title);
		updateRenderer(xMin, xMax, yMin, yMax, xTitle, yTitle, colors, styles);
		if (mChartView == null) {
			// mChartView = ChartFactory.getTimeChartView(context,
			// getDateDemoDataset(title),
			// getDemoRenderer(xMin, xMax, yMin, yMax, xTitle, yTitle),
			// "hh:mm:ss");
			mChartView = ChartFactory.getTimeChartView(context, mDataset,
					mRenderer, "hh:mm:ss");
			mChartView.setId(R.id.sensor_sample_chart_view);
			((LinearLayout) sampleDisplayView
					.findViewById(R.id.sensor_sample_chart_lt)).addView(
					mChartView, new LinearLayout.LayoutParams(
							LinearLayout.LayoutParams.FILL_PARENT,
							LinearLayout.LayoutParams.FILL_PARENT));
		} else {
			// updateSeries(title);
			// updateRenderer(xMin, xMax, yMin, yMax, xTitle, yTitle);
			mChartView.repaint();
		}
	}

	private void updateSeries(String[] titles) {
		if (mDataset == null) {
			mDataset = new XYMultipleSeriesDataset();
			// mCurrentSeries = new TimeSeries(title);
			// series.addAnnotation("Pressure", 6, 28);
			// mDataset.addSeries(mCurrentSeries);
		} else {
			// mCurrentSeries.setTitle(title);
			// mCurrentSeries.clear();
		}
		mDataset.clear();
		mCurrentSeries = new TimeSeries[titles.length];
		for (int i = 0; i < titles.length; i++) {
			mCurrentSeries[i] = new TimeSeries(titles[i]);
			mDataset.addSeries(mCurrentSeries[i]);
		}
	}

	private void updateRenderer(int xMin, int xMax, int yMin, int yMax,
			String xTitle, String yTitle, int[] colors, PointStyle[] styles) {
		if (mRenderer == null) {
			mRenderer = new XYMultipleSeriesRenderer();
			mRenderer.setChartTitleTextSize(16);
			// mRenderer.setChartTitle("Press Sample");
			mRenderer.setAxisTitleTextSize(16);
			// mRenderer.setXTitle(xTitle);
			// mRenderer.setYTitle(yTitle);
			mRenderer.setLabelsColor(Color.BLACK);
			mRenderer.setAxesColor(Color.BLACK);
			mRenderer.setLabelsTextSize(15);
			// renderers.setXLabelsAlign(Align.RIGHT);
			// renderers.setYLabelsAlign(Align.RIGHT);
			mRenderer.setXLabelsColor(Color.BLACK);
			mRenderer.setYLabelsColor(0, Color.BLACK);
			// mRenderer.setYAxisMin(yMin);
			// mRenderer.setYAxisMax(yMax);
			mRenderer.setLegendTextSize(15);
			mRenderer.setMargins(new int[] { 20, 30, 15, 10 });
			mRenderer.setShowLegend(true);
			mRenderer.setPointSize(5f);
			mRenderer.setMargins(new int[] { 15, 30, 0, 0 });
			mRenderer.setMarginsColor(Color.WHITE);
			mRenderer.setShowGrid(true);
			mRenderer.setZoomButtonsVisible(false);
			// renderers.setPanLimits(new double[] { -10, 20, -10, 40 });
			// renderers.setZoomLimits(new double[] { -10, 20, -10, 40 });
			mRenderer.setPanEnabled(false, false);
			mRenderer.setInScroll(true);

			// mCurrentRenderer = new XYSeriesRenderer();
			// // set some renderer properties
			// mCurrentRenderer.setColor(Color.GREEN);
			// mCurrentRenderer.setPointStyle(PointStyle.CIRCLE);
			// mCurrentRenderer.setFillPoints(true);
			// // currentRenderer.setDisplayChartValues(true);
			// // currentRenderer.setDisplayChartValuesDistance(10);
			// mRenderer.addSeriesRenderer(mCurrentRenderer);
		}
		mRenderer.removeAllRenderers();
		for (int i = 0; i < colors.length; i++) {
			XYSeriesRenderer r = new XYSeriesRenderer();
			r.setColor(colors[i]);
			r.setPointStyle(styles[i]);
			r.setFillPoints(true);
			mRenderer.addSeriesRenderer(r);
		}
		mRenderer.setXTitle(xTitle);
		mRenderer.setYTitle(yTitle);
		mRenderer.setYAxisMin(yMin);
		mRenderer.setYAxisMax(yMax);
	}

	public void updateSample(Object dataInfo) {
		if (node == null)
			return;
		int deviceId = node.getDeviceId().getDeviceId();
		if (DataMessageInfo.isSmaple(deviceId)) {
			if (dataInfo == null || mChartView == null)
				return;
			// mCurrentSeries.add(new Date(),
			// vdata[node_collect_spinner_select]);
			if (mCurrentSeries[0].getItemCount() > length) {
				for (int i = 0; i < mCurrentSeries.length; i++) {
					mCurrentSeries[i].remove(0);
				}
			}
			// int len = length - 1 > mCurrentSeries.getItemCount() ?
			// mCurrentSeries
			// .getItemCount() : length - 1;
			// double[] ycache = new double[len];
			// for (int i = 0; i < len; i++) {
			// ycache[i] = mCurrentSeries.getY(i);
			// }
			// mCurrentSeries.clear();

			ChannelModule[] channelModules = ((SampleDataInfo) dataInfo)
					.getChannelModules();
			// if (node.getDeviceId().getDeviceId() ==
			// DataMessageInfo.TriaxialModuleCode) {
			// ChannelModule channelModule = channelModules[0];
			// byte[] data = channelModule.getData();
			// if (data.length == channelModule.getDataCount() * 12) {
			// float[] dataf = new float[data.length / 4];
			// for (int i = 0; i < dataf.length; i++) {
			// byte[] temp = new byte[4];
			// System.arraycopy(data, i * 4, temp, 0, temp.length);
			// dataf[i] = Utile.Byte2Float(temp);
			// }
			// // Log.i(TAG, String.valueOf(dataf[currentPos]));
			// // mCurrentSeries.add(new Date(), dataf[currentPos]);
			// for (int i = 0; i < mCurrentSeries.length; i++) {
			// mCurrentSeries[i].add(new Date(), dataf[i]);
			// }
			// }
			// } else
			if (node.getDeviceId().getDeviceId() == DataMessageInfo.TemperatureHumidityModuleCode
					|| node.getDeviceId().getDeviceId() == DataMessageInfo.TemperatureHumidityHightModuleCode) {
				if (currentPos >= 0 && currentPos < channelModules.length) {
					ChannelModule channelModule = channelModules[currentPos];
					byte[] data = channelModule.getData();
					if (data.length == channelModule.getDataCount() * 4) {
						float[] dataf = new float[channelModule.getDataCount()];
						for (int i = 0; i < channelModule.getDataCount(); i++) {
							byte[] temp = new byte[4];
							System.arraycopy(data, i * 4, temp, 0, temp.length);
							dataf[i] = Utile.Byte2Float(temp);
						}
						Log.i(TAG, String.valueOf(dataf[0]));
						mCurrentSeries[0].add(new Date(), dataf[0]);
					}
				}
			} else {
				for (int i = 0; i < mCurrentSeries.length; i++) {
					ChannelModule channelModule = channelModules[i];
					byte[] data = channelModule.getData();
					if (data.length == channelModule.getDataCount() * 4) {
						float[] dataf = new float[channelModule.getDataCount()];
						for (int k = 0; k < channelModule.getDataCount(); k++) {
							byte[] temp = new byte[4];
							System.arraycopy(data, k * 4, temp, 0, temp.length);
							dataf[k] = Utile.Byte2Float(temp);
						}
						Log.i(TAG, String.valueOf(dataf[0]));

						if (DataMessageInfo.isGasSensor(deviceId))
							mCurrentSeries[i].add(new Date(), 5 - dataf[i]);
						else if (deviceId == DataMessageInfo.CurrentDoubleModuleCode)
							mCurrentSeries[i].add(new Date(),
									(1 + dataf[0]) * 5);
						else
							mCurrentSeries[i].add(new Date(), dataf[0]);
					}
				}
			}
			// for (int k = 0; k < len; k++) {
			// mCurrentSeries.add(new Date(), ycache[k]);
			// }
			mChartView.repaint();
		} else {
			switch (deviceId) {
			case DataMessageInfo.InfraredModuleCode:
			case DataMessageInfo.MegneticApproachModuleCode:
			case DataMessageInfo.MetalApproachModuleCode:
				SwitchDataInfo switchData = (SwitchDataInfo) dataInfo;
				ImageView closeIv = (ImageView) closeDisplayView
						.findViewById(R.id.sensor_close_iv);
				if (switchData.getInputData() == 0x00) {
					closeIv.setImageResource(R.drawable.call_on);
				} else {
					closeIv.setImageResource(R.drawable.call_off);
				}
				break;
			case DataMessageInfo.GPinFourModuleCode:
				int gpInSate = ((SwitchDataInfo) dataInfo).getInputData();
				if ((gpInSate & 0x01) == 0x00) {
					((Button) gpinView.findViewById(R.id.sensor_gpin1))
							.setBackgroundResource(R.drawable.gp_in_on);
					((Button) gpinView.findViewById(R.id.sensor_gpin1))
							.setText(R.string.sensor_gpin1_on);
				} else {
					((Button) gpinView.findViewById(R.id.sensor_gpin1))
							.setBackgroundResource(R.drawable.gp_in_off);
					((Button) gpinView.findViewById(R.id.sensor_gpin1))
							.setText(R.string.sensor_gpin1_off);
				}
				if ((gpInSate & 0x02) == 0x00) {
					((Button) gpinView.findViewById(R.id.sensor_gpin2))
							.setBackgroundResource(R.drawable.gp_in_on);
					((Button) gpinView.findViewById(R.id.sensor_gpin2))
							.setText(R.string.sensor_gpin2_on);
				} else {
					((Button) gpinView.findViewById(R.id.sensor_gpin2))
							.setBackgroundResource(R.drawable.gp_in_off);
					((Button) gpinView.findViewById(R.id.sensor_gpin2))
							.setText(R.string.sensor_gpin2_off);
				}
				if ((gpInSate & 0x04) == 0x00) {
					((Button) gpinView.findViewById(R.id.sensor_gpin3))
							.setBackgroundResource(R.drawable.gp_in_on);
					((Button) gpinView.findViewById(R.id.sensor_gpin3))
							.setText(R.string.sensor_gpin3_on);
				} else {
					((Button) gpinView.findViewById(R.id.sensor_gpin3))
							.setBackgroundResource(R.drawable.gp_in_off);
					((Button) gpinView.findViewById(R.id.sensor_gpin3))
							.setText(R.string.sensor_gpin3_off);
				}
				if ((gpInSate & 0x08) == 0x00) {
					((Button) gpinView.findViewById(R.id.sensor_gpin4))
							.setBackgroundResource(R.drawable.gp_in_on);
					((Button) gpinView.findViewById(R.id.sensor_gpin4))
							.setText(R.string.sensor_gpin4_on);
				} else {
					((Button) gpinView.findViewById(R.id.sensor_gpin4))
							.setBackgroundResource(R.drawable.gp_in_off);
					((Button) gpinView.findViewById(R.id.sensor_gpin4))
							.setText(R.string.sensor_gpin4_off);
				}
				break;
			}
		}
	}

	private void updateTemHumLayout(LinearLayout.LayoutParams params) {
		Button temBt = new Button(context);
		temBt.setId(R.id.sensor_sample_display_temperature_bt);
		temBt.setText(context.getString(R.string.sensor_sample_display_tem));
		temBt.setOnClickListener(this);
		sampleExactLt.addView(temBt, params);

		Button humBt = new Button(context);
		humBt.setId(R.id.sensor_sample_display_humdity_bt);
		humBt.setText(context.getString(R.string.sensor_sample_display_hum));
		humBt.setOnClickListener(this);
		sampleExactLt.addView(humBt, params);
	}

	private void updateTemHumLigLayout(LinearLayout.LayoutParams params) {
		updateTemHumLayout(params);
		Button hightBt = new Button(context);
		hightBt.setId(R.id.sensor_sample_display_hight_bt);
		hightBt.setText(context.getString(R.string.sensor_sample_display_light));
		hightBt.setOnClickListener(this);
		sampleExactLt.addView(hightBt, params);
	}

	private void updateTriaxialLayout(LinearLayout.LayoutParams params) {
		Button xBt = new Button(context);
		xBt.setId(R.id.sensor_sample_display_triaxial_x_bt);
		xBt.setText(context
				.getString(R.string.sensor_sample_display_triaxial_x));
		xBt.setOnClickListener(this);
		sampleExactLt.addView(xBt, params);

		Button yBt = new Button(context);
		yBt.setId(R.id.sensor_sample_display_triaxial_y_bt);
		yBt.setText(context
				.getString(R.string.sensor_sample_display_triaxial_y));
		yBt.setOnClickListener(this);
		sampleExactLt.addView(yBt, params);

		Button zBt = new Button(context);
		zBt.setId(R.id.sensor_sample_display_triaxial_z_bt);
		zBt.setText(context
				.getString(R.string.sensor_sample_display_triaxial_z));
		zBt.setOnClickListener(this);
		sampleExactLt.addView(zBt, params);
	}

	private void updateCurrentDoubleLayout(LinearLayout.LayoutParams params) {
		Button oneBt = new Button(context);
		oneBt.setId(R.id.sensor_sample_display_current_double_1_bt);
		oneBt.setText(context
				.getString(R.string.sensor_sample_display_current_double_1));
		oneBt.setOnClickListener(this);
		sampleExactLt.addView(oneBt, params);

		Button twoBt = new Button(context);
		twoBt.setId(R.id.sensor_sample_display_current_double_2_bt);
		twoBt.setText(context
				.getString(R.string.sensor_sample_display_current_double_2));
		twoBt.setOnClickListener(this);
		sampleExactLt.addView(twoBt, params);
	}

	private void updataVoltageFourLayout(LinearLayout.LayoutParams params) {
		Button oneBt = new Button(context);
		oneBt.setId(R.id.sensor_sample_display_voltage_four_1_bt);
		oneBt.setText(context
				.getString(R.string.sensor_sample_display_voltage_four_1));
		oneBt.setOnClickListener(this);
		sampleExactLt.addView(oneBt, params);

		Button twoBt = new Button(context);
		twoBt.setId(R.id.sensor_sample_display_voltage_four_2_bt);
		twoBt.setText(context
				.getString(R.string.sensor_sample_display_voltage_four_2));
		twoBt.setOnClickListener(this);
		sampleExactLt.addView(twoBt, params);

		Button ThreeBt = new Button(context);
		ThreeBt.setId(R.id.sensor_sample_display_voltage_four_3_bt);
		ThreeBt.setText(context
				.getString(R.string.sensor_sample_display_voltage_four_3));
		ThreeBt.setOnClickListener(this);
		sampleExactLt.addView(ThreeBt, params);

		Button fourBt = new Button(context);
		fourBt.setId(R.id.sensor_sample_display_voltage_four_4_bt);
		fourBt.setText(context
				.getString(R.string.sensor_sample_display_voltage_four_4));
		fourBt.setOnClickListener(this);
		sampleExactLt.addView(fourBt, params);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.sensor_sample_display_temperature_bt:
			if (currentPos == 0)
				return;
			updateChartView(new String[] { "Temperature" }, 0, 0, 0, 100,
					"Time", "Temperatue", new int[] { Color.BLUE },
					new PointStyle[] { PointStyle.CIRCLE });
			currentPos = 0;
			break;
		case R.id.sensor_sample_display_humdity_bt:
			if (currentPos == 1)
				return;
			updateChartView(new String[] { "Humdity" }, 0, 0, 0, 100, "Time",
					"Humdity", new int[] { Color.BLUE },
					new PointStyle[] { PointStyle.CIRCLE });
			currentPos = 1;
			break;
		case R.id.sensor_sample_display_hight_bt:
			if (currentPos == 2)
				return;
			updateChartView(new String[] { "Hight" }, 0, 0, 0, 20000, "Time",
					"Hight", new int[] { Color.BLUE },
					new PointStyle[] { PointStyle.CIRCLE });
			currentPos = 2;
			break;
		// case R.id.sensor_sample_display_triaxial_x_bt:
		// if (currentPos == 0)
		// return;
		// updateChartView(node.getLable(), 0, 0, -2, 2, "Time", "X");
		// currentPos = 0;
		// break;
		// case R.id.sensor_sample_display_triaxial_y_bt:
		// if (currentPos == 1)
		// return;
		// updateChartView(node.getLable(), 0, 0, -2, 2, "Time", "Y");
		// currentPos = 1;
		// break;
		// case R.id.sensor_sample_display_triaxial_z_bt:
		// if (currentPos == 2)
		// return;
		// updateChartView(node.getLable(), 0, 0, -2, 2, "Time", "Z");
		// currentPos = 2;
		// break;
		// case R.id.sensor_sample_display_current_double_1_bt:
		// if (currentPos == 0)
		// return;
		// updateChartView(node.getLable(), 0, 0, -2, 10, "Time", "Current 1");
		// currentPos = 0;
		// break;
		// case R.id.sensor_sample_display_current_double_2_bt:
		// if (currentPos == 1)
		// return;
		// updateChartView(node.getLable(), 0, 0, -2, 10, "Time", "Current 2");
		// currentPos = 1;
		// break;
		// case R.id.sensor_sample_display_voltage_four_1_bt:
		// if (currentPos == 0)
		// return;
		// updateChartView(node.getLable(), 0, 0, 0, 36, "Time", "Voltage 1");
		// currentPos = 0;
		// break;
		// case R.id.sensor_sample_display_voltage_four_2_bt:
		// if (currentPos == 1)
		// return;
		// updateChartView(node.getLable(), 0, 0, 0, 36, "Time", "Voltage 2");
		// currentPos = 1;
		// break;
		// case R.id.sensor_sample_display_voltage_four_3_bt:
		// if (currentPos == 2)
		// return;
		// updateChartView(node.getLable(), 0, 0, 0, 36, "Time", "Voltage 3");
		// currentPos = 2;
		// break;
		// case R.id.sensor_sample_display_voltage_four_4_bt:
		// if (currentPos == 3)
		// return;
		// updateChartView(node.getLable(), 0, 0, 0, 36, "Time", "Voltage 4");
		// currentPos = 3;
		// break;
		}
	}
}

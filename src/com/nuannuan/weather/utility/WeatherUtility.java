package com.nuannuan.weather.utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.TimeZone;

import org.json.JSONArray;
import org.json.JSONException;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import com.nuannuan.common.R;
import com.nuannuan.weather.interfaces.WeatherConditionIm;
import com.nuannuan.weather.utility.SinaWeatherHelper.HelpListener;

public class WeatherUtility {
	private Context mContext;
	private WeatherConditionIm event;
	private ImageView mProgressBar;
	private Animation operatingAnim;

	public WeatherUtility(Context context, WeatherConditionIm event,
			ImageView mProgressBar) {
		mContext = context;
		this.event = event;
		this.mProgressBar = mProgressBar;
		onCreate();

		operatingAnim = AnimationUtils.loadAnimation(context,
				R.anim.rotation_refresh);
		LinearInterpolator lin = new LinearInterpolator();
		operatingAnim.setInterpolator(lin);

	}

	private final static int HDL_SET_VIEW = 0;
	private final static int HDL_START_PROGRESS = 1;
	private final static int HDL_STOP_PROGRESS = 2;
	private final static int HDL_SHOW_MSG = 3;

	private final static String KEY_LOCAL = "local";
	private final static String KEY_CITIES = "cities";

	private SharedPreferences sf;
	// private ProgressDialog pDlg;
	private static SinaWeatherHelper helper;
	public static LinkedHashMap<String, WeatherParser> map;
	private LinkedList<String> cityNames;
	private String localCityName;
	private int currentCity = -1;
	private boolean isAdd = false;

	/**
	 * 这里会启动一个线程.在这个线程里会通过网络定位来获得我们当前的城市名.
	 * 然后再通过SharedPreferences来获得上次退出时保存的城市.最后通过访问 网络上的服务器来获得城市的天气情况.
	 */
	// @Override
	public void onCreate() {
		// super.onCreate(savedInstanceState);
		// setContentView(R.layout.fragment_weather);
		//
		// findView();
		sf = mContext.getSharedPreferences(
				"com.CES.example.weather_preference", 0);

		Thread thread = new Thread(new Runnable() {
			@SuppressLint("NewApi")
			@Override
			public void run() {
				mHandler.sendEmptyMessage(HDL_START_PROGRESS);
				// localCityName =
				// LocationUtils.getLocationCity(MainActivity.this);
				localCityName = "广州";
				cityNames = new LinkedList<String>();
				// if (localCityName == null)
				// localCityName = sf.getString(KEY_LOCAL, "");
				map = new LinkedHashMap<String, WeatherParser>();
				// LinkedList<String> cityList = null;

				String citiesS = sf.getString(KEY_CITIES, null);
				JSONArray mjson;
				if (citiesS != null) {
					try {
						mjson = new JSONArray(citiesS);

						for (int i = 0; i < mjson.length(); i++) {
							cityNames.add(mjson.getString(i));
						}

					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
				if (!(cityNames.size() > 0)) {
					cityNames = new LinkedList<String>();
					cityNames.add(localCityName);
					cityNames.add("深圳");
					cityNames.add("中山");
					cityNames.add("梅州");
				}
				for (int i = 0; i < cityNames.size(); i++)
					map.put(cityNames.get(i), new WeatherParser());
				System.out.println("LocalCity: " + localCityName);
				helper = new SinaWeatherHelper();
				helper.setMap(map);
				helper.setDay(SinaWeatherHelper.DAY_ALL);
				helper.setHelpListener(listener);
				helper.start();
				// saveCityNames();
				// addCityDlg()
			}
		});
		thread.start();

	}

	// /**
	// * 退出时会将城市名存到SharedPreferences里,以便下次再使用.
	// */
	// @SuppressLint({ "CommitPrefEdits", "NewApi" })
	// // @Override
	@SuppressLint("NewApi")
	public void saveCityNames(String city) {
		Editor e = sf.edit();
		// e.putString(KEY_LOCAL, localCityName);
		// LinkedHashSet<String> cities = new LinkedHashSet<String>(cityNames);
		JSONArray arry = new JSONArray();
		for (int i = 0; i < cityNames.size(); i++) {
			arry.put(cityNames.get(i));
		}

		e.putString(KEY_CITIES, arry.toString());

		e.commit();
		// super.onDestroy();
	}

	/**
	 * 点击触摸时切换城市
	 */
	// @Override
	public void onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			currentCity++;
			if (currentCity >= cityNames.size())
				currentCity -= cityNames.size() + 1;
			mHandler.sendEmptyMessage(HDL_SET_VIEW);
		}
		// return super.onTouchEvent(event);
	}

	/**
	 * 切换城市时更新所有的控件
	 * 
	 * @param parser
	 *            包含选中城市的天气情况的WeatherParser实例
	 */
	private void setView(WeatherParser parser) {
		boolean isday = isDay();

		if (map != null) {
			Log.v("===========cityNames==========", "" + cityNames.size());
			event.Succeed(map);
		} else {
			event.Failed();
		}

	}

	/**
	 * 根据天气状态来选择图片
	 */
	public static int getIcon(String status, boolean isday) {
		if (status.equals("晴"))
			return (isday) ? R.drawable.weather_sunny
					: R.drawable.weather_sunny_n;
		else if (status.equals("雾"))
			return R.drawable.weather_fog;
		else if (status.equals("霾"))
			return R.drawable.weather_dust;
		else if (status.equals("阴") || status.equals("多云"))
			return (isday) ? R.drawable.weather_sunny
					: R.drawable.weather_sunny_n;
		else if (status.equals("小雨") || status.equals("中雨")
				|| status.equals("大雨") || status.equals("暴雨"))
			return R.drawable.weather_rain;
		else if (status.equals("小雪") || status.equals("中雪")
				|| status.equals("大雪") || status.equals("暴雪"))
			return R.drawable.weather_snow;
		else if (status.equals("雨夹雪"))
			return R.drawable.weather_icyrain;
		else if (status.equals("阵雨"))
			return R.drawable.weather_chancerain;
		else if (status.equals("阵雪"))
			return R.drawable.weather_chancesnow;
		else if (status.equals("雷阵雨") || status.equals("雷阵雪"))
			return (isday) ? R.drawable.weather_chancestorm
					: R.drawable.weather_chancestorm_n;
		else if (status.equals("雷雨"))
			return R.drawable.weather_storm;
		else if (status.equals("小到中雨") || status.equals("小到中雨")
				|| status.equals("小到大雨"))
			return R.drawable.weather_rain;
		return R.drawable.weather_no;
	}

	/**
	 * 解释yyyy-MM-dd时间格式的的时间.并获得月份,日期和星期.
	 */
	public static String getDateAndWeek(String date) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date d = sdf.parse(date);

			String dateAndWeek = (d.getMonth() + 1) + "月" + d.getDate() + "日  ";
			if (d.getDay() == 0)
				dateAndWeek += "星期日";
			if (d.getDay() == 1)
				dateAndWeek += "星期一";
			if (d.getDay() == 2)
				dateAndWeek += "星期二";
			if (d.getDay() == 3)
				dateAndWeek += "星期三";
			if (d.getDay() == 4)
				dateAndWeek += "星期四";
			if (d.getDay() == 5)
				dateAndWeek += "星期五";
			if (d.getDay() == 6)
				dateAndWeek += "星期六";
			return dateAndWeek;
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 根据系统当前时间来判断是否是白天
	 */
	public static boolean isDay() {
		Calendar c = Calendar.getInstance();
		int h = c.get(Calendar.HOUR_OF_DAY);
		return (h > 6 && h <= 18);
	}

	/**
	 * 通过向Handler发送消息再由Handler来显示信息.
	 */
	private void sendMSG(String strMSG) {
		Message msg = new Message();
		msg.what = HDL_SHOW_MSG;
		Bundle b = new Bundle();
		b.putString("msg", strMSG);
		msg.setData(b);
		mHandler.sendMessage(msg);
	}

	/**
	 * 切换城市对话框
	 */
	private Dialog changeCityDlg() {
		String[] cities = new String[cityNames.size() + 1];
		cities[0] = "本地(" + localCityName + ")";
		for (int i = 0; i < cityNames.size(); i++)
			cities[i + 1] = cityNames.get(i);

		return new AlertDialog.Builder(mContext)
				.setIcon(android.R.drawable.ic_dialog_info)
				.setTitle("请选择")
				.setSingleChoiceItems(cities, currentCity + 1,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								currentCity = which - 1;
								mHandler.sendEmptyMessage(HDL_SET_VIEW);
								dialog.dismiss();
							}
						})
				.setNegativeButton(R.string.dlg_cancel, new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				}).create();
	}

	private String cityName = "";

	/**
	 * 添加城市对话框
	 */
	public void addCityDlg(String city) {
		cityName = city;

		LinkedHashMap<String, WeatherParser> oneCity = new LinkedHashMap<String, WeatherParser>();
		oneCity.put(cityName, new WeatherParser());

		isAdd = true;
		mHandler.sendEmptyMessage(HDL_START_PROGRESS);
		helper.setMap(oneCity);
		helper.start();
	}

	/**
	 * 移除城市对话框
	 */
	private Dialog removeCityDlg() {
		final String[] cities = new String[cityNames.size()];
		final boolean[] state = new boolean[cityNames.size()];
		for (int i = 0; i < cities.length; i++) {
			cities[i] = cityNames.get(i);
			state[i] = false;
		}

		return new AlertDialog.Builder(mContext)
				.setIcon(android.R.drawable.ic_delete)
				.setTitle(R.string.action_remove)
				.setMultiChoiceItems(cities, state,
						new OnMultiChoiceClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which, boolean isChecked) {
							}
						})
				.setPositiveButton(R.string.dlg_sure, new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						for (int i = 0; i < state.length; i++) {
							if (state[i]) {
								if (currentCity == i)
									currentCity--;
								String cityName = cities[i];
								cityNames.remove(cityName);
								map.remove(cityName);
								mHandler.sendEmptyMessage(HDL_SET_VIEW);
							}
						}
					}
				})
				.setNegativeButton(R.string.dlg_cancel, new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				}).create();
	}

	/**
	 * 一个Handler.用于修改界面.
	 */
	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case HDL_SET_VIEW:
				WeatherParser parser = null;
				if (currentCity == -1)
					parser = map.get(localCityName);
				else if (currentCity >= 0 && currentCity < cityNames.size())
					parser = map.get(cityNames.get(currentCity));

				setView(parser);
				break;
			case HDL_START_PROGRESS:
				// pDlg = ProgressDialog.show(mContext, null,
				// mContext.getString(R.string.dlg_msg), true);
				if (mProgressBar != null)
					mProgressBar.startAnimation(operatingAnim);
				break;
			case HDL_STOP_PROGRESS:
				if (mProgressBar != null)
					// pDlg.dismiss();
					mProgressBar.clearAnimation();
				break;
			case HDL_SHOW_MSG:
				String text = msg.getData().getString("msg");
				Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
				break;
			}
		}
	};

	/**
	 * SinaWeatherHelper的监听器,用于监听SinaWeatherHelper的运行情况.
	 */
	private HelpListener listener = new HelpListener() {

		@Override
		public void onStart() {
		}

		@Override
		public void onFinishCity(String cityName, WeatherParser parser) {
			if (parser != null) {
				if (parser.getWeatherCount() == 0) {
					if (cityName.equals(localCityName))
						sendMSG("无法获得本地城市的天气情况.");
					else
						sendMSG("无法获得\"" + cityName + "\"的城市天气情况.");

					if (!cityName.equals(localCityName)
							&& map.containsKey(cityName)) {
						map.remove(cityName);
						cityNames.remove(cityName);
					}
					return;
				}
			}

			if (isAdd) {
				if (!map.containsKey(cityName))
					map.put(cityName, parser);
				if (!cityNames.contains(cityName))
					cityNames.add(cityName);

				currentCity = cityNames.indexOf(cityName);
				mHandler.sendEmptyMessage(HDL_SET_VIEW);
			}
		}

		@Override
		public void onFinishAll(LinkedHashMap<String, WeatherParser> map) {
			isAdd = false;
			mHandler.sendEmptyMessage(HDL_SET_VIEW);
			mHandler.sendEmptyMessage(HDL_STOP_PROGRESS);
		}
	};

	// @SuppressLint("NewApi")
	/**
	 * 把添加的城市
	 * 
	 * @param city
	 *            城市名字
	 * @param mContext
	 *            context
	 */
	public static void saveCity(String city, Context mContext) {

		SharedPreferences sf = mContext.getSharedPreferences(
				"com.CES.example.weather_preference", 0); // SharedPreferences
															// 是安卓的一种存储方式，key
															// value

		String citiesS = sf.getString(KEY_CITIES, null);
		LinkedList<String> list = new LinkedList<String>();

		JSONArray mjson;
		if (citiesS != null) {
			try {
				mjson = new JSONArray(citiesS);

				for (int i = 0; i < mjson.length(); i++) {
					list.add(mjson.getString(i));
				}

			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		list.add(city);
		// =====================================
		Editor e = sf.edit();
		// e.putString(KEY_LOCAL, localCityName);
		JSONArray arry = new JSONArray();
		for (int i = 0; i < list.size(); i++) {
			arry.put(list.get(i));
		}

		e.putString(KEY_CITIES, arry.toString());

		e.commit();
		// super.onDestroy();
	}

	public static void changeCity(JSONArray city, Context mContext) {

		SharedPreferences sf = mContext.getSharedPreferences(
				"com.CES.example.weather_preference", 0); // SharedPreferences
															// 是安卓的一种存储方式，key
															// value
															// String citiesS =
															// sf.getString(KEY_CITIES,
															// null);
		LinkedList<String> list = new LinkedList<String>();
		for (int i = 0; i < city.length() - 1; i++) {
			try {
				list.add(city.getString(i));
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		// =====================================
		Editor e = sf.edit();
		// e.putString(KEY_LOCAL, localCityName);
		JSONArray arry = new JSONArray();
		for (int i = 0; i < list.size() - 1; i++) {

			arry.put(list.get(i));
		}

		e.putString(KEY_CITIES, arry.toString());

		e.commit();
		map.clear();
		for (int i = 0; i < list.size(); i++)
			map.put(list.get(i), new WeatherParser());
		// NuanNuanApp.setMapCity(map.toString());
		helper.setMap(map);
		helper.setDay(SinaWeatherHelper.DAY_ALL);
		helper.start();
		// super.onDestroy();
	}

	/**
	 * 通过传入去的日期获取星期几
	 * 
	 * @param i
	 * @return string
	 */
	public static String getWeek(int i) {
		final Calendar c = Calendar.getInstance();
		c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));

		int week = c.get(Calendar.DAY_OF_WEEK) + i;
		if (week > 7) {
			week = week - 7;
		}
		String mWay = String.valueOf(week);
		if ("1".equals(mWay)) {
			mWay = "日";
		} else if ("2".equals(mWay)) {
			mWay = "一";
		} else if ("3".equals(mWay)) {
			mWay = "二";
		} else if ("4".equals(mWay)) {
			mWay = "三";
		} else if ("5".equals(mWay)) {
			mWay = "四";
		} else if ("6".equals(mWay)) {
			mWay = "五";
		} else if ("7".equals(mWay)) {
			mWay = "六";
		}
		return "周" + mWay;
	}
}

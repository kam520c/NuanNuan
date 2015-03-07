package com.nuannuan.weather.custom.controls;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.TimeZone;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nuannuan.common.R;
import com.nuannuan.weather.utility.WeatherCondition;
import com.nuannuan.weather.utility.WeatherParser;
import com.nuannuan.weather.utility.WeatherUtility;

public class WeatherTrendLin extends LinearLayout {
	private Context mContext;
	private List<TextView> tvTopStatus = new ArrayList<TextView>();
	private List<TextView> tvBottomStatus = new ArrayList<TextView>();

	public WeatherTrendLin(Context context, WeatherParser parser) {
		super(context);
		// TODO Auto-generated constructor stub
		mContext = context;
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);// 迭代器

		View view = inflater.inflate(R.layout.layout_weather_trend, this);
		WeatherTrendView trend = (WeatherTrendView) view
				.findViewById(R.id.weather_trend);
		initView(view);

		List<Integer> topList = new LinkedList<Integer>();
		List<Integer> lowList = new LinkedList<Integer>();

		List<Integer> top = new LinkedList<Integer>();
		List<Integer> low = new LinkedList<Integer>();
		for (int i = 0; i < parser.getWeatherCount(); i++) {

			WeatherCondition condition = parser.getWeather(i);

			if (condition != null) {

				String day = condition.status1;
				String night = condition.status2;

				if (night == null) {
					night = day;
				}
				if (i < tvTopStatus.size()) {
					tvTopStatus.get(i).setText(day + "");
				}
				if (i < tvBottomStatus.size()) {
					tvBottomStatus.get(i).setText(night + "");
				}

				topList.add(WeatherUtility.getIcon(day, true));
				lowList.add(WeatherUtility.getIcon(night, false));

				String tempDay = condition.temperature1;
				String tempNight = condition.temperature2;

				int tempTop = Integer.parseInt(tempDay.trim());

				int tempLow = 0;
				if (tempNight == null) {
					tempLow = tempTop - 8;
				} else {
					tempLow = Integer.parseInt(tempNight.trim());
				}

				top.add(tempTop);
				low.add(tempLow);
			}
		}

		DisplayMetrics dm = getResources().getDisplayMetrics();
		int displayWidth = dm.widthPixels;
		int displayHeight = dm.heightPixels;

		// trend.setWidthHeight(displayWidth, displayHeight);
		trend.setTemperature(top, low);
		trend.setBitmap(topList, lowList);
	}

	private void initView(View view) {

		final Calendar c = Calendar.getInstance();
		c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));

		int mDay = c.get(Calendar.DAY_OF_MONTH);// 获取当前月份的日期号码

		TextView top1 = (TextView) view.findViewById(R.id.top1);
		TextView top2 = (TextView) view.findViewById(R.id.top2);
		TextView top3 = (TextView) view.findViewById(R.id.top3);
		TextView top4 = (TextView) view.findViewById(R.id.top4);
		TextView top5 = (TextView) view.findViewById(R.id.top5);
		top1.setText(WeatherUtility.getWeek(0) + '\n' + mDay + "日");
		top2.setText(WeatherUtility.getWeek(1) + '\n' + (mDay + 1) + "日");
		top3.setText(WeatherUtility.getWeek(2) + '\n' + (mDay + 2) + "日");
		top4.setText(WeatherUtility.getWeek(3) + '\n' + (mDay + 3) + "日");
		top5.setText(WeatherUtility.getWeek(4) + '\n' + (mDay + 4) + "日");

		TextView state1 = (TextView) view.findViewById(R.id.state1);
		TextView state2 = (TextView) view.findViewById(R.id.state2);
		TextView state3 = (TextView) view.findViewById(R.id.state3);
		TextView state4 = (TextView) view.findViewById(R.id.state4);
		TextView state5 = (TextView) view.findViewById(R.id.state5);

		tvTopStatus.add(state1);
		tvTopStatus.add(state2);
		tvTopStatus.add(state3);
		tvTopStatus.add(state4);
		tvTopStatus.add(state5);

		TextView bottom1 = (TextView) view.findViewById(R.id.bottom1);
		TextView bottom2 = (TextView) view.findViewById(R.id.bottom2);
		TextView bottom3 = (TextView) view.findViewById(R.id.bottom3);
		TextView bottom4 = (TextView) view.findViewById(R.id.bottom4);
		TextView bottom5 = (TextView) view.findViewById(R.id.bottom5);

		tvTopStatus.add(state1);
		tvTopStatus.add(state2);
		tvTopStatus.add(state3);
		tvTopStatus.add(state4);
		tvTopStatus.add(state5);

		tvBottomStatus.add(bottom1);
		tvBottomStatus.add(bottom2);
		tvBottomStatus.add(bottom3);
		tvBottomStatus.add(bottom4);
		tvBottomStatus.add(bottom5);

	}


}

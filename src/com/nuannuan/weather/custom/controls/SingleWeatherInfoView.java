package com.nuannuan.weather.custom.controls;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nuannuan.weather.utilitys.WeatherCondition;
import com.scau.feelingmusic.R;

/**
 * 自定义控件.这个控件是显示未来的天气情况的.这里会显示日期,天气状态和气温.
 * 
 * @author Hong
 *
 */
public class SingleWeatherInfoView extends LinearLayout{

	private Context context;
	private ImageView weatherIcon = null;
    private TextView textDate = null;
    private TextView textStatus = null;
    private TextView textTemperature = null;
    
    private String day = "";
    private String week = "";
    private String status = "";
    private String tempUp = "";
    private String tempDown = "";
    
    /**
     * 构造函数
     */
    public SingleWeatherInfoView(Context context){
    	super(context);
    }
    
    /**
     * 构造函数
     */
	public SingleWeatherInfoView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		int dir = getOrientation();
		setGravity(Gravity.CENTER);
		setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT) );
		
		weatherIcon = new ImageView(context);
		weatherIcon.setPadding(20, 0, 10, 5);
		weatherIcon.setImageResource(R.drawable.weather_no);
		
		textDate = new TextView(context);
		textDate.setTextSize(12);
		textDate.setTextColor(context.getResources().getColor(R.color.black));
		textStatus = new TextView(context);
		textStatus.setTextSize(12);
		textStatus.setTextColor(context.getResources().getColor(R.color.black));
		textTemperature = new TextView(context);
		textTemperature.setTextColor(context.getResources().getColor(R.color.black));
		textTemperature.setTextSize(15);
		setView();
		
//		if(dir == LinearLayout.VERTICAL)
			doDirVertical();
//		else
//			doDirHorizontal();
	}
	
	/**
	 * 显示为坚屏时的情况
	 */
	private void doDirVertical() {
		setPadding(5, 0, 5, 0);
		addView(textDate, new LinearLayout.LayoutParams( 
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		addView(weatherIcon, new LinearLayout.LayoutParams(90, 90));
		addView(textStatus, new LinearLayout.LayoutParams( 
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		addView(textTemperature, new LinearLayout.LayoutParams( 
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
	}
	
//	/**
//	 * 显示为横屏时的情况
//	 */
//	private void doDirHorizontal() {
//		LinearLayout layout = new LinearLayout(context);
//		layout.setOrientation(LinearLayout.VERTICAL);
//		layout.setGravity(Gravity.CENTER);
//		layout.addView(textDate, new LayoutParams( 
//				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
//		layout.addView(textStatus, new LinearLayout.LayoutParams( 
//				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
//		layout.addView(textTemperature, new LinearLayout.LayoutParams( 
//				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
//		
//		addView(layout, new LinearLayout.LayoutParams( 
//				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
//		addView(weatherIcon, new LinearLayout.LayoutParams(90, 90));
//	}
	
	/**
	 * 设置控件
	 */
	private void setView() {
		int dir = getOrientation();
		weatherIcon.setImageResource(getIcon());
		textDate.setText(day + " " + week);
		textStatus.setText(status);
		if(dir == LinearLayout.VERTICAL)
			textTemperature.setText(tempUp + "\n" + tempDown);
		else
			textTemperature.setText(tempUp + " / " + tempDown);
	}
	
	/**
	 * 通过天气状态来选择图片
	 */
	private int getIcon() {
		if(status.equals("晴"))
			return R.drawable.weather_sunny;
		else if(status.equals("雾"))
			return R.drawable.weather_fog;
		else if(status.equals("霾"))
			return R.drawable.weather_dust;
		else if(status.equals("阴") ||
				status.equals("多云"))
			 return R.drawable.weather_mostlycloudy;
		else if(status.equals("小雨") || 
				status.equals("中雨") ||
				status.equals("大雨") ||
				status.equals("暴雨"))
			return R.drawable.weather_rain;
		else if(status.equals("小雪") || 
				status.equals("中雪") ||
				status.equals("大雪") ||
				status.equals("暴雪"))
			return R.drawable.weather_snow;
		else if(status.equals("雨夹雪"))
			return R.drawable.weather_icyrain;
		else if(status.equals("阵雨"))
			return R.drawable.weather_chancerain;
		else if(status.equals("阵雪"))
			return R.drawable.weather_chancesnow;
		else if(status.equals("雷阵雨") ||
				status.equals("雷阵雪"))
			return R.drawable.weather_chancestorm;
		else if(status.equals("雷雨"))
			return R.drawable.weather_storm;
		return R.drawable.weather_no;
	}
	
	/**
	 * 设备天气情况
	 */
	@SuppressLint("SimpleDateFormat")
	public void setWeather(WeatherCondition weather) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(weather.savedate_weather);
			String week = null;
			if(date.getDay() == 0) week = "星期日";
			if(date.getDay() == 1) week = "星期一";
			if(date.getDay() == 2) week = "星期二";
			if(date.getDay() == 3) week = "星期三";
			if(date.getDay() == 4) week = "星期四";
			if(date.getDay() == 5) week = "星期五";
			if(date.getDay() == 6) week = "星期六";
			
			this.day = date.getDate() + "日  " + week;
			this.status = weather.status1;
			this.tempUp = weather.temperature1 + "°C";
			this.tempDown = weather.temperature2 + "°C";
			setView();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

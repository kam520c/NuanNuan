package com.nuannuan.weather.utilitys;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

/**
 * 天气数据解释类.从网络获得是是一个XML文件.所以我们需要解释它才可以用.解释后的数据
 * 存放在WeatherCondition类里.
 * 
 * @author Hong
 *
 */
public class WeatherParser {
	
	private List<WeatherCondition> weatherList = new LinkedList<WeatherCondition>();

	/**
	 * 获得解释后的天气
	 */
	public WeatherCondition getWeather(int day) {
		for(int i = 0; i < weatherList.size(); i++) {
			WeatherCondition weather = weatherList.get(i);
			if(weather.day == day)
				return weather;
		}
		return null;
	}
	
	/**
	 * 获得解释后的天气数量,因为一般来说都会从网络上获取5天的天气情况.
	 */
	public int getWeatherCount() {
		return weatherList.size();
	}
	
	/**
	 * 清除天气情况
	 */
	public void clearWeather() {
		weatherList.clear();
	}

	/**
	 * 解释动作.
	 */
	public boolean parse(InputStream is, int day) 
			throws IOException, XmlPullParserException{
		if(is == null)
			throw new IllegalArgumentException("InputStream=null");
		
		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(is, "UTF-8");
		WeatherCondition weather = null;
		
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				break;
			case XmlPullParser.START_TAG:
				if(parser.getName().equals("Weather")) {
					weather = new WeatherCondition();
					weather.day = day;
					weatherList.add(weather);
				}
				else if(parser.getName().equals("city")) {
					parser.next();
					weather.city = parser.getText();
				}
				else if(parser.getName().equals("status1")) {
					parser.next();
					weather.status1 = parser.getText();
				}
				else if(parser.getName().equals("status2")) {
					parser.next();
					weather.status2 = parser.getText();
				}
				else if(parser.getName().equals("direction1")) {
					parser.next();
					weather.direction1 = parser.getText();
				}
				else if(parser.getName().equals("direction2")) {
					parser.next();
					weather.direction2 = parser.getText();
				}
				else if(parser.getName().equals("power1")) {
					parser.next();
					weather.power1 = parser.getText();
				}
				else if(parser.getName().equals("power2")) {
					parser.next();
					weather.power2 = parser.getText();
				}
				else if(parser.getName().equals("temperature1")) {
					parser.next();
					weather.temperature1 = parser.getText();
				}
				else if(parser.getName().equals("temperature2")) {
					parser.next();
					weather.temperature2 = parser.getText();
				}
				else if(parser.getName().equals("tgd1")) {
					parser.next();
					weather.tgd1 = parser.getText();
				}
				else if(parser.getName().equals("tgd2")) {
					parser.next();
					weather.tgd2 = parser.getText();
				}
				else if(parser.getName().equals("zwx_l")) {
					parser.next();
					weather.zwx_l = parser.getText();
				}
				else if(parser.getName().equals("chy_l")) {
					parser.next();
					weather.chy_l = parser.getText();
				}
				else if(parser.getName().equals("pollution_l")) {
					parser.next();
					weather.pollution_l = parser.getText();
				}
				else if(parser.getName().equals("yd_l")) {
					parser.next();
					weather.yd_l = parser.getText();
				}
				else if(parser.getName().equals("savedate_weather")) {
					parser.next();
					weather.savedate_weather = parser.getText();
				}
				break;
			case XmlPullParser.END_TAG:
				break;
			}
			eventType = parser.next();
		}
		return weather != null;
	}
	
}

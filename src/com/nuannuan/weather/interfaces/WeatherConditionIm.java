package com.nuannuan.weather.interfaces;

import java.util.LinkedHashMap;
import java.util.LinkedList;

import com.nuannuan.weather.utility.WeatherParser;

public interface WeatherConditionIm {
	public void Succeed(LinkedHashMap<String, WeatherParser> map);

	public void Failed();
}

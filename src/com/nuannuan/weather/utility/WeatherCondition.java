package com.nuannuan.weather.utility;

/**
 * 天气数据存放类.
 * 
 * @author Hong
 *
 */
public class WeatherCondition {
	public int day;
	/** 城市 */
	public String city;
	/** 白天天气 */
	public String status1;
	/** 夜晚天气 */
	public String status2;
	/** 白天风向 */
	public String direction1;
	/** 夜晚风向 */
	public String direction2;
	/** 白天风级 */
	public String power1;
	/** 夜晚风级 */
	public String power2;
	/** 白天温度 */
	public String temperature1;
	/** 夜晚温度 */
	public String temperature2;
	/** 白天体感度 */
	public String tgd1;
	/** 夜晚体感度 */
	public String tgd2;
	/** 紫外线说明 */
	public String zwx_l;
	/** 穿衣说明 */
	public String chy_l;
	/** 污染说明 */
	public String pollution_l;
	/** 运动说明 */
	public String yd_l;
	/** 日期 */
	public String savedate_weather;
	/** 穿衣说明 详情*/
	public String chy_shuoming;
}

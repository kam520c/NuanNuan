package com.nuannuan.common.utility;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import android.content.res.Resources;
import android.util.TypedValue;

import com.nuannuan.common.R;

public class CommonUtil {

	public static int dpToPx(Resources res, int dp) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
				res.getDisplayMetrics());
	}

	/**
	 * 
	 * @param oriText
	 *            原始词汇
	 * @param str
	 *            需要比较的关键字
	 * @return
	 */
	private static boolean GetWeatherState(String oriText, String str) {
		boolean isExist = false;
		if (oriText.length() > 3) {
			oriText = oriText.substring(0, 3);
		}
		String regex = str;
		Matcher matcher = Pattern.compile(regex).matcher(oriText);

		while (matcher.find()) {
			isExist = true;
		}
		return isExist;
	}

	/**
	 * 根据天气状态来选择图片
	 */
	public static int getIcon(String status, boolean isday) {

		if (GetWeatherState(status, "晴"))
			return (isday) ? R.drawable.weather_sunny
					: R.drawable.weather_sunny_n;
		else if (GetWeatherState(status, "雾"))
			return R.drawable.weather_fog;
		else if (GetWeatherState(status, "霾"))
			return R.drawable.weather_dust;
		else if ((GetWeatherState(status, "阴"))
				|| (GetWeatherState(status, "多云")))
			return (isday) ? R.drawable.weather_sunny
					: R.drawable.weather_sunny_n;
		else if (GetWeatherState(status, "小雨") || GetWeatherState(status, "中雨")
				|| GetWeatherState(status, "大雨")
				|| GetWeatherState(status, "暴雨"))
			return R.drawable.weather_rain;
		else if (GetWeatherState(status, "小雪") || GetWeatherState(status, "中雪")
				|| GetWeatherState(status, "大雪")
				|| GetWeatherState(status, "暴雪"))
			return R.drawable.weather_snow;
		else if (GetWeatherState(status, "雨夹雪"))
			return R.drawable.weather_icyrain;
		else if (GetWeatherState(status, "阵雨"))
			return R.drawable.weather_chancerain;
		else if (GetWeatherState(status, "阵雪"))
			return R.drawable.weather_chancesnow;
		else if (GetWeatherState(status, "雷阵雨")
				|| GetWeatherState(status, "雷阵雪"))
			return (isday) ? R.drawable.weather_chancestorm
					: R.drawable.weather_chancestorm_n;
		else if (GetWeatherState(status, "雷雨"))
			return R.drawable.weather_storm;
		else if (status.equals("小到中雨")|| status.equals("小到中雨")|| status.equals("小到大雨"))
			return R.drawable.weather_rain;
		return R.drawable.weather_no;
	}

	/**
	 * 根据天气状态来选择图片
	 */
	public static int getIM(String status, boolean isday) {
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
		else if (status.equals("小到中雨")|| status.equals("小到中雨")|| status.equals("小到大雨"))
			return R.drawable.weather_rain;
		return R.drawable.weather_no;
	}

	/**
	 * 使用httpGet请求天气
	 */
	public static String httpGetWeather(String url) {
		String result = null;
		// final String url = "http://m.weather.com.cn/data/101280101.html";
		// 第一步，创建HttpGet对象
		HttpGet httpGet = new HttpGet(url);
		// 第二步，使用execute方法发送HTTP GET请求，并返回HttpResponse对象
		HttpResponse httpResponse = null;

		HttpClient client = new DefaultHttpClient();
		// 请求超时
		client.getParams().setParameter(
				CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
		// 读取超时
		client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 5000);

		try {
			httpResponse = client.execute(httpGet);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				// 第三步，使用getEntity方法活得返回结果
				result = EntityUtils.toString(httpResponse.getEntity());
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}

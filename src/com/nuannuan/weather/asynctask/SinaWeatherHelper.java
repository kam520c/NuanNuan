package com.nuannuan.weather.asynctask;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;

import com.nuannuan.weather.utilitys.WeatherParser;

/**
 * 新浪天气帮助类.这里会通过访问新浪网来获得天气情况
 * 
 * @author Hong
 * 
 */
public class SinaWeatherHelper {

	// 访问新浪网天气所必需的东西
	public final static String HTTP_SINA_URL = "http://php.weather.sina.com.cn/xml.php";
	public final static String HTTP_PASSWORD = "DJOYnieT8234jlsK";
	public final static String HTTP_ENCODE = "gb2312";

	// 查询的日期.0为当天,1为明天,2为后天,3为大后天,4为大大后天.最多为4.
	public final static int DAY_TODAY = 1 << 0;
	public final static int DAY_TOMORROW = 1 << 1;
	public final static int DAY_AFTER_TOW_DAYS = 1 << 2;
	public final static int DAY_AFTER_THREE_DAYS = 1 << 3;
	public final static int DAY_AFTER_FOUR_DAYS = 1 << 4;
	public final static int DAY_ALL = (1 << 5) - 1;

	// map里存放了所有要查询的城市和WeatherParser天气解释类.
	private HashMap<String, WeatherParser> map;
	private HelpListener helpListener;
	private int day = 0;
	private boolean isRunning = false;

	/**
	 * 获得天气情况的一个Runnable.用于启动一个新的线程
	 */
	private Runnable getWeatherRunnable = new Runnable() {
		@Override
		public void run() {
			if (helpListener != null)
				helpListener.onStart();

			if (map == null || map.size() == 0 || day <= 0)
				isRunning = false;

			Set<String> cities = map.keySet();
			Iterator<String> it = cities.iterator();

			while (it.hasNext() && isRunning) {
				String cityName = it.next();
				// String cityName = "广州";
				WeatherParser parser = map.get(cityName);
				for (int i = 0; i < 5 && isRunning; i++) {
					if (isTheDayRequest(i)) {
						if (!getOneDayWeather(cityName, parser, i) && i == 0)
							break;
					}
				}

				if (helpListener != null)
					helpListener.onFinishCity(cityName, parser);
			}

			isRunning = false;
			if (helpListener != null)
				helpListener.onFinishAll(map);
		}
	};

	/**
	 * 判断d是否需要查询天气
	 */
	private boolean isTheDayRequest(int d) {
		int n = 1 << d;
		return (day & n) == n;
	}

	/**
	 * 获取一天的天气情况.这里会访问网络,所以这里是放在一个线程里运行.
	 */
	private boolean getOneDayWeather(String whichCity, WeatherParser parser,
			int whichDay) {
		boolean ret = false;
		try {
			String c = java.net.URLEncoder.encode(whichCity, HTTP_ENCODE);

			String url = HTTP_SINA_URL + "?city=" + c + "&password="
					+ HTTP_PASSWORD + "&day=" + whichDay;

			HttpGet httpGet = new HttpGet(url);
			HttpClient hc = new DefaultHttpClient();
			// 请求超时
			httpGet.getParams().setParameter(
					CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
			// 读取超时
			httpGet.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
					5000);
			HttpResponse ht = hc.execute(httpGet);
			if (ht.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity he = ht.getEntity();
				InputStream is = he.getContent();

			
				if (parser != null)
					ret = parser.parse(is, whichDay);

				is.close();
				return ret;
			} else {
				return ret;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ret;
		}
	}

	/**
	 * 开始获取
	 */
	public void start() {
		isRunning = true;
		(new Thread(getWeatherRunnable)).start();
	}

	/**
	 * 停止获取
	 */
	public void stop() {
		isRunning = false;
	}

	public HashMap<String, WeatherParser> getMap() {
		return map;
	}

	public void setMap(HashMap<String, WeatherParser> map) {
		this.map = map;
	}

	public WeatherParser getParser(String cityName) {
		return map.get(cityName);
	}

	/**
	 * 设置监听器
	 */
	public void setHelpListener(HelpListener helpListener) {
		this.helpListener = helpListener;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	/**
	 * 监听器.
	 * 
	 * @author Hong
	 * 
	 */
	public interface HelpListener {
		void onStart();

		void onFinishCity(String cityName, WeatherParser parser);

		void onFinishAll(HashMap<String, WeatherParser> map);

	}
}

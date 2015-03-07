package com.nuannuan.common.activity;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nuannuan.common.R;
import com.nuannuan.common.utility.CommonUtil;
import com.nuannuan.mood.asynctask.BlowAsyncEvent;
import com.nuannuan.mood.asynctask.RecordThread;
import com.nuannuan.mood.custom.controls.BlowAnimView;

public class BlowActivity extends Activity {
	private BlowAnimView flowerView;// 吹一吹的自定义View

	private RecordThread recordThread;

	private ImageView mImageView;
	private Context context;

	private JSONObject Weather = null;

	private RelativeLayout relat;
	private ImageView icon;
	private TextView textCity;
	private TextView textDate;
	private TextView textTGD;
	private TextView textDir;
	private TextView textPower;
	private TextView textZWX;
	private TextView textPol;
	private TextView textYD;
	private TextView textChy;
	private TextView textTemp;
	private TextView textStatus;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_blow);

		flowerView = (BlowAnimView) findViewById(R.id.animView);
		recordThread = new RecordThread(handler, 1);
		initView();
//		Intent mIntent = getIntent();
//		String mgetIntent = mIntent.getStringExtra("weather");
//
//		if (mgetIntent != null) {
//			try {
//				Weather = new JSONObject(mgetIntent);
//				getWeatherFromJSON(Weather);
//			} catch (JSONException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			relat.setVisibility(View.VISIBLE);
//			getWeatherFromJSON(Weather);
//		}

		flowerView.setNumber(20);
		flowerView.setDayOrNight(true);

		mImageView = (ImageView) findViewById(R.id.imageView);

		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		float width = dm.widthPixels;
		float heigth = dm.heightPixels;

		context = this;

		Animation alpha = getAlphaAm(1.0f, 0.0f, 2000);
		Animation scale = getTranslateAm(-width / 3, -width, 0, 0, 2000);
		AnimationSet set = new AnimationSet(false);
		set.addAnimation(scale);
		set.addAnimation(alpha);
		mImageView.setAnimation(set);
		mImageView.startAnimation(set);
		set.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				// mImageView.setVisibility(View.VISIBLE);
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				mImageView.setVisibility(View.GONE);

			}
		});
		recordThread.start();

	}

	private void getWeatherFromJSON(JSONObject weather) {
		try {
			JSONObject weatherJson = new JSONObject(
					weather.getString("weather"));
			String allWeather = weatherJson.getString("allWeather");
			String realTime = weatherJson.getString("RealTime");

			JSONObject json = new JSONObject(allWeather);
			JSONObject mJsonObject = new JSONObject(json.get("weatherinfo")
					.toString());

			String city = mJsonObject.getString("city");
			String date_y = mJsonObject.getString("date_y");
			String week = mJsonObject.getString("week");
			String wind1 = mJsonObject.getString("wind1");
			String windPower = mJsonObject.getString("fl1");
			String ZWX = mJsonObject.getString("index_uv");
			String Chy = mJsonObject.getString("index_d");

			String co = mJsonObject.getString("index_co");
			String cl = mJsonObject.getString("index_cl");
			String ls = mJsonObject.getString("index_ls");
			String ag = mJsonObject.getString("index_ag");

			// String temp1 = mJsonObject.getString("temp1");
			// String state = mJsonObject.getString("weather1");

			JSONObject realJson = new JSONObject(realTime);
			JSONObject realObject = new JSONObject(realJson.get("weatherinfo")
					.toString());
			String state = realObject.getString("weather");
			String temp1 = realObject.getString("temp2") + "至"
					+ realObject.getString("temp1");
			String updateTime = realObject.getString("ptime");

			String img_title1 = mJsonObject.getString("img_title1");
			textStatus.setText("" + state);

			textTemp.setText("气温:" + temp1);
			textYD.setText("舒适指数：" + co);
			textZWX.setText("紫外线：" + ZWX);
			textChy.setText("穿衣指数：" + Chy);
			textPower.setText("风力:" + windPower);
			textDate.setText("更新时间：" + updateTime);
			textCity.setText("" + city);

			int imageBg = CommonUtil.getIM(state, true);

			icon.setBackgroundResource(imageBg);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 初始化控件
	 */
	private void initView() {
		relat = (RelativeLayout) findViewById(R.id.re_weather);
		icon = (ImageView) findViewById(R.id.image_icon);
		textCity = (TextView) findViewById(R.id.text_city);
		textDate = (TextView) findViewById(R.id.text_date);
		// textTGD = (TextView) findViewById(R.id.text_tgd);
		// textDir = (TextView) findViewById(R.id.text_direction);
		textPower = (TextView) findViewById(R.id.text_power);
		textZWX = (TextView) findViewById(R.id.text_zwx);
		// textPol = (TextView) findViewById(R.id.text_pollution);
		textYD = (TextView) findViewById(R.id.text_yd);
		textChy = (TextView) findViewById(R.id.text_chy);
		textTemp = (TextView) findViewById(R.id.text_temperature);
		textStatus = (TextView) findViewById(R.id.text_status);
	}

	/**
	 * 
	 * @param fromX
	 * @param toX
	 * @param fromY
	 * @param toY
	 * @param time
	 * @return �õ�λ�ƶ���
	 */
	private Animation getTranslateAm(float fromX, float toX, float fromY,
			float toY, long time) {
		TranslateAnimation animation = new TranslateAnimation(fromX, toX,
				fromY, toY);
		animation.setFillAfter(true);
		animation.setDuration(time);
		return animation;
	}

	/**
	 * 
	 * @param from
	 * @param to
	 * @param time
	 * @return �õ�͸���仯�Ķ���
	 */
	private Animation getAlphaAm(float from, float to, long time) {
		AlphaAnimation myAnimation_Alpha = new AlphaAnimation(from, to);
		myAnimation_Alpha.setFillAfter(true);
		myAnimation_Alpha.setDuration(time);
		return myAnimation_Alpha;
	}

	BlowHandler handler = new BlowHandler();
	boolean isSucceed = false;

	class BlowHandler extends Handler {
		public void sleep(long delayMillis) {
			this.removeMessages(0);
			sendMessageDelayed(obtainMessage(2), delayMillis);
		}

		@Override
		public void handleMessage(Message msg) {

			switch (msg.what) {
			case 1:
				recordThread.stopRecord();
				// 网络不成功的时候这个线程会决定飞行时间

				WeatherAsyncTask task = new WeatherAsyncTask(context,
						new BlowAsyncEvent() {

							@Override
							public void DataLoadSuccess(JSONObject city) {
								// TODO Auto-generated method stub
								Weather = city;
								isSucceed = true;
							}

							@Override
							public void DataLoadFaild() {
								// TODO Auto-generated method stub
								isSucceed = false;

							}
						});
				task.execute("");
				isSucceed = true;
				start();
				// 决定多久之后执行云飞出的动画
				sendEmptyMessageDelayed(3, 2500);
				break;
			case 2:
				// 动画飞回来之后的延迟的时间
				// sleep(100);
				if (isSucceed) {
					start();
				} else {
					stop(false);
				}
				break;
			case 3:
				flowerView.cloudStop();
				sendEmptyMessageDelayed(4, 3500);
				break;
			case 4:
				// Log.v("==========flowerView4==========", "");
				// flowerView.stop();
				// flowerView.clearAnimation();
				// isFirst = false;
				Intent it = new Intent(BlowActivity.this, HomeActivity.class);
//				JSONObject json = new JSONObject();
//				try {
//					json.put("weather", Weather);
//				} catch (JSONException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				it.putExtra("weather", json.toString());
				startActivity(it);
				overridePendingTransition(R.anim.fade, R.anim.hold);
				finish();

				break;
			default:
				break;

			}
		}

	};

	private class WeatherAsyncTask extends
			AsyncTask<String, Integer, JSONObject> {

		private BlowAsyncEvent event;
		private String city = "";
		private ProgressBar progressBar;
		private Context mContext;

		public WeatherAsyncTask(Context context, BlowAsyncEvent event) {
			super();
			this.event = event;
			mContext = context;
			handler.sleep(2000);
		}

		@Override
		protected JSONObject doInBackground(String... params) {
//			// TODO Auto-generated method stub
			JSONObject map = new JSONObject();
//			String result = httpGetWeather();
//			String RealTime = CommonUtil
//					.httpGetWeather("http://www.weather.com.cn/data/cityinfo/101280101.html");
//
//			try {
//				map.put("allWeather", result);
//				map.put("RealTime", RealTime);
//			} catch (JSONException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			return map;
		}

		@Override
		protected void onPostExecute(JSONObject result) {
			super.onPostExecute(result);
			if (result != null) {

				event.DataLoadSuccess(result);

			} else {
				event.DataLoadFaild();
			}
		}

		@Override
		protected void onPreExecute() {

		}

		@Override
		protected void onProgressUpdate(Integer... values) {

		}

		/**
		 * 使用httpGet请求天气
		 */
		private String httpGetWeather() {
			String result = null;
			final String url = "http://m.weather.com.cn/data/101280101.html";
			// 第一步，创建HttpGet对象
			HttpGet httpGet = new HttpGet(url);
			// 第二步，使用execute方法发送HTTP GET请求，并返回HttpResponse对象
			HttpResponse httpResponse = null;

			HttpClient client = new DefaultHttpClient();
			// 请求超时
			client.getParams().setParameter(
					CoreConnectionPNames.CONNECTION_TIMEOUT, 1900);
			// 读取超时
			client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
					1900);

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

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		recordThread.stopRecord();
		super.onStop();
	}

	/**
	 * �?��动画
	 */
	private void start() {
		flowerView.fly();
	}

	/**
	 * 根据是否获取到数据，结束动画
	 */
	private void stop(boolean success) {
		if (success) {// 如果成功获取数据，白云飘过结束动�?
			flowerView.cloudStop();
		} else {// 没有获取数据则另�?��动画
			flowerView.flyDone();
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		if (flowerView.bg_day != null) {
			flowerView.bg_day.recycle();
		}

		if (flowerView.cloud != null) {
			flowerView.cloud.recycle();
		}

		if (flowerView.flower != null) {
			flowerView.flower.recycle();
		}

		if (flowerView.flower2 != null) {
			flowerView.flower2.recycle();
		}
		if (flowerView.fly != null) {
			flowerView.fly.recycle();
		}

		super.onDestroy();
	}

}

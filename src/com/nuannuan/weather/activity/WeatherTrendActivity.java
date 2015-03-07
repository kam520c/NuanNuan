package com.nuannuan.weather.activity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.nuannuan.common.R;
import com.nuannuan.common.app.NuanNuanApp;
import com.nuannuan.common.bean.AddCitiesItemBean;
import com.nuannuan.common.custom.controls.JazzyViewPager;
import com.nuannuan.common.custom.controls.JazzyViewPager.TransitionEffect;
import com.nuannuan.weather.adapter.WeatherTrendAdapter;
import com.nuannuan.weather.custom.controls.WeatherTrendLin;
import com.nuannuan.weather.interfaces.WeatherConditionIm;
import com.nuannuan.weather.utility.WeatherParser;
import com.nuannuan.weather.utility.WeatherUtility;

public class WeatherTrendActivity extends Activity implements
		OnPageChangeListener, View.OnClickListener {
	private GridView gridView;
	private LayoutInflater inflater;
	private List<AddCitiesItemBean> addCitiesItem = null;
	private JazzyViewPager mJazzy;
	private NuanNuanApp mNuanApp;
	private ImageView mBar;
	private LinkedHashMap<String, WeatherParser> linkMap = new LinkedHashMap<String, WeatherParser>();
	private ArrayList<LinearLayout> listLin = new ArrayList<LinearLayout>();
	private TextView mTextView;
	private List<String> mlist = new ArrayList<String>();
	private LinkedHashMap<String, WeatherParser> map;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏顶部title
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weather_trend);
		mNuanApp = (NuanNuanApp) getApplication();
		map = mNuanApp.getMapCity();
		initView();
		setupJazziness(TransitionEffect.CubeOut);
		// initDots();
	}

	private void initView() {
		mJazzy = (JazzyViewPager) findViewById(R.id.trend_jazzy_pager);
		// ViewGroup group = (ViewGroup) findViewById(R.id.guide_bg);
		// mBar = (ProgressBar) findViewById(R.id.progressBar);
		LinearLayout backBtn = (LinearLayout) findViewById(R.id.btn_back);
		backBtn.setOnClickListener(this);
		mTextView = (TextView) findViewById(R.id.include_mood_title);
		if (map != null) {
			Set<String> set = map.keySet();
			mlist.addAll(set);
			if (mlist.size() > 0) {
				mTextView.setText(mlist.get(0));
			}
		}
		
		Button BtnBack = (Button) findViewById(R.id.all_back);
		BtnBack.setOnClickListener(this);

	}

	private WeatherUtility utilityWeather = null;

	private void setupJazziness(TransitionEffect effect) {
		mJazzy.setTransitionEffect(effect);

		if (map == null) {
			utilityWeather = new WeatherUtility(this, new WeatherConditionIm() {
				@Override
				public void Succeed(LinkedHashMap<String, WeatherParser> map) {
					initInhale(map);
					Set<String> set = map.keySet();
					int count = mlist.size();
					for (int i = 0; i < count; i++) {
						mlist.remove(i);
					}
					mlist.addAll(set);
					if (mlist.size() > 0) {
						mTextView.setText(mlist.get(0));
					}
					mNuanApp.setMapCity(map);
				}

				@Override
				public void Failed() {
					// TODO Auto-generated method stub
					Toast.makeText(WeatherTrendActivity.this, "获取天气失败,请重试",
							Toast.LENGTH_SHORT).show();
				}
			}, mBar);
		} else {
			initInhale(map);
		}
	}

	private void initInhale(LinkedHashMap<String, WeatherParser> linkMap) {
		int screenWidth = this.getWindowManager().getDefaultDisplay()
				.getWidth();
		int screenHeigth = this.getWindowManager().getDefaultDisplay()
				.getHeight();

		LinkedList<String> cityList = new LinkedList<String>();
		Set<String> key = linkMap.keySet();
		for (String city : key) {
			cityList.add(city);
		}

		for (int i = 0; i < linkMap.size(); i++) {
			WeatherTrendLin lin = new WeatherTrendLin(this,
					linkMap.get(cityList.get(i)));
			listLin.add(lin);

		}
		WeatherTrendAdapter adapter = new WeatherTrendAdapter(listLin, mJazzy);
		mJazzy.setAdapter(adapter);
		mJazzy.setPageMargin(30);
		mJazzy.setOnPageChangeListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_back:
			finish();
			break;
		case R.id.all_back:
			finish();
			break;
		default:
			break;
		}

	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		if (arg0 < mlist.size()) {
			String city = mlist.get(arg0);
			mTextView.setText(city);
		}
	}
}
package com.nuannuan.common.fragment;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.nuannuan.common.R;
import com.nuannuan.common.R.anim;
import com.nuannuan.common.activity.HomeActivity;
import com.nuannuan.common.app.NuanNuanApp;
import com.nuannuan.common.custom.controls.InhaleView;
import com.nuannuan.common.custom.controls.JazzyViewPager;
import com.nuannuan.common.custom.controls.JazzyViewPager.TransitionEffect;
import com.nuannuan.common.utility.MoodTimeCount;
import com.nuannuan.common.utility.Rotate3dAnimation;
import com.nuannuan.common.utility.RotationTimeCount;
import com.nuannuan.mood.activity.LineEditActivity;
import com.nuannuan.weather.activity.ChangeCitiesActivity;
import com.nuannuan.weather.adapter.WeatherAdapter;
import com.nuannuan.weather.interfaces.WeatherConditionIm;
import com.nuannuan.weather.interfaces.WeatherIm;
import com.nuannuan.weather.utility.WeatherParser;
import com.nuannuan.weather.utility.WeatherUtility;

public class WeatherFragment extends android.support.v4.app.Fragment implements
		OnPageChangeListener, View.OnClickListener {

	private HomeActivity home;
	private JazzyViewPager mJazzy;
	private ArrayList<InhaleView> listLin = new ArrayList<InhaleView>();
	private ImageView[] imageViews = null;
	private Button deleteBtn;
	private boolean mReverse = false;
	private int witch;
	// private ImageView mImageView;
	private List<Integer> gifList = new ArrayList<Integer>();
	private int num = 0;
	private LinearLayout mImageView1 = null;
	private LinearLayout mImageView2 = null;
	private LinearLayout mStartAnimView = null;
	private String weather = null;
	private boolean isAddNum = false;
	private boolean isClick = false;// 传入给信纸的那个界面
	private ViewGroup group;
	private WeatherAdapter adapter;
	private WeatherUtility weatherUtility;
	private NuanNuanApp mNuanApp;
	private ImageView mBar;
	private RotationTimeCount time;
	private MoodTimeCount moodTime;
	private final static int INITGIF=3;

	// private LinkedHashMap<String, WeatherParser> map;

	public WeatherFragment(ArrayList<InhaleView> listInhale) {
		listInhale = listLin;
	}

	public WeatherFragment() {

	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		isClick = false;
		super.onPause();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View weatherLayout = inflater.inflate(R.layout.fragment_weather,
				container, false);

		home = (HomeActivity) getActivity();
		mNuanApp = (NuanNuanApp) home.getApplication();

		initGifList();

		initView(weatherLayout);
		updateWeather();
		initDots();
		return weatherLayout;
	}

	/**
	 * 初始化控件
	 * 
	 * @param view
	 */
	private void initView(View view) {
		mJazzy = (JazzyViewPager) view.findViewById(R.id.jazzy_pager);
		mJazzy.setTransitionEffect(TransitionEffect.Standard);
		deleteBtn = (Button) view.findViewById(R.id.deleteBtn);
		group = (ViewGroup) view.findViewById(R.id.guide_bg);

		deleteBtn.setOnClickListener(this);
		Button AddBtn = (Button) view.findViewById(R.id.addBtn);
		AddBtn.setOnClickListener(this);
		mImageView1 = (LinearLayout) view.findViewById(R.id.home_gif);
		mImageView1.setOnClickListener(this);
		initAnim(gifList.get(INITGIF));
		mImageView2 = (LinearLayout) view.findViewById(R.id.home_gif2);
		mContainer = view.findViewById(R.id.container);
		mStartAnimView = mImageView1;
		ImageView pen = (ImageView) view.findViewById(R.id.pen_btn);
		pen.setOnClickListener(this);

		LinearLayout linearLayout = (LinearLayout) view
				.findViewById(R.id.refresh_lin);
		linearLayout.setOnClickListener(this);

		mBar = (ImageView) view.findViewById(R.id.refresh_btn);

		time = new RotationTimeCount(1000000, 800);
		time.start();
		moodTime = new MoodTimeCount(2500, 2500, home);
	}

	private LinkedHashMap<String, WeatherParser> linkMap = new LinkedHashMap<String, WeatherParser>();

	private WeatherUtility utilityWeather = null;
	private boolean isRefreshWeather = false;// 是否可以刷新天气信息

	/**
	 * 更新主页的天气信息
	 * 
	 * @param effect
	 */
	private void updateWeather() {

		LinkedHashMap<String, WeatherParser> map = mNuanApp.getMapCity();
		if (map == null || isRefreshWeather) {
			utilityWeather = new WeatherUtility(home, new WeatherConditionIm() {
				@Override
				public void Succeed(LinkedHashMap<String, WeatherParser> map) {
					initInhale(map);
					mNuanApp.setMapCity(map);
					isRefreshWeather = false;
				}

				@Override
				public void Failed() {
					// TODO Auto-generated method stub
					Toast.makeText(home, "获取天气失败,请重试", Toast.LENGTH_SHORT)
							.show();
					isRefreshWeather = false;
				}
			}, mBar);
		} else {
			initInhale(map);
		}
	}

	private void initInhale(LinkedHashMap<String, WeatherParser> linkMap) {
		int screenWidth = home.getWindowManager().getDefaultDisplay()
				.getWidth();
		int screenHeigth = home.getWindowManager().getDefaultDisplay()
				.getHeight();

		LinkedList<String> cityList = new LinkedList<String>();
		Set<String> key = linkMap.keySet();
		for (String city : key) {
			cityList.add(city);
		}

		for (int i = 0; i < linkMap.size(); i++) {
			InhaleView mInhaleView1 = new InhaleView(home, linkMap.get(cityList
					.get(i)), screenWidth, screenHeigth);
			listLin.add(mInhaleView1);

		}
		adapter = new WeatherAdapter(home, listLin, mJazzy);
		mJazzy.setAdapter(adapter);
		mJazzy.setPageMargin(30);
		mJazzy.setOnPageChangeListener(this);
	}

	private void initGifList() {
		gifList.add(anim.gif_md1);
		gifList.add(anim.gif_md2);
		gifList.add(anim.gif_md3);
		gifList.add(anim.gif_md4);
		gifList.add(anim.gif_md5);
		gifList.add(anim.gif_md6);
		gifList.add(anim.gif_md7);
		gifList.add(anim.gif_md8);
		gifList.add(anim.gif_md9);
		gifList.add(anim.gif_md10);
		gifList.add(anim.gif_md11);
		gifList.add(anim.gif_md12);
		gifList.add(anim.gif_md13);
		gifList.add(anim.gif_md14);
		gifList.add(anim.gif_md15);
		gifList.add(anim.gif_md15);
	}

	// 初始化动画
	private void initAnim(int anim) {
		// 城市箭头补间动画
		mImageView1.setBackgroundResource(anim);
		AnimationDrawable ad = (AnimationDrawable) mImageView1.getBackground();
		ad.start();
	}

	/**
	 * 心情图片存入json
	 * @return
	 */
	private String getJsonForMood() {
		String mood = "";

		JSONObject json = new JSONObject();
		try {
			if (num == 0) {
				if (isClick) {
					json.put("gif", num);
				} else {
					json.put("gif", INITGIF);
				}
			} else {
				json.put("gif", num - 1);
			}
			json.put("mood", mood);
			json.put("weather", "weather");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mood = json.toString();
		return mood;
	}

	/**
	 * 初始化点的个数
	 */
	private void initDots() {

		imageViews = new ImageView[listLin.size()];
		group.removeAllViews();
		for (int i = 0; i < listLin.size(); i++) {
			ImageView imageView = new ImageView(home);
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(20, 20);
			lp.setMargins(20, 0, 20, 0);
			imageView.setLayoutParams(lp);

			imageViews[i] = imageView;
			if (i == 0) {
				// Ĭ��ѡ�е�һ��ͼƬ
				imageViews[i].setBackgroundResource(R.drawable.mood_click);
			} else {
				imageViews[i].setBackgroundResource(R.drawable.mood_unclick);
			}
			group.addView(imageViews[i]);
		}
	}

	// ������״̬�ı�ʱ����
	@Override
	public void onPageScrollStateChanged(int arg0) {
	}

	// ����ǰҳ�汻����ʱ����
	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

	// ���µ�ҳ�汻ѡ��ʱ����
	@Override
	public void onPageSelected(int arg0) {
		witch = arg0;
		// initDots();
		for (int i = 0; i < imageViews.length; i++) {
			imageViews[arg0].setBackgroundResource(R.drawable.mood_click);
			if (arg0 != i) {
				imageViews[i].setBackgroundResource(R.drawable.mood_unclick);
			}

		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.deleteBtn:

			if (listLin.size() <= 1 || witch == listLin.size()) {
				Toast.makeText(home, "最后一个城市不能删除", Toast.LENGTH_LONG).show();
			} else {
				InhaleView inhaView = (InhaleView) listLin.get(witch);
				inhaView.startAnimation(mReverse);

				inhaView.setmWeatherIm(new WeatherIm() {

					@Override
					public void WeatherImClick() {
						// TODO Auto-generated method stub
						listLin.remove(witch);
						adapter.setData(listLin);
						adapter.refreshAdapter(true);
						mJazzy.setAdapter(adapter);
						mJazzy.setPageMargin(30);
						mJazzy.clearAnimation();
						initDots();
					}
				});
			}

			break;
		case R.id.addBtn:

			Intent mIntent = new Intent();
			mIntent.setClass(home, ChangeCitiesActivity.class);
			startActivity(mIntent);
			// WeatherUtility.saveCity("北京", home);
			break;
		case R.id.home_gif:

			if (time.isGifClick) {
				isClick = true;
				if (num < gifList.size()) {
					if (num % 2 == 1) {
						mImageView1.setBackgroundResource(gifList.get(num));
					} else {
						mImageView2.setBackgroundResource(gifList.get(num));
					}

					mCenterX = mContainer.getWidth() / 2;
					mCenterY = mContainer.getHeight() / 2;
					getDepthZ();
					applyRotation(mStartAnimView, 0, 90);
					if (num == gifList.size() - 1) {
						num = 0;
						isAddNum = true;
					}
					if (isAddNum) {
						isAddNum = false;
					} else {
						num = num + 1;
					}
					time.isGifClick = false;
				}

			}
			moodTime.setMood(getJsonForMood());
			moodTime.setNum(num);
			if (moodTime.isSaveGif) {
				moodTime.isSaveGif = false;
				moodTime.start();
			}
			break;
		case R.id.pen_btn:
			Intent intent = new Intent(home, LineEditActivity.class);
			intent.putExtra("mood", getJsonForMood());
			startActivity(intent);
			// home.finish();
			break;
		case R.id.refresh_lin:
			isRefreshWeather = true;
			updateWeather();
			break;
		default:
			break;

		}
	}

	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		for (InhaleView view : listLin) {
			view.mBitmap.recycle();
		}
		// onDestroy();
		super.onDestroyView();
	}

	View mContainer = null;
	int mDuration = 200;
	float mCenterX = 0.0f;
	float mCenterY = 0.0f;
	float mDepthZ = 0.0f;
	int mIndex = 0;

	private void getDepthZ() {

		try {
			mDepthZ = 0;
			// mDepthZ = Math.min(mDepthZ, 300.0f);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void applyRotation(View animView, float startAngle, float toAngle) {
		float centerX = mCenterX;
		float centerY = mCenterY;
		Rotate3dAnimation rotation = new Rotate3dAnimation(startAngle, toAngle,
				centerX, centerY, mDepthZ, true);
		rotation.setDuration(mDuration);
		rotation.setFillAfter(true);
		rotation.setInterpolator(new AccelerateInterpolator());
		rotation.setAnimationListener(new DisplayNextView());

		animView.startAnimation(rotation);
	}

	/**
	 * This class listens for the end of the first half of the animation. It
	 * then posts a new action that effectively swaps the views when the
	 * container is rotated 90 degrees and thus invisible.
	 */
	private final class DisplayNextView implements Animation.AnimationListener {

		public void onAnimationStart(Animation animation) {
		}

		public void onAnimationEnd(Animation animation) {

			mContainer.post(new SwapViews());
		}

		public void onAnimationRepeat(Animation animation) {
		}
	}

	private final class SwapViews implements Runnable {
		@Override
		public void run() {
			mImageView1.setVisibility(View.GONE);
			mImageView2.setVisibility(View.GONE);

			mIndex++;
			if (0 == mIndex % 2) {
				mStartAnimView = mImageView1;
			} else {
				mStartAnimView = mImageView2;
			}

			mStartAnimView.setVisibility(View.VISIBLE);
			mStartAnimView.requestFocus();

			Rotate3dAnimation rotation = new Rotate3dAnimation(-90, 0,
					mCenterX, mCenterY, mDepthZ, false);

			rotation.setDuration(mDuration);
			rotation.setFillAfter(true);
			rotation.setInterpolator(new DecelerateInterpolator());
			mStartAnimView.startAnimation(rotation);
		}
	}
}

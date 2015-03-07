package com.nuannuan.mood.activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nuannuan.common.R;
import com.nuannuan.common.R.anim;
import com.nuannuan.common.activity.HomeActivity;
import com.nuannuan.common.custom.controls.JazzyViewPager;
import com.nuannuan.common.custom.controls.JazzyViewPager.TransitionEffect;
import com.nuannuan.mood.custom.controls.LineEditText;
import com.nuannuan.mood.custom.controls.MoodLin;
import com.nuannuan.mood.interfaces.TrendButtonIm;
import com.nuannuan.mood.utility.MoodUtility;
import com.nuannuan.star.adapter.RecordMoodAdapter;

public class RecordMoodsActivity extends Activity implements
		OnPageChangeListener, OnClickListener {

	private JazzyViewPager mJazzy;

	// private ViewPager vp;
	// private ViewPagerAdapter vpAdapter;
	private ArrayList<LinearLayout> list = new ArrayList<LinearLayout>();
	private ImageView[] imageViews = null;

	private long oldTime;
	private int day;
	private Context mContext;
	private LineEditText mEditText;
	private ImageView gif;
	private Animation animation; // 渐变动画
	private List<Integer> gifList = new ArrayList<Integer>();
	private int position = 0;
	// private List<Integer> gifList2 = new ArrayList<Integer>();
	// private List<Integer> listup2;
	private List<Integer> listup;
	private int imagePosition = 0;
	private List<Integer> listDown;
	private List<String> textList;
	private String JsonMoodStr = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 隐去标题栏（应用程序的名字）
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_record_moods);
		Intent mIntent = getIntent();

		// oldTime = mIntent.getLongExtra("oldtimes", -1);
		// 直接获取到最后一次的时间
		oldTime = MoodUtility.readTime(this);
		// Log.v("==========oldTime=============", ""+oldTime);
		// Log.v("==========newOL=============", ""+newOL);

		// Date mDate = new Date();
		// int days=mDate.getDay();

		Calendar mCalendar = Calendar.getInstance();
		day = mCalendar.get(Calendar.DAY_OF_MONTH);
		// Log.v("==========days=============", ""+days);
		// day = mIntent.getIntExtra("day", -1);
		mContext = this;
		initGifList1();
		addMoodUp1();
		addMoodDown1();
		addMoodText1();

		initAnim(gifList.get(0));

		ImageView GoLineEdit = (ImageView) findViewById(R.id.moods_pen_btn);
		GoLineEdit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				JsonMoodStr = getJsonForMood();
				Intent mIntent = new Intent(RecordMoodsActivity.this,
						LineEditActivity.class);
				mIntent.putExtra("mood", JsonMoodStr);
				startActivity(mIntent);
			}
		});
		//
		gif = (ImageView) findViewById(R.id.mood_img_bg);

		TextView mTextView = (TextView) findViewById(R.id.include_addmood_title);
		mTextView.setText("心情选择");

		RelativeLayout mButton = (RelativeLayout) findViewById(R.id.btn_add);
		mButton.setOnClickListener(this);
		setupJazziness(TransitionEffect.CubeOut);
		initDots();
		LinearLayout backBtn = (LinearLayout) findViewById(R.id.btn_back);
		backBtn.setOnClickListener(this);
	}

	private String getJsonForMood() {
		String mood = "";

		JSONObject json = new JSONObject();
		try {
			json.put("gif", imagePosition);
			json.put("mood", mood);
			json.put("weather", "weather");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mood = json.toString();
		return mood;
	}

	private void initGifList1() {

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

	private List<Integer> addMoodUp1() {
		listup = new ArrayList<Integer>();
		listup.add(R.drawable.md1_up);
		listup.add(R.drawable.md2_up);
		listup.add(R.drawable.md3_up);
		listup.add(R.drawable.md4_up);
		listup.add(R.drawable.md5_up);
		listup.add(R.drawable.md6_up);
		listup.add(R.drawable.md7_up);
		listup.add(R.drawable.md8_up);

		listup.add(R.drawable.md9_up);
		listup.add(R.drawable.md10_up);
		listup.add(R.drawable.md11_up);
		listup.add(R.drawable.md12_up);
		listup.add(R.drawable.md13_up);
		listup.add(R.drawable.md14_up);
		listup.add(R.drawable.md15_up);
		listup.add(R.drawable.md15_up);
		return listup;
	}

	private List<Integer> addMoodDown1() {
		listDown = new ArrayList<Integer>();
		listDown.add(R.drawable.md1_down);
		listDown.add(R.drawable.md2_down);
		listDown.add(R.drawable.md3_down);
		listDown.add(R.drawable.md4_down);
		listDown.add(R.drawable.md5_down);
		listDown.add(R.drawable.md6_down);
		listDown.add(R.drawable.md7_down);
		listDown.add(R.drawable.md8_down);

		listDown.add(R.drawable.md9_down);
		listDown.add(R.drawable.md10_down);
		listDown.add(R.drawable.md11_down);
		listDown.add(R.drawable.md12_down);
		listDown.add(R.drawable.md13_down);
		listDown.add(R.drawable.md14_down);
		listDown.add(R.drawable.md15_down);
		listDown.add(R.drawable.md15_down);
		return listDown;
	}

	private List<String> addMoodText1() {
		textList = new ArrayList<String>();
		String[] listData = mContext.getResources().getStringArray(
				R.array.gif_name);
		int count = listData.length;

		for (int i = 0; i < count; i++) {
			textList.add(listData[i]);
		}
		return textList;
	}

	private void setupJazziness(TransitionEffect effect) {
		mJazzy = (JazzyViewPager) findViewById(R.id.jazzy_pager);
		// TransitionEffect effect = TransitionEffect.valueOf("cubeout");
		mJazzy.setTransitionEffect(effect);
		int screenWidth = getWindowManager().getDefaultDisplay().getWidth();

		List<Integer> listDownLin = new ArrayList<Integer>();
		List<Integer> listupLin = new ArrayList<Integer>();
		List<String> listTxtLin = new ArrayList<String>();
		int j = 0;
		for (int i = 0; i < gifList.size(); i++) {

			listDownLin.add(listDown.get(i));
			listupLin.add(listup.get(i));
			listTxtLin.add(textList.get(i));

			if ((listDownLin.size()) % 8 == 0) {

				j = j + 1;
				MoodLin layout = new MoodLin(this, screenWidth, listupLin,
						listDownLin, listTxtLin, j);

				for (int k = 0; k < 8; k++) {
					listDownLin.remove(0);
					listupLin.remove(0);
					listTxtLin.remove(0);
				}

				layout.setmButtonIm(new TrendButtonIm() {

					@Override
					public void TrendonClick(View view) {
						// TODO Auto-generated method stub
						imagePosition = view.getId() - 1;
						Log.v("=====imagePosition=====", "" + imagePosition);
						int anim = gifList.get(imagePosition);
						initAnim(anim);
					}
				});

				list.add(layout);
			}
		}

		RecordMoodAdapter adapter = new RecordMoodAdapter(list, mJazzy);
		mJazzy.setAdapter(adapter);

		mJazzy.setPageMargin(30);
		// 绑定回调
		mJazzy.setOnPageChangeListener(this);
	}

	// 初始化动画
	private void initAnim(int anim) {
		// 城市箭头补间动画
		ImageView imageView = (ImageView) findViewById(R.id.mood_img_bg);
		imageView.setBackgroundResource(anim);
		AnimationDrawable ad = (AnimationDrawable) imageView.getBackground();
		ad.start();
	}

	/**
	 * 初始化底部
	 */
	private void initDots() {

		ViewGroup group = (ViewGroup) findViewById(R.id.guide_bg);

		imageViews = new ImageView[list.size()];

		for (int i = 0; i < list.size(); i++) {
			ImageView imageView = new ImageView(this);
			// LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
			// LinearLayout.LayoutParams.WRAP_CONTENT,
			// LinearLayout.LayoutParams.WRAP_CONTENT);
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(20, 20);
			lp.setMargins(20, 0, 20, 0);
			imageView.setLayoutParams(lp);

			imageViews[i] = imageView;
			if (i == 0) {
				// 默认选中第一张图片
				imageViews[i].setBackgroundResource(R.drawable.mood_click);
			} else {
				imageViews[i].setBackgroundResource(R.drawable.mood_unclick);
			}
			group.addView(imageViews[i]);
		}
	}

	// 当滑动状态改变时调用
	@Override
	public void onPageScrollStateChanged(int arg0) {
	}

	// 当当前页面被滑动时调用
	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

	// 当新的页面被选中时调用
	@Override
	public void onPageSelected(int arg0) {
		position = arg0;
		// 设置底部小点选中状态
		for (int i = 0; i < imageViews.length; i++) {
			imageViews[arg0].setBackgroundResource(R.drawable.mood_click);
			if (arg0 != i) {
				imageViews[i].setBackgroundResource(R.drawable.mood_unclick);
			}
			if (arg0 == 3) {
				// 设置导航按钮可见
				// mButton.setVisibility(View.VISIBLE);
			} else {
				// 设置导航按钮不可见
				// mButton.setVisibility(View.INVISIBLE);
			}
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_add:
			JsonMoodStr = getJsonForMood();

			MoodUtility.WriteJsonMood(mContext, oldTime, day, JsonMoodStr);
			Intent intent = new Intent(mContext, HomeActivity.class);
			// Log.v("=======tab=======", ""+tab);
			intent.putExtra("tab", 1);
			mContext.startActivity(intent);
			break;
		case R.id.all_back:
			finish();
			break;
		case R.id.btn_back:
			finish();
			break;
		default:
			break;
		}
	}

}

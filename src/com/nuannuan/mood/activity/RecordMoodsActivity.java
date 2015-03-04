package com.nuannuan.mood.activity;

import java.util.ArrayList;
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
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nuannuan.common.activity.HomeActivity;
import com.nuannuan.common.custom.controls.JazzyViewPager;
import com.nuannuan.common.custom.controls.JazzyViewPager.TransitionEffect;
import com.nuannuan.mood.adapter.MoodsAdapter;
import com.nuannuan.mood.custom.controls.LineEditText;
import com.nuannuan.mood.custom.controls.MoodLin;
import com.nuannuan.mood.interfaces.TrendButtonIm;
import com.nuannuan.mood.utilitys.MoodUtility;
import com.nuannuan.star.adapter.RecordMoodAdapter;
import com.scau.feelingmusic.R;
import com.scau.feelingmusic.R.anim;

public class RecordMoodsActivity extends Activity implements
		OnPageChangeListener {

	private JazzyViewPager mJazzy;

	// private ViewPager vp;
	// private ViewPagerAdapter vpAdapter;
	private ArrayList<LinearLayout> list = new ArrayList<LinearLayout>();
	private ImageView[] imageViews = null;
	private Button mButton;

	private long oldTime;
	private int day;
	private Context mContext;
	private LineEditText mEditText;
	private ImageView gif;
	private Animation animation; // 渐变动画
	private List<Integer> gifList = new ArrayList<Integer>();
	private int position = 0;
	private List<Integer> gifList2 = new ArrayList<Integer>();
	private List<Integer> listup2;
	private List<Integer> listup;
	private int imagePosition = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 隐去标题栏（应用程序的名字）
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_record_moods);
		Intent mIntent = getIntent();

		oldTime = mIntent.getLongExtra("oldtimes", -1);
		day = mIntent.getIntExtra("day", -1);
		mContext = this;
		initGifList1();
		initGifList2();
		// mEditText = (LineEditText) findViewById(R.id.edit_mood);
		initAnim(gifList.get(0));
		Button GoLineEdit = (Button) findViewById(R.id.add_line_btn);
		GoLineEdit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent mIntent = new Intent(RecordMoodsActivity.this,
						LineEditActivity.class);
				startActivity(mIntent);
			}
		});

		Button mButton = (Button) findViewById(R.id.add_mood_btn);
		gif = (ImageView) findViewById(R.id.gif1);

		mButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String mood = "";

				JSONObject json = new JSONObject();
				try {
					if (position == 0) {
						json.put("Drawable", listup.get(imagePosition));
						json.put("gif", imagePosition);
					} else {
						json.put("Drawable", listup2.get(imagePosition));
						json.put("gif", imagePosition + 7);
					}

					json.put("mood", mood);
					json.put("weather", "weather");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				MoodUtility.WriteJsonMood(mContext, oldTime, day,
						json.toString());
				Intent intent = new Intent(mContext, HomeActivity.class);
				// Log.v("=======tab=======", ""+tab);
				intent.putExtra("tab", 1);
				mContext.startActivity(intent);
			}
		});
		setupJazziness(TransitionEffect.CubeOut);
		initDots();

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
		return listup;
	}

	private List<Integer> addMoodDown1() {
		List<Integer> listDown = new ArrayList<Integer>();
		listDown.add(R.drawable.md1_down);
		listDown.add(R.drawable.md2_down);
		listDown.add(R.drawable.md3_down);
		listDown.add(R.drawable.md4_down);
		listDown.add(R.drawable.md5_down);
		listDown.add(R.drawable.md6_down);
		listDown.add(R.drawable.md7_down);
		listDown.add(R.drawable.md8_down);
		return listDown;
	}

	private List<String> addMoodText1() {
		List<String> textList = new ArrayList<String>();
		textList.add("幸福");
		textList.add("生气");
		textList.add("泪奔");
		textList.add("耶");
		textList.add("晕");
		textList.add("喜欢");
		textList.add("砍人");
		textList.add("崇拜");
		return textList;
	}

	private void initGifList2() {
		gifList2.add(anim.gif_md9);
		gifList2.add(anim.gif_md10);
		gifList2.add(anim.gif_md11);
		gifList2.add(anim.gif_md12);
		gifList2.add(anim.gif_md13);
		gifList2.add(anim.gif_md14);
		gifList2.add(anim.gif_md15);
		gifList2.add(anim.gif_md15);
	}

	private List<Integer> addMoodUp2() {
		listup2 = new ArrayList<Integer>();
		listup2.add(R.drawable.md9_up);
		listup2.add(R.drawable.md10_up);
		listup2.add(R.drawable.md11_up);
		listup2.add(R.drawable.md12_up);
		listup2.add(R.drawable.md13_up);
		listup2.add(R.drawable.md14_up);
		listup2.add(R.drawable.md15_up);
		listup2.add(R.drawable.md15_up);
		return listup2;
	}

	private List<Integer> addMoodDown2() {
		List<Integer> listDown2 = new ArrayList<Integer>();
		listDown2.add(R.drawable.md9_down);
		listDown2.add(R.drawable.md10_down);
		listDown2.add(R.drawable.md11_down);
		listDown2.add(R.drawable.md12_down);
		listDown2.add(R.drawable.md13_down);
		listDown2.add(R.drawable.md14_down);
		listDown2.add(R.drawable.md15_down);
		listDown2.add(R.drawable.md15_down);
		return listDown2;
	}

	private List<String> addMoodText2() {
		List<String> textList2 = new ArrayList<String>();
		textList2.add("无语");
		textList2.add("开心");
		textList2.add("乖乖");
		textList2.add("尴尬");
		textList2.add("发呆");
		textList2.add("大哭");
		textList2.add("鄙视");
		textList2.add("鄙视");
		return textList2;
	}

	private void setupJazziness(TransitionEffect effect) {
		mJazzy = (JazzyViewPager) findViewById(R.id.jazzy_pager);
		// TransitionEffect effect = TransitionEffect.valueOf("cubeout");
		mJazzy.setTransitionEffect(effect);
		int screenWidth = getWindowManager().getDefaultDisplay().getWidth();

		MoodLin layout1 = new MoodLin(this, screenWidth, addMoodUp1(),
				addMoodDown1(), addMoodText1());

		layout1.setmButtonIm(new TrendButtonIm() {

			@Override
			public void TrendonClick(View view) {
				// TODO Auto-generated method stub
				imagePosition = view.getId();
				Log.v("=====imagePosition=====", "" + imagePosition);
				int anim = gifList.get(imagePosition);
				initAnim(anim);
			}
		});

		MoodLin layout2 = new MoodLin(this, screenWidth, addMoodUp2(),
				addMoodDown2(), addMoodText2());

		layout2.setmButtonIm(new TrendButtonIm() {

			@Override
			public void TrendonClick(View view) {
				// TODO Auto-generated method stub
				imagePosition = view.getId();
				int anim = gifList2.get(imagePosition);
				initAnim(anim);
			}
		});

		list.add(layout1);
		list.add(layout2);

		RecordMoodAdapter adapter = new RecordMoodAdapter(list, mJazzy);
		mJazzy.setAdapter(adapter);

		mJazzy.setPageMargin(30);
		// 绑定回调
		mJazzy.setOnPageChangeListener(this);
	}

	// 初始化动画
	private void initAnim(int anim) {
		// 城市箭头补间动画
		ImageView imageView = (ImageView) findViewById(R.id.gif1);
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
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.WRAP_CONTENT,
					LinearLayout.LayoutParams.WRAP_CONTENT);
			lp.setMargins(20, 0, 20, 0);
			imageView.setLayoutParams(lp);

			imageViews[i] = imageView;
			if (i == 0) {
				// 默认选中第一张图片
				imageViews[i].setBackgroundResource(R.drawable.splash_doc_blue);
			} else {
				imageViews[i].setBackgroundResource(R.drawable.splash_doc_gray);
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
			imageViews[arg0].setBackgroundResource(R.drawable.splash_doc_blue);
			if (arg0 != i) {
				imageViews[i].setBackgroundResource(R.drawable.splash_doc_gray);
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

}

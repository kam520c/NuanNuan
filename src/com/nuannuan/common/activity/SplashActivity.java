package com.nuannuan.common.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nuannuan.common.adapter.SplashAdapter;
import com.nuannuan.common.custom.controls.JazzyViewPager;
import com.nuannuan.common.custom.controls.JazzyViewPager.TransitionEffect;
import com.scau.feelingmusic.R;

public class SplashActivity extends Activity implements OnPageChangeListener,
		OnClickListener {

	private JazzyViewPager mJazzy;

	// private ViewPager vp;
	// private ViewPagerAdapter vpAdapter;
	private ArrayList<ImageView> list = new ArrayList<ImageView>();
	private ImageView[] imageViews = null;
	private Button mButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 隐去标题栏（应用程序的名字）
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_splash);
		setupJazziness(TransitionEffect.CubeOut);

		mButton = (Button) findViewById(R.id.guide_btn);

		mButton.setOnClickListener(this);
		initDots();
		
	}

	private void setupJazziness(TransitionEffect effect) {
		mJazzy = (JazzyViewPager) findViewById(R.id.jazzy_pager);
		// TransitionEffect effect = TransitionEffect.valueOf("cubeout");
		mJazzy.setTransitionEffect(effect);

		// 图片1
		ImageView img1 = new ImageView(SplashActivity.this);
		img1.setBackgroundResource(R.drawable.splash01);
		// 图片2
		ImageView img2 = new ImageView(SplashActivity.this);
		img2.setBackgroundResource(R.drawable.splash02);
		// 图片3
		ImageView img3 = new ImageView(SplashActivity.this);
		img3.setBackgroundResource(R.drawable.splash03);
		// 图片4
		ImageView img4 = new ImageView(SplashActivity.this);
		img4.setBackgroundResource(R.drawable.splash04);

		list.add(img1);
		list.add(img2);
		list.add(img3);
		list.add(img4);

		SplashAdapter adapter = new SplashAdapter(list, mJazzy);
		mJazzy.setAdapter(adapter);
		mJazzy.setPageMargin(30);
		// 绑定回调
		mJazzy.setOnPageChangeListener(this);
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
		// 设置底部小点选中状态
		for (int i = 0; i < imageViews.length; i++) {
			imageViews[arg0].setBackgroundResource(R.drawable.splash_doc_blue);
			if (arg0 != i) {
				imageViews[i].setBackgroundResource(R.drawable.splash_doc_gray);
			}
			if (arg0 == 3) {
				// 设置导航按钮可见
				mButton.setVisibility(View.VISIBLE);
			} else {
				// 设置导航按钮不可见
				mButton.setVisibility(View.INVISIBLE);
			}
		}
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		goHome();
	}

	private void goHome() {
		 Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
		 startActivity(intent);
		 SplashActivity.this.finish();
	}
}

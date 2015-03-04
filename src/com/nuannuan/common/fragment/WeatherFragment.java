package com.nuannuan.common.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.nuannuan.common.activity.HomeActivity;
import com.nuannuan.common.custom.controls.InhaleView;
import com.nuannuan.common.custom.controls.JazzyViewPager;
import com.nuannuan.common.custom.controls.JazzyViewPager.TransitionEffect;
import com.nuannuan.common.utilitys.Rotate3dAnimation;
import com.nuannuan.mood.activity.LineEditActivity;
import com.nuannuan.weather.activity.AddCityActivity;
import com.nuannuan.weather.adapter.WeatherAdapter;
import com.nuannuan.weather.interfaces.WeatherIm;
import com.scau.feelingmusic.R;
import com.scau.feelingmusic.R.anim;

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
	private String weather=null;

	public WeatherFragment(ArrayList<InhaleView> listInhale) {
		listInhale = listLin;
	}

	public WeatherFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View weatherLayout = inflater.inflate(R.layout.fragment_weather,
				container, false);
		home = (HomeActivity) getActivity();
		initGifList();

		initView(weatherLayout);
		setupJazziness(TransitionEffect.Standard);
		initDots(weatherLayout);

		return weatherLayout;
	}

	private void initView(View view) {
		mJazzy = (JazzyViewPager) view.findViewById(R.id.jazzy_pager);
		deleteBtn = (Button) view.findViewById(R.id.deleteBtn);
		deleteBtn.setOnClickListener(this);
		Button AddBtn = (Button) view.findViewById(R.id.addBtn);
		AddBtn.setOnClickListener(this);
		mImageView1 = (LinearLayout) view.findViewById(R.id.home_gif);
		mImageView1.setOnClickListener(this);
		initAnim(gifList.get(9));
		mImageView2 = (LinearLayout) view.findViewById(R.id.home_gif2);
		mContainer = view.findViewById(R.id.container);
		mStartAnimView = mImageView1;
		ImageView pen = (ImageView) view.findViewById(R.id.pen_btn);
		pen.setOnClickListener(this);

	}

	private void setupJazziness(TransitionEffect effect) {
		mJazzy.setTransitionEffect(effect);

		int screenWidth = home.getWindowManager().getDefaultDisplay()
				.getWidth();
		int screenHeigth = home.getWindowManager().getDefaultDisplay()
				.getHeight();
		InhaleView mInhaleView1 = new InhaleView(home, 1, screenWidth,
				screenHeigth);
		InhaleView mInhaleView2 = new InhaleView(home, 2, screenWidth,
				screenHeigth);
		InhaleView mInhaleView3 = new InhaleView(home, 3, screenWidth,
				screenHeigth);
		InhaleView mInhaleView4 = new InhaleView(home, 4, screenWidth,
				screenHeigth);

		listLin.add(mInhaleView1);
		listLin.add(mInhaleView2);
		listLin.add(mInhaleView3);
		listLin.add(mInhaleView4);

		WeatherAdapter adapter = new WeatherAdapter(listLin, mJazzy);
		mJazzy.setAdapter(adapter);
		mJazzy.setPageMargin(30);
		// �󶨻ص�
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
	 * ��ʼ���ײ�
	 */
	private void initDots(View view) {

		ViewGroup group = (ViewGroup) view.findViewById(R.id.guide_bg);

		imageViews = new ImageView[listLin.size()];

		for (int i = 0; i < listLin.size(); i++) {
			ImageView imageView = new ImageView(home);
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.WRAP_CONTENT,
					LinearLayout.LayoutParams.WRAP_CONTENT);
			lp.setMargins(20, 0, 20, 0);
			imageView.setLayoutParams(lp);

			imageViews[i] = imageView;
			if (i == 0) {
				// Ĭ��ѡ�е�һ��ͼƬ
				imageViews[i].setBackgroundResource(R.drawable.splash_doc_blue);
			} else {
				imageViews[i].setBackgroundResource(R.drawable.splash_doc_gray);
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
		for (int i = 0; i < imageViews.length; i++) {
			imageViews[arg0].setBackgroundResource(R.drawable.splash_doc_blue);
			if (arg0 != i) {
				imageViews[i].setBackgroundResource(R.drawable.splash_doc_gray);
			}

		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.deleteBtn:

			if (listLin.size() <= 1) {
				Toast.makeText(home, "最后一个城市不能删除", Toast.LENGTH_LONG).show();
			} else {
				InhaleView inhaView = (InhaleView) listLin.get(witch);
				inhaView.startAnimation(mReverse);

				inhaView.setmWeatherIm(new WeatherIm() {

					@Override
					public void WeatherImClick() {
						// TODO Auto-generated method stub
						listLin.remove(witch);
						WeatherAdapter adapter = new WeatherAdapter(listLin,
								mJazzy);
						mJazzy.setAdapter(adapter);
						mJazzy.setPageMargin(30);
						mJazzy.clearAnimation();

						WeatherFragment fragment = new WeatherFragment(listLin);

						android.support.v4.app.FragmentTransaction transaction = home
								.getSupportFragmentManager().beginTransaction();
						transaction.replace(R.id.content, fragment);

					}
				});
			}

			break;
		case R.id.addBtn:
			Intent mIntent = new Intent();
			mIntent.setClass(home, AddCityActivity.class);
			startActivity(mIntent);
			break;
		case R.id.home_gif:
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
				num = num + 1;
				// initAnim(gifList.get(num));
				if (num > gifList.size()) {
					num = 0;
				}
			}
			break;
		case R.id.pen_btn:
			Intent intent = new Intent(home, LineEditActivity.class);
			intent.putExtra("num", num);
			intent.putExtra("weather", weather);
			startActivity(intent);
			break;
		default:
			break;
		}
	}

	View mContainer = null;
	int mDuration = 300;
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

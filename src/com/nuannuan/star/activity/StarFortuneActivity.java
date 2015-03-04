package com.nuannuan.star.activity;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nuannuan.common.custom.controls.JazzyViewPager;
import com.nuannuan.common.custom.controls.JazzyViewPager.TransitionEffect;
import com.nuannuan.star.adapter.StarAdapter;
import com.nuannuan.star.custom.controls.StarDayLin;
import com.nuannuan.star.custom.controls.StarLoveLin;
import com.nuannuan.star.custom.controls.StarMonthLin;
import com.nuannuan.star.custom.controls.StarTomorrowLin;
import com.nuannuan.star.custom.controls.StarWeekLin;
import com.nuannuan.star.custom.controls.StarYearLin;
import com.scau.feelingmusic.R;

public class StarFortuneActivity extends Activity implements
		OnPageChangeListener {

	private JazzyViewPager mJazzy;
	private ArrayList<LinearLayout> listLin = new ArrayList<LinearLayout>();
	private ImageView[] imageViews = null;
	private Button mButton;
	private Intent mIntent;
	private String str1;
	private String str2;
	private String str3;
	private String str4;
	private String str5;
	private String str6;

	private String json;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_star_fortune);

		mIntent = getIntent();
		json = mIntent.getStringExtra("json");
		getListforLin();

		setupJazziness(TransitionEffect.CubeOut);
		initDots();

	}

	private void getListforLin() {
		try {
			JSONArray json1 = new JSONArray(json);
			str1 = (String) json1.get(0);
			str2 = (String) json1.get(1);
			str3 = (String) json1.get(2);
			str4 = (String) json1.get(3);
			str5 = (String) json1.get(4);
			str6 = (String) json1.get(5);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void setupJazziness(TransitionEffect effect) {
		mJazzy = (JazzyViewPager) findViewById(R.id.jazzy_pager);
		// TransitionEffect effect = TransitionEffect.valueOf("cubeout");
		mJazzy.setTransitionEffect(effect);

		StarDayLin ll1 = new StarDayLin(this, str1);
		StarTomorrowLin ll2 = new StarTomorrowLin(this, str2);
		StarWeekLin ll3 = new StarWeekLin(this, str3);
		StarMonthLin ll4 = new StarMonthLin(this, str4);
		StarYearLin ll5 = new StarYearLin(this, str5);
		StarLoveLin ll6 = new StarLoveLin(this, str6);

	
		// ͼƬ1

		listLin.add(ll1);
		listLin.add(ll2);
		listLin.add(ll3);
		listLin.add(ll4);
		listLin.add(ll5);
		listLin.add(ll6);

		StarAdapter adapter = new StarAdapter(listLin, mJazzy);
		mJazzy.setAdapter(adapter);
		mJazzy.setPageMargin(30);
		// �󶨻ص�
		mJazzy.setOnPageChangeListener(this);
	}

	/**
	 * ��ʼ���ײ�
	 */
	private void initDots() {

		ViewGroup group = (ViewGroup) findViewById(R.id.guide_bg);

		imageViews = new ImageView[listLin.size()];

		for (int i = 0; i < listLin.size(); i++) {
			ImageView imageView = new ImageView(this);
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
		// ���õײ�С��ѡ��״̬
		for (int i = 0; i < imageViews.length; i++) {
			imageViews[arg0].setBackgroundResource(R.drawable.splash_doc_blue);
			if (arg0 != i) {
				imageViews[i].setBackgroundResource(R.drawable.splash_doc_gray);
			}

		}
	}

	private void goHome() {
		// Intent intent = new Intent(AllActivity.this, HomeActivity.class);
		// startActivity(intent);
		// AllActivity.this.finish();
	}
}

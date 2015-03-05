package com.nuannuan.star.activity;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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

@SuppressLint("ResourceAsColor")
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

	private TextView todayTxt;// 设置今日运势的标题背景;
	private TextView tomorrowTxt;
	private TextView weekTxt;
	private TextView monthTxt;
	private TextView yearTxt;
	private TextView loveTxt;// 设置爱情运势的标题背景

	private TextView dayTitle;// 设置今日运势的标题背景;
	private TextView weekTitle;
	private TextView monthTitle;
	private TextView yearTitle;
	private TextView tomorrowTitle;
	
	private TextView starTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_star_fortune);

		mIntent = getIntent();
		json = mIntent.getStringExtra("json");
		getListforLin();

		setupJazziness(TransitionEffect.CubeOut);
//		initDots(str1);
		initView();
		initDots(str1);
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
		// 设置手势滑动的监听器
		mJazzy.setOnPageChangeListener(this);
	}

	/**
	 * ��ʼ���ײ�
	 */
	private void initDots(String str) {

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
//				imageViews[i].setBackgroundResource(R.drawable.splash_doc_blue);
				try {
					changedViewtitle(0,str);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
//				imageViews[i].setBackgroundResource(R.drawable.splash_doc_gray);
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

		String str=null;
		int i=arg0+1;
		switch(arg0){
		case 0:str=str1;break;
		case 1:str=str2;break;
		case 2:str=str3;break;
		case 3:str=str4;break;
		case 4:str=str5;break;
		case 5:str=str6;break;
		default:str=str1;break;
		}
		try {
//			str ="str" + "i";
			changedViewtitle(arg0,str);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		for (int i = 0; i < imageViews.length; i++) {
//			imageViews[arg0].setBackgroundResource(R.drawable.splash_doc_blue);
//			if (arg0 != i) {
//				imageViews[i].setBackgroundResource(R.drawable.splash_doc_gray);
//			}
//
//		}
	}

	/**
	 * 初始化控件
	 */
	private void initView() {
		todayTxt = (TextView) findViewById(R.id.title_today);// 设置今日运势的标题背景;
		todayTxt.setBackgroundResource(R.drawable.star_title_button);
		tomorrowTxt = (TextView) findViewById(R.id.title_tomorrow);
		weekTxt = (TextView) findViewById(R.id.title_week);
		monthTxt = (TextView) findViewById(R.id.title_month);
		yearTxt = (TextView) findViewById(R.id.title_year);
		loveTxt = (TextView) findViewById(R.id.title_love);// 设置爱情运势的标题背景

		yearTitle = (TextView) findViewById(R.id.year);
		monthTitle = (TextView) findViewById(R.id.month);
		dayTitle = (TextView) findViewById(R.id.day);
		weekTitle = (TextView) findViewById(R.id.week);
		starTitle = (TextView) findViewById(R.id.star);
	}

	private void changedViewtitle(int i, String str) throws JSONException {
		// TODO Auto-generated method stub

		// TextView text220 = null;
		 String year;
		 String month;
		 String week;
		 String day;
		 String date = null;
		// TextView text222;
		// TextView text223;
		// TextView text224;
		// TextView text225;
		 JSONObject name = new JSONObject();
		 JSONArray arr = new JSONArray(str);
		 String starname = null;
		switch (i) {
		case 0:
			 name = (JSONObject) arr.get(10);
			 starname = name.get("cn").toString();
			 date = arr.getString(11).toString();
			 year = date.substring(0, 4);
			 month = date.substring(5, 7);
			 day = date.substring(8, 10);
			 yearTitle.setText(year + "/");
			 monthTitle.setText(month + "/");
			 dayTitle.setText(day);
			 weekTitle.setText("星期六");
			todayTxt.setBackgroundResource(R.drawable.star_title_button);
//			todayTxt.setTextColor(R.color.white);
			tomorrowTxt.setBackgroundResource(R.color.white);
			weekTxt.setBackgroundResource(R.color.white);
			monthTxt.setBackgroundResource(R.color.white);
			yearTxt.setBackgroundResource(R.color.white);
			loveTxt.setBackgroundResource(R.color.white);

			break;
		case 1:
			 name = (JSONObject) arr.get(10);
			 date = arr.getString(11).toString();
			 year = date.substring(0, 4);
			 month = date.substring(5, 7);
			 day = date.substring(8, 10);
			 yearTitle.setText(year + "/");
			 monthTitle.setText(month + "/");
			 dayTitle.setText(day);
			 weekTitle.setText("星期六");
			todayTxt.setBackgroundResource(R.color.white);
			weekTxt.setBackgroundResource(R.color.white);
			monthTxt.setBackgroundResource(R.color.white);
			yearTxt.setBackgroundResource(R.color.white);
			loveTxt.setBackgroundResource(R.color.white);
			tomorrowTxt.setBackgroundResource(R.drawable.star_title_button);
//			tomorrowTxt.setTextColor(R.color.white);
			break;
		case 2:

			 name = (JSONObject) arr.get(8);
			 date = arr.getString(9).toString();
			 year = date.substring(2, 12);
			 week = date.substring(15, 25);
			 yearTitle.setText(year + "/");
			 weekTitle.setText(week);
			 weekTxt.setBackgroundResource(R.drawable.star_title_button);

			todayTxt.setBackgroundResource(R.color.white);
			weekTxt.setBackgroundResource(R.drawable.star_title_button);
//			weekTxt.setTextColor(R.color.white);
			monthTxt.setBackgroundResource(R.color.white);
			yearTxt.setBackgroundResource(R.color.white);
			loveTxt.setBackgroundResource(R.color.white);
			tomorrowTxt.setBackgroundResource(R.color.white);
			break;
		case 3:

			 name = (JSONObject) arr.get(5);
			 date = arr.getString(6).toString();
			 year = date.substring(2, 12);
			 week = date.substring(15, 25);
			 yearTitle.setText(year + "/");
			 weekTitle.setText(week);
			monthTxt.setBackgroundResource(R.drawable.star_title_button);
//			monthTxt.setTextColor(R.color.white);
			todayTxt.setBackgroundResource(R.color.white);
			weekTxt.setBackgroundResource(R.color.white);
			yearTxt.setBackgroundResource(R.color.white);
			loveTxt.setBackgroundResource(R.color.white);
			tomorrowTxt.setBackgroundResource(R.color.white);
			break;
		case 4:

			 name = (JSONObject) arr.get(6);
			 date = arr.getString(7).toString();
			 year = date.substring(2, 12);
			 week = date.substring(15, 25);
			 yearTitle.setText(year + "/");
			 weekTitle.setText(week);
			yearTxt.setBackgroundResource(R.drawable.star_title_button);
//			yearTxt.setTextColor(R.color.white);
			monthTxt.setBackgroundResource(R.color.white);
			todayTxt.setBackgroundResource(R.color.white);
			weekTxt.setBackgroundResource(R.color.white);
			loveTxt.setBackgroundResource(R.color.white);
			tomorrowTxt.setBackgroundResource(R.color.white);
			break;
		case 5:
			name = (JSONObject) arr.get(3);
			date = arr.getString(4).toString();
			 year = date.substring(2, 12);
			 week = date.substring(15, 25);
			 yearTitle.setText(year + "/");
			 weekTitle.setText(week);
			loveTxt.setBackgroundResource(R.drawable.star_title_button);
//			loveTxt.setTextColor(R.color.white);
			yearTxt.setBackgroundResource(R.color.white);
			monthTxt.setBackgroundResource(R.color.white);
			todayTxt.setBackgroundResource(R.color.white);
			weekTxt.setBackgroundResource(R.color.white);
			// loveTxt.setBackgroundResource(R.color.white);
			tomorrowTxt.setBackgroundResource(R.color.white);
			break;
		default:
			 name = (JSONObject) arr.get(10);
			 starname = name.get("cn").toString();
			 date = arr.getString(11).toString();
			 year = date.substring(0, 4);
			 month = date.substring(5, 7);
			 day = date.substring(8, 10);
			 yearTitle.setText(year + "/");
			 monthTitle.setText(month + "/");
			 dayTitle.setText(day);
			todayTxt.setBackgroundResource(R.drawable.star_title_button);
//			todayTxt.setTextColor(R.color.white);
			tomorrowTxt.setBackgroundResource(R.color.white);
			weekTxt.setBackgroundResource(R.color.white);
			monthTxt.setBackgroundResource(R.color.white);
			yearTxt.setBackgroundResource(R.color.white);
			loveTxt.setBackgroundResource(R.color.white);

			break;
		}
		// text220.setBackgroundResource(R.drawable.star_title_button);
		// text220.setTextColor(android.graphics.Color.WHITE);
//		 TextView text221 = (TextView) findViewById(R.id.star);// 设置星座背景图
		starname = name.get("cn").toString();
		Log.v("==============arr==============", ""+arr);
//		Log.v("==============starname==============", ""+starname);
//		starTitle.setText(starname + i);
		if (starname.equals("白羊座"))
			 starTitle.setBackgroundResource(R.drawable.star_baiyang);
		 else if (starname.equals("金牛座"))
			 starTitle.setBackgroundResource(R.drawable.star_jinniu);
		 else if (starname.equals("双子座"))
			 starTitle.setBackgroundResource(R.drawable.star_shuangzi);
		 else if (starname.equals("巨蟹座"))
			 starTitle.setBackgroundResource(R.drawable.star_juxie);
		 else if (starname.equals("狮子座"))
			 starTitle.setBackgroundResource(R.drawable.star_shizi);
		 else if (starname.equals("处女座"))
			 starTitle.setBackgroundResource(R.drawable.star_chunv);
		 else if (starname.equals("天秤座"))
			 starTitle.setBackgroundResource(R.drawable.star_tianping);
		 else if (starname.equals("天蝎座"))
			 starTitle.setBackgroundResource(R.drawable.star_tianxie);
		 else if (starname.equals("射手座"))
			 starTitle.setBackgroundResource(R.drawable.star_sheshou);
		 else if (starname.equals("魔羯座"))
			 starTitle.setBackgroundResource(R.drawable.star_mojie);
		 else if (starname.equals("水瓶座"))
			 starTitle.setBackgroundResource(R.drawable.star_shuiping);
		 else if (starname.equals("双鱼座"))
			 starTitle.setBackgroundResource(R.drawable.star_shuangyu);
		else
			starTitle.setBackgroundResource(R.drawable.star_shuangyu);
			
	}

	private void goHome() {
		// Intent intent = new Intent(AllActivity.this, HomeActivity.class);
		// startActivity(intent);
		// AllActivity.this.finish();
	}

	// @Override
	// public void onClick(View v) {
	// // TODO Auto-generated method stub
	// switch (v.getId()) {
	// case R.id.title_today:
	// mJazzy.setObjectForPosition(null,0);
	// changedViewtitle(0);
	// break;
	// case R.id.title_tomorrow:
	// mJazzy.setObjectForPosition(null,1);
	// changedViewtitle(1);
	// break;
	// case R.id.title_week:
	// mJazzy.setObjectForPosition(null,2);
	// changedViewtitle(2);
	// break;
	// case R.id.title_month:
	// mJazzy.setObjectForPosition(null,3);
	// changedViewtitle(3);
	// break;
	// case R.id.title_year:
	// // mJazzy.setObjectForPosition(null,4);
	// changedViewtitle(4);
	// break;
	// case R.id.title_love:
	// // mJazzy.setObjectForPosition(null,5);
	// changedViewtitle(5);
	// break;
	// default:
	// break;
	// }
	// }
}

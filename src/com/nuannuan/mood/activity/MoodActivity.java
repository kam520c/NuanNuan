package com.nuannuan.mood.activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nuannuan.common.R;
import com.nuannuan.common.utility.AnimationUtil;
import com.nuannuan.mood.custom.controls.TrendView;
import com.nuannuan.mood.interfaces.TrendButtonIm;
import com.nuannuan.mood.utility.MoodUtility;
import com.nuannuan.star.constants.MoodConstants;

public class MoodActivity extends Activity {
	private TrendView treview;
	private Context mContext;
	private int screenWidth, screenheight;
	private String json;
	private long time;
	private boolean isNowMoth = false;;
	private ArrayList<Integer> listup;
	private ImageView mView1, mView2;
	private ArrayList<Integer> arrayList;
	private Animation ani1, ani2;
	private int count = 0;
	private int count2 = 4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 隐去标题栏（应用程序的名字）
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_mood);
		mContext = this;
		initCloud();
		Intent mIntent = getIntent();
		json = "";
		json = mIntent.getStringExtra("json");
		time = mIntent.getLongExtra("time", -1);

		screenWidth = getWindowManager().getDefaultDisplay().getWidth(); // ��Ļ�?���أ��磺480px��
		screenheight = getWindowManager().getDefaultDisplay().getHeight();

		treview = (TrendView) findViewById(R.id.trendView);
		// mContainer = findViewById(R.id.container);
		treview.setWidthHeight(screenWidth, MoodConstants.Left);
		mView1 = (ImageView) findViewById(R.id.mood_cloud1);
		mView2 = (ImageView) findViewById(R.id.mood_cloud2);

		mView1.setBackgroundResource(R.drawable.mood_cloud_angry);
		mView2.setBackgroundResource(R.drawable.mood_cloud_speechless);

		TextView mTextView = (TextView) findViewById(R.id.include_mood_title);
		mTextView.setText("心情轨迹");

		ani1 = AnimationUtil.getTranslateAm(-300, screenWidth,
				screenheight / 7, screenheight / 7, 3900);
		mView1.startAnimation(ani1);

		ani1.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub

				count++;
				if (count > 4) {
					count = 0;
				}
				mView1.clearAnimation();
				mView1.setBackgroundResource(arrayList.get(count));
				mView1.startAnimation(ani1);

			}
		});

		ani2 = AnimationUtil.getTranslateAm(screenWidth, -300,
				screenheight * 3 / 8, screenheight * 3 / 8, 4900);
		mView2.startAnimation(ani2);
		ani2.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub

				count2--;
				if (count2 < 0) {
					count2 = 4;
				}
				mView2.clearAnimation();
				mView2.setBackgroundResource(arrayList.get(count2));
				mView2.startAnimation(ani2);

			}
		});

		addMoodUp1();

		RelativeLayout mButton = (RelativeLayout) findViewById(R.id.btn_refresh);
		mButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				// Date mDate = new Date();
				//
				// mDate.setYear(113);
				// mDate.setMonth(3);
				// mDate.setDate(3);
				// MoodUtility.insertDate(mContext, mDate.getTime());
				// JSONArray array = new JSONArray();
				//
				// for (int k = 0; k < 28; k++) {
				// JSONObject obj = new JSONObject();
				// JSONObject json = new JSONObject();
				// try {
				// if (k >= 15) {
				// json.put("gif", 12);
				// json.put("mood", "我好伤心，呜呜");
				// json.put("weather", "天气晴 10°/17°");
				// } else {
				// json.put("gif", k);
				// json.put("mood",
				// "我很开心啊，哈哈......gdhfhfghfgjgsh说过话的哈的发货的发货的俄国和人格和任何和任何人和和任何人和话的哈的发货的发货的俄国和人格和任何和任何人和和任话的哈的发货的发货的俄国和人格和任何和任何人和和任话的哈的发货的发货的俄国和人格和任何和任何人和和任话的哈的发货的发货的俄国和人格和任何和任何人和和任话的哈的发货的发货的俄国和人格和任何和任何人和和任话的哈的发货的发货的俄国和人格和任何和任何人和和任话的哈的发货的发货的俄国和人格和任何和任何人和和任话的哈的发货的发货的俄国和人格和任何和任何人和和任话的哈的发货的发货的俄国和人格和任何和任何人和和任");
				// json.put("weather", "天气晴 10°/17°");
				// }
				//
				// obj.put("mood", json.toString());
				// } catch (JSONException e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
				// }
				// array.put(obj);
				// }
				// Log.v("===========array===========", "" + array.toString());
				// MoodUtility.insertJson(mContext, array.toString());

				Intent it = new Intent(MoodActivity.this, MoodActivity.class);
				it.putExtra("json", json);
				it.putExtra("time", time);
				startActivity(it);
				overridePendingTransition(R.anim.fade, R.anim.hold);
				finish();
			}
		});

		LinearLayout backBtn = (LinearLayout) findViewById(R.id.btn_back);
		backBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});

		isSameMoth();
		initOldMonth();

		Button BtnBack = (Button) findViewById(R.id.all_back);
		BtnBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

	private void initCloud() {
		arrayList = new ArrayList<Integer>();
		arrayList.add(R.drawable.mood_cloud_angry);
		arrayList.add(R.drawable.mood_cloud_cry);
		arrayList.add(R.drawable.mood_cloud_happy);
		arrayList.add(R.drawable.mood_cloud_smile);
		arrayList.add(R.drawable.mood_cloud_speechless);
	}

	private void isSameMoth() {
		if (time == -1) {
			isNowMoth = true;
		}
		Date clickDate = new Date(time);
		Date nowDate = new Date();
		int clickMonth = clickDate.getMonth();
		int nowMonth = nowDate.getMonth();
		int clickYear = clickDate.getYear();
		int nowYear = nowDate.getYear();
		if (clickMonth == nowMonth && clickYear == nowYear) {
			isNowMoth = true;
		}
	}

	private void initOldMonth() {

		List<Integer> position = new ArrayList<Integer>();

		int max = 2;
		int min = 0;
		Random random = new Random();
		int pos;
		int j = 0;
		int newMax = 0;
		int newmin = 0;

		ArrayList<Integer> drawableList = new ArrayList<Integer>();

		Date date = new Date(time);
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, date.getYear());
		c.set(Calendar.MONTH, date.getMonth());
		int day = c.get(Calendar.DATE);

		int allday = c.getActualMaximum(Calendar.DAY_OF_MONTH);

		for (int i = 0; i < allday; i++) {
			if ((i) % 5 == 0) {
				newMax = max + 3 * j;
				newmin = min + 3 * j;
				j++;
			}
			int positionRandom = random.nextInt(newMax) % (newMax - newmin + 1)
					+ newmin;
			int drawable = -1;
			if (time != -1) {
				try {
					JSONArray arry = new JSONArray(json);

					JSONObject mood = new JSONObject(arry.get(i).toString());
					JSONObject obj = new JSONObject(mood.getString("mood"));
					drawable = obj.getInt("gif");

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (drawable == -1) {

				if (day == i + 1) {
					if (isNowMoth) {
						drawableList.add(R.drawable.trend_mood_td);
					} else {
						drawableList.add(R.drawable.trend_mood_null);
					}

				} else {
					drawableList.add(R.drawable.trend_mood_null);
				}
			} else {

				if (day == i + 1) {
					if (isNowMoth) {
						drawableList.add(listup.get(drawable));
					} else {
						drawableList.add(listup.get(drawable));
					}
				} else {
					drawableList.add(listup.get(drawable));
				}
			}
			position.add(positionRandom);
		}
		treview.setPosition(position);
		treview.setDrawable(drawableList);

		treview.setmButtonIm(new TrendButtonIm() {

			@Override
			public void TrendonClick(View view) {
				// TODO Auto-generated method stub
				int i = view.getId();
				JSONObject jsonMood = null;
				if (time != -1) {
					try {
						JSONArray arry = new JSONArray(json);
						jsonMood = new JSONObject(arry.get(i).toString());
						// String moods = jsonMood.getString("mood");

					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				// Toast.makeText(mContext, moods, Toast.LENGTH_SHORT).show();
				// long time = MoodUtility.readTime(mContext);
				String tring = "sss";

				// }
				List<String> ss = MoodUtility.readAllJson(mContext);
				Log.v("==========	MoodUtility.readAllJson(mContext);============",
						"" + ss);
				MoodUtility.WriteMoods(mContext, time, i + 1, tring, jsonMood,
						isNowMoth);
			}
		});
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

}

// }

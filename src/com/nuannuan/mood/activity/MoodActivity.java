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
import android.widget.Button;

import com.nuannuan.mood.custom.controls.TrendView;
import com.nuannuan.mood.interfaces.TrendButtonIm;
import com.nuannuan.mood.utilitys.MoodUtility;
import com.nuannuan.star.constants.MoodConstants;
import com.scau.feelingmusic.R;

public class MoodActivity extends Activity {
	private TrendView treview;
	private Context mContext;
	private int screenWidth;
	private String json;
	private long time;
	private boolean isNowMoth = false;;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 隐去标题栏（应用程序的名字）
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_mood);
		mContext = this;
		Intent mIntent = getIntent();
		json = "";
		json = mIntent.getStringExtra("json");
		time = mIntent.getLongExtra("time", -1);
		

		screenWidth = getWindowManager().getDefaultDisplay().getWidth(); // ��Ļ�?���أ��磺480px��

		treview = (TrendView) findViewById(R.id.trendView);
		// mContainer = findViewById(R.id.container);
		treview.setWidthHeight(screenWidth, MoodConstants.Left);

		Button mButton = (Button) findViewById(R.id.btn_refresh);
		mButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Date mDate = new Date();
				for (int i = 1; i < 3; i++) {
					mDate.setYear(113);
					mDate.setMonth(i);
					mDate.setDate(3);
					MoodUtility.insertDate(mContext, mDate.getTime());
					JSONArray array = new JSONArray();
					JSONObject obj = new JSONObject();
					JSONObject json = new JSONObject();
					try {
						json.put("gif", 1);
						obj.put("mood", json.toString());
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
					array.put(obj);
					MoodUtility.insertJson(mContext, array.toString());
				
				}
			}
		});
	
		isSameMoth();
		initOldMonth();
		// }

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
		if (clickMonth == nowMonth&&clickYear==nowYear) {
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
					JSONObject obj =new JSONObject(mood.getString("mood"));
					drawable = obj.getInt("Drawable");

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
						drawableList.add(drawable);
					} else {
						drawableList.add(R.drawable.trend_mood_null);
					}
				} else {
					drawableList.add(drawable);
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
				List<String> ss=MoodUtility.readAllJson(mContext);
				Log.v("==========	MoodUtility.readAllJson(mContext);============", ""+ss	);
				MoodUtility.WriteMoods(mContext, time, i + 1, tring, jsonMood,
						isNowMoth);
			}
		});

	}

	// }
	//
	// private void initNewView() {
	//
	// List<Integer> position = new ArrayList<Integer>();
	//
	// int max = 2;
	// int min = 0;
	// Random random = new Random();
	// int pos;
	// int j = 0;
	// int newMax = 0;
	// int newmin = 0;
	//
	// ArrayList<Integer> drawableList = new ArrayList<Integer>();
	//
	// Date date = new Date();
	// Calendar c = Calendar.getInstance();
	// c.set(Calendar.YEAR, date.getYear());
	// c.set(Calendar.MONTH, date.getMonth());
	// int day = c.get(Calendar.DATE);
	//
	// int allday = c.getActualMaximum(Calendar.DAY_OF_MONTH);
	//
	// for (int i = 0; i < allday; i++) {
	// if ((i) % 5 == 0) {
	// newMax = max + 3 * j;
	// newmin = min + 3 * j;
	// j++;
	// }
	// int positionRandom = random.nextInt(newMax) % (newMax - newmin + 1)
	// + newmin;
	// int dawbale = -1;
	// if (time != -1) {
	// try {
	// JSONArray arry = new JSONArray(json);
	// JSONObject mood = new JSONObject(arry.get(i).toString());
	// dawbale = mood.getInt("drawable");
	//
	// } catch (JSONException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// if (dawbale == -1) {
	//
	// if (day == i + 1) {
	//
	// drawableList.add(R.drawable.trend_mood_td);
	// } else {
	// drawableList.add(R.drawable.trend_mood_null);
	// }
	// } else {
	//
	// if (day == i + 1) {
	// drawableList.add(R.drawable.trend_mood_td);
	// } else {
	// drawableList.add(dawbale);
	// }
	// }
	// position.add(positionRandom);
	// }
	// treview.setPosition(position);
	// treview.setDrawable(drawableList);
	//
	// treview.setmButtonIm(new TrendButtonIm() {
	//
	// @Override
	// public void TrendonClick(View view) {
	// // TODO Auto-generated method stub
	// int i = view.getId();
	// JSONObject jsonMood = null;
	// if (time != -1) {
	// try {
	// JSONArray arry = new JSONArray(json);
	// jsonMood = new JSONObject(arry.get(i).toString());
	// // String moods = jsonMood.getString("mood");
	//
	// } catch (JSONException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// // Toast.makeText(mContext, moods, Toast.LENGTH_SHORT).show();
	// long time = MoodUtility.readTime(mContext);
	// String tring = "sss";
	// MoodUtility.WriteMoods(mContext, time, i + 1, tring, jsonMood);
	// }
	// });
	//
	// }
}

// }

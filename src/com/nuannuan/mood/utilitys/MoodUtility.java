package com.nuannuan.mood.utilitys;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.AnimationDrawable;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

import com.nuannuan.common.sqlite.MoodDatabaseHelper;
import com.nuannuan.mood.activity.RecordMoodsActivity;
import com.nuannuan.mood.bean.MoodBean;
import com.scau.feelingmusic.R;
import com.scau.feelingmusic.R.anim;

public class MoodUtility {

	private static com.nuannuan.mood.custom.controls.MyDialog dialog;
	private static List<Integer> gifList = new ArrayList<Integer>();

	/**
	 * 判断是否可以进行表情更改
	 * 
	 * @param context
	 * @param oldtimes
	 *            获取数据库最后一次存储的时间
	 * @param day
	 *            点击的那天是否是当天
	 * @param jsonMoods
	 *            需要存放的json串
	 * @return
	 */
	public static Long readMoods(Context context, Long oldtimes, int day, int n) {

		Date nowDate = new Date();
		Date date_old = new Date(oldtimes);
		Date newDay = new Date();
		newDay.setDate(day);
		// 现在的时间
		long now_times = nowDate.getTime();
		// 以前最后一次记录的时间
		long old_times = date_old.getTime();
		// 新的时间
		long new_times = newDay.getTime();
		// 判断是否是同一个月的同一天
		SimpleDateFormat isSameDay = new SimpleDateFormat("yyyyMMdd");
		String nowDayStr = isSameDay.format(nowDate);
		String newDayStr = isSameDay.format(newDay);
		// 判断是否是同一个月
		SimpleDateFormat isSameMonth = new SimpleDateFormat("yyyyMM");
		String newMonthStr = isSameMonth.format(newDay);
		String oldMonthStr = isSameMonth.format(date_old);
		Log.e("=======newMonthStr=============", "" + newMonthStr);
		Log.e("=======oldMonthStr=============", "" + oldMonthStr);
		// long newtimes = end - start;
		// 现在的时间如果大于之前的时间
		if (now_times - new_times > 0) {
			Toast.makeText(context, "亲，过去了，就让他成为美好的回忆吧", Toast.LENGTH_SHORT)
					.show();

			// 现在的时间和新的时间匹配是否在同一天
		} else if (newDayStr.equals(nowDayStr)) {
			// 跳转

		} else {
			// 未来的时间
			Toast.makeText(context, "亲，那天还没有到呢", Toast.LENGTH_SHORT).show();
		}

		return new_times;
	}

	/**
	 * 读取某一天的心情记录
	 * 
	 * @param context
	 * @return
	 */
	public static String readJson(Context context, int day, int witchMonth) {
		String jsonStr = null;
		List<String> list = new ArrayList<String>();
		MoodDatabaseHelper dbHelper = new MoodDatabaseHelper(context, "moods",
				1);
		SQLiteDatabase db = dbHelper.getReadableDatabase();

		Cursor cursor = db.query("moods", new String[] { "id", "jsons" }, null,
				null, null, null, null);

		if (cursor.moveToFirst()) {
			do {
				jsonStr = cursor.getString(cursor.getColumnIndex("jsons"));
				list.add(jsonStr);
			} while (cursor.moveToNext());
		}
		String str = list.get(witchMonth);
		String json = null;
		try {
			JSONArray mArray = new JSONArray(str);
			json = mArray.getString(day);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.v("=============json===========", "" + json);
		cursor.close();
		return json;
	}

	/**
	 * 读取所有的心情记录
	 * 
	 * @param context
	 * @return List<String> String是JSONArray
	 */
	public static List<String> readAllJson(Context context) {

		List<String> list = new ArrayList<String>();
		MoodDatabaseHelper dbHelper = new MoodDatabaseHelper(context, "moods",
				1);
		SQLiteDatabase db = dbHelper.getReadableDatabase();

		Cursor cursor = db.query("moods", new String[] {}, null, null, null,
				null, null);

		if (cursor.moveToFirst()) {
			do {
				String jsonStr = cursor
						.getString(cursor.getColumnIndex("json"));
				list.add(jsonStr);
			} while (cursor.moveToNext());
		}
		cursor.close();
		return list;
	}

	/**
	 * 
	 * @param context
	 * @return long 返回一个最后一个时间搓 的对象
	 */
	public static long readTime(Context context) {
		long times = 0;
		MoodDatabaseHelper dbHelper = new MoodDatabaseHelper(context, "times",
				1);
		SQLiteDatabase db = dbHelper.getReadableDatabase();

		Cursor cursor = db.query("times", new String[] {}, null, null, null,
				null, null);

		if (cursor.moveToFirst()) {
			do {
				int id = cursor.getInt(cursor.getColumnIndex("id"));
				times = cursor.getLong(cursor.getColumnIndex("times"));
			} while (cursor.moveToNext());
		}
		cursor.close();
		return times;
	}

	/**
	 * 
	 * @param context
	 * @return long 返回所有时间搓的对象
	 */
	public static List<Long> readTimes(Context context) {
		List<Long> list = new ArrayList<Long>();
		MoodDatabaseHelper dbHelper = new MoodDatabaseHelper(context, "times",
				1);
		SQLiteDatabase db = dbHelper.getReadableDatabase();

		Cursor cursor = db.query("times", new String[] { "id", "times" }, null,
				null, null, null, null);

		if (cursor.moveToFirst()) {
			do {
				int id = cursor.getInt(cursor.getColumnIndex("id"));
				Long times = cursor.getLong(cursor.getColumnIndex("times"));
				list.add(times);
			} while (cursor.moveToNext());
		}
		cursor.close();
		return list;
	}

	/**
	 * 判断是否可以进行表情更改
	 * 
	 * @param context
	 * @param oldtimes
	 *            获取数据库最后一次存储的时间
	 * @param day
	 *            点击的那天是否是当天
	 * @param jsonMoods
	 *            需要存放的json串
	 * @return
	 */
	public static Long WriteMoods(Context context, Long oldtimes, int day,
			String jsonMoods, JSONObject moodJson, boolean isNowMonth) {

		dialog = new com.nuannuan.mood.custom.controls.MyDialog(context,
				R.style.MyDialog);
		dialog.setDialog(R.layout.layout_dialog);

		dialog.txt_Title.setText("");
		dialog.txt_Content.setText("");
		gifList();
		dialog.btn_Ok.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		// 鍙栨秷鎸夐挳鍝嶅簲浜嬩欢
		dialog.btn_Close.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});

		Date nowDate = new Date();
		Date date_old = new Date(oldtimes);

		Date newDay = new Date();
		newDay.setDate(day);
		// 现在的时间
		long now_times = nowDate.getTime();
		// 以前最后一次记录的时间
		long old_times = date_old.getTime();
		// 新的时间
		long new_times = newDay.getTime();
		// 判断是否是同一个月的同一天
		SimpleDateFormat isSameDay = new SimpleDateFormat("yyyyMMdd");
		String nowDayStr = isSameDay.format(nowDate);
		String newDayStr = isSameDay.format(newDay);
		// 判断是否是同一个月
		SimpleDateFormat isSameMonth = new SimpleDateFormat("yyyyMM");
		String newMonthStr = isSameMonth.format(newDay);
		String oldMonthStr = isSameMonth.format(date_old);

		// long newtimes = end - start;
		if (isNowMonth) {
			// 现在的时间如果大于之前的时间
			if (now_times - new_times > 0) {

				if (moodJson != null) {
					String mood = "";
					try {
						mood = (String) moodJson.get("mood");
						JSONObject obj = new JSONObject(mood);
						int witchGif = obj.getInt("gif");
						// 城市箭头补间动画
						ImageView imageView = dialog.imageView;
						imageView.setBackgroundResource(gifList.get(witchGif));
						AnimationDrawable ad = (AnimationDrawable) imageView
								.getBackground();
						ad.start();
						dialog.show();

					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Toast.makeText(context, "mood", Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(context, "亲，过去了，就让他成为美好的回忆吧",
							Toast.LENGTH_SHORT).show();
					dialog.show();
				}

				// 现在的时间和新的时间匹配是否在同一天
			} else if (newDayStr.equals(nowDayStr)) {

				if (isNowMonth) {
					Intent mIntent = new Intent(context,
							RecordMoodsActivity.class);
					mIntent.putExtra("day", day);
					mIntent.putExtra("oldtimes", oldtimes);
					context.startActivity(mIntent);
				} else {

				}

			} else {
				// 未来的时间
				Toast.makeText(context, "亲，那天还没有到呢", Toast.LENGTH_SHORT).show();
			}
		} else {
			if (moodJson != null) {
				String mood = "";
				try {
					mood = (String) moodJson.get("mood");
					JSONObject obj = new JSONObject(mood);
					int witchGif = obj.getInt("gif");
					// 城市箭头补间动画
					ImageView imageView = dialog.imageView;
					imageView.setBackgroundResource(gifList.get(witchGif));
					AnimationDrawable ad = (AnimationDrawable) imageView
							.getBackground();
					ad.start();
					dialog.show();

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Toast.makeText(context, "mood" + mood, Toast.LENGTH_SHORT)
						.show();
			} else {
				Toast.makeText(context, "亲，过去了，就让他成为美好的回忆吧", Toast.LENGTH_SHORT)
						.show();
			}
		}

		return new_times;
	}

	/**
	 * 
	 * @param context
	 * @param oldtimes
	 * @param day
	 * @param jsonMoods
	 *            一个JsonObject，用来记录心情
	 */
	public static void WriteJsonMood(Context context, Long oldtimes, int day,
			String jsonMoods) {

		Date nowDate = new Date();
		Date date_old = new Date(oldtimes);
		Date newDay = new Date();
		newDay.setDate(day);

		// 新的时间
		long new_times = newDay.getTime();

		// 判断是否是同一个月
		SimpleDateFormat isSameMonth = new SimpleDateFormat("yyyyMM");
		String newMonthStr = isSameMonth.format(newDay);
		String oldMonthStr = isSameMonth.format(date_old);

		// 新的时间月份是否和最后一次的月份相同
		if (newMonthStr.equals(oldMonthStr)) {

			updateDate(context, new_times, oldtimes);

			String jsonMood = readJson(context);
			JSONArray arryJson = null;
			if (jsonMood != null) {
				try {
					arryJson = new JSONArray(jsonMood);
					JSONObject json = new JSONObject();

					json.put("mood", jsonMoods);

					arryJson.put(day - 1, json);

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (arryJson != null) {
				updateJson(context, arryJson.toString());
			}
			Toast.makeText(context, "更新当天数据库" + jsonMood, Toast.LENGTH_SHORT)
					.show();
		} else {
			// 更新times和mood 2个数据库
			insertDate(context, new_times);

			JSONArray arryJson = null;
			try {
				arryJson = new JSONArray();
				JSONObject json = new JSONObject();
				json.put("mood", jsonMoods);
				arryJson.put(day - 1, json);

				Calendar c = Calendar.getInstance();
				c.set(Calendar.YEAR, newDay.getYear());
				c.set(Calendar.MONTH, newDay.getMonth() - 1);
				int allday = c.getActualMaximum(Calendar.DAY_OF_MONTH);
				arryJson.put(allday, "");

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (arryJson != null) {
				insertJson(context, arryJson.toString());
			}
			String jsonMood = readJson(context);
			Toast.makeText(context, "增加到新的月份的数据库" + jsonMood,
					Toast.LENGTH_SHORT).show();
		}

	}

	/**
	 * 更新同一个月的时间搓
	 * 
	 * @param new_times
	 * @param oldtimes
	 */
	public static void updateDate(Context context, long new_times, long oldtimes) {

		MoodDatabaseHelper dbHelper = new MoodDatabaseHelper(context, "times",
				1);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("times", new_times);
		// 第一个参数是要更新的表名
		// 第二个参数是一个ContentValeus对象
		// 第三个参数是where子句
		String old_timestr = Long.toString(oldtimes);
		db.update("times", values, "times=?", new String[] { old_timestr });
		db.close();
	}

	/**
	 * 增加不同月的时间搓
	 * 
	 * @param new_times
	 * @param oldtimes
	 */
	public static void insertDate(Context context, long new_times) {
		// 生成ContentValues对象
		ContentValues values = new ContentValues();
		// 想该对象当中插入键值对，其中键是列名，值是希望插入到这一列的值，值必须和数据库当中的数据类型一致
		MoodDatabaseHelper dbHelper = new MoodDatabaseHelper(context, "times",
				1);
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		String new_timestr = Long.toString(new_times);

		values.put("times", new_timestr);
		// 调用insert方法，就可以将数据插入到数据库当中
		db.insert("times", null, values);
		db.close();
	}

	/**
	 * 插入最新一天的心情记录
	 * 
	 * @param context
	 * @param json
	 *            JSONAarry 里面存放每个月所有天数的心情
	 */
	public static void insertJson(Context context, String json) {
		if (json == null || json.equals("")) {
			return;
		}
		// 生成ContentValues对象
		ContentValues values = new ContentValues();
		// 想该对象当中插入键值对，其中键是列名，值是希望插入到这一列的值，值必须和数据库当中的数据类型一致
		MoodDatabaseHelper dbHelper = new MoodDatabaseHelper(context, "moods",
				1);
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		values.put("json", json);
		// 调用insert方法，就可以将数据插入到数据库当中
		db.insert("moods", null, values);
		db.close();
	}

	/**
	 * 读取最后一个月的心情记录
	 * 
	 * @param context
	 * @return
	 */
	public static String readJson(Context context) {
		String jsonStr = null;
		MoodDatabaseHelper dbHelper = new MoodDatabaseHelper(context, "moods",
				1);
		SQLiteDatabase db = dbHelper.getReadableDatabase();

		Cursor cursor = db.query("moods", new String[] { "id", "json" }, null,
				null, null, null, null);

		if (cursor.moveToFirst()) {
			do {
				jsonStr = cursor.getString(cursor.getColumnIndex("json"));
			} while (cursor.moveToNext());
		}
		Log.v("=============readJson===========", "" + jsonStr);
		cursor.close();
		return jsonStr;
	}

	/**
	 * 更新最新一天的心情记录
	 * 
	 * @param context
	 * @param moodJson
	 */
	public static void updateJson(Context context, String moodJson) {
		if (moodJson == null || moodJson.equals("")) {
			return;
		}
		MoodDatabaseHelper dbHelper = new MoodDatabaseHelper(context, "moods",
				1);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("json", moodJson);
		// 第一个参数是要更新的表名
		// 第二个参数是一个ContentValeus对象
		// 第三个参数是where子句
		// String old_timestr = Long.toString(oldtimes);
		String isJson = readJson(context);
		db.update("moods", values, "json=?", new String[] { isJson });
		db.close();
	}

	private static void gifList() {
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

}

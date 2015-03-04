package com.nuannuan.star.custom.controls;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.scau.feelingmusic.R;

public class StarWeekLin extends LinearLayout {

	// private List<TextView> textList = new ArrayList<TextView>();

	private TextView text0;
	private TextView text1_title;

	private TextView text1;
	private TextView text_1;
	private TextView text11;
	private TextView text_11;

	private TextView text2;
	private TextView text3;
	private TextView text4;

	private TextView text5;
	private TextView text6;
	private TextView text7;

	private TextView text00;
	private TextView text22;
	private TextView text33;
	private TextView text44;
	private TextView text55;
	private TextView text66;
	private TextView text77;

	private Context mContext;

//	private TextView day_title;
//	private TextView day_time_title;

	private RatingBar bar0;
	private RatingBar bar1;
	private RatingBar bar_1;
	private RatingBar bar2;
	private RatingBar bar3;
	private RatingBar bar4;

	public StarWeekLin(Context context, String str) {
		super(context);
		// TODO Auto-generated constructor stub
		mContext = context;
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View view = inflater.inflate(R.layout.layout_star_week, this);
		View viewTitle = inflater.inflate(R.layout.layout_star_fortune_title,
				this);
		initView(view);
		JSONObject json0 = new JSONObject();
		JSONObject json1 = new JSONObject();
		JSONObject json2 = new JSONObject();
		JSONObject json3 = new JSONObject();
		JSONObject json4 = new JSONObject();
		JSONObject json5 = new JSONObject();
		JSONObject json6 = new JSONObject();
		JSONObject json7 = new JSONObject();
		JSONObject json8 = new JSONObject();
		JSONObject json9 = new JSONObject();

		JSONObject name = new JSONObject();

		String time;

		try {
			JSONArray arr = new JSONArray(str);
			json0 = (JSONObject) arr.get(0);
			text0.setText(json0.get("title").toString() + "");

			json1 = (JSONObject) arr.get(1);
			text1_title.setText(json1.get("title").toString() + "");
			JSONArray arr1_title2 = new JSONArray(json1.getString("title2"));
			text1.setText(arr1_title2.getString(0));
			text_1.setText(arr1_title2.getString(1));
			JSONArray arr1_rank = new JSONArray(json1.getString("rank"));
			bar1.setRating((Integer) arr1_rank.get(0));
			bar_1.setRating((Integer) arr1_rank.get(1));
			JSONArray arr1_value2 = new JSONArray(json1.getString("value2"));
			text11.setText(arr1_value2.getString(0));
			text_11.setText(arr1_value2.getString(1));

			json2 = (JSONObject) arr.get(2);
			text2.setText(json2.get("title").toString() + "");
			json3 = (JSONObject) arr.get(3);
			text3.setText(json3.get("title").toString() + "");
			json4 = (JSONObject) arr.get(4);
			text4.setText(json4.get("title").toString() + "");
			json5 = (JSONObject) arr.get(5);
			text5.setText(json5.get("title").toString() + "");
			json6 = (JSONObject) arr.get(6);
			text6.setText(json6.get("title").toString() + "");
			json7 = (JSONObject) arr.get(7);
			text7.setText(json7.get("title").toString() + ":");
			// json8 = (JSONObject) arr.get(8);
			// json9 = (JSONObject) arr.get(9);

			text00.setText(json0.get("value").toString());
			text22.setText(json2.get("value").toString());
			text33.setText(json2.get("value").toString());
			text44.setText(json4.get("value").toString());

			text55.setText(json5.get("value").toString());
			text66.setText(json6.get("value").toString());
			text77.setText(json7.get("value").toString());

			name = (JSONObject) arr.get(8);
			// day_time_title.setText(name.get("cn").toString() + " "
			// + arr.get(9).toString());
			// day_title.setText("周运势");
			String starname = name.get("cn").toString();
			String date = arr.getString(9).toString();
			initViewtitle(viewTitle, starname, date);// 初始化头部的时间和星座

			bar0.setRating((Integer) json0.get("rank"));

			bar2.setRating((Integer) json2.get("rank"));
			bar3.setRating((Integer) json3.get("rank"));
			bar4.setRating((Integer) json4.get("rank"));

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// textList.add(text0);
	}

	private void initViewtitle(View viewTitle, String starname, String date) {
		// TODO Auto-generated method stub
		TextView text220 = (TextView) viewTitle.findViewById(R.id.title_week);// 设置本周运势的标题背景
		text220.setBackgroundResource(R.drawable.star_title_button);
		text220.setTextColor(android.graphics.Color.WHITE);
		TextView text221 = (TextView) viewTitle.findViewById(R.id.star);// 设置星座背景图
		int a = 0;
		if (starname.equals("白羊座"))
			a = 1;
		else if (starname.equals("金牛座"))
			a = 2;
		else if (starname.equals("双子座"))
			a = 3;
		else if (starname.equals("巨蟹座"))
			a = 4;
		else if (starname.equals("狮子座"))
			a = 5;
		else if (starname.equals("处女座"))
			a = 6;
		else if (starname.equals("天秤座"))
			a = 7;
		else if (starname.equals("天蝎座"))
			a = 8;
		else if (starname.equals("射手座"))
			a = 9;
		else if (starname.equals("魔羯座"))
			a = 10;
		else if (starname.equals("水瓶座"))
			a = 11;
		else if (starname.equals("双鱼座"))
			a = 12;
		switch (a) {
		case 1:
			text221.setBackgroundResource(R.drawable.star_title_xingzuo);
			break;
		case 2:
			text221.setBackgroundResource(R.drawable.star_title_xingzuo);
			break;
		case 3:
			text221.setBackgroundResource(R.drawable.star_title_xingzuo);
			break;
		case 4:
			text221.setBackgroundResource(R.drawable.star_title_xingzuo);
			break;
		case 5:
			text221.setBackgroundResource(R.drawable.star_title_xingzuo);
			break;
		case 6:
			text221.setBackgroundResource(R.drawable.star_title_xingzuo);
			break;
		case 7:
			text221.setBackgroundResource(R.drawable.star_title_xingzuo);
			break;
		case 8:
			text221.setBackgroundResource(R.drawable.star_title_xingzuo);
			break;
		case 9:
			text221.setBackgroundResource(R.drawable.star_title_xingzuo);
			break;
		case 10:
			text221.setBackgroundResource(R.drawable.star_title_xingzuo);
			break;
		case 11:
			text221.setBackgroundResource(R.drawable.star_title_xingzuo);
			break;
		case 12:
			text221.setBackgroundResource(R.drawable.star_title_xingzuo);
			break;
		default:
			text221.setBackgroundResource(R.drawable.star_title_xingzuo);
			break;
		}
		// text21.setBackgroundResource(R.drawable.star_title_xingzuo);
		// text20.setBackgroundColor(color.hotpink);
		// TextView text21 = (TextView)
		// viewTitle.findViewById(R.id.star);//设置星座背景图
		// text21.setText(starname);
		String year = date.substring(2, 12);
//		String month = date.substring(5, 7);
//		String day = date.substring(8, 10);
		String week = date.substring(15, 25);
		TextView text222 = (TextView) viewTitle.findViewById(R.id.year);
//		TextView text223 = (TextView) viewTitle.findViewById(R.id.month);
//		TextView text224 = (TextView) viewTitle.findViewById(R.id.day);
		TextView text225 = (TextView) viewTitle.findViewById(R.id.week);
		text222.setText(year + "/");
//		text223.setText(month + "/");
//		text224.setText(day);
		text225.setText(week);
	}

	private void initView(View view) {
		text0 = (TextView) view.findViewById(R.id.text0);
		text1 = (TextView) view.findViewById(R.id.text1);
		text2 = (TextView) view.findViewById(R.id.text2);
		text3 = (TextView) view.findViewById(R.id.text3);
		text4 = (TextView) view.findViewById(R.id.text4);
		text5 = (TextView) view.findViewById(R.id.text5);
		text6 = (TextView) view.findViewById(R.id.text6);
		text7 = (TextView) view.findViewById(R.id.text7);

		text00 = (TextView) view.findViewById(R.id.text00);
		text22 = (TextView) view.findViewById(R.id.text22);
		text33 = (TextView) view.findViewById(R.id.text33);
		text44 = (TextView) view.findViewById(R.id.text44);
		text55 = (TextView) view.findViewById(R.id.text55);
		text66 = (TextView) view.findViewById(R.id.text66);
		text77 = (TextView) view.findViewById(R.id.text77);

//		day_title = (TextView) view.findViewById(R.id.ctl_day_title);
//		day_time_title = (TextView) view.findViewById(R.id.ctl_day_time_title);

		bar0 = (RatingBar) view.findViewById(R.id.rtb0);
		bar1 = (RatingBar) view.findViewById(R.id.rtb1);
		bar2 = (RatingBar) view.findViewById(R.id.rtb2);
		bar3 = (RatingBar) view.findViewById(R.id.rtb3);

		text11 = (TextView) view.findViewById(R.id.text11);
		text_1 = (TextView) view.findViewById(R.id.text_1);
		text_11 = (TextView) view.findViewById(R.id.text_11);

		bar_1 = (RatingBar) view.findViewById(R.id.rtb_1);
		bar4 = (RatingBar) view.findViewById(R.id.rtb4);

		text1_title = (TextView) view.findViewById(R.id.text1_title);

		Typeface face2 = Typeface.createFromAsset(mContext.getAssets(),
				"fonts/snFontP2.TTF");
		text0.setTypeface(face2);
		text1.setTypeface(face2);

		text2.setTypeface(face2);
		text3.setTypeface(face2);
		text4.setTypeface(face2);
		text5.setTypeface(face2);
		text6.setTypeface(face2);
		text7.setTypeface(face2);

		text00.setTypeface(face2);
		text22.setTypeface(face2);
		text33.setTypeface(face2);
		text44.setTypeface(face2);
		text55.setTypeface(face2);
		text66.setTypeface(face2);
		text77.setTypeface(face2);

		text11.setTypeface(face2);
		text_1.setTypeface(face2);
		text_11.setTypeface(face2);
		text1_title.setTypeface(face2);

		// day_title.setTypeface(face2);
		// day_time_title.setTypeface(face2);
	}
}

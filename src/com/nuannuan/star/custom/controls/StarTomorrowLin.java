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

public class StarTomorrowLin extends LinearLayout {

	// private List<TextView> textList = new ArrayList<TextView>();

	private TextView text0;
	private TextView text1;
	private TextView text2;
	private TextView text3;
	private TextView text4;

	private TextView text5;
	private TextView text6;
	private TextView text7;
	private TextView text8;
	private TextView text9;
	private TextView text10;

	private TextView text44;
	private TextView text55;
	private TextView text66;
	private TextView text77;
	private TextView text88;
	private TextView text99;
	private Context mContext;

	// private TextView day_title;
	// private TextView day_time_title;

	private RatingBar bar0;
	private RatingBar bar1;
	private RatingBar bar2;
	private RatingBar bar3;

	public StarTomorrowLin(Context context, String str) {
		super(context);
		// TODO Auto-generated constructor stub
		mContext = context;
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View view = inflater.inflate(R.layout.layout_star_tomorow, this);
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

		// JSONObject json44 = new JSONObject();
		// JSONObject json55 = new JSONObject();
		// JSONObject json66 = new JSONObject();
		// JSONObject json77 = new JSONObject();
		// JSONObject json88 = new JSONObject();
		// JSONObject json99 = new JSONObject();

		JSONObject name = new JSONObject();

		String time;

		try {
			JSONArray arr = new JSONArray(str);
			json0 = (JSONObject) arr.get(0);
			text0.setText(json0.get("title").toString() + "");
			json1 = (JSONObject) arr.get(1);
			text1.setText(json1.get("title").toString() + "");
			json2 = (JSONObject) arr.get(2);
			text2.setText(json2.get("title").toString() + "");
			json3 = (JSONObject) arr.get(3);
			text3.setText(json3.get("title").toString() + "");
			json4 = (JSONObject) arr.get(4);
			text4.setText(json4.get("title").toString() + ":");
			json5 = (JSONObject) arr.get(5);
			text5.setText(json5.get("title").toString() + ":");
			json6 = (JSONObject) arr.get(6);
			text6.setText(json6.get("title").toString() + ":");
			json7 = (JSONObject) arr.get(7);
			text7.setText(json7.get("title").toString() + ":");
			json8 = (JSONObject) arr.get(8);
			text8.setText(json8.get("title").toString() + ":");
			json9 = (JSONObject) arr.get(9);
			text9.setText(json9.get("title").toString() + "");

			text44.setText(json4.get("value").toString());
			text55.setText(json5.get("value").toString());
			text66.setText(json6.get("value").toString());
			text77.setText(json7.get("value").toString());
			text88.setText(json8.get("value").toString());
			text99.setText(json9.get("value").toString());

			name = (JSONObject) arr.get(10);
			// day_time_title.setText(name.get("cn").toString()+" "+arr.get(11).toString());
			// day_title.setText("明日运势");
			String starname = name.get("cn").toString();

			String date = arr.getString(11).toString();
			initViewtitle(viewTitle, starname, date);// 初始化头部的时间和星座

			bar0.setRating((Integer) json0.get("rank"));
			bar1.setRating((Integer) json1.get("rank"));
			bar2.setRating((Integer) json2.get("rank"));
			bar3.setRating((Integer) json3.get("rank"));

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// textList.add(text0);
	}

	private void initViewtitle(View viewTitle, String starname, String date) {
		// TODO Auto-generated method stub
		TextView text20 = (TextView) viewTitle.findViewById(R.id.title_tomorrow);// 设置明日运势的标题背景
		text20.setBackgroundResource(R.drawable.star_title_button);
		text20.setTextColor(android.graphics.Color.WHITE);
		TextView text21 = (TextView) viewTitle.findViewById(R.id.star);// 设置星座背景图
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
			text21.setBackgroundResource(R.drawable.star_title_xingzuo);
			break;
		case 2:
			text21.setBackgroundResource(R.drawable.star_title_xingzuo);
			break;
		case 3:
			text21.setBackgroundResource(R.drawable.star_title_xingzuo);
			break;
		case 4:
			text21.setBackgroundResource(R.drawable.star_title_xingzuo);
			break;
		case 5:
			text21.setBackgroundResource(R.drawable.star_title_xingzuo);
			break;
		case 6:
			text21.setBackgroundResource(R.drawable.star_title_xingzuo);
			break;
		case 7:
			text21.setBackgroundResource(R.drawable.star_title_xingzuo);
			break;
		case 8:
			text21.setBackgroundResource(R.drawable.star_title_xingzuo);
			break;
		case 9:
			text21.setBackgroundResource(R.drawable.star_title_xingzuo);
			break;
		case 10:
			text21.setBackgroundResource(R.drawable.star_title_xingzuo);
			break;
		case 11:
			text21.setBackgroundResource(R.drawable.star_title_xingzuo);
			break;
		case 12:
			text21.setBackgroundResource(R.drawable.star_title_xingzuo);
			break;
		default:
			text21.setBackgroundResource(R.drawable.star_title_xingzuo);
			break;
		}
		// text21.setBackgroundResource(R.drawable.star_title_xingzuo);
		// text20.setBackgroundColor(color.hotpink);
		// TextView text21 = (TextView)
		// viewTitle.findViewById(R.id.star);//设置星座背景图
		// text21.setText(starname);
		String year = date.substring(0, 4);
		String month = date.substring(5, 7);
		String day = date.substring(8, 10);
		TextView text22 = (TextView) viewTitle.findViewById(R.id.year);
		TextView text23 = (TextView) viewTitle.findViewById(R.id.month);
		TextView text24 = (TextView) viewTitle.findViewById(R.id.day);
		text22.setText(year + "/");
		text23.setText(month + "/");
		text24.setText(day);
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
		text8 = (TextView) view.findViewById(R.id.text8);
		text9 = (TextView) view.findViewById(R.id.text9);

		text44 = (TextView) view.findViewById(R.id.text44);
		text55 = (TextView) view.findViewById(R.id.text55);
		text66 = (TextView) view.findViewById(R.id.text66);
		text77 = (TextView) view.findViewById(R.id.text77);
		text88 = (TextView) view.findViewById(R.id.text88);
		text99 = (TextView) view.findViewById(R.id.text99);

		// day_title = (TextView) view.findViewById(R.id.ctl_day_title);
		// day_time_title = (TextView)
		// view.findViewById(R.id.ctl_day_time_title);

		bar0 = (RatingBar) view.findViewById(R.id.rtb0);
		bar1 = (RatingBar) view.findViewById(R.id.rtb1);
		bar2 = (RatingBar) view.findViewById(R.id.rtb2);
		bar3 = (RatingBar) view.findViewById(R.id.rtb3);

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
		text8.setTypeface(face2);
		text9.setTypeface(face2);

		text44.setTypeface(face2);
		text55.setTypeface(face2);
		text66.setTypeface(face2);
		text77.setTypeface(face2);
		text88.setTypeface(face2);
		text99.setTypeface(face2);

		// day_title.setTypeface(face2);
		// day_time_title.setTypeface(face2);
	}
}

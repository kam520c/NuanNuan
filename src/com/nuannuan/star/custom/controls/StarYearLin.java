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

public class StarYearLin extends LinearLayout{

	// private List<TextView> textList = new ArrayList<TextView>();

	private TextView text0;
	private TextView text1;
	private TextView text2;
	private TextView text3;
	private TextView text4;
	private TextView text5;
//	private TextView text6;

	private TextView text00;
	private TextView text11;
	private TextView text22;
	private TextView text33;
	private TextView text44;
	private TextView text55;
//	private TextView text66;
//	private TextView text44;
	// private TextView text55;
	private Context mContext;

	private TextView day_title;
	private TextView day_time_title;

	private RatingBar bar0;
	private RatingBar bar1;
	private RatingBar bar2;
	private RatingBar bar3;
	private RatingBar bar4;

	public StarYearLin(Context context, String str) {
		super(context);
		// TODO Auto-generated constructor stub
		mContext = context;
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View view = inflater.inflate(R.layout.layout_star_year, this);
		View viewTitle = inflater.inflate(R.layout.layout_star_fortune_title,
				this);
		initView(view);
		JSONObject json0 = new JSONObject();
		JSONObject json1 = new JSONObject();
		JSONObject json2 = new JSONObject();
		JSONObject json3 = new JSONObject();
		JSONObject json4 = new JSONObject();
		JSONObject json5 = new JSONObject();

		JSONObject name = new JSONObject();

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
			text4.setText(json4.get("title").toString() + "");
			json5 = (JSONObject) arr.get(5);
			text5.setText(json5.get("title").toString() + ":");

			text00.setText(json0.get("value").toString());
			text11.setText(json1.get("value").toString());
			text22.setText(json2.get("value").toString());
			text33.setText(json3.get("value").toString());
			text44.setText(json4.get("value").toString());
			text55.setText(json5.get("value").toString());
//			text44.setText(json6.get("value").toString());

			name = (JSONObject) arr.get(6);
            String starname =name.get("cn").toString();
			
			String date =arr.getString(7).toString();
			initViewtitle(viewTitle,starname,date);//初始化头部的时间和星座
//			day_time_title.setText(name.get("cn").toString() + " "
//					+ arr.get(7).toString());
//			day_title.setText("年运势");

			bar0.setRating((Integer) json0.get("rank"));
			bar1.setRating((Integer) json1.get("rank"));
			bar2.setRating((Integer) json2.get("rank"));
			bar3.setRating((Integer) json3.get("rank"));
			bar4.setRating((Integer) json4.get("rank"));

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// textList.add(text0);
	}
	private void initViewtitle(View viewTitle,String starname,String date) {
		// TODO Auto-generated method stub
		TextView text20 = (TextView) viewTitle.findViewById(R.id.title_year);//设置今年运势的标题背景
		text20.setBackgroundResource(R.drawable.star_title_button);
		text20.setTextColor(android.graphics.Color.WHITE);
		TextView text21 = (TextView) viewTitle.findViewById(R.id.star);//设置星座背景图
		int a = 0;
		if(starname.equals("白羊座"))
			a=1;
		else if(starname.equals("金牛座"))
			a=2;
		else if(starname.equals("双子座"))
			a=3;
		else if(starname.equals("巨蟹座"))
			a=4;
		else if(starname.equals("狮子座"))
			a=5;
		else if(starname.equals("处女座"))
			a=6;
		else if(starname.equals("天秤座"))
			a=7;
		else if(starname.equals("天蝎座"))
			a=8;
		else if(starname.equals("射手座"))
			a=9;
		else if(starname.equals("魔羯座"))
			a=10;
		else if(starname.equals("水瓶座"))
			a=11;
		else if(starname.equals("双鱼座"))
			a=12;
		switch(a){
		case 1:text21.setBackgroundResource(R.drawable.star_title_xingzuo);break;
		case 2:text21.setBackgroundResource(R.drawable.star_title_xingzuo);break;
		case 3:text21.setBackgroundResource(R.drawable.star_title_xingzuo);break;
		case 4:text21.setBackgroundResource(R.drawable.star_title_xingzuo);break;
		case 5:text21.setBackgroundResource(R.drawable.star_title_xingzuo);break;
		case 6:text21.setBackgroundResource(R.drawable.star_title_xingzuo);break;
		case 7:text21.setBackgroundResource(R.drawable.star_title_xingzuo);break;
		case 8:text21.setBackgroundResource(R.drawable.star_title_xingzuo);break;
		case 9:text21.setBackgroundResource(R.drawable.star_title_xingzuo);break;
		case 10:text21.setBackgroundResource(R.drawable.star_title_xingzuo);break;
		case 11:text21.setBackgroundResource(R.drawable.star_title_xingzuo);break;
		case 12:text21.setBackgroundResource(R.drawable.star_title_xingzuo);break;
		default:text21.setBackgroundResource(R.drawable.star_title_xingzuo);break;
		}
//		text21.setBackgroundResource(R.drawable.star_title_xingzuo);
//		text20.setBackgroundColor(color.hotpink);
//		TextView text21 = (TextView) viewTitle.findViewById(R.id.star);//设置星座背景图
//		text21.setText(starname);
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
//		text6 = (TextView) view.findViewById(R.id.text6);

		text00 = (TextView) view.findViewById(R.id.text00);
		text11 = (TextView) view.findViewById(R.id.text11);
		text22 = (TextView) view.findViewById(R.id.text22);
		text33 = (TextView) view.findViewById(R.id.text33);
		text44 = (TextView) view.findViewById(R.id.text44);
		text55 = (TextView) view.findViewById(R.id.text55);
//		text66 = (TextView) view.findViewById(R.id.text66);

//		day_title = (TextView) view.findViewById(R.id.ctl_day_title);
//		day_time_title = (TextView) view.findViewById(R.id.ctl_day_time_title);

		bar0 = (RatingBar) view.findViewById(R.id.rtb0);
		bar1 = (RatingBar) view.findViewById(R.id.rtb1);
		bar2 = (RatingBar) view.findViewById(R.id.rtb2);
		bar3 = (RatingBar) view.findViewById(R.id.rtb3);
		bar4 = (RatingBar) view.findViewById(R.id.rtb4);
		
		Typeface face2 = Typeface.createFromAsset(mContext.getAssets(),
				"fonts/snFontP2.TTF");
		text0.setTypeface(face2);
		text1.setTypeface(face2);
		text2.setTypeface(face2);
		text3.setTypeface(face2);
		text4.setTypeface(face2);
		text5.setTypeface(face2);
//		text6.setTypeface(face2);

		text00.setTypeface(face2);
		text11.setTypeface(face2);
		text22.setTypeface(face2);
		text33.setTypeface(face2);
		text44.setTypeface(face2);
		text55.setTypeface(face2);
//		text66.setTypeface(face2);

//		day_title.setTypeface(face2);
//		day_time_title.setTypeface(face2);
	}

}

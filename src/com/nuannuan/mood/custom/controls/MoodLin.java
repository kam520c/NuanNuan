package com.nuannuan.mood.custom.controls;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nuannuan.common.R;
import com.nuannuan.mood.interfaces.TrendButtonIm;

public class MoodLin extends LinearLayout implements View.OnClickListener {
	private Context mContext;
//	private List<Integer> mlistup;
//	private List<String> mtextList;
	private int mWidth;
//	private List<Integer> mlistDown;
	private List<String>  mtextList = new ArrayList<String>();
	private List<Integer> mlistup = new ArrayList<Integer>();
	private List<Integer> mlistDown = new ArrayList<Integer>();

	public TrendButtonIm getmButtonIm() {
		return mButtonIm;
	}

	public void setmButtonIm(TrendButtonIm mButtonIm) {
		this.mButtonIm = mButtonIm;
	}

	private TrendButtonIm mButtonIm = null;
	private List<ImageView> listImage = new ArrayList<ImageView>();

	public MoodLin(Context context, int width, List<Integer> listUp,
			List<Integer> listDown, List<String> textList, int whitch) {
		super(context);
		// TODO Auto-generated constructor stub
		mContext = context;
		mWidth = width / 5;

		

		this.mlistup = listUp;
		this.mlistDown = listDown;
		this.mtextList = textList;

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View view = inflater.inflate(R.layout.layout_moodlin, this);
		LinearLayout lin1 = (LinearLayout) view.findViewById(R.id.moodLin1);
		LinearLayout lin2 = (LinearLayout) view.findViewById(R.id.moodLin2);

		LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		lp1.weight = 1;

		LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(mWidth,
				mWidth);
		lp2.leftMargin = 10;
		lp2.rightMargin = 10;

		if (listUp.size() >= 4) {

			for (int i = 1; i < 5; i++) {
				LinearLayout linMood = new LinearLayout(mContext);
				linMood.setOrientation(VERTICAL);
				linMood.setGravity(Gravity.CENTER);
				TextView text = new TextView(mContext);
				text.setGravity(Gravity.CENTER);
				text.setTextSize(18);
				text.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
				text.setText(textList.get(i - 1));
				ImageView mood = new ImageView(mContext);
				mood.setBackgroundResource(mlistup.get(i - 1));
				int a=i + 8*(whitch-1);
				mood.setId(a);
				mood.setOnClickListener(this);
				linMood.addView(mood, lp2);
				linMood.addView(text);
				lin1.addView(linMood, lp1);
				listImage.add(mood);
			}
		}

		for (int i = 5; i < listUp.size() + 1; i++) {
			LinearLayout linMood = new LinearLayout(mContext);
			linMood.setOrientation(VERTICAL);
			linMood.setGravity(Gravity.CENTER);
			TextView text = new TextView(mContext);
			text.setGravity(Gravity.CENTER);
			text.setTextSize(18);
			text.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
			text.setText(textList.get(i - 1));
			ImageView mood = new ImageView(mContext);
			mood.setBackgroundResource(mlistup.get(i - 1));
		
			int a=i + 8*(whitch-1);
			mood.setId(a);
			mood.setOnClickListener(this);
			linMood.addView(mood, lp2);
			linMood.addView(text);
			lin2.addView(linMood, lp1);
			listImage.add(mood);
		}

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case 1:
			setImage(arg0, 1);
			setImageClick(arg0);
			break;
		case 2:
			setImage(arg0, 2);
			setImageClick(arg0);
			break;
		case 3:
			setImage(arg0, 3);
			setImageClick(arg0);
			break;
		case 4:
			setImage(arg0, 4);
			setImageClick(arg0);
			break;
		case 5:
			setImage(arg0, 5);
			setImageClick(arg0);
			break;
		case 6:
			setImage(arg0, 6);
			setImageClick(arg0);
			break;
		case 7:
			setImage(arg0, 7);
			setImageClick(arg0);
			break;
		case 8:
			setImage(arg0, 8);
			setImageClick(arg0);
			break;
		case 9:
			setImage(arg0, 9);
			setImageClick(arg0);
			break;
		case 10:
			setImage(arg0, 10);
			setImageClick(arg0);
			break;
		case 11:
			setImage(arg0, 11);
			setImageClick(arg0);
			break;
		case 12:
			setImage(arg0, 12);
			setImageClick(arg0);
			break;
		case 13:
			setImage(arg0, 13);
			setImageClick(arg0);
			break;
		case 14:
			setImage(arg0, 14);
			setImageClick(arg0);
			break;
		case 15:
			setImage(arg0, 15);
			setImageClick(arg0);
			break;
		case 16:
			setImage(arg0, 16);
			setImageClick(arg0);
			break;
			
		default:
			break;
		}
	}

	private void setImage(View view, int j) {
		// int id = view.getId() - 1;

//		for (int i = 0; i < listImage.size(); i++) {
//			Log.v("=========mlistDown===========", "" + mlistDown.size());
//			Log.v("===========mlistup===========", "" + mlistup);
//			listImage.get(i).setBackgroundResource(mlistup.get(i));
//		}
//		ImageView image = listImage.get(j);
//		image.setBackgroundResource(mlistDown.get(j));
	}

	public void setImageClick(View view) {

		if (mButtonIm != null) {
			mButtonIm.TrendonClick(view);
		}
	}
}

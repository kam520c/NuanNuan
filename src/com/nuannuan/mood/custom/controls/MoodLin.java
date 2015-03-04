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

import com.nuannuan.mood.interfaces.TrendButtonIm;
import com.scau.feelingmusic.R;

public class MoodLin extends LinearLayout implements View.OnClickListener {
	private Context mContext;
	private List<Integer> listup = new ArrayList<Integer>();
	private List<String> textList = new ArrayList<String>();
	private int mWidth;
	private List<Integer> listDown = new ArrayList<Integer>();

	public TrendButtonIm getmButtonIm() {
		return mButtonIm;
	}

	public void setmButtonIm(TrendButtonIm mButtonIm) {
		this.mButtonIm = mButtonIm;
	}

	private TrendButtonIm mButtonIm = null;
	private List<ImageView> listImage = new ArrayList<ImageView>();

	public MoodLin(Context context, int width, List<Integer> listUp,
			List<Integer> listDown, List<String> textList) {
		super(context);
		// TODO Auto-generated constructor stub
		mContext = context;
		mWidth = width / 5;

		this.listup = listUp;
		this.listDown = listDown;
		this.textList = textList;

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

		
		for (int i = 0; i < 4; i++) {
			LinearLayout linMood = new LinearLayout(mContext);
			linMood.setOrientation(VERTICAL);
			linMood.setGravity(Gravity.CENTER);
			TextView text = new TextView(mContext);
			text.setGravity(Gravity.CENTER);
			text.setTextSize(18);
			text.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
			text.setText(textList.get(i));
			ImageView mood = new ImageView(mContext);
			mood.setId(i);
			mood.setBackgroundResource(listup.get(i));
			mood.setOnClickListener(this);
			linMood.addView(mood, lp2);
			linMood.addView(text);
			lin1.addView(linMood, lp1);
			listImage.add(mood);
		}
		for (int i = 4; i < 8; i++) {
			LinearLayout linMood = new LinearLayout(mContext);
			linMood.setOrientation(VERTICAL);
			linMood.setGravity(Gravity.CENTER);
			TextView text = new TextView(mContext);
			text.setGravity(Gravity.CENTER);
			text.setTextSize(18);
			text.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
			text.setText(textList.get(i));
			ImageView mood = new ImageView(mContext);
			mood.setId(i);
			mood.setBackgroundResource(listup.get(i));
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
		case 0:
			setImage(arg0);
			setImageClick(arg0);
			break;
		case 1:
			setImage(arg0);
			setImageClick(arg0);
			break;
		case 2:
			setImage(arg0);
			setImageClick(arg0);
			break;
		case 3:
			setImage(arg0);
			setImageClick(arg0);
			break;
		case 4:
			setImage(arg0);
			setImageClick(arg0);
			break;
		case 5:
			setImage(arg0);
			setImageClick(arg0);
			break;
		case 6:
			setImage(arg0);
			setImageClick(arg0);
			break;
		case 7:
			setImage(arg0);
			setImageClick(arg0);
			break;
		default:
			break;
		}
	}

	private void setImage(View view) {
		int id = view.getId();
		for (int i = 0; i < listImage.size(); i++) {
			listImage.get(i).setBackgroundResource(listup.get(i));
		}
		ImageView image = listImage.get(id);
		image.setBackgroundResource(listDown.get(id));
	}

	public void setImageClick(View view) {

		if (mButtonIm != null) {
			mButtonIm.TrendonClick(view);
		}
	}
}

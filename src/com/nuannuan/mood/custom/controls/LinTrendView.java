package com.nuannuan.mood.custom.controls;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.nuannuan.common.R;
import com.nuannuan.mood.interfaces.TrendButtonIm;
import com.nuannuan.star.constants.MoodConstants;

public class LinTrendView extends LinearLayout{

	public LinTrendView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public LinTrendView(Context context,int screenWidth,int director,ArrayList<Integer> drawableList,List<Integer> position) {
		super(context);
		// TODO Auto-generated constructor stub
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View view = inflater.inflate(R.layout.layout_trendview, this);
		TrendView mTrendView=(TrendView) view.findViewById(R.id.lin_tredview);
		mTrendView.setWidthHeight(screenWidth, MoodConstants.Right);
		mTrendView.setDrawable(drawableList);
		mTrendView.setPosition(position);
		mTrendView.setmButtonIm(new TrendButtonIm() {
			
			@Override
			public void TrendonClick(View view) {
				// TODO Auto-generated method stub
				mIm.getViewObj(view);
			}
		});
	
	}
	public LinTrendIm mIm;
	public void getTrendView(LinTrendIm mTrendIm){
		this.mIm = mTrendIm;
	}
	
	public interface LinTrendIm{
		public void getViewObj(View view);
	}
}

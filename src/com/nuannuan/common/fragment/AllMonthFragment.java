package com.nuannuan.common.fragment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.nuannuan.common.R;
import com.nuannuan.common.activity.HomeActivity;
import com.nuannuan.common.custom.controls.JazzyViewPager;
import com.nuannuan.mood.activity.MoodActivity;
import com.nuannuan.mood.adapter.AllMonthAdapter;
import com.nuannuan.mood.custom.controls.LinTrendView;
import com.nuannuan.mood.utility.MoodUtility;
import com.nuannuan.weather.custom.controls.GalleryView;

public class AllMonthFragment extends android.support.v4.app.Fragment {

	private HomeActivity home;
	// private TrendView treview;

	private JazzyViewPager mJazzy;
	private ArrayList<ImageView> list = new ArrayList<ImageView>();
	private List<LinTrendView> trendList = new ArrayList<LinTrendView>();
	private TextView tvTitle;
	private GalleryView gallery;
	private AllMonthAdapter adapter;
	private List<String> StrList = new ArrayList<String>();
	private List<Long> loogList = new ArrayList<Long>();
	private List<Integer> intList;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View views = inflater.inflate(R.layout.fragment_all_month, container,
				false);
		home = (HomeActivity) getActivity();
		initRes(views);
		return views;
	}

	private void initRes(View view) {
		tvTitle = (TextView) view.findViewById(R.id.tvTitle);
		gallery = (GalleryView) view.findViewById(R.id.mygallery);

		int screenHeight = home.getWindowManager().getDefaultDisplay()
				.getHeight();

		loogList = MoodUtility.readTimes(home);

		intList = new ArrayList<Integer>();
		if (loogList.size() > 0) {
			for (long dates : loogList) {
				Date mDate = new Date(dates);
				int Month = mDate.getMonth();
				intList.add(Month);
			}
		}

		StrList = MoodUtility.readAllJson(home);

		adapter = new AllMonthAdapter(home, screenHeight, intList);
		adapter.createReflectedImages();
		gallery.setAdapter(adapter);

		gallery.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				if(intList.size()>position){
					tvTitle.setText(intList.get(position)+1+"月");
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});

		gallery.setOnItemClickListener(new OnItemClickListener() { // ���õ���¼�����
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent mIntent = new Intent(home, MoodActivity.class);
				String json = null;
				long time = -1;
				try {
					json = StrList.get(position);
					time = loogList.get(position);
				} catch (Exception e) {

				}
				mIntent.putExtra("json", json);
				mIntent.putExtra("time", time);
				startActivity(mIntent);
			}
		});
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

}

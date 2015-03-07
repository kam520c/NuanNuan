package com.nuannuan.weather.adapter;

import java.util.List;

import com.nuannuan.common.custom.controls.InhaleView;
import com.nuannuan.common.custom.controls.JazzyViewPager;
import com.nuannuan.common.custom.controls.OutlineContainer;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

public class WeatherTrendAdapter extends PagerAdapter {

	private List<LinearLayout> mList;
	private JazzyViewPager mJazzy;

	public WeatherTrendAdapter(List<LinearLayout> list, JazzyViewPager jazzy) {
		mList = list;
		mJazzy = jazzy;
	}
	public void refreshAdapter(boolean isRefresh) {
		if (isRefresh) {
			notifyDataSetChanged();
		}
	}
	
	@Override
	public Object instantiateItem(ViewGroup container, final int position) {

		container.addView(mList.get(position), LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		mJazzy.setObjectForPosition(mList.get(position), position);

		return mList.get(position);
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object obj) {
		container.removeView(mJazzy.findViewFromObject(position));
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object obj) {
		if (view instanceof OutlineContainer) {
			return ((OutlineContainer) view).getChildAt(0) == obj;
		} else {
			return view == obj;
		}
	}

}

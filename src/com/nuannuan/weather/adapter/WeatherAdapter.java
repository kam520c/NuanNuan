package com.nuannuan.weather.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

import com.nuannuan.common.custom.controls.InhaleView;
import com.nuannuan.common.custom.controls.JazzyViewPager;
import com.nuannuan.common.custom.controls.OutlineContainer;
import com.nuannuan.weather.activity.WeatherTrendActivity;

public class WeatherAdapter extends PagerAdapter {

	private ArrayList<InhaleView> mList;
	private JazzyViewPager mJazzy;
	private Context mContext;
	private int x1;

	public WeatherAdapter(Context context, ArrayList<InhaleView> list,
			JazzyViewPager jazzy) {
		mList = list;
		mJazzy = jazzy;
		mContext = context;
	}

	public void refreshAdapter(boolean isRefresh) {
		if (isRefresh) {
			notifyDataSetChanged();
		}
	}

	public void setData(ArrayList<InhaleView> list) {
		this.mList = list;
	}

	@Override
	public Object instantiateItem(ViewGroup container, final int position) {

		container.addView(mList.get(position), LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		mJazzy.setObjectForPosition(mList.get(position), position);
		final InhaleView view = mList.get(position);
		DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
		final int displayWidth = dm.widthPixels;
		final int displayHeight = dm.heightPixels;
		view.setFocusable(true);

		view.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				// TODO Auto-generated method stub

				int y = (int) arg1.getY();

				int action = arg1.getAction();
				switch (action) {
				case MotionEvent.ACTION_DOWN:
					x1 = (int) arg1.getX();
					break;
				case MotionEvent.ACTION_MOVE:
					break;
				case MotionEvent.ACTION_UP:
					int x2 = (int) arg1.getX();
					int abs = Math.abs(x2 - x1);
					if (abs < 5) {
						if (y < displayHeight * 0.37 && y > displayHeight *0.05) {
							Intent mIntent = new Intent(mContext,
									WeatherTrendActivity.class);
							mContext.startActivity(mIntent);
						}
					}
					break;
				default:
					break;
				}
				return true;
			}
		});

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

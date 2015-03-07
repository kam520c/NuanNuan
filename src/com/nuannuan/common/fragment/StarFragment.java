package com.nuannuan.common.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nuannuan.common.R;
import com.nuannuan.common.activity.HomeActivity;
import com.nuannuan.star.custom.controls.AllStarView;

public class StarFragment extends android.support.v4.app.Fragment {
	private HomeActivity home;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View star= inflater.inflate(R.layout.fragment_all_star,
				container, false);
		home = (HomeActivity) getActivity();
		initView(star);
		return star;
	}

	private void initView(View view) {
		AllStarView starView = (AllStarView) view
				.findViewById(R.id.allStarView);
		int screenWidth = home.getWindowManager().getDefaultDisplay()
				.getWidth();
		Log.v("==========screenWidth================", "==============="
				+ screenWidth);
		starView.setWidth(screenWidth);
	}
}

package com.nuannuan.mood.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.scau.feelingmusic.R;

public class LineEditActivity extends Activity {
	@Override 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 隐去标题栏（应用程序的名字）
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_line_edit);
		
		
	}
}

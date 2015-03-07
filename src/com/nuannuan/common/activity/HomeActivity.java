package com.nuannuan.common.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.nuannuan.common.R;
import com.nuannuan.common.fragment.AllMonthFragment;
import com.nuannuan.common.fragment.StarFragment;
import com.nuannuan.common.fragment.WeatherFragment;

public class HomeActivity extends FragmentActivity implements OnClickListener {

	/**
	 * 用于展示联系人的Fragment
	 */
	private WeatherFragment contactsFragment;
	/**
	 * 用于展示动态的Fragment
	 */
	private AllMonthFragment newsFragment;
	/**
	 * 用于展示设置的Fragment
	 */
	private StarFragment settingFragment;
	/**
	 * 消息界面布局
	 */

	private View contactsLayout;
	/**
	 * 动态界面布局
	 */
	private View newsLayout;
	/**
	 * 设置界面布局
	 */
	private View settingLayout;
	/**
	 * 在Tab布局上显示消息图标的控件
	 */

	private ImageView contactsImage;
	/**
	 * 在Tab布局上显示动态图标的控件
	 */
	private ImageView newsImage;
	/**
	 * 在Tab布局上显示设置图标的控件
	 */
	private ImageView settingImage;

	/**
	 * 在Tab布局上显示联系人标题的控件
	 */
	private TextView contactsText;
	/**
	 * 在Tab布局上显示动态标题的控件
	 */
	private TextView newsText;
	/**
	 * 在Tab布局上显示设置标题的控件
	 */
	private TextView settingText;
	/**
	 * 用于对Fragment进行管理
	 */
	private android.support.v4.app.FragmentManager fragmentManager;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		// initMap();
		// 初始化布局元素
		initViews();
		fragmentManager = this.getSupportFragmentManager();
		;
		;
		// 第一次启动时选中第0个tab
		setTabSelection(0);
	}

	/**
	 * 根据传入的index参数来设置选中的tab页。
	 * 
	 * @param index
	 *            每个tab页对应的下标。0表示消息，1表示联系人，2表示动态，3表示设置。
	 */
	public void setTabSelection(int index) {
		// 每次选中之前先清楚掉上次的选中状态
		clearSelection();
		// 开启一个Fragment事务
		android.support.v4.app.FragmentTransaction transaction = fragmentManager
				.beginTransaction();
		// 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
		// hideFragments(transaction);
		switch (index) {

		case 0:
			// 当点击了动态tab时，改变控件的图片和文字颜色
			newsImage.setImageResource(R.drawable.news_selected);
			newsText.setTextColor(getResources().getColor(R.color.white));
			contactsFragment = new WeatherFragment();
			transaction.replace(R.id.content, contactsFragment);
			break;
		case 1:
			// 当点击了联系人tab时，改变控件的图片和文字颜色
			contactsImage.setImageResource(R.drawable.contacts_selected);
			contactsText.setTextColor(getResources().getColor(R.color.white));
			newsFragment = new AllMonthFragment();
			transaction.replace(R.id.content, newsFragment);
			break;
		case 2:
			// 当点击了设置tab时，改变控件的图片和文字颜色
			settingImage.setImageResource(R.drawable.setting_selected);
			settingText.setTextColor(getResources().getColor(R.color.white));
			// if (settingFragment == null) {
			// 如果SettingFragment为空，则创建一个并添加到界面上
			settingFragment = new StarFragment();
			transaction.replace(R.id.content, settingFragment);
			break;
		default:
			break;
		}
		transaction.commitAllowingStateLoss();
	}

	/**
	 * 清除掉所有的选中状态。
	 */
	private void clearSelection() {

		contactsImage.setImageResource(R.drawable.contacts_unselected);
		contactsText.setTextColor(getResources().getColor(R.color.purple));
		newsImage.setImageResource(R.drawable.news_unselected);
		newsText.setTextColor(getResources().getColor(R.color.purple));
		settingImage.setImageResource(R.drawable.setting_unselected);
		settingText.setTextColor(getResources().getColor(R.color.purple));
	}

	@Override
	protected void onDestroy() {

		super.onDestroy();
	}

	@Override
	protected void onPause() {
		super.onPause();
		// mMapView.pause();
	}

	@Override
	protected void onResume() {
		Intent mIntent = getIntent();
		int tab = mIntent.getIntExtra("tab", 0);
		setTabSelection(tab);
		super.onResume();
		// mMapView.unpause();
	}

	/**
	 * 在这里获取到每个需要用到的控件的实例，并给它们设置好必要的点击事件。
	 */
	private void initViews() {
		contactsLayout = findViewById(R.id.contacts_layout);
		newsLayout = findViewById(R.id.news_layout);
		settingLayout = findViewById(R.id.setting_layout);
		contactsImage = (ImageView) findViewById(R.id.contacts_image);
		newsImage = (ImageView) findViewById(R.id.news_image);
		settingImage = (ImageView) findViewById(R.id.setting_image);
		contactsText = (TextView) findViewById(R.id.contacts_text);
		newsText = (TextView) findViewById(R.id.news_text);
		settingText = (TextView) findViewById(R.id.setting_text);
		contactsLayout.setOnClickListener(this);
		newsLayout.setOnClickListener(this);
		settingLayout.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.contacts_layout:
			// 当点击了联系人tab时，选中第2个tab
			setTabSelection(1);
			break;
		case R.id.news_layout:
			// 当点击了动态tab时，选中第3个tab
			setTabSelection(0);
			break;
		case R.id.setting_layout:
			// 当点击了设置tab时，选中第4个tab
			setTabSelection(2);
			break;
		default:
			break;
		}
	}

}
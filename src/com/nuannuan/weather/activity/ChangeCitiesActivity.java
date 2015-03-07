package com.nuannuan.weather.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.nuannuan.common.R;
import com.nuannuan.common.activity.HomeActivity;
import com.nuannuan.common.app.NuanNuanApp;
import com.nuannuan.common.bean.ChangeCitiesItemBean;
import com.nuannuan.weather.adapter.ChangeCitiesItemAdapter;
import com.nuannuan.weather.custom.controls.DragGridView;
import com.nuannuan.weather.custom.controls.DragGridView.OnChanageListener;
import com.nuannuan.weather.utility.WeatherCondition;
import com.nuannuan.weather.utility.WeatherParser;
import com.nuannuan.weather.utility.WeatherUtility;

public class ChangeCitiesActivity extends Activity implements OnClickListener {

	private DragGridView gridView;
	private JSONArray city = new JSONArray();
	private List<ChangeCitiesItemBean> changeCitiesItem = null;

	private TextView test;
	private TextView title;
//	private Button btn_return;
//	private Button draw_refresh;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏顶部title
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_changecities);
		gridView = (DragGridView) findViewById(R.id.draggridview);
		title = (TextView) findViewById(R.id.include_addmood_title);
		// btn_return = (Button) findViewById(R.id.btn_return);
		// draw_refresh = (Button) findViewById(R.id.btn_refresh_city);
		changeCitiesItem = new ArrayList<ChangeCitiesItemBean>();
		title.setText(R.string.manage_city);
		// btn_return.setOnClickListener(this);
		// draw_refresh.setOnClickListener(this);
		final NuanNuanApp app = (NuanNuanApp) getApplication();
		LinkedHashMap<String, WeatherParser> mapCity = app.getMapCity();
		if (mapCity != null) {
            
			Set<String> setCity = mapCity.keySet();
			boolean isDay = WeatherUtility.isDay();
			String weather = null ;
			String temp = null ;
			int image = R.drawable.weather_sunny;
			for (String city : setCity) {
				WeatherParser weatherParser = mapCity.get(city);
				WeatherCondition weatherCondition =weatherParser.getWeather(0);
				if(isDay){
				weather = weatherCondition.status1;
				temp =weatherCondition.tgd1;
				}
				else{
					weather = weatherCondition.status2;
					temp =weatherCondition.tgd2;
				}
				image =WeatherUtility.getIcon(weather, isDay);
				ChangeCitiesItemBean city0 = new ChangeCitiesItemBean(city,
						image, weather, temp + "℃");
				changeCitiesItem.add(city0);
			}
			if (setCity.size() < 9) {
				ChangeCitiesItemBean city8 = new ChangeCitiesItemBean(null,
						R.drawable.bg_changecity_add, "", "");
				changeCitiesItem.add(city8);
			}
		}

		String city_str = null;
		for (ChangeCitiesItemBean bean : changeCitiesItem) {
			city_str = bean.getTitle();
			city.put(city_str);
		}
		final ChangeCitiesItemAdapter adapter = new ChangeCitiesItemAdapter(
				changeCitiesItem, this);
		gridView.setAdapter(adapter);

		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				Toast.makeText(ChangeCitiesActivity.this,
						"item" + (position + 1), Toast.LENGTH_SHORT).show();
				if (changeCitiesItem.size() == position + 1) {
					Intent intent = new Intent();
					intent.putExtra("city0", city.toString());
					intent.setClass(ChangeCitiesActivity.this,
							AddCitiesActivity.class);
					startActivity(intent);
					finish();
				}
			}
		});

		gridView.setOnChangeListener(new OnChanageListener() {

			@Override
			public void onChange(int from, int to) {
				ChangeCitiesItemBean temp = changeCitiesItem.get(from);
				JSONArray cities = new JSONArray();

				// 直接交互item
				// dataSourceList.set(from, dataSourceList.get(to));
				// dataSourceList.set(to, temp);

				// 这里的处理需要注意下
				if (to != city.length() - 1 && from != city.length() - 1) {
					if (from < to) {
						for (int i = from; i < to; i++) {
							Collections.swap(changeCitiesItem, i, i + 1);
						}
					} else if (from > to) {
						for (int i = from; i > to; i--) {
							Collections.swap(changeCitiesItem, i, i - 1);
						}
					}

					String city_str = null;
					for (ChangeCitiesItemBean bean : changeCitiesItem) {
						city_str = bean.getTitle();
						cities.put(city_str);
					}

					JSONArray arry = new JSONArray();
					for (int i = 0; i < city.length(); i++) {
						try {
							arry.put(cities.get(i));
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					changeCitiesItem.set(to, temp);

					adapter.notifyDataSetChanged();
					WeatherUtility.changeCity(cities, app);
				}

			}
		});
	}

	@Override
	public void onClick(View v) {
//		// TODO Auto-generated method stub
//		Intent intent = new Intent();
//		switch (v.getId()) {
//		case R.id.btn_back:
//			// TODO Auto-generated method stub
//			intent.setClass(ChangeCitiesActivity.this, HomeActivity.class);
//			startActivity(intent);
//			// mUtility.StartActivity(this, TabActivity.class);
//			finish();
//			break;
//		// case R.id.btn_refresh_city:
//		// intent.setClass(ChangeCitiesActivity.this, HomeActivity.class);
//		// startActivity(intent);
//		// // mUtility.StartActivity(this, FriendsActivity.class);
//		// finish();
//		// break;
//		default:
//			break;
//		}
	}

}

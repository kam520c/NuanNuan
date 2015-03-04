package com.nuannuan.weather.activity;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;
import android.widget.Toast;

import com.nuannuan.weather.adapter.ImageAdapter;
import com.nuannuan.weather.custom.controls.GalleryView;
import com.scau.feelingmusic.R;

public class AddCityActivity extends Activity {

	private TextView tvTitle; 	
	private GalleryView gallery; 	
	private ImageAdapter adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.grallery_layout);

		initRes();
	}
	
	private void initRes(){
		tvTitle = (TextView) findViewById(R.id.tvTitle);
		gallery = (GalleryView) findViewById(R.id.mygallery);


		int screenHeight = this.getWindowManager().getDefaultDisplay()
				.getHeight();
		adapter = new ImageAdapter(this,screenHeight); 	
		adapter.createReflectedImages();
		gallery.setAdapter(adapter);
		
		gallery.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				tvTitle.setText(adapter.titles[position]);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});

		gallery.setOnItemClickListener(new OnItemClickListener() {			// ���õ���¼�����
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(AddCityActivity.this, "img " + (position+1) + " selected", Toast.LENGTH_SHORT).show();
			}
		});
	}
}
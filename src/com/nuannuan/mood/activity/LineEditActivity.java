package com.nuannuan.mood.activity;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nuannuan.common.R;
import com.nuannuan.common.activity.HomeActivity;
import com.nuannuan.common.utility.AnimationUtil;
import com.nuannuan.mood.custom.controls.LineEditText;
import com.nuannuan.mood.utility.MoodUtility;

public class LineEditActivity extends Activity implements OnClickListener {
	private Button deleteBtn;
	private RelativeLayout mButton;
	private String strMood = null;
	private LineEditText lineEdit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 隐去标题栏（应用程序的名字）
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_note_paper);

		Intent mIntent = getIntent();
		strMood = mIntent.getStringExtra("mood");
		Log.e("=======strMood=========", "" + strMood);
		initView();
	}

	private void initView() {
		deleteBtn = (Button) findViewById(R.id.deleteNote);
		deleteBtn.setOnClickListener(this);

		LinearLayout backBtn = (LinearLayout) findViewById(R.id.btn_back);
		backBtn.setOnClickListener(this);

		mButton = (RelativeLayout) findViewById(R.id.btn_add);
		mButton.setOnClickListener(this);

		lineEdit = (LineEditText) findViewById(R.id.edit_mood);

		TextView mTextView = (TextView) findViewById(R.id.include_addmood_title);
		mTextView.setText("心情笔记");

		Button BtnBack = (Button) findViewById(R.id.all_back);
		BtnBack.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.deleteNote:
			deleteNoteAm(lineEdit);
			break;
		case R.id.btn_add:
			String mood = "";
			mood = lineEdit.getText().toString();
			JSONObject json = null;
			if (strMood != null) {
				try {
					json = new JSONObject(strMood);
					json.put("mood", mood);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (json != null) {
			
				MoodUtility.ThatDayMoodForSave(this, json.toString());
				Intent intent = new Intent(this, HomeActivity.class);
				intent.putExtra("tab", 1);
				startActivity(intent);
				this.finish();

			} else {
				Toast.makeText(this, "储存失败请重试!", Toast.LENGTH_SHORT).show();
			}

			break;
		case R.id.btn_back:
			finish();
			break;
		case R.id.all_back:
			finish();
			break;
		default:
			break;
		}
	}

	/**
	 * 组合动画实现LineEditText是动画效果
	 * 
	 * @param
	 */
	private void deleteNoteAm(final LineEditText v) {
		Animation alpha = AnimationUtil.getAlphaAm(1.0f, 0.0f, 1000);
		Animation rotate = AnimationUtil.getRotateAm(0, 360, 600);
		Animation scale = AnimationUtil
				.getScaleAm(1.0f, 0.0f, 1.0f, 0.0f, 1000);
		AnimationSet set = new AnimationSet(false);
		set.addAnimation(rotate);
		set.addAnimation(alpha);
		set.addAnimation(scale);
		v.setAnimation(set);
		v.startAnimation(set);
		set.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				v.setText("");
			}
		});
	}
}

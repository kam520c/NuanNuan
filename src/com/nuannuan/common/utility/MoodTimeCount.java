package com.nuannuan.common.utility;

import android.content.Context;
import android.os.CountDownTimer;
import android.widget.Toast;

import com.nuannuan.common.R;
import com.nuannuan.mood.utility.MoodUtility;

public class MoodTimeCount extends CountDownTimer {

	public boolean isSaveGif = true;
	private Context mContext;
	private int num;
	private String mood;
	
	public MoodTimeCount(long millisInFuture, long countDownInterval,
			Context context) {
		super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
		mContext = context;
	}

	@Override
	public void onFinish() {// 计时完毕时触发
		isSaveGif = true;
		String[] listData = mContext.getResources().getStringArray(
				R.array.gif_name);
		if (num - 1 < 0) {
		} else {
			
			MoodUtility.ThatDayMoodForSave(mContext, mood);
			Toast.makeText(mContext, "心情已更改", Toast.LENGTH_SHORT)
					.show();
		}

	}

	@Override
	public void onTick(long millisUntilFinished) {// 计时过程显示
	}

	public void setNum(int num) {
		this.num = num;
	}
	
	public void setMood(String mood) {
		this.mood = mood;
	}
}

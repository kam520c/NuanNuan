package com.nuannuan.common.utility;

import android.os.CountDownTimer;
import android.widget.Button;

public class RotationTimeCount extends CountDownTimer{

	public boolean isGifClick = false;
//	public TimeCount(long millisInFuture, long countDownInterval) {
//		super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
//	}

	public RotationTimeCount(long millisInFuture, long countDownInterval) {
		super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
//		this.AutoCode=AutoCode;
	}
	
	@Override
	public void onFinish() {// 计时完毕时触发
		
	}

	@Override
	public void onTick(long millisUntilFinished) {// 计时过程显示
//		AutoCode.setClickable(false);
//		AutoCode.setText(millisUntilFinished / 1000 + "秒");
		isGifClick = true;
	}
}

package com.nuannuan.common.utility;

import android.os.CountDownTimer;
import android.widget.Button;

public class TimeCount extends CountDownTimer{

	public boolean isLoad;
//	public TimeCount(long millisInFuture, long countDownInterval) {
//		super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
//	}

	public TimeCount(long millisInFuture, long countDownInterval,Button AutoCode) {
		super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
//		this.AutoCode=AutoCode;
	}
	
	@Override
	public void onFinish() {// 计时完毕时触发
//		AutoCode.setText(R.string.resetpassword_btn_auth_code);
//		AutoCode.setClickable(true);
	}

	@Override
	public void onTick(long millisUntilFinished) {// 计时过程显示
//		AutoCode.setClickable(false);
//		AutoCode.setText(millisUntilFinished / 1000 + "秒");
	}
}

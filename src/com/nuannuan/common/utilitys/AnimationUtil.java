package com.nuannuan.common.utilitys;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

public class AnimationUtil {
	/**
	 * 
	 * @param fromX
	 *            动画起始的宽
	 * @param toX
	 *            动画结束的宽
	 * @param fromY
	 *            动画起始的高
	 * @param toY
	 *            动画结束的高
	 * @param time
	 *            动画时间
	 * @return
	 */
	public static Animation getScaleAm(float fromX, float toX, float fromY,
			float toY, long time) {
		ScaleAnimation myAnimation_Scale = new ScaleAnimation(fromX, toX,
				fromY, toY, Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f);
		myAnimation_Scale.setFillAfter(false);
		myAnimation_Scale.setDuration(time);
		return myAnimation_Scale;
	}

	/**
	 * 
	 * @param fromX
	 *            动画起始的x坐标
	 * @param toX
	 *            动画结束的x坐标
	 * @param fromY
	 *            动画起始的y坐标
	 * @param toY
	 *            动画结束的y坐标
	 * @param time
	 *            动画时间
	 * @return
	 */
	public static Animation getTranslateAm(float fromX, float toX, float fromY,
			float toY, long time) {
		TranslateAnimation animation = new TranslateAnimation(fromX, toX,
				fromY, toY);
		animation.setFillAfter(true);
		animation.setDuration(time);
		return animation;
	}

	/**
	 * 透明变化的动画效果
	 * 
	 * @param from
	 *            1是最大
	 * @param to
	 *            0是最小
	 * @param time
	 *            动画时间
	 * @return
	 */
	public static Animation getAlphaAm(float from, float to, long time) {
		AlphaAnimation myAnimation_Alpha = new AlphaAnimation(from, to);
		myAnimation_Alpha.setFillAfter(true);
		myAnimation_Alpha.setDuration(time);
		return myAnimation_Alpha;
	}

	/**
	 * 旋转的动画效果
	 * 
	 * @param from
	 *            角度
	 * @param to
	 *            角度
	 * @param time
	 *            动画时间
	 * @return
	 */
	public static Animation getRotateAm(float from, float to, long time) {
		RotateAnimation rotate = new RotateAnimation(from, to,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		rotate.setDuration(time);
		rotate.setFillAfter(true);
		return rotate;
	}
}

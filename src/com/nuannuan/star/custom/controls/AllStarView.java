package com.nuannuan.star.custom.controls;

import org.json.JSONArray;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import com.nuannuan.common.asynctask.AsyncEvent;
import com.nuannuan.star.activity.StarFortuneActivity;
import com.nuannuan.star.asynctask.StarAsyncTaskHelper;
import com.nuannuan.star.interfaces.StarAsyncEventIm;
import com.scau.feelingmusic.R;

/**
 * 
 * @author Dave
 * 
 */
public class AllStarView extends ViewGroup implements View.OnClickListener {

	private int mSelectView = -1;
	private Context c;
	private int width;
	private int heigth;
	private int Radius;
	private int starRadius;
	private int centerX = width / 2;
	private int centerY = heigth / 2;
	private boolean isButtonShow = false;

	private ImageView center;
	// private ImageView centerAdd;
	private ImageView baiyang;
	private ImageView jinniu;
	private ImageView shuangzi;
	private ImageView juxie;
	private ImageView shizi;
	private ImageView chunv;
	private ImageView tianping;
	private ImageView tianxie;
	private ImageView sheshou;
	private ImageView mojie;
	private ImageView shuiping;
	private ImageView shuangyu;
	private ViewGroup gp;
	private View view;
	private int centerRadius;
	private int pxWidth;

	public AllStarView(Context context) {
		super(context);
		this.c = context;
		init();

	}

	public AllStarView(Context context, AttributeSet attr) {
		super(context, attr);
		this.c = context;
		init();

	}

	private void init() {

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		setMeasuredDimension(width, heigth);
	}

	public void setWidth(int width) {
		this.width = width;
		heigth = width + width / 3;
		starRadius = width / 12;
		centerRadius = width / 10;
		pxWidth = centerRadius - starRadius;
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub

		if (mSelectView != -1) {
			View v = getChildAt(mSelectView);
			if (v != null)
				// v.layout(x, y, x + 300, y + 500);
				return;
		}

		Radius = (int) (width / 2 - width / 10);
		int PX;
		int PY;
		centerX = width / 2;
		centerY = heigth / 2;

		center = new ImageView(c);
		center.setBackgroundResource(R.drawable.show);
		center.layout(centerX - centerRadius, centerY - centerRadius, centerX
				+ centerRadius, centerY + centerRadius);
		center.setOnClickListener(this);
		center.setId(12);

		// centerAdd = new ImageView(c);
		// centerAdd.setBackgroundResource(R.drawable.center_add);
		// centerAdd
		// .layout(centerX - 25, centerY - 25, centerX + 25, centerY + 25);
		// centerAdd.setId(13);

		baiyang = new ImageView(c);
		baiyang.setBackgroundResource(R.drawable.star_1_baiyang);
		PX = (int) (Radius * Math.cos(getRadian(60)));
		PY = (int) (Radius * Math.sin(getRadian(60)));
		baiyang.layout(centerX + PX - starRadius, centerY - PY - starRadius,
				centerX + PX + starRadius, centerY - PY + starRadius);
		baiyang.setId(0);
		baiyang.setOnClickListener(this);
		baiyang.setVisibility(View.INVISIBLE);

		jinniu = new ImageView(c);
		jinniu.setBackgroundResource(R.drawable.star_2_jin_niu);
		PX = (int) (Radius * Math.cos(getRadian(30)));
		PY = (int) (Radius * Math.sin(getRadian(30)));
		jinniu.layout(centerX + PX - starRadius, centerY - PY - starRadius,
				centerX + PX + starRadius, centerY - PY + starRadius);
		jinniu.setId(1);
		jinniu.setVisibility(View.INVISIBLE);
		jinniu.setOnClickListener(this);

		shuangzi = new ImageView(c);
		shuangzi.setBackgroundResource(R.drawable.star_3_shuang_zi);
		PX = (int) (Radius * Math.cos(getRadian(0)));
		PY = (int) (Radius * Math.sin(getRadian(0)));
		shuangzi.layout(centerX + PX - starRadius, centerY - PY - starRadius,
				centerX + PX + starRadius, centerY - PY + starRadius);
		shuangzi.setId(2);
		shuangzi.setVisibility(View.INVISIBLE);
		shuangzi.setOnClickListener(this);

		juxie = new ImageView(c);
		juxie.setBackgroundResource(R.drawable.star_4_ju_xie);
		PX = (int) (Radius * Math.cos(getRadian(30)));
		PY = (int) (-Radius * Math.sin(getRadian(30)));
		juxie.layout(centerX + PX - starRadius, centerY - PY - starRadius,
				centerX + PX + starRadius, centerY - PY + starRadius);
		juxie.setId(3);
		juxie.setVisibility(View.INVISIBLE);
		juxie.setOnClickListener(this);

		shizi = new ImageView(c);
		shizi.setBackgroundResource(R.drawable.star_5_shi_zi);
		PX = (int) (Radius * Math.cos(getRadian(60)));
		PY = (int) (-Radius * Math.sin(getRadian(60)));
		shizi.layout(centerX + PX - starRadius, centerY - PY - starRadius,
				centerX + PX + starRadius, centerY - PY + starRadius);
		shizi.setId(4);
		shizi.setVisibility(View.INVISIBLE);
		shizi.setOnClickListener(this);

		chunv = new ImageView(c);
		chunv.setBackgroundResource(R.drawable.star_6_chu_nv);
		PX = (int) (Radius * Math.cos(getRadian(90)));
		PY = (int) (-Radius * Math.sin(getRadian(90)));
		chunv.layout(centerX + PX - starRadius, centerY - PY - starRadius,
				centerX + PX + starRadius, centerY - PY + starRadius);
		chunv.setId(5);
		chunv.setVisibility(View.INVISIBLE);
		chunv.setOnClickListener(this);

		tianping = new ImageView(c);
		tianping.setBackgroundResource(R.drawable.star_7_tian_ping);
		PX = (int) (-Radius * Math.cos(getRadian(60)));
		PY = (int) (-Radius * Math.sin(getRadian(60)));
		tianping.layout(centerX + PX - starRadius, centerY - PY - starRadius,
				centerX + PX + starRadius, centerY - PY + starRadius);
		tianping.setId(6);
		tianping.setVisibility(View.INVISIBLE);
		tianping.setOnClickListener(this);

		tianxie = new ImageView(c);
		tianxie.setBackgroundResource(R.drawable.star_8_tian_xie);
		PX = (int) (-Radius * Math.cos(getRadian(30)));
		PY = (int) (-Radius * Math.sin(getRadian(30)));
		tianxie.layout(centerX + PX - starRadius, centerY - PY - starRadius,
				centerX + PX + starRadius, centerY - PY + starRadius);
		tianxie.setId(7);
		tianxie.setVisibility(View.INVISIBLE);
		tianxie.setOnClickListener(this);

		sheshou = new ImageView(c);
		sheshou.setBackgroundResource(R.drawable.star_9_she_shou);
		PX = (int) (-Radius * Math.cos(getRadian(0)));
		PY = (int) (-Radius * Math.sin(getRadian(0)));
		sheshou.layout(centerX + PX - starRadius, centerY - PY - starRadius,
				centerX + PX + starRadius, centerY - PY + starRadius);
		sheshou.setId(8);
		sheshou.setVisibility(View.INVISIBLE);
		sheshou.setOnClickListener(this);

		mojie = new ImageView(c);
		mojie.setBackgroundResource(R.drawable.star_10_mo_jie);
		PX = (int) (-Radius * Math.cos(getRadian(30)));
		PY = (int) (Radius * Math.sin(getRadian(30)));
		mojie.layout(centerX + PX - starRadius, centerY - PY - starRadius,
				centerX + PX + starRadius, centerY - PY + starRadius);
		mojie.setId(9);
		mojie.setVisibility(View.INVISIBLE);
		mojie.setOnClickListener(this);

		shuiping = new ImageView(c);
		shuiping.setBackgroundResource(R.drawable.star_11_shui_ping);
		PX = (int) (-Radius * Math.cos(getRadian(60)));
		PY = (int) (Radius * Math.sin(getRadian(60)));
		shuiping.layout(centerX + PX - starRadius, centerY - PY - starRadius,
				centerX + PX + starRadius, centerY - PY + starRadius);
		shuiping.setId(10);
		shuiping.setVisibility(View.INVISIBLE);
		shuiping.setOnClickListener(this);

		shuangyu = new ImageView(c);
		shuangyu.setBackgroundResource(R.drawable.star_12_shuang_yu);
		PX = (int) (-Radius * Math.cos(getRadian(90)));
		PY = (int) (Radius * Math.sin(getRadian(90)));
		shuangyu.layout(centerX + PX - starRadius, centerY - PY - starRadius,
				centerX + PX + starRadius, centerY - PY + starRadius);
		shuangyu.setId(11);
		shuangyu.setVisibility(View.INVISIBLE);
		shuangyu.setOnClickListener(this);

		// if (changed) {
		addView(baiyang);
		addView(jinniu);
		addView(shuangzi);
		addView(juxie);
		addView(shizi);
		addView(chunv);

		addView(tianping);
		addView(tianxie);
		addView(sheshou);
		addView(mojie);
		addView(shuiping);
		addView(shuangyu);

		addView(center);
		// addView(centerAdd);
		// }
	}

	private double getRadian(int angle) {
		double radian = 3.14159 * angle / 180;
		return radian;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case 0:
			clickStarButton(v);
			break;
		case 1:
			clickStarButton(v);
			break;
		case 2:
			clickStarButton(v);
			break;
		case 3:
			clickStarButton(v);
			break;
		case 4:
			clickStarButton(v);
			break;
		case 5:
			clickStarButton(v);
			break;
		case 6:
			clickStarButton(v);
			break;
		case 7:
			clickStarButton(v);
			break;
		case 8:
			clickStarButton(v);
			break;
		case 9:
			clickStarButton(v);
			break;
		case 10:
			clickStarButton(v);
			break;
		case 11:
			clickStarButton(v);
			break;
		case 12:
			if (!isButtonShow) {
				clickCenterOut();
			} else {
				clickCenterIn();
			}
			isButtonShow = !isButtonShow;
			break;

		default:
			break;

		}
	}

	/**
	 * ������������ʱ����֮���ִ��չ���Ķ���Ч��
	 */
	private void clickCenterOut() {

		Animation rotate = getRotateAm(0, 720, 2000);
		rotate.setDuration(2000);

		Animation myAnimation_Scale = getScaleAm(0.0f, 4.6f, 0.0f, 4.6f, 2500);

		AnimationSet set = new AnimationSet(false);
		set.addAnimation(myAnimation_Scale);
		set.addAnimation(rotate);
		center.setAnimation(set);
		center.startAnimation(set);

		Animation animation = null;
		for (int i = 0; i < 12; i++) {
			view = getChildAt(i);

			animation = getTranslateAm(centerX - view.getLeft() - starRadius,
					0, centerY - view.getTop() - starRadius, 0, 300);

			animation.setStartOffset(i * 100);
			animation.setInterpolator(new OvershootInterpolator(2F));

			view.startAnimation(animation);

		}

		animation.setAnimationListener(new AnimationListener() {

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

				// view.clearAnimation();
				for (int i = 0; i < 12; i++) {
					View views = getChildAt(i);
					// animation = MyAnimations.getRotateAnimation(0,
					// 720,
					// 600);
					animation = getRotateAm(0, 720, 600);
					views.startAnimation(animation);
				}

				for (int i = 0; i < 12; i++) {
					View views = getChildAt(i);
					views.setVisibility(View.VISIBLE);
					views.setClickable(true);
				}

			}
		});

	}

	/**
	 * ������չ����ʱ����֮���ִ�������Ķ���Ч��
	 */
	private void clickCenterIn() {

		Animation mAnimation = getScaleAm(4.6f, 0f, 4.6f, 0f, 1800);
		AnimationSet set = new AnimationSet(false);
		set.addAnimation(mAnimation);
		// set.addAnimation(rotate);
		center.setAnimation(set);
		center.startAnimation(set);
		Animation animation;
		for (int i = 0; i < 12; i++) {
			view = getChildAt(i);
			animation = getTranslateAm(0,
					centerX - view.getLeft() - starRadius, 0,
					centerY - view.getTop() - starRadius, 300);
			animation.setStartOffset(i * 100);
			animation.setInterpolator(new AnticipateInterpolator(2F));
			view.startAnimation(animation);
		}
		for (int i = 0; i < 12; i++) {
			View views = getChildAt(i);
			views.setVisibility(View.INVISIBLE);
			views.setClickable(false);
		}

	}

	/**
	 * ִ��������֮����¼�
	 * 
	 * @param v
	 */
	private void clickStarButton(final View v) {
		Animation alpha = getAlphaAm(1.0f, 0.0f, 800);
		Animation scale = getScaleAm(0.0f, 1.6f, 0.0f, 1.6f, 400);
		AnimationSet set = new AnimationSet(false);
		set.addAnimation(scale);
		set.addAnimation(alpha);
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
				 StarAsyncTaskHelper helper = new StarAsyncTaskHelper(c,
				 new StarAsyncEventIm() {
				
				 @Override
				 public void DataLoadSuccess(JSONArray arry) {
				 // TODO Auto-generated method stub
				 Log.v("========arry=========", ""+arry);
				 Intent mIntent = new Intent();
				 mIntent.setClass(c, StarFortuneActivity.class);
				
				 mIntent.putExtra("json", arry.toString());
				 c.startActivity(mIntent);
				 c.fileList();
				
				 }
				
				 @Override
				 public void DataLoadFaild() {
				 // TODO Auto-generated method stub
				 Toast.makeText(c, "网络问题，请重新点击", Toast.LENGTH_SHORT).show();
				 }
				 });
				 
				 helper.execute(v.getId() + "");
			}
		});

	}

	/**
	 * 
	 * @param fromX
	 * @param toX
	 * @param fromY
	 * @param toY
	 * @param time
	 * @return �õ����Ŷ���
	 */
	private Animation getScaleAm(float fromX, float toX, float fromY,
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
	 * @param toX
	 * @param fromY
	 * @param toY
	 * @param time
	 * @return �õ�λ�ƶ���
	 */
	private Animation getTranslateAm(float fromX, float toX, float fromY,
			float toY, long time) {
		TranslateAnimation animation = new TranslateAnimation(fromX, toX,
				fromY, toY);
		animation.setFillAfter(true);
		animation.setDuration(time);
		return animation;
	}

	/**
	 * 
	 * @param from
	 * @param to
	 * @param time
	 * @return �õ�͸���仯�Ķ���
	 */
	private Animation getAlphaAm(float from, float to, long time) {
		AlphaAnimation myAnimation_Alpha = new AlphaAnimation(from, to);
		myAnimation_Alpha.setFillAfter(true);
		myAnimation_Alpha.setDuration(time);
		return myAnimation_Alpha;
	}

	/**
	 * 
	 * @param from
	 * @param to
	 * @param time
	 * @return �õ���ת�Ķ���
	 */
	private Animation getRotateAm(float from, float to, long time) {
		RotateAnimation rotate = new RotateAnimation(from, to,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		rotate.setDuration(time);
		rotate.setFillAfter(true);
		return rotate;
	}

}

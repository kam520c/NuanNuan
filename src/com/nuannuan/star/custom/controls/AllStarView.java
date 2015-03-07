package com.nuannuan.star.custom.controls;

import org.json.JSONArray;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import com.nuannuan.common.R;
import com.nuannuan.common.utility.AnimationUtil;
import com.nuannuan.star.activity.StarFortuneActivity;
import com.nuannuan.star.asynctask.StarAsyncTaskHelper;
import com.nuannuan.star.interfaces.StarAsyncEventIm;

/**
 * 
 * @author Kam & Eden
 * 
 */
public class AllStarView extends ViewGroup implements View.OnClickListener {

	private int mSelectView = -1;
	private Context c;
	private int width;
	private int heigth;
	private int Radius;
	private int starRadius;//12个星座的半径
	private int centerX = width / 2;//控件中心点的宽
	private int centerY = heigth / 2;//控件中心点的高
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

	/**
	 * 构造函数，初始化自定义控件
	 * @param context指向activity的指针
	 */
	public AllStarView(Context context) {
		super(context);
		this.c = context;

	}
	
	/**
	 * 构造函数，初始化自定义控件
	 * @param context指向activity的指针
	 */
	public AllStarView(Context context, AttributeSet attr) {
		super(context, attr);
		this.c = context;

	}

	/**
	 * 设定整个控件的大小
	 * @param widthMeasureSpec 是xml里面传进来的宽度
	 * @param heightMeasureSpec 是xml里面传进来的高度
	 */
	@Override  //继承类或者接口里面的抽象方法，可选择性实现，系统自动调用
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		//set控件的大小，整块
		setMeasuredDimension(width, heigth);
	}

	/**
	 * set自定义控件的高度和宽度
	 * @param width 为获取到的手机屏幕的宽
	 */
	public void setWidth(int width) {
		this.width = width;
		heigth = width ;
		starRadius = width / 12;//12个星座的半径大小，可变
		centerRadius = width / 10; //中心点的半径大小
		pxWidth = centerRadius - starRadius;
	}

	/**
	 * 设定各星座的坐标位置，layout是来控制ltrb，控制每个星座的具体位置和大小
	 * @param changed 子控件是否改变位置
	 * @param l 左
	 * @param r 右
	 * @param t 上
	 * @param b 下
	 */
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
		PX = (int) (Radius * Math.cos(getRadian(75)));
		PY = (int) (Radius * Math.sin(getRadian(75)));
		
		baiyang.layout(centerX + PX - starRadius, centerY - PY - starRadius,
				centerX + PX + starRadius, centerY - PY + starRadius);
		baiyang.setId(0);
		baiyang.setOnClickListener(this);
		baiyang.setVisibility(View.INVISIBLE);

		jinniu = new ImageView(c);
		jinniu.setBackgroundResource(R.drawable.star_2_jin_niu);
		PX = (int) (Radius * Math.cos(getRadian(45)));
		PY = (int) (Radius * Math.sin(getRadian(45)));
		jinniu.layout(centerX + PX - starRadius, centerY - PY - starRadius,
				centerX + PX + starRadius, centerY - PY + starRadius);
		jinniu.setId(1);
		jinniu.setVisibility(View.INVISIBLE);
		jinniu.setOnClickListener(this);

		shuangzi = new ImageView(c);
		shuangzi.setBackgroundResource(R.drawable.star_3_shuang_zi);
		PX = (int) (Radius * Math.cos(getRadian(15)));
		PY = (int) (Radius * Math.sin(getRadian(15)));
		shuangzi.layout(centerX + PX - starRadius, centerY - PY - starRadius,
				centerX + PX + starRadius, centerY - PY + starRadius);
		shuangzi.setId(2);
		shuangzi.setVisibility(View.INVISIBLE);
		shuangzi.setOnClickListener(this);

		juxie = new ImageView(c);
		juxie.setBackgroundResource(R.drawable.star_4_ju_xie);
		PX = (int) (Radius * Math.cos(getRadian(15)));
		PY = (int) (-Radius * Math.sin(getRadian(15)));
		juxie.layout(centerX + PX - starRadius, centerY - PY - starRadius,
				centerX + PX + starRadius, centerY - PY + starRadius);
		juxie.setId(3);
		juxie.setVisibility(View.INVISIBLE);
		juxie.setOnClickListener(this);

		shizi = new ImageView(c);
		shizi.setBackgroundResource(R.drawable.star_5_shi_zi);
		PX = (int) (Radius * Math.cos(getRadian(45)));
		PY = (int) (-Radius * Math.sin(getRadian(45)));
		shizi.layout(centerX + PX - starRadius, centerY - PY - starRadius,
				centerX + PX + starRadius, centerY - PY + starRadius);
		shizi.setId(4);
		shizi.setVisibility(View.INVISIBLE);
		shizi.setOnClickListener(this);

		chunv = new ImageView(c);
		chunv.setBackgroundResource(R.drawable.star_6_chu_nv);
		PX = (int) (Radius * Math.cos(getRadian(75)));
		PY = (int) (-Radius * Math.sin(getRadian(75)));
		chunv.layout(centerX + PX - starRadius, centerY - PY - starRadius,
				centerX + PX + starRadius, centerY - PY + starRadius);
		chunv.setId(5);
		chunv.setVisibility(View.INVISIBLE);
		chunv.setOnClickListener(this);

		tianping = new ImageView(c);
		tianping.setBackgroundResource(R.drawable.star_7_tian_ping);
		PX = (int) (-Radius * Math.cos(getRadian(75)));
		PY = (int) (-Radius * Math.sin(getRadian(75)));
		tianping.layout(centerX + PX - starRadius, centerY - PY - starRadius,
				centerX + PX + starRadius, centerY - PY + starRadius);
		tianping.setId(6);
		tianping.setVisibility(View.INVISIBLE);
		tianping.setOnClickListener(this);

		tianxie = new ImageView(c);
		tianxie.setBackgroundResource(R.drawable.star_8_tian_xie);
		PX = (int) (-Radius * Math.cos(getRadian(45)));
		PY = (int) (-Radius * Math.sin(getRadian(45)));
		tianxie.layout(centerX + PX - starRadius, centerY - PY - starRadius,
				centerX + PX + starRadius, centerY - PY + starRadius);
		tianxie.setId(7);
		tianxie.setVisibility(View.INVISIBLE);
		tianxie.setOnClickListener(this);

		sheshou = new ImageView(c);
		sheshou.setBackgroundResource(R.drawable.star_9_she_shou);
		PX = (int) (-Radius * Math.cos(getRadian(15)));
		PY = (int) (-Radius * Math.sin(getRadian(15)));
		sheshou.layout(centerX + PX - starRadius, centerY - PY - starRadius,
				centerX + PX + starRadius, centerY - PY + starRadius);
		sheshou.setId(8);
		sheshou.setVisibility(View.INVISIBLE);
		sheshou.setOnClickListener(this);

		mojie = new ImageView(c);
		mojie.setBackgroundResource(R.drawable.star_10_mo_jie);
		PX = (int) (-Radius * Math.cos(getRadian(15)));
		PY = (int) (Radius * Math.sin(getRadian(15)));
		mojie.layout(centerX + PX - starRadius, centerY - PY - starRadius,
				centerX + PX + starRadius, centerY - PY + starRadius);
		mojie.setId(9);
		mojie.setVisibility(View.INVISIBLE);
		mojie.setOnClickListener(this);

		shuiping = new ImageView(c);
		shuiping.setBackgroundResource(R.drawable.star_11_shui_ping);
		PX = (int) (-Radius * Math.cos(getRadian(45)));
		PY = (int) (Radius * Math.sin(getRadian(45)));
		shuiping.layout(centerX + PX - starRadius, centerY - PY - starRadius,
				centerX + PX + starRadius, centerY - PY + starRadius);
		shuiping.setId(10);
		shuiping.setVisibility(View.INVISIBLE);
		shuiping.setOnClickListener(this);

		shuangyu = new ImageView(c);
		shuangyu.setBackgroundResource(R.drawable.star_12_shuang_yu);
		PX = (int) (-Radius * Math.cos(getRadian(75)));
		PY = (int) (Radius * Math.sin(getRadian(75)));
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
	/**
	 * from angle to radian
	 * @param angle
	 * @return
	 */
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
	 *clickCenterOut是12个控件飞出来
	 */
	private void clickCenterOut() {
		// 转圈
		Animation rotate = AnimationUtil.getRotateAm(0, 1440, 2000);
		rotate.setDuration(2000);

		//设置中心点的按钮的变大
		Animation myAnimation_Scale = AnimationUtil.getScaleAm(0.0f, 4.6f,
				0.0f, 4.6f, 2500);

		AnimationSet set = new AnimationSet(false);
		// set.addAnimation(myAnimation_Scale); //放大效果
		set.addAnimation(rotate);//旋转效果
		center.setAnimation(set);
		center.startAnimation(set);

		Animation animation = null;
		for (int i = 0; i < 12; i++) {
			view = getChildAt(i);//得到ViewGroup的子控件

			//getTranslateAm函数是飞出来的效果
			animation = AnimationUtil.getTranslateAm(centerX - view.getLeft()
					- starRadius, 0, centerY - view.getTop() - starRadius, 0,
					300);

			animation.setStartOffset(i * 100);//设置动画延迟，就是一个一个飞出来，每个延迟0.1s
			animation.setInterpolator(new OvershootInterpolator(2F));//飞出去的时候偏离轨道再弹回来，偏离2F

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
					View views = getChildAt(i);//得到ViewGroup的子控件
					// animation = MyAnimations.getRotateAnimation(0,
					// 720,
					// 600);
					animation = AnimationUtil.getRotateAm(0, 720, 600);
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
	 *clickCenterIn是12个控件飞进去
	 */
	private void clickCenterIn() {

		Animation mAnimation = AnimationUtil.getRotateAm(1440, 0, 1800);
		AnimationSet set = new AnimationSet(false);
		set.addAnimation(mAnimation);
		// set.addAnimation(rotate);
		center.setAnimation(set);
		center.startAnimation(set);
		Animation animation;
		for (int i = 0; i < 12; i++) {
			view = getChildAt(i);
			animation = AnimationUtil.getTranslateAm(0,
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
		Animation alpha = AnimationUtil.getAlphaAm(1.0f, 0.0f, 800);//变透明，1为真，0为透明
		Animation scale = AnimationUtil.getScaleAm(0.0f, 1.6f, 0.0f, 1.6f, 400);//放大
		AnimationSet set = new AnimationSet(false);//可同时set两个动画效果，两个动画效果同时执行
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
								Log.v("========arry=========", "" + arry);
								Intent mIntent = new Intent();
								mIntent.setClass(c, StarFortuneActivity.class);

								mIntent.putExtra("json", arry.toString());
								c.startActivity(mIntent);
								c.fileList();

							}

							@Override
							public void DataLoadFaild() {
								// TODO Auto-generated method stub
								Toast.makeText(c, "网络问题，请重新点击",
										Toast.LENGTH_SHORT).show();
							}
						});

				helper.execute(v.getId() + "");
			}
		});

	}
}

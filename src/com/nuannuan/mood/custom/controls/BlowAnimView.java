package com.nuannuan.mood.custom.controls;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.nuannuan.common.R;

public class BlowAnimView extends View {

	private final Paint paint;
	private final Paint paint2;
	private final float w, h;// 该View的宽跟高
	public Bitmap bg_day, flower, flower2, fly, cloud;
	private Matrix bg_m, flower_m, flower2_m;
	private int stage = 0;
	private final int text_x;
	private int text_y;
	private final int text_ys;
	private int cloud_x;
	private final int cloud_y;
	private final float dy, dx;// 背景跟屏幕高的差
	private final float f;// 背景跟屏幕宽的比�?
	private int flower_alpha = 255;
	private int fly_alpha = 255;
	private int all_alpha = 255;
	private boolean isrun = true, canstop = false, dayornight = true;
	private int number = 20;// 蒲公英数量，默认10�?
	private List<Location> locations;
	private Context mContext;

	class Location {
		public float x;// x坐标
		public float y;// y坐标
		public float vx;// 初始速度
		public float vy;
		public int dx;// x位移
		public int dy;// y位移

		public Location(float x, float y, float vx, float vy) {
			this.x = x;
			this.y = y;
			this.vx = vx;
			this.vy = vy;
			dx = (int) (3 * vx);
			dy = (int) (6 * vy);
		}
	}

	private final Thread thread = new Thread(new Runnable() {

		@Override
		public void run() {
			int i = -2, min_y = text_y - 50, max_y = text_y;
			int j = 0, v = 80;
			while (isrun) {

				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				switch (stage) {
				case 1:
					text_y += i;
					if (text_y > max_y || text_y < min_y) {
						i = -i;
					}
					break;
				case 2:// 飞起来的情况，让蒲公英的�?��爸爸渐渐消失、背景跟蒲公英爸爸下�?
					if (j >= v) {
						if (j < v * 2) {
							bg_m.postTranslate(-dx / v, 0);
							j++;
						}
						break;
					}
					bg_m.postTranslate(0, dy / v);
					flower_m.postTranslate(0, dy / v);
					flower2_m.postTranslate(0, dy / v);
					j++;
					break;
				case 3:// 飞完了，背景回到原始位置
					if (j <= v) {
						if (j > 0) {
							bg_m.postTranslate(0, -dy / v);
							flower_m.postTranslate(0, -dy / v);
							flower2_m.postTranslate(0, -dy / v);
							j--;
						}
						break;
					}
					bg_m.postTranslate(dx / v, 0);
					j--;
					break;

				default:
					break;
				}

				if (canstop) {
					cloud_x -= 10;
					if (cloud_x < w / 3) {
						all_alpha -= 2;
						Log.i("test", all_alpha + "");
						if (all_alpha < 0) {
							all_alpha = 0;
						}
					}
				}

				refresh();
			}
		}
	});

	private final Handler handler = new Handler() {
		@Override
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				invalidate();
				break;

			default:
				break;
			}
		}
	};

	public BlowAnimView(Context context, AttributeSet attrs) {
		super(context, attrs);
		w = ((Activity) context).getWindowManager().getDefaultDisplay()
				.getWidth();
		h = ((Activity) context).getWindowManager().getDefaultDisplay()
				.getHeight() + 50;
		mContext=context;
		text_x = (int) (w / 6);
		text_y = (int) (h - 100);
		text_ys=(int)h/2;
		cloud_x = (int) w;
		cloud_y = 0;
		paint = new Paint();
		paint2 = new Paint();
		bg_day = BitmapFactory.decodeResource(getResources(),
				R.drawable.wb_wlog_blow_bg_daytime);
		f = 2;// w / bg.getWidth();
		dy = bg_day.getHeight() * f - h;
		dx = bg_day.getWidth() * f - w;
		flower = BitmapFactory.decodeResource(getResources(), R.drawable.a001);
		flower2 = BitmapFactory.decodeResource(getResources(), R.drawable.a002);
		fly = BitmapFactory.decodeResource(getResources(), R.drawable.a003);
		cloud = BitmapFactory.decodeResource(getResources(),
				R.drawable.wb_wlog_blow_transitions);
		Log.i("test", "w=" + w + ";h=" + h + ";bg.w=" + bg_day.getWidth()
				+ ";bg.h=" + bg_day.getHeight() + ";f=" + f);
		initMatrix();
		setNumber(number);
		start();
	}

	private void initMatrix() {
		// 背景
		bg_m = new Matrix();
		bg_m.postScale(f, f);
		bg_m.postTranslate(0, h - bg_day.getHeight() * f - 40);
		// 蒲公英爸�?
		flower_m = new Matrix();
		flower_m.postTranslate(0, h - flower.getHeight() - 60);
		// 蒲公英的第二个爸�?
		flower2_m = new Matrix();
		flower2_m.postTranslate(0, h - flower.getHeight() - 60);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		Bitmap bg = null;
		// 判断是否为白�?
		// if (dayornight) {
		bg = bg_day;
		// } else {
		// bg = bg_night;
		// }

		paint.setAlpha(all_alpha);
		switch (stage) {
		case 1:
			canvas.drawBitmap(bg, bg_m, paint);
			canvas.drawBitmap(flower, flower_m, paint);
			break;
		case 2:// 蒲公英飞起来的动�?
			paint2.setColor(mContext.getResources().getColor(R.color.trend_txt_pink));
			paint2.setFakeBoldText(true);
			paint2.setTextSize(45);
			canvas.drawText("暖暖天气，暖暖心情 ^_^", text_x, text_ys, paint2);
			
			canvas.drawBitmap(bg, bg_m, paint);
			canvas.drawBitmap(flower2, flower2_m, paint);
			if (flower_alpha >= 5) {
				flower_alpha -= 5;

				paint.setAlpha(flower_alpha);
				canvas.drawBitmap(flower, flower_m, paint);
			}

//			if (fly_alpha >= 30) {
//				fly_alpha -= 1;
//				paint2.setAlpha(fly_alpha);
//			}

			paint.setAlpha(all_alpha);

			// 下面是重点，蒲公英飞的距离
			float d = -0.02f;

			int count=locations.size();
			for (int i = 0; i < count; i++) {
				Location location = locations.get(i);

				canvas.drawBitmap(fly, location.x += location.dx * location.vx
						+ 0.35 * i, location.y -= location.dy * location.vx + 3,
						paint);

				if (location.vy < 0) {
					d = -d;
				}
				location.vx += d; 
				location.vy += d;
			}

			// for (Location location : locations) {
			//
			//
			// }

			break;
		case 3:// 蒲公英飞完后如果断网的话返回原状的动�?
			canvas.drawBitmap(bg, bg_m, paint);
			canvas.drawBitmap(flower2, flower2_m, paint);
			if (flower_alpha <= 250) {
				flower_alpha += 5;
				paint.setAlpha(flower_alpha);
				canvas.drawBitmap(flower, flower_m, paint);
			} else {
				canvas.drawBitmap(flower, flower_m, paint);
			}
			paint.setAlpha(all_alpha);
			break;

		default:
			break;
		}

		if (canstop) {// 如果获取了数据，要让白云画出�?
			canvas.save();
			float f = h / cloud.getHeight();
			canvas.scale(f, f);
			canvas.drawBitmap(cloud, cloud_x / f, cloud_y, paint);
			canvas.restore();
		}

	}

	public void start() {
		stage = 1;
		isrun = true;
		thread.start();
	}

	public void stop() {
		stage = 0;
		isrun = false;
	}

	public void refresh() {
		handler.sendEmptyMessage(0);
	}

	/**
	 * 调用这个方法使蒲公英飞起�?
	 */
	public void fly() {
		stage = 2;
	}

	/**
	 * 飞完了，回到原始状�?
	 */
	public void flyDone() {
		stage = 3;
		all_alpha = 255;
		canstop = false;
		cloud_x = (int) w;
		setNumber(number);
	}

	/**
	 * 如果飞一半就获得了数据，就一朵云飘过，结束动�?
	 */
	public void cloudStop() {
		canstop = true;
	}

	/**
	 * 设置蒲公英数�?
	 */
	public void setNumber(int number) {
		this.number = number;
		locations = new ArrayList<BlowAnimView.Location>();
		for (int i = 0; i < number; i++) {
			locations.add(new Location(flower.getWidth() / 2, h
					- flower.getHeight(), (float) (Math.random() + 0.5),
					(float) (Math.random() + 0.5)));
		}
	}

	/**
	 * 设置背景图为白天或黑�?true为白天；false为黑�?
	 */
	public void setDayOrNight(boolean don) {
		dayornight = don;
	}
}

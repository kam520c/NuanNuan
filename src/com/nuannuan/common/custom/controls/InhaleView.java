package com.nuannuan.common.custom.controls;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.nuannuan.common.R;
import com.nuannuan.common.custom.controls.InhaleMesh.InhaleDir;
import com.nuannuan.weather.interfaces.WeatherIm;
import com.nuannuan.weather.utility.WeatherCondition;
import com.nuannuan.weather.utility.WeatherParser;
import com.nuannuan.weather.utility.WeatherUtility;

public class InhaleView extends View {

	private static final int WIDTH = 40;
	private static final int HEIGHT = 40;

	private WeatherIm mWeatherIm;
	public Bitmap mBitmap;
	private final Matrix mMatrix = new Matrix();
	private final Matrix mInverse = new Matrix();

	private boolean mIsDebug = false;
	private Paint mPaint = new Paint();
	private float[] mInhalePt = new float[] { 0, 0 };
	private InhaleMesh mInhaleMesh = null;
	private Context mContext;
	private int screenWidth;
	private int screenHeigth;
	private List<WeatherCondition> mlist = null;

	// public LinearLayout trend;
	// public RelativeLayout relative;

	public void setmWeatherIm(WeatherIm mWeatherIm) {
		this.mWeatherIm = mWeatherIm;
	}

	public InhaleView(Context context, WeatherParser parser, int screenWidth,
			int screenHeigth) {
		super(context);
		setFocusable(true);

		this.screenWidth = screenWidth;
		this.screenHeigth = screenHeigth;
		mContext = context;
		LayoutInflater factory = LayoutInflater.from(context);
		View view = factory.inflate(R.layout.layout_weather, null);

		WeatherCondition condition = null;
		if (parser != null) {
			condition = parser.getWeather(0);
			mlist = parser.getWeather();
		}
		initInhale(view, condition);

		// 启用绘图缓存
		view.setDrawingCacheEnabled(true);
		// 调用下面这个方法非常重要，如果没有调用这个方法，得到的bitmap为null

		view.measure(MeasureSpec.makeMeasureSpec((int) (screenWidth),
				MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(
				(int) (screenHeigth * 0.45), MeasureSpec.EXACTLY));
		// 这个方法也非常重要，设置布局的尺寸和位置
		view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
		// 获得绘图缓存中的Bitmap
		view.buildDrawingCache();
		mBitmap = view.getDrawingCache();
		mInhaleMesh = new InhaleMesh(WIDTH, HEIGHT);
		mInhaleMesh.setBitmapSize(mBitmap.getWidth(), mBitmap.getHeight());
		mInhaleMesh.setInhaleDir(InhaleDir.DOWN);
	}

	private void initInhale(View view, WeatherCondition condition) {

		// TextView city = (TextView) view.findViewById(R.id.cityWeather);
		TextView temp = (TextView) view.findViewById(R.id.tempWeather);
		TextView state = (TextView) view.findViewById(R.id.stateWeather);
		TextView tempBT = (TextView) view.findViewById(R.id.tempBT);
		TextView wind = (TextView) view.findViewById(R.id.windInhale);
		// ImageView img = (ImageView) view.findViewById(R.id.inhaleImg);

		TextView state1 = (TextView) view.findViewById(R.id.weather_txt1);
		TextView state2 = (TextView) view.findViewById(R.id.weather_txt2);
		TextView state3 = (TextView) view.findViewById(R.id.weather_txt3);

		TextView time1 = (TextView) view.findViewById(R.id.weather_txt11);
		TextView time2 = (TextView) view.findViewById(R.id.weather_txt22);
		TextView time3 = (TextView) view.findViewById(R.id.weather_txt33);

		ImageView img1 = (ImageView) view.findViewById(R.id.weather_img1);
		ImageView img2 = (ImageView) view.findViewById(R.id.weather_img2);
		ImageView img3 = (ImageView) view.findViewById(R.id.weather_img3);

		TextView time = (TextView) view.findViewById(R.id.weather_time);
		TextView week = (TextView) view.findViewById(R.id.weather_week);
		TextView city = (TextView) view.findViewById(R.id.weather_city);
		TextView chuyi = (TextView) view.findViewById(R.id.weather_chy_txt);

		boolean isday = WeatherUtility.isDay();
		if (mlist != null) {
			if (mlist.size() > 4) {
				WeatherCondition condition1 = mlist.get(1);
				String status1 = (isday) ? condition1.status1
						: condition1.status2;
				state1.setText(status1 + " " + condition1.temperature2 + "°"
						+ "/" + condition1.temperature1 + "°");
				img1.setImageResource(WeatherUtility.getIcon(status1, isday));
				time1.setText(WeatherUtility.getWeek(1));

				WeatherCondition condition2 = mlist.get(2);
				String status2 = (isday) ? condition2.status1
						: condition2.status2;
				state2.setText(status2 + " " + condition2.temperature2 + "°"
						+ "/" + condition2.temperature1 + "°");
				img2.setImageResource(WeatherUtility.getIcon(status2, isday));
				time2.setText(WeatherUtility.getWeek(2));

				WeatherCondition condition3 = mlist.get(3);
				String status3 = (isday) ? condition3.status1
						: condition3.status2;
				state3.setText(status3 + " " + condition3.temperature2 + "°"
						+ "/" + condition3.temperature1 + "°");
				img3.setImageResource(WeatherUtility.getIcon(status3, isday));
				time3.setText(WeatherUtility.getWeek(3));
			}
		}
		final Calendar c = Calendar.getInstance();
		c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
		int mDay = c.get(Calendar.DAY_OF_MONTH);// 获取当前月份的日期号码

		week.setText(WeatherUtility.getWeek(0));

		if (condition != null) {
			String status = (isday) ? condition.status1 : condition.status2;
			String tgd = (isday) ? condition.tgd1 : condition.tgd2;
			String power = (isday) ? condition.power1 : condition.power2;

			temp.setText(tgd + "°");
			state.setText(status);
			tempBT.setText(condition.temperature2 + "°" + "/"
					+ condition.temperature1 + "°");
			wind.setText("风力" + power);
			city.setText(condition.city);
			time.setText(mDay + "日");
			String chuanyi = condition.chy_shuoming;
			chuyi.setText("穿衣提示："+chuanyi);
		}
	}

	public void setIsDebug(boolean isDebug) {
		mIsDebug = isDebug;
	}

	public void setWidth(int width) {
		screenWidth = width;
	}

	public void setHeigth(int heigth) {
		screenHeigth = heigth;
	}

	public void setInhaleDir(InhaleMesh.InhaleDir dir) {
		mInhaleMesh.setInhaleDir(dir);

		float w = mBitmap.getWidth();
		float h = mBitmap.getHeight();
		float endX = 0;
		float endY = 0;
		float dx = 10;
		float dy = 10;
		mMatrix.reset();

		switch (dir) {
		case DOWN:
			endX = w / 2;
			endY = getHeight() - 20;
			break;

		case UP:
			dy = getHeight() - h - 20;
			endX = w / 2;
			endY = -dy + 10;
			break;

		case LEFT:
			dx = getWidth() - w - 20;
			endX = -dx + 10;
			endY = h / 2;
			break;

		case RIGHT:
			endX = getWidth() - 20;
			endY = h / 2;
			break;
		}

		mMatrix.setTranslate(dx, dy);
		mMatrix.invert(mInverse);
		buildPaths(endX, endY);
		buildMesh(w, h);
		invalidate();
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);

		float bmpW = mBitmap.getWidth();
		float bmpH = mBitmap.getHeight();

		// mMatrix.setTranslate(10, 10);
		// mMatrix.setTranslate(10, 10);
		mMatrix.invert(mInverse);

		mPaint.setColor(Color.RED);
		mPaint.setStrokeWidth(2);
		mPaint.setAntiAlias(true);

		buildPaths(bmpW / 2, h - 20);
		buildMesh(bmpW, bmpH);
	}

	public boolean startAnimation(boolean reverse) {
		Animation anim = this.getAnimation();
		if (null != anim && !anim.hasEnded()) {
			return false;
		}

		PathAnimation animation = new PathAnimation(0, HEIGHT + 1, reverse,
				new PathAnimation.IAnimationUpdateListener() {
					@Override
					public void onAnimUpdate(int index) {
						mInhaleMesh.buildMeshes(index);
						invalidate();
					}
				});

		if (null != animation) {
			animation.setDuration(1400);
			this.startAnimation(animation);
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
					if (mWeatherIm != null) {
						mWeatherIm.WeatherImClick();
					}
				}
			});
		}

		return true;
	}

	@Override
	protected void onDraw(Canvas canvas) {

		setPointPosition();

		canvas.drawColor(0x00CCCCCC);

		canvas.concat(mMatrix);
		canvas.drawBitmapMesh(mBitmap, mInhaleMesh.getWidth(),
				mInhaleMesh.getHeight(), mInhaleMesh.getVertices(), 0, null, 0,
				mPaint);
		// ===========================================
		// Draw the target point.

		mPaint.setColor(Color.RED);
		mPaint.setStyle(Style.FILL);
		//
		// canvas.drawCircle(mInhalePt[0], mInhalePt[1], 5, mPaint);

		if (mIsDebug) {
			// ===========================================
			// Draw the mesh vertices.
			canvas.drawPoints(mInhaleMesh.getVertices(), mPaint);
			// ===========================================
			// Draw the paths
			mPaint.setColor(Color.BLUE);
			mPaint.setStyle(Style.STROKE);
			Path[] paths = mInhaleMesh.getPaths();
			for (Path path : paths) {
				canvas.drawPath(path, mPaint);
			}
		}
	}

	private void buildMesh(float w, float h) {
		mInhaleMesh.buildMeshes(w, h);
	}

	private void buildPaths(float endX, float endY) {
		mInhalePt[0] = endX;
		mInhalePt[1] = endY;
		mInhaleMesh.buildPaths(endX, endY);
	}

	int mLastWarpX = 0;
	int mLastWarpY = 0;

	/**
	 * 设置点的位置
	 */
	private void setPointPosition() {
		float[] pt = { screenWidth / 10 * 9, screenHeigth / 10 * 7 };
		int x = (int) pt[0];
		int y = (int) pt[1];
		mInverse.mapPoints(pt);
		mLastWarpX = x;
		mLastWarpY = y;
		buildPaths(pt[0], pt[1]);

	}
}

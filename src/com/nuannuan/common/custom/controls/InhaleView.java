package com.nuannuan.common.custom.controls;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.TextView;

import com.nuannuan.common.custom.controls.InhaleMesh.InhaleDir;
import com.nuannuan.weather.interfaces.WeatherIm;
import com.scau.feelingmusic.R;

public class InhaleView extends View {

	private static final int WIDTH = 40;
	private static final int HEIGHT = 40;

	private WeatherIm mWeatherIm;
	private Bitmap mBitmap;
	private final Matrix mMatrix = new Matrix();
	private final Matrix mInverse = new Matrix();

	private boolean mIsDebug = false;
	private Paint mPaint = new Paint();
	private float[] mInhalePt = new float[] { 0, 0 };
	private InhaleMesh mInhaleMesh = null;
	private Context mContext;
	private int screenWidth;
	private int screenHeigth;

	public void setmWeatherIm(WeatherIm mWeatherIm) {
		this.mWeatherIm = mWeatherIm;
	}

	public InhaleView(Context context, int i, int screenWidth, int screenHeigth) {
		super(context);
		setFocusable(true);

		this.screenWidth=screenWidth;
		this.screenHeigth=screenHeigth;
		mContext=context;
		LayoutInflater factory = LayoutInflater.from(context);
		View view = factory.inflate(R.layout.layout_weather, null);

		TextView city = (TextView) view.findViewById(R.id.test);

		city.setText("" + i);
		// 启用绘图缓存
		view.setDrawingCacheEnabled(true);
		// 调用下面这个方法非常重要，如果没有调用这个方法，得到的bitmap为null

		view.measure(
				MeasureSpec.makeMeasureSpec((int) (screenWidth), MeasureSpec.EXACTLY),
				MeasureSpec.makeMeasureSpec((int) (screenHeigth*0.7), MeasureSpec.EXACTLY));
		// 这个方法也非常重要，设置布局的尺寸和位置
		view.layout((int) (screenWidth*0.1), (int) (screenHeigth*0.1), (int) (screenWidth*0.1)+view.getMeasuredWidth(), (int) (screenHeigth*0.1)+view.getMeasuredHeight());
		// view.layout(0, 0,720, 1280);
		// 获得绘图缓存中的Bitmap
		view.buildDrawingCache();
		mBitmap = view.getDrawingCache();

		mInhaleMesh = new InhaleMesh(WIDTH, HEIGHT);
		mInhaleMesh.setBitmapSize(mBitmap.getWidth(), mBitmap.getHeight());
		mInhaleMesh.setInhaleDir(InhaleDir.DOWN);

	}

	public void setIsDebug(boolean isDebug) {
		mIsDebug = isDebug;
	}
	
	public void setWidth(int width){
		screenWidth=width;
	}
	public void setHeigth(int heigth){
		screenHeigth=heigth;
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

//		mMatrix.setTranslate(10, 10);
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
			animation.setDuration(2000);
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
		Log.i("leehong2", "onDraw  =========== ");
		canvas.drawColor(0x00CCCCCC);

		canvas.concat(mMatrix);

		canvas.drawBitmapMesh(mBitmap, mInhaleMesh.getWidth(),
				mInhaleMesh.getHeight(), mInhaleMesh.getVertices(), 0, null, 0,
				mPaint);

		// ===========================================
		// Draw the target point.
		mPaint.setColor(Color.RED);
		mPaint.setStyle(Style.FILL);
		canvas.drawCircle(mInhalePt[0], mInhalePt[1], 5, mPaint);

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

	// @Override
	// public boolean onTouchEvent(MotionEvent event) {
	// float[] pt = { event.getX(), event.getY() };
	// mInverse.mapPoints(pt);
	//
	// if (event.getAction() == MotionEvent.ACTION_UP) {
	// int x = (int) pt[0];
	// int y = (int) pt[1];
	// if (mLastWarpX != x || mLastWarpY != y) {
	// mLastWarpX = x;
	// mLastWarpY = y;
	// buildPaths(pt[0], pt[1]);
	// invalidate();
	// }
	// }
	// return true;
	// }
}

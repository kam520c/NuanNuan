package com.nuannuan.weather.custom.controls;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import com.nuannuan.common.R;

/**
 * 未来天气趋势图
 * 
 * @author Eden
 * 
 */
public class WeatherTrendView extends View {

	private Paint mPointPaint;
	private Paint mTextPaint;
	private Paint mLinePaint1;
	private Paint mLinePaint2;
	private Paint mbackLinePaint;
	private Paint mTextPaint2;

	private int x[] = new int[5];
	private float radius = 17;
	private int h;
	private List<Integer> topTem;
	private List<Integer> lowTem;
	private Bitmap[] topBmps;
	private Bitmap[] lowBmps;
	private int widthImg;

	private Context c;

	public WeatherTrendView(Context context) {
		super(context);
		this.c = context;
		setWidthHeight();
		init();
	}

	public WeatherTrendView(Context context, AttributeSet attr) {
		super(context, attr);
		this.c = context;
		setWidthHeight();
		init();
	}

	private void init() {
		topBmps = new Bitmap[5];
		lowBmps = new Bitmap[5];

		topTem = new ArrayList<Integer>();
		lowTem = new ArrayList<Integer>();

		mbackLinePaint = new Paint();
		mbackLinePaint.setColor(Color.WHITE);

		mPointPaint = new Paint();
		mPointPaint.setAntiAlias(true);
		mPointPaint.setColor(Color.WHITE);

		mLinePaint1 = new Paint();
		mLinePaint1
				.setColor(getResources().getColor(R.color.trend_line_yellow));
		mLinePaint1.setAntiAlias(true);
		mLinePaint1.setStrokeWidth(10);
		mLinePaint1.setStyle(Style.FILL);

		mLinePaint2 = new Paint();
		mLinePaint2.setColor(getResources().getColor(R.color.blue));
		mLinePaint2.setAntiAlias(true);
		mLinePaint2.setStrokeWidth(10);
		mLinePaint2.setStyle(Style.FILL);

		mTextPaint = new Paint();
		mTextPaint.setAntiAlias(true);
		mTextPaint.setColor(Color.BLACK);
		mTextPaint.setFakeBoldText(true);
		mTextPaint.setTextSize(30F);
		mTextPaint.setTextAlign(Align.CENTER);

		mTextPaint2 = new Paint();
		mTextPaint2.setAntiAlias(true);
		mTextPaint2.setColor(Color.YELLOW);
		mTextPaint2.setTextSize(25F);
		mTextPaint2.setTextAlign(Align.CENTER);
	}

	public void setWidthHeight() {

		DisplayMetrics dm = getResources().getDisplayMetrics();
		int displayWidth = dm.widthPixels;
		int displayHeight = dm.heightPixels;
		// displayHeight = h;

		x[0] = displayWidth / 11;//
		x[1] = displayWidth * 3 / 11;
		x[2] = displayWidth * 5 / 11;
		x[3] = displayWidth * 7 / 11;
		x[4] = displayWidth * 9 / 11;
		widthImg = displayWidth;
		h = displayHeight;
	}

	public void setTemperature(List<Integer> top, List<Integer> low) {
		this.topTem = top;
		this.lowTem = low;

		postInvalidate();
	}

	public void setBitmap(List<Integer> topList, List<Integer> lowList) {
		Bitmap bitmap = null;
		for (int i = 0; i < topList.size(); i++) {
			bitmap = BitmapFactory.decodeResource(c.getResources(),
					topList.get(i));
			topBmps[i] = bitmap;
		}
		for (int i = 0; i < lowList.size(); i++) {
			bitmap = BitmapFactory.decodeResource(c.getResources(),
					lowList.get(i));
			lowBmps[i] = bitmap;
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		float space = 0f;
		float space1 = 0f;
		int temspace = 15;

		FontMetrics fontMetrics = mTextPaint.getFontMetrics();
		// �������ָ߶�
		float fontHeight = fontMetrics.bottom - fontMetrics.top;

		int h = this.h / 2;
		int h2 = (int) (h - fontHeight / 2);
		int h4 = (int) (h + fontHeight);

		int countTop = topTem.size();

		Bitmap grayBit = BitmapFactory.decodeResource(c.getResources(),
				R.drawable.trend_gray_point);
		Bitmap whiteBit = BitmapFactory.decodeResource(c.getResources(),
				R.drawable.trend_white_point);
		whiteBit = getPointBitmap(whiteBit);
		for (int i = 0; i < countTop; i++) {
			space = (-topTem.get(i)) * temspace;
			if (topTem.get(i) != 100) {
				if (i != topTem.size() - 1) {
					space1 = (-topTem.get(i + 1)) * temspace;
					canvas.drawLine(x[i], h + space, x[i + 1], h + space1,
							mLinePaint1);
				}
				canvas.drawText(topTem.get(i) + "°c", x[i], h2 + space - 20,
						mTextPaint);
				// canvas.drawCircle(x[i], h + space, radius, mPointPaint);

				canvas.drawBitmap(whiteBit, x[i] - whiteBit.getWidth() / 2, h
						+ space - whiteBit.getHeight() / 2, null);

				Bitmap newBitmap = getNewBitmap(topBmps[i]);
				canvas.drawBitmap(newBitmap, x[i] - newBitmap.getWidth() / 2,
						widthImg / 11, null);
				newBitmap.recycle();
			}
		}
		int countLow = lowTem.size();
		for (int i = 0; i < countLow; i++) {
			space = (-lowTem.get(i)) * temspace;
			if (i != lowTem.size() - 1) {
				space1 = (-lowTem.get(i + 1)) * temspace;
				canvas.drawLine(x[i], h + space, x[i + 1], h + space1,
						mLinePaint2);
			}
			canvas.drawText(lowTem.get(i) + "°c", x[i], h4 + space + 20,
					mTextPaint);

			// canvas.drawCircle(x[i], h + space, radius, mPointPaint);
			canvas.drawBitmap(whiteBit, x[i] - whiteBit.getWidth() / 2, h
					+ space - whiteBit.getHeight() / 2, null);

			Bitmap newBitmap = getNewBitmap(lowBmps[i]);

			canvas.drawBitmap(newBitmap, x[i] - newBitmap.getWidth() / 2,
					widthImg * 10 / 11 - newBitmap.getHeight(), null);
			newBitmap.recycle();
		}
	}

	private Bitmap getNewBitmap(Bitmap bitmap) {
		// 获得图片的宽高
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		// 设置想要的大小
		int newWidth = widthImg / 7;
		int newHeight = widthImg / 7;
		// 计算缩放比例
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;
		// 取得想要缩放的matrix参数
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);
		// 得到新的图片
		Bitmap newbm = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix,
				true);
		return newbm;
	}
	
	private Bitmap getPointBitmap(Bitmap bitmap) {
		// 获得图片的宽高
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		
		// 计算缩放比例
		float scaleWidth = (float) 0.7;
		float scaleHeight = (float) 0.7;
		// 取得想要缩放的matrix参数
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);
		// 得到新的图片
		Bitmap newbm = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix,
				true);
		return newbm;
	}
}

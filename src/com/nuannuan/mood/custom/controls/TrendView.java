package com.nuannuan.mood.custom.controls;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nuannuan.mood.interfaces.TrendButtonIm;

/**
 * 
 * @author Eden
 * 
 */
public class TrendView extends ViewGroup implements View.OnClickListener {

	private Paint mLinePaint1;// 虚线路
	private Paint mLinePaint2;// 实线

	private int x[] = new int[5];
	private List<Integer> topTem;
	// private Bitmap[] topBmps;
	private List<Point> list = new ArrayList<Point>();// 左右两边的坐标点
	private int height;// 设置控件距离最上边的距离
	private int temspace;// 每格直接的间距
	private int imagSize;// 设置图片的大小
	private int mSelectView = -1;

	private Context c;
	private List<Integer> draList = new ArrayList<Integer>();
	private int Width;
	private int Heigth;
	private LinearLayout dialogLin;
	private View view;
	private int TextHeigth;

	public TrendButtonIm getmButtonIm() {
		return mButtonIm;
	}

	public void setmButtonIm(TrendButtonIm mButtonIm) {
		this.mButtonIm = mButtonIm;
	}

	private TrendButtonIm mButtonIm = null;

	public TrendView(Context context) {
		super(context);
		this.c = context;
		init();
		setWillNotDraw(false);

	}

	public TrendView(Context context, AttributeSet attr) {
		super(context, attr);
		this.c = context;
		init();
		setWillNotDraw(false);
	}

	private void init() {

		topTem = new ArrayList<Integer>();

		mLinePaint1 = new Paint();
		mLinePaint1.setColor(0xff666666);
		// mLinePaint1.setAlpha(100);
		mLinePaint1.setAntiAlias(true);

		PathEffect effects = new DashPathEffect(
				new float[] { 1, 4, 2, 2, 5, 20 }, 1);
		mLinePaint1.setPathEffect(effects);
		mLinePaint1.setStrokeWidth(20);
		mLinePaint1.setStyle(Style.STROKE);
		// LinearGradient lg = new LinearGradient(0, 0, 100, 100, Color.RED,
		// Color.GREEN, Shader.TileMode.MIRROR);
		// mLinePaint1.setShader(lg);

		mLinePaint2 = new Paint();
		mLinePaint2.setColor(0xffFF6699);
		mLinePaint2.setStrokeWidth(20);
		mLinePaint2.setAntiAlias(true);
		mLinePaint2.setStyle(Style.FILL);
	}

	public void setPosition(int a, int b, int c, int d) {
		x[0] = a;
		x[1] = b;
		x[2] = c;
		x[3] = d;
	}

	// ����X��ƫ����
	public void setWidthHeight(int w, int a) {
		if (a == 0) {
			setWidth2(w);
		} else {
			setWidth1(w);
		}
		imagSize = w / 9;
		height = w / 12;
		Width = w;
		Heigth = (int) (3.6 * w);
		temspace = (int) (1.5 * imagSize);
		TextHeigth = w / 14;
	}

	/**
	 * ���ұ߿�ʼ
	 * 
	 * @param w
	 */
	private void setWidth1(int w) {
		x[0] = w / 10;// ԭ����8
		x[1] = w * 3 / 10;
		x[2] = w * 5 / 10;
		x[3] = w * 7 / 10;
		x[4] = w * 9 / 10;
	}

	/**
	 * ����߿�ʼ
	 * 
	 * @param w
	 */
	private void setWidth2(int w) {
		x[0] = w - w / 10;// ԭ����8
		x[1] = w - w * 3 / 10;
		x[2] = w - w * 5 / 10;
		x[3] = w - w * 7 / 10;
		x[4] = w - w * 9 / 10;
	}

	public void setPosition(List<Integer> top) {
		this.topTem = top;
	}

	public void setDrawable(List<Integer> drawable) {
		this.draList = drawable;
	}

	// 把坐标点都添加进一个集合里面
	private void AddPoint(int dayNum) {
		Point pon1 = new Point();
		pon1.x = x[0];
		pon1.y = height + (topTem.get(0)) * temspace;
		list.add(pon1);
		Point pon2 = new Point();
		pon2.x = x[0];
		pon2.y = height + (topTem.get(5)) * temspace;
		list.add(pon2);
		Point pon3 = new Point();
		pon3.x = x[4];
		pon3.y = height + (topTem.get(9)) * temspace;
		list.add(pon3);
		Point pon4 = new Point();
		pon4.x = x[4];
		pon4.y = height + (topTem.get(14)) * temspace;
		list.add(pon4);
		Point pon5 = new Point();
		pon5.x = x[0];
		pon5.y = height + (topTem.get(10)) * temspace;
		list.add(pon5);
		Point pon6 = new Point();
		pon6.x = x[0];
		pon6.y = height + (topTem.get(15)) * temspace;
		list.add(pon6);
		Point pon7 = new Point();
		pon7.x = x[4];
		pon7.y = height + (topTem.get(19)) * temspace;
		list.add(pon7);
		Point pon8 = new Point();
		pon8.x = x[4];
		pon8.y = height + (topTem.get(24)) * temspace;
		list.add(pon8);
		Point pon9 = new Point();
		pon9.x = x[0];
		pon9.y = height + (topTem.get(20)) * temspace;
		list.add(pon9);
		Point pon10 = new Point();
		pon10.x = x[0];
		pon10.y = height + (topTem.get(25)) * temspace;
		list.add(pon10);

		// 如果有30天以上，则添加30号的点
		if (dayNum == 30 || dayNum == 31) {
			Point pon11 = new Point();
			pon11.x = x[4];
			pon11.y = height + (topTem.get(29)) * temspace;
			list.add(pon11);
		}
		// 如果有31天，则添加最后一个点
		if (dayNum == 31) {
			Point pon12 = new Point();
			pon12.x = x[4];
			pon12.y = height + (topTem.get(30)) * temspace;
			list.add(pon12);
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		// 根据不同的月份进行不同的处理
		if (topTem.size() == 30) {
			drawPic(canvas, 30);
		}
		// 根据不同的月份进行不同的处理
		if (topTem.size() == 28) {
			drawPic(canvas, 28);
		}
		// 根据不同的月份进行不同的处理
		if (topTem.size() == 29) {
			drawPic(canvas, 29);
		}
		// 根据不同的月份进行不同的处理
		if (topTem.size() == 31) {
			drawPic(canvas, 31);
		}

	}

	// 执行画线
	private void drawPic(Canvas canvas, int dayNum) {
		int j = 0;
		AddPoint(dayNum);

		float space = 0f;
		float space1 = 0f;

		Point point1 = new Point();
		Point point2 = new Point();
		for (int k = 0; k < list.size(); k++) {

			if (k != list.size() - 1) {
				point1 = list.get(k);
				point2 = list.get(k + 1);

				if (point1.x == point2.x) {
					// canvas.drawLine(point1.x, point1.y, point2.x, point2.y,
					// mLinePaint1);
					if (point1.y - point2.y < 4 * temspace) {
						Path path2 = new Path();
						path2.moveTo(point1.x, point1.y);// 设置Path的起点

						path2.quadTo((float) (point1.x * 0.90), point1.y,
								point2.x, point2.y); // 设置贝塞尔曲线的控制点坐标和终点坐标
						// canvas.drawPath(path2, mLinePaint1);// 画出贝塞尔曲线

						canvas.drawLine(point1.x, point1.y, point2.x, point2.y,
								mLinePaint2);// 画出直线
					}
				}
			}
		}

		for (int i = 0; i < topTem.size(); i++) {
			space = (topTem.get(i)) * temspace;

			if (i % 5 == 0) {
				j = 0;
			}

			if (i != topTem.size() - 1) {
				space1 = (topTem.get(i + 1)) * temspace;
				canvas.drawCircle(x[j], height + space,
						(float) (1.2 * imagSize) / 2, mLinePaint2); // 画圆
				if (j % 4 == 0 && j != 0) {
				} else {
					// ��ͬһ��֮�������
					// canvas.drawLine(x[j], height + space, x[j + 1], height
					// + space1, mLinePaint1);

					Path path2 = new Path();
					path2.moveTo(x[j], height + space);// 设置Path的起点
					path2.quadTo((float) (x[j] * 0.85),
							(float) (height * 1.3 + space), x[j + 1], height
									+ space1); // 设置贝塞尔曲线的控制点坐标和终点坐标

					// canvas.drawPath(path2, mLinePaint1);// 画出贝塞尔曲线

					canvas.drawLine(x[j], height + space, x[j + 1], height
							+ space1, mLinePaint2);// 画出直线
				}
				//最后一个圈需要做判断
			} else {
				if (topTem.size() == 31) {
					canvas.drawCircle(x[4 - j], height + space,
							(float) (1.2 * imagSize) / 2, mLinePaint2); // 画圆
				} else {

					canvas.drawCircle(x[j], height + space,
							(float) (1.2 * imagSize) / 2, mLinePaint2); // 画圆
				}
			}
			j++;

		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		setMeasuredDimension(widthMeasureSpec, Heigth);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		// super.onLayout(changed, left, top, right, bottom);

		if (mSelectView != -1) {
			View v = getChildAt(mSelectView);
			if (v != null)
				// v.layout(x, y, x + 300, y + 500);
				return;
		}

		float space = 0f;
		int j = 0;

		int k = 2;// 翻转id的参数
		int y = 1;// 隔行进行翻转的参数
		for (int i = 0; i < topTem.size(); i++) {
			space = (topTem.get(i)) * temspace;
			// 每隔5个换一行
			if (i % 5 == 0) {
				j = 0;
			}
			// 如果i=30则，进行一次x逆转运算
			if (i == 30) {
				ImageView view1 = new ImageView(c);
				view1.setBackgroundResource(draList.get(30));
				// view1.setBackgroundResource(R.drawable.a0001);
				int imgl2 = x[4 - j] - imagSize / 2;
				int imgt2 = (int) (height + space - imagSize / 2);

				TextView textView = new TextView(c);
				textView.setText((i + 1) + "号");
				textView.setTextColor(0xffCC3366);
				textView.layout(imgl2, imgt2 + imagSize, imgl2 + imagSize,
						imgt2 + imagSize + TextHeigth);
				textView.setGravity(17);
				view1.layout(imgl2, imgt2, imgl2 + imagSize, imgt2 + imagSize);
				addView(view1);
				// testI = i;

				addView(textView);
				view1.setId(i);
				view1.setOnClickListener(this);
			} else {
				// 把图片放进放进去每一个坐标点
				ImageView view1 = new ImageView(c);
				view1.setBackgroundResource(draList.get(i));
				// view1.setBackgroundResource(R.drawable.a0001);
				int imgl = x[j] - imagSize / 2;
				int imgt = (int) (height + space - imagSize / 2);
				view1.layout(imgl, imgt, imgl + imagSize, imgt + imagSize);

				TextView textView = new TextView(c);
				textView.layout(imgl, imgt + imagSize, imgl + imagSize, imgt
						+ imagSize + TextHeigth);
				textView.setGravity(17);
				textView.setTextColor(0xffCC3366);
				addView(view1);
				addView(textView);
				// 对隔行需要逆转一次
				if (i % 5 == 0 && i != 0) {
					if (i != 0) {
						y++;
					}
				}
				// 如果y不等于2的倍数，则进行逆转算法
				if (y % 2 != 0) {
					if (i % 5 == 0) {
						k = 2;
					}
					view1.setBackgroundResource(draList.get(i + 2 * k));
					view1.setId(i + 2 * k);
					textView.setText((i + 1) + 2 * k + "号");
					view1.setOnClickListener(this);
					k--;
					// 如果y等于2的倍数，则不进行逆转
				} else {
					view1.setBackgroundResource(draList.get(i));
					view1.setId(i);
					textView.setText((i + 1) + "号");
					view1.setOnClickListener(this);

				}
			}

			j++;
		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case 0:
			setImageClick(v);
			break;
		case 1:
			setImageClick(v);
			break;
		case 2:
			setImageClick(v);
			break;
		case 3:
			setImageClick(v);
			break;
		case 4:
			setImageClick(v);
			break;
		case 5:
			setImageClick(v);
			break;
		case 6:
			setImageClick(v);
			break;
		case 7:
			setImageClick(v);
			break;
		case 8:
			setImageClick(v);
			break;
		case 9:
			setImageClick(v);
			break;
		case 10:
			setImageClick(v);
			break;
		case 11:
			setImageClick(v);
			break;
		case 12:
			setImageClick(v);
			break;
		case 13:
			setImageClick(v);
			break;
		case 14:
			setImageClick(v);
			break;
		case 15:
			setImageClick(v);
			break;
		case 16:
			setImageClick(v);
			break;
		case 17:
			setImageClick(v);
			break;
		case 18:
			setImageClick(v);
			break;
		case 19:
			setImageClick(v);
			break;
		case 20:
			setImageClick(v);
			break;
		case 21:
			setImageClick(v);
			break;
		case 22:
			setImageClick(v);
			break;
		case 23:
			setImageClick(v);
			break;
		case 24:
			setImageClick(v);
			break;
		case 25:
			setImageClick(v);
			break;
		case 26:
			setImageClick(v);
			break;
		case 27:
			setImageClick(v);
			break;
		case 28:
			setImageClick(v);
			break;
		case 29:
			setImageClick(v);
			break;
		case 30:
			setImageClick(v);
			break;
		default:
			break;

		}
	}

	public void setImageClick(View view) {

		if (mButtonIm != null) {
			mButtonIm.TrendonClick(view);
		}
	}

}

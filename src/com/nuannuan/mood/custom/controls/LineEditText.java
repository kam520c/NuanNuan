package com.nuannuan.mood.custom.controls;

import java.util.HashMap;
import java.util.Map;

import com.scau.feelingmusic.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.Paint.FontMetrics;
import android.graphics.Rect;
import android.text.Layout;
import android.text.Selection;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.URLSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.MotionEvent;
import android.widget.EditText;

public class LineEditText extends EditText {

	private Paint mPaint;
    private int lines = 0;
    private float fontHeight = 0;
    private float leading = 0;
    private Rect mRect;

    public LineEditText(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        initPaint();
    }

    public LineEditText(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        initPaint();
    }

    public LineEditText(Context context)
    {
        super(context);
        initPaint();
    }

    private void initPaint()
    {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(0x800000FF);
        mPaint.setAntiAlias(true);
        mRect = new Rect();
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus)
    {
        super.onWindowFocusChanged(hasWindowFocus);
        init();
    }

    private void init()
    {
        int lineHeight = getLineHeight();
        int viewHeight = getHeight();
        lines = viewHeight / lineHeight;
        setGravity(Gravity.TOP);
        float textSize = getTextSize();
        Paint paint = new Paint();
        paint.setTextSize(textSize);
        FontMetrics fontMetrics = paint.getFontMetrics();
        fontHeight = fontMetrics.descent - fontMetrics.ascent;
        leading = fontMetrics.leading;// leading == 0
        setBackgroundResource(R.drawable.edit_green);
    }

    @Override
    public void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        if (lines == 0)
        {
            init();
        }
        int count = getLineCount();
        if (count <= lines)
        {
            for (int i = 1; i < lines; i++)
            {

                canvas.drawLine(0,
                        fontHeight * i + leading * i,
                        getWidth(),
                        fontHeight * i + leading * i,
                        mPaint);
            }
        }
        else
        {
            for (int i = 0; i < count; i++)
            {
                int baseline = getLineBounds(i, mRect);
                canvas.drawLine(mRect.left,
                        baseline + 1,
                        mRect.right,
                        baseline + 1,
                        mPaint);
            }
        }
    }
//	private Rect mRect;
//	private Paint mPaint;
//
//	// This constructor is used by LayoutInflater
//	public LineEditText(Context context, AttributeSet attrs) {
//		super(context, attrs);
//
//		// Creates a Rect and a Paint object, and sets the style and color of
//		// the Paint object.
//		mRect = new Rect();
//		mPaint = new Paint();
//		mPaint.setStyle(Paint.Style.STROKE);
//		mPaint.setColor(0x800000FF);
//	}
//
//	/**
//	 * This is called to draw the LinedEditText object
//	 * 
//	 * @param canvas
//	 *            The canvas on which the background is drawn.
//	 */
//	@Override
//	protected void onDraw(Canvas canvas) {
//
//		// Gets the number of lines of text in the View.
//		int count = getLineCount();
//
//		// Gets the global Rect and Paint objects
//		Rect r = mRect;
//		Paint paint = mPaint;
//
//		/*
//		 * Draws one line in the rectangle for every line of text in the
//		 * EditText
//		 */
//		for (int i = 0; i < count; i++) {
//
//			// Gets the baseline coordinates for the current line of text
//			int baseline = getLineBounds(i, r);
//
//			/*
//			 * Draws a line in the background from the left of the rectangle to
//			 * the right, at a vertical position one dip below the baseline,
//			 * using the "paint" object for details.
//			 */
//			canvas.drawLine(r.left, baseline + 1, r.right, baseline + 1, paint);
//		}
//
//		// Finishes up by calling the parent method
//		super.onDraw(canvas);
//	}
}
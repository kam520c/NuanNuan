package com.nuannuan.mood.custom.controls;

import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.scau.feelingmusic.R;

/**
 * 自定义的dialog
 * 
 * @author Eden
 * 
 */
public class MyDialog extends Dialog {
	private Window window = null;
	public TextView txt_Title = null;
	public TextView txt_Content = null;
	public ImageView imageView = null;
	private int width = 100;
	private int heigth = 100;

	public MyDialog(Context context) {
		super(context);
	}

	public MyDialog(Context context, int theme) {
		super(context, theme);

		DisplayMetrics dm2 = context.getResources().getDisplayMetrics();
		System.out.println("heigth2 : " + dm2.heightPixels);
		System.out.println("width2 : " + dm2.widthPixels);
		width = dm2.widthPixels / 10 * 9;
		heigth = dm2.heightPixels / 2;
	}

	public void setDialog(int layoutResID) {
		setContentView(layoutResID);
		txt_Title = (TextView) findViewById(R.id.txt_Title);
		txt_Content = (TextView) findViewById(R.id.txt_content);
		imageView = (ImageView) findViewById(R.id.dialog_image);
		window = getWindow();
		window.setWindowAnimations(R.style.dialogWindowAnim);

		window.setLayout(width, heigth);
		setCanceledOnTouchOutside(true);
	}

	/**
	 * 显示dialog
	 */
	public void showDialog() {
		show();
	}

	/**
	 * 取消dialog
	 */
	public void Closedialog() {
		dismiss();
	}
}

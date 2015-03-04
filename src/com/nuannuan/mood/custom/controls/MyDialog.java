package com.nuannuan.mood.custom.controls;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.scau.feelingmusic.R;

/**
 * 鑷畾涔塪ialog绫�
 * 
 * @author lijingjin
 * 
 */
public class MyDialog extends Dialog {
	private Window window = null;
	public TextView txt_Title = null;
	public TextView txt_Content = null;
	public Button btn_Ok = null;
	public Button btn_Close = null;
	public ImageView imageView=null;

	public MyDialog(Context context) {
		super(context);
	}

	public MyDialog(Context context, int theme) {
		super(context, theme);

	}

	public void setDialog(int layoutResID) {
		setContentView(layoutResID);
		txt_Title = (TextView) findViewById(R.id.txt_Title);
		txt_Content = (TextView) findViewById(R.id.txt_content);
		btn_Ok = (Button) findViewById(R.id.dialog_button_ok);
		btn_Close = (Button) findViewById(R.id.dialog_button_cancel);
		imageView= (ImageView)findViewById(R.id.dialog_image);
		window = getWindow();
		window.setWindowAnimations(R.style.dialogWindowAnim); 

		setCanceledOnTouchOutside(false);

		setCancelable(false);
	}

	/**
	 * 鏄剧ずdialog
	 */
	public void showDialog() {
		show();
	}

	/**
	 * 鍏抽棴dialog
	 */
	public void Closedialog() {
		dismiss();
	}
}

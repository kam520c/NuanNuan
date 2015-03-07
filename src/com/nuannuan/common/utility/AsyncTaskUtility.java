package com.nuannuan.common.utility;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

public class AsyncTaskUtility {

	 /**
	  * 
	  * @param mDialog
	  * @param layout
	  */
	  public static void showRoundProcessDialog( Dialog mDialog,int layout)
	    {
	        OnKeyListener keyListener = new OnKeyListener()
	        {
	            @Override
	            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event)
	            {
	                if (keyCode == KeyEvent.KEYCODE_HOME || keyCode == KeyEvent.KEYCODE_SEARCH)
	                    return true;
	                return false;
	            }
	        };
	        //初始化一个AlertDialog。暗度设定无效
	        mDialog.setOnKeyListener(keyListener);
	        mDialog.show();
	        
	        //layout loading_process_dialog_progressBar must be set after show() - alex!
	        mDialog.setContentView(layout);
	        
	        //set dark
	        Window mWindow = mDialog.getWindow();  
	        mWindow.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND); 
	        WindowManager.LayoutParams lp = mWindow.getAttributes();  
	        lp.dimAmount = 0.5f;//暗度为0，就是全亮
	        
	        mWindow.setAttributes(lp);
	        //set dark end
	    }
}

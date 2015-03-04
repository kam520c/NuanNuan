package com.nuannuan.common.asynctask;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ProgressBar;

import com.nuannuan.common.utilitys.AsyncTaskUtility;
import com.scau.feelingmusic.R;

public class AsyncTaskHelper extends AsyncTask<String, Integer, String>{
	private AsyncEvent event;
	private String city="";
	private ProgressBar progressBar;  
	private Dialog mDialog;

	public AsyncTaskHelper(Context context,AsyncEvent event) {
		super();
		this.event=event;
		mDialog = new AlertDialog.Builder(context).create();
		AsyncTaskUtility.showRoundProcessDialog(mDialog, R.layout.loading_process_dialog_anim);
	}
	
	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		try {
			params[0].trim();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		return city;
	}
	 @Override
     protected void onPostExecute(String result) {
         // 返回HTML页面的时候
		 super.onPostExecute(result);
		 event.DataLoadSuccess(result);
		 mDialog.dismiss();   
     }

     @Override
     protected void onPreExecute() {
         // 任务启动，可以在这里显示对话框，这里UI线程处理
     }

     @Override
     protected void onProgressUpdate(Integer... values) {
         // 更新进度
     }


}

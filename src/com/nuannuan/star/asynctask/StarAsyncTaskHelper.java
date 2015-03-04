package com.nuannuan.star.asynctask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;

import com.nuannuan.star.interfaces.StarAsyncEventIm;
import com.scau.feelingmusic.R;

public class StarAsyncTaskHelper extends AsyncTask<String, Integer, JSONArray> {
	private StarAsyncEventIm event;
	private String city = "";
	private ProgressBar progressBar;
	private Dialog mDialog;
	private final String uriAPI = "http://api.uihoo.com/astro/astro.http.php";

	public StarAsyncTaskHelper(Context context, StarAsyncEventIm event) {
		super();
		this.event = event;
		mDialog = new AlertDialog.Builder(context).create();
		AsyncTaskUtility.showRoundProcessDialog(mDialog,
				R.layout.loading_process_dialog_anim);
	}

	/**
	 * URL锟斤拷锟斤拷说锟斤拷 ===============================================
	 * 
	 * @param string
	 *            fun 锟斤拷锟斤拷锟斤拷锟斤拷(day,tomorrow,week,month,year,love)
	 * @param integer
	 *            id 锟斤拷锟斤拷锟斤拷(锟斤拷锟斤拷)
	 * @param string
	 *            format 锟斤拷莞锟绞�json,jsonp,xml)
	 * @param string
	 *            callback 只锟叫碉拷锟斤拷莞锟绞轿猨sonp时,callback锟斤拷锟斤拷锟斤拷锟叫�
	 *            =========================================
	 */

	@Override
	protected JSONArray doInBackground(String... params) {
		// TODO Auto-generated method stub

		// List<String> results = new ArrayList<String>();
		/* 锟斤拷锟斤拷锟斤拷址锟街凤拷 */
		JSONArray arry = new JSONArray();

		String day = getDayData("day", params[0]);
		String tomorrow = getDayData("tomorrow", params[0]);
		String week = getDayData("week", params[0]);
		String month = getDayData("month", params[0]);
		String year = getDayData("year", params[0]);
		String love = getDayData("love", params[0]);

		arry.put(day);
		arry.put(tomorrow);
		arry.put(week);
		arry.put(month);
		arry.put(year);
		arry.put(love);
		
		if(day==null||week==null||month==null||tomorrow==null||year==null||love==null){
			arry=null;
		}

		return arry;

	}

	private String getDayData(String Type, String swichtOne) {

		String result = null;
		/* 锟斤拷锟斤拷HTTP Post锟斤拷锟斤拷 */
		HttpPost httpRequest = new HttpPost(uriAPI);
		/*
		 * Post锟斤拷锟斤拷锟斤拷锟酵憋拷锟斤拷锟斤拷锟斤拷锟斤拷NameValuePair[]锟斤拷锟介储锟斤拷
		 */
		List<NameValuePair> paramList = new ArrayList<NameValuePair>();
		paramList.add(new BasicNameValuePair("fun", Type));
		paramList.add(new BasicNameValuePair("id", swichtOne));
		paramList.add(new BasicNameValuePair("format", "json"));
		try {
			/* 锟斤拷锟斤拷HTTP request */
			httpRequest.setEntity(new UrlEncodedFormEntity(paramList,
					HTTP.UTF_8));
			/* 取锟斤拷HTTP response */
			HttpResponse httpResponse = new DefaultHttpClient()
					.execute(httpRequest);
			/* 锟斤拷状态锟斤拷为200 ok */
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				/* 取锟斤拷锟斤拷应锟街凤拷 */
				String strResult = EntityUtils.toString(httpResponse
						.getEntity());
				result = strResult;
				Log.v("=============result==============", "" + result);
			}

		} catch (ClientProtocolException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	@Override
	protected void onPostExecute(JSONArray result) {
		// 杩斿洖HTML椤甸潰鐨勬椂锟� super.onPostExecute(result);
	
		if (result!= null) {
			event.DataLoadSuccess(result);
		} else {
			event.DataLoadFaild();
		}
		mDialog.dismiss();
	}

	@Override
	protected void onPreExecute() {
		// 浠诲姟鍚姩锛屽彲浠ュ湪杩欓噷鏄剧ず瀵硅瘽妗嗭紝杩欓噷UI绾跨▼澶勭悊
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		// 鏇存柊杩涘害
	}

}

package com.nuannuan.star.asynctask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
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
		//设置传入参数
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

		if (day == null || week == null || month == null || tomorrow == null
				|| year == null || love == null) {
			arry = null;
		}

		return arry;

	}

	private String getDayData(String Type, String swichtOne) {

		String result = null;

		// 创建HttpPost对象
		HttpPost httpPost = new HttpPost(uriAPI);

		// 设置HTTP POST请求参数必须用NameValuePair对象
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("fun", Type));
		params.add(new BasicNameValuePair("id", swichtOne));
		params.add(new BasicNameValuePair("format", "json"));

		HttpResponse httpResponse = null;

		HttpClient client = new DefaultHttpClient();
		// 请求超时
		client.getParams().setParameter(
				CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
		// 读取超时
		client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 5500);

		try {
			// 设置httpPost请求参数
			httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			// Log.v("==============httpPost:=============", "" + httpPost);
			httpResponse = client.execute(httpPost);
			// System.out.println(httpResponse.getStatusLine().getStatusCode());
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				// 第三步，使用getEntity方法活得返回结果
				result = EntityUtils.toString(httpResponse.getEntity());
				Log.e("==============result=============", result);
				// T.displayToast(HttpURLActivity.this, "result:" + result);
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
		// 异步线程请求完之后返回的结果  super.onPostExecute(result);

		if (result != null) {
			event.DataLoadSuccess(result);
		} else {
			event.DataLoadFaild();
		}
		mDialog.dismiss();
	}

	@Override
	protected void onPreExecute() {
		// 
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		// 
	}

}

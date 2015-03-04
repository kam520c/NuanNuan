package com.nuannuan.common.app;

import com.baidu.location.*;

import android.app.Application;
import android.util.Log;
import android.widget.TextView;
import android.os.Process;
import android.os.Vibrator;

public class NuanNuanApp extends Application {

	public LocationClient mLocationClient = null;
	private String mData;
	public MyLocationListenner myListener = new MyLocationListenner();
	public TextView mTv;
	public NotifyLister mNotifyer = null;
	public Vibrator mVibrator01;
	public double latitude, longitude;

	@Override
	public void onCreate() {
		mLocationClient = new LocationClient(getApplicationContext());
		mLocationClient.registerLocationListener(myListener);

		super.onCreate();
		Log.d("locSDK_Demo1",
				"... Application onCreate... pid=" + Process.myPid());
	}

	/**
	 * ��ʾ�ַ�
	 * 
	 * @param str
	 */
	public void logMsg(String str) {
		try {
			mData = str;
			if (mTv != null)
				mTv.setText(mData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public class MyLocationListenner implements BDLocationListener {
		@Override
		public void onReceiveLocation(BDLocation location) {
			if (location == null)
				return;
			StringBuffer sb = new StringBuffer(256);
			sb.append("time : ");
			sb.append(location.getTime());
			sb.append("\nerror code : ");
			sb.append(location.getLocType());
			sb.append("\nlatitude : ");
			sb.append(location.getLatitude());
			sb.append("\nlontitude : ");
			sb.append(location.getLongitude());

			latitude = location.getLatitude();
			longitude = location.getLongitude();
			 Log.v("=================",
			 "-----latitude----"+location.getLatitude());
			 Log.v("=================",
			 "-----longitude----"+location.getLongitude());

			sb.append("\nradius : ");
			sb.append(location.getRadius());
			if (location.getLocType() == BDLocation.TypeGpsLocation) {
				sb.append("\nspeed : ");
				sb.append(location.getSpeed());
				sb.append("\nsatellite : ");
				sb.append(location.getSatelliteNumber());
			} else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
				sb.append("\nʡ��");
				sb.append(location.getProvince());
				sb.append("\n�У�");
				sb.append(location.getCity());
				sb.append("\n��/�أ�");
				sb.append(location.getDistrict());
				sb.append("\naddr : ");
				sb.append(location.getAddrStr());
			}
			sb.append("\nsdk version : ");
			sb.append(mLocationClient.getVersion());
			logMsg(sb.toString());
		}

		public void onReceivePoi(BDLocation poiLocation) {
			if (poiLocation == null) {
				return;
			}
			StringBuffer sb = new StringBuffer(256);
			sb.append("Poi time : ");
			sb.append(poiLocation.getTime());
			sb.append("\nerror code : ");
			sb.append(poiLocation.getLocType());
			sb.append("\nlatitude : ");
			sb.append(poiLocation.getLatitude());
			sb.append("\nlontitude : ");
			sb.append(poiLocation.getLongitude());
			sb.append("\nradius : ");
			sb.append(poiLocation.getRadius());
			if (poiLocation.getLocType() == BDLocation.TypeNetWorkLocation) {
				sb.append("\naddr : ");
				sb.append(poiLocation.getAddrStr());
			}
			if (poiLocation.hasPoi()) {
				sb.append("\nPoi:");
				sb.append(poiLocation.getPoi());
			} else {
				sb.append("noPoi information");
			}
			logMsg(sb.toString());
		}
	}

	public class NotifyLister extends BDNotifyListener {
		public void onNotify(BDLocation mlocation, float distance) {
			mVibrator01.vibrate(1000);
		}
	}

	// ====================================================================================
	private boolean isLogin;
	private String otherReasion;

	public String getOtherReasion() {
		return otherReasion;
	}

	public void setOtherReasion(String otherReasion) {
		this.otherReasion = otherReasion;
	}

	/**
	 * 判断是否登录
	 * 
	 * @return
	 */
	public boolean isLogin() {
		return isLogin;
	}

	/**
	 * 
	 * @param isLogin
	 */
	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}

}
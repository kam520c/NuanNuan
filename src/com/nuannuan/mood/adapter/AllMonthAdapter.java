package com.nuannuan.mood.adapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader.TileMode;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.nuannuan.weather.custom.controls.GalleryView;
import com.scau.feelingmusic.R;

public class AllMonthAdapter extends BaseAdapter {
//	private ImageView[] mImages; // ���浹ӰͼƬ������

	private List<ImageView> imageList = new ArrayList<ImageView>();
	private Context mContext;
	public List<Map<String, Object>> list;
	private int height;

	public Integer[] imgs = { R.drawable.image01, R.drawable.image02,
			R.drawable.image03, R.drawable.image04, R.drawable.image05,
			R.drawable.image01, R.drawable.image02, R.drawable.image03,
			R.drawable.image04, R.drawable.image05, R.drawable.image01,
			R.drawable.image02, R.drawable.image03 };
	public String[] titles = { "01", "02", "03", "04", "05", "06", "07" };
	private List<Integer> mlistMonth = new ArrayList<Integer>();
//	private List<String> mJsonList = new ArrayList<String>();
//	private List<Long> timeList = new ArrayList<Long>();

	public AllMonthAdapter(Context c, int ScreenHeight, List<Integer> intList) {
		this.height = ScreenHeight;
		this.mContext = c;
		this.mlistMonth = intList;
//		this.mJsonList=jsonList;
//		this.timeList=longList;
		
		list = new ArrayList<Map<String, Object>>();
		Date mDate = new Date();
		int month = mDate.getMonth();
		String jsonMood=null;
		
		if (mlistMonth.size() == 0) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("image", imgs[month]);
			list.add(map);

		} else {
			int lastMonth = -1;
			for (int moreMonth = 0; moreMonth<mlistMonth.size()  ; moreMonth++) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				lastMonth = mlistMonth.get(moreMonth);
				map.put("image", imgs[lastMonth]);
				list.add(map);
			}
			if (lastMonth != month) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("image", imgs[month]);
				list.add(map);
			}
		}
//		mImages = new ImageView[list.size()];
		long time=-1;
		for(int j=0;j<list.size();j++){
			ImageView mImageView = new ImageView(mContext);
			
//			try{
//				jsonMood=mJsonList.get(j);
//				time=timeList.get(j);
//			}catch(Exception e){
//				
//			}
//			mImageView.setTag(R.id.tag_json,jsonMood);
//			mImageView.setTag(R.id.tag_time,time);
			imageList.add(mImageView);
		}
		
	}

	/** ���䵹Ӱ */
	public boolean createReflectedImages() {
		final int reflectionGap = 4;
		final int Height = (int) (height * 0.417);
		int index = 0;
		for (Map<String, Object> map : list) {
			Integer id = (Integer) map.get("image");
			// ��ȡԭʼͼƬ
			Bitmap originalImage = BitmapFactory.decodeResource(
					mContext.getResources(), id);
			int width = originalImage.getWidth();
			int height = originalImage.getHeight();
			float scale = Height / (float) height;

			Matrix sMatrix = new Matrix();
			sMatrix.postScale(scale, scale);
			Bitmap miniBitmap = Bitmap.createBitmap(originalImage, 0, 0,
					originalImage.getWidth(), originalImage.getHeight(),
					sMatrix, true);

			// �Ƿ�ԭͼƬ��ݣ���ʡ�ڴ�
			originalImage.recycle();

			int mwidth = miniBitmap.getWidth();
			int mheight = miniBitmap.getHeight();
			Matrix matrix = new Matrix();
			matrix.preScale(1, -1); // ͼƬ����任���ӵͲ��򶥲��ĵ�Ӱ��
			Bitmap reflectionImage = Bitmap.createBitmap(miniBitmap, 0,
					mheight / 2, mwidth, mheight / 2, matrix, false); // ��ȡԭͼ�°벿��
			Bitmap bitmapWithReflection = Bitmap.createBitmap(mwidth,
					(mheight + mheight / 2), Config.ARGB_8888); // ������ӰͼƬ���߶�Ϊԭͼ3/2��

			Canvas canvas = new Canvas(bitmapWithReflection); // ���Ƶ�Ӱͼ��ԭͼ +
																// ��� + ��Ӱ��
			canvas.drawBitmap(miniBitmap, 0, 0, null); // ����ԭͼ
			Paint paint = new Paint();
			canvas.drawRect(0, mheight, mwidth, mheight + reflectionGap, paint); // ����ԭͼ�뵹Ӱ�ļ��
			canvas.drawBitmap(reflectionImage, 0, mheight + reflectionGap, null); // ���Ƶ�Ӱͼ

			paint = new Paint();
			LinearGradient shader = new LinearGradient(0,
					miniBitmap.getHeight(), 0, bitmapWithReflection.getHeight()
							+ reflectionGap, 0x70ffffff, 0x00ffffff,
					TileMode.CLAMP);
			paint.setShader(shader); // ���Խ���Ч��
			paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN)); // ��Ӱ����Ч��
			canvas.drawRect(0, mheight, mwidth,
					bitmapWithReflection.getHeight() + reflectionGap, paint); // ���Ƶ�Ӱ����ӰЧ��

			ImageView imageView = new ImageView(mContext);
			imageView.setImageBitmap(bitmapWithReflection); // ���õ�ӰͼƬ
			imageView.setLayoutParams(new GalleryView.LayoutParams(
					(int) (width * scale),
					(int) (mheight * 3 / 2.0 + reflectionGap)));
			imageView.setScaleType(ScaleType.MATRIX);
			imageList.add(index++, imageView);
//			mImages[index++] = imageView;
		}
		return true;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return imageList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return imageList.get(position); // ��ʾ��ӰͼƬ����ǰ��ȡ���㣩
	}

	public float getScale(boolean focused, int offset) {
		return Math.max(0, 1.0f / (float) Math.pow(2, Math.abs(offset)));
	}

}

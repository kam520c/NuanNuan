package com.nuannuan.mood.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.scau.feelingmusic.R;

public class MoodsAdapter extends BaseAdapter {

	private Context mContext;
	private LayoutInflater mInflater;
	private Integer[] mImageIds = {

	};

	public MoodsAdapter(Context c, Integer[] mImageIds) {
		mContext = c;
		this.mImageIds = mImageIds;
		LayoutInflater mInflaters = LayoutInflater.from(c);
		this.mInflater = mInflaters;
	}

	// ��ȡͼƬ����
	public int getCount() {
		return mImageIds.length;
	}

	// ��ȡͼƬ�ڿ��е�λ��
	public Object getItem(int position) {
		return position;
	}

	// ��ȡͼƬID
	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		// GifView GifView;
		if (convertView == null) {

			viewHolder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.adapter_moods, null);

			viewHolder.img = (ImageView) convertView
					.findViewById(R.id.adapter_img_mood);

		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.img.setBackgroundResource(mImageIds[position]);
		convertView.setTag(viewHolder);

		return convertView;
	}

	private static class ViewHolder {

		public TextView text;
		public ImageView img;
	}
}

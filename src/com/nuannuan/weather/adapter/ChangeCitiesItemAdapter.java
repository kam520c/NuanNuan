package com.nuannuan.weather.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nuannuan.common.R;
import com.nuannuan.common.bean.ChangeCitiesItemBean;

public class ChangeCitiesItemAdapter extends BaseAdapter {
	private LayoutInflater inflater;   
    private List<ChangeCitiesItemBean> changeCitiesItem;   
	
    public ChangeCitiesItemAdapter(List<ChangeCitiesItemBean> changeCitiesItem1, Context context)   
    {   
        super();   
        this.changeCitiesItem = changeCitiesItem1;
        inflater = LayoutInflater.from(context); 
    }   

    @Override  
    public int getCount( )  
    {  
        if (null != changeCitiesItem)   
        {   
            return changeCitiesItem.size();   
        }   
        else  
        {   
            return 0;   
        }   
    }  
  
    @Override  
    public Object getItem( int position )  
    {  
        return changeCitiesItem.get(position);   
    }  
  
    @Override  
    public long getItemId( int position )  
    {  
        return position;   
    }  
  
    @Override  
    public View getView( int position, View convertView, ViewGroup parent )  
    {  
        ViewHolder viewHolder;   
        if (convertView == null)   
        {   
            convertView = inflater.inflate(R.layout.adapter_changecities_item, null);   
            viewHolder = new ViewHolder();   
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);   
            viewHolder.image = (ImageView) convertView.findViewById(R.id.image); 
            viewHolder.weather = (TextView) convertView.findViewById(R.id.weather);
            viewHolder.temp = (TextView) convertView.findViewById(R.id.temp);
            convertView.setTag(viewHolder);   
        } else  
        {   
            viewHolder = (ViewHolder) convertView.getTag();   
        }   
        viewHolder.title.setText(changeCitiesItem.get(position).getTitle());  
        viewHolder.image.setImageResource(changeCitiesItem.get(position).getImageId());
        viewHolder.weather.setText(changeCitiesItem.get(position).getWeather());
        viewHolder.temp.setText(changeCitiesItem.get(position).getTemp());
        return convertView;   
    }  
    
    private class ViewHolder   
    {   
        public TextView temp;
		public TextView weather;
		public ImageView image;   
        public TextView title;  
    }   
    
}

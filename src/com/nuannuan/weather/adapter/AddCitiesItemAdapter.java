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
import com.nuannuan.common.bean.AddCitiesItemBean;
  
public class AddCitiesItemAdapter extends BaseAdapter  
{  
  
    private LayoutInflater inflater;   
    private List<AddCitiesItemBean> addCitiesItem;   
   
    public AddCitiesItemAdapter(List<AddCitiesItemBean> addCitiesItem1, Context context)   
    {   
        super();   
        this.addCitiesItem = addCitiesItem1;
        inflater = LayoutInflater.from(context); 
    }   
    @Override  
    public int getCount( )  
    {  
        if (null != addCitiesItem)   
        {   
            return addCitiesItem.size();   
        }   
        else  
        {   
            return 0;   
        }   
    }  
  
    @Override  
    public Object getItem( int position )  
    {  
        return addCitiesItem.get(position);   
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
            convertView = inflater.inflate(R.layout.adapter_addcities_item, null);   
            viewHolder = new ViewHolder();   
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);   
            viewHolder.image = (ImageView) convertView.findViewById(R.id.image);
            convertView.setTag(viewHolder);   
        } else  
        {   
            viewHolder = (ViewHolder) convertView.getTag();   
        }   
        viewHolder.title.setText(addCitiesItem.get(position).getTitle());  
        if(addCitiesItem.get(position).isShowImage()==true){
        	viewHolder.image.setImageResource(addCitiesItem.get(position).getImageId());
        }
        return convertView;   
    }  
    
    private class ViewHolder   
    {   
        public ImageView image;   
        public TextView title;  
        public boolean isShow;
    }   
}
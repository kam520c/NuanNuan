package com.nuannuan.weather.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

import com.nuannuan.common.R;
import com.nuannuan.common.bean.AddCitiesItemBean;
import com.nuannuan.weather.adapter.AddCitiesItemAdapter;

public class AddCitiesActivity extends Activity{
	private GridView gridView;
	private List<AddCitiesItemBean> addCitiesItem = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏顶部title
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addcities);
		gridView = (GridView) findViewById(R.id.gridview);  
        addCitiesItem = new ArrayList<AddCitiesItemBean>();
        Intent intent = getIntent();
    	AddCitiesItemBean city0= new AddCitiesItemBean("定位");
    	AddCitiesItemBean city1= new AddCitiesItemBean("北京");
    	AddCitiesItemBean city2= new AddCitiesItemBean("上海");
    	AddCitiesItemBean city3= new AddCitiesItemBean("广州");
    	AddCitiesItemBean city4= new AddCitiesItemBean("深圳");
    	AddCitiesItemBean city5= new AddCitiesItemBean("武汉");
    	AddCitiesItemBean city6= new AddCitiesItemBean("南京");
    	AddCitiesItemBean city7= new AddCitiesItemBean("杭州");
    	AddCitiesItemBean city8= new AddCitiesItemBean("西安");
    	AddCitiesItemBean city9= new AddCitiesItemBean("郑州");
    	AddCitiesItemBean city10= new AddCitiesItemBean("成都");
    	AddCitiesItemBean city11= new AddCitiesItemBean("东莞");
    	AddCitiesItemBean city12= new AddCitiesItemBean("沈阳");
    	AddCitiesItemBean city13= new AddCitiesItemBean("天津");
    	AddCitiesItemBean city14= new AddCitiesItemBean("哈尔滨");
    	AddCitiesItemBean city15= new AddCitiesItemBean("长沙");
    	AddCitiesItemBean city16= new AddCitiesItemBean("福州");
    	AddCitiesItemBean city17= new AddCitiesItemBean("石家庄");
    	AddCitiesItemBean city18= new AddCitiesItemBean("苏州");
    	AddCitiesItemBean city19= new AddCitiesItemBean("重庆");
    	AddCitiesItemBean city20= new AddCitiesItemBean("无锡");
    	AddCitiesItemBean city21= new AddCitiesItemBean("济南");
    	AddCitiesItemBean city22= new AddCitiesItemBean("大连");
    	AddCitiesItemBean city23= new AddCitiesItemBean("佛山");
    	AddCitiesItemBean city24= new AddCitiesItemBean("厦门");
    	AddCitiesItemBean city25= new AddCitiesItemBean("南昌");
    	AddCitiesItemBean city26= new AddCitiesItemBean("太原");
    	AddCitiesItemBean city27= new AddCitiesItemBean("长春");
    	AddCitiesItemBean city28= new AddCitiesItemBean("合肥");
    	AddCitiesItemBean city29= new AddCitiesItemBean("浦东");
    	AddCitiesItemBean city30= new AddCitiesItemBean("青岛");
    	AddCitiesItemBean city31= new AddCitiesItemBean("汕头");
    	AddCitiesItemBean city32= new AddCitiesItemBean("昆明");
    	AddCitiesItemBean city33= new AddCitiesItemBean("南宁");
        addCitiesItem.add(city0);
        addCitiesItem.add(city1);
        addCitiesItem.add(city2);
        addCitiesItem.add(city3);
        addCitiesItem.add(city4);
        addCitiesItem.add(city5);
        addCitiesItem.add(city6);
        addCitiesItem.add(city7);
        addCitiesItem.add(city8);
        addCitiesItem.add(city9);
        addCitiesItem.add(city10);
        addCitiesItem.add(city11);
        addCitiesItem.add(city12);
        addCitiesItem.add(city13);
        addCitiesItem.add(city14);
        addCitiesItem.add(city15);
        addCitiesItem.add(city16);
        addCitiesItem.add(city17);
        addCitiesItem.add(city18);
        addCitiesItem.add(city19);
        addCitiesItem.add(city20);
        addCitiesItem.add(city21);
        addCitiesItem.add(city22);
        addCitiesItem.add(city23);
        addCitiesItem.add(city24);
        addCitiesItem.add(city25);
        addCitiesItem.add(city26);
        addCitiesItem.add(city27);
        addCitiesItem.add(city28);
        addCitiesItem.add(city29);
        addCitiesItem.add(city30);
        addCitiesItem.add(city31);
        addCitiesItem.add(city32);
        addCitiesItem.add(city33);
        String city =intent.getStringExtra("city0");
        String city_str = null ;
        try {
			JSONArray cities = new JSONArray(city);
			for(int i=0;i<cities.length()-1;i++){
				String citiess =(String)cities.get(i);
				for(AddCitiesItemBean bean:addCitiesItem){
		        	city_str = bean.getTitle();
		        	if(city_str != null && citiess !=null){
		        	if(city_str.equals(citiess)){
		        		bean.setShowImage(true);
		        		bean.setImageId(R.drawable.btn_save);
		        	}
		        	}
		        }
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        AddCitiesItemAdapter adapter = new AddCitiesItemAdapter(addCitiesItem,this);   
        gridView.setAdapter(adapter);
   
        gridView.setOnItemClickListener(new OnItemClickListener()   
            {   
                @Override  
                public void onItemClick(AdapterView<?> parent, View v, int position, long id)   
                {   
                    Toast.makeText(AddCitiesActivity.this, "item" + (position+1), Toast.LENGTH_SHORT).show();
                    if(addCitiesItem.size()==position+1)
                    {
                    	Intent intent = new Intent();
            			intent.setClass(AddCitiesActivity.this,AddCitiesActivity.class);
            			startActivity(intent);
        			    finish();
                    }
                }  
            });   
    }  
	}

package com.nuannuan.common.bean;

public class ChangeCitiesItemBean {

	private String title;   
    private int imageId;   
    private String weather;
    private String temp;
      
    public ChangeCitiesItemBean()   
    {   
        super();   
    }   
   
    public ChangeCitiesItemBean(String title, int imageId,String weather,String temp)   
    {   
        super();   
        this.title = title;   
        this.imageId = imageId;   
        this.weather = weather;
        this.temp = temp;
    }   

    public String getTitle()   
    {   
        return title;   
    }   
   
    public int getImageId()   
    {   
        return imageId;   
    }
    
    public void getImageId(int imageId) {
		this.imageId = imageId;
	}   

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}   
}

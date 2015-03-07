package com.nuannuan.common.bean;

public class AddCitiesItemBean {

	private String title;   
	private int imageId; 
	private boolean isShowImage = false;
      
    public AddCitiesItemBean()   
    {   
        super();   
    }   
   
    public AddCitiesItemBean(String title, int imageId, boolean isShowImage)   
    {   
        super();   
        this.title = title;   
        this.imageId = imageId;
        this.isShowImage = isShowImage;
    }   
    
    public AddCitiesItemBean(String title, int imageId)   
    {   
        super();   
        this.title = title;   
        this.imageId = imageId;
    }   
    public AddCitiesItemBean(String title)   
    {   
        super();   
        this.title = title;   
    }   

    public String getTitle()   
    {   
        return title;   
    }   
    public int getImageId()   
    {   
        return imageId;   
    }

	public boolean isShowImage() {
		return isShowImage;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	public void setShowImage(boolean isShowImage) {
		this.isShowImage = isShowImage;
	}   
}

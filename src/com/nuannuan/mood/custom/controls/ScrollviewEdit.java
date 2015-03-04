package com.nuannuan.mood.custom.controls;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class ScrollviewEdit extends ScrollView{
	private static final String TAG = "ScrollviewEdit";     
    private ScrollView parent_scrollview;   
    public ScrollView getParent_scrollview() {  
        return parent_scrollview;  
    }  
    public void setParent_scrollview(ScrollView parent_scrollview) {  
        this.parent_scrollview = parent_scrollview;  
    }  
  
    public ScrollviewEdit(Context context) {  
        super(context);  
    }  
      
    public ScrollviewEdit(Context context, AttributeSet attrs) {  
        super(context, attrs);  
    }  
  
    int currentY;  
    @Override  
    public boolean onInterceptTouchEvent(MotionEvent ev) {  
//        LogUtils.i(TAG, "onInterceptTouchEvent--------");      
        if (parent_scrollview == null) {  
            return super.onInterceptTouchEvent(ev);  
        } else {  
            if (ev.getAction() == MotionEvent.ACTION_DOWN) {  
                // ����scrollview�Ĺ����¼�����  
                currentY = (int) ev.getY();  
                setParentScrollAble(false);  
//                LogUtils.i(TAG, "����scrollview�Ĺ����¼�����-----");      
                return super.onInterceptTouchEvent(ev);    
            } else if (ev.getAction() == MotionEvent.ACTION_UP) {  
                // �ѹ����¼��ָ���Scrollview  
                setParentScrollAble(true);  
//                LogUtils.i(TAG, "�ѹ����¼��ָ���Scrollview-----");    
            } else if (ev.getAction() == MotionEvent.ACTION_MOVE) {  
            }  
        }  
        return super.onInterceptTouchEvent(ev);    
    }  
    /** 
     * �Ƿ�ѹ����¼�����scrollview 
     * @param flag 
     */  
    private void setParentScrollAble(boolean flag) {  
        parent_scrollview.requestDisallowInterceptTouchEvent(!flag);  
    }  
}

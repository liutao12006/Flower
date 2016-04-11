package com.example.view;

import android.view.View.MeasureSpec;
import android.widget.GridView;

public class MyGridView extends GridView  
{  
    public MyGridView(android.content.Context context,  
            android.util.AttributeSet attrs)  
    {  
        super(context, attrs);  
    }  
  
    /** 
     * 设置不滚动 
     * Integer.MAX_VALUE >> 2,如果不设置，系统默认设置是显示两条
     */  
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec)  
    {  
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,  
                MeasureSpec.AT_MOST);  
                 super.onMeasure(widthMeasureSpec, expandSpec);  
  
    }  
  
}  
package com.example.unit;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.flower.Myapplication;



public class Utils {
	/**
	 * 将dip转成px
	 * 
	 * @param dpValue
	 * @return
	 */
	public static int dip2px(float dpValue) {
		float scale = Myapplication.getContext().getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5F);
	}
	
	/*
	 * 动态设置listview的item的高度   解决scrollview 和listview滑动冲突的方法  
	 */
	
	public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
      if (listAdapter == null) {
      // pre-condition
            return;
      }

      int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
      for (int i = 0; i < listAdapter.getCount(); i++) {
           View listItem = listAdapter.getView(i, null, listView);
           if (listItem instanceof ViewGroup) {
              listItem.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
           }
           listItem.measure(0, 0);
           totalHeight += listItem.getMeasuredHeight();
      }

      ViewGroup.LayoutParams params = listView.getLayoutParams();
      params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
                listView.setLayoutParams(params);
  }
}

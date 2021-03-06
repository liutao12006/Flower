package com.example.taobaohead.headview;

import java.util.List;

import com.example.bean.BeanVo;
import com.example.flower.R;
import com.example.flower.inteface.OnAdapterClickListener;
import com.example.unit.Utils;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.TextView;



/*
 * 类似淘宝 、头条
 * 
 */
public class ScrollTopView extends LinearLayout {

	private Scroller mScroller;  //滚动实例

	private List<BeanVo> articleList;  //存放数据集合
	private final int DURING_TIME = 2000;  //滚动延迟
	private OnAdapterClickListener<BeanVo> click;  
	
	private List<BeanVo> articleList1;  //存放数据集合

	public ScrollTopView(Context context) {
		super(context);
		init();
	}

	public ScrollTopView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
		mScroller = new Scroller(getContext());
	}

	/**
	 * 设置数据
	 * @param articleList
	 */
	public void setData(List<BeanVo> articleList) {
		this.articleList = articleList;
		if (articleList != null) {
			removeAllViews();
			Log.i("tag", articleList.size() + "");
			int size = articleList.size() > 1 ? 4 : articleList.size();
			for (int i = 0; i < size; i++) {
				addContentView(i);
			}
			if (articleList.size() > 3) {
				getLayoutParams().height = Utils.dip2px(50);  //调节滚动数据的高度
				// 滚动
				cancelAuto();
				mHandler.sendEmptyMessageDelayed(0, DURING_TIME);
				smoothScrollBy(0, Utils.dip2px(50));
			}
		}
	}

	/**
	 * 设置列表点击事件
	 * 
	 * @param click
	 */
	public void setClickListener(OnAdapterClickListener<BeanVo> click) {
		this.click = click;
	}

	/**
	 * 重置数据
	 */
	private void resetView() {
		BeanVo article = articleList.get(0);
		articleList.remove(0);
		articleList.add(article);

		for (int i = 0; i < 4; i++) {
			addContentView(i);
		}
	}

	/**
	 * 取消滚动
	 */
	public void cancelAuto() {
		mHandler.removeMessages(0);
	}

	private void addContentView(int position) {
		ViewHolder mHolder;
		if (position >= getChildCount()) {
			mHolder = new ViewHolder();
			View v = View.inflate(getContext(), R.layout.myhead, null);
			mHolder.nameTv = (TextView) v.findViewById(R.id.tv);
			mHolder.nameTv1 = (TextView) v.findViewById(R.id.tv1);
			v.setTag(mHolder);
			addView(v, LayoutParams.MATCH_PARENT, Utils.dip2px(50));
		} else {
			mHolder = (ViewHolder) getChildAt(position).getTag();
		}
		final BeanVo article = articleList.get(position);
		final BeanVo article1 = articleList.get(position);
		mHolder.nameTv.setText(article.getName());
	    mHolder.nameTv1.setText(article1.getName());
		mHolder.nameTv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (click != null) {
					click.onAdapterClick(null, article);
				}
				// if(null != article){
				// Intent intent = new Intent(getContext(),
				// CareHairDetailActivity.class);
				// intent.putExtra("id", article.getId());
				// intent.putExtra("name", article.getName());
				// getContext().startActivity(intent);
				// }
			}
		});
		
		mHolder.nameTv1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (click != null) {
					click.onAdapterClick(null, article);
				}
			}
		});
	}

	private class ViewHolder {
		TextView nameTv;
		TextView nameTv1;
	}

	Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			mHandler.removeMessages(0);
			mHandler.sendEmptyMessageDelayed(0, DURING_TIME);
			smoothScrollBy(0, Utils.dip2px(50));
			resetView();
		};
	};

	// 调用此方法设置滚动的相对偏移
	public void smoothScrollBy(int dx, int dy) {
		// 设置mScroller的滚动偏移量
		mScroller.startScroll(mScroller.getFinalX(), 0, dx, dy, DURING_TIME);
		invalidate();// 这里必须调用invalidate()才能保证computeScroll()会被调用，否则不一定会刷新界面，看不到滚动效果
	}

	@Override
	public void computeScroll() {

		// 先判断mScroller滚动是否完成
		if (mScroller.computeScrollOffset()) {

			// 这里调用View的scrollTo()完成实际的滚动
			scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
			// 必须调用该方法，否则不一定能看到滚动效果
			postInvalidate();

		}
		super.computeScroll();
	}

	public void setData1(List<BeanVo> list, List<BeanVo> list1) {

		this.articleList = list;
		this.articleList = list1;
		if (articleList != null) {
			removeAllViews();
			Log.i("tag", articleList.size() + "");
			int size = articleList.size() > 1 ? 4 : articleList.size();
			for (int i = 0; i < size; i++) {
				addContentView(i);
			}
			if (articleList.size() > 3) {
				getLayoutParams().height = Utils.dip2px(50);  //调节滚动数据的高度
				// 滚动
				cancelAuto();
				mHandler.sendEmptyMessageDelayed(0, DURING_TIME);
				smoothScrollBy(0, Utils.dip2px(50));
			}
		}
	
	}
}

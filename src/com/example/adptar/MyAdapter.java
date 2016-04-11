package com.example.adptar;

import java.util.List;

import com.example.flower.R;
import com.squareup.picasso.Picasso;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class MyAdapter  extends PagerAdapter{
	
	private Context mContext;
	List<String> url;
	ImageClickListener imgClickListener;
//	public ImageClickListener getImgClickListener() {
//		return imgClickListener;
//	}
//
//	public void setImgClickListener(ImageClickListener imgClickListener) {
//		this.imgClickListener = imgClickListener;
//	}
	
	public MyAdapter(Context context, List<String> url,ImageClickListener imgClickListener) {
		this.mContext = context;
		this.url = url;
		this.imgClickListener = imgClickListener;
	}
	public MyAdapter(Context context, List<String> url) {
		this.mContext = context;
		this.url = url;
	}

	@Override
	public int getCount() {
		return url.size();
	}

	@Override
	public Object instantiateItem(View view, final int position) {
		ImageView imageview = new ImageView(mContext);
		imageview.setScaleType(ScaleType.CENTER_CROP);
		Picasso.with(mContext).load(url.get(position))
		.placeholder(R.color.grayColor).error(R.color.grayColor)
		.into(imageview);
        ((ViewPager) view).addView(imageview);
        imageview.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(imgClickListener!=null)
				{
					System.out.println("22");
					imgClickListener.imageClick(position);
				}
			}
		});
		return imageview;
//		((ViewPager) arg0).addView(imageViews.get(arg1));
//		return imageViews.get(arg1);
	}
	//回调接口
	public interface ImageClickListener 
	  {
		 public void imageClick(int position);
	  }

	@Override
	public void destroyItem(View arg0, int arg1, Object arg2) {
		((ViewPager) arg0).removeView((View) arg2);
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public void restoreState(Parcelable arg0, ClassLoader arg1) {

	}

	@Override
	public Parcelable saveState() {
		return null;
	}

	@Override
	public void startUpdate(View arg0) {

	}

	@Override
	public void finishUpdate(View arg0) {

	}


}

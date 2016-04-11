package com.example.adptar;

import java.util.List;

import com.example.bean.GridViewGoods;
import com.example.flower.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridViewAdapter extends BaseAdapter {

	Context context;
	List<GridViewGoods> list;

	public GridViewAdapter(Context context, List<GridViewGoods> list) {
		this.list = list;
		this.context = context;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(convertView==null){
			holder = new ViewHolder();
			
			convertView=LayoutInflater.from(context).inflate(R.layout.topscrollviewlist_item, null);
			holder.goodsiamge = (ImageView)convertView.findViewById(R.id.one_gridview_goods_imageview);
			holder.goodsname = (TextView) convertView.findViewById(R.id.one_gridview_goods_name);
			holder.goodsoldprice = (TextView) convertView.findViewById(R.id.one_gridview_goods_oldprice);
			holder.goodsnewprice = (TextView) convertView.findViewById(R.id.one_gridview_goods_newprice);
		    convertView.setTag(holder);
	    }else
            {
            holder = (ViewHolder)convertView.getTag();
       }
		GridViewGoods  goods = list.get(position);
		holder.goodsname.setText(goods.getGoodsname());
		holder.goodsoldprice.setText(goods.getOidprice());
		holder.goodsnewprice.setText(goods.getNewprice());
		return convertView;
	}
	
	static class ViewHolder{
		ImageView goodsiamge;
		TextView goodsname;
		TextView goodsoldprice;
		TextView goodsnewprice;
	}

}

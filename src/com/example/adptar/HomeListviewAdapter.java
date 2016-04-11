package com.example.adptar;

import java.util.List;
import java.util.Map;

import com.example.adptar.GridViewAdapter.ViewHolder;
import com.example.flower.R;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeListviewAdapter extends BaseAdapter {
	Context context;
	List<Map<String, Object>>  data;

	public HomeListviewAdapter(Context context, List<Map<String, Object>> data) {
		super();
		this.context = context;
		this.data = data;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder  holder;
		if(convertView == null){
			holder = new ViewHolder();
			convertView=LayoutInflater.from(context).inflate(R.layout.home_mylistview_item, null);
			holder.goodsiamge=(ImageView) convertView.findViewById(R.id.imageView1);
			holder.goodsname=(TextView) convertView.findViewById(R.id.textView1);
			convertView.setTag(holder);
		}else{
			 holder = (ViewHolder)convertView.getTag();
		}
		Map<String, Object> map = data.get(position);
		//holder.goodsiamge.setBackground((Drawable) map.get("image"));
		holder.goodsname.setText((CharSequence) map.get("name"));
		return convertView;
	}
	
	static class ViewHolder{
		ImageView goodsiamge;
		TextView goodsname;
	}
}

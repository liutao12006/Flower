package com.example.fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.example.adptar.GalleryAdapter;
import com.example.adptar.GridViewAdapter;
import com.example.adptar.HomeListviewAdapter;
import com.example.adptar.MyAdapter;
import com.example.adptar.MyAdapter.ImageClickListener;
import com.example.bean.BeanVo;
import com.example.bean.GridViewGoods;
import com.example.flower.R;
import com.example.flower.ThreeActivity;
import com.example.flower.TwoActivity;
import com.example.flower.inteface.OnAdapterClickListener;
import com.example.taobaohead.headview.ScrollTopView;
import com.example.unit.AsyncBitmapLoader;
import com.example.unit.AsyncBitmapLoader.ImageCallBack;
import com.example.unit.Utils;
import com.example.unit.getPicList;
import com.example.view.MyListView;
import com.example.view.PullToRefreshView;
import com.example.view.PullToRefreshView.OnFooterRefreshListener;
import com.example.view.PullToRefreshView.OnHeaderRefreshListener;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

/**
 *frangment 中的view的点击事件 应该在它所在的Activity中写  这个页面中的 签到  邀请好友 等4个view的点击事件都在OneActivity 
 */
public class FirstFragment extends Fragment implements ImageClickListener,OnHeaderRefreshListener,
                    OnFooterRefreshListener{
	private ViewPager viewPager; // android-support-v4中的滑动组件
	private List<ImageView> imageViews; // 滑动的图片集合
	ProgressDialog p;
	private String[] titles; // 图片标题
	private int[] imageResId; // 图片ID
	private List<View> dots; // 图片标题正文的那些点
    private List<String> url=null,file=null;
	private TextView tv_title;
	private int currentItem = 0; // 当前图片的索引号
	// An ExecutorService that can schedule commands to run after a given delay,
	// or to execute periodically.
	private ScheduledExecutorService scheduledExecutorService;
	
	View view;
	ScrollTopView mytaobaoheadview;
	
	GridView gridView,gridView1,gridView2,home_mainGridview;
	List<GridViewGoods> goodsList;
	
   List<Map<String, Object>> data;
   
   ListView home_main_listview;
   //主gridview 的图片
   private int[] mainGridViewImageId={R.drawable.yaoqing,R.drawable.qiandao,R.drawable.choujiang,R.drawable.yaoqing,R.drawable.qiandao,R.drawable.choujiang};
   private int[] mainaListViewImageId={R.drawable.yaoqing,R.drawable.qiandao,R.drawable.choujiang,R.drawable.yaoqing,R.drawable.qiandao,R.drawable.choujiang
		   ,R.drawable.yaoqing,R.drawable.qiandao,R.drawable.choujiang,R.drawable.yaoqing,R.drawable.qiandao,R.drawable.choujiang
	       ,R.drawable.yaoqing,R.drawable.qiandao,R.drawable.choujiang,R.drawable.yaoqing,R.drawable.qiandao,R.drawable.choujiang,R.drawable.qiandao,R.drawable.choujiang};
   
	String[]  listviewitem_jianjie={"jianjie1","jianjie2","jianjie3","jianjie4","jianjie5","jianjie6","jianjie7","jianjie8","jianjie9","jianjie10","jianjie11","jianjie12","jianjie13","jianjie14","jianjie15","jianjie16","jianjie17","jianjie18","jianjie19","jianjie20"};
	//下拉刷新组件
	private PullToRefreshView mPullToRefreshView;
   // 切换当前显示的图片

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				System.out.println("cccc");
				viewPager.setCurrentItem(currentItem);
				break;
			case 1:
				
				break;
			default:
				break;
			}
		}
	};
	@Override
	public View onCreateView(android.view.LayoutInflater inflater, android.view.ViewGroup container, Bundle savedInstanceState) {
	   view =inflater.inflate(R.layout.fragment_1, null);
	   //上拉刷新  下拉加载
	   mPullToRefreshView = (PullToRefreshView) view.findViewById(R.id.main_pull_refresh_view);
	   mPullToRefreshView.setOnHeaderRefreshListener(this);
	   mPullToRefreshView.setOnFooterRefreshListener(this);
	   mPullToRefreshView.setLastUpdated(new Date().toLocaleString());//获取时间
	   //最下面的Listview
	   home_main_listview = (ListView) view.findViewById(R.id.home_mainListView);
	   HomeListviewAdapter homeListviewaAdapter = new HomeListviewAdapter(getContext(),getDate());
	   home_main_listview.setAdapter(homeListviewaAdapter);
	   //解决scrollview 和listview滑动冲突的方法
	   Utils.setListViewHeightBasedOnChildren(home_main_listview);
	  //主题gridview
	   home_mainGridview = (GridView) view.findViewById(R.id.home_mainGridView);
	   data = new ArrayList<Map<String,Object>>();
	   for(int i=0;i<mainGridViewImageId.length;i++){
		   Map<String, Object> hashmap = new HashMap<String, Object>();
		   hashmap.put("image", mainGridViewImageId[i]);
		   data.add(hashmap);
	   }
	   SimpleAdapter simpleAdapter = new SimpleAdapter(
			   getContext(), 
			   data, 
			   R.layout.home_maingridview_item, 
			   new String[]{"image"}, 
			   new int[]{R.id.home_maingridview_item_imageview});
	   
	   home_mainGridview.setAdapter(simpleAdapter);
	   home_mainGridview.setOnItemClickListener(new HomeMainListener());
	   //个人中心下的横向商品展示
	   gridView = (GridView) view.findViewById(R.id.gridview1_home_boye_list_view1);
	   gridView1 = (GridView) view.findViewById(R.id.gridview2_home_boye_list_view1);
	   gridView2 = (GridView) view.findViewById(R.id.gridview3_home_boye_list_view1);
	   setData();
	   setGridView(gridView);
	   setGridView(gridView1);
	   setGridView(gridView2);
	   setListener(gridView);
	   setListener(gridView1);
	   setListener(gridView2);
	 //个人中心的新闻滚动条  及点击事件
	   mytaobaoheadview=(ScrollTopView) view.findViewById(R.id.mytaobao);
	   
	   List<BeanVo> list = new ArrayList<BeanVo>();
		List<BeanVo> list1 = new ArrayList<BeanVo>();
		BeanVo vo = new BeanVo("age", "dyq");
		BeanVo vo1 = new BeanVo("age1", "dyq1");
		BeanVo vo2 = new BeanVo("age2", "dyq2");
		BeanVo vo3 = new BeanVo("age3", "dyq3");

		list.add(vo1);
		list.add(vo2);
		list.add(vo3);
		list.add(vo);
		list1.add(vo1);
		list1.add(vo2);
		list1.add(vo3);
		list1.add(vo);
		

		mytaobaoheadview.setData1(list,list1);
		mytaobaoheadview.setClickListener( new OnAdapterClickListener<BeanVo>(){
			@Override
			public void onAdapterClick(View v, BeanVo t) {
				Toast.makeText(getContext(), "taiozhang", 1000).show();
				Intent intent = new Intent(getContext(), TwoActivity.class);
				startActivity(intent);
			}});
	   //viewPager轮流播放网络图片 及各种事件
       String a="http://g.hiphotos.baidu.com/image/pic/item/3bf33a87e950352ac8725f015143fbf2b2118ba3.jpg";
       String b="http://f.hiphotos.baidu.com/image/pic/item/5bafa40f4bfbfbed2ec46f987af0f736aec31fb3.jpg";
       String c="http://h.hiphotos.baidu.com/image/pic/item/eac4b74543a98226202210a88882b9014b90eb5a.jpg";
       String d= "http://d.hiphotos.baidu.com/image/pic/item/79f0f736afc3793181f5dfcee9c4b74542a911b3.jpg";
       String e="http://f.hiphotos.baidu.com/image/pic/item/2f738bd4b31c8701a4828b69257f9e2f0708ff0c.jpg";
       url=new ArrayList<String>();
       url.add(a);
       url.add(b);
       url.add(c);
       url.add(d);
       url.add(e);
       getPicList gp=new getPicList();
       file=gp.getFilePathList(url);
		   
		    imageResId = new int[] { R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e };
			titles = new String[imageResId.length];
			titles[0] = "巩俐不低俗，我就不能低俗";
			titles[1] = "扑树又回来啦！再唱经典老歌引万人大合唱";
			titles[2] = "揭秘北京电影如何升级";
			titles[3] = "乐视网TV版大派送";
			titles[4] = "热血�丝的反杀";

			imageViews = new ArrayList<ImageView>();

			// 初始化图片资源
			for (int i = 0; i < url.size(); i++) {
				System.out.println("i");
				ImageView image = new ImageView(this.getActivity());
				//imageView.setImageBitmap(getBitmap(file.get(i).toString()));
				AsyncBitmapLoader asyncBitmapLoader=new AsyncBitmapLoader();
				Bitmap bitmap=asyncBitmapLoader.loadBitmap(image, url.get(i).toString(), new ImageCallBack() {  
					                  
					                 public void imageLoad(ImageView imageView, Bitmap bitmap) {  
					                     // TODO Auto-generated method stub  
					                    imageView.setImageBitmap(bitmap);
					                    System.out.println("eee");
					                }  
					            });  
					             if(bitmap == null)    
					                {    
					                   image.setImageResource(R.drawable.ic_launcher);    
					                 }    
					                 else    
					                {    
					                     image.setImageBitmap(bitmap);    
					               }    

				image.setScaleType(ScaleType.FIT_XY);
				imageViews.add(image);
			}

			//点
			dots = new ArrayList<View>();
			dots.add(view.findViewById(R.id.v_dot0));
			dots.add(view.findViewById(R.id.v_dot1));
			dots.add(view.findViewById(R.id.v_dot2));
			dots.add(view.findViewById(R.id.v_dot3));
			dots.add(view.findViewById(R.id.v_dot4));
//			Toast.makeText(this.getContext(), "你点击了图片", 1000).show();
			tv_title = (TextView) view.findViewById(R.id.tv_title);
			tv_title.setText(titles[0]);//

			viewPager = (ViewPager) view.findViewById(R.id.vp);
			viewPager.setAdapter(new MyAdapter(this.getActivity(),url,this));// 设置填充ViewPager页面的适配器
			// 设置一个监听器，当ViewPager中的页面改变时调用
			viewPager.setOnPageChangeListener(new MyPageChangeListener());
			return view;
		
		
	}     


	private Bitmap getBitmap(String path)
	{
		String myJpgPath = path;
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inSampleSize = 2;
		Bitmap bm = BitmapFactory.decodeFile(myJpgPath, options);
		return bm;
		
	}
	
   @Override
    public void onStart() {
	   scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    // 当Activity显示出来后，每两秒钟切换一次图片显示
	   System.out.println("aaa");
		scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 5, 5, TimeUnit.SECONDS);
	super.onStart();
}
   @Override
    public void onStop() {
	// 当Activity不可见的时候停止切换
		scheduledExecutorService.shutdown();
	super.onStop();
}

	/**
	 * 换行切换任务
	 * 
	 * @author Administrator
	 * 
	 */
	private class ScrollTask implements Runnable {

		public void run() {
			synchronized (viewPager) {
				System.out.println("currentItem: " + currentItem);
				System.out.println("bbbbb");
				currentItem = (currentItem + 1) % imageViews.size();
				handler.sendEmptyMessage(0);// 通过Handler切换图片
			}
		}

	}

	/**
	 * 当ViewPager中页面的状态发生改变时调用
	 * 
	 * @author Administrator
	 * 
	 */
	private class MyPageChangeListener implements OnPageChangeListener {
		private int oldPosition = 0;

		/**
		 * This method will be invoked when a new page becomes selected.
		 * position: Position index of the new selected page.
		 */
		public void onPageSelected(int position) {
			currentItem = position;
			tv_title.setText(titles[position]);
			dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
			dots.get(position).setBackgroundResource(R.drawable.dot_focused);
			oldPosition = position;
		}

		public void onPageScrollStateChanged(int arg0) {

		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}
	}

	@Override
	public void imageClick(int position) {
		// TODO Auto-generated method stub
		System.out.println("oooooooo");
		Toast.makeText(this.getContext(), "你点击了图片"+position, 1000).show();
		Intent intent = new Intent(this.getActivity(),TwoActivity.class);
		startActivity(intent);

	}

	//为gridview 设置初始化数据

		private void setData() {
			goodsList = new ArrayList<GridViewGoods>();
			GridViewGoods good = new GridViewGoods("imageuri","大宝","￥10.00","￥6.66");
			goodsList.add(good);
			GridViewGoods good1 = new GridViewGoods("imageuri","大宝","￥10.00","￥6.66");
			goodsList.add(good1);
			GridViewGoods good2 = new GridViewGoods("imageuri","大宝","￥10.00","￥6.66");
			goodsList.add(good2);
			GridViewGoods good3 = new GridViewGoods("imageuri","大宝","￥10.00","￥6.66");
			goodsList.add(good3);
			GridViewGoods good4 = new GridViewGoods("imageuri","大宝","￥10.00","￥6.66");
			goodsList.add(good4);
			GridViewGoods good5 = new GridViewGoods("imageuri","查看更多"," "," ");
			goodsList.add(good5);
		}
		
		/**
		 * 设置GirdView
		 */
		private void setGridView(GridView view) {
			int size = goodsList.size();

			int length = 100;

			DisplayMetrics dm = new DisplayMetrics();//获取屏幕的参数
			getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
			float density = dm.density;
			int gridviewWidth = (int) (size * (length + 4) * density);
			System.out.println(gridviewWidth);
     		int itemWidth = (int) (length * density);

			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(gridviewWidth,
					LinearLayout.LayoutParams.FILL_PARENT);
			view.setLayoutParams(params); // 重点
			view.setColumnWidth(itemWidth); // 重点  每个item的宽度
			view.setHorizontalSpacing(10); // 间距
			view.setStretchMode(GridView.NO_STRETCH);
			view.setNumColumns(size); // 重点
          //  if(view.getId()==R.id.gridview1_home_boye_list_view1){
            	GridViewAdapter adapter = new GridViewAdapter(this.getActivity(), goodsList);
            	view.setAdapter(adapter);
            //}

		}
		
		 //设置gridView中Item的点击事件
		private void setListener(GridView view) {
			view.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					switch (position) {
					case 0:
						Intent intent =new Intent(getActivity(), TwoActivity.class);
					startActivity(intent);
						break;
					case 1:
						Intent intent1 =new Intent(getActivity(), ThreeActivity.class);
					startActivity(intent1);
						break;
						
					default:
						break;
					}
					
				}
			});
			
		}
		//处理主题gridview item的点击事件
		private  final class HomeMainListener implements OnItemClickListener{
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(getContext(), String.valueOf(position), 1000).show();
			}
			
		}
		//Listview 的数据
		public List<Map<String, Object>>  getDate(){
			List<Map<String, Object>> data = new ArrayList<Map<String,Object>>();
			for(int i=0;i<20;i++){
				Map<String, Object> hashmap = new HashMap<String, Object>();
				hashmap.put("image", mainaListViewImageId[i]);
				hashmap.put("name", listviewitem_jianjie[i]);
				data.add(hashmap);
			}
			return data;}

        //下拉加载
		@Override
		public void onFooterRefresh(PullToRefreshView view) {
			mPullToRefreshView.postDelayed(new Runnable() {

				@Override
				public void run() {
					mPullToRefreshView.onFooterRefreshComplete();
					//gridViewData.add(R.drawable.pic1);
					//myAdapter.setGridViewData(gridViewData);
					Toast.makeText(getContext(), "加载更多数据!", 0).show();
				}

			}, 3000);
		}

        //上拉刷新
		@Override
		public void onHeaderRefresh(PullToRefreshView view) {
			mPullToRefreshView.postDelayed(new Runnable() {
				@Override
				public void run() {
					mPullToRefreshView.onHeaderRefreshComplete("更新于:"
							+ Calendar.getInstance().getTime().toLocaleString());
					mPullToRefreshView.onHeaderRefreshComplete();

					Toast.makeText(getContext(), "数据刷新完成!", 0).show();
				}

			}, 3000);
		}
	
}

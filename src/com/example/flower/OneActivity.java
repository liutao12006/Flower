package com.example.flower;

import java.util.ArrayList;








import com.example.fragment.FirstFragment;
import com.example.fragment.FourFragment;
import com.example.fragment.ThreeFragment;
import com.example.fragment.TwoFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

public class OneActivity extends FragmentActivity {

	private ViewPager viewPager;
	private ArrayList<Fragment> fragments;
	private MyPagerAdapter adapter;
    private RadioButton r0;
	private RadioButton r1;
	private RadioButton r2;
	private RadioButton r3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.one);
		setViews();
		setListeners();
		//��Viewpager����FragmentPagerAdapter
		fragments=new ArrayList<Fragment>();
		//��fragments�������fragment����
		fragments.add(new FirstFragment());
		fragments.add(new TwoFragment());
		fragments.add(new ThreeFragment());
		fragments.add(new FourFragment());
		adapter=new MyPagerAdapter(getSupportFragmentManager());
		viewPager.setAdapter(adapter);
	}
	/**
	 * ��ʼ����ť
	 */
    private void setViews() {
		viewPager=(ViewPager) findViewById(R.id.viewPager);
		r0=(RadioButton) findViewById(R.id.radio0);
		r1=(RadioButton) findViewById(R.id.radio1);
		r2=(RadioButton) findViewById(R.id.radio2);
		r3=(RadioButton) findViewById(R.id.radio3);
		
	}
    /**
     * 
     * @author Administrator
     *
     */
	class MyPagerAdapter extends FragmentPagerAdapter{
          public MyPagerAdapter(FragmentManager fm) {
			super(fm);
			}
         @Override
		public Fragment getItem(int arg0) {
			
			return fragments.get(arg0);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return fragments.size();
		}
		
	}
	/**
	 * �����ťʵ�ֵ��RaidoButtonʱviewPagerҳ��Ļ���
	 * @param view
	 */
	public void doClick(View view){
		switch (view.getId()) {
		case R.id.radio0:
			viewPager.setCurrentItem(0);
			break;
		case R.id.radio1:
			viewPager.setCurrentItem(1);
			break;
		case R.id.radio2:
			viewPager.setCurrentItem(2);
			break;
		case R.id.radio3:
			viewPager.setCurrentItem(3);
			break;

		
		}
		
	}
	/**
	 * ��Ӽ����¼���������viewPagerʱ�±ߵ�raidoButtonҲ��任
	 */
	private void setListeners() {
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				switch (position) {
				case 0:
					r0.setChecked(true);
					break;
				case 1:
					r1.setChecked(true);
					break;
				case 2:
					r2.setChecked(true);
					break;
				case 3:
					r3.setChecked(true);
					break;

				
				}
				
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
     
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	//�������¼���fragment1 �� ǩ��  �齱  ������ѵ��ĸ���ť�ĵ���¼�
	public void imageClick(View v){    

	    switch (v.getId()) {
		case R.id.ImageView04:
			Toast.makeText(this, "�������", 1000).show();
			break;
	    case R.id.ImageView05:
	    	Toast.makeText(this, "Ƥ������", 1000).show();
			break;
	   case R.id.ImageView06:
		   Toast.makeText(this, "�齱", 1000).show();
			break;
	   case R.id.ImageView07:
		   Toast.makeText(this, "ǩ��", 1000).show();
		   System.out.println("ǩ��");
//		   Toast.makeText(FirstFragment.this.getActivity(), "ǩ��", 1000).show();
			break;
		default:
			break; 
		 }
		}

}


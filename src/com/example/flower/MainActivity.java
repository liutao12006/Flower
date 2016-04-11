package com.example.flower;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends TabActivity {

	private TabHost tabHost;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tabHost = getTabHost();
		add("��ҳ", R.drawable.itembg, new Intent(this, OneActivity.class));
		add("�ͷ�", R.drawable.itembg1, new Intent(this, TwoActivity.class));
		add("����", R.drawable.itembg2, new Intent(this, ThreeActivity.class));
		add("���ﳵ", R.drawable.itembg1, new Intent(this, TwoActivity.class));
		add("�ҵ�", R.drawable.itembg2, new Intent(this, ThreeActivity.class));
		
	}

	private void add( String title,int bg,Intent intent) {
		// TODO Auto-generated method stub
		 View localView = LayoutInflater.from(this.tabHost.getContext()
				 ).inflate(R.layout.tab_item, null);//����ļ��¿�����tabhost ��ͼƬ��С��������ɫ
		 
		 //(ImageView)
		 (localView.findViewById(R.id.tabImg)).setBackgroundResource(bg);
		  ((TextView)localView.findViewById(R.id.tabText)).setText(title);
		  TabHost.TabSpec localTabSpec = tabHost.newTabSpec(title).setIndicator(localView).setContent(intent);
		  tabHost.addTab(localTabSpec);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}

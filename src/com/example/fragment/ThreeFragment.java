package com.example.fragment;



import com.example.flower.Cheeses;
import com.example.flower.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ThreeFragment extends Fragment{
	private ListView listView3;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    
		View view =inflater.inflate(R.layout.fragment_3, null);
		listView3=(ListView) view.findViewById(R.id.listView3);
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
				  getActivity(), android.R.layout.simple_list_item_1, Cheeses.sCheeseStrings);
		  listView3.setAdapter(arrayAdapter);
		return view;
	}

}

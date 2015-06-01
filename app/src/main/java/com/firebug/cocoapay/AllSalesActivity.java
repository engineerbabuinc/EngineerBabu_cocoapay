package com.firebug.cocoapay;

import java.util.ArrayList;

import com.firebug.cocoapay.adapter.AllSalesAdapter;
import com.firebug.cocoapay.datainfo.AllSalesInfo;
import com.firebug.cocoapay.datainfo.PricePointsInfo;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class AllSalesActivity extends Activity

{
	private ActionBar actionBar;
	private ListView allSalesListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.allsales);
		allSalesListView = (ListView) findViewById(R.id.allsaleslist);

		actionBar = getActionBar();
		customactionBar();

		ArrayList<AllSalesInfo> arrayList = new ArrayList<AllSalesInfo>();
		for (int i = 0; i < 100; i++) {
			AllSalesInfo items = new AllSalesInfo();
			items.setSaleDate("Today");
			items.setSaleProduct("Rs 350 -3 Products");
			items.setSaleTime("12:16 PM");
			arrayList.add(items);

		}
		AllSalesAdapter adapter = new AllSalesAdapter(AllSalesActivity.this,
				R.layout.allsales_list_item, arrayList);
		allSalesListView.setAdapter(adapter);
	}

	@SuppressLint("NewApi")
	public void customactionBar() {

		/*
		 * actionBar.setDisplayShowHomeEnabled(false);
		 * actionBar.setDisplayShowTitleEnabled(false);
		 */
		LayoutInflater mInflater = LayoutInflater.from(this);

		View mCustomView = mInflater.inflate(R.layout.custom_actionbar, null);

		actionBar.setCustomView(mCustomView);
		actionBar.setDisplayOptions(actionBar.DISPLAY_SHOW_CUSTOM
				| actionBar.DISPLAY_SHOW_HOME);
		View homeIcon = findViewById(android.R.id.home);
		// Hides the View (and so the icon)
		((View) homeIcon.getParent()).setVisibility(View.GONE);
		TextView titleTextView = (TextView) mCustomView
				.findViewById(R.id.title);
		titleTextView.setText("All Sales");
		ImageView button = (ImageView) mCustomView.findViewById(R.id.home_icon);
		button.setVisibility(View.INVISIBLE);

		ImageView cartImageView = (ImageView) mCustomView
				.findViewById(R.id.cart_icon);
		cartImageView.setVisibility(View.INVISIBLE);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
}

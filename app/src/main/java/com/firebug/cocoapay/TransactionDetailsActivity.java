package com.firebug.cocoapay;

import java.util.ArrayList;

import com.firebug.cocoapay.datainfo.ProductListItems;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class TransactionDetailsActivity extends Activity {
	private ActionBar actionBar;
	private ListView todayListView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.transaction_details);
		todayListView = (ListView) findViewById(R.id.items_list);
		actionBar = getActionBar();
		customactionBar();
		

		ArrayList<ProductListItems> arrayList = new ArrayList<ProductListItems>();
		for (int i = 0; i < 100; i++) {
			ProductListItems items = new ProductListItems();
			items.setProductImage("R.drawable.ic_launcher");
			items.setProductName("Pencil");
			items.setProductPrice("$ 100");
			arrayList.add(items);

		}
		ProductListAdapter adapter = new ProductListAdapter(TransactionDetailsActivity.this,
				R.layout.slider_list_item, arrayList);
		todayListView.setAdapter(adapter);
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
		titleTextView.setText("Rs. 1009 Sale");
		ImageView button=(ImageView) mCustomView.findViewById(R.id.home_icon);
		button.setVisibility(View.INVISIBLE);
		
		ImageView cartImageView=(ImageView) mCustomView.findViewById(R.id.cart_icon);
		cartImageView.setVisibility(View.INVISIBLE);
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

}

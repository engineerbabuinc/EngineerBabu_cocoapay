package com.firebug.cocoapay;

import java.util.ArrayList;

import com.firebug.cocoapay.datainfo.PricePointsInfo;
import com.firebug.cocoapay.datainfo.ProductListItems;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class PricePointsActivity extends Activity {
	private ActionBar actionBar;
	private ListView todayListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.price_points);
		todayListView = (ListView) findViewById(R.id.pricepointlist);
		actionBar = getActionBar();
		customactionBar();

		ArrayList<PricePointsInfo> arrayList = new ArrayList<PricePointsInfo>();
		for (int i = 0; i < 100; i++) {
			PricePointsInfo items = new PricePointsInfo();
			items.setProductName("Pencil");
			items.setProductPrice("$ 100");
			arrayList.add(items);

		}
		PricePointsAdapter adapter = new PricePointsAdapter(
				PricePointsActivity.this, R.layout.price_points_list_item, arrayList);
		todayListView.setAdapter(adapter);

	}
	
	public void addDynamicLayout()
	{
		LayoutInflater inflater =
			    (LayoutInflater)this.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
		
		View view = inflater.inflate( R.layout.price_points_list_item, null );
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
		titleTextView.setText("Price Points");
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

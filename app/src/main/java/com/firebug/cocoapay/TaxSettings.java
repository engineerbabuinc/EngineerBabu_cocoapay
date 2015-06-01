package com.firebug.cocoapay;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class TaxSettings extends Activity {
	
	private ActionBar actionBar;
	private View mCustomView;
	private ImageView cartImageView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tax_settings);
		actionBar = getActionBar();
		customactionBar();
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@SuppressLint("NewApi")
	public void customactionBar() {

		/*
		 * actionBar.setDisplayShowHomeEnabled(false);
		 * actionBar.setDisplayShowTitleEnabled(false);
		 */
		LayoutInflater mInflater = LayoutInflater.from(this);

		mCustomView = mInflater.inflate(R.layout.custom_actionbar, null);

		actionBar.setCustomView(mCustomView);
		actionBar.setDisplayOptions(actionBar.DISPLAY_SHOW_CUSTOM
				| actionBar.DISPLAY_SHOW_HOME);
		View homeIcon = findViewById(android.R.id.home);

		TextView titleTextView = (TextView) mCustomView
				.findViewById(R.id.title);
		titleTextView.setText("Tax");
		// Hides the View (and so the icon)
		((View) homeIcon.getParent()).setVisibility(View.GONE);

		ImageView button = (ImageView) mCustomView.findViewById(R.id.home_icon);
		button.setImageResource(R.drawable.ic_action_back);
		button.setVisibility(View.VISIBLE);

		cartImageView = (ImageView) mCustomView.findViewById(R.id.cart_icon);
		cartImageView.setImageResource(R.drawable.ic_action_new);
		cartImageView.setVisibility(View.VISIBLE);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				TaxSettings.this.finish();
				
			}
		});
		cartImageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(TaxSettings.this,AddTaxLevelActivity.class));

			}
		});

	}
}

package com.firebug.cocoapay;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class SupportActivity extends Activity implements OnClickListener {

	private View mCustomView;
	private ImageView cartImageView;
	private ActionBar actionBar;
	private TextView profileTextView;
	private TextView profileTextViewOne;
	private TextView profileTextViewTwo;
	private TextView securityTextView;
	private TextView taxTextView;
	private TextView tippingTextView;
	private TextView employeeTextView;
	private boolean isTrue=false;
	
	private boolean isTrueOne=false;
	private boolean isTrueTwo=false;
	private LinearLayout securityLayout, taxLayout, tipLayout,
			empLayout;
	private LinearLayout profileLayoutOne,profileLayoutTwo; 
	private View profileViewOne,profileViewTwo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.support);
		actionBar = getActionBar();
		customactionBar();
		profileViewOne=findViewById(R.id.view_profile_one);
		profileViewTwo=findViewById(R.id.view_profile_two);
		profileTextView = (TextView) findViewById(R.id.profile_tv);
		profileTextViewOne = (TextView) findViewById(R.id.profile_tv_one);
		profileTextViewTwo = (TextView) findViewById(R.id.profile_tv_two);
		securityTextView = (TextView) findViewById(R.id.security_tv);
		taxTextView = (TextView) findViewById(R.id.tax_tv);
		tippingTextView = (TextView) findViewById(R.id.tip_tv);
		employeeTextView = (TextView) findViewById(R.id.emp_tv);

		profileTextView.setOnClickListener(this);
		profileTextViewOne.setOnClickListener(this);
		profileTextViewTwo.setOnClickListener(this);
		securityTextView.setOnClickListener(this);
		taxTextView.setOnClickListener(this);
		tippingTextView.setOnClickListener(this);
		employeeTextView.setOnClickListener(this);

		profileLayoutOne = (LinearLayout) findViewById(R.id.profile_layout_one);
		profileLayoutTwo = (LinearLayout) findViewById(R.id.profile_layout_two);
		securityLayout = (LinearLayout) findViewById(R.id.security_layout);
		taxLayout = (LinearLayout) findViewById(R.id.tax_layout);
		tipLayout = (LinearLayout) findViewById(R.id.tipping_layout);
		empLayout = (LinearLayout) findViewById(R.id.employees_layout);
	}

	public void callView(LinearLayout layout) {
		
		layout.setVisibility(View.VISIBLE);
		LayoutParams lparams = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		TextView tv = new TextView(this);
		tv.setLayoutParams(lparams);
		tv.setText(getResources().getString(R.string.dummytext));
		layout.addView(tv);
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
		titleTextView.setText("Support");
		// Hides the View (and so the icon)
		((View) homeIcon.getParent()).setVisibility(View.GONE);

		ImageView button = (ImageView) mCustomView.findViewById(R.id.home_icon);
		button.setImageResource(R.drawable.ic_action_back);
		button.setVisibility(View.VISIBLE);

		cartImageView = (ImageView) mCustomView.findViewById(R.id.cart_icon);
		cartImageView.setVisibility(View.INVISIBLE);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SupportActivity.this.finish();

			}
		});

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.profile_tv:
			toggleView();
			break;
			
		case R.id.profile_tv_one:
			toggleTvOne(profileLayoutOne);
			break;
			
		case R.id.profile_tv_two:
			toggleTvTwo(profileLayoutTwo);
			break;

		case R.id.security_tv:
			//toggleView(securityLayout);
			break;

		case R.id.tax_tv:
			//toggleView(taxLayout);
			break;

		case R.id.tip_tv:
			//toggleView(tipLayout);
			break;

		case R.id.emp_tv:
			//toggleView(empLayout);
			break;

		default:
			break;
		}
	}
	
	private void toggleView() {
		// TODO Auto-generated method stub
		if(!isTrue)
		{
			//callView(layout);
			
			profileTextViewOne.setVisibility(View.VISIBLE);
			profileTextViewTwo.setVisibility(View.VISIBLE);
			profileViewTwo.setVisibility(View.VISIBLE);
			profileViewOne.setVisibility(View.VISIBLE);
			isTrue=true;
		}
		else
		{
			profileTextViewOne.setVisibility(View.GONE);
			profileTextViewTwo.setVisibility(View.GONE);
			profileViewTwo.setVisibility(View.GONE);
			profileViewOne.setVisibility(View.GONE);
			isTrue=false;
			//layout.setVisibility(View.GONE);
		}
	}
	
	private void toggleTvOne(LinearLayout layout) 
	{
		if(!isTrueOne)
		{
			callView(layout);
			profileViewOne.setVisibility(View.VISIBLE);
			isTrueOne=true;
		}
		else
		{
			isTrueOne=false;
			layout.setVisibility(View.GONE);
			profileViewOne.setVisibility(View.VISIBLE);
		}
	}
	
	private void toggleTvTwo(LinearLayout layout) 
	{
		if(!isTrueTwo)
		{
			callView(layout);
			profileViewTwo.setVisibility(View.VISIBLE);
			isTrueTwo=true;
		}
		else
		{
			isTrueTwo=false;
			layout.setVisibility(View.GONE);
			profileViewTwo.setVisibility(View.VISIBLE);
		}
	}
}

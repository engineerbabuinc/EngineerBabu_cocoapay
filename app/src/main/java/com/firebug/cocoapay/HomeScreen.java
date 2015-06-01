package com.firebug.cocoapay;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.firebug.cocoapay.adapter.CartAdapter;
import com.firebug.cocoapay.adapter.TabsPagerAdapter;
import com.firebug.cocoapay.datainfo.CartInfo;
import com.firebug.cocoapay.main.AppConstants;
import com.firebug.cocoapay.utils.ApplicationSettings;
import com.firebug.cocoapay.utils.SimpleGestureFilter;

public class HomeScreen extends FragmentActivity implements
		ActionBar.TabListener {
	@SuppressWarnings("unused")
	private SimpleGestureFilter detector;

	private ViewPager viewPager;
	private TabsPagerAdapter mAdapter;
	
	// Tab titles
	private String[] tabs = { "Custom Charge", "Product List" };
	private View mCustomView;
	private ImageView cartImageView;
	private ActionBar actionBar;
	// The "x" and "y" position of the "Show Button" on screen.
	Point p;
	public boolean isTrue = false;
	private PopupWindow popupWindow;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// Initilization
		viewPager = (ViewPager) findViewById(R.id.pager);
		actionBar = getActionBar();
		customactionBar();
		mAdapter = new TabsPagerAdapter(getSupportFragmentManager());
		/*
		 * Drawable res =
		 * getResources().getDrawable(R.drawable.tab_selected_example);
		 * getActionBar().setBackgroundDrawable(res);
		 */
		viewPager.setAdapter(mAdapter);
		actionBar.setHomeButtonEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		// Adding Tabs
		/*
		 * for (String tab_name : tabs) {
		 * 
		 * SpannableString s = new SpannableString(
		 * Html.fromHtml("<font color=\"#ed207b\"><b>" + tab_name +
		 * "</b></font>")); s.setSpan(new
		 * com.medinfi.utils.TypefaceSpan(MainActivity.this,
		 * "RobotoRegular.ttf"), 0, s.length(),
		 * Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		 * 
		 * actionBar.addTab(actionBar.newTab().setText(s)
		 * .setTabListener(this)); }
		 */
		for (String tab_name : tabs) {
			LayoutInflater inflater = LayoutInflater.from(this);
			View customView = inflater.inflate(R.layout.tab_title, null);

			TextView titleTV = (TextView) customView
					.findViewById(R.id.action_custom_title);
			titleTV.setText(tab_name);
			// Here you can also add any other styling you want.
			// actionBar.getTabAt(i).setCustomView(customView);
			actionBar.addTab(actionBar.newTab().setCustomView(customView)
					.setTabListener(this));
		}

		/**
		 * on swiping the viewpager make respective tab selected
		 * */
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// on changing the page
				// make respected tab selected
					viewPager.setTag(ApplicationSettings.getPref(AppConstants.CHARGE_PRICE, ""));
				Log.e("Values::::::::::::Homescreen::::::::::::::::::::::::", ""+ApplicationSettings.getPref(AppConstants.CHARGE_PRICE, ""));
				Log.e("Values::::::::::::Homescreen::::::::::::::::::::::Charge Static", ""+CustomChargeFragment.chargeValue);
				actionBar.setSelectedNavigationItem(position);
				  ValueUpdateInterface fragment = (ValueUpdateInterface) mAdapter.instantiateItem(viewPager, position);
	                if (fragment != null) {
	                    fragment.fragmentBecameVisible();
	                } 
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}

	@SuppressLint("ResourceAsColor")
	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// on tab selected
		// show respected fragment view

		if (popupWindow != null && popupWindow.isShowing()) {
			popupWindow.dismiss();
		}

		viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
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
		// Hides the View (and so the icon)
		((View) homeIcon.getParent()).setVisibility(View.GONE);

		ImageView button = (ImageView) mCustomView.findViewById(R.id.home_icon);
		button.setVisibility(View.VISIBLE);

		cartImageView = (ImageView) mCustomView.findViewById(R.id.cart_icon);
		cartImageView.setVisibility(View.VISIBLE);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int width = (int) TypedValue.applyDimension(
						TypedValue.COMPLEX_UNIT_DIP, 60, getResources()
								.getDisplayMetrics());
				int screenwidth = getWindowManager().getDefaultDisplay()
						.getWidth();
				int t = screenwidth - width;
				SlideoutActivity.prepare(HomeScreen.this, R.id.pager, width);
				startActivity(new Intent(HomeScreen.this, MenuActivity.class));
				overridePendingTransition(0, 0);
				// overridePendingTransition(R.anim.slide_in_left,
				// R.anim.slide_in_right);

			}
		});

		cartImageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// Open popup window
				/*
				 * if (p != null) showPopup(HomeScreen.this, p, cartImageView);
				 */
				toggleView();
			}
		});
	}

	// Get the x and y position after the button is draw on screen
	// (It's important to note that we can't get the position in the onCreate(),
	// because at that stage most probably the view isn't drawn yet, so it will
	// return (0, 0))
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {

		int[] location = new int[2];
		ImageView cartImageView = (ImageView) mCustomView
				.findViewById(R.id.cart_icon);

		// Get the x, y location and store it in the location[] array
		// location[0] = x, location[1] = y.
		cartImageView.getLocationOnScreen(location);

		// Initialize the Point with x, and y positions
		p = new Point();
		p.x = location[0];
		p.y = location[1];

	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent me) {
		// Call onTouchEvent of SimpleGestureFilter class
		return super.dispatchTouchEvent(me);
	}

	// The method that displays the popup.
	private void showPopup(final Activity context, Point p, ImageView imageView) {
		LayoutInflater layoutInflater = (LayoutInflater) getBaseContext()
				.getSystemService(LAYOUT_INFLATER_SERVICE);
		View popupView = layoutInflater.inflate(R.layout.cart_popup, null);
		popupWindow = new PopupWindow(popupView, LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		popupWindow.setWindowLayoutMode(ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT);
		
		// Set content width and height
		popupWindow.setHeight(WindowManager.LayoutParams.MATCH_PARENT);
		popupWindow.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
		popupView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		ListView cartListView = (ListView) popupView
				.findViewById(R.id.cart_list);

		ArrayList<CartInfo> arrayList = new ArrayList<CartInfo>();
		for (int i = 0; i < 100; i++) {
			CartInfo items = new CartInfo();
			items.setCartName("Product Name");
			items.setCartPrice("Rs 60*2 = 120 ");
			items.setCartQuantity("10");
			arrayList.add(items);

		}
		CartAdapter adapter = new CartAdapter(HomeScreen.this,
				R.layout.cart_list_item, arrayList);
		cartListView.setAdapter(adapter);

		/*
		 * Button btnDismiss = (Button) popupView.findViewById(R.id.dismiss);
		 * btnDismiss.setOnClickListener(new Button.OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { // TODO Auto-generated method
		 * stub popupWindow.dismiss(); } });
		 */

		popupWindow.showAsDropDown(imageView, 0, 0);
	}

	private void toggleView() {
		// TODO Auto-generated method stub
		if (!isTrue) {
			showPopup(HomeScreen.this, p, cartImageView);
			// paymentPopup();
			isTrue = true;
		} else {
			isTrue = false;
			if (popupWindow != null && popupWindow.isShowing()) {
				popupWindow.dismiss();
			}
			/*
			 * if(dialog!=null) dialog.cancel();
			 */
		}
	}

	public void paymentPopup() {

		// TODO Auto-generated method stub
		// custom dialog
		dialog = new Dialog(HomeScreen.this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setCancelable(true);
		dialog.setContentView(R.layout.cart_popup);
		dialog.show();
	}

	public Dialog dialog;
}

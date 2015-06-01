package com.firebug.cocoapay.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.firebug.cocoapay.ProductListFragment;
import com.firebug.cocoapay.CustomChargeFragment;
import com.firebug.cocoapay.main.AppConstants;
import com.firebug.cocoapay.utils.ApplicationSettings;

public class TabsPagerAdapter extends FragmentPagerAdapter {

	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {
		case 0:
			// Top Rated fragment activity
			return new CustomChargeFragment();
		case 1:
			// Games fragment activity
			/*Log.e("Values::::::::::::::::::::::::::::::::::::", ""+ApplicationSettings.getPref(AppConstants.CHARGE_PRICE, ""));
			Log.e("Values::::::::::::::::::::::::::::::::::Charge Static", ""+CustomChargeFragmentragment.chargeValue);
			*/
			Bundle bundle=new Bundle();
			bundle.putString("a", ApplicationSettings.getPref(AppConstants.CHARGE_PRICE, ""));
			return new ProductListFragment(ApplicationSettings.getPref(AppConstants.CHARGE_PRICE, ""));
		}

		return null;
	}

	@Override
	public int getCount() {
		// get item count - equal to number of tabs
		return 2;
	}

}

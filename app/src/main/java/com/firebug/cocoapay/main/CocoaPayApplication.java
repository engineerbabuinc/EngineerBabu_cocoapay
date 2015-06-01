package com.firebug.cocoapay.main;

import android.app.Application;
import android.util.Log;

import com.firebug.cocoapay.tableutils.DBManager;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

public class CocoaPayApplication extends Application {
	private static CocoaPayApplication appInstance;
	public String customChargeValue;
	
	
	public String getCustomChargeValue() {
		return customChargeValue;
	}

	public void setCustomChargeValue(String customChargeValue) {
		this.customChargeValue = customChargeValue;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onCreate() {
		appInstance = this;
		super.onCreate();
		this.initializeInstance();
		// UNIVERSAL IMAGE LOADER SETUP
		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
				.cacheOnDisc(true).cacheInMemory(true)
				.imageScaleType(ImageScaleType.EXACTLY)
				.displayer(new FadeInBitmapDisplayer(300)).build();

		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				getApplicationContext())
				.defaultDisplayImageOptions(defaultOptions)
				.memoryCache(new WeakMemoryCache())
				.discCacheSize(100 * 1024 * 1024).build();

		ImageLoader.getInstance().init(config);
		// END - UNIVERSAL IMAGE LOADER SETUP
	}

	protected void initializeInstance() {
		Log.e("Inside Application", "CocoaPayApplication");
		DBManager.initializeDB( CocoaPayApplication.this, AppConstants.COCOAPAY);


	}

	public static CocoaPayApplication getAppInstance() {
		if (appInstance == null)
			throw new NullPointerException("Application is not initialized");
		return appInstance;
	}

	public String getAppName() {
		return "COCOAPAY";
	}
	
}

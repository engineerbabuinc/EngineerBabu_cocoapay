package com.firebug.cocoapay.main;

import android.app.Application;


public abstract class AocApplication extends Application
{
	public static final boolean DEBUG_MODE = true;
	protected static AocApplication instance;
	
	public abstract String getAppDir();
	
	public abstract String getAppName();
	
	public abstract String getTwitterAuthKey();
	
	public abstract String getTwitterAuthSectetKey();
	
	public abstract String getTweeterUserName();


	public static AocApplication getInstance()
	{
		if( instance == null)
			throw new NullPointerException( "Application has not been initialized");
		return instance;
	}


}

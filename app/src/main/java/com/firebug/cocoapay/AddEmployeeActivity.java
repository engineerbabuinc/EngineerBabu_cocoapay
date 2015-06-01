package com.firebug.cocoapay;

import android.app.Activity;
import android.os.Bundle;

public class AddEmployeeActivity extends Activity 
{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_employee);
		getActionBar().hide();
	}

}

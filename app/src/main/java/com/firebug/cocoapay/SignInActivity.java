package com.firebug.cocoapay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SignInActivity extends Activity {
	private Button signInButton;
	private Button signUpButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sign_in);
		signInButton=(Button) findViewById(R.id.signin_btn);
		
		signInButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(SignInActivity.this, HomeScreen.class);
                startActivity(i);
                // close this activity
                finish();
			}
		});

		}

}

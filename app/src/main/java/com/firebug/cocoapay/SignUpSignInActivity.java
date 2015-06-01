package com.firebug.cocoapay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SignUpSignInActivity extends Activity {
	
	private Button signInButton;
	private Button signUpButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup_signin);
		signInButton=(Button) findViewById(R.id.signin_button);
		signUpButton=(Button) findViewById(R.id.signup_button);
		
		signInButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(SignUpSignInActivity.this, SignInActivity.class);
                startActivity(i);
                // close this activity
                finish();
			}
		});

		signUpButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(SignUpSignInActivity.this, SignUpActivity.class);
                startActivity(i);
 
                // close this activity
                finish();
			}
		});
}

}

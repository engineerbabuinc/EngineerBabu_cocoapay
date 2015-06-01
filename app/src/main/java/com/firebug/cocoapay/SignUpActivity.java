package com.firebug.cocoapay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SignUpActivity extends Activity {

	private Button signUpButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sign_up_one);
		signUpButton = (Button) findViewById(R.id.signup_btn);
		signUpButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(SignUpActivity.this,
						SignUpTwoActivity.class);
				startActivity(i);

				// close this activity
				finish();
			}
		});

	}

}

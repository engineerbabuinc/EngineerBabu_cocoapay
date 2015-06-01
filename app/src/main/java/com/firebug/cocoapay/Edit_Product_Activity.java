package com.firebug.cocoapay;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Edit_Product_Activity extends Activity {

    private ActionBar actionBar;
    private ImageView addPricePointImageView;
    private Button addProductButton;
    private AutoCompleteView categoryAutoCompleteView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_product);

        String[] countries = getResources().
                getStringArray(R.array.list_of_countries);
        ArrayAdapter adapter = new ArrayAdapter
                (this,android.R.layout.simple_list_item_1,countries);

        addProductButton=(Button) findViewById(R.id.add_product_button);
        addPricePointImageView=(ImageView) findViewById(R.id.pricepoint_iv);
        categoryAutoCompleteView=(AutoCompleteView) findViewById(R.id.category);
        categoryAutoCompleteView.setAdapter(adapter);


        actionBar = getActionBar();
        customactionBar();
    }

    @SuppressLint("NewApi")
    public void customactionBar() {

		/*
		 * actionBar.setDisplayShowHomeEnabled(false);
		 * actionBar.setDisplayShowTitleEnabled(false);
		 */
        LayoutInflater mInflater = LayoutInflater.from(this);

        View mCustomView = mInflater.inflate(R.layout.custom_actionbar, null);

    actionBar.setCustomView(mCustomView);
    actionBar.setDisplayOptions(actionBar.DISPLAY_SHOW_CUSTOM
            | actionBar.DISPLAY_SHOW_HOME);

        View homeIcon = findViewById(android.R.id.home);
        // Hides the View (and so the icon)
        ((View) homeIcon.getParent()).setVisibility(View.GONE);
        TextView titleTextView = (TextView) mCustomView
                .findViewById(R.id.title);
        titleTextView.setText("Edit Product");
        ImageView button=(ImageView) mCustomView.findViewById(R.id.home_icon);
        button.setImageResource(R.drawable.ic_action_back);
        button.setVisibility(View.VISIBLE);
        ImageView cartImageView=(ImageView) mCustomView.findViewById(R.id.cart_icon);
        cartImageView.setVisibility(View.INVISIBLE);

        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
				/*int width = (int) TypedValue.applyDimension(
						TypedValue.COMPLEX_UNIT_DIP, 60, getResources()
								.getDisplayMetrics());
				int screenwidth = getWindowManager().getDefaultDisplay()
						.getWidth();
				int t = screenwidth - width;

				SlideoutActivity.prepare(AddProductActivity.this, R.id.lay_id,
						width);
				startActivity(new Intent(AddProductActivity.this,
						MenuActivity.class));
				overridePendingTransition(0, 0);*/

                Edit_Product_Activity.this.finish();

            }
        });

    }
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }
}

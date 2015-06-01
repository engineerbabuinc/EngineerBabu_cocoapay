package com.firebug.cocoapay;

import java.util.ArrayList;

import com.firebug.cocoapay.adapter.CartAdapter;
import com.firebug.cocoapay.datainfo.CartInfo;
import com.firebug.cocoapay.datainfo.ProductListItems;
import com.firebug.cocoapay.main.AppConstants;
import com.firebug.cocoapay.main.CocoaPayApplication;
import com.firebug.cocoapay.utils.ApplicationSettings;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public final class CustomChargeFragment extends Fragment implements OnClickListener,ValueUpdateInterface {
	private static final String KEY_CONTENT = "TestFragment:Content";
	public Dialog dialog;
	public Button numberpadOne, numberpadTwo, numberpadThree, numberpadFour,
			numberpadFive, numberpadSix, numberpadSeven, numberpadEight,
			numberpadNine, numberpadZero, numberpadAdd, numberpadClear;
	public TextView numpadValue,customChargeTv;
	public int number=0;
	public static String chargeValue;
	CocoaPayApplication application;
	public static CustomChargeFragment newInstance(String content) {
		CustomChargeFragment fragment = new CustomChargeFragment();

		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 20; i++) {
			builder.append(content).append(" ");
		}
		builder.deleteCharAt(builder.length() - 1);
		fragment.mContent = builder.toString();

		return fragment;
	}

	private String mContent = "???";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if ((savedInstanceState != null)
				&& savedInstanceState.containsKey(KEY_CONTENT)) {
			mContent = savedInstanceState.getString(KEY_CONTENT);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		/*
		 * TextView text = new TextView(getActivity());
		 * text.setGravity(Gravity.CENTER); text.setText("RAJNAL");
		 * text.setTextSize(20 * getResources().getDisplayMetrics().density);
		 * text.setPadding(20, 20, 20, 20);
		 * 
		 * LinearLayout layout = new LinearLayout(getActivity());
		 * layout.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
		 * LayoutParams.FILL_PARENT)); layout.setGravity(Gravity.CENTER);
		 * layout.addView(text);
		 * 
		 * return layout;
		 */
		
		
		View view = inflater.inflate(R.layout.ccl, container, false);
		application=CocoaPayApplication.getAppInstance();
		numpadValue=(TextView) view.findViewById(R.id.numpad_value);
		customChargeTv=(TextView) view.findViewById(R.id.custom_charge_tv);
		
		numberpadAdd = (Button) view.findViewById(R.id.numpad_add);
		numberpadClear = (Button) view.findViewById(R.id.numpad_clear);
		
		numberpadOne = (Button) view.findViewById(R.id.numpad_one);
		numberpadTwo = (Button) view.findViewById(R.id.numpad_two);
		numberpadThree = (Button) view.findViewById(R.id.numpad_three);
		numberpadFour = (Button) view.findViewById(R.id.numpad_four);
		numberpadFive = (Button) view.findViewById(R.id.numpad_five);
		numberpadSix = (Button) view.findViewById(R.id.numpad_six);
		numberpadSeven = (Button) view.findViewById(R.id.numpad_seven);
		numberpadEight = (Button) view.findViewById(R.id.numpad_eight);
		numberpadNine = (Button) view.findViewById(R.id.numpad_nine);
		numberpadZero = (Button) view.findViewById(R.id.numpad_zero);
		
		numberpadAdd.setOnClickListener(this);
		numberpadClear.setOnClickListener(this);
		numberpadOne.setOnClickListener(this);
		numberpadTwo.setOnClickListener(this);
		numberpadThree.setOnClickListener(this);
		numberpadFour.setOnClickListener(this);
		numberpadFive.setOnClickListener(this);
		numberpadSix.setOnClickListener(this);
		numberpadSeven.setOnClickListener(this);
		numberpadEight.setOnClickListener(this);
		numberpadNine.setOnClickListener(this);
		numberpadZero.setOnClickListener(this);
		
		LinearLayout chargeLayout = (LinearLayout) view
				.findViewById(R.id.charge_layout);

		chargeLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				paymentPopup();
			}
		});
		return view;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString(KEY_CONTENT, mContent);
	}

	public void paymentPopup() {

		// TODO Auto-generated method stub
		// custom dialog
		dialog = new Dialog(getActivity());
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.getWindow().setBackgroundDrawable(
				new ColorDrawable(Color.TRANSPARENT));
		dialog.setCancelable(true);

		dialog.setContentView(R.layout.payment_popup);
		dialog.show();
		RelativeLayout viewLayout = (RelativeLayout) dialog
				.findViewById(R.id.rl2);
		ImageView closeBtn = (ImageView) dialog.findViewById(R.id.close_btn);
		ImageView nxtImageView = (ImageView) dialog
				.findViewById(R.id.nxt_arrow_iv);

		nxtImageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				paymentPopupCart();
			}
		});

		viewLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				paymentPopupCart();
			}
		});

		ImageView paymentCart = (ImageView) dialog
				.findViewById(R.id.payment_cart);
		paymentCart.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				paymentPopupCart();

			}
		});

		closeBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (dialog != null && dialog.isShowing()) {
					dialog.cancel();
				}
			}
		});
	}

	public void paymentPopupCart() {

		if (dialog != null && dialog.isShowing()) {
			dialog.cancel();
		}
		final Dialog dialogCart = new Dialog(getActivity());
		dialogCart.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialogCart.getWindow().setBackgroundDrawable(
				new ColorDrawable(Color.TRANSPARENT));
		dialogCart.setCancelable(true);
		dialogCart.setContentView(R.layout.payment_cart);
		dialogCart.show();
		ListView cartListView = (ListView) dialogCart
				.findViewById(R.id.cart_list);
		RelativeLayout backImageView = (RelativeLayout) dialogCart
				.findViewById(R.id.parenttop2);

		backImageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (dialogCart != null && dialogCart.isShowing()) {
					dialogCart.cancel();
				}
				paymentPopup();
			}
		});
		ArrayList<CartInfo> arrayList = new ArrayList<CartInfo>();
		for (int i = 0; i < 100; i++) {
			CartInfo items = new CartInfo();
			items.setCartImage("R.drawable.ic_launcher");
			items.setCartName("Pencil");
			items.setCartPrice("Rs. 60*2 =Rs. 120");
			items.setCartQuantity("10");
			arrayList.add(items);

		}
		CartAdapter adapter = new CartAdapter(getActivity(),
				R.layout.cart_list_item, arrayList);
		cartListView.setAdapter(adapter);

		ImageView closeBtn = (ImageView) dialogCart
				.findViewById(R.id.close_btn);
		closeBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (dialogCart != null && dialogCart.isShowing()) {
					dialogCart.cancel();
				}
			}
		});

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.numpad_add:
			
			break;
case R.id.numpad_clear:
	numpadValue.setText("");
	customChargeTv.setText("");
	ApplicationSettings.putPref(AppConstants.CHARGE_PRICE, "0.00");
			break;
case R.id.numpad_one:
	addValuetoTV(numpadValue, "1");
	break;
case R.id.numpad_two:
	addValuetoTV(numpadValue, "2");
	break;
case R.id.numpad_three:
	addValuetoTV(numpadValue, "3");
	break;
case R.id.numpad_four:
	addValuetoTV(numpadValue, "4");
	break;
case R.id.numpad_five:
	addValuetoTV(numpadValue, "5");
	break;
case R.id.numpad_six:
	addValuetoTV(numpadValue, "6");
	break;
case R.id.numpad_seven:
	addValuetoTV(numpadValue, "7");
	break;
case R.id.numpad_eight:
	addValuetoTV(numpadValue, "8");
	break;
case R.id.numpad_nine:
	addValuetoTV(numpadValue, "9");
	break;
case R.id.numpad_zero:
	addValuetoTV(numpadValue, "0");
	break;
		default:
			break;
		}
	}
	
	public void addValuetoTV(TextView textView,String value)
	{
		String et=textView.getText().toString()+value;
		
		if(et.length()>0)
		chargeValue=et;
		else
			chargeValue="";
		
		ApplicationSettings.putPref(AppConstants.CHARGE_PRICE, et);
		textView.setText(et);
		customChargeTv.setText(et);
		application.setCustomChargeValue(et);
		
	}

	@Override
	public void fragmentBecameVisible() {
		if(customChargeTv!=null)
		{
			Log.e("AHHHHHHHHHHHHHHHHHHHHHHHHHHH", " Custom ChargeCumminggggggggggggggggg");
			customChargeTv.setText(ApplicationSettings.getPref(AppConstants.CHARGE_PRICE, ""));
		}
	
	}
}

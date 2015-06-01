package com.firebug.cocoapay;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firebug.cocoapay.adapter.CartAdapter;
import com.firebug.cocoapay.datainfo.CartInfo;
import com.firebug.cocoapay.datainfo.ProductListItems;
import com.firebug.cocoapay.main.AppConstants;
import com.firebug.cocoapay.main.CocoaPayApplication;
import com.firebug.cocoapay.utils.ApplicationSettings;

public final class ProductListFragment extends Fragment implements ValueUpdateInterface  {
	private static final String KEY_CONTENT = "ProductListFragment";
	public boolean isTrue=false;
	private PopupWindow popupWindow;;
	private TextView productEditTextView;
	public Dialog dialog;
	public TextView productChargeTv;
	public CocoaPayApplication application;
	public String val;
	public ProductListFragment(String val) {
		this.val=val;
	}
	/*public static ProductListFragment newInstance(String content) {
		ProductListFragment fragment = new ProductListFragment(va);
		Log.e("Values::::::::::::::::::::::::::::::::::Charge Static", ""+CustomChargeFragment.chargeValue);
		Log.e("Values::::::::::::::::::::::::::::::::::::", ""+ApplicationSettings.getPref(AppConstants.CHARGE_PRICE, ""));
		Log.e("Values::::::::::::::::::::::::::::::::::::", ""+ApplicationSettings.getPref(AppConstants.CHARGE_PRICE, ""));
		Log.e("Values::::::::::::::::::::::::::::::::::::", ""+ApplicationSettings.getPref(AppConstants.CHARGE_PRICE, ""));
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 20; i++) {
			builder.append(content).append(" ");
		}
		builder.deleteCharAt(builder.length() - 1);
		fragment.mContent = builder.toString();

		return fragment;
	}
*/
	private String mContent = "???";
	private void toggleView() {
		// TODO Auto-generated method stub
		if(!isTrue)
		{
			showFilterPopup(getActivity(),productEditTextView);
			isTrue=true;
		}
		else
		{
			isTrue=false;
			if(popupWindow!=null && popupWindow.isShowing())
			{
				popupWindow.dismiss();
			}
		}
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.e("Oncreate", ""+CustomChargeFragment.chargeValue);
		if ((savedInstanceState != null)
				&& savedInstanceState.containsKey(KEY_CONTENT)) {
			mContent = savedInstanceState.getString(KEY_CONTENT);
			application=CocoaPayApplication.getAppInstance();
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.e("onCreateView", ""+CustomChargeFragment.chargeValue);
		View view = inflater.inflate(R.layout.p_list, container, false);
		Log.e("Values::::::::::::::::::::::::::::::::::Charge Static", ""+CustomChargeFragment.chargeValue);
		application=CocoaPayApplication.getAppInstance();
		LinearLayout layout=(LinearLayout) view.findViewById(R.id.alp);
		ListView listView = (ListView) view.findViewById(R.id.mListView);
		ImageView filterIcon = (ImageView) view.findViewById(R.id.filter_iv);
		productEditTextView=(TextView) view.findViewById(R.id.edit_product_tv);
		productChargeTv=(TextView) view.findViewById(R.id.product_charge_tv);
		
		productChargeTv.setText(val);
		Log.e("Values:::::application.getCustomChargeValue();:::::::::::::::::::::::::::::::", ""+application.getCustomChargeValue());
		Log.e("Values::::::::::::::::::::::::::::::::::::", ""+ApplicationSettings.getPref(AppConstants.CHARGE_PRICE, ""));
		Log.e("Values::::::::::::::::::::::::::::::::::::", ""+ApplicationSettings.getPref(AppConstants.CHARGE_PRICE, ""));
		Log.e("Values::::::::::::::::::::::::::::::::::::", ""+ApplicationSettings.getPref(AppConstants.CHARGE_PRICE, ""));
		
	
		LinearLayout chargeLayout = (LinearLayout) view
				.findViewById(R.id.charge_layout);

		chargeLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				paymentPopup();
			}
		});
		
		
		filterIcon.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v)
			{
				toggleView();
			}
		});
		productEditTextView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				showEditPopup();
			}
		});
		ArrayList<ProductListItems> arrayList = new ArrayList<ProductListItems>();
		for (int i = 0; i < 100; i++) {
			ProductListItems items = new ProductListItems();
			items.setProductImage("R.drawable.ic_launcher");
			items.setProductName("Pencil");
			items.setProductPrice("$ 100");
			arrayList.add(items);

		}
		ProductListAdapter adapter = new ProductListAdapter(getActivity(),
				R.layout.product_list_item, arrayList);
		listView.setAdapter(adapter);
		return view;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString(KEY_CONTENT, mContent);
	}
	
	// The method that displays the popup.
		private void showFilterPopup(final Activity context, TextView tv) {
			LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View popupView = layoutInflater.inflate(R.layout.ep, null);
			popupWindow = new PopupWindow(popupView,
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			popupWindow.showAsDropDown(tv, 50, -15);
		}
		
		// The method that displays the popup.
				private void showEditPopup() {
				/*	LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
					View popupView = layoutInflater.inflate(R.layout.product_list_edit, null);
					popupWindow = new PopupWindow(popupView,
							LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
					//popupWindow.showAsDropDown(tv, 50, 10);
					ListView produoctEditListView=(ListView) popupView.findViewById(R.id.product_edit_listview);
					Button doneButton=(Button) popupView.findViewById(R.id.done_button);
					doneButton.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							popupWindow.dismiss();
						}
					});*/	final Dialog dialog = new Dialog(getActivity());
					dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
					dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
					dialog.setCancelable(true);
					
					dialog.setContentView(R.layout.product_list_edit);
					dialog.show();
					ListView produoctEditListView=(ListView) dialog.findViewById(R.id.product_edit_listview);
					Button doneButton=(Button) dialog.findViewById(R.id.done_button);
					doneButton.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							dialog.cancel();
						}
					});
					ArrayList<ProductListItems> arrayList = new ArrayList<ProductListItems>();
					for (int i = 0; i < 100; i++) {
						ProductListItems items = new ProductListItems();
						items.setProductImage("R.drawable.ic_launcher");
						items.setProductName("Pencil");
						items.setProductPrice("$ 100");
						arrayList.add(items);

					}
					ProductListAdapter adapter = new ProductListAdapter(getActivity(),
							R.layout.product_list_item, arrayList);
					produoctEditListView.setAdapter(adapter);
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

				public void paymentPopupCart() 
				{
					
					if (dialog != null && dialog.isShowing()) 
					{
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
								ImageView backImageView = (ImageView) dialogCart
										.findViewById(R.id.back_btn);
						
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
				public void onResume() {
					super.onResume();
					productChargeTv.setText(ApplicationSettings.getPref(AppConstants.CHARGE_PRICE, ""));
				}
				@Override
				public void fragmentBecameVisible() {
					if(productChargeTv!=null)
					{
						Log.e("AHHHHHHHHHHHHHHHHHHHHHHHHHHH", "Cumminggggggggggggggggg");
						productChargeTv.setText(ApplicationSettings.getPref(AppConstants.CHARGE_PRICE, ""));
					}
				
				}
}

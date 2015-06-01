package com.firebug.cocoapay;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firebug.cocoapay.datainfo.ProductListItems;

public class MenuFragment extends Fragment implements OnClickListener {
	private ListView todayListView;
	//private ListView otherListView;
	//private TextView dailyTotalTextView;
	private TextView viewAllTextView;
	private Button employeeButton;
	private Button addProductButton;
	private Button settingsButton;
	private Button supportButton;

	@SuppressLint("InlinedApi")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.menuview, container, false);
		alignTitle(view);
		todayListView = (ListView) view.findViewById(R.id.todaylist);
		/*otherListView = (ListView) view.findViewById(R.id.detailslist);
		dailyTotalTextView = (TextView) view
				.findViewById(R.id.daily_total_price);*/
		viewAllTextView = (TextView) view.findViewById(R.id.view_all);

		employeeButton = (Button) view.findViewById(R.id.emp_btn);
		addProductButton = (Button) view.findViewById(R.id.addproduct_btn);
		settingsButton = (Button) view.findViewById(R.id.settings_btn);
		supportButton = (Button) view.findViewById(R.id.support_btn);

		employeeButton.setOnClickListener(this);
		addProductButton.setOnClickListener(this);
		settingsButton.setOnClickListener(this);
		supportButton.setOnClickListener(this);

		viewAllTextView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getActivity(), AllSalesActivity.class));
			}
		});

		ArrayList<ProductListItems> arrayList = new ArrayList<ProductListItems>();
		for (int i = 0; i < 100; i++) {
			ProductListItems items = new ProductListItems();
			items.setProductImage("R.drawable.ic_launcher");
			items.setProductName("Rs 350 -3 Products");
			items.setProductPrice("12:16 PM");
			arrayList.add(items);

		}
		ProductListAdapter adapter = new ProductListAdapter(getActivity(),
				R.layout.slider_list_item, arrayList);
		todayListView.setAdapter(adapter);

		todayListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getActivity(),
						TransactionDetailsActivity.class));
				getActivity().closeOptionsMenu();
			}
		});

		todayListView.setOnTouchListener(new ListView.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				int action = event.getAction();
				switch (action) {
				case MotionEvent.ACTION_DOWN:
					// Disallow ScrollView to intercept touch events.
					v.getParent().requestDisallowInterceptTouchEvent(true);
					break;

				case MotionEvent.ACTION_UP:
					// Allow ScrollView to intercept touch events.
					v.getParent().requestDisallowInterceptTouchEvent(false);
					break;
				}

				// Handle ListView touch events.
				v.onTouchEvent(event);
				return true;
			}
		});

		ArrayList<ProductListItems> arrayList1 = new ArrayList<ProductListItems>();
		for (int i = 0; i < 100; i++) {
			ProductListItems items = new ProductListItems();
			items.setProductImage("R.drawable.ic_launcher");
			items.setProductName("Rs 350 -3 Products");
			items.setProductPrice("12:16 PM");
			arrayList1.add(items);

		}
	/*	ProductListAdapter adapter1 = new ProductListAdapter(getActivity(),
				R.layout.slider_list_item, arrayList1);
		otherListView.setAdapter(adapter1);

		otherListView.setOnTouchListener(new ListView.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				int action = event.getAction();
				switch (action) {
				case MotionEvent.ACTION_DOWN:
					// Disallow ScrollView to intercept touch events.
					v.getParent().requestDisallowInterceptTouchEvent(true);
					break;

				case MotionEvent.ACTION_UP:
					// Allow ScrollView to intercept touch events.
					v.getParent().requestDisallowInterceptTouchEvent(false);
					break;
				}

				// Handle ListView touch events.
				v.onTouchEvent(event);
				return true;
			}
		});*/

		return view;
	}

	/*
	 * @Override public void onActivityCreated(Bundle savedInstanceState) {
	 * super.onActivityCreated(savedInstanceState); setListAdapter(new
	 * ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,
	 * new String[] { " First", " Second", " Third", " Fourth", " Fifth",
	 * " Sixth"})); getListView().setCacheColorHint(0); }
	 * 
	 * @Override public void onListItemClick(ListView l, View v, int position,
	 * long id) { super.onListItemClick(l, v, position, id);
	 * ((MenuActivity)getActivity()).getSlideoutHelper().close(); }
	 */

	public void alignTitle(View view) {
		int width = (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 60, getResources()
						.getDisplayMetrics());
		int screenwidth = getActivity().getWindowManager().getDefaultDisplay()
				.getWidth();
		int t = screenwidth - width;
		RelativeLayout layout = (RelativeLayout) view.findViewById(R.id.ll);
		layout.setLayoutParams(new LayoutParams(t, LayoutParams.MATCH_PARENT));
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.addproduct_btn:
			getActivity().closeContextMenu();
			getActivity().closeOptionsMenu();
			startActivity(new Intent(getActivity(), AddProductActivity.class));
			/*
			 * AddProductFragment AddProductFragment = new AddProductFragment();
			 * FragmentManager fragmentManager = getFragmentManager();
			 * FragmentTransaction fragmentTransaction =
			 * fragmentManager.beginTransaction();
			 * fragmentTransaction.replace(android.R.id.content,
			 * AddProductFragment); fragmentTransaction.commit();
			 */
			break;

		case R.id.support_btn:
			getActivity().closeContextMenu();
			getActivity().closeOptionsMenu();
			startActivity(new Intent(getActivity(), SupportActivity.class));
			/*
			 * AddProductFragment AddProductFragment = new AddProductFragment();
			 * FragmentManager fragmentManager = getFragmentManager();
			 * FragmentTransaction fragmentTransaction =
			 * fragmentManager.beginTransaction();
			 * fragmentTransaction.replace(android.R.id.content,
			 * AddProductFragment); fragmentTransaction.commit();
			 */
			break;

		case R.id.settings_btn:
			getActivity().closeContextMenu();
			getActivity().closeOptionsMenu();
			startActivity(new Intent(getActivity(), SettingsActivity.class));
			break;		
			
		case R.id.emp_btn:
			getActivity().closeContextMenu();
			getActivity().closeOptionsMenu();
			startActivity(new Intent(getActivity(), EmployeeActivity.class));
			break;		
			
		default:
			break;
		}
	}

	@Override
	public void onDestroyOptionsMenu() {
		// TODO Auto-generated method stub
		super.onDestroyOptionsMenu();

	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

}

package com.firebug.cocoapay.adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.firebug.cocoapay.R;
import com.firebug.cocoapay.datainfo.EmployeeInfo;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class EmployeeAdapter extends ArrayAdapter<EmployeeInfo> {
	Context context;
	int layoutResourceId;
	private List<EmployeeInfo> employeeList = null;
	private ArrayList<EmployeeInfo> arraylist;
	@SuppressWarnings("unused")
	private ImageLoader imageLoader;
	DisplayImageOptions options;

	@SuppressWarnings("deprecation")
	public EmployeeAdapter(Context context, int layoutResourceId,
			ArrayList<EmployeeInfo> saleList) {
		super(context, layoutResourceId, saleList);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.employeeList = saleList;
		this.arraylist = new ArrayList<EmployeeInfo>();
		this.arraylist.addAll(saleList);
		imageLoader = ImageLoader.getInstance();
		options = new DisplayImageOptions.Builder().cacheInMemory(true)
				.cacheOnDisc(true).resetViewBeforeLoading(true)
				.showImageForEmptyUri(R.drawable.ic_launcher)
				.showImageOnFail(R.drawable.ic_launcher)
				.showImageOnLoading(R.drawable.ic_launcher).build();

	}

	/*
	 * @Override public ProductListItems getItem(int position) { return
	 * productList.get(position); }
	 * 
	 * @Override public long getItemId(int position) { return position; }
	 */
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View row = convertView;
		EmployeeHolder holder = null;

		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);

			holder = new EmployeeHolder();
			holder.employeeName = (TextView) row
					.findViewById(R.id.emp_name);
			row.setTag(holder);

		} else {
			holder = (EmployeeHolder) row.getTag();
		}

		final EmployeeInfo employeeInfo = employeeList.get(position);
		holder.employeeName.setText(employeeInfo.getEmployeeName());
		return row;
	}

	static class EmployeeHolder {
		TextView employeeName;
	}

}
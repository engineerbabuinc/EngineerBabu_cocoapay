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
import com.firebug.cocoapay.datainfo.AllSalesInfo;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class AllSalesAdapter extends ArrayAdapter<AllSalesInfo> {
	Context context;
	int layoutResourceId;
	private List<AllSalesInfo> saleList = null;
	private ArrayList<AllSalesInfo> arraylist;
	@SuppressWarnings("unused")
	private ImageLoader imageLoader;
	DisplayImageOptions options;

	@SuppressWarnings("deprecation")
	public AllSalesAdapter(Context context, int layoutResourceId,
			ArrayList<AllSalesInfo> saleList) {
		super(context, layoutResourceId, saleList);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.saleList = saleList;
		this.arraylist = new ArrayList<AllSalesInfo>();
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
		AllSalesHolder holder = null;

		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);

			holder = new AllSalesHolder();
			holder.saleDate = (TextView) row.findViewById(R.id.sale_date);
			holder.saleTime = (TextView) row.findViewById(R.id.sale_time);
			holder.saleProduct = (TextView) row.findViewById(R.id.sale_product);
			row.setTag(holder);

		} else {
			holder = (AllSalesHolder) row.getTag();
		}

		final AllSalesInfo salesInfo = saleList.get(position);

		holder.saleDate.setText(salesInfo.getSaleDate());
		holder.saleTime.setText(salesInfo.getSaleTime());
		holder.saleProduct.setText(salesInfo.getSaleProduct());

		return row;
	}

	static class AllSalesHolder {
		TextView saleDate;
		TextView saleTime;
		TextView saleProduct;
	}

}
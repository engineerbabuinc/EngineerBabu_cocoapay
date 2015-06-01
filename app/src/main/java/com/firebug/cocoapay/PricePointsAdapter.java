package com.firebug.cocoapay;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.firebug.cocoapay.datainfo.PricePointsInfo;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class PricePointsAdapter extends ArrayAdapter<PricePointsInfo> {
	Context context;
	int layoutResourceId;
	private List<PricePointsInfo> productList = null;
	private ArrayList<PricePointsInfo> arraylist;
	private ImageLoader imageLoader;
	DisplayImageOptions options;

	public PricePointsAdapter(Context context, int layoutResourceId,
			ArrayList<PricePointsInfo> productList) {
		super(context, layoutResourceId, productList);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.productList = productList;
		this.arraylist = new ArrayList<PricePointsInfo>();
		this.arraylist.addAll(productList);
		imageLoader = ImageLoader.getInstance();
		options = new DisplayImageOptions.Builder().cacheInMemory(true)
				.cacheOnDisc(true).resetViewBeforeLoading(true)
				.showImageForEmptyUri(R.drawable.ic_launcher)
				.showImageOnFail(R.drawable.ic_launcher)
				.showImageOnLoading(R.drawable.ic_launcher).build();

	}

/*	@Override
	public ProductListItems getItem(int position) {
		return productList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
*/
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View row = convertView;
		ProductHolder holder = null;

		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);

			holder = new ProductHolder();
			holder.txtProductName = (TextView) row
					.findViewById(R.id.pp_name);

			holder.txtProductPrice = (TextView) row
					.findViewById(R.id.pp_price);


			row.setTag(holder);

		} else {
			holder = (ProductHolder) row.getTag();
		}

		final PricePointsInfo ProductListItems = productList.get(position);

		holder.txtProductName.setText(ProductListItems.getProductName());
		if (ProductListItems.getProductPrice() != null
				&& !ProductListItems.getProductPrice().equalsIgnoreCase("")
				&& !ProductListItems.getProductPrice().equalsIgnoreCase("null"))
			holder.txtProductPrice.setText(ProductListItems.getProductPrice());
		else
			holder.txtProductPrice.setText("");
		

		return row;
	}

	static class ProductHolder {
		TextView txtProductName;
		TextView txtProductPrice;
	}

}
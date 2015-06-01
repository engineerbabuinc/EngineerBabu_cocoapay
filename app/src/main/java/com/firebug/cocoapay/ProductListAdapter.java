package com.firebug.cocoapay;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebug.cocoapay.datainfo.ProductListItems;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ProductListAdapter extends ArrayAdapter<ProductListItems> {
	Context context;
	int layoutResourceId;
	private List<ProductListItems> productList = null;
	private ArrayList<ProductListItems> arraylist;
	private ImageLoader imageLoader;
	DisplayImageOptions options;

	public ProductListAdapter(Context context, int layoutResourceId,
			ArrayList<ProductListItems> productList) {
		super(context, layoutResourceId, productList);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.productList = productList;
		this.arraylist = new ArrayList<ProductListItems>();
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
					.findViewById(R.id.product_name);

			holder.txtProductPrice = (TextView) row
					.findViewById(R.id.product_price);

			holder.productImageView = (ImageView) row
					.findViewById(R.id.product_image);

			row.setTag(holder);

		} else {
			holder = (ProductHolder) row.getTag();
		}

		final ProductListItems ProductListItems = productList.get(position);

		holder.txtProductName.setText(ProductListItems.getProductName());
		if (ProductListItems.getProductPrice() != null
				&& !ProductListItems.getProductPrice().equalsIgnoreCase("")
				&& !ProductListItems.getProductPrice().equalsIgnoreCase("null"))
			holder.txtProductPrice.setText(ProductListItems.getProductPrice());
		else
			holder.txtProductPrice.setText("");

		// holder.callButton.setTag(ProductListItems);
		if (ProductListItems.getProductImage() != null
				&& !ProductListItems.getProductImage().equalsIgnoreCase("")
				&& !ProductListItems.getProductImage().equalsIgnoreCase(null)
				&& !ProductListItems.getProductImage().equalsIgnoreCase("null"))
			imageLoader.displayImage(ProductListItems.getProductImage(),
					holder.productImageView, options);
		else
			holder.productImageView.setImageResource(R.drawable.ic_launcher);
		return row;
	}

	static class ProductHolder {
		TextView txtProductName;
		TextView txtProductPrice;
		ImageView productImageView;
	}
	

}
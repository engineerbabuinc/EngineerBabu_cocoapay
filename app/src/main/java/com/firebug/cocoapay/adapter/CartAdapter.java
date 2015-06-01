package com.firebug.cocoapay.adapter;

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

import com.firebug.cocoapay.R;
import com.firebug.cocoapay.datainfo.CartInfo;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class CartAdapter extends ArrayAdapter<CartInfo> {
	Context context;
	int layoutResourceId;
	private List<CartInfo> cartList = null;
	private ArrayList<CartInfo> arraylist;
	@SuppressWarnings("unused")
	private ImageLoader imageLoader;
	DisplayImageOptions options;

	@SuppressWarnings("deprecation")
	public CartAdapter(Context context, int layoutResourceId,
			ArrayList<CartInfo> saleList) {
		super(context, layoutResourceId, saleList);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.cartList = saleList;
		this.arraylist = new ArrayList<CartInfo>();
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
		AllCartHolder holder = null;

		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);

			holder = new AllCartHolder();
			holder.cartQuantity = (TextView) row
					.findViewById(R.id.cart_item_qty);
			holder.cartTotal = (TextView) row
					.findViewById(R.id.cart_item_total);
			holder.cartName = (TextView) row.findViewById(R.id.cart_item_name);
			row.setTag(holder);

		} else {
			holder = (AllCartHolder) row.getTag();
		}

		final CartInfo cartInfo = cartList.get(position);

		holder.cartQuantity.setText(cartInfo.getCartQuantity());
		holder.cartTotal.setText(cartInfo.getCartPrice());
		holder.cartName.setText(cartInfo.getCartName());

		return row;
	}

	static class AllCartHolder {
		TextView cartQuantity;
		TextView cartTotal;
		TextView cartName;
		ImageView cartImage;
	}

}
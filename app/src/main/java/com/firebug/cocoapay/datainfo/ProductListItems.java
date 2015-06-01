package com.firebug.cocoapay.datainfo;

public class ProductListItems {

	private String productName;
	private String productImage;
	private String productPrice;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}
	

	public String getIndicator(){
		return Character.toString(productName.charAt(0)) + Character.toString(Character.toLowerCase(productName.charAt(0)));
	}

}

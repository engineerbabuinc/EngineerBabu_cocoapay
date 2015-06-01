package com.firebug.cocoapay.tableutils;

public class TableUtils extends com.firebug.cocoapay.dbutils.TableUtils {

	// Table Orders
	public static final String KEY_ORDER_ID = "OrderID";
	public static final String KEY_ORDER_NAME = "OrderName";
	public static final String KEY_CATEGORY_ID = "CategoryID";
	public static final String KEY_RESTAURANT_ID = "RestaurantID";
	public static final String KEY_MENU_ID = "MenuID";
	public static final String KEY_ITEM_QUANTITY = "ItemQty";
	public static final String KEY_SUBMENU_ID = "SubMenuID";
	public static final String KEY_ITEM_ID = "ItemID";
	public static final String KEY_ITEM_NAME = "ItemName";
	public static final String KEY_ITEM_PRICE = "ItemPrice";
	public static final String KEY_TOTAL_PRICE = "TotalPrice";
	public static final String KEY_TOTAL_QUANTITY = "TotalQuantity";

	// Table Great Value Packs
	public static final String KEY_GVP_ORDER_ID = "GVPOrderID";
	public static final String KEY_GVP_NAME = "GVPName";
	public static final String KEY_GVP_RESTAURANT_ID = "GVPRestaurantID";
	public static final String KEY_GVP_ITEM_ID = "GVPItemID";
	public static final String KEY_GVP_ITEM_NAME = "GVPItemName";
	public static final String KEY_GVP_ITEM_PRICE = "GVPItemPrice";
	public static final String KEY_GVP_TOTAL_RICE_COUNT = "GVPRiceCount";
	public static final String KEY_GVP_ITEM_PICKUP_PRICE = "GVPItemPickupPrice";
	public static final String KEY_GVP_ITEM_DELIVERY_PRICE = "GVPItemDeliveryPrice";
	public static final String KEY_GVP_TOTAL_PRICE_DELIVERY = "GVPTotalDeliveryPrice";
	public static final String KEY_GVP_TOTAL_PRICE_PICKUP = "GVPTotalPickupPrice";
	public static final String KEY_GVP_ITEM_STATUS = "GVPitemStatus";
	public static final String KEY_GVP_RICE_TYPE = "GVPRiceType";

	public static final String KEY_GVP_CURRENT_TIME = "GVPCurrentTime";
	public static final String KEY_GVP_EXTRA_PRICE = "GVPExtraPrice";
	
	public static final String KEY_GVP_EXTRA_CHARGES = "GVPExtraCharges";

	// Table Names

	public static final String TABLE_ORDERS = "Orders";
	public static final String TABLE_GVP_ORDERS = "GvpOrders";
	public static final String TABLE_GVP = "Gvp";

	public static String[] getTableCreationSQLs() {
		return new String[] {
				getTableCreationSQL(TABLE_ORDERS, getOrderColumns()),
				getTableCreationSQL(TABLE_GVP_ORDERS, getGVPOrdersColumns()),
				getTableCreationSQL(TABLE_GVP, getGVPColumns()) };
	}

	public static String[] getOrderColumns() {
		return new String[] {

		TableUtils.KEY_ORDER_ID + " INTEGER PRIMARY KEY, ",
				KEY_ORDER_NAME + " text, ", KEY_CATEGORY_ID + " text, ",
				KEY_RESTAURANT_ID + " text, ", KEY_MENU_ID + " text, ",
				KEY_SUBMENU_ID + " text, ", KEY_ITEM_ID + " text, ",
				KEY_ITEM_NAME + " text, ", KEY_ITEM_PRICE + " text, ",
				KEY_ITEM_QUANTITY + " text, ", KEY_TOTAL_PRICE + " text, ",
				KEY_TOTAL_QUANTITY + " text " };
	}

	public static String[] getGVPOrdersColumns() {
		return new String[] {

		TableUtils.KEY_GVP_ORDER_ID + " INTEGER PRIMARY KEY, ",
				KEY_GVP_RESTAURANT_ID + " text, ", KEY_GVP_ITEM_ID + " text, ",
				KEY_GVP_NAME + " text, ", KEY_GVP_ITEM_NAME + " text, ",
				KEY_GVP_ITEM_PRICE + " text ,",
				KEY_GVP_ITEM_STATUS + " text ,",
				KEY_GVP_CURRENT_TIME + " text ,",
				KEY_GVP_EXTRA_CHARGES + " text " };
	}

	public static String[] getGVPColumns() {
		return new String[] {

		TableUtils.KEY_GVP_ORDER_ID + " INTEGER PRIMARY KEY, ",
				KEY_GVP_NAME + " text, ", KEY_GVP_RESTAURANT_ID + " text, ",
				KEY_GVP_ITEM_PICKUP_PRICE + " text, ",
				KEY_GVP_ITEM_DELIVERY_PRICE + " text, ",
				KEY_GVP_TOTAL_PRICE_DELIVERY + " text ,",
				KEY_GVP_TOTAL_PRICE_PICKUP + " text ,",
				KEY_GVP_TOTAL_RICE_COUNT + " text ,",
				KEY_GVP_CURRENT_TIME + " text ,",
				KEY_GVP_RICE_TYPE + " text ,",
				KEY_GVP_EXTRA_PRICE + " text ,",
				KEY_GVP_EXTRA_CHARGES + " text " };
	}
}

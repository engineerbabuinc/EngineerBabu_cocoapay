package com.firebug.cocoapay.tableutils;

import android.content.Context;
import com.firebug.cocoapay.dbutils.DBMangaer;

public class DBManager extends DBMangaer {
	public static synchronized void initializeDB(Context context, String dbName) {
		if (mInstance == null)
			mInstance = new DBManager();
		mInstance.createDataBase(context, dbName);
	}

	@Override
	public String[] getTableCreationSqls() {
		return TableUtils.getTableCreationSQLs();
	}

}

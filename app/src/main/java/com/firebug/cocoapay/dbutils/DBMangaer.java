package com.firebug.cocoapay.dbutils;



import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.GridView;


public abstract class DBMangaer 
{
	private String mDataBaseName;
	private sqliteOpenHelperClass mDbHelper;
	private SQLiteDatabase mDb;
	private static final int DATABASE_VERSION = 1;
	
	protected static DBMangaer mInstance;
		
	public abstract String[] getTableCreationSqls();
	
	public static DBMangaer getIntance() throws NullPointerException
	{
		if( mInstance == null)
			throw new NullPointerException( " instance of dbmanager is null");
		
		return mInstance;
	}
	
	
	public void createDataBase( Context context, String dbName)
	{
		this.mDataBaseName = dbName;
		mDbHelper = new sqliteOpenHelperClass( context, dbName, null, DATABASE_VERSION);
		try
		{
			mDb = mDbHelper.getWritableDatabase();
			
		}
		catch ( SQLiteException sqe)
		{
			mDb = mDbHelper.getReadableDatabase();
			
		}

	}
	
	
	class sqliteOpenHelperClass extends SQLiteOpenHelper
	{

		public sqliteOpenHelperClass(Context context, String name, CursorFactory factory, int version) 
		{
			super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase db) 
		{
			String[] tableCreationStrings = getTableCreationSqls();
			for (int i = 0; i < tableCreationStrings.length; i++)
			{
				String command = tableCreationStrings[i];
				db.execSQL(command);
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
		{
		}
		
	}
	
	public synchronized boolean dropAll( Context context)
	{
		mInstance.close();
		context.deleteDatabase( mDataBaseName);
		mInstance.createDataBase( context, mDataBaseName);
		return true;
	}

	public SQLiteDatabase getDB()
	{
		return mDb;
	}
	public void updateDatabase(int oldVersion, int newVersion)
	{
		if(  mInstance != null && mDbHelper != null)
		{
			mDbHelper.onUpgrade(mDb, oldVersion, newVersion);
		}
	}

	public void close()
	{
		try
		{
    		mDbHelper.close();
    		mDb.close();
    		mDbHelper = null;
    		mDb = null;
    	}
		catch ( SQLiteException sqe)
		{
		}
	}
	
	public void executeSQL(String sqlStatement) throws SQLException
	{
		try
		{
			mDb.execSQL(sqlStatement);
		}
		catch ( SQLiteException sqe)
		{
		}
	}

	public long update(String tableName, ContentValues newValue, String whereClause)
	{
		try
		{
			return mDb.update(tableName, newValue, whereClause, null);
		}
		catch ( SQLiteException sqe)
		{
		}
		return -1;
	}

	public long insert(String tableName, ContentValues newValue)
	{
		try
		{
			return mDb.insert(tableName, null, newValue);
		}
		catch ( SQLiteException sqe)
		{
		}
		return -1;
	}

	public int delete(String tableName, String whereClause)
	{
		try
		{
    		return mDb.delete(tableName, whereClause, null);
    	}
		catch ( SQLiteException sqe)
		{
		}
		return -1;
	}
	
	public Cursor query(String tableName)
    {
		try
		{
    		return query(true, tableName, null, null, null, null, null, null, null);
    	}
		catch ( SQLiteException sqe)
		{
		}
		return null;
    }
	
	public Cursor query(boolean distinct, String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having,
			String orderBy, String limit)
	{
		try
		{
			return mDb.query(distinct, table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
		}
		catch ( SQLiteException sqe)
		{
		}
		return null;
	}
	
	public boolean checkifvalueExists(String tableName,String itemID,String itemName)
	{
		Cursor mCursor = mDb.rawQuery("SELECT * FROM " + tableName + " WHERE ItemID=? AND ItemName=?", new String[]{itemID,itemName});

		if (mCursor != null && mCursor.getCount()>0)
		{
		            return true;
		 }
		else
		{
		        return false;
		}
	}
	
	public boolean checkifvalueExists(String tableName,String itemName)
	{
		Cursor mCursor = mDb.rawQuery("SELECT * FROM " + tableName + " WHERE GVPCurrentTime=?", new String[]{itemName});

		if (mCursor != null && mCursor.getCount()>0)
		{
		            return true;
		 }
		else
		{
		        return false;
		}
	}
	//db.execSQL("delete from "+TBL_NAME+" where Google='"+id+"'");
	public boolean checkifvalueExistsGVPORDERS(String tableName,String id,String currenttime)
	{
		//Cursor mCursor = mDb.rawQuery("SELECT * FROM " + tableName + " WHERE GVPItemID=? AND GVPItemName=?", new String[]{itemID,itemName});
		Cursor mCursor = mDb.rawQuery("SELECT * FROM " + tableName + " WHERE GVPItemID=? AND GVPCurrentTime=? ", new String[]{id,currenttime});

		if (mCursor != null && mCursor.getCount()>0)
		{
		            return true;
		 }
		else
		{
		        return false;
		}
	}

	public boolean deleterowGVPORDERS(String tableName,String id,String currenttime)
	{
		//Cursor mCursor = mDb.rawQuery("SELECT * FROM " + tableName + " WHERE GVPItemID=? AND GVPItemName=?", new String[]{itemID,itemName});
		Cursor mCursor = mDb.rawQuery("DELETE FROM " + tableName + " WHERE GVPItemID=? AND GVPCurrentTime=? ", new String[]{id,currenttime});

		if (mCursor != null && mCursor.getCount()>0)
		{
		            return true;
		 }
		else
		{
		        return false;
		}
	}
	
	
	
	public boolean getGVPOrdersfromDB(String tableName,String currenttime)
	{
		//Cursor mCursor = mDb.rawQuery("SELECT * FROM " + tableName + " WHERE GVPItemID=? AND GVPItemName=?", new String[]{itemID,itemName});
		Cursor mCursor = mDb.rawQuery("SELECT * FROM " + tableName + " WHERE GVPCurrentTime=? ", new String[]{currenttime});

		if (mCursor != null && mCursor.getCount()>0)
		{
		            return true;
		 }
		else
		{
		        return false;
		}
	}
public int getCountofTable(String tableName)
{
	Cursor c = mDb.rawQuery("select * from "+tableName,null);
	//Log.i("Number of Records"," :: "+c.getCount());	
	return c.getCount();
}
	/*
	public int insertorthrow(String table, ContentValues contentValues)
	{
		int fail=0;
		int success=1;
		try
		{
			mDb.insertOrThrow( table, null, contentValues);
			return success;
		}
		catch ( SQLException e)
		{
			// TODO Auto-generated catch block
			return fail;
			
		}
		
	}*/
	
}

package com.firebug.cocoapay.dbutils;

// R.T //

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;


public abstract class TableUtils 
{

	
	public static final String	KEY_ID	= "id";	
	
	
	public static String[] getTableCreationSQLs()
	{
		return new String[]
		{
			// R.T //
			/*
			 * here we use the method getTableCreationSQL with table name and columns values.
			 *  example
			 *  
			 *  getTableCreationSql( "AzuyoTable", getAzuyoColumnInfo() );	
			 */
				
		};
	}
	
	public static String[] getAzuyoColumnInfo()
	{
		return	new String[] 
		{ 
			// R.T //
				/*
				 *  how to pass the columns values
				 *   example:
				 *  
 			TableUtils.KEY_ID + "INTEGER PRIMARY KEY, ",
 *   		KEY_TITLE + " text, ", 
			KEY_DESCRIPTION + " text, ",
			KEY_LOCATION + " text, ",
			KEY_DATE + " text, ",
			KEY_DAY + "  INTEGER DEFAULT " + 0 + ", ",
			KEY_MONTH + "  INTEGER DEFAULT " + 0 + ", ",
			KEY_YEAR + "  INTEGER DEFAULT " + 0 

				 */
		};
	}

	public static String getTableCreationSQL( String tableName, String[] columns)
	{
		if( tableName != null && columns != null)
		{
			String tableCreationSQL = "create table if not exists " + tableName + " (";
			for (int i = 0; i < columns.length; i++)
				tableCreationSQL += columns[i];

			tableCreationSQL += ");";
			return tableCreationSQL;
		}
		return null;
	}
	
		public static boolean updateToTable(String key, String value, String tableName,
			ContentValues newValue)
	{
		if (tableName != null)
		{
			String whereClause = keyVal( key, value);
			long n = DBMangaer.getIntance().update( tableName, newValue,
					whereClause);
			if (n <= 0)
				n = insertToTable(tableName, newValue);

			return n > 0;
		}
		return false;
	}


	public static long insertToTable(String tableName, ContentValues newValue)
	{
		return DBMangaer.getIntance().insert(tableName, newValue);
	}

	public static boolean removeFromTable( String key, String value, String tableName)
	{
		String whereClause = keyVal( key, value);
		return DBMangaer.getIntance().delete( tableName, whereClause) > 0;
	}

	public static Cursor query( String tableName, int typeId)
	{
		return query( tableName, typeId, null);
	}

	public static Cursor query( String tableName, int typeId, String[] columns)
	{
		return query( tableName, typeId, columns, null);
	}

	public static Cursor query( String tableName, int typeId, String[] columns, String selection)
	{
		String sortOrder = "date DESC";
		return query( typeId, true, columns, selection, null, null, null, sortOrder, null, tableName);
	}

	public static Cursor query( int typeId, boolean distinct, String[] columns, String selection, String[] selectionArgs, String groupBy, String having,
			String orderBy, String limit, String tableName)
	{
		return DBMangaer.getIntance().query( distinct, tableName, columns,
				selection, selectionArgs, groupBy, having, orderBy, limit);
		
	}
	
	public static String getValueFromTable( String tableName,String assetId, String column,
			Activity activity, String orderBy)
	{
		Cursor cursor = query( tableName,
				new String[] { column }, keyVal(KEY_ID, assetId), orderBy);
		activity.startManagingCursor(cursor);
		if (cursor.getCount() > 0 && cursor.moveToFirst())
		{
			return cursor.getString(cursor.getColumnIndex(column));
		}
		return null;
	}
	
	public static Cursor query(String tableName, String[] columns, String selection, String orderBy)
	{
		if ( tableName != null)
		{
			try
			{
				return DBMangaer.getIntance().query(true, tableName, columns, selection, null, null, null, orderBy, null);
			}
			catch ( Exception e)
			{
			}
		}
		return null;
	}
	
	public static String keyVal(String key, String value)
	{
		return key + "=\"" + value + "\"";
	}

	public static int getCount( String tableName, String whereClause, String orderBy)
	{
		
		return TableUtils.query( tableName, null, whereClause, orderBy).getCount();
	}
}

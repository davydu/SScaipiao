package com.sscaipiao.android.utils;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.sscaipiao.android.model.Property;

public class DataHelper {
	// ��ݿ����
	private static String DB_NAME = "sscaipiao.db";
	// ��ݿ�汾
	private static int DB_VERSION = 1;
	private SQLiteDatabase db;
	private SqliteHelper dbHelper;

	public DataHelper(Context context) {
		dbHelper = new SqliteHelper(context, DB_NAME, null, DB_VERSION);
		db = dbHelper.getWritableDatabase();
	}

	public void Close() {
		db.close();
		dbHelper.close();
	}

	// ��ȡPropertys���е�PropertyID��Access Token��Access Secret�ļ�¼
	public List<Property> GetPropertyList() {
//		SQLiteStatement stmt = db.compileStatement("SELECT * FROM Country WHERE code = ?");
//		stmt.bindString(1, "US");
//		stmt.execute();

		List<Property> PropertyList = new ArrayList<Property>();
		Cursor cursor = db.query(SqliteHelper.TB_NAME, null, null, null, null,
				null, Property.ID + " DESC");
		cursor.moveToFirst();
		while (!cursor.isAfterLast() && (cursor.getString(1) != null)) {
			Property Property = new Property();
			Property.setId(cursor.getLong(0));
			Property.setNeedGuide(cursor.getInt(1));
			Property.setAccount(cursor.getString(2));
			Property.setPassword(cursor.getString(3));
			PropertyList.add(Property);
			cursor.moveToNext();
		}
		cursor.close();
		return PropertyList;
	}

	// �ж�Propertys���е��Ƿ��ĳ��PropertyID�ļ�¼
	public Boolean HaveProperty(String PropertyId) {
		Boolean b = false;
		Cursor cursor = db.query(SqliteHelper.TB_NAME, null, Property.ID
				+ "=?", new String[]{PropertyId}, null, null, null);
		b = cursor.moveToFirst();
		Log.e("HaveProperty", b.toString());
		cursor.close();
		return b;
	}



	// ����Propertys��ļ�¼
	public int UpdateProperty(Property Property) {
		ContentValues values = new ContentValues();
		values.put(Property.ID, Property.getId());
		values.put(Property.NEEDGUIDE, Property.getNeedGuide());
		values.put(Property.PASSWORDE, Property.getPassword());
		values.put(Property.ACCOUNT, Property.getAccount());
		int id = db.update(SqliteHelper.TB_NAME, values, Property.ID + "="
				+ Property.getId(), null);
		Log.e("UpdateProperty", id + "");
		return id;
	}

	// ���Propertys��ļ�¼
	public Long SaveProperty(Property Property) {
		ContentValues values = new ContentValues();
		values.put(Property.ID, Property.getId());
		values.put(Property.NEEDGUIDE, Property.getNeedGuide());
		values.put(Property.PASSWORDE, Property.getPassword());
		values.put(Property.ACCOUNT, Property.getAccount());
		Long uid = db.insert(SqliteHelper.TB_NAME, Property.ID, values);
		Log.e("SaveProperty", uid + "");
		return uid;
	}
	

	// ɾ��Propertys��ļ�¼
	public int DelProperty(Long PropertyId) {
		int id = db.delete(SqliteHelper.TB_NAME,
				Property.ID + "=?", new String[]{PropertyId.toString()});
		Log.e("DelProperty", id + "");
		return id;
	}
	
	public static Property getPropertyByAccount(String propertyAccount,List<Property> propertyList){
		Property property = null;
		int size = propertyList.size();
		for(int i=0;i<size;i++){
			if(propertyAccount.equals(propertyList.get(i).getAccount())){
				property = propertyList.get(i);
				break;
			}
		}
		return property;
	}
	
}
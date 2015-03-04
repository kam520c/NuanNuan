package com.nuannuan.common.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class MoodDatabaseHelper extends SQLiteOpenHelper {

	private static final int VERSION = 1;

	// private Context mContext;

	public MoodDatabaseHelper(Context context, String name,
			CursorFactory factory, int version) {

		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	public MoodDatabaseHelper(Context context, String name) {

		this(context, name, VERSION);
	}

	public MoodDatabaseHelper(Context context, String name, int version) {
		this(context, name, null, version);
	}

	//
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table user(id integer primary key autoincrement,userName varchar(100),userId varchar(100),userCoin varchar(100),userEmails varchar(100))");
		db.execSQL("create table moods(id integer primary key autoincrement,json varchar(1000000))");
		db.execSQL("create table times(id integer primary key autoincrement,times varchar(200))");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
	}

}

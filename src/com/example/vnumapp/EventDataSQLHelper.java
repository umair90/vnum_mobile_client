package com.example.vnumapp;

//import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

/** Helper to the database, manages versions and creation */
public class EventDataSQLHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "events.db";
	private static final int DATABASE_VERSION = 2;

	// Table name
	public static final String TABLE = "PABX";

	// Columns
	public static final String TIME = "time";
	public static final String TITLE = "title";
	public static final String KEY = "KEY";
	public static final String virtExtension = "virtExtension";
	public static final String virtNumber = "virtNumber";
	public static final String realNumber = "realNumber";
	public static final String userName = "userName";
	public static final String userDesig = "userDesig";
	public static final String deptID = "deptID";
	public static final String allowAdminCompanyVm = "allowAdminCompanyVm";
	public static final String allowAdminFeedback = "allowAdminFeedback";
	public static final String allowVM = "allowVM";
	public static final String allowOutdial = "allowOutdial";
	public static final String prefBusyAction = "prefBusyAction";
	public static final String prefBusyNextExtension = "prefBusyNextExtension";
	public static final String prefDndAction = "prefDndAction";
	public static final String prefDndNextExtension = "prefDndNextExtension";
	public static final String prefRejAction = "prefRejAction";
	public static final String prefRejNextExtension = "prefRejNextExtension";
	public static final String status = "status";
	public static final String dt = "dt";
	public static final String login = "login";
	public static final String allowObdMsgRec = "allowObdMsgRec";
	public static final String pwd = "pwd";
	public static final String isOwner = "isOwner";
	public static final String role = "role";
	public static final String sessionID = "sessionID";
	public static final String dndSchedType = "dndSchedType";
	public static final String dndSchedStartMin = "dndSchedStartMin";
	public static final String dndSchedEndMin = "dndSchedEndMin";
	public static final String dndSchedSun = "dndSchedSun";
	public static final String dndSchedMon = "dndSchedMon";
	public static final String dndSchedTue = "dndSchedTue";
	public static final String dndSchedWed = "dndSchedWed";
	public static final String dndSchedThu = "dndSchedThu";
	public static final String dndSchedFri = "dndSchedFri";
	public static final String dndSchedSat = "dndSchedSat";
	public static final String chnlID = "chnlID";
	public static final String asterikHost = "asterikHost";
	public static final String dialinReqStartTime = "dialinReqStartTime";
	public static final String allowIntercom = "allowIntercom";
	public static final String allowFreeIntercom = "allowFreeIntercom";
	public static final String depNameList = "depName";
	public static final String realNumberList = "realNumberList";
	public static final String userNameList = "userNameList";
	public static final String oldPwd = "oldPwd";
	public static String sqlCreateTable = null;

	public EventDataSQLHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// @SuppressLint({ "ParserError", "ParserError" })
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "create table " + TABLE + "( " + BaseColumns._ID
				+ " integer primary key , " + TIME + " integer, " + TITLE
				+ " text not null);";
		Log.d("EventsData", "onCreate: " + sql);

		sqlCreateTable = "CREATE TABLE" + " " + TABLE + "(" + " "
				+ BaseColumns._ID + " " + "INTEGER PRIMARY KEY," + " " + KEY
				+ " " + "INTEGER NOT NULL," + " " + virtExtension + " "
				+ "VARCHAR(5) NOT NULL," + " " + virtNumber + " "
				+ "VARCHAR(15) NOT NULL," + " " + realNumber + " "
				+ "VARCHAR(15) NOT NULL," + " " + userName + " "
				+ "VARCHAR(255)," + " " + userDesig + " " + "VARCHAR(255),"
				+ " " + deptID + " " + "INTEGER," + " " + allowAdminCompanyVm
				+ " " + "INTEGER NOT NULL," + " " + allowAdminFeedback + " "
				+ "INTEGER NOT NULL," + " " + allowVM + " " + " "
				+ "INTEGER NOT NULL," + " " + allowOutdial + " "
				+ "INTEGER NOT NULL," + " " + prefBusyAction + " " + "INTEGER,"
				+ " " + prefBusyNextExtension + " " + "VARCHAR(5)," + " "
				+ prefDndAction + " " + "INTEGER," + " " + prefDndNextExtension
				+ " " + "VARCHAR(5)," + " " + prefRejAction + " " + "INTEGER,"
				+ " " + prefRejNextExtension + " " + "VARCHAR(5)," + " "
				+ status + " " + "INTEGER NOT NULL," + " " + dt + " "
				+ "TIMESTAMP NOT NULL," + " " + login + " "
				+ "VARCHAR(20) NOT NULL," + " " + allowObdMsgRec + " "
				+ "INTEGER NOT NULL," + " " + pwd + " "
				+ "VARCHAR(50) NOT NULL," + " " + isOwner + " "
				+ "INTEGER NOT NULL," + " " + role + " " + "INTEGER NOT NULL,"
				+ " " + sessionID + " " + "LONGTEXT," + " " + dndSchedType
				+ " " + "INTEGER," + " " + dndSchedStartMin + " " + "INTEGER,"
				+ " " + dndSchedEndMin + " " + "INTEGER," + " " + dndSchedSun
				+ " " + "INTEGER," + " " + dndSchedMon + " " + "INTEGER," + " "
				+ dndSchedTue + " " + "INTEGER," + " " + dndSchedWed + " "
				+ "INTEGER," + " " + dndSchedThu + " " + "INTEGER," + " "
				+ dndSchedFri + " " + "INTEGER," + " " + dndSchedSat + " "
				+ "INTEGER," + " " + chnlID + " " + "VARCHAR(50)," + " "
				+ asterikHost + " " + "VARCHAR(25)," + " " + dialinReqStartTime
				+ " " + "TIMESTAMP," + " " + allowIntercom + " "
				+ "INTEGER DEFAULT 0," + " " + allowFreeIntercom + " "
				+ "INTEGER DEFAULT 0);";
		Log.e("Create Table =  ", sqlCreateTable);
		db.execSQL(sqlCreateTable);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (oldVersion >= newVersion)
			return;

		String sql = null;
		final String ALTER_TBL = "ALTER TABLE " + TABLE
				+ " ADD COLUMN oldPwd varchar(50) default yrwiutwcbeiurtwi;";
		if (oldVersion == 1)
			sql = "alter table " + TABLE + " add note text;";
		if (oldVersion == 2)
			sql = "";

		Log.d("EventsData", "onUpgrade	: " + sql);
		if (sql != null)
			db.execSQL(ALTER_TBL);
	}

}
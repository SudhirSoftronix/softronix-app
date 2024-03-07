package in.ranium.bloodbankmanage.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DbDonor extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "donor";

	// Contacts table name
	private static final String TABLE_CONTACTS = "donortable";

	// Contacts Table Columns names
	private static final String KEY_ID = "id";
	private static final String name = "name";
	private static final String bloodgroup = "bloodgroup";
	private static final String mobile = "mobile";
	private static final String address = "address";
	
	public DbDonor(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
				+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +  name + " TEXT, " +  bloodgroup + " TEXT, " + mobile
				+ " TEXT, "  + address + " TEXT " + ")";

		db.execSQL(CREATE_CONTACTS_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed

	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	// Adding new contact
	 public void addContact(Donor message) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();

		values.put(name, message.getName());
		 values.put(bloodgroup, message.getBloodgroup());
		 values.put(mobile, message.getMobile());
		 values.put(address, message.getAddress());
		db.insert(TABLE_CONTACTS, null, values);
		db.close(); // Closing database connection
	}
	public int checkgroup( String uname,String ubloodgroup) {
		int count = 0;
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS + " WHERE "+name +"=? AND "+bloodgroup +"=?";
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, new String[] { uname,ubloodgroup});

		count = cursor.getCount();

		cursor.close();
		db.close();
		return count;
	}


	// Getting All Donor
	public ArrayList<Donor> getindividualuserid() {
		ArrayList<Donor> messageList = new ArrayList<Donor>();

		String selectQuery =" SELECT * FROM " + TABLE_CONTACTS ;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, new String[] { });

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Donor message = new Donor();

				message.setId(cursor.getString(cursor
						.getColumnIndexOrThrow(KEY_ID)));
				message.setName(cursor.getString(cursor
						.getColumnIndexOrThrow(name)));
				message.setBloodgroup(cursor.getString(cursor
						.getColumnIndexOrThrow(bloodgroup)));
				message.setMobile(cursor.getString(cursor
						.getColumnIndexOrThrow(mobile)));
				message.setAddress(cursor.getString(cursor
						.getColumnIndexOrThrow(address)));

				messageList.add(message);
			} while (cursor.moveToNext());
		}
		cursor.close();
		db.close();

		// return contact list
		return messageList;
	}



	// Getting All Donor
	public ArrayList<Donor> getSearchData(String ubloodgroup) {
		ArrayList<Donor> messageList = new ArrayList<Donor>();

		String selectQuery =" SELECT * FROM " + TABLE_CONTACTS + " WHERE "+bloodgroup +"=? ";
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, new String[] { ubloodgroup});

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Donor message = new Donor();

				message.setId(cursor.getString(cursor
						.getColumnIndexOrThrow(KEY_ID)));
				message.setName(cursor.getString(cursor
						.getColumnIndexOrThrow(name)));
				message.setBloodgroup(cursor.getString(cursor
						.getColumnIndexOrThrow(bloodgroup)));
				message.setMobile(cursor.getString(cursor
						.getColumnIndexOrThrow(mobile)));
				message.setAddress(cursor.getString(cursor
						.getColumnIndexOrThrow(address)));
				messageList.add(message);
			} while (cursor.moveToNext());
		}
		cursor.close();
		db.close();

		// return contact list
		return messageList;
	}



	public void deletegroup(String userid) {

		// Select All Query
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_CONTACTS,
				KEY_ID + "=?",
				new String[]{ userid});

		db.close();

	}






}
package in.ranium.bloodbankmanage.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbRegister extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "userReg";

	// Contacts table name
	private static final String TABLE_CONTACTS = "register";

	// Register Table Columns names
	private static final String KEY_ID = "ID";
	private static final String NAME = "NAME";
	private static final String PASSWORD = "PASSWORD";


	
	public DbRegister(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
				+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME
				+ " TEXT, " + PASSWORD + " TEXT" + ")";
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
	 public void addContact(UserRegister message) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(NAME, message.getNAME());
		values.put(PASSWORD, message.getPASSWORD());
		db.insert(TABLE_CONTACTS, null, values);
		db.close(); // Closing database connection
	}
    
    //to check user is registered or not
	public int checkgroup(String username, String password) {
		int count = 0;
		// Select All Query

		String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS + " WHERE "+NAME+"=? AND "+PASSWORD +"=? ";
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, new String[] {username, password});
		count = cursor.getCount();
		cursor.close();
		db.close();
		// return contact list
		return count;
	}

	//used to delete User
	public void deleteuser(String userid) {

		// Select All Query
		System.out.println("title--" + userid);
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_CONTACTS,
				KEY_ID + "=?",
				new String[]{userid});

		db.close();

	}

//used to check login
	public int getname(String username,String password) {
		int name = 0;
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS + " WHERE "
				+ NAME + "=?AND "+ PASSWORD + "=?";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, new String[] { username,password });
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				int fname = cursor.getInt(cursor
						.getColumnIndexOrThrow(KEY_ID));
				name = fname;
				// messageList.add(grp);
			} while (cursor.moveToNext());
		}
		cursor.close();
		db.close();
		return name;
	}


}
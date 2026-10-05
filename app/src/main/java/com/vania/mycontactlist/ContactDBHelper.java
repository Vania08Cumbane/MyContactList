package com.vania.mycontactlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class ContactDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mycontacts.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_CONTACT = "contact";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "contactname";
    private static final String COLUMN_ADDRESS = "streetaddress";
    private static final String COLUMN_PHONE = "phonenumber";
    private static final String COLUMN_EMAIL = "email";

    private static final String CREATE_TABLE_CONTACT =
            "CREATE TABLE " + TABLE_CONTACT + " (" +
                    COLUMN_ID + " integer primary key autoincrement, " +
                    COLUMN_NAME + " text not null, " +
                    COLUMN_ADDRESS + " text, " +
                    COLUMN_PHONE + " text, " +
                    COLUMN_EMAIL + " text);";

    public ContactDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CONTACT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACT);
        onCreate(db);
    }

    public boolean insertContact(Contact c) {
        boolean success = false;
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues initialValues = new ContentValues();

            initialValues.put(COLUMN_NAME, c.getContactName());
            initialValues.put(COLUMN_ADDRESS, c.getStreetAddress());
            initialValues.put(COLUMN_PHONE, c.getPhoneNumber());
            initialValues.put(COLUMN_EMAIL, c.getEmail());

            success = db.insert(TABLE_CONTACT, null, initialValues) > 0;
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

    public ArrayList<Contact> getContacts() {
        ArrayList<Contact> contacts = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CONTACT, null);

        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setContactID(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                contact.setContactName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)));
                contact.setStreetAddress(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ADDRESS)));
                contact.setPhoneNumber(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PHONE)));
                contact.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL)));
                contacts.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return contacts;
    }
}
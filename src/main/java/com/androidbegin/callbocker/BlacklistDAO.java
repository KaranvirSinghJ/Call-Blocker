package com.androidbegin.callbocker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class BlacklistDAO {

    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public BlacklistDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
        open();
    }

    private void open() throws SQLException {

        database = dbHelper.getWritableDatabase();
    }

    public void close() {

        dbHelper.close();
    }

    public Blacklist create(final Blacklist blackList) {

        final ContentValues values = new ContentValues();

        values.put("phone_number", blackList.phoneNumber);

        final long id = database.insert(DatabaseHelper.TABLE_BLACKLIST , null, values);

        blackList.id = id;
        return blackList;
    }

    public void delete(final Blacklist blackList) {

        database.delete(DatabaseHelper.TABLE_BLACKLIST, "phone_number = '" + blackList.phoneNumber + "'", null);
    }

    public List<Blacklist> getAllBlacklist() {

        final List<Blacklist> blacklistNumbers = new ArrayList<Blacklist>();

        final Cursor cursor = database.query(DatabaseHelper.TABLE_BLACKLIST, new String[]{"id","phone_number"}, null, null, null, null, null);

        cursor.moveToFirst();


        while (!cursor.isAfterLast()) {
            final Blacklist number = new Blacklist();

            number.id = cursor.getLong(0);
            number.phoneNumber = cursor.getString(1);

            blacklistNumbers.add(number);

            cursor.moveToNext();
        }
        return blacklistNumbers;
    }
}
package com.example.mahmud.digitedu.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mahmud on 9/4/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "GCM";
    private static final String TABLE_NAME = "newsTable";
    private static final String ID = "_id";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME
                + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, MESSAGE STRING, sqltime STRING)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insertMsg(String msg) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("MESSAGE", msg);
        cv.put("sqltime",new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss").format(new Date()));

        db.insert(TABLE_NAME, null, cv);

        db.close();

    }

    public String getAppCategoryDetail() {

        //final String TABLE_NAME = "name of table";

        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor cursor      = db.rawQuery(selectQuery, null);
        String data      = null;
        int i = 0;

        if (cursor.moveToFirst()) {
            do {
                // get the data into array, or class variable
                data = cursor.getString(1);
                i++;

            } while (cursor.moveToNext());
        }
        cursor.close();
        return data;
    }

    private String[] getMessages() {
        String[] messages = null;


        return messages;

    }

    public Cursor getCursor() {
        String selectQuery = "SELECT  * FROM " + TABLE_NAME  + " ORDER BY "+ ID +" DESC";
        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor cursor      = db.rawQuery(selectQuery, null);
        //cursor.close();

        return  cursor;
    }
}

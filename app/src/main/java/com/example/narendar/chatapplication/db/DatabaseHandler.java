package com.example.narendar.chatapplication.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.narendar.chatapplication.db.tables.MESSAGE_TBL;

/**
 * Created by Naren Wadhwa on 7/15/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String TAG = DatabaseHandler.class.getSimpleName();
    private static final String DB_NAME = "CHAT_DB";
    private static final int dbVersion = 1;
    private SQLiteDatabase dbObject;

    public DatabaseHandler(Context context) {
        super(context, DB_NAME, null, dbVersion);
        dbObject = this.getWritableDatabase();
    }

    public SQLiteDatabase getDB() {
        return dbObject;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "Inside onCreate");
        db.execSQL(MESSAGE_TBL.createTable());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(MESSAGE_TBL.dropTable());
        onCreate(db);
    }

    public void onClose() {
        dbObject.close();
    }
}

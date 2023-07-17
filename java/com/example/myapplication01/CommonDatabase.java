package com.example.myapplication01;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class CommonDatabase {
    private Database dbHelper;
    private SQLiteDatabase db;

    public SQLiteDatabase getSqliteObject(Context context, String db_name) {
        dbHelper = new Database(context, "db_name", null, 1);
        db = dbHelper.getWritableDatabase();
        return db;
    }
}

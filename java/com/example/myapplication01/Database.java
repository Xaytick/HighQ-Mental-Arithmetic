package com.example.myapplication01;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createPersonalInfo);
        db.execSQL(createWrongExercises);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists personalInfo");
        db.execSQL("drop table if exists wrongExercises");
        onCreate(db);
    }
    public static final String createPersonalInfo="create table personalInfo("
            +"id integer primary key autoincrement,"
            +"userName text,"
            +"password text)";

    public static final String createWrongExercises="create table wrongExercises("
            +"exercises text,"
            +"number integer)";

    private Context mContext;

}


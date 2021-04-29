package com.example.sleeptracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class recordsDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "recordsDatabase.sqlite";
    private static final int VERSION = 1;


    public recordsDatabase(Context context)
    {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE records(date TEXT, timeOfSleep TEXT, timeOfWaking TEXT, sleepRating INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL( "DROP TABLE IF EXISTS records");
        onCreate(db);
    }

}

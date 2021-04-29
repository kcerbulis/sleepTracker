package com.example.sleeptracker;

import android.database.sqlite.SQLiteDatabase;

import java.util.AbstractSequentialList;
import java.util.ArrayList;
import java.util.Collection;

public class Records {

    //Collection of database entries
    private static Collection<DatabaseEntry> entries;



    //Methods
    //Add entry to list

    public static void addEntryToList(DatabaseEntry GivenEntry)
    {
        entries.add(GivenEntry);
    }










}

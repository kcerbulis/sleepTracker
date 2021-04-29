package com.example.sleeptracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;



public class DatabaseEntry {


    //Variables
    String date;
    String timeOfSleep;
    String timeOfWaking;
    float sleepRating;
    float lengthOfSleep;
    private Context context;





    //Constructor
    public DatabaseEntry( String GivenDate, String GivenCurrentTime , String  GivenWakingTime , float GivenSleepRating, Context context)
    {

        date = GivenDate;
        timeOfSleep = GivenCurrentTime;
        timeOfWaking = GivenWakingTime;
        sleepRating = GivenSleepRating;
        this.context = context;

    }

    public DatabaseEntry (Context context)
    {
        this.context = context;
    }

    //Getters and setters
    public String  getDate(){
        return date;
    }


    public String getTimeOfSleep(){
        return timeOfSleep;
    }


    public String getTimeOfWaking(){
        return timeOfWaking;
    }


    public float getSleepRating(){
        return sleepRating;
    }

}

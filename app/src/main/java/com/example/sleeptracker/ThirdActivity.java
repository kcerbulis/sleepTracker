package com.example.sleeptracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ThirdActivity extends AppCompatActivity {

    //Values to send into DB class
    int HourOfWaking = userDataInputActivity.getWakeHour();
    int MinOfWaking = userDataInputActivity.getWakeMinute();
    int NumOfStars = userDataInputActivity.getStarRating();
    recordsDatabase recordsDB;











    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Records records = new Records();
        recordsDB = new recordsDatabase(getApplicationContext());
    }



    //FAB button function to open first view
    public void openFirstView(View view) {


        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

        //get Date
        Calendar calendar = Calendar.getInstance();

        //
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date = dateFormat.format(calendar.getTime());


        //Get time of sleep
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm a");
        String currentTime = timeFormat.format(calendar.getTime());


        //get time of waking from time picker values
        Date wakingTime = userDataInputActivity.getTimeOfWaking();
        String wakingTimeFormatted = timeFormat.format(wakingTime);


        //Get star rating
        final RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        float starRating = ratingBar.getRating();


        DatabaseEntry track = new DatabaseEntry(date,currentTime,wakingTimeFormatted, starRating, this);



        //Inserting into Database


        SQLiteDatabase db = recordsDB.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("date", track.getDate());
        values.put("timeOfSleep", String.valueOf(track.getTimeOfSleep()));
        values.put("timeOfWaking", String.valueOf(track.getTimeOfWaking()));
        values.put("sleepRating", track.getSleepRating());

        long newRecord = db.insert("records", null, values);

        //Records.addEntryToList(track);


    }


}
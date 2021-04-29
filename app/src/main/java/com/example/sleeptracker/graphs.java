package com.example.sleeptracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.TimePicker;

import java.io.Console;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class graphs extends AppCompatActivity {

    recordsDatabase recordsDB;
    SQLiteDatabase db;
    String allRecords ="";
    private TextView recordsList;
    String listOfRecords ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphs);

        recordsDB = new recordsDatabase(getApplicationContext());
        db = recordsDB.getWritableDatabase();

        //Setting up text view
         recordsList = (TextView) findViewById(R.id.recordsList);


        String sql = "SELECT * FROM records";

        Cursor resultSet = db.rawQuery(sql, null);

        List itemRecords = new ArrayList<>();

        //resultSet.moveToFirst();


        while(resultSet.moveToNext()) {

            String date = resultSet.getString(resultSet.getColumnIndex("date"));
            String timeOfSleeping = resultSet.getString(resultSet.getColumnIndex("timeOfSleep"));
            String timeOfWaking = resultSet.getString(resultSet.getColumnIndex("timeOfWaking"));
            Float sleepRating = resultSet.getFloat(resultSet.getColumnIndex("sleepRating"));

            SimpleDateFormat format = new SimpleDateFormat("HH:mm");

            //Do some thing with data

            String finalRecord = "Date: " + date + "," + System.getProperty("line.separator") + "Time Of Sleeping: " + timeOfSleeping  + ","+ System.getProperty("line.separator")  + "Time Of Waking: " + timeOfWaking+ "," + System.getProperty("line.separator")+ "Rating: " + sleepRating.toString();

            itemRecords.add(finalRecord);
        }

        for(Object s : itemRecords)
        {
            allRecords = allRecords + s + System.getProperty("line.separator") + System.getProperty("line.separator") ;
        }

        recordsList.setText(allRecords);




    }

}
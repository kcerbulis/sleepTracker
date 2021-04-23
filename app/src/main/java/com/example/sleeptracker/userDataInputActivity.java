package com.example.sleeptracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class userDataInputActivity extends AppCompatActivity {
    private TimePicker timePicker1;
    private TextView time;
    private TextView curTimeText, hourTxt, minTxt, timeSleeping, remCyclesCount;
    private Calendar calendar;
    private String format = "";

    int hourSleep;
    int minSleep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data_input);

        timePicker1 = (TimePicker) findViewById(R.id.timePicker1);
        time = (TextView) findViewById(R.id.textView1);
        calendar = Calendar.getInstance();

        //Gets selected time from time selector
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);
        showTime(hour, min);

        //Selects specific TextViews
        curTimeText = (TextView) findViewById(R.id.curTime);
        timeSleeping =  (TextView) findViewById(R.id.timeSleep);
        remCyclesCount = (TextView) findViewById(R.id.remCyclesCount);

    }


    public void openThirdView(View view) {
        Intent intent = new Intent(this, ThirdActivity.class);
        startActivity(intent);
    }









    public void setTime(View view) {
        int hour = timePicker1.getCurrentHour();
        int min = timePicker1.getCurrentMinute();


        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");


        //Displays current time
        String currentTime = Calendar.getInstance().getTime().toString();
        curTimeText.setText(formatter.format(calendar.getTime()));

        //Slices time string for calculation
        String curTimeHourString = (String) curTimeText.getText().subSequence(0, 2);
        String curTimeMinString = (String) curTimeText.getText().subSequence(3, 5);

        int curTimeHour = Integer.parseInt(curTimeHourString);
        int curTimeMin = Integer.parseInt(curTimeMinString);


        //-------------------------------Time difference calculation-------------------------------
        if(min > curTimeMin){
            hourSleep = hour - curTimeHour;
            minSleep = min - curTimeMin;
        }

        else if(min <= curTimeMin){
            min += 60;
            hour--;
            hourSleep = hour - curTimeHour;
            minSleep = min - curTimeMin;
        }

        if(minSleep == 60){
            minSleep = 0;
            hourSleep++;
        }

        if(hourSleep < 0){
            hourSleep = 24 + hourSleep;
        }
        //-----------------------------------------------------------------------------------------

        
        //++++++++++++++++++++++++++++++Display string builders++++++++++++++++++++++++++++++++++++

        remCyclesCount.setText(new StringBuilder().append("69"));




        if(String.valueOf(hourSleep).length() == 2){
            timeSleeping.setText(new StringBuilder().append(hourSleep).append(" : 0").append(minSleep)
                    .append(" "));


        }


        if(String.valueOf(minSleep).length() == 1){
            timeSleeping.setText(new StringBuilder().append(hourSleep).append(" : 0").append(minSleep)
                    .append(" "));
        }
        else {
            timeSleeping.setText(new StringBuilder().append(hourSleep).append(" : ").append(minSleep)
                    .append(" "));
        }
    }

    public void showTime(int hour, int min) {
        time.setText(new StringBuilder().append(hour).append(" : ").append(min)
                .append(" ").append(format));
    }
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

}
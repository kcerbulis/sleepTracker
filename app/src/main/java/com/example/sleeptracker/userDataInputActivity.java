package com.example.sleeptracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Date;


public class userDataInputActivity extends AppCompatActivity {

    //Final Variables needed for db;
    private static Integer TimePickerHour;
    private static Integer TimePickerMinute;
    private static int starRating;
    private static Date timeOfWaking;


    //Initializing variables
    private TimePicker timePicker1;
    private TextView time;
    private TextView curTimeText, timeSleeping, remCyclesCount, sleepRating;
    private Calendar calendar;
    private String format = "";
    int hourSleep;
    int minSleep;


    //FAB button function to open first view
    public void openFirstView(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    //FAB button function to open third view
    public void openThirdView(View view) {
        Intent intent = new Intent(this, ThirdActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data_input);

        //Finding elements by ID
        timePicker1 = (TimePicker) findViewById(R.id.timePicker1);
        time = (TextView) findViewById(R.id.textView1);
        curTimeText = (TextView) findViewById(R.id.curTime);
        timeSleeping =  (TextView) findViewById(R.id.timeSleep);
        remCyclesCount = (TextView) findViewById(R.id.remCyclesCount);
        sleepRating = (TextView) findViewById(R.id.sleepRating);

        //5 star sleep rating initialized
        //Use .getRating to get selected value
        //final RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        //starRating = ratingBar.getNumStars();


        //Gets selected time from time selector
        calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);
        showTime(hour, min);
    }






    //Called when the save button is pressed
    //Calculates sleep time, REM and sleep rating
    public void setTime(View view) throws ParseException {

        //Gets values selected from time picker
        int hour = timePicker1.getCurrentHour();
        int min = timePicker1.getCurrentMinute();

        TimePickerHour = timePicker1.getCurrentHour();
        TimePickerMinute = timePicker1.getCurrentMinute();

        //Formats date data to give us time in 24h format
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");

        String dateString= TimePickerHour+":"+TimePickerMinute;
        timeOfWaking = formatter.parse(dateString);






        //Displays current time
        String currentTime = Calendar.getInstance().getTime().toString();
        curTimeText.setText(formatter.format(calendar.getTime()));


        //Slices time string for calculation
        String curTimeHourString = (String) curTimeText.getText().subSequence(0, 2);
        String curTimeMinString = (String) curTimeText.getText().subSequence(3, 5);

        //Converts current hours and minutes to integers for calculation
        int curTimeHour = Integer.parseInt(curTimeHourString);
        int curTimeMin = Integer.parseInt(curTimeMinString);


        //-------------------------------Time difference (time sleeping) calculation-------------------------------
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

        //REM calculation
        int remCyclesSlept = ((hourSleep * 60) + minSleep) / 90;

        //Outputs REM cycles
        remCyclesCount.setText(new StringBuilder().append("~" + remCyclesSlept));
        
        //++++++++++++++++++++++++++++++Display string builders++++++++++++++++++++++++++++++++++++
        if(remCyclesSlept <= 3){
            sleepRating.setText("Poor");
        }
        else if(remCyclesSlept == 4){
            sleepRating.setText("Good");
        }
        else if(remCyclesSlept > 4 && remCyclesSlept <= 6){
            sleepRating.setText("Ideal");
        }
        else if(remCyclesSlept > 6 && remCyclesSlept <= 7){
            sleepRating.setText("Good");
        }
        else if(remCyclesSlept > 7){
            sleepRating.setText("Excessive");
        }

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

    //Formats and display selected time
    public void showTime(int hour, int min) {
        time.setText(new StringBuilder().append(hour).append(" : ").append(min)
                .append(" ").append(format));
    }
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++



    //Getters and Setters for Data Values:



    public static int getStarRating() {
        return starRating;
    }

    public static Date getTimeOfWaking() {
        return timeOfWaking;
    }

    public static int getWakeHour() {
        return TimePickerHour;
    }

    public static int getWakeMinute() {
        return TimePickerMinute;
    }





}



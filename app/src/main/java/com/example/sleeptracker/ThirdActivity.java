package com.example.sleeptracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
    }

    //FAB button function to open first view
    public void openFirstView(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
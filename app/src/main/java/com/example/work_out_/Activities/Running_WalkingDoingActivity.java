package com.example.work_out_.Activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.work_out_.FinishedExerciseActivity;
import com.example.work_out_.R;
import com.example.work_out_.sensors.CLocation;

import java.util.Formatter;
import java.util.Locale;

/*
 *  I did this class with the help of this video: https://www.youtube.com/watch?v=1vHlLWtjJAw
 */

public class Running_WalkingDoingActivity extends AppCompatActivity implements View.OnClickListener {

    Chronometer chronometer;
    Button btnFinish;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runningwalking_doing);

        chronometer = findViewById(R.id.chronometer);
        btnFinish = findViewById(R.id.activitiesbuttonmain);
        btnFinish.setOnClickListener(this);

        chronometer.start();


    }


    @Override
    public void onClick(View v) {
        chronometer.stop();

        Intent goToFinish = new Intent(getApplicationContext(), FinishedExerciseActivity.class);
        goToFinish.putExtra("name","time".toCharArray());
        goToFinish.putExtra("number",(SystemClock.elapsedRealtime()-chronometer.getBase()));
        startActivity(goToFinish);
    }
    @Override
    public void onBackPressed(){

    }
}

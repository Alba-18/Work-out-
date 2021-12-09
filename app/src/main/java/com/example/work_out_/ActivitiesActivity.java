package com.example.work_out_;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnContextClickListener;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Date;

public class ActivitiesActivity extends AppCompatActivity {
    @RequiresApi(api = Build.VERSION_CODES.M)
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_activities_screen);
        setUpText();

        SharedPreferences sharedPreferences = getSharedPreferences("appSettings", MODE_PRIVATE);
        if(sharedPreferences.getBoolean("notifications", false)){
            notificationAlarm(1);
        }

        Button helpPopup = (Button) findViewById(R.id.HelpMain);
        helpPopup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(ActivitiesActivity.this,Help_PopUp.class));
            }
        });

    }

    public void notificationAlarm(int number_of_days_interval) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 10);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 1);

        if (calendar.getTime().compareTo(new Date()) < 0)
            calendar.add(Calendar.DAY_OF_MONTH, 1);

        Intent intent = new Intent(getApplicationContext(), Notifications.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        if (alarmManager != null) {
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), number_of_days_interval*AlarmManager.INTERVAL_DAY, pendingIntent);

        }
    }

    private void setUpText(){
        TextView difficultyPushups = findViewById(R.id.difficulty_pushups);
        TextView difficultySitups = findViewById(R.id.difficulty_situps);
        TextView difficultyWalking = findViewById(R.id.difficulty_walking);
        TextView difficultyRunning = findViewById(R.id.difficulty_running);
        //Change this for the difficulty chosen by the user
        String inputDifficulty = "high";
        difficultyPushups.setText(inputDifficulty);
        difficultySitups.setText(inputDifficulty);
        difficultyWalking.setText(inputDifficulty);
        difficultyRunning.setText(inputDifficulty);

        TextView timePushups = findViewById(R.id.time_pushups);
        TextView timeSitups = findViewById(R.id.time_situps);
        TextView timeWalking = findViewById(R.id.time_walking);
        TextView timeRunning = findViewById(R.id.time_running);
        //Change this for the different times depending on the difficulty of each activity
        String inputTP = "10 min";
        String inputTS = "15 min";
        String inputTW = "20 min";
        String inputTR = "25 min";
        timePushups.setText(inputTP);
        timeSitups.setText(inputTS);
        timeWalking.setText(inputTW);
        timeRunning.setText(inputTR);
    }

}

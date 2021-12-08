package com.example.work_out_;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivitiesActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_activities_screen);
        setUpText();

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

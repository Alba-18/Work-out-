package com.example.work_out_;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnContextClickListener;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class ActivitiesActivity extends AppCompatActivity {
    @RequiresApi(api = Build.VERSION_CODES.M)
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_activities_screen);
        setUpText();

        Button helpPopup = (Button) findViewById(R.id.HelpMain);
        helpPopup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(ActivitiesActivity.this,Help_PopUp.class));
            }
        });

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

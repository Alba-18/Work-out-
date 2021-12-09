package com.example.work_out_.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.work_out_.R;

public class RunningStartActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running_start);
        setUpText();
    }

    private void setUpText(){
        TextView time = findViewById(R.id.time_start_running);
        TextView difficulty = findViewById(R.id.difficulty_start_running);

        //Get from database
        //is a series needed for running and walking???
        String inputTime = "10 min";
        String inputDifficulty = "High";

        time.setText(inputTime);
        difficulty.setText(inputDifficulty);
    }
}
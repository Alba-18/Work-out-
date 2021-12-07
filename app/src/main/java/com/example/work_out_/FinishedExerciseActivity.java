package com.example.work_out_;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FinishedExerciseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished_activitiy);
        setUpText();
    }

    private void setUpText(){
        TextView info = findViewById(R.id.info_finished_exercise);

        //Get from last activity done
        String input ="";
        info.setText(input);
    }
}

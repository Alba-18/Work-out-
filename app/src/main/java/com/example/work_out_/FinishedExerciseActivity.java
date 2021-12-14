package com.example.work_out_;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class FinishedExerciseActivity extends AppCompatActivity implements View.OnClickListener {

    private Button finishButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished_activitiy);
        finishButton = findViewById(R.id.activitiesbuttonmain);
        finishButton.setOnClickListener(this);
        setUpText();
    }

    @SuppressLint("SetTextI18n")
    private void setUpText(){
        TextView info = findViewById(R.id.info_finished_exercise);
        TextView numberView = findViewById(R.id.info_finished_exercise_number);
        Intent getExercise = getIntent();
        char[] name = getExercise.getCharArrayExtra("name");
        int number = getExercise.getIntExtra("number",0);
        String input = new String(name);
        numberView.setText(Integer.toString(number));
        info.setText(input);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(getApplicationContext(),ActivitiesActivity.class));
    }
}

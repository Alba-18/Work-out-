package com.example.work_out_;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SitupsStartActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_situps_start);
        setUpText();
    }

    private void setUpText(){
        TextView time = findViewById(R.id.time_start_situps);
        TextView difficulty = findViewById(R.id.difficulty_start_situps);
        TextView serie = findViewById(R.id.situps_exercise_serie);

        //Get from database
        String inputTime = "10 min";
        String inputDifficulty = "High";
        String inputSerie = "10-11-12-12-14";

        time.setText(inputTime);
        difficulty.setText(inputDifficulty);
        serie.setText(inputSerie);
    }
}
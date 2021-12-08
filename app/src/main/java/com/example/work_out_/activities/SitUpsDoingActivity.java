package com.example.work_out_.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.work_out_.R;
import com.example.work_out_.sensors.Gyroscope;

public class SitUpsDoingActivity extends AppCompatActivity {

    private TextView counterView;
    private Gyroscope gyroscope;
    private final int[] count = {0};
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_situps_doing);
        counterView = findViewById(R.id.situps_counter);
        
        counterView.setText(Integer.toString(count[0]));
        gyroscope = new Gyroscope(this);

        gyroscope.setListener((rx,ry,rz)-> {
            if(rx > 4.0f){
                count[0] += 1;
                counterView.setText(Integer.toString(count[0]));
            }
        });


    }

    @Override
    protected void onResume(){
        super.onResume();
        gyroscope.register();
    }

    @Override
    protected void onPause(){
        super.onPause();
        gyroscope.unregister();
    }

}

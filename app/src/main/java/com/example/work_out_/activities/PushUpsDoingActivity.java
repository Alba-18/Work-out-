package com.example.work_out_.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.work_out_.R;
import com.example.work_out_.sensors.Gyroscope;

public class PushUpsDoingActivity extends AppCompatActivity {
        private TextView counterView;
        private final int[] count = {0};
        @SuppressLint("SetTextI18n")
        @Override
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_pushups_doing);
            counterView = findViewById(R.id.pushups_counter);

            View.OnClickListener actionButtonListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String initialCounter = counterView.getText().toString();
                    int number = Integer.parseInt(initialCounter);
                    number ++;
                    String finalCounter = Integer.toString(number);
                    counterView.setText(finalCounter);
                }
            };

            Button buttonDoPushup = (Button) findViewById(R.id.dopushup);
            buttonDoPushup.setOnClickListener(actionButtonListener);

        }

        @Override
        protected void onResume(){
            super.onResume();
        }

        @Override
        protected void onPause(){
            super.onPause();
        }

    }

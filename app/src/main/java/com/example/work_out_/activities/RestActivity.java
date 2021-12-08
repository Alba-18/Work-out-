package com.example.work_out_.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.work_out_.R;
import com.example.work_out_.sensors.Gyroscope;

public class RestActivity extends AppCompatActivity {
    private TextView counterView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest);
        counterView = findViewById(R.id.rest_counter);
        timer();

        View.OnClickListener actionButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RestActivity.this,"TERMINADO",Toast.LENGTH_SHORT).show();
            }
        };

        Button buttonFinishRest = (Button) findViewById(R.id.finish_rest);
        buttonFinishRest.setOnClickListener(actionButtonListener);

    }

    private void timer(){
        int restTime = 60*1000;
        CountDownTimer count = new CountDownTimer(restTime, 1000) {
            @Override
            public void onTick(long l) {
                System.out.println("Texto del contador: "+counterView.getText());
                System.out.println("Texto a colocar: "+l);
                long time = l/1000;
                String input = ""+time;
                counterView.setText(input);
            }

            @Override
            public void onFinish() {
                Toast.makeText(RestActivity.this,"TERMINADO",Toast.LENGTH_SHORT).show();
            }
        }.start();
    }

}

package com.example.work_out_.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.work_out_.R;

public class RestActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView counterView;
    Intent getActualSet,goToPushUps, goToSitups;
    int actualSet;
    String activityName ;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getActualSet = getIntent();
        actualSet = getActualSet.getIntExtra("sets",1);
        activityName = new String(getActualSet.getCharArrayExtra("name"));

        setContentView(R.layout.activity_rest);
        counterView = findViewById(R.id.rest_counter);
        timer();
        Button buttonFinishRest = (Button) findViewById(R.id.finish_rest);
        buttonFinishRest.setOnClickListener(this);

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
                goToPushUps = new Intent(getApplicationContext(),PushUpsDoingActivity.class);
                goToPushUps.putExtra("sets",actualSet + 1);
                startActivity(goToPushUps);
            }
        }.start();
    }

    @Override
    public void onBackPressed(){

    }

    @Override
    public void onClick(View v) {
        if(activityName.equals("pushups")){
            goToPushUps = new Intent(getApplicationContext(),PushUpsDoingActivity.class);
            goToPushUps.putExtra("sets",actualSet + 1);

            startActivity(goToPushUps);
        }
        else if(activityName.equals("sit-ups")){
            goToSitups = new Intent(getApplicationContext(),SitUpsDoingActivity.class);
            goToSitups.putExtra("sets",actualSet + 1);

            startActivity(goToSitups);
        }
    }
}

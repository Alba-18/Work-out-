package com.example.work_out_;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class ActivitiesActivity extends AppCompatActivity {
    @RequiresApi(api = Build.VERSION_CODES.M)

    Button btn_open_popUp;
    Button btn_close;
    LayoutInflater layoutInflater;
    View popupView;
    PopupWindow popupWindow;

    @RequiresApi(api = Build.VERSION_CODES.M)
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_activities_screen);
        setUpText();

        btn_open_popUp = (Button)findViewById(R.id.helpConfig);
        btn_open_popUp.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View arg0){
                layoutInflater =(LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                popupView = layoutInflater.inflate(R.layout.activity_config_help, null);
                popupWindow = new PopupWindow(popupView, RadioGroup.LayoutParams.WRAP_CONTENT,
                        RadioGroup.LayoutParams.WRAP_CONTENT);
                btn_close = (Button)popupView.findViewById(R.id.id_close);
                btn_close.setOnClickListener(new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }});

                popupWindow.showAsDropDown(btn_open_popUp);

            }});


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

package com.example.work_out_;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.work_out_.Activities.MapActivity;
import com.example.work_out_.Activities.PushupsStartActivity;
import com.example.work_out_.Activities.RunningStartActivity;
import com.example.work_out_.Activities.SitupsStartActivity;
import com.example.work_out_.Activities.WalkingStartActivity;

public class ActivitiesActivity extends AppCompatActivity implements View.OnClickListener {
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

        btn_open_popUp = (Button)findViewById(R.id.HelpActivitiesScreen);
        btn_open_popUp.setOnClickListener(new Button.OnClickListener(){
            @SuppressLint("InflateParams")
            @Override
            public void onClick(View arg0){
                layoutInflater =(LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                popupView = layoutInflater.inflate(R.layout.activity_help_pop_up, null);
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

        FrameLayout activity1 = findViewById(R.id.FirstActivity);
        FrameLayout activity2 = findViewById(R.id.SecondActivity);
        FrameLayout activity3 = findViewById(R.id.ThirdActivity);
        FrameLayout activity4 = findViewById(R.id.FourthActivity);
        activity1.setOnClickListener(this);
        activity2.setOnClickListener(this);
        activity3.setOnClickListener(this);
        activity4.setOnClickListener(this);

    }

    private void setUpText(){

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

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.FirstActivity:
                startActivity(new Intent(getApplicationContext(), PushupsStartActivity.class));
                break;
            case R.id.SecondActivity:
                startActivity(new Intent(getApplicationContext(), WalkingStartActivity.class));
                break;
            case R.id.ThirdActivity:
                startActivity(new Intent(getApplicationContext(), RunningStartActivity.class));
                break;
            case R.id.FourthActivity:
                startActivity(new Intent(getApplicationContext(), SitupsStartActivity.class));
                break;
            case R.id.gotomapbuttonmain:
                startActivity(new Intent(getApplicationContext(), MapActivity.class));
                break;
            case R.id.ActivitiesGoToProfile:
                startActivity(new Intent(ActivitiesActivity.this, ProfileActivity.class));
                break;
            case R.id.ActivitiesGoToConfig:
                startActivity(new Intent(ActivitiesActivity.this, ConfigActivity.class));
                break;
        }

    }
}

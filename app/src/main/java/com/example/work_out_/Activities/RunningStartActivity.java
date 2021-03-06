package com.example.work_out_.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.work_out_.ActivitiesActivity;
import com.example.work_out_.R;

public class RunningStartActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_open_popUp;
    Button btn_close;
    Button btn_goToRun;
    LayoutInflater layoutInflater;
    View popupView;
    PopupWindow popupWindow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running_start);
        setUpText();

        btn_goToRun = findViewById(R.id.btnGoToRun);
        btn_goToRun.setOnClickListener(this);
        btn_open_popUp = (Button)findViewById(R.id.HelpRunning);
        btn_open_popUp.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View arg0){
                layoutInflater =(LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                popupView = layoutInflater.inflate(R.layout.activity_activity_popup, null);
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
        TextView time = findViewById(R.id.time_start_running);

        //Get from database
        //is a series needed for running and walking???
        String inputTime = "10 min";
        String inputDifficulty = "High";

        time.setText(inputTime);
    }


    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Row_Push_Up:
                startActivity(new Intent(RunningStartActivity.this, ActivitiesActivity.class));
                break;
            case R.id.btnGoToRun:
                startActivity(new Intent(getApplicationContext(),Running_WalkingDoingActivity.class));
        }


    }

}
package com.example.work_out_.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.work_out_.R;

public class SitupsStartActivity extends AppCompatActivity {

    Button btn_open_popUp;
    Button btn_close;
    LayoutInflater layoutInflater;
    View popupView;
    PopupWindow popupWindow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_situps_start);
        setUpText();

        btn_open_popUp = (Button)findViewById(R.id.HelpSitUps);
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
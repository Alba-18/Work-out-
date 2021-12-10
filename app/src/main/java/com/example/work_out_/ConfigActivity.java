package com.example.work_out_;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.provider.Settings.System;
import android.widget.Switch;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class ConfigActivity extends AppCompatActivity {

    private SeekBar seekbar;
    private Switch switchNotif;
    private ContentResolver cResolver;
    private Window window;
    private WindowManager.LayoutParams layoutParams;

    Button btn_open_popUp;
    Button btn_close;
    LayoutInflater layoutInflater;
    View popupView;
    PopupWindow popupWindow;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_config);

        seekbar = findViewById(R.id.seekBar);
        seekbar.setOnSeekBarChangeListener(new SeekbarListener());
        window = getWindow();
        cResolver =  getContentResolver();
        layoutParams = window.getAttributes();

        switchNotif = findViewById(R.id.switchNotifications);

        if(!System.canWrite(getApplicationContext())){
            Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
            startActivity(intent);
        }

        loadSettings();

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

    protected void onStop() {
        super.onStop();
        SharedPreferences sharedPreferences = getSharedPreferences("appSettings", MODE_PRIVATE);
        if(sharedPreferences.getBoolean("notifications", false)){
            startService( new Intent( this, NotificationService.class ));
        }
    }

    public void loadSettings(){
        SharedPreferences sharedPreferences = getSharedPreferences("appSettings", MODE_PRIVATE);

        int brightness = sharedPreferences.getInt("brightness", 80);
        this.seekbar.setProgress(brightness);
        layoutParams.screenBrightness = brightness / (float)100;
        window.setAttributes(layoutParams);

        boolean notifications = sharedPreferences.getBoolean("notifications", false);
        this.switchNotif.setChecked(notifications);


    }

    public void save(View view){
        SharedPreferences sharedPreferences = getSharedPreferences("appSettings", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("brightness", this.seekbar.getProgress());
        editor.putBoolean("notifications", switchNotif.isChecked());
        editor.apply();
    }

    public void goBack(View view){
        startActivity(new Intent(this, ActivitiesActivity.class));
    }

    private class SeekbarListener implements SeekBar.OnSeekBarChangeListener {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            layoutParams.screenBrightness = progress / (float)100;
            window.setAttributes(layoutParams);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }



}

package com.example.work_out_;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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


    }

    public void loadSettings(){
        SharedPreferences sharedPreferences = getSharedPreferences("appSettings", MODE_PRIVATE);

        int brightness = sharedPreferences.getInt("brightness", 80);
        boolean notifications = sharedPreferences.getBoolean("notifications", false);
        this.seekbar.setProgress(brightness);
        this.switchNotif.setChecked(notifications);

        layoutParams.screenBrightness = brightness / (float)255;
        window.setAttributes(layoutParams);
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

package com.example.work_out_;

import android.content.ContentResolver;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.provider.Settings.System;
import android.widget.Switch;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class ConfigActivity extends AppCompatActivity {

    private SeekBar seekbar;
    private Switch switchNotif;
    private int defaultBrightness;
    private boolean defaultSwitchNotif;
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
        layoutParams.screenBrightness = defaultBrightness / (float)255;
        window.setAttributes(layoutParams);

        switchNotif = findViewById(R.id.switchNotifications);
        defaultSwitchNotif = switchNotif.isChecked();
        switchNotif.setOnCheckedChangeListener(new SwitchListener());

        try {
            defaultBrightness = System.getInt(cResolver, System.SCREEN_BRIGHTNESS);

        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }

        seekbar.setProgress(defaultBrightness);

        if(!Settings.System.canWrite(getApplicationContext())){
            Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
            startActivity(intent);
        }

    }

    public void save(View view) throws Settings.SettingNotFoundException {
        defaultBrightness = System.getInt(cResolver, System.SCREEN_BRIGHTNESS);
        defaultSwitchNotif = switchNotif.isChecked();
    }

    public void goBack(View view){
        System.putInt(cResolver, System.SCREEN_BRIGHTNESS, defaultBrightness);
        layoutParams.screenBrightness = defaultBrightness / (float)100;
        window.setAttributes(layoutParams);
        seekbar.setProgress(defaultBrightness);
        switchNotif.setChecked(defaultSwitchNotif);
    }

    private class SeekbarListener implements SeekBar.OnSeekBarChangeListener {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            System.putInt(cResolver, System.SCREEN_BRIGHTNESS, progress);
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

    private class SwitchListener implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            //TODO enable notifications or not
        }
    }

}

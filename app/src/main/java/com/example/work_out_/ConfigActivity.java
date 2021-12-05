package com.example.work_out_;

import android.content.ContentResolver;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.provider.Settings.System;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class ConfigActivity extends AppCompatActivity {

    private SeekBar seekbar;
    private int brightness;
    private ContentResolver cResolver;
    private Window window;
    WindowManager.LayoutParams layoutParams;

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
        layoutParams.screenBrightness = brightness / (float)255;
        window.setAttributes(layoutParams);


        try {
            brightness = System.getInt(cResolver, System.SCREEN_BRIGHTNESS);

        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }

        seekbar.setProgress(brightness);

        if(!Settings.System.canWrite(getApplicationContext())){
            Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
            startActivity(intent);
        }

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

}

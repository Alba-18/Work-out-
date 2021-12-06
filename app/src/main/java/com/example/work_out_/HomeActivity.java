package com.example.work_out_;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_home_screen);

        progressBar = findViewById(R.id.progressBar);
        ObjectAnimator anim = ObjectAnimator.ofInt(progressBar, "progress", 100);
        anim.setDuration(2500);
        anim.start();
        (new Handler()).postDelayed(this::goToLogin, 2600);


    }

    public void goToLogin(){
        startActivity(new Intent(this, LoginActivity.class));
    }
}

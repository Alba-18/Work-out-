package com.example.work_out_.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.work_out_.R;
import com.example.work_out_.model.Activities;
import com.example.work_out_.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;

public class PushupsStartActivity extends AppCompatActivity implements View.OnClickListener {

    private Button startActivityButton;
    private User userProfile;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pushups_start);

        startActivityButton = findViewById(R.id.activitiesbuttonmain);
        startActivityButton.setOnClickListener(this);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("users");
        userID = user.getUid();

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userProfile = snapshot.getValue(User.class);

                if(userProfile != null){
                    Activities activity = new Activities("description","push-ups",userProfile);
                    setUpText(userProfile.getLevelOfExercise(),activity.getSets());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setUpText(String difficult,int[] setUps){
        TextView time = findViewById(R.id.time_start_pushups);
        TextView difficulty = findViewById(R.id.difficulty_start_pushups);
        TextView serie = findViewById(R.id.pushups_exercise_serie);

        //Get from database
        String inputTime = "10 min";
        String inputDifficulty = difficult;
        String inputSerie = Arrays.toString(setUps).replace(",","-");

        time.setText(inputTime);
        difficulty.setText(inputDifficulty);
        serie.setText(inputSerie);
    }

    @Override
    public void onClick(View v) {
        Intent goToDoingPushUps = new Intent(getApplicationContext(),PushUpsDoingActivity.class);
        goToDoingPushUps.putExtra("sets",0);
        startActivity(goToDoingPushUps);
    }
}

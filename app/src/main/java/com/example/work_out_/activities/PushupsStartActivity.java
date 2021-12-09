package com.example.work_out_.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

public class PushupsStartActivity extends AppCompatActivity implements View.OnClickListener {

    private Button startWorkout;
    private User userProfile;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    private Activities activities;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pushups_start);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("users");
        userID = user.getUid();
        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userProfile = snapshot.getValue(User.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        activities = new Activities("description","push-ups", userProfile );
        setUpText();

    }

    private void setUpText(){
        TextView time = findViewById(R.id.time_start_pushups);
        TextView difficulty = findViewById(R.id.difficulty_start_pushups);
        TextView serie = findViewById(R.id.pushups_exercise_serie);
        startWorkout = findViewById(R.id.activitiesbuttonmain);
        startWorkout.setOnClickListener(this);
        //Get from database
        String inputTime = "10 min";
        String inputDifficulty = userProfile.getLevelOfExercise();
        String numberOfSetsText = "";
        int[] inputSerie = activities.getSets();
        StringBuilder strBuilder = new StringBuilder();
        for (int j : inputSerie) {
            strBuilder = strBuilder.append(j);
            strBuilder = strBuilder.append("-");
        }
        time.setText(inputTime);
        difficulty.setText(inputDifficulty);
        serie.setText(strBuilder.toString());
    }

    @Override
    public void onClick(View v) {
        Intent startPushUps = new Intent(getApplicationContext(),PushUpsDoingActivity.class);
        startPushUps.putExtra("numberOfSets",0);
        startActivity(startPushUps);
    }
}

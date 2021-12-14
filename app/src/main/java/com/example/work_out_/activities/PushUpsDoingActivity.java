package com.example.work_out_.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.work_out_.FinishedExerciseActivity;
import com.example.work_out_.R;
import com.example.work_out_.model.Activities;
import com.example.work_out_.model.User;
import com.example.work_out_.sensors.Gyroscope;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PushUpsDoingActivity extends AppCompatActivity {
        private TextView counterView;
        private TextView numberOfPushUpsView;
        private Intent getActualSet,goToRest;

        private User userProfile;
        private FirebaseUser user;
        private DatabaseReference reference;
        private String userID;

        private Activities activity;
        @SuppressLint("SetTextI18n")
        @Override
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_pushups_doing);
            numberOfPushUpsView = findViewById(R.id.numberOfPushUps);
            counterView = findViewById(R.id.pushups_counter);
            getActualSet = getIntent();
            int actualSet = getActualSet.getIntExtra("sets",0);
            user = FirebaseAuth.getInstance().getCurrentUser();
            reference = FirebaseDatabase.getInstance().getReference("users");
            userID = user.getUid();

            reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    userProfile = snapshot.getValue(User.class);

                    if(userProfile != null){
                        activity = new Activities("description","pushups",userProfile.getLevelOfExercise());

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            numberOfPushUpsView.setText("objective:" + activity.getSets()[actualSet]);
            View.OnClickListener actionButtonListener = v -> {

                String initialCounter = counterView.getText().toString();
                int number = Integer.parseInt(initialCounter);
                number ++;
                String finalCounter = Integer.toString(number);
                counterView.setText(finalCounter);

                if(activity.getSets()[actualSet] == number){
                    goToRest = new Intent(getApplicationContext(),RestActivity.class);
                    goToRest.putExtra("sets",actualSet);
                    startActivity(goToRest);
                }
                else if(actualSet == 5){
                    Intent goToFinishExercise = new Intent(getApplicationContext(),FinishedExerciseActivity.class);
                    int[] numberOfPushUps = activity.getSets();
                    int n = 0;
                    for(int i = 0; i < numberOfPushUps.length; i++){
                        n = n + activity.getSets()[i];
                    }
                    goToFinishExercise.putExtra("name",activity.getName().toCharArray());
                    goToFinishExercise.putExtra("number",n);
                    startActivity(goToFinishExercise);
                }
            };



            Button buttonDoPushup = (Button) findViewById(R.id.dopushup);
            buttonDoPushup.setOnClickListener(actionButtonListener);


        }

        @Override
        protected void onResume(){
            super.onResume();
        }

        @Override
        protected void onPause(){
            super.onPause();
        }

    }

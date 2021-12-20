package com.example.work_out_.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.work_out_.ActivitiesActivity;
import com.example.work_out_.FinishedExerciseActivity;
import com.example.work_out_.ForgotPasswordActivity;
import com.example.work_out_.LoginActivity;
import com.example.work_out_.R;
import com.example.work_out_.RegisterActivity;
import com.example.work_out_.model.Activities;
import com.example.work_out_.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PushUpsDoingActivity extends AppCompatActivity implements View.OnClickListener {
        private TextView counterView;
        private TextView numberOfPushUpsView;
        private Intent getActualSet,goToRest;

        private User userProfile;
        private FirebaseUser user;
        private DatabaseReference reference;
        private String userID;
        private int actualSet;
        private Activities activity;
        @SuppressLint("SetTextI18n")
        @Override
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_pushups_doing);
            numberOfPushUpsView = findViewById(R.id.numberOfPushUps);
            counterView = findViewById(R.id.pushups_counter);
            getActualSet = getIntent();
            actualSet = getActualSet.getIntExtra("sets",0);
            user = FirebaseAuth.getInstance().getCurrentUser();
            reference = FirebaseDatabase.getInstance().getReference("users");
            userID = user.getUid();


            reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    userProfile = snapshot.getValue(User.class);

                    if(userProfile != null){
                        activity = new Activities("description","pushups",userProfile.getLevelOfExercise());
                        numberOfPushUpsView.setText("objective:" + activity.getSets()[actualSet]);

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            Button buttonDoPushup = (Button) findViewById(R.id.dopushup);
            buttonDoPushup.setOnClickListener(this);




        }
    public void onClick(View v) {
            switch (v.getId()) {
                case (R.id.dopushup):
                    String initialCounter = counterView.getText().toString();
                    int number = Integer.parseInt(initialCounter);
                    number++;
                    String finalCounter = Integer.toString(number);
                    counterView.setText(finalCounter);

                    if (activity.getSets()[actualSet] == number && actualSet < 5) {
                        goToRest = new Intent(getApplicationContext(), RestActivity.class);
                        goToRest.putExtra("name", activity.getName().toCharArray());
                        goToRest.putExtra("sets", actualSet);
                        startActivity(goToRest);
                    } else if (actualSet == 5) {

                        Intent goToFinishExercise = new Intent(getApplicationContext(), FinishedExerciseActivity.class);
                        int[] numberOfPushUps = activity.getSets();
                        int n = 0;
                        for (int i = 0; i < numberOfPushUps.length; i++) {
                            n = n + activity.getSets()[i];
                        }
                        goToFinishExercise.putExtra("name", activity.getName().toCharArray());
                        goToFinishExercise.putExtra("number", n);
                        startActivity(goToFinishExercise);
                    }
                    break;

                case (R.id.activityendpushups):
                    startActivity(new Intent(PushUpsDoingActivity.this, ActivitiesActivity.class));
                    break;
            }
    }

    @Override
        public void onBackPressed(){

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

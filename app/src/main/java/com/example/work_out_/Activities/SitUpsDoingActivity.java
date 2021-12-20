package com.example.work_out_.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.work_out_.ActivitiesActivity;
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

public class SitUpsDoingActivity extends AppCompatActivity {

    private TextView counterView,numberOfSitUpsView;
    private Intent goToRest;

    private User userProfile;

    private Activities activity;
    private Gyroscope gyroscope;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_situps_doing);
        numberOfSitUpsView = findViewById(R.id.numberOfSitUps);
        counterView = findViewById(R.id.situps_counter);
        Intent getActualSet = getIntent();
        int actualSet = getActualSet.getIntExtra("sets",0);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        assert user != null;
        String userID = user.getUid();

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userProfile = snapshot.getValue(User.class);

                if(userProfile != null){
                    activity = new Activities("description","sit-ups",userProfile.getLevelOfExercise());
                    numberOfSitUpsView.setText("objective" + activity.getSets()[actualSet]);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        final int[] count = {0};
        counterView.setText(Integer.toString(0));
        gyroscope = new Gyroscope(this);

        gyroscope.setListener((rx,ry,rz)-> {
            if(rx > 5.0f){
                count[0] += 1 ;
                counterView.setText(Integer.toString(count[0]));
                if(activity.getSets()[actualSet] == count[0] && actualSet < 5) {
                    if (activity.getSets()[actualSet] == count[0]) {
                        goToRest = new Intent(getApplicationContext(), RestActivity.class);
                        goToRest.putExtra("name", activity.getName().toCharArray());
                        goToRest.putExtra("sets", actualSet);
                        startActivity(goToRest);
                    }
                }
                else if (actualSet == 5) {
                    Intent goToFinishExercise = new Intent(getApplicationContext(), FinishedExerciseActivity.class);
                    int[] numberOfSitUps = activity.getSets();
                    int n = 0;
                    for(int i = 0;i < numberOfSitUps.length; i++){
                        n = n + activity.getSets()[i];
                    }
                    goToFinishExercise.putExtra("name",activity.getName().toCharArray());
                    goToFinishExercise.putExtra("number",n);
                    startActivity(goToFinishExercise);
                }
            }
        });

    }
    @Override
    public void onBackPressed(){

    }
    public void onClick(View v) {
        switch (v.getId()) {
            case (R.id.activitiesbuttonmain):
                startActivity(new Intent(SitUpsDoingActivity.this, ActivitiesActivity.class));
                break;
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        gyroscope.register();
    }

    @Override
    protected void onPause(){
        super.onPause();
        gyroscope.unregister();
    }

}

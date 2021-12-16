package com.example.work_out_.Espresso_Tests;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
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

import java.util.Arrays;

public class SitupsStartActivity extends AppCompatActivity implements View.OnClickListener {


    Button btn_open_popUp;
    Button btn_close;
    LayoutInflater layoutInflater;
    View popupView;
    PopupWindow popupWindow;

    private User userProfile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_situps_start);

        Button startActivityButton = findViewById(R.id.activitiesbuttonmain);
        startActivityButton.setOnClickListener(this);


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        assert user != null;
        String userID = user.getUid();
        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userProfile = snapshot.getValue(User.class);

                if(userProfile != null){
                    Activities activity = new Activities("description","sit-ups",userProfile.getLevelOfExercise());
                    setUpText(userProfile.getLevelOfExercise(),activity.getSets());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btn_open_popUp = (Button)findViewById(R.id.HelpSitUps);
        btn_open_popUp.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View arg0){
                layoutInflater =(LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                popupView = layoutInflater.inflate(R.layout.activity_activity_popup, null);
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

    private void setUpText(String difficult, int[] setUps){
        TextView time = findViewById(R.id.time_start_situps);
        TextView difficulty = findViewById(R.id.difficulty_start_situps);
        TextView serie = findViewById(R.id.situps_exercise_serie);

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
        Intent goToDoingPushUps = new Intent(getApplicationContext(),SitUpsDoingActivity.class);
        goToDoingPushUps.putExtra("sets",0);
        startActivity(goToDoingPushUps);

    }
}
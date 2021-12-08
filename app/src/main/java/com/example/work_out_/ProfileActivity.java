package com.example.work_out_;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.work_out_.model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class ProfileActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner levelOfExerciseSpinner;
    private Spinner cardioSpinner;
    private Spinner exerciseImpactSpinner;
    private Spinner objetiveSpinner;

    private EditText profileNameView;
    private EditText profileAgeView;
    private EditText profileWeightView;
    private EditText profileHeightView;

    private String profileLevelOfExercise;
    private String profileName;
    private String profileAge;
    private String profileWeight;
    private String profileHeight;
    private String profileCardio;
    private String profileExerciseImpact;
    private String profileObjetive;


    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("users");
        userID = user.getUid();

        //Creation of EditText Views
        profileNameView = (EditText) findViewById(R.id.profileUserName);
        profileAgeView = (EditText) findViewById(R.id.profileAge);
        profileWeightView = (EditText) findViewById(R.id.profileWeight);
        profileHeightView = (EditText) findViewById(R.id.profileHeight);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if(userProfile != null){
                    String fullName = userProfile.getName();
                    String email = userProfile.getEmail();
                    String age = userProfile.getAge();

                    profileNameView.setText(fullName);
                    profileAgeView.setText(age);

                    Toast.makeText(ProfileActivity.this,fullName,Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(ProfileActivity.this,"User not found",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        switch (parent.getId()) {
            case R.id.profileLevelOfExercise:
                this.profileLevelOfExercise = (String) parent.getItemAtPosition(pos);
                break;

            case R.id.profileCardio:
                this.profileCardio = (String) parent.getItemAtPosition(pos);
                break;

            case R.id.profileExerciseImpact:
                this.profileExerciseImpact = (String) parent.getItemAtPosition(pos);
                break;

            case R.id.profileObjetive:
                this.profileObjetive = (String) parent.getItemAtPosition(pos);
                break;
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

}
package com.example.work_out_;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import com.example.work_out_.model.User;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener{

    private FirebaseAuth mAuth;
    private EditText nameView, emailView, ageView, passView, weightView, heightView;
    private Spinner levelOfExerciseSpinner, cardioSpinner, exerciseImpactSpinner, objetiveSpinner;
    private String registerLevelOfExercise, registerCardio, registerExerciseImpact, registerObjetive;
    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        nameView = (EditText) findViewById(R.id.user_Name);
        emailView = (EditText) findViewById(R.id.Register_Email);
        ageView = (EditText) findViewById(R.id.Register_Age);
        passView = (EditText) findViewById(R.id.user_Password);
        heightView = (EditText) findViewById(R.id.Register_Height);
        weightView = (EditText) findViewById(R.id.Register_Weight);

        Button registerButtonView = (Button) findViewById(R.id.Register_FinishButton);
        registerButtonView.setOnClickListener(this);

        //Creation of Spinners
        //Creation of Profile Level of Exercise Spinner
        this.levelOfExerciseSpinner = (Spinner) findViewById(R.id.Register_LevelOfExercise);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> levelOfExerciseAdapter = ArrayAdapter.createFromResource(this,
                R.array.levelOfExerciseArray, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        levelOfExerciseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        this.levelOfExerciseSpinner.setAdapter(levelOfExerciseAdapter);
        this.levelOfExerciseSpinner.setOnItemSelectedListener(this);

        //Creation of Cardio Spinner
        this.cardioSpinner = (Spinner) findViewById(R.id.Register_CardioExercise);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> cardioAdapter = ArrayAdapter.createFromResource(this,
                R.array.cardioArray, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        cardioAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        this.cardioSpinner.setAdapter(cardioAdapter);
        this.cardioSpinner.setOnItemSelectedListener(this);

        //Creation of Exercise Impact Spinner
        this.exerciseImpactSpinner = (Spinner) findViewById(R.id.Register_ExerciseImpact);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> exerciseImpactAdapter = ArrayAdapter.createFromResource(this,
                R.array.exerciseImpact, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        cardioAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        this.exerciseImpactSpinner.setAdapter(exerciseImpactAdapter);
        this.exerciseImpactSpinner.setOnItemSelectedListener(this);

        //Creation of Objetive Spinner
        this.objetiveSpinner = (Spinner) findViewById(R.id.registerObjetive);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> objetiveAdapter = ArrayAdapter.createFromResource(this,
                R.array.objetiveArray, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        cardioAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        this.objetiveSpinner.setAdapter(objetiveAdapter);
        this.objetiveSpinner.setOnItemSelectedListener(this);
    }


    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        switch (parent.getId()) {
            case R.id.Register_LevelOfExercise:
                this.registerLevelOfExercise = (String) parent.getItemAtPosition(pos);
                break;

            case R.id.Register_CardioExercise:
                this.registerCardio = (String) parent.getItemAtPosition(pos);
                break;

            case R.id.Register_ExerciseImpact:
                this.registerExerciseImpact = (String) parent.getItemAtPosition(pos);
                break;

            case R.id.registerObjetive:
                this.registerObjetive = (String) parent.getItemAtPosition(pos);
                break;
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    public void onClick(View v){
        if (v.getId() == R.id.Register_FinishButton) {
            registerUser();
        }
    }

    public void registerUser(){

        String name = nameView.getText().toString().trim();
        String email = emailView.getText().toString().trim();
        String age = ageView.getText().toString().trim();
        String pass = passView.getText().toString().trim();
        String height = heightView.getText().toString().trim();
        String weight = weightView.getText().toString().trim();

        StringEmptyAndError(name,nameView);
        StringEmptyAndError(email,emailView);
        StringEmptyAndError(age,ageView);
        StringEmptyAndError(pass,passView);

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailView.setError("invalid email");
            emailView.requestFocus();
        }

        if(pass.length() < 6){
            passView.setError("Invalid password");
            passView.requestFocus();
        }

        mAuth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User(name,email,age, weight, height, registerLevelOfExercise, registerExerciseImpact, registerCardio, registerObjetive);
                            FirebaseDatabase.getInstance().getReference("users")
                                    .child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if(task.isSuccessful()){
                                        Toast.makeText(RegisterActivity.this, "User has been registered successfully",Toast.LENGTH_SHORT).show();
                                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                                        if(user.isEmailVerified()){
                                            startActivity(new Intent(RegisterActivity.this,ProfileActivity.class));
                                        }
                                        else{
                                            user.sendEmailVerification();
                                            Toast.makeText(RegisterActivity.this, "Check your email to verify",Toast.LENGTH_SHORT).show();

                                        }

                                    }{
                                        Toast.makeText(RegisterActivity.this, "Failed to register try again",Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                        }else{
                            Toast.makeText(RegisterActivity.this, "Failed to register try again",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void StringEmptyAndError(String text, EditText textView){
        if(text.isEmpty()){
            textView.setError("field required");
            textView.requestFocus();
        }
    }

}



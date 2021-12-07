package com.example.work_out_;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private EditText nameView, emailView, ageView, passView;

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        nameView = (EditText) findViewById(R.id.user_Name);
        emailView = (EditText) findViewById(R.id.Register_Email);
        ageView = (EditText) findViewById(R.id.Register_Age);
        passView = (EditText) findViewById(R.id.user_Password);

        Button registerButtonView = (Button) findViewById(R.id.Register_FinishButton);
        registerButtonView.setOnClickListener(this);
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
                            User user = new User(name,email,age);

                            FirebaseDatabase.getInstance().getReference("users")
                                    .child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if(task.isSuccessful()){
                                        Toast.makeText(RegisterActivity.this, "User has beenn registered succesfully",Toast.LENGTH_SHORT).show();
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



package com.example.work_out_;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText emailView;
    private Button resetPasswordButton, goToLogin;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        emailView = findViewById(R.id.emailView);
        resetPasswordButton = findViewById(R.id.resetPasswordButton);
        goToLogin = findViewById(R.id.nav_login);
        auth = FirebaseAuth.getInstance();

        resetPasswordButton.setOnClickListener(v -> resetPassword());

        goToLogin.setOnClickListener(v -> startActivity(new Intent(ForgotPasswordActivity.this,MainActivity.class)));

    }

    private void resetPassword(){
        String email = emailView.getText().toString().trim();

        if(email.isEmpty()){
            emailView.setError("Email is required");
            emailView.requestFocus();
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailView.setError("Please provide a valid email");
        }

        auth.sendPasswordResetEmail(email).addOnCompleteListener(task -> {

            if(task.isSuccessful()){
                Toast.makeText(ForgotPasswordActivity.this, "Check your email to reset your password", Toast.LENGTH_LONG).show();
                resetPasswordButton.setVisibility(View.INVISIBLE);
                goToLogin.setVisibility(View.VISIBLE);

            }else{
                Toast.makeText(ForgotPasswordActivity.this, "Something went wrong ! try again", Toast.LENGTH_LONG).show();
            }
        });
    }
}
package com.example.work_out_;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView forgotPassView,registerView;
    private EditText emailView, passView;
    private Button loginButton;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_login);

        loginButton = (Button) findViewById(R.id.login_button);
        loginButton.setOnClickListener(this);

        emailView = (EditText) findViewById(R.id.user_Name_Login);
        passView = (EditText) findViewById(R.id.user_Password);

        forgotPassView = (TextView) findViewById(R.id.forgotPassword);
        forgotPassView.setOnClickListener(this);

        registerView = (TextView) findViewById(R.id.nav_to_register);
        registerView.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_button:
                userLogin();
                break;

            case R.id.forgotPassword:
                startActivity(new Intent(LoginActivity.this,ForgotPasswordActivity.class));
                break;

            case R.id.nav_to_register:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
        }
    }

    private void userLogin() {
        String email = emailView.getText().toString().trim();
        String pass = passView.getText().toString().trim();

        if(email.isEmpty()){
            emailView.setError("email required!");
            emailView.requestFocus();
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailView.setError("invalid email");
            emailView.requestFocus();
        }

        if(pass.isEmpty()){
            passView.setError("pass request");
            passView.requestFocus();
        }

        mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(LoginActivity.this, ActivitiesActivity.class));
                }else{
                    Toast.makeText(LoginActivity.this, "Failed to login" , Toast.LENGTH_LONG).show();

                }
            }
        });
    }


}






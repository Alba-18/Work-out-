/*package com.example.work_out_;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
    }
}
 */
package com.example.work_out_;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText emailView, passView;

    private FirebaseAuth mAuth;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_login);

        Button loginButton = (Button) findViewById(R.id.login_button);
        loginButton.setOnClickListener(this);

        emailView = (EditText) findViewById(R.id.user_Name_Login);
        passView = (EditText) findViewById(R.id.user_Password);

        mAuth = FirebaseAuth.getInstance();


    }

    @Override
    protected void onStop() {
        super.onStop() ;
        SharedPreferences sharedPreferences = getSharedPreferences("appSettings", MODE_PRIVATE);
        if(sharedPreferences.getBoolean("notifications", false)){
            startService( new Intent( this, NotificationService.class ));
        }


    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login_button) {
            userLogin();
        }
    }
    //this method checks the user information and try to find the user in the realtime Database
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

        mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(task -> {
            if(task.isSuccessful()){

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                assert user != null;
                if(user.isEmailVerified()){
                    startActivity(new Intent(MainActivity.this,ProfileActivity.class));
                }
                else{
                    user.sendEmailVerification();
                    Toast.makeText(MainActivity.this, "Check your email to verify",Toast.LENGTH_SHORT).show();

                }

            }else{
                Toast.makeText(MainActivity.this, "Failed to login" , Toast.LENGTH_LONG).show();

            }
        });
    }
}





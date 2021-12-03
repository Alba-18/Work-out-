package com.example.work_out_;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String NAME_KEY = "Name";
    private static final String AGE_KEY = "Age";
    private static final String WEIGHT_KEY = "Weight";
    private static final String HEIGHT_KEY = "Height";
    private static final String LEVEL_OF_EXERCISE_KEY = "LevelofExercise";
    private static final String CARDIO_KEY = "Cardio";
    private static final String EXERCISE_IMPACT_KEY = "ExerciseImpact";
    private static final String OBJETIVE_KEY = "Objetive";
    private static final String EMAIL_KEY = "Email";
    private static final String PASSWORD_KEY = "Password";

    private Spinner levelOfExerciseSpinner;
    private Spinner cardioSpinner;
    private Spinner exerciseImpactSpinner;
    private Spinner objetiveSpinner;

    private EditText registerNameView;
    private EditText registerAgeView;
    private EditText registerWeightView;
    private EditText registerHeightView;
    private EditText registerEmailView;
    private EditText registerPasswordView;
    private EditText registerConfirmPasswordView;

    private String registerLevelOfExercise;
    private String registerName;
    private String registerAge;
    private String registerWeight;
    private String registerHeight;
    private String registerCardio;
    private String registerExerciseImpact;
    private String registerObjetive;
    private String registerEmail;
    private String registerPassword;
    private String registerConfirmPassword;

    private FirebaseAuth mAuth;
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.mAuth = FirebaseAuth.getInstance();

        //Creation of EditText Views

        this.registerEmailView = (EditText) findViewById(R.id.Register_Email);
        this.registerPasswordView = (EditText) findViewById(R.id.Register_Password);
        this.registerConfirmPasswordView = (EditText) findViewById(R.id.Register_ConfirmPassword);

        this.registerNameView = (EditText) findViewById(R.id.Register_Name);
        this.registerAgeView = (EditText) findViewById(R.id.Register_Age);
        this.registerWeightView = (EditText) findViewById(R.id.Register_Weight);
        this.registerHeightView = (EditText) findViewById(R.id.Register_Height);

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
                R.array.levelOfExerciseArray, android.R.layout.simple_spinner_item);
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
            case R.id.Register_Name:
                this.registerName = (String) parent.getItemAtPosition(pos);
                break;

            case R.id.Register_Password:
                this.registerPassword = (String) parent.getItemAtPosition(pos);
                break;

            case R.id.Register_ConfirmPassword:
                this.registerConfirmPassword = (String) parent.getItemAtPosition(pos);
                break;

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

    public void createAccount(View view){

        this.registerEmail = this.registerEmailView.getText().toString();
        this.registerPassword = this.registerPasswordView.getText().toString();

        this.mAuth.createUserWithEmailAndPassword(this.registerEmail, this.registerPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        this.registerName = this.registerNameView.getText().toString();
        this.registerAge =  this.registerAgeView.getText().toString();
        this.registerWeight =  this.registerWeightView.getText().toString();
        this.registerHeight =  this.registerHeightView.getText().toString();
        this.registerEmail = this.registerEmailView.getText().toString();
        this.registerPassword = this.registerPasswordView.getText().toString();

        CollectionReference mCollRef = db.collection("sampleData/users/" + this.registerEmail);
        DocumentReference mDocRef = mCollRef.document("data");

        Map<String,Object> dataToSave = new HashMap<String, Object>();
        dataToSave.put(NAME_KEY ,this.registerName);
        dataToSave.put(AGE_KEY,this.registerAge);
        dataToSave.put(WEIGHT_KEY,this.registerWeight);
        dataToSave.put(HEIGHT_KEY,this.registerHeight);

        dataToSave.put(LEVEL_OF_EXERCISE_KEY, this.registerLevelOfExercise);
        dataToSave.put(CARDIO_KEY, this.registerCardio);
        dataToSave.put(EXERCISE_IMPACT_KEY, this.registerExerciseImpact);
        dataToSave.put(OBJETIVE_KEY, this.registerObjetive);


        mDocRef.set(dataToSave).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Context context = getApplicationContext();
                CharSequence text = "Data saved";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Context context = getApplicationContext();
                CharSequence text = "CAGASTE";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
    }

    public boolean checkPassword(String pass, String confirmPass){
        return pass == confirmPass;
    }



}



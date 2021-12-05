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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class ProfileActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String NAME_KEY = "Name";
    private static final String AGE_KEY = "Age";
    private static final String WEIGHT_KEY = "Weight";
    private static final String HEIGHT_KEY = "Height";
    private static final String LEVEL_OF_EXERCISE_KEY = "LevelofExercise";
    private static final String CARDIO_KEY = "Cardio";
    private static final String EXERCISE_IMPACT_KEY = "ExerciseImpact";
    private static final String OBJETIVE_KEY = "Objetive";

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

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        this.mAuth = FirebaseAuth.getInstance();

        //Creation of EditText Views
        this.profileNameView = (EditText) findViewById(R.id.profileUserName);
        this.profileAgeView = (EditText) findViewById(R.id.profileAge);
        this.profileWeightView = (EditText) findViewById(R.id.profileWeight);
        this.profileHeightView = (EditText) findViewById(R.id.profileHeight);

        //Creation of Spinners
        //Creation of Profile Level of Exercise Spinner
        this.levelOfExerciseSpinner = (Spinner) findViewById(R.id.profileLevelOfExercise);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> levelOfExerciseAdapter = ArrayAdapter.createFromResource(this,
                R.array.levelOfExerciseArray, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        levelOfExerciseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        this.levelOfExerciseSpinner.setAdapter(levelOfExerciseAdapter);
        this.levelOfExerciseSpinner.setOnItemSelectedListener(this);

        //Creation of Cardio Spinner
        this.cardioSpinner = (Spinner) findViewById(R.id.profileCardio);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> cardioAdapter = ArrayAdapter.createFromResource(this,
                R.array.cardioArray, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        cardioAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        this.cardioSpinner.setAdapter(cardioAdapter);
        this.cardioSpinner.setOnItemSelectedListener(this);

        //Creation of Exercise Impact Spinner
        this.exerciseImpactSpinner = (Spinner) findViewById(R.id.profileExerciseImpact);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> exerciseImpactAdapter = ArrayAdapter.createFromResource(this,
                R.array.levelOfExerciseArray, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        cardioAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        this.exerciseImpactSpinner.setAdapter(exerciseImpactAdapter);
        this.exerciseImpactSpinner.setOnItemSelectedListener(this);

        //Creation of Objetive Spinner
        this.objetiveSpinner = (Spinner) findViewById(R.id.profileObjetive);
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

    public void saveQuote(View view){

        this.profileName = this.profileNameView.getText().toString();
        this.profileAge =  this.profileAgeView.getText().toString();
        this.profileWeight =  this.profileWeightView.getText().toString();
        this.profileHeight =  this.profileHeightView.getText().toString();

        CollectionReference mCollRef = db.collection("sampleData/users/" + profileName);
        DocumentReference mDocRef = mCollRef.document("data");

        Map<String,Object> dataToSave = new HashMap<String, Object>();
        dataToSave.put(NAME_KEY ,this.profileName);
        dataToSave.put(AGE_KEY,this.profileAge);
        dataToSave.put(WEIGHT_KEY,this.profileWeight);
        dataToSave.put(HEIGHT_KEY,this.profileHeight);

        dataToSave.put(LEVEL_OF_EXERCISE_KEY, this.profileLevelOfExercise);
        dataToSave.put(CARDIO_KEY, this.profileCardio);
        dataToSave.put(EXERCISE_IMPACT_KEY, this.profileExerciseImpact);
        dataToSave.put(OBJETIVE_KEY, this.profileObjetive);


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
}
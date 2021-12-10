package com.example.work_out_;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.work_out_.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ProfileActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    public static final int PICK_IMAGE = 1;

    private Spinner levelOfExerciseSpinner, cardioSpinner, exerciseImpactSpinner, objetiveSpinner;

    private EditText profileNameView;
    private EditText profileAgeView;
    private EditText profileWeightView;
    private EditText profileHeightView;

    private Uri profileImageUri;
    private ImageView profileImageView;

    private Button buttonUpdateProfile;
    private String profileLevelOfExercise, profileName, profileAge, profileWeight, profileHeight, profileCardio, profileExerciseImpact, profileObjetive;

    private User userProfile;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    private Button btn_open_popUp;
    private Button btn_close;
    private LayoutInflater layoutInflater;
    private View popupView;
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Button imageProfile = findViewById(R.id.editImageProfile);
        imageProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 3);
            }
        });


        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("users");
        userID = user.getUid();

        //Creation of EditText Views
        profileNameView = (EditText) findViewById(R.id.profileUserName);
        profileAgeView = (EditText) findViewById(R.id.profileAge);
        profileWeightView = (EditText) findViewById(R.id.profileWeight);
        profileHeightView = (EditText) findViewById(R.id.profileHeight);
        profileImageView = (ImageView) findViewById(R.id.profileImage);
        buttonUpdateProfile = findViewById(R.id.profileSaveButton);
        buttonUpdateProfile.setOnClickListener(this);

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
                R.array.exerciseImpact, android.R.layout.simple_spinner_item);
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


        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userProfile = snapshot.getValue(User.class);

                if(userProfile != null){
                    String fullName = userProfile.getName();
                    String age = userProfile.getAge();
                    String weight = userProfile.getWeight();
                    String height = userProfile.getHeight();
                    String levelOfExercise = userProfile.getLevelOfExercise();
                    String exerciseImpact = userProfile.getExerciseImpact();
                    String objetive = userProfile.getObjetive();
                    profileImageUri = userProfile.getImage();

                    profileImageView.setImageURI(profileImageUri);
                    profileNameView.setText(fullName);
                    profileAgeView.setText(age);
                    profileWeightView.setText(weight);
                    profileHeightView.setText(height);


                    switch (levelOfExercise){
                        case "Beginner":
                            levelOfExerciseSpinner.setSelection(1);
                            break;
                        case "Intermediate":
                            levelOfExerciseSpinner.setSelection(2);
                            break;
                        case "Advanced":
                            levelOfExerciseSpinner.setSelection(3);
                            break;
                    }

                    switch (exerciseImpact){
                        case "Low":
                            exerciseImpactSpinner.setSelection(1);
                            break;
                        case "High":
                            exerciseImpactSpinner.setSelection(2);
                            break;
                    }

                    switch (objetive){
                        case "7 days":
                            objetiveSpinner.setSelection(1);
                            break;
                        case "15 days":
                            objetiveSpinner.setSelection(2);
                            break;
                        case "30 days":
                            objetiveSpinner.setSelection(3);
                            break;
                    }

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

        btn_open_popUp = (Button)findViewById(R.id.helpProfile);
        btn_open_popUp.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View arg0){
                layoutInflater =(LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                popupView = layoutInflater.inflate(R.layout.activity_profile_popup, null);
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Hay que checkear el tamaño de la imagen
        if(resultCode == RESULT_OK && data != null ){
            Uri selectedImage = data.getData();
            ImageView imageView = findViewById(R.id.profileImage);
            imageView.setImageURI(selectedImage);
            userProfile.setImage(selectedImage);
            reference.child(userID).setValue(userProfile);
            //imageView.setBackground(Drawable.createFromPath("#B2BE50"));

        }

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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.profileSaveButton:
                saveUser();
                break;
            case R.id.helpProfile:
                startActivity(new Intent(ProfileActivity.this,ActivitiesActivity.class));
                break;
        }
    }

    private void saveUser() {
        userProfile.setAge(profileAgeView.getText().toString().trim());
        userProfile.setWeight(profileWeightView.getText().toString().trim());
        userProfile.setHeight(profileHeightView.getText().toString().trim());
        userProfile.setName(profileNameView.getText().toString().trim());
        userProfile.setLevelOfExercise(profileLevelOfExercise);
        userProfile.setCardio(profileCardio);
        userProfile.setExerciseImpact(profileExerciseImpact);
        userProfile.setCardio(profileCardio);

        reference.child(userID).setValue(userProfile);
    }
}
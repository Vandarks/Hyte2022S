package com.example.ravintosovellushyte2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

/*
"USER_NAME" Is the key where user name is stored
"USER_AGE" Is the key where user age is stored
"USER_SEX" Is the key where user sex is stored
 */

public class UserSettingsActivity extends AppCompatActivity {

    private SharedPreferences sharedPref;
    private SharedPreferences.Editor sharedEdit;

    private EditText nameInput;
    private EditText ageInput;
    private RadioGroup sexInputGroup;
    private Button saveButton;
    private Button clearSaveButton;

    public static final String GENERAL_PREFS = "RavintoSovellusHYTE2022General"; //Static name for the shared prefs file for general settings

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);

        //Creating access to shared preferences (General)
        sharedPref = getApplicationContext().getSharedPreferences(GENERAL_PREFS, MODE_PRIVATE);
        sharedEdit = sharedPref.edit();

        //Initializing views
        nameInput = (EditText) findViewById(R.id.nameInput);
        ageInput = (EditText) findViewById(R.id.ageInput);
        sexInputGroup = (RadioGroup) findViewById(R.id.sexGroup);
        saveButton = (Button) findViewById(R.id.saveButton);
        clearSaveButton = (Button) findViewById(R.id.clearSaveButton);

        updateUI();
    }

    public void SaveData(View view){ //TODO: Check that user doesn't try to save empty name or age
        //Storing user inputted values
        String userName = nameInput.getText().toString();
        String userAge = ageInput.getText().toString();
        int userSex = sexInputGroup.getCheckedRadioButtonId(); //example: (R.id.maleButton) is an int

        //Saving those values to sharedPreferences
        sharedEdit.putString("USER_NAME", userName);
        sharedEdit.putString("USER_AGE", userAge);
        //Switch case comparing radiobutton ID's to choose if male or female is checked
        switch (userSex){
            case R.id.maleBox: sharedEdit.putString("USER_SEX", "Male");
                break;
            case R.id.femaleBox: sharedEdit.putString("USER_SEX", "Female");
                break;
            default:
                Toast.makeText(this, "Something went wrong with saving user sex", Toast.LENGTH_SHORT).show();
        }
        //Success! apply, message and return to previous activity
        sharedEdit.apply();
        Toast.makeText(this, "Data Saved!", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void clearSaveData(View view){
        //LOG deleted data
        Log.d("ClearedName:", sharedPref.getString("USER_NAME", "none"));
        Log.d("ClearedAge:", sharedPref.getString("USER_AGE", "none"));
        Log.d("ClearedSex:", sharedPref.getString("USER_SEX", "none"));
        //Clear and apply
        sharedEdit.clear();
        sharedEdit.apply();
        Toast.makeText(this, "Data Cleared!", Toast.LENGTH_SHORT).show();
        updateUI(); //Updates UI
    }

    public void updateUI(){
        nameInput.setHint(sharedPref.getString("USER_NAME", "Name"));
        ageInput.setHint(sharedPref.getString("USER_AGE", "Age"));
    }
}
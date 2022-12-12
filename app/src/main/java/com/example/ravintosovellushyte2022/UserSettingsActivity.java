package com.example.ravintosovellushyte2022;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);

        //Creating access to shared preferences (General)
        sharedPref = getApplicationContext().getSharedPreferences(MainActivity.GENERAL_PREFS, MODE_PRIVATE);
        sharedEdit = sharedPref.edit();

        //Initializing views
        nameInput = (EditText) findViewById(R.id.nameInput);
        ageInput = (EditText) findViewById(R.id.ageInput);
        sexInputGroup = (RadioGroup) findViewById(R.id.sexGroup);

        updateUI();
    }

    /**
     * Tallentaa henkilön tiedot
     * @param view Save data nappi
     */
    public void SaveData(View view){

        //Get user inputted values
        String userName = nameInput.getText().toString();
        String userAge = ageInput.getText().toString();
        int userSex = sexInputGroup.getCheckedRadioButtonId(); //example: (R.id.maleButton) is an int

        //Saving those values to sharedPreferences
        if(!userName.matches("")) {
            sharedEdit.putString("USER_NAME", userName);
        }
        if(!userAge.matches("")) {
            sharedEdit.putString("USER_AGE", userAge);
        }
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
        sharedEdit.putBoolean("FIRST_TIME_SETUP", false);
        sharedEdit.apply();
        if(sharedPref.getString("USER_NAME", "") == "" || sharedPref.getString("USER_AGE", "") == "" || sharedPref.getString("USER_SEX", "") ==  "" ) { //Checks for missing user information
            Toast.makeText(this, "Missing name/age!", Toast.LENGTH_SHORT).show();
            sharedEdit.clear();
            sharedEdit.apply();
        } else {
            Toast.makeText(this, "Data Saved!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    /**
     * Clears all profile data
     * @param view Clear saved button
     */
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

    /**
     * Updates visuals
     */
    public void updateUI(){
        //Updates hint messages to be currently saved values
        nameInput.setHint(sharedPref.getString("USER_NAME", "Name"));
        ageInput.setHint(sharedPref.getString("USER_AGE", "Age"));
    }
}
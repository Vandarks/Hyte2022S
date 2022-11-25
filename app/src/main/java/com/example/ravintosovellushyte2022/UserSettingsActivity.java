package com.example.ravintosovellushyte2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class UserSettingsActivity extends AppCompatActivity {

    private SharedPreferences sharedPref;
    private SharedPreferences.Editor sharedEdit;

    private EditText nameInput;
    private EditText ageInput;
    private RadioGroup sexInput;
    private Button saveButton;

    public static final String GENERAL_PREFS = "RavintoSovellusHYTE2022General"; //Static name for the shared prefs file for general settings

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);

        sharedPref = getSharedPreferences(GENERAL_PREFS, MODE_PRIVATE);
        sharedEdit = sharedPref.edit();

        //Initializing views
        nameInput = (EditText) findViewById(R.id.nameInput);
        ageInput = (EditText) findViewById(R.id.ageInput);
        sexInput = (RadioGroup) findViewById(R.id.sexGroup);
        saveButton = (Button) findViewById(R.id.saveButton);

    }

    public void SaveData(View view){
        //TODO: Save the inputted data
        Toast.makeText(this, "Data Saved!", Toast.LENGTH_SHORT).show();
        finish();
    }
}
package com.example.ravintosovellushyte2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPref; //Object to read sharedPreferences
    private SharedPreferences.Editor sharedEdit; //Object to write to sharedPreferences

    public static final String GENERAL_PREFS = "RavintoSovellusHYTE2022General"; //Static name for the shared prefs file for general settings

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPref = getSharedPreferences(GENERAL_PREFS, MODE_PRIVATE); //Accessing shared preferences
        sharedEdit = sharedPref.edit(); //Editor for writing to general sharedPreferences

        //Check for first time launch
        if(sharedPref.getBoolean("FIRST_LAUNCH", true)){
            firstTimeSetup();
        }
    }

    public void firstTimeSetup(){
        TextView txt = findViewById(R.id.textView);
        txt.setText("First time user setup required");
        //TODO:First time setup to get required user data then change "FIRST_LAUNCH" false
        //First time setup. New intent -> activity
        //Gather user name, age, sex. Save data and change "FIRST_LAUNCH" false
    }

    // Onnin kommentti, kirjoitettu 24.11.22 @11:33
    //Tapsan kommetti 24.11.22 @13:15
}
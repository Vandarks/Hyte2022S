package com.example.ravintosovellushyte2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPref; //Object to read sharedPreferences
    private SharedPreferences.Editor sharedEdit; //Object to write to sharedPreferences

    public static final String GENERAL_PREFS = "HyteSovellusGeneral"; //Static name for the shared prefs file for general settings
    public static final String NUTRITION_PREFS = "HyteSovellusNutrition";

    private NutritionTracker nutritionTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPref = getApplicationContext().getSharedPreferences(GENERAL_PREFS, MODE_PRIVATE); //Accessing shared preferences
        sharedEdit = sharedPref.edit(); //Editor for writing to general sharedPreferences

        //Create nutritionTracker
        nutritionTracker = new NutritionTracker(getApplicationContext());

        //Check for first time launch TODO: Make a way to only do first time setup when necessary (Missing important userinfo for example)
        firstTimeSetup();

        //TODO: Remove when making UI
        TextView txt = findViewById(R.id.textView);
        TextView caloriesTxt = findViewById(R.id.calories);
        TextView carbsTxt = findViewById(R.id.carbs);
        TextView fatsTxt = findViewById(R.id.fats);
        TextView saltsTxt = findViewById(R.id.salts);

        txt.setText(nutritionTracker.getDate());
        caloriesTxt.setText("Calories: " + Float.toString(nutritionTracker.getCalories()) + " grams");
        carbsTxt.setText("Carbs: " + Float.toString(nutritionTracker.getCarbs()) + " grams");
        fatsTxt.setText("Fats: " + Float.toString(nutritionTracker.getFats()) + " grams");
        saltsTxt.setText("Salts: " + Float.toString(nutritionTracker.getSalts()) + " grams");

    }

    public void firstTimeSetup(){
        Intent intent = new Intent(MainActivity.this, UserSettingsActivity.class);
        startActivity(intent);
    }

    // Onnin kommentti, kirjoitettu 24.11.22 @11:33
    //Tapsan kommetti 24.11.22 @13:15
}
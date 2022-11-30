package com.example.ravintosovellushyte2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
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
        firstTimeSetup(); //Goes to the user settings activity

        updateUI();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //Updates UI when coming back
        updateUI();
    }

    public void firstTimeSetup(){
        //Goes to the user settings activity
        Intent intent = new Intent(MainActivity.this, UserSettingsActivity.class);
        startActivity(intent);
    }

    public void updateUI(){
        //Updates nutritional tracker values from NUTRITION_PREFS
        nutritionTracker.updateFromSavedData();

        //Finds relevant UI element ID's
        TextView dateTxt = findViewById(R.id.datetext);
        TextView caloriesTxt = findViewById(R.id.calories);
        TextView carbsTxt = findViewById(R.id.carbs);
        TextView fatsTxt = findViewById(R.id.fats);
        TextView saltsTxt = findViewById(R.id.salts);

        //Update the contents of the relevant UI elements
        dateTxt.setText(nutritionTracker.getDate());
        caloriesTxt.setText("Calories: " + Float.toString(nutritionTracker.getCalories()));
        carbsTxt.setText("Carbs: " + Float.toString(nutritionTracker.getCarbs()) + " g");
        fatsTxt.setText("Fats: " + Float.toString(nutritionTracker.getFats()) + " g");
        saltsTxt.setText("Salts: " + Float.toString(nutritionTracker.getSalts()) + " g");
    }

    public void addCustomFood(View v){
        //Starts the add custom food activity
        Intent intent = new Intent(MainActivity.this, AddCustomFoodActivity.class);
        MainActivity.this.startActivity(intent);
    }

    // Onnin kommentti, kirjoitettu 24.11.22 @11:33
    //Tapsan kommetti 24.11.22 @13:15
}
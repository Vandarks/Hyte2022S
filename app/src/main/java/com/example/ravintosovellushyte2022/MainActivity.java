package com.example.ravintosovellushyte2022;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Calendar;
import java.text.SimpleDateFormat;

/**
 * Main class of the app
 * @Author Miiko Majewski
 */
public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPref; //Object to read sharedPreferences
    private SharedPreferences.Editor sharedEdit; //Object to write to sharedPreferences

    public static final String GENERAL_PREFS = "HyteSovellusGeneral"; //Static name for the shared prefs file for general settings
    public static final String NUTRITION_PREFS = "HyteSovellusNutrition";
    public static final String MEAL_PREFS = "HyteSovellusMeal"; //depricated since 14.12.2022 @02:30
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;

    private float caloriesMax, carbsMax, fatsMax, saltsMax;

    private NutritionTracker nutritionTracker;

    /**
     * This function is called when the app starts.
     * @param savedInstanceState Previously saved instance of the app's data
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        sharedPref = getApplicationContext().getSharedPreferences(GENERAL_PREFS, MODE_PRIVATE); //Accessing shared preferences
        sharedEdit = sharedPref.edit(); //Editor for writing to general sharedPreferences



        //Create nutritionTracker
        nutritionTracker = new NutritionTracker(getApplicationContext());

        //Check for first time launch
        if(sharedPref.getBoolean("FIRST_TIME_SETUP", true)) {
            firstTimeSetup(); //Goes to the user settings activity
        }

        updateUI();
    }

    /**
     * Resumes the app and calls updateUI
     */
    @Override
    protected void onResume() {
        super.onResume();
        //Updates UI and Nutrition tracker when coming back
        updateUI();
    }

    /**
     * Calls firstTimeSetup from button
     * @param v Settings button
     */
    public void goToSettings(View v) {
        firstTimeSetup();
    }

    /**
     * Starts UserSettingsActivity
     */
    public void firstTimeSetup(){
        //Goes to the user settings activity
        Intent intent = new Intent(MainActivity.this, UserSettingsActivity.class);
        startActivity(intent);
    }

    /**
     * Starts AddCustomFoodActivity and changes it into edit mode
     * @param v Edit meal button
     */
    public void editMeal(View v) {
        Intent intent = new Intent(MainActivity.this, AddCustomFoodActivity.class);
        intent.putExtra("EDIT_MEAL", true);
        startActivity(intent);
    }

    /**
     * Refreshes all elements and their data from saved preferences
     */
    public void updateUI(){
        //Updates nutritional tracker values from NUTRITION_PREFS

        nutritionTracker.updateFromSavedData();
        date = nutritionTracker.getDate();
        String sex = sharedPref.getString("USER_SEX", "Male");
        if (sex.equals("Male")){
            caloriesMax = 2500.00f;
            carbsMax = 125.00f;
            fatsMax = 80.00f;
            saltsMax = 5.00f;
        } else if (sex.equals("Female")) {
            caloriesMax = 2000.00f;
            carbsMax = 100.00f;
            fatsMax = 65.00f;
            saltsMax = 5.00f;
        }
        sharedEdit.putFloat("MAXCAL", caloriesMax);
        sharedEdit.putFloat("MAXCARBS", carbsMax);
        sharedEdit.putFloat("MAXFATS", fatsMax);
        sharedEdit.putFloat("MAXSALTS", saltsMax);
        sharedEdit.apply();

        float calper = (nutritionTracker.getCalories()/sharedPref.getFloat("MAXCAL", 1)) * 100;
        float carbsper = (nutritionTracker.getCarbs()/sharedPref.getFloat("MAXCARBS", 1))*100;
        float fatsper = (nutritionTracker.getFats()/sharedPref.getFloat("MAXFATS", 1)) * 100;
        float saltsper = (nutritionTracker.getSalts()/sharedPref.getFloat("MAXSALTS", 1))*100;

        float cal = nutritionTracker.getCalories();
        float carbs = nutritionTracker.getCarbs();
        float fats = nutritionTracker.getFats();
        float salts = nutritionTracker.getSalts();
        //Finds relevant UI element ID's
        TextView nameTxt = findViewById(R.id.greeting);
        TextView dateTxt = findViewById(R.id.datetext);
        TextView caloriesTxt = findViewById(R.id.calories);
        TextView fatsTxt = findViewById(R.id.fats);
        TextView carbsTxt = findViewById(R.id.carbs);
        TextView saltsTxt = findViewById(R.id.salts);
        TextView caloriesPercent = findViewById(R.id.calPercent);
        TextView carbsPercent = findViewById(R.id.carbsPercent);
        TextView fatsPercent = findViewById(R.id.fatsPercent);
        TextView saltsPercent = findViewById(R.id.saltsPercent);
        TextView mealCounter = findViewById(R.id.mealCounter);
        ImageView alertImage = findViewById(R.id.alertImage);
        ProgressBar calBar = findViewById(R.id.calBar);
        ProgressBar carbsBar = findViewById(R.id.carbsBar);
        ProgressBar fatsBar = findViewById(R.id.fatsBar);
        ProgressBar saltsBar = findViewById(R.id.saltsBar);

        //Update the contents of the relevant UI elements
        nameTxt.setText("Hello, " + sharedPref.getString("USER_NAME", "friend") + "!");
        dateTxt.setText(nutritionTracker.getDate());
        caloriesTxt.setText("Calories: " + String.format("%.2f", cal));
        carbsTxt.setText("Carbs: " + String.format("%.2f", carbs) + " g");
        fatsTxt.setText("Fats: " + String.format("%.2f", fats) + " g");
        saltsTxt.setText("Salts: " + String.format("%.2f", salts) + " g");
        caloriesPercent.setText(String.format("%.2f",calper) + " %");
        carbsPercent.setText(String.format("%.2f",carbsper) + " %");
        fatsPercent.setText(String.format("%.2f",fatsper) + " %");
        saltsPercent.setText(String.format("%.2f",saltsper) + " %");
        calBar.setProgress(Math.round(calper));
        carbsBar.setProgress(Math.round(carbsper));
        fatsBar.setProgress(Math.round(fatsper));
        saltsBar.setProgress(Math.round(saltsper));
        mealCounter.setText(nutritionTracker.getTimesEaten() + "/5");

        if(nutritionTracker.getTimesEaten() > 5){
            alertImage.setAlpha(1f);
        } else {
            alertImage.setAlpha(0f);
        }

    }

    /**
     * Starts AddCustomFoodActivity and changes edit mode off
     * @param v Add Meal button
     */
    public void addCustomFood(View v){
        //Starts the add custom food activity
        Intent intent = new Intent(MainActivity.this, AddCustomFoodActivity.class);
        intent.putExtra("EDIT_MEAL", false);
        MainActivity.this.startActivity(intent);
    }

    /**
     * Starts SavedMealsActivity
     * @param v Saved Meals button
     */
    public void savedMeals(View v) {
        Intent intent = new Intent(MainActivity.this, SavedMealsActivity.class);
        MainActivity.this.startActivity(intent);
    }

    /**
     * Starts the HistoryActivity
     * @param v History button
     */
    public void goToHistory(View v){
        Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
        MainActivity.this.startActivity(intent);
    }
}
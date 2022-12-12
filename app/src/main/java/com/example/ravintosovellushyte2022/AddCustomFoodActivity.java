package com.example.ravintosovellushyte2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * AddCustomFoodActivity is responsible for both adding custom nutritional values and editing previously added nutritional values
 * @author Miiko Majewski
 * @version 1.1 12/2022
 */
public class AddCustomFoodActivity extends AppCompatActivity {

    private Button addButton;
    private EditText gramsInput;
    private EditText caloriesInput;
    private EditText carbsInput;
    private EditText fatsInput;
    private EditText saltsInput;
    private Button removeMealButton;
    private Button clearTodayButton;
    private Button editLastButton;


    private NutritionTracker nutritionTracker;

    /**
     * This function is called when the app starts.
     * @param savedInstanceState Previously saved instance of the app's data
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_custom_food);

        //Finding relevant UI elements by ID
        addButton = findViewById(R.id.addfood);
        gramsInput = findViewById(R.id.gramsInput);
        caloriesInput = findViewById(R.id.caloriesInput);
        carbsInput = findViewById(R.id.carbsInput);
        fatsInput = findViewById(R.id.fatsInput);
        saltsInput = findViewById(R.id.saltsInput);
        removeMealButton = findViewById(R.id.removeMealButton);
        clearTodayButton = findViewById(R.id.cleartoday);
        editLastButton = findViewById(R.id.editLastButton);

        //New nutrition tracker to calculate food stuff
        nutritionTracker = new NutritionTracker(getApplicationContext());

        Bundle b = getIntent().getExtras();
        boolean edit = b.getBoolean("EDIT_MEAL", false);
        boolean premade = b.getBoolean("PREMADE", false);
        if (edit){
            float grams = nutritionTracker.getPreviousGrams();
            float calories = nutritionTracker.getPreviousCalories();
            float carbs = nutritionTracker.getPreviousCarbs();
            float fats = nutritionTracker.getPreviousFats();
            float salts = nutritionTracker.getPreviousSalts();
            gramsInput.setText(String.format("%.2f", grams));
            caloriesInput.setText(String.format("%.2f", calories));
            carbsInput.setText(String.format("%.2f", carbs));
            fatsInput.setText(String.format("%.2f", fats));
            saltsInput.setText(String.format("%.2f", salts));

            removeMealButton.setAlpha(1f);
            removeMealButton.setEnabled(true);
            editLastButton.setAlpha(1f);
            editLastButton.setEnabled(true);
            addButton.setAlpha(0f);
            addButton.setEnabled(false);
            clearTodayButton.setAlpha(0f);
            clearTodayButton.setEnabled(false);
        } else if (premade) {

            float grams = 0;
            float calories = b.getFloat("CALORIES", 0);
            float carbs = b.getFloat("CARBS", 0);
            float fats = b.getFloat("FATS", 0);
            float salts = b.getFloat("SALTS", 0);
            gramsInput.setHint("0");
            caloriesInput.setText(String.format("%.2f", calories));
            carbsInput.setText(String.format("%.2f", carbs));
            fatsInput.setText(String.format("%.2f", fats));
            saltsInput.setText(String.format("%.2f", salts));

            removeMealButton.setAlpha(0f);
            removeMealButton.setEnabled(false);
            editLastButton.setAlpha(0f);
            editLastButton.setEnabled(false);
            addButton.setAlpha(1f);
            addButton.setEnabled(true);
            clearTodayButton.setAlpha(1f);
            clearTodayButton.setEnabled(true);
        } else {

            removeMealButton.setAlpha(0f);
            removeMealButton.setEnabled(false);
            editLastButton.setAlpha(0f);
            editLastButton.setEnabled(false);
            addButton.setAlpha(1f);
            addButton.setEnabled(true);
            clearTodayButton.setAlpha(1f);
            clearTodayButton.setEnabled(true);
        }
    }

    /**
     * Saves the nutritional data to the object and returns from this activity
     * @param v Add food button
     */
    public void saveData(View v){
        //Values to save
        float grams;
        float calories;
        float carbs;
        float fats;
        float salts;

        //Getting user inputted values. If empty, defaulting to 0
        if(gramsInput.getText().toString().matches("")){
            Toast.makeText(this, "Invalid grams value", Toast.LENGTH_SHORT).show();
            return;
        }else{
            grams = Float.parseFloat(gramsInput.getText().toString());
        }
        if(caloriesInput.getText().toString().matches("")){
            calories = 0;
        }else{
            calories = Float.parseFloat(caloriesInput.getText().toString());
        }
        if(carbsInput.getText().toString().matches("")){
            carbs = 0;
        }else{
            carbs = Float.parseFloat(carbsInput.getText().toString());
        }
        if(fatsInput.getText().toString().matches("")){
            fats = 0;
        }else{
            fats = Float.parseFloat(fatsInput.getText().toString());
        }
        if(saltsInput.getText().toString().matches("")){
            salts = 0;
        }else{
            salts = Float.parseFloat(saltsInput.getText().toString());
        }

        //Passing values to nutritionTracker to calculate.
        nutritionTracker.addNutritions(grams, calories, carbs, fats, salts);
        Toast.makeText(this, "Food added to today's total", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(AddCustomFoodActivity.this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Calls nutritionTracker class' clearToday and returns from this activity
     * @param v Clear Today button
     */
    public void clearToday(View v){
        nutritionTracker.clearToday();
        Toast.makeText(this, "Today's nutritional values cleared", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void removeMeal(View v){
        float grams;
        float calories;
        float carbs;
        float fats;
        float salts;

        //Getting user inputted values. If empty, defaulting to 0
        if(gramsInput.getText().toString().matches("")){
            Toast.makeText(this, "Invalid grams value", Toast.LENGTH_SHORT).show();
            return;
        }else{
            grams = Float.parseFloat(gramsInput.getText().toString());
        }
        if(caloriesInput.getText().toString().matches("")){
            calories = 0;
        }else{
            calories = Float.parseFloat(caloriesInput.getText().toString());
        }
        if(carbsInput.getText().toString().matches("")){
            carbs = 0;
        }else{
            carbs = Float.parseFloat(carbsInput.getText().toString());
        }
        if(fatsInput.getText().toString().matches("")){
            fats = 0;
        }else{
            fats = Float.parseFloat(fatsInput.getText().toString());
        }
        if(saltsInput.getText().toString().matches("")){
            salts = 0;
        }else{
            salts = Float.parseFloat(saltsInput.getText().toString());
        }
        nutritionTracker.removeLastMeal(grams, calories, carbs, fats, salts);
        Toast.makeText(this, "Last meal has been cleared", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void editFood(View v){
        float grams;
        float calories;
        float carbs;
        float fats;
        float salts;

        //Getting user inputted values. If empty, defaulting to 0
        if(gramsInput.getText().toString().matches("")){
            Toast.makeText(this, "Invalid grams value", Toast.LENGTH_SHORT).show();
            return;
        }else{
            grams = Float.parseFloat(gramsInput.getText().toString());
        }
        if(caloriesInput.getText().toString().matches("")){
            calories = 0;
        }else{
            calories = Float.parseFloat(caloriesInput.getText().toString());
        }
        if(carbsInput.getText().toString().matches("")){
            carbs = 0;
        }else{
            carbs = Float.parseFloat(carbsInput.getText().toString());
        }
        if(fatsInput.getText().toString().matches("")){
            fats = 0;
        }else{
            fats = Float.parseFloat(fatsInput.getText().toString());
        }
        if(saltsInput.getText().toString().matches("")){
            salts = 0;
        }else{
            salts = Float.parseFloat(saltsInput.getText().toString());
        }

        nutritionTracker.editNutritions(grams, calories, carbs, fats, salts);
        Toast.makeText(this, "Previous meal edited", Toast.LENGTH_SHORT).show();
        finish();
    }


    public void fuckGoBack(View v) {
        Intent intent = new Intent(AddCustomFoodActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
package com.example.ravintosovellushyte2022;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddCustomFoodActivity extends AppCompatActivity {

    private Button addButton;
    private EditText gramsInput;
    private EditText caloriesInput;
    private EditText carbsInput;
    private EditText fatsInput;
    private EditText saltsInput;

    private NutritionTracker nutritionTracker;

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

        //New nutrition tracker to calculate food stuff
        nutritionTracker = new NutritionTracker(getApplicationContext());
    }

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
        //Saving values to NUTRITION_PREFS
        nutritionTracker.saveNutritions();
        Toast.makeText(this, "Food added to today's total", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void clearToday(View v){
        nutritionTracker.clearToday();
        Toast.makeText(this, "Today's nutritional values cleared", Toast.LENGTH_SHORT).show();
        finish();
    }
}
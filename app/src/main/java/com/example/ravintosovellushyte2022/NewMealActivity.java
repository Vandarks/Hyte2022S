package com.example.ravintosovellushyte2022;

import static com.example.ravintosovellushyte2022.MainActivity.MEAL_PREFS;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class NewMealActivity extends AppCompatActivity {

    private MealList mealList;

    private Button addButton;
    private EditText mealNameInput;
    private EditText caloriesInput;
    private EditText carbsInput;
    private EditText fatsInput;
    private EditText saltsInput;
    private Button cancelButton;


    private SharedPreferences sharedPref; //Object to read sharedPreferences
    private SharedPreferences.Editor sharedEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_meal);


        sharedPref = getApplicationContext().getSharedPreferences(MEAL_PREFS, MODE_PRIVATE);
        sharedEdit = sharedPref.edit(); //Editor for writing to general sharedPreferences


        addButton = findViewById(R.id.addfood);
        mealNameInput = findViewById(R.id.mealNameInput);
        caloriesInput = findViewById(R.id.caloriesInput);
        carbsInput = findViewById(R.id.carbsInput);
        fatsInput = findViewById(R.id.fatsInput);
        saltsInput = findViewById(R.id.saltsInput);
        cancelButton = findViewById(R.id.btnCancel);

        addButton.setOnClickListener(view ->
                savePremadeNutritions()
        );

    }


    public void savePremadeNutritions() {
        //Values to save
        String name;
        float calories;
        float carbs;
        float fats;
        float salts;

        if (mealNameInput.getText().toString().matches("")) {
            name = "Custom food";
        } else {
            name = caloriesInput.getText().toString();
        }
        if (caloriesInput.getText().toString().matches("")) {
            calories = 0;
        } else {
            calories = Float.parseFloat(caloriesInput.getText().toString());
        }
        if (carbsInput.getText().toString().matches("")) {
            carbs = 0;
        } else {
            carbs = Float.parseFloat(carbsInput.getText().toString());
        }
        if (fatsInput.getText().toString().matches("")) {
            fats = 0;
        } else {
            fats = Float.parseFloat(fatsInput.getText().toString());
        }
        if (saltsInput.getText().toString().matches("")) {
            salts = 0;
        } else {
            salts = Float.parseFloat(saltsInput.getText().toString());
        }
        sharedEdit.putString(name+"premadeName", name);
        sharedEdit.putFloat(name + "premadeCalories", calories);
        sharedEdit.putFloat(name + "premadeCarbs", carbs);
        sharedEdit.putFloat(name + "premadeFats", fats);
        sharedEdit.putFloat(name + "premadeSalts", salts);
        sharedEdit.apply();
        Log.d("Debug", "Called savePremadeNutritions");
        mealList.saveToList(name);
    }
}
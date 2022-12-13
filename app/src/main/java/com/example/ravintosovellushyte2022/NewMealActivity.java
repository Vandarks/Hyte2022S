package com.example.ravintosovellushyte2022;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NewMealActivity extends AppCompatActivity {

    private EditText mealNameInput, caloriesInput, fatsInput, carbsInput, saltsInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_meal2);
        initWidgets();

    }

    private void initWidgets() {

        mealNameInput = findViewById(R.id.mealNameInput);
        caloriesInput = findViewById(R.id.caloriesInput);
        fatsInput = findViewById(R.id.fatsInput);
        carbsInput = findViewById(R.id.carbsInput);
        saltsInput = findViewById(R.id.saltsInput);
    }

    public void addMeal(View view){
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        String name = String.valueOf(mealNameInput.getText());
        float calories = Float.parseFloat(String.valueOf(caloriesInput.getText()));
        float fats = Float.parseFloat(String.valueOf(fatsInput.getText()));
        float carbs = Float.parseFloat(String.valueOf(carbsInput.getText()));
        float salts = Float.parseFloat(String.valueOf(saltsInput.getText()));
        int id = MealList.getInstance().getSize();

        Meal newMeal = new Meal(id, name, calories, fats, carbs, salts);
        MealList.getInstance().addMeal(newMeal);
        sqLiteManager.addFoodToDatabase(newMeal);
        finish();
    }

    public void cancel(View view){
        finish();
    }
}
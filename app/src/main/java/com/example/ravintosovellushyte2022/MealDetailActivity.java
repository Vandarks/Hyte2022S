package com.example.ravintosovellushyte2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MealDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_detail);

        Bundle b = getIntent().getExtras();
        int i = b.getInt(SavedMealsActivity.EXTRA, 0);

        TextView mealName = findViewById(R.id.mealName);
        TextView mealCalories = findViewById(R.id.mealCalories);
        TextView mealFats = findViewById(R.id.mealFats);
        TextView mealCarbs = findViewById(R.id.mealCarbs);
        TextView mealSalts = findViewById(R.id.mealSalts);

        String name = MealList.getInstance().getMeal(i).getName();
        String calories = Float.toString(MealList.getInstance().getMeal(i).getCalories()) + " kcal";
        String fats = Float.toString(MealList.getInstance().getMeal(i).getFats()) + " g";
        String carbs = Float.toString(MealList.getInstance().getMeal(i).getCarbs()) + " g";
        String salts = Float.toString(MealList.getInstance().getMeal(i).getSalts()) + " g";

        mealName.setText(name);
        mealCalories.setText(calories);
        mealFats.setText(fats);
        mealCarbs.setText(carbs);
        mealSalts.setText(salts);


    }

    public void fuckGoBack(View v) {
        finish();
    }

    public void addFood(View v) {
        Intent intent = new Intent(MealDetailActivity.this, AddCustomFoodActivity.class);

        intent.putExtra("PREMADE", true);
        startActivity(intent);
    }

}
package com.example.ravintosovellushyte2022;

import static com.example.ravintosovellushyte2022.MainActivity.MEAL_PREFS;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * MealDetail page
 * @author Onni Alasaari
 * @version 1.1 12/2022
 */
public class MealDetailActivity extends AppCompatActivity {

    private SharedPreferences sharedPref;
    private SharedPreferences.Editor sharedEdit;
    private int i;
    /**
     * This function is called when the app starts.
     * @param savedInstanceState Previously saved instance of the app's data
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_detail);

        sharedPref = getApplicationContext().getSharedPreferences(MEAL_PREFS, MODE_PRIVATE);
        sharedEdit = sharedPref.edit();

        Bundle b = getIntent().getExtras();
        i = b.getInt(SavedMealsActivity.EXTRA, 0);

        TextView mealName = findViewById(R.id.mealName);
        TextView mealCalories = findViewById(R.id.mealCalories);
        TextView mealFats = findViewById(R.id.mealFats);
        TextView mealCarbs = findViewById(R.id.mealCarbs);
        TextView mealSalts = findViewById(R.id.mealSalts);

        String name = MealList.getInstance().getMeal(i).getName();
        String calories = "Calories: " + Float.toString(MealList.getInstance().getMeal(i).getCalories()) + " kcal";
        String fats = "Fats: " + Float.toString(MealList.getInstance().getMeal(i).getFats()) + " g";
        String carbs = "Carbs: " +Float.toString(MealList.getInstance().getMeal(i).getCarbs()) + " g";
        String salts = "Salts: " + Float.toString(MealList.getInstance().getMeal(i).getSalts()) + " g";

        mealName.setText(name);
        mealCalories.setText(calories);
        mealFats.setText(fats);
        mealCarbs.setText(carbs);
        mealSalts.setText(salts);


    }

    /**
     * Returns to the previous activity
     * @param v Back button
     */
    public void fuckGoBack(View v) {
        finish();
    }

    /**
     * Takes the nutritional values of the food and auto-fills them to the AddCustomFoodActivity elements, then starts said activity
     * @param v Add button
     */
    public void addFood(View v) {
        Intent intent = new Intent(MealDetailActivity.this, AddCustomFoodActivity.class);

        intent.putExtra("PREMADE", true);
        intent.putExtra("CALORIES", MealList.getInstance().getMeal(i).getCalories());
        intent.putExtra("FATS", MealList.getInstance().getMeal(i).getFats());
        intent.putExtra("CARBS", MealList.getInstance().getMeal(i).getCarbs());
        intent.putExtra("SALTS", MealList.getInstance().getMeal(i).getSalts());
        startActivity(intent);
    }

}
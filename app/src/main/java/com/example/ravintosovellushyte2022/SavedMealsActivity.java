package com.example.ravintosovellushyte2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SavedMealsActivity extends AppCompatActivity {

    public static final String EXTRA ="mealIndex";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_meals);

        ListView lv = findViewById(R.id.mealListView);

        lv.setAdapter(new ArrayAdapter<Meal>(
                this,
                android.R.layout.simple_list_item_1,
                MealList.getInstance().getMeals())
        );

        lv.setOnItemClickListener((adapterView, view, i, l) -> {
            Log.d("Meal", "onItemClick(" + i + ")");
            Intent nextActivity = new Intent(SavedMealsActivity.this, MealDetailActivity.class);
            nextActivity.putExtra(EXTRA, i);
            startActivity(nextActivity);
        });
    }
    public void addNewMeal(View v) {
        Intent intent = new Intent(SavedMealsActivity.this, NewMealActivity.class);
        startActivity(intent);
    }
    public void fuckGoBack(View v) {
        finish();
    }
}
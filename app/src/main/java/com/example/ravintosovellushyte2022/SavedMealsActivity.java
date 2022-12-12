package com.example.ravintosovellushyte2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * @author Miiko Majewski
 * @version 1.1 12/2022
 */
public class SavedMealsActivity extends AppCompatActivity {

    public static final String EXTRA ="mealIndex";
    /**
     * This function is called when the app starts.
     * @param savedInstanceState Previously saved instance of the app's data
     */
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

    /**
     * Returns to a previous page
     * @param v current button
     */
    public void fuckGoBack(View v) {
        finish();
    }
}
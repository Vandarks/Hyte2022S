package com.example.ravintosovellushyte2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class SavedMealsActivity extends AppCompatActivity {

    private ListView mealListView;
    SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_meals);
        initWidget();
        loadFromDBToMemory();
        // setNoteAdapter();
        mealListView.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                MealList.getInstance().getMealList()
        ));
    }


    private void initWidget() {
        mealListView = findViewById(R.id.mealListView);
    }

    private void loadFromDBToMemory() {
        sqLiteManager.populateMealListArray();
    }

    public void newMeal(View view) {

        Intent newMealIntent = new Intent(this, NewMealActivity.class);
        startActivity(newMealIntent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mealListView.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                MealList.getInstance().getMealList()));
    }
    public void goBack(View view){
        finish();
    }
}
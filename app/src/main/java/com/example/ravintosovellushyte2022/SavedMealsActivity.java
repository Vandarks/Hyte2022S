package com.example.ravintosovellushyte2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * @author Tapio Humaljoki
 * A list of user preconfigured meal templates. These can be used, edited or removed from the list at will.
 */
public class SavedMealsActivity extends AppCompatActivity {

    private ListView mealListView;
    SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);

    /**
     * onCreate function for the activity
     * @param savedInstanceState Presaved state for the app
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_meals);
        initWidget();
        setOnClickListener();
        loadFromDBToMemory();
        mealListView.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                MealList.getInstance().getMealList()
        ));

    }

    /**
     * Initializes the necessary UI elements from the xml file.
     */
    private void initWidget() {
        mealListView = findViewById(R.id.mealListView);
    }

    /**
     * Calls the populateMealListArray function from the database manager class
     */
    private void loadFromDBToMemory() {
        sqLiteManager.populateMealListArray();
    }

    /**
     * Transfers to the NewMealActivity
     * @param view Add Meal button
     */
    public void newMeal(View view) {

        Intent newMealIntent = new Intent(this, NewMealActivity.class);
        startActivity(newMealIntent);
    }

    /**
     * Allows for listView elements to function as buttons. Buttons take the user to the NewMealActivity, allowing them to edit the preconfigured meals.
     */
    private void setOnClickListener(){
        mealListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l){
                Meal selectedMeal = (Meal) mealListView.getItemAtPosition(position);
                Intent editMealIntent = new Intent(getApplicationContext(), NewMealActivity.class);
                editMealIntent.putExtra(Meal.MEAL_EDIT_EXTRA, selectedMeal.getId());
                startActivity(editMealIntent);
            }
        });
    }

    /**
     * onStart function for the SavedMealsActivity. Refreshes the arrayList.
     */
    @Override
    protected void onStart() {
        super.onStart();
        loadFromDBToMemory();
        mealListView.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                MealList.getInstance().getMealList()
        ));
    }

    /**
     * Ends the activity and returns to the previous activity
     * @param view Back button
     */
    public void goBack(View view){
        finish();
    }
}
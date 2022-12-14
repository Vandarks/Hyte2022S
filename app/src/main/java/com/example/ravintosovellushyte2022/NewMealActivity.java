package com.example.ravintosovellushyte2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @author Tapio Humaljoki
 * The activity used for creating new premade meals.
 */
public class NewMealActivity extends AppCompatActivity {

    private EditText mealNameInput, caloriesInput, fatsInput, carbsInput, saltsInput;
    private Meal selectedMeal;


    private Button useButton;
    private Button addButton;
    /**
     * Main function of the activity
     * @param savedInstanceState Presaved state for the app.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_meal2);
        initWidgets();
        useButton = findViewById(R.id.useButton);
        addButton = findViewById(R.id.addFoodButton);
        useButton.setAlpha(0f);
        useButton.setEnabled(false);
        checkForEditMeal();
    }

    /**
     * Checks if meal is already created with the same values. If this is the case, lets the user edit the premade values.
     */
    private void checkForEditMeal() {
        Intent previousIntent = getIntent();
        int passedMealID = previousIntent.getIntExtra(Meal.MEAL_EDIT_EXTRA, -1);

        if(passedMealID >= 0){
            selectedMeal = MealList.getInstance().getMeal(passedMealID);
            if(selectedMeal != null) {
                mealNameInput.setText(selectedMeal.getName());
                caloriesInput.setText(Float.toString(selectedMeal.getCalories()));
                fatsInput.setText(Float.toString(selectedMeal.getFats()));
                carbsInput.setText(Float.toString(selectedMeal.getCarbs()));
                saltsInput.setText(Float.toString(selectedMeal.getSalts()));
                useButton.setAlpha(1f);
                useButton.setEnabled(true);
                addButton.setText("Edit food");
            }
        }
    }

    /**
     * Initializes the necessary UI elements from the xml file.
     */
    private void initWidgets() {

        mealNameInput = findViewById(R.id.mealNameInput);
        caloriesInput = findViewById(R.id.caloriesInput);
        fatsInput = findViewById(R.id.fatsInput);
        carbsInput = findViewById(R.id.carbsInput);
        saltsInput = findViewById(R.id.saltsInput);
    }

    /**
     * Adds a new premade meal to the list and to the database
     * @param view Add Food button
     */
    public void addMeal(View view){
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);

        String name;
        float calories, carbs, fats, salts;

        //START
        if(String.valueOf(mealNameInput.getText()).matches("")){
            Toast.makeText(this, "Missing name!", Toast.LENGTH_SHORT).show();
            return;
        } else {
            name = String.valueOf(mealNameInput.getText());
        }
        if(String.valueOf(caloriesInput.getText()).matches("")){
            Toast.makeText(this, "Missing calories!", Toast.LENGTH_SHORT).show();
            return;
        } else {
            calories = Float.parseFloat(String.valueOf(caloriesInput.getText()));
        }
        if(String.valueOf(carbsInput.getText()).matches("")){
            Toast.makeText(this, "Missing carbs!", Toast.LENGTH_SHORT).show();
            return;
        } else {
            carbs = Float.parseFloat(String.valueOf(carbsInput.getText()));
        }
        if(String.valueOf(fatsInput.getText()).matches("")){
            Toast.makeText(this, "Missing fats!", Toast.LENGTH_SHORT).show();
            return;
        } else {
            fats = Float.parseFloat(String.valueOf(fatsInput.getText()));
        }
        if(String.valueOf(saltsInput.getText()).matches("")){
            Toast.makeText(this, "Missing salts!", Toast.LENGTH_SHORT).show();
            return;
        } else {
            salts = Float.parseFloat(String.valueOf(saltsInput.getText()));
        }
        //END
        if(selectedMeal == null) {
            int id = MealList.getInstance().getSize();
            Meal newMeal = new Meal(id, name, calories, fats, carbs, salts);
            MealList.getInstance().addMeal(newMeal);
            sqLiteManager.addFoodToDatabase(newMeal);
        } else {
            selectedMeal.setName(name);
            selectedMeal.setCalories(calories);
            selectedMeal.setFats(fats);
            selectedMeal.setCarbs(carbs);
            selectedMeal.setSalts(salts);
            sqLiteManager.updateMealInDatabase(selectedMeal);
        }
        finish();
    }

    /**
     * Takes the premade values of the selected meal and puts them into the AddCustomFood activity.
     * @param view Use button
     */
    public void useMeal(View view){
        Intent intent = new Intent(NewMealActivity.this, AddCustomFoodActivity.class);
        intent.putExtra("PREMADE", true);
        intent.putExtra("CALORIES", selectedMeal.getCalories());
        intent.putExtra("FATS", selectedMeal.getFats());
        intent.putExtra("CARBS", selectedMeal.getCarbs());
        intent.putExtra("SALTS", selectedMeal.getSalts());

        startActivity(intent);

    }

    /**
     * Returns the user to the SavedMealsActivity.
     * @param view Cancel button
     */
    public void cancel(View view){
        finish();
    }
}
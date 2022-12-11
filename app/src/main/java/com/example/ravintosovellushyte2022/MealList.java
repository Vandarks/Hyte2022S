package com.example.ravintosovellushyte2022;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;

public class MealList {
    private static final MealList ourInstance = new MealList();
    private ArrayList<Meal> meals;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor sharedEdit;

    public static MealList getInstance() {return ourInstance;}


    public MealList() {
        meals = new ArrayList<Meal>();
        /*
        meals.add(new Meal("Saarioinen lihamakaroonilaatikko", 133, 4.8f, 15, 0.85f));
        meals.add(new Meal("Ristorante pizza hawaii", 208, 7.6f, 25, 1));
        meals.add(new Meal("Culinea makkarapyttipannu", 169, 7.8f, 19,  0.79f));

         */


    }



    public void saveToList(String name) {
        float calories = sharedPref.getFloat(name + "premadeCalories", 0);
        Log.d("Debug", "calories logged as " + calories);
        float carbs = sharedPref.getFloat(name + "premadeCarbs", 0);
        float fats = sharedPref.getFloat(name + "premadeFats", 0);
        float salts = sharedPref.getFloat(name + "premadeSalts", 0);

        meals.add(new Meal(name,calories, carbs, fats, salts));
    }

    public ArrayList<Meal> getMeals() {return meals;}
    public Meal getMeal(int i) {return meals.get(i);}
}

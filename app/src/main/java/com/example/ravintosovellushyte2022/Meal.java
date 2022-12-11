package com.example.ravintosovellushyte2022;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

public class Meal {
    private String name;
    private float calories;
    private float fats;
    private float carbs;
    private float salts;

    public Meal(String name, float calories, float fats, float carbs, float salts) {


        this.name = name;
        this.calories = calories;
        this.fats = fats;
        this.carbs = carbs;
        this.salts = salts;

    }




    public String toString() {return name;}
    public String getName() {return name;}
    public float getCalories() {return calories;}
    public float getFats() {return fats;}
    public float getCarbs() {return carbs;}
    public float getSalts() {return salts;}


}

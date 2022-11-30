package com.example.ravintosovellushyte2022;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NutritionTracker {

    //Variables to store data
    private float calories; //MM-DD-YYYY eg: 07-10-1996
    private float carbs;
    private float fats;
    private float salts;

    //Variables to get date
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;

    private SharedPreferences sharedPref; //Object to read sharedPreferences
    private SharedPreferences.Editor sharedEdit; //Object to write to sharedPreferences

    public NutritionTracker(Context context){
        sharedPref = context.getSharedPreferences(MainActivity.NUTRITION_PREFS, Context.MODE_PRIVATE);
        sharedEdit = sharedPref.edit();

        //Getting current date
        calendar = Calendar.getInstance(); //Calendar object to get time
        dateFormat = new SimpleDateFormat("dd/MM/yyyy"); //Dateformat to format the date
        date = dateFormat.format(calendar.getTime()); //Saving the formatted date

        //Read daily nutrition values from sharedPreferences
        calories = sharedPref.getFloat(date + "calories", 0);
        carbs = sharedPref.getFloat(date + "carbs", 0);
        fats = sharedPref.getFloat(date + "fats", 0);
        salts = sharedPref.getFloat(date + "salts", 0);
    }

    public void saveNutritions(){
        //Save the variables to NUTRITION_PREFS sharedPreferences
        sharedEdit.putFloat(date + "calories", calories);
        sharedEdit.putFloat(date + "carbs", carbs);
        sharedEdit.putFloat(date + "fats", fats);
        sharedEdit.putFloat(date + "salts", salts);
        sharedEdit.apply();
    }

    //Add methods
    public void addCalories(float calories){
        this.calories += calories;
    }
    public void addCarbs(float carbs){
        this.carbs += carbs;
    }
    public void addFats(float fats){
        this.fats += fats;
    }
    public void addSalts(float salts){
        this.salts += salts;
    }

    public void addNutritions(int grams, float calories, float carbs, float fats, float salts){
        this.calories += (grams/100)*calories;
        this.carbs += (grams/100)*carbs;
        this.fats += (grams/100)*fats;
        this.salts += (grams/100)*salts;
    }

    //Get methods
    public String getDate(){
        return date;
    }
    public float getCalories(){
        return calories;
    }
    public float getCarbs(){
        return carbs;
    }
    public float getFats(){
        return fats;
    }
    public float getSalts(){
        return salts;
    }
}

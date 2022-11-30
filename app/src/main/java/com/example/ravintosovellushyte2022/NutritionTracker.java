package com.example.ravintosovellushyte2022;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NutritionTracker {

    //Variables to store data
    private float calories;
    private float carbs;
    private float fats;
    private float salts;
    private int timesEaten;

    //Variables to get date
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date; //MM-DD-YYYY eg: 07-10-1996

    private SharedPreferences sharedPref; //Object to read sharedPreferences
    private SharedPreferences.Editor sharedEdit; //Object to write to sharedPreferences

    public NutritionTracker(Context context){
        sharedPref = context.getSharedPreferences(MainActivity.NUTRITION_PREFS, Context.MODE_PRIVATE);
        sharedEdit = sharedPref.edit();

        //Creating calendar and date formatter for getting today's date
        calendar = Calendar.getInstance(); //Calendar object to get time
        dateFormat = new SimpleDateFormat("dd/MM/yyyy"); //Date format to format the date

        //Read daily nutrition values from sharedPreferences and get today's date
        updateFromSavedData();
    }

    public void saveNutritions(){
        //Save the variables to NUTRITION_PREFS sharedPreferences
        sharedEdit.putFloat(date + "calories", calories);
        sharedEdit.putFloat(date + "carbs", carbs);
        sharedEdit.putFloat(date + "fats", fats);
        sharedEdit.putFloat(date + "salts", salts);
        sharedEdit.putInt(date + "timesEaten", timesEaten);
        //Apply changes
        sharedEdit.apply();
    }

    public void clearToday(){
        //Clears today's nutritional values
        sharedEdit.remove(date + "calories");
        sharedEdit.remove(date + "carbs");
        sharedEdit.remove(date + "fats");
        sharedEdit.remove(date + "salts");
        sharedEdit.remove(date + "timesEaten");
        //Apply changes
        sharedEdit.apply();
    }

    //Add methods
    public void addNutritions(float grams, float calories, float carbs, float fats, float salts){
        //Calculates nutritions based on per 100 grams values
        this.calories += (grams/100)*calories;
        this.carbs += (grams/100)*carbs;
        this.fats += (grams/100)*fats;
        this.salts += (grams/100)*salts;
        this.timesEaten++;
    }

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
    public void addTimesEaten() {this.timesEaten++;}

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
    public int getTimesEaten() {return timesEaten;}

    //Method to get the current date and currently saved nutritional values
    public void updateFromSavedData(){
        date = dateFormat.format(calendar.getTime()); //Getting the date
        calories = sharedPref.getFloat(date + "calories", 0); //Getting calorie amount from SavedPreferences
        carbs = sharedPref.getFloat(date + "carbs", 0); //..carbs
        fats = sharedPref.getFloat(date + "fats", 0); //..fats
        salts = sharedPref.getFloat(date + "salts", 0); //..salts
        timesEaten = sharedPref.getInt(date + "timesEaten", 0);
    }

}

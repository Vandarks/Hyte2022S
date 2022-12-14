package com.example.ravintosovellushyte2022;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * History day class object that contains date-specific nutritional value data
 * @author Miiko Majewski
 */
public class HistoryDay {

    private SharedPreferences sharedPref; //Object to read sharedPreferences
    private SharedPreferences generalPref;
    private SharedPreferences.Editor sharedEdit; //Object to write to sharedPreferences
    private float calories;
    private float carbs;
    private float fats;
    private float salts;

    float calper;
    float carbsper;
    float fatsper;
    float saltsper;

    private String date;
    private String dateName;

    /**
     * Constructor for a day
     * @param context Context where the function is called from
     * @param date Date from where the data is pulled from
     * @param dateName Name of the day, to be put in the toString function.
     */
    public HistoryDay(Context context, String date, String dateName){
        this.sharedPref = context.getSharedPreferences(MainActivity.NUTRITION_PREFS, Context.MODE_PRIVATE);
        this.generalPref = context.getSharedPreferences(MainActivity.GENERAL_PREFS, Context.MODE_PRIVATE);
        this.date = date;
        this.dateName = dateName;

        calories = sharedPref.getFloat(this.date + "calories", 0); //Getting calorie amount from SavedPreferences
        carbs = sharedPref.getFloat(this.date + "carbs", 0); //..carbs
        fats = sharedPref.getFloat(this.date + "fats", 0); //..fats
        salts = sharedPref.getFloat(this.date + "salts", 0); //..salts

        calper = (calories/generalPref.getFloat("MAXCAL", 1))*100;
        carbsper = (carbs/generalPref.getFloat("MAXCARBS", 1))*100;
        fatsper = (fats/generalPref.getFloat("MAXFATS", 1))*100;
        saltsper = (salts/generalPref.getFloat("MAXSALTS", 1))*100;
    }

    /**
     * Returns all information stored in the object as a humane string.
     * @return Information to be displayed in the HistoryActivity
     */
    public String toString(){
        return dateName + " " + date + "\nCalories: " + String.format("%.2f", calories) + "g (" + Math.round(calper) + "%)"+ "\nCarbs: " + String.format("%.2f", carbs) + "g (" + Math.round(carbsper) + "%)" + "\nFats: " + String.format("%.2f", fats) + "g (" + Math.round(fatsper) + "%)" + "\nSalts: " + String.format("%.2f", salts) + "g (" + Math.round(saltsper) + "%)";
    }
}

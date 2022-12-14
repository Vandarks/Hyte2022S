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
    private float previousCalories;
    private float previousCarbs;
    private float previousFats;
    private float previousSalts;
    private float previousGrams;


    //Variables to get date
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date; //MM-DD-YYYY eg: 07-10-1996

    private SharedPreferences sharedPref; //Object to read sharedPreferences
    private SharedPreferences.Editor sharedEdit; //Object to write to sharedPreferences

    /**
     * @author Miiko Majewski
     * Default constructor for NutritionTracker
     * @param context Context is used to call shared preferences
     */
    public NutritionTracker(Context context){
        sharedPref = context.getSharedPreferences(MainActivity.NUTRITION_PREFS, Context.MODE_PRIVATE);
        sharedEdit = sharedPref.edit();

        //Creating calendar and date formatter for getting today's date
        calendar = Calendar.getInstance(); //Calendar object to get time
        dateFormat = new SimpleDateFormat("dd/MM/yyyy"); //Date format to format the date


        //Read daily nutrition values from sharedPreferences and get today's date
        updateFromSavedData();
    }

    /**
     * Saves all current and previous nutritional values to shared preferences     *
     */
    public void saveNutritions(){
        //Save the variables to NUTRITION_PREFS sharedPreferences
        Log.d("Debug", "daten arvo: " + date);
        sharedEdit.putFloat(date + "calories", calories);
        sharedEdit.putFloat(date + "carbs", carbs);
        sharedEdit.putFloat(date + "fats", fats);
        sharedEdit.putFloat(date + "salts", salts);
        sharedEdit.putInt(date + "timesEaten", timesEaten);
        sharedEdit.putFloat(date + "previousCalories", previousCalories);
        sharedEdit.putFloat(date + "previousCarbs", previousCarbs);
        sharedEdit.putFloat(date + "previousFats", previousFats);
        sharedEdit.putFloat(date + "previousSalts", previousSalts);
        sharedEdit.putFloat(date + "previousGrams", previousGrams);
        //Apply changes
        sharedEdit.apply();
    }

    /**
     * Clears the current days values from shared preferences
     */
    public void clearToday(){
        //Clears today's nutritional values
        sharedEdit.remove(date + "calories");
        sharedEdit.remove(date + "carbs");
        sharedEdit.remove(date + "fats");
        sharedEdit.remove(date + "salts");
        sharedEdit.remove(date + "timesEaten");
        sharedEdit.remove(date + "previousCalories");
        sharedEdit.remove(date + "previousCarbs");
        sharedEdit.remove(date + "previousFats");
        sharedEdit.remove(date + "previousSalts");
        sharedEdit.remove(date + "previousGrams");

        //Apply changes
        sharedEdit.apply();
    }

    /**
     * Add methods for nutritional values
     * @param grams amount of grams
     * @param calories amount of calories per 100 g
     * @param carbs amount of carbs per 100 g
     * @param fats amount of fats per 100 g
     * @param salts amount of salts per 100 g
     */
    public void addNutritions(float grams, float calories, float carbs, float fats, float salts){
        //Calculates nutritions based on per 100 grams values
        this.calories += (grams/100)*calories;
        this.carbs += (grams/100)*carbs;
        this.fats += (grams/100)*fats;
        this.salts += (grams/100)*salts;
        this.timesEaten++;
        this.previousCalories = calories;
        this.previousCarbs = carbs;
        this.previousFats = fats;
        this.previousSalts = salts;
        this.previousGrams = grams;
        saveNutritions();
    }

    /**
     * Calculates nutritions based on per 100 g values and calls saveNutritions
     * @param grams amount of grams
     * @param calories amount of calories per 100 g
     * @param carbs amount of carbs per 100 g
     * @param fats amount of fats per 100 g
     * @param salts amount of salts per 100 g
     */
    public void editNutritions(float grams, float calories, float carbs, float fats, float salts){
        //Calculates nutritions based on per 100 grams values

        this.calories = (this.calories - (this.getPreviousGrams()/100) * this.getPreviousCalories())+ (grams/100)*calories;
        this.carbs = (this.carbs - (this.getPreviousGrams()/100) * this.getPreviousCarbs())+ (grams/100)*carbs;
        this.fats = (this.fats - (this.getPreviousGrams()/100) * this.getPreviousFats())+ (grams/100)*fats;
        this.salts = (this.salts - (this.getPreviousGrams()/100) * this.getPreviousSalts())+ (grams/100)*salts;
        this.previousCalories = calories;
        this.previousCarbs = carbs;
        this.previousFats = fats;
        this.previousSalts = salts;
        this.previousGrams = grams;
        saveNutritions();
    }

    /**
     * Removes previous meal saved and calls saveNutritions
     * @param grams amount of grams
     * @param calories amount of calories per 100 g
     * @param carbs amount of carbs per 100 g
     * @param fats amount of fats per 100 g
     * @param salts amount of salts per 100 g
     */
    public void removeLastMeal(float grams, float calories, float carbs, float fats,float salts){
        this.calories -= (grams/100)*calories;
        this.carbs -= (grams/100)*carbs;
        this.fats -= (grams/100)*fats;
        this.salts -= (grams/100)*salts;
        if(this.timesEaten > 0) {
            this.timesEaten--;
        }
        previousGrams = 0;
        previousCalories = 0;
        previousCarbs = 0;
        previousFats = 0;
        previousSalts = 0;

        saveNutritions();
    }

    /**
     * Get method for date
     * @return date
     */
    public String getDate(){
        return date;
    }
    /**
     * Get method for calories
     * @return calories
     */
    public float getCalories(){
        return calories;
    }
    /**
     * Get method for carbs
     * @return carbs
     */
    public float getCarbs(){
        return carbs;
    }
    /**
     * Get method for fats
     * @return fats
     */
    public float getFats(){
        return fats;
    }
    /**
     * Get method for salts
     * @return salts
     */
    public float getSalts(){
        return salts;
    }
    /**
     * Get method for amount of times eaten
     * @return Times eaten
     */
    public int getTimesEaten() {return timesEaten;}
    /**
     * Get method for previous calories
     * @return Previous calories
     */
    public float getPreviousCalories(){
        return previousCalories;
    }
    /**
     * Get method for previous carbs
     * @return Previous carbs
     */
    public float getPreviousCarbs(){ return previousCarbs;}
    /**
     * Get method for previous fats
     * @return Previous fats
     */
    public float getPreviousFats(){
        return previousFats;
    }
    /**
     * Get method for previous salts
     * @return Previous salts
     */
    public float getPreviousSalts(){
        return previousSalts;
    }
    /**
     * Get method for previous grams
     * @return Previous grams
     */
    public float getPreviousGrams() { return previousGrams;}



    //for history tab, unused yet
    public int getDay() {return calendar.get(Calendar.DAY_OF_MONTH);}
    public int getMonth() {return calendar.get(Calendar.MONTH);}
    public int getYear() {return calendar.get(Calendar.YEAR);}


    /**
     * Method to get the current date and currently saved nutritional values
     */
    public void updateFromSavedData(){
        date = dateFormat.format(calendar.getTime()); //Getting the date
        calories = sharedPref.getFloat(date + "calories", 0); //Getting calorie amount from SavedPreferences
        carbs = sharedPref.getFloat(date + "carbs", 0); //..carbs
        fats = sharedPref.getFloat(date + "fats", 0); //..fats
        salts = sharedPref.getFloat(date + "salts", 0); //..salts
        previousCalories = sharedPref.getFloat(date + "previousCalories", 0); //Getting calorie amount from SavedPreferences
        previousCarbs = sharedPref.getFloat(date + "previousCarbs", 0); //..carbs
        previousFats = sharedPref.getFloat(date + "previousFats", 0); //..fats
        previousSalts = sharedPref.getFloat(date + "previousSalts", 0); //..salts
        previousGrams = sharedPref.getFloat(date + "previousGrams", 0);
        timesEaten = sharedPref.getInt(date + "timesEaten", 0);
    }

}

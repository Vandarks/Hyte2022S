package com.example.ravintosovellushyte2022;

import java.util.ArrayList;

/**
 * @author Onni Alasaari
 * Meal class to be used globally in the database and listview
 */
public class Meal {
    public static String MEAL_EDIT_EXTRA = "mealEdit";
    public static ArrayList<Meal> mealsArrayList = new ArrayList<>();

    private int id;
    private String name;
    private float calories;
    private float fats;
    private float carbs;
    private float salts;

    /**
     * Constructor for a meal with several traits
     * @param id Id for the database
     * @param name Name of the food
     * @param calories Calories of the food per 100 grams
     * @param fats Fats of the food per 100 grams
     * @param carbs Carbs of the food per 100 grams
     * @param salts Salts of the food per 100 grams
     */
    public Meal(int id, String name, float calories, float fats, float carbs, float salts) {
        this.id = id;
        this.name = name;
        this.calories = calories;
        this.fats = fats;
        this.carbs = carbs;
        this.salts = salts;
    }

    /**
     * Get function for the id
     * @return Id
     */
    public int getId() {
        return id;
    }

    /**
     * Get function for the name
     * @return Name
     */
    public String getName() {
        return name;
    }

    /**
     * Get function for the calories
     * @return Calories
     */
    public float getCalories() {
        return calories;
    }

    /**
     * Get function for the fats
     * @return Fats
     */
    public float getFats() {
        return fats;
    }

    /**
     * Get function for the carbs
     * @return Carbs
     */
    public float getCarbs() {
        return carbs;
    }

    /**
     * Get function for the Salts
     * @return Salts
     */
    public float getSalts() {
        return salts;
    }


    /**
     * Set function for the id
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Set function for the name
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Set function for the calories
     */
    public void setCalories(float calories) {
        this.calories = calories;
    }
    /**
     * Set function for the fats
     */
    public void setFats(float fats) {
        this.fats = fats;
    }
    /**
     * Set function for the carbs
     */
    public void setCarbs(float carbs) {
        this.carbs = carbs;
    }
    /**
     * Set function for the salts
     */
    public void setSalts(float salts) {
        this.salts = salts;
    }

    /**
     * toString override function for the meals
     * @return Name
     */
    public String toString(){
        return name;
    }

}

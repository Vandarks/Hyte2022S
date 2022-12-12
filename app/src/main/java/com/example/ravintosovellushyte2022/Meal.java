package com.example.ravintosovellushyte2022;

/**
 * @author Onni Alasaari
 * @version 1.1 12/2022
 */
public class Meal {
    private String name;
    private float calories;
    private float fats;
    private float carbs;
    private float salts;

    /**
     * Meal object, used for the MealList, Object has:
     * @param name name of the food
     * @param calories calorie amount per 100 g
     * @param fats fats amount per 100 g
     * @param carbs carbs amount per 100 g
     * @param salts salts amount per 100 g
     */
    public Meal(String name, float calories, float fats, float carbs, float salts) {


        this.name = name;
        this.calories = calories;
        this.fats = fats;
        this.carbs = carbs;
        this.salts = salts;

    }


    /**
     * Returns the namee of the food
     * @return name of the food
     */
    public String toString() {return name;}

    /**
     * Returns the name of the food
     * @return name of the food
     */
    public String getName() {return name;}

    /**
     * Returns the calories from object
     * @return calories per 100 g
     */
    public float getCalories() {return calories;}
    /**
     * Returns the fats from object
     * @return fats per 100 g
     */
    public float getFats() {return fats;}
    /**
     * Returns the carbs from object
     * @return carbs per 100 g
     */
    public float getCarbs() {return carbs;}

    /**
     * Returns the salts from object
     * @return salts per 100 g
     */
    public float getSalts() {return salts;}


}

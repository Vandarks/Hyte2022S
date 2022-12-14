package com.example.ravintosovellushyte2022;

import java.util.ArrayList;
import java.util.List;

/**
 * The array list class for the preconfigured meals.
 * @author Onni Alasaari
 */
public class MealList {
    private List<Meal> mealsArrayList;
    private static final MealList ourInstance = new MealList();

    /**
     * Gets the current meal list instance
     * @return Current instance
     */
    public static MealList getInstance(){
        return ourInstance;
    }
    private MealList(){
        mealsArrayList = new ArrayList<>();
    }

    /**
     * Gets the meal array list
     * @return Meal array list
     */
    public List<Meal> getMealList(){
        return mealsArrayList;
    }

    /**
     * Gets the meal array list size
     * @return Size of the arraylist
     */
    public int getSize(){
        return mealsArrayList.size();
    }

    /**
     * Adds a new meal to the arraylist
     * @param meal Meal to be added
     */
    public void addMeal(Meal meal){
        mealsArrayList.add(meal);
    }

    /**
     * Gets a meal from the arraylist
     * @param i # of the wanted meal in the list
     * @return The wanted meal from the list.
     */
    public Meal getMeal(int i){
        return mealsArrayList.get(i);
    }

    /**
     * Clears the meal list array
     */
    public void clearMealList(){ mealsArrayList.clear();}

}

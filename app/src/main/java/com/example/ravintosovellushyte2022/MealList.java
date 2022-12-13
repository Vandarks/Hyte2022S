package com.example.ravintosovellushyte2022;

import java.util.ArrayList;
import java.util.List;

public class MealList {
    private List<Meal> mealsArrayList;
    private static final MealList ourInstance = new MealList();
    public static MealList getInstance(){
        return ourInstance;
    }
    private MealList(){
        mealsArrayList = new ArrayList<>();
    }
    public List<Meal> getMealList(){
        return mealsArrayList;
    }
    public int getSize(){
        return mealsArrayList.size();
    }
    public void addMeal(Meal meal){
        mealsArrayList.add(meal);
    }
    public Meal getMeal(int i){
        return mealsArrayList.get(i);
    }
}

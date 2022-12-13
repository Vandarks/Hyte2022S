package com.example.ravintosovellushyte2022;

public class Meal {
    private int id;
    private String name;
    private float calories;
    private float fats;
    private float carbs;
    private float salts;

    public Meal(int id, String name, float calories, float fats, float carbs, float salts) {
        this.id = id;
        this.name = name;
        this.calories = calories;
        this.fats = fats;
        this.carbs = carbs;
        this.salts = salts;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getCalories() {
        return calories;
    }

    public float getFats() {
        return fats;
    }

    public float getCarbs() {
        return carbs;
    }

    public float getSalts() {
        return salts;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }

    public void setFats(float fats) {
        this.fats = fats;
    }

    public void setCarbs(float carbs) {
        this.carbs = carbs;
    }

    public void setSalts(float salts) {
        this.salts = salts;
    }

    public String toString(){
        return name;
    }
}

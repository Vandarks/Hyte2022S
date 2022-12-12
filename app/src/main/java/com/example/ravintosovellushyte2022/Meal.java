package com.example.ravintosovellushyte2022;


public class Meal {
    private String name;
    private float calories;
    private float fats;
    private float carbs;
    private float salts;

    /**
     * Luo uuden ruuan
     * @param name ruuan nimi
     * @param calories kalorimäärä per 100g ruokaa
     * @param fats rasvamäärä per 100g ruokaa
     * @param carbs hiilihydraattimäärä per 100g ruokaa
     * @param salts suolaa per 100g ruokaa
     */
    public Meal(String name, float calories, float fats, float carbs, float salts) {


        this.name = name;
        this.calories = calories;
        this.fats = fats;
        this.carbs = carbs;
        this.salts = salts;

    }


    /**
     * Palauttaa toString arvon
     * @return ruuan nimi
     */
    public String toString() {return name;}
    public String getName() {return name;}
    public float getCalories() {return calories;}
    public float getFats() {return fats;}
    public float getCarbs() {return carbs;}
    public float getSalts() {return salts;}


}

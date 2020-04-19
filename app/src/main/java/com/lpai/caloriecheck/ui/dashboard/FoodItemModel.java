package com.lpai.caloriecheck.ui.dashboard;


public class FoodItemModel {
    public String name;
    public double proteins;
    public double carbs;
    public double fat;
    public double calories;


    public FoodItemModel(MacroRatio macroRatio, double quantity) {
        this.name = macroRatio.name;
        this.proteins = macroRatio.proteinRatio*quantity;
        this.carbs    = macroRatio.carbsRatio*quantity;
        this.fat      = macroRatio.fatRatio*quantity;
        this.calories = macroRatio.caloriesRatio*quantity;
    }

    public FoodItemModel(String name, double proteins, double carbs, double fat, double calories) {
        this.name = name;
        this.proteins = proteins;
        this.carbs = carbs;
        this.fat = fat;
        this.calories = calories;
    }
}
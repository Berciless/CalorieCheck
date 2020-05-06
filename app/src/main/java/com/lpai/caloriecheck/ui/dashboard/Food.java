package com.lpai.caloriecheck.ui.dashboard;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "daily_food")
public class Food {

    @PrimaryKey(autoGenerate = true)
    public long foodId;
    public String name;
    public double proteins;
    public double carbs;
    public double fat;
    public double calories;

    public Food( String name, double proteins, double carbs, double fat, double calories) {
        this.name = name;
        this.proteins = proteins;
        this.carbs = carbs;
        this.fat = fat;
        this.calories = calories;
    }

    @Ignore
    public Food(MacroRatio macroRatio, double quantity) {
        this.name = macroRatio.name;
        this.proteins = macroRatio.proteinRatio*quantity;
        this.carbs    = macroRatio.carbsRatio*quantity;
        this.fat      = macroRatio.fatRatio*quantity;
        this.calories = macroRatio.caloriesRatio*quantity;
    }


}

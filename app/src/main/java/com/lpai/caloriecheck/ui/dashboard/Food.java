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

    @Ignore
    public Food( String name, double proteins, double carbs, double fat, double calories) {
        this.name = name;
        this.proteins = proteins;
        this.carbs = carbs;
        this.fat = fat;
        this.calories = calories;
    }

    public Food( String name) {
        this.name = name;
        this.proteins = 20;
        this.carbs = 100;
        this.fat = 10;
        this.calories = 570;
    }

//    public Food(Food food) {
//        this.name = food.name;
//        this.proteins = food.proteins;
//        this.carbs = food.carbs;
//        this.fat = food.fat;
//        this.calories = food.calories;
//    }
}

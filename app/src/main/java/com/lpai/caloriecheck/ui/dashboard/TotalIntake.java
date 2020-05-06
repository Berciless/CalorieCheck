package com.lpai.caloriecheck.ui.dashboard;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "total_intake")
public class TotalIntake {
    @PrimaryKey(autoGenerate = true)
    long Id;
    public double proteins;
    public double carbs;
    public double fat;
    public double calories;

    public TotalIntake(double calories,double proteins,double carbs, double fat) {
        this.calories=calories;
        this.proteins=proteins;
        this.carbs=carbs;
        this.fat=fat;

    }
}

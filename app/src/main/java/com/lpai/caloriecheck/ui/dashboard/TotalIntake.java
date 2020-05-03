package com.lpai.caloriecheck.ui.dashboard;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;

@Entity(tableName = "total_intake")
public class TotalIntake {
    @PrimaryKey(autoGenerate = true)
    public long Id;
    public String date;
    public String name;
    public double proteins;
    public double carbs;
    public double fat;
    public double calories;


    public TotalIntake(String name) {
        this.name = name;
        this.proteins=0;
        this.carbs=0;
        this.fat=0;
        this.calories=0;
        this.date=
                String.valueOf(LocalDateTime.now().getDayOfMonth()<10? "0"+LocalDateTime.now().getDayOfMonth():LocalDateTime.now().getDayOfMonth())+
                "-"+
                String.valueOf(LocalDateTime.now().getMonthValue()<10? "0"+LocalDateTime.now().getMonthValue():LocalDateTime.now().getMonthValue())+
                "-"+
                String.valueOf(LocalDateTime.now().getYear());
    }
}

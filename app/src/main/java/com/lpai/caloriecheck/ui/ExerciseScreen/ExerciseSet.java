package com.lpai.caloriecheck.ui.ExerciseScreen;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;

@Entity(tableName = "sets")
public class ExerciseSet {
    @PrimaryKey(autoGenerate = true)
    public long setId;
    public long exerciseId;
    public String date;
    public int reps;
    public double weight;

    public ExerciseSet (long exerciseId, int reps, double weight){
        this.exerciseId=exerciseId;
        this.reps=reps;
        this.weight=weight;
        this.date=
                    String.valueOf(LocalDateTime.now().getDayOfMonth()<10? "0"+LocalDateTime.now().getDayOfMonth():LocalDateTime.now().getDayOfMonth())+
                    "-"+
                    String.valueOf(LocalDateTime.now().getMonthValue()<10? "0"+LocalDateTime.now().getMonthValue():LocalDateTime.now().getMonthValue())+
                    "-"+
                    String.valueOf(LocalDateTime.now().getYear())+
                    "  "+
                    String.valueOf(LocalDateTime.now().getHour()<10?  "0"+LocalDateTime.now().getHour() : LocalDateTime.now().getHour())+
                    ":" +
                    String.valueOf(LocalDateTime.now().getMinute()<10? "0"+LocalDateTime.now().getMinute() : LocalDateTime.now().getMinute());
    }
}



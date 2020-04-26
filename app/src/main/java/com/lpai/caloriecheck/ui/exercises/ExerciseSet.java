package com.lpai.caloriecheck.ui.exercises;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class ExerciseSet {
    @PrimaryKey(autoGenerate = true)
    public long workoutId;
    public long exerciseId;
    public String date;
    public int reps;
    public double weight;

    public ExerciseSet (long exerciseId, int reps, double weight){
        this.exerciseId=exerciseId;
        this.reps=reps;
        this.weight=weight;
        this.date=
                String.valueOf(LocalDateTime.now().getDayOfMonth())+
                "/"+
                String.valueOf(LocalDateTime.now().getMonth())+
                "/"+
                String.valueOf(LocalDateTime.now().getYear())+
                "  "+
                String.valueOf(LocalDateTime.now().getHour())+
                ":" +
                String.valueOf(LocalDateTime.now().getMinute());
    }
}


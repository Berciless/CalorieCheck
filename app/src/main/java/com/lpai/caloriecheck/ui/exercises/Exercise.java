package com.lpai.caloriecheck.ui.exercises;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "exercises")
public class Exercise {

    @PrimaryKey(autoGenerate = true)
    public long exerciseId;
    public String name;

    public Exercise( String name) {
        this.name = name;
    }
}

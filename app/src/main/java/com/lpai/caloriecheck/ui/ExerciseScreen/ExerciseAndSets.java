package com.lpai.caloriecheck.ui.ExerciseScreen;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.lpai.caloriecheck.ui.exercises.Exercise;

import java.util.List;

public class ExerciseAndSets {
    @Embedded
    public Exercise exercise;
    @Relation(
            parentColumn = "exerciseId",
            entityColumn = "exerciseId",
            entity = ExerciseSet.class
    )
    public List<ExerciseSet> sets;
}


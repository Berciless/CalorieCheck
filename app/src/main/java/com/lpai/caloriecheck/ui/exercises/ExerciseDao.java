package com.lpai.caloriecheck.ui.exercises;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ExerciseDao {
    @Insert
    void insert(Exercise exercise);

    @Query("DELETE FROM exercises")
    void deleteAll();

    @Query("SELECT * FROM exercises")
    LiveData<List<Exercise>> getTodaySExercise();

//    @Query("SELECT * FROM exercises WHERE exerciseId=$id)
}


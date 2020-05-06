package com.lpai.caloriecheck.ui.exercises;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.lpai.caloriecheck.ui.ExerciseScreen.ExerciseSet;

import java.util.List;

@Dao
public interface ExerciseDao {
    @Insert
    void insertExercise(Exercise exercise);

    @Insert
    void insertSet(ExerciseSet set);

    @Query("DELETE FROM exercises")
    void deleteAllExercises();

    @Query("DELETE FROM exercises WHERE exerciseId=:id")
    void deleteExerciseById(long id);

    @Query("DELETE FROM sets WHERE setId=:id")
    void deleteSetById(long id);

    @Query("SELECT * FROM exercises")
    LiveData<List<Exercise>> getExercise();

    @Query("SELECT * FROM sets WHERE exerciseId=:id ORDER BY setId desc")
    LiveData<List<ExerciseSet>> getSetsForExercise(long id);

    @Query("DELETE FROM sets WHERE exerciseId=:id")
    void deleteSetByExercise(long id);

}


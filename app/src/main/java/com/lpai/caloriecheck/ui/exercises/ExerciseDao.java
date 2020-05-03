package com.lpai.caloriecheck.ui.exercises;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.lpai.caloriecheck.ui.ExerciseScreen.ExerciseAndSets;
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

    @Query("DELETE FROM exerciseset")
    void deleteAllSets();

    @Query("DELETE FROM exercises WHERE exerciseId=:id")
    void deleteExerciseById(long id);

    @Query("DELETE FROM exerciseset WHERE setId=:id")
    void deleteSetById(long id);

    @Query("SELECT * FROM exercises")
    LiveData<List<Exercise>> getExercise();


    @Query("SELECT * FROM exerciseset")
    public LiveData<List<ExerciseSet>> getAllSets();

    @Query("SELECT * FROM exerciseset WHERE exerciseId=:id ORDER BY setId desc")
    public LiveData<List<ExerciseSet>> getSetsForExercise(long id);

    @Query("DELETE FROM exerciseset WHERE exerciseId=:id")
    void deleteSetByExercise(long id);

//    @Query("SELECT * FROM exercises WHERE exerciseId=:id)
}


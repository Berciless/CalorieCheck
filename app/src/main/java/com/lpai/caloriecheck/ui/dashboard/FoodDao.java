package com.lpai.caloriecheck.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FoodDao {
    @Insert
    void insert(Food food);

    @Insert
    void insertTarget(TotalIntake totalIntake);

    @Query("DELETE FROM total_intake")
    void deleteTarget();

    @Query("DELETE FROM daily_food")
    void deleteAll();

    @Query("SELECT * FROM daily_food")
    LiveData<List<Food>> getTodaySFood();

    @Query("SELECT 0 foodId,'total' name, COALESCE(sum(COALESCE(proteins,0)), 0) as proteins, " +
            "COALESCE(sum(COALESCE(carbs,0)), 0) as carbs, " +
            "COALESCE(sum(COALESCE(fat,0)), 0) as fat," +
            " COALESCE(sum(COALESCE(calories,0)), 0) " +
            "as calories FROM daily_food")
    Food getTotal();

    @Query("SELECT * FROM total_intake LIMIT 1")
    TotalIntake getTarget();

    @Query("DELETE FROM daily_food WHERE foodId=:id")
    void deleteFoodById(Long id);
}

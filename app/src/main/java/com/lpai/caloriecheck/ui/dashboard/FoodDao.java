package com.lpai.caloriecheck.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FoodDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Food food);

    @Query("DELETE FROM daily_food")
    void deleteAll();

    @Query("SELECT * FROM daily_food")
    LiveData<List<Food>> getTodaySFood();

//    @Query("SELECT * FROM daily_food WHERE id=")

}

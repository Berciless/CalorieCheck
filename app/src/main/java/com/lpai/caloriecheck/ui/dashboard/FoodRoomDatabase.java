package com.lpai.caloriecheck.ui.dashboard;

import android.content.Context;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities={Food.class},version = 1,exportSchema = false)
public abstract class FoodRoomDatabase extends RoomDatabase {
    public abstract FoodDao foodDao();
    private  static FoodRoomDatabase INSTANCE;

    public static FoodRoomDatabase getDatabase(final Context context){
        if(INSTANCE==null){
            synchronized (FoodRoomDatabase.class){
                if (INSTANCE==null){
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(),FoodRoomDatabase.class,"database").fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }
}

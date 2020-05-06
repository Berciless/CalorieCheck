package com.lpai.caloriecheck.ui.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.lpai.caloriecheck.ui.ExerciseScreen.ExerciseSet;
import com.lpai.caloriecheck.ui.dashboard.Food;
import com.lpai.caloriecheck.ui.dashboard.FoodDao;
import com.lpai.caloriecheck.ui.dashboard.TotalIntake;
import com.lpai.caloriecheck.ui.exercises.Exercise;
import com.lpai.caloriecheck.ui.exercises.ExerciseDao;

@Database(entities={Food.class, TotalIntake.class, Exercise.class, ExerciseSet.class},version = 5,exportSchema = false)
public abstract class AppRoomDatabase extends RoomDatabase {
    public abstract FoodDao foodDao();
    public abstract ExerciseDao exerciseDao();
    private static AppRoomDatabase INSTANCE;
    static AppRoomDatabase getDatabase(final Context context){
        if(INSTANCE==null){
            synchronized (AppRoomDatabase.class){
                if (INSTANCE==null){
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(), AppRoomDatabase.class,"database")
                            .fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }

    private static Callback roomDatabaseCallback =
            new Callback(){
                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };
    public static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        final FoodDao dao;
        PopulateDbAsync(AppRoomDatabase db) {
            dao = db.foodDao();
        }
        @Override
        public Void doInBackground(final Void... params) {
//            Do DB instruction when te app starts
            return null;
        }
    }
}


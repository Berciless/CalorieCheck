package com.lpai.caloriecheck.ui.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.lpai.caloriecheck.ui.dashboard.Food;
import com.lpai.caloriecheck.ui.dashboard.FoodDao;
import com.lpai.caloriecheck.ui.exercises.Exercise;
import com.lpai.caloriecheck.ui.exercises.ExerciseDao;
import com.lpai.caloriecheck.ui.exercises.ExerciseSet;

@Database(entities={Food.class, Exercise.class, ExerciseSet.class},version = 3,exportSchema = false)
public abstract class AppRoomDatabase extends RoomDatabase {
    public abstract FoodDao foodDao();
    public abstract ExerciseDao exerciseDao();
    public  static AppRoomDatabase INSTANCE;
    public static AppRoomDatabase getDatabase(final Context context){
        if(INSTANCE==null){
            synchronized (AppRoomDatabase.class){
                if (INSTANCE==null){
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(), AppRoomDatabase.class,"database")
                            .fallbackToDestructiveMigration().addCallback(roomDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }


    public static Callback roomDatabaseCallback =
            new Callback(){
                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };


    public static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        public final ExerciseDao dao;
        String[] exercises = {"Over Head Press", "Deadlift", "Pull-up", };

        PopulateDbAsync(AppRoomDatabase db) {
            dao = db.exerciseDao();
        }

        @Override
        public Void doInBackground(final Void... params) {
//             Start the app with a clean database every time.
//             Not needed if you only populate the database
//             when it is first created
            dao.deleteAll();

            for (int i = 0; i < exercises.length ; i++) {
                Exercise exercise = new Exercise(exercises[i]);
                dao.insert(exercise);
            }
            return null;
        }
    }
}


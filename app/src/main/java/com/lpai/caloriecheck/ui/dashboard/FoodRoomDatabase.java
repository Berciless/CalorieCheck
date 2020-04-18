package com.lpai.caloriecheck.ui.dashboard;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities={Food.class},version = 1,exportSchema = false)
public abstract class FoodRoomDatabase extends RoomDatabase {
    public abstract FoodDao foodDao();
    private  static FoodRoomDatabase INSTANCE;

    public static FoodRoomDatabase getDatabase(final Context context){
        if(INSTANCE==null){
            synchronized (FoodRoomDatabase.class){
                if (INSTANCE==null){
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(),FoodRoomDatabase.class,"database")
                            .fallbackToDestructiveMigration().addCallback(roomDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }


    private static RoomDatabase.Callback roomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };


    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final FoodDao dao;
        String[] foods = {"dolphin", "crocodile", "cobra"};

        PopulateDbAsync(FoodRoomDatabase db) {
            dao = db.foodDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate the database
            // when it is first created
            dao.deleteAll();

            for (int i = 0; i < foods.length ; i++) {
                Food food = new Food("Apple Pie",20,100,15,600);
                dao.insert(food);
            }
            return null;
        }
    }
}


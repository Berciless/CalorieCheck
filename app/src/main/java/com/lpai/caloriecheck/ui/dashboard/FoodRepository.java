package com.lpai.caloriecheck.ui.dashboard;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.ListIterator;

public class FoodRepository {
    private FoodDao foodDao;
    private LiveData<List<Food>> todaySFood;

    FoodRepository(Application application) {
        FoodRoomDatabase db = FoodRoomDatabase.getDatabase(application);
        foodDao = db.foodDao();
        todaySFood = foodDao.getTodaySFood();
    }

    LiveData<List<Food>> getTodaySFood() {
        return todaySFood;
    }

    public void insert(Food food) {
        new insertAsyncTask(foodDao).execute(food);
    }

    private static class insertAsyncTask extends AsyncTask<Food, Void, Void> {
        private FoodDao asyncTaskDao;

        insertAsyncTask(FoodDao dao) {
            asyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(final Food... params){
            asyncTaskDao.insert(params[0]);
            return null;
        }

    }
}

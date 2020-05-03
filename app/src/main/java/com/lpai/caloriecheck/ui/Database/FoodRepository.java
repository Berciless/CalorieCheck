package com.lpai.caloriecheck.ui.Database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.lpai.caloriecheck.ui.dashboard.Food;
import com.lpai.caloriecheck.ui.dashboard.FoodDao;

import java.util.List;

public class FoodRepository {
    private FoodDao foodDao;
    private LiveData<List<Food>> todaySFood;

    public FoodRepository(Application application) {
        AppRoomDatabase db = AppRoomDatabase.getDatabase(application);
        foodDao = db.foodDao();
        todaySFood = foodDao.getTodaySFood();
    }

    public LiveData<List<Food>> getTodaySFood() {
        return todaySFood;
    }

    public void insert(Food food) {
        new insertAsyncTask(foodDao).execute(food);
    }
    public static class insertAsyncTask extends AsyncTask<Food, Void, Void> {
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

    public void deleteAll(){
        new deleteAllAsyncTask(foodDao).execute();
    }

    public static class deleteAllAsyncTask extends AsyncTask<Void, Void, Void>{
        private FoodDao asyncTaskDao;
        deleteAllAsyncTask(FoodDao dao){ asyncTaskDao=dao; }
        @Override
        protected Void doInBackground(Void... voids){
            asyncTaskDao.deleteAll();
            return null;
        }
    }

}

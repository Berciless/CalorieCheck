package com.lpai.caloriecheck.ui.Database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.lpai.caloriecheck.ui.ExerciseScreen.ExerciseSet;
import com.lpai.caloriecheck.ui.exercises.ExerciseDao;

import java.util.List;

public class SetsRepository  {
    private ExerciseDao exerciseDao;
    private LiveData<List<ExerciseSet>> sets;

    public SetsRepository(Application application) {
        AppRoomDatabase db = AppRoomDatabase.getDatabase(application);
        exerciseDao = db.exerciseDao();
//        sets = exerciseDao.getAllSets();
    }

    public LiveData<List<ExerciseSet>> getSetsForExercise(long id) {
        return exerciseDao.getSetsForExercise(id);
    }


    public void insert(ExerciseSet set) {
        new insertAsyncTask(exerciseDao).execute(set);
    }


    public static class insertAsyncTask extends AsyncTask<ExerciseSet, Void, Void> {
        private ExerciseDao asyncTaskDao;

        insertAsyncTask(ExerciseDao dao) {
            asyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(final ExerciseSet... params){
            asyncTaskDao.insertSet(params[0]);
            return null;
        }

    }

    public void deleteSetsByExercise(long id){ new deleteByExerciseAsyncTask(exerciseDao).execute(id);}
    public static class deleteByExerciseAsyncTask extends AsyncTask<Long, Void, Void>{
        private ExerciseDao asyncTaskDao;
        deleteByExerciseAsyncTask(ExerciseDao dao){asyncTaskDao=dao;}
        @Override
        protected Void doInBackground(Long... longs) {
            asyncTaskDao.deleteSetByExercise(longs[0]);
            return null;
        }
    }


    public void deleteSetById(long id) { new deleteSetByIdAsyncTask(exerciseDao).execute(id); }
    public static class deleteSetByIdAsyncTask extends  AsyncTask<Long,Void,Void>{
        private ExerciseDao asyncTaskDao;
        deleteSetByIdAsyncTask(ExerciseDao dao){ asyncTaskDao=dao;}
        @Override
        protected Void doInBackground(Long... longs) {
            asyncTaskDao.deleteSetById(longs[0]);
            return null;
        }
    }

    public void deleteAllSets(){
        new deleteAllAsyncTask(exerciseDao).execute();
    }

    public static class deleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
        private ExerciseDao asyncTaskDao;
        deleteAllAsyncTask(ExerciseDao dao){ asyncTaskDao=dao; }
        @Override
        protected Void doInBackground(Void... voids){
            asyncTaskDao.deleteAllSets();
            return null;
        }
    }

}


package com.lpai.caloriecheck.ui.Database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.lpai.caloriecheck.ui.exercises.Exercise;
import com.lpai.caloriecheck.ui.exercises.ExerciseDao;

import java.util.List;

public class ExercisesRepository {
    private ExerciseDao exerciseDao;
    private LiveData<List<Exercise>> exercise;

    public ExercisesRepository(Application application) {
        AppRoomDatabase db = AppRoomDatabase.getDatabase(application);
        exerciseDao = db.exerciseDao();
        exercise = exerciseDao.getExercise();
    }

    public LiveData<List<Exercise>> getTodaySExercise() {
        return exercise;
    }

    public void insert(Exercise exercise) {
        new insertAsyncTask(exerciseDao).execute(exercise);
    }

    public static class insertAsyncTask extends AsyncTask<Exercise, Void, Void> {
        private ExerciseDao asyncTaskDao;

        insertAsyncTask(ExerciseDao dao) {
            asyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(final Exercise... params){
            asyncTaskDao.insertExercise(params[0]);
            return null;
        }

    }


    public void deleteExerciseById(long id) { new deleteByIdAsyncTask(exerciseDao).execute(id); }
    public class deleteByIdAsyncTask extends AsyncTask<Long, Void, Void>{
        private ExerciseDao asyncTaskDao;

        deleteByIdAsyncTask(ExerciseDao dao){asyncTaskDao=dao;}
        @Override
        protected Void doInBackground(Long... longs) {
            asyncTaskDao.deleteExerciseById(longs[0]);
            return null;
        }
    }

    public void deleteAll(){ new deleteAllAsyncTask(exerciseDao).execute(); }
    public static class deleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
        private ExerciseDao asyncTaskDao;
        deleteAllAsyncTask(ExerciseDao dao){ asyncTaskDao=dao; }
        @Override
        protected Void doInBackground(Void... voids){
            asyncTaskDao.deleteAllExercises();
            return null;
        }
    }

}

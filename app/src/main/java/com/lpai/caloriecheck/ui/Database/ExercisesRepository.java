package com.lpai.caloriecheck.ui.Database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.lpai.caloriecheck.ui.exercises.Exercise;
import com.lpai.caloriecheck.ui.exercises.ExerciseDao;

import java.util.List;

public class ExercisesRepository {
    private ExerciseDao exerciseDao;
    private LiveData<List<Exercise>> todaySExercise;

    public ExercisesRepository(Application application) {
        AppRoomDatabase db = AppRoomDatabase.getDatabase(application);
        exerciseDao = db.exerciseDao();
        todaySExercise = exerciseDao.getTodaySExercise();
    }

    public LiveData<List<Exercise>> getTodaySExercise() {
        return todaySExercise;
    }

    public void insert(Exercise exercise) {
        new insertAsyncTask(exerciseDao).execute(exercise);
    }

    public void deleteAll(){
        new deleteAllAsyncTask(exerciseDao).execute();
    }

    public static class deleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
        private ExerciseDao asyncTaskDao;
        deleteAllAsyncTask(ExerciseDao dao){ asyncTaskDao=dao; }
        @Override
        protected Void doInBackground(Void... voids){
            asyncTaskDao.deleteAll();
            return null;
        }
    }

    public static class insertAsyncTask extends AsyncTask<Exercise, Void, Void> {
        private ExerciseDao asyncTaskDao;

        insertAsyncTask(ExerciseDao dao) {
            asyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(final Exercise... params){
            asyncTaskDao.insert(params[0]);
            return null;
        }

    }
}

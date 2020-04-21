package com.lpai.caloriecheck.ui.exercises;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.lpai.caloriecheck.ui.Database.ExercisesRepository;

import java.util.List;

public class ExercisesViewModel extends AndroidViewModel {

    private ExercisesRepository repository;

    private LiveData<List<Exercise>> todaySExercise;
    public ExercisesViewModel(Application application){
        super(application);
        repository = new ExercisesRepository(application);
        todaySExercise = new MutableLiveData<>();
        todaySExercise = repository.getTodaySExercise();

    }

    public LiveData<List<Exercise>> getTodaySExercise(){ return todaySExercise; }

    public void insert(Exercise exercise){repository.insert(exercise);}

    public void deleteAll(){repository.deleteAll();}

}
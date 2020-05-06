package com.lpai.caloriecheck.ui.ExerciseScreen;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.lpai.caloriecheck.ui.Database.SetsRepository;

import java.util.List;

public class SetViewModel extends AndroidViewModel {
    private SetsRepository repository;

    private LiveData<List<ExerciseSet>> sets;
    public SetViewModel(Application application){
        super(application);
        repository = new SetsRepository(application);
        sets = new MutableLiveData<>();
    }

    LiveData<List<ExerciseSet>> getSets(long id){
       return repository.getSetsForExercise(id);
    }

    public void insert(ExerciseSet set){repository.insert(set);}

    void deleteSetById(long id){repository.deleteSetById(id);}

}

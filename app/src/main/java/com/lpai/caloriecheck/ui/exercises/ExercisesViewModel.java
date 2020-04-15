package com.lpai.caloriecheck.ui.exercises;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ExercisesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ExercisesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Fragment de exercitii");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
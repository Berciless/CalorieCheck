package com.lpai.caloriecheck.ui.home;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lpai.caloriecheck.ui.Database.FoodRepository;
import com.lpai.caloriecheck.ui.dashboard.Food;

import java.util.List;

public class HomeViewModel extends AndroidViewModel {

    public FoodRepository repository;
    private LiveData<List<Food>> todaySFood;
    public HomeViewModel(Application application) {
        super(application);
        repository = new FoodRepository(application);
        todaySFood = new MutableLiveData<>();
        todaySFood = repository.getTodaySFood();
    }

    public Food getTotalIntake(){return repository.getTotal();}

}
package com.lpai.caloriecheck.ui.dashboard;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lpai.caloriecheck.ui.Database.FoodRepository;

import java.util.List;

public class DashboardViewModel extends AndroidViewModel {

    private FoodRepository repository;

    private LiveData<List<Food>> todaySFood;
    public DashboardViewModel(Application application){
        super(application);
        repository = new FoodRepository(application);
        todaySFood = new MutableLiveData<>();
        todaySFood = repository.getTodaySFood();

    }

    public LiveData<List<Food>> getTodaySFood(){ return todaySFood; }

    public void insert(Food food){repository.insert(food);}

    public void deleteAll(){repository.deleteAll();}

}

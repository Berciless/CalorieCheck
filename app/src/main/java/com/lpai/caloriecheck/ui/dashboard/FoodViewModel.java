package com.lpai.caloriecheck.ui.dashboard;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class FoodViewModel extends AndroidViewModel {
    private FoodRepository repository;
    private LiveData<List<Food>> todaySFood;
    public FoodViewModel(Application application){
        super(application);
        repository=new FoodRepository(application);
        todaySFood = repository.getTodaySFood();
    }
    LiveData<List<Food>> getTodaySFood(){
        return todaySFood;
    }
    public void insert(Food food){repository.insert(food);}

}

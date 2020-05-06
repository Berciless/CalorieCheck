package com.lpai.caloriecheck.ui.home;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import com.lpai.caloriecheck.R;
import com.lpai.caloriecheck.ui.Database.FoodRepository;
import com.lpai.caloriecheck.ui.dashboard.Food;
import com.lpai.caloriecheck.ui.dashboard.FoodDao;
import com.lpai.caloriecheck.ui.dashboard.TotalIntake;
import com.lpai.caloriecheck.ui.exercises.AddExerciseActivity;

import java.util.Objects;

import static android.app.Activity.RESULT_OK;

public class HomeFragment extends Fragment  {
    private static final int SET_TARGET_REQUEST_CODE = 3;
    HomeViewModel homeViewModel;
    ProgressBar progress;
    ProgressBar progressCarbs;
    ProgressBar progressFat;
    ProgressBar progressProtein;
    Button setTargetBtn;
//    TextView calories;
//    TextView protein;
//    TextView carbs;
//    TextView fat;
//    TextView targetCalories;
//    TextView targetProtein;
//    TextView targetCarbs;
//    TextView targetFat;
//    Food total;
//    TotalIntake target;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        setTargetBtn= (Button) root.findViewById(R.id.set_target_btn);
        progress = (ProgressBar) root.findViewById(R.id.progress_Bar);
        progressCarbs = (ProgressBar) root.findViewById(R.id.progressBar_carbs);
        progressFat = (ProgressBar) root.findViewById(R.id.progressBar_fats);
        progressProtein = (ProgressBar) root.findViewById(R.id.progressBar_protein);

//        calories = (TextView) root .findViewById(R.id.calories_progre_value);
//        protein = (TextView) root .findViewById(R.id.protein_progre_value);
//        carbs = (TextView) root .findViewById(R.id.carbs_progre_value);
//        fat = (TextView) root .findViewById(R.id.fat_progre_value);
//        targetCalories = (TextView) root .findViewById(R.id.calories_target_value);
//        targetProtein = (TextView) root .findViewById(R.id.protein_target_value);
//        targetCarbs = (TextView) root .findViewById(R.id.carbs_target_value);
//        targetFat = (TextView) root .findViewById(R.id.fat_target_value);

        setTargetBtn.setOnClickListener(
                v->{
                    Intent intent = new Intent(getActivity(), SetTargetActivity.class);
                    startActivityForResult(intent,SET_TARGET_REQUEST_CODE);
                }
        );
        AsyncTask<Void, Void, Void> task = new getTotalAsyncTask(homeViewModel.repository,
                progress, progressProtein, progressCarbs, progressFat).execute();
        return root;
    }
    @Override
    public void onStart() {
        super.onStart();
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == SET_TARGET_REQUEST_CODE && resultCode == RESULT_OK) {
            TotalIntake newTarget= new TotalIntake(
                    data.getExtras().getDouble("calories"),
                    data.getExtras().getDouble("protein"),
                    data.getExtras().getDouble("carbs"),
                    data.getExtras().getDouble("fat")
            );
            homeViewModel.repository.insertTarget(newTarget);
            new getTotalAsyncTask(homeViewModel.repository,
                    progress,progressProtein,progressCarbs,progressFat).execute();
        }
    }
    public static class getTotalAsyncTask extends AsyncTask<Void,Void,Void>{
        private FoodRepository asyncTask;
        ProgressBar progressCalories;
        ProgressBar progressProtein;
        ProgressBar progressCarbs;
        ProgressBar progressFat;

        getTotalAsyncTask(FoodRepository fr,
                          ProgressBar calories,ProgressBar protein,ProgressBar carbs,ProgressBar fat) {
            this.asyncTask=fr;
            this.progressCalories=calories;
            this.progressProtein=protein;
            this.progressCarbs=carbs;
            this.progressFat=fat;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            Food total= asyncTask.getTotal();
            TotalIntake target =asyncTask.getTarget();
            progressCalories.setMax((int) Math.ceil(target.calories));
            progressProtein.setMax((int) Math.ceil(target.proteins));
            progressCarbs.setMax((int) Math.ceil(target.carbs));
            progressFat.setMax((int) Math.ceil(target.fat));
            progressCalories.setProgress((int) Math.ceil(total.calories));
            progressProtein.setProgress((int) Math.ceil(total.proteins));
            progressCarbs.setProgress((int) Math.ceil(total.carbs));
            progressFat.setProgress((int) Math.ceil(total.fat));
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}

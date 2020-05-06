package com.lpai.caloriecheck.ui.dashboard;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lpai.caloriecheck.MainActivity;
import com.lpai.caloriecheck.R;

import java.sql.SQLOutput;
import java.util.Objects;

import static android.app.Activity.RESULT_OK;


public class DashboardFragment extends Fragment implements FoodListAdapter.DeleteFoodListener {

    private static final int ADD_FOOD_REQUEST_CODE = 1;

    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.recyclerview);
        FoodListAdapter adapter = new FoodListAdapter(this.getActivity(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        dashboardViewModel.getTodaySFood().observe(getViewLifecycleOwner(), adapter::setFoods);
        Button button = root.findViewById(R.id.addFoodBtn);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(),AddFoodActivity.class);
            startActivityForResult(intent,ADD_FOOD_REQUEST_CODE);
        });

        return root;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == ADD_FOOD_REQUEST_CODE && resultCode == RESULT_OK){
            assert data != null;
            Food food = new Food(
                    Objects.requireNonNull(data.getExtras()).getString("name"),
                    data.getExtras().getDouble("protein"),
                    data.getExtras().getDouble("carbs"),
                    data.getExtras().getDouble("fat"),
                    data.getExtras().getDouble("calories")
                    );
            dashboardViewModel.insert(food);
        }
    }

    @Override
    public void onDeletePressed(long id) {
        dashboardViewModel.deleteFoodById(id);
    }
}

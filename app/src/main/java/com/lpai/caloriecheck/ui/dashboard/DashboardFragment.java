package com.lpai.caloriecheck.ui.dashboard;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lpai.caloriecheck.MainActivity;
import com.lpai.caloriecheck.R;

import java.sql.SQLOutput;


public class DashboardFragment extends Fragment {


    DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);

        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.recyclerview);

        FoodListAdapter adapter = new FoodListAdapter(this.getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        dashboardViewModel.getTodaySFood().observe(getViewLifecycleOwner(), adapter::setFoods);

        Button button = root.findViewById(R.id.button);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(),AddFoodActivity.class);
            startActivityForResult(intent,1);
        });



        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == 1 && resultCode == -1){
            Food food = new Food(data.getStringExtra(AddFoodActivity.EXTRA_REPLY));
            dashboardViewModel.insert(food);
        } else {
            Toast.makeText(
                    getActivity().getApplicationContext(),
                    R.string.fat,
                    Toast.LENGTH_LONG).show();
        }
    }

}

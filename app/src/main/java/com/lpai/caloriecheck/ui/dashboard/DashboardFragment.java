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

import static android.app.Activity.RESULT_OK;


public class DashboardFragment extends Fragment implements View.OnClickListener {

    public static final int ADD_FOOD_REQUEST_CODE = 1;

    DashboardViewModel dashboardViewModel;
    TextView totalCalories;
    Button deleteAllBtn;
    RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);

        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerview);

        FoodListAdapter adapter = new FoodListAdapter(this.getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));



        dashboardViewModel.getTodaySFood().observe(getViewLifecycleOwner(), adapter::setFoods);
        totalCalories=(TextView) root.findViewById(R.id.totalCalories);


        deleteAllBtn=(Button) root.findViewById(R.id.button3);
        deleteAllBtn.setOnClickListener(e->dashboardViewModel.deleteAll());

        Button button = root.findViewById(R.id.button);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(),AddFoodActivity.class);
            startActivityForResult(intent,ADD_FOOD_REQUEST_CODE);
        });

        return root;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void onClick(View v) {
        int pos = recyclerView.indexOfChild(v);
        Toast.makeText(
                getActivity().getApplicationContext(),
                "aiapasat"+pos,
                Toast.LENGTH_LONG).show();

    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == ADD_FOOD_REQUEST_CODE && resultCode == RESULT_OK){
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

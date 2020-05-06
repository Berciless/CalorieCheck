package com.lpai.caloriecheck.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.lpai.caloriecheck.R;
import com.lpai.caloriecheck.ui.Database.FoodRepository;
import com.lpai.caloriecheck.ui.dashboard.Food;

public class SetTargetDialog  extends DialogFragment {

    private TargetDialogListener listener;
    FoodRepository repository;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_set_target,container,false);
        EditText calories = (EditText) v.findViewById(R.id.calories_target);
        EditText protein = (EditText) v.findViewById(R.id.protein_target);
        EditText carbs = (EditText) v.findViewById(R.id.carbs_target);
        EditText fat = (EditText) v.findViewById(R.id.fat_target);
        Button addBtn = v.findViewById(R.id.confirm_target);
        Button cancelSetBtn = v.findViewById(R.id.cancel_target);

        addBtn.setOnClickListener(v1 -> {
            if(TextUtils.isEmpty(fat.getText()) || TextUtils.isEmpty(protein.getText()) || TextUtils.isEmpty(carbs.getText())) {
                Toast.makeText(getContext(),"Protein, carbs and fat amounts are necessary",Toast.LENGTH_LONG).show();

            }else{
                listener.onAddClicked(
                        Double.parseDouble(calories.getText().toString()),
                        Double.parseDouble(protein.getText().toString()),
                        Double.parseDouble(carbs.getText().toString()),
                        Double.parseDouble(fat.getText().toString())
                );
            }
            dismiss();
        });

        cancelSetBtn.setOnClickListener(v12 -> dismiss());


        return v;
    }

    public interface TargetDialogListener {
        void onAddClicked(double caloreis, double protein, double carbs, double fat);
    }
}

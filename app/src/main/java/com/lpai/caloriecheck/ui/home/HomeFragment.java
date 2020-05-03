package com.lpai.caloriecheck.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import com.lpai.caloriecheck.R;

public class HomeFragment extends Fragment {

    ProgressBar progress;
    ProgressBar progressCarbs;
    ProgressBar progressFat;
    ProgressBar progressProtein;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        progress = (ProgressBar) root.findViewById(R.id.progressBar);
        progress.setProgress(800);
        progress.setMax(1000);

        progressCarbs = (ProgressBar) root.findViewById(R.id.progressBar_carbs);
        progressCarbs.setProgress(400);
        progressCarbs.setMax(1000);

        progressFat = (ProgressBar) root.findViewById(R.id.progressBar_fats);
        progressFat.setProgress(250);
        progressFat.setMax(1000);

        progressProtein = (ProgressBar) root.findViewById(R.id.progressBar_protein);
        progressProtein.setProgress(90);
        progressProtein.setMax(1000);

        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), s -> textView.setText(s));
        return root;
    }
}

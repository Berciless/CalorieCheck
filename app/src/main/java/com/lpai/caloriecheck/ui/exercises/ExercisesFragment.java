package com.lpai.caloriecheck.ui.exercises;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.lpai.caloriecheck.R;

public class ExercisesFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ExercisesViewModel exercisesViewModel = new ViewModelProvider(this).get(ExercisesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_exercisees, container, false);
        final TextView textView = root.findViewById(R.id.text_exercises);
        exercisesViewModel.getText().observe(getViewLifecycleOwner(), s -> textView.setText(s));
        return root;
    }
}

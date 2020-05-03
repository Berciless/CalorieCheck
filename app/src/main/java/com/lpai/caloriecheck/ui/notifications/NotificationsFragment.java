package com.lpai.caloriecheck.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.lpai.caloriecheck.R;

public class NotificationsFragment extends Fragment {

    Spinner spinner;
    Spinner spinnerSex;
    EditText age;
    EditText weight;
    EditText height;
    TextView results;
    Button btnResults;
    View root;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        NotificationsViewModel notificationsViewModel = new ViewModelProvider(this).get(NotificationsViewModel.class);

        root = inflater.inflate(R.layout.fragment_notifications, container, false);

        spinner = (Spinner) root.findViewById(R.id.activitySpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity().getBaseContext(),
                R.array.activity_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinnerSex = (Spinner) root.findViewById(R.id.sex_spinner);
        ArrayAdapter<CharSequence> adapterSex = ArrayAdapter.createFromResource(getActivity().getBaseContext(),
                R.array.sex_array, android.R.layout.simple_spinner_item);
        adapterSex.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSex.setAdapter(adapterSex);

        weight = (EditText) root.findViewById(R.id.weight);
        height = (EditText) root.findViewById(R.id.height);
        age = (EditText) root.findViewById(R.id.age);
        btnResults = (Button) root.findViewById(R.id.buttonResults);
        results = (TextView) root.findViewById(R.id.textResult);
        btnResults.setOnClickListener(v -> {
            double W = Double.parseDouble(weight.getText().toString());
            double H = Double.parseDouble(height.getText().toString());
            double A = Double.parseDouble(age.getText().toString());
            results.setText(String.valueOf(BMR(W,H,A)));
        });



        final TextView textView = root.findViewById(R.id.text_notifications);
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), s -> textView.setText(s));
        return root;
    }

    public double BMR(double W, double H, double A) {
        String text = spinnerSex.getSelectedItem().toString();
        System.out.println(text);

        if (text.equals("Male")) {
            return 10*W + 6.25*H - 5*A +5;
        } else {
            return  10*W + 6.25*H - 5*A -161;
        }
    }

}

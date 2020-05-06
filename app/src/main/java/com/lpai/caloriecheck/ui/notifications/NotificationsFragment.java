package com.lpai.caloriecheck.ui.notifications;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.lpai.caloriecheck.R;

public class NotificationsFragment extends Fragment {

    private Spinner spinnerSex;
    private EditText age;
    private EditText weight;
    private EditText height;
    private TextView results;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        Spinner spinner = (Spinner) root.findViewById(R.id.activitySpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(requireActivity().getBaseContext(),
                R.array.activity_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinnerSex = (Spinner) root.findViewById(R.id.sex_spinner);
        ArrayAdapter<CharSequence> adapterSex = ArrayAdapter.createFromResource(requireActivity().getBaseContext(),
                R.array.sex_array, android.R.layout.simple_spinner_item);
        adapterSex.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSex.setAdapter(adapterSex);

        weight = (EditText) root.findViewById(R.id.weight);
        height = (EditText) root.findViewById(R.id.height);
        age = (EditText) root.findViewById(R.id.age);
        Button btnResults = (Button) root.findViewById(R.id.buttonResults);
        results = (TextView) root.findViewById(R.id.textResult);
        btnResults.setOnClickListener(v -> {
            if(TextUtils.isEmpty(weight.getText()) || TextUtils.isEmpty(height.getText()) || TextUtils.isEmpty(weight.getText())){
                Toast.makeText(getContext(),"Weight, height and age are mandatory",Toast.LENGTH_LONG).show();
            }
            else {
                double W = Double.parseDouble(weight.getText().toString());
                double H = Double.parseDouble(height.getText().toString());
                double A = Double.parseDouble(age.getText().toString());
                results.setText(String.valueOf(BMR(W, H, A)));
            }
        });
        return root;
    }
    private double BMR(double W, double H, double A) {

        String text = spinnerSex.getSelectedItem().toString();
        if (text.equals("Male")) {
            return 10*W + 6.25*H - 5*A +5;
        } else {
            return  10*W + 6.25*H - 5*A -161;
        }
    }

}

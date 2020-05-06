package com.lpai.caloriecheck.ui.ExerciseScreen;


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

public class AddSetFragment extends DialogFragment {

    private DialogListener listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_add_set,container,false);
        EditText reps = (EditText) v.findViewById(R.id.newSetReps);
        EditText weight = (EditText) v.findViewById(R.id.newSetWeight);
        Button addBtn = v.findViewById(R.id.confirm_set);
        Button cancelSetBtn = v.findViewById(R.id.cancel_set);
        addBtn.setOnClickListener(v1 -> {
            if(!TextUtils.isEmpty(reps.getText()) && !TextUtils.isEmpty(weight.getText())) {
                listener.onAddClicked(Integer.parseInt(reps.getText().toString()), Double.parseDouble(weight.getText().toString()));
            }else{
                Toast.makeText(getContext(),"Introduce number of repetitions AND weight",Toast.LENGTH_LONG).show();
            }
            dismiss();
        });
        cancelSetBtn.setOnClickListener(v12 -> dismiss());
        return v;
    }

    public interface DialogListener {
        void onAddClicked(int reps, double weight);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (DialogListener) context;

    }
}

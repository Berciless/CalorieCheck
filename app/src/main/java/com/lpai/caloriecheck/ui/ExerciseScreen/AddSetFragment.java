package com.lpai.caloriecheck.ui.ExerciseScreen;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
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
            if(!reps.getText().toString().equals("")  && !weight.getText().toString().equals("")) {
                listener.onAddClicked(Integer.parseInt(reps.getText().toString()), Double.parseDouble(weight.getText().toString()));
            }else{
                Toast.makeText(getContext(),"Introduce number of repetitions AND weight",Toast.LENGTH_LONG).show();
            }
            dismiss();
        });

        cancelSetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


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

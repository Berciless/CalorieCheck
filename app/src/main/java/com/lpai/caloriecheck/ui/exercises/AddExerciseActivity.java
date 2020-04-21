package com.lpai.caloriecheck.ui.exercises;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.lpai.caloriecheck.R;

public class AddExerciseActivity  extends AppCompatActivity {

    public static final String EXTRA_REPLY="REPLY";
    private EditText newExercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);
        newExercise =findViewById(R.id.newExerciseName);

        final Button button =findViewById(R.id.confirmNewEserciseBtn);
        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if(TextUtils.isEmpty(newExercise.getText())){
                setResult(RESULT_CANCELED,replyIntent);
            } else{
                String name = newExercise.getText().toString();
                replyIntent.putExtra(EXTRA_REPLY,name);
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });

    }
}

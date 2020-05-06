package com.lpai.caloriecheck.ui.exercises;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        Button button =findViewById(R.id.confirmNewEserciseBtn);
        button.setOnClickListener(view -> {
            if(TextUtils.isEmpty(newExercise.getText())){
                Toast.makeText(getApplicationContext(),"You must set a name for the exercise",Toast.LENGTH_LONG).show();
            } else{

                Intent replyIntent = new Intent();
                String name = newExercise.getText().toString();
                replyIntent.putExtra(EXTRA_REPLY,name);
                setResult(RESULT_OK, replyIntent);
                finish();

            }
        });

    }
}

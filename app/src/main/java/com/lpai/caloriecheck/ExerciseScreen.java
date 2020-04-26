package com.lpai.caloriecheck;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ActivityNavigator;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ExerciseScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_screen);
        TextView name =(TextView) findViewById(R.id.exercise_name);
    }
}

package com.lpai.caloriecheck.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.lpai.caloriecheck.R;

public class SetTargetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_target);

        EditText targetCalories= (EditText) findViewById(R.id.calories_target);
        EditText targetProtein= (EditText) findViewById(R.id.protein_target);
        EditText targetCarbs= (EditText) findViewById(R.id.carbs_target);
        EditText targetFat= (EditText) findViewById(R.id.fat_target);
        Button addBtn = findViewById(R.id.confirm_target);
        Button cancelSetBtn = findViewById(R.id.cancel_target);
        Intent replyIntent = new Intent();

        cancelSetBtn.setOnClickListener(v -> {
            finish();
        });

        addBtn.setOnClickListener(v -> {
            double cals;
            double prots;
            double carbs;
            double fat;

            if(TextUtils.isEmpty(targetProtein.getText())){
                prots = 0;
            }else {
                prots = Double.parseDouble(targetProtein.getText().toString());
            }

            if(TextUtils.isEmpty(targetCarbs.getText())){
                carbs = 0;
            }else {
                carbs = Double.parseDouble(targetCarbs.getText().toString());
            }

            if(TextUtils.isEmpty(targetFat.getText())){
                fat = 0;
            }else {
                fat = Double.parseDouble(targetFat.getText().toString());
            }

            if(TextUtils.isEmpty(targetCalories.getText())){
                cals= prots*4+carbs*4+fat*9;
            }
            else{
                cals= Double.parseDouble(targetCalories.getText().toString());
            }

            Bundle bundle = new Bundle();
            bundle.putDouble("protein",prots);
            bundle.putDouble("carbs",carbs);
            bundle.putDouble("fat",fat);
            bundle.putDouble("calories",cals);
            replyIntent.putExtras(bundle);
            setResult(RESULT_OK, replyIntent);
            finish();

        });



    }
}

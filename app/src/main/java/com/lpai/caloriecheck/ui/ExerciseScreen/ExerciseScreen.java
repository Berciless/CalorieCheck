package com.lpai.caloriecheck.ui.ExerciseScreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.lpai.caloriecheck.R;

public class ExerciseScreen extends AppCompatActivity implements AddSetFragment.DialogListener , SetListAdapter.DeleteItemListener {

    private SetViewModel setViewModel;

    long id;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_screen);
        name=getIntent().getExtras().getString("exerciseName");
        id = getIntent().getExtras().getLong("exerciseId");

        RecyclerView recyclerView =findViewById(R.id.Exerccise_recyclerview);
        SetListAdapter adapter = new SetListAdapter(this,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        TextView exerciseName = findViewById(R.id.exercise_title);
        exerciseName.setText(name);

        setViewModel = new ViewModelProvider(this).get(SetViewModel.class);
        setViewModel.getSets(id).observe(this, adapter::setSets);

        Button addSetBtn = findViewById(R.id.addSetBtn);
        addSetBtn.setOnClickListener(v -> {
                    AddSetFragment dialog = new AddSetFragment();
                    dialog.show(getSupportFragmentManager(),"addSetDialog");
                }
        );
    }

    @Override
    public void onAddClicked(int reps, double weight) {
        setViewModel.insert(new ExerciseSet(id,reps,weight));
    }

    @Override
    public void onDeletePressed(long id) {
        setViewModel.deleteSetById(id);
    }
}

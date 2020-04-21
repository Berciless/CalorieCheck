package com.lpai.caloriecheck.ui.exercises;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.lpai.caloriecheck.R;
import static android.app.Activity.RESULT_OK;


public class ExercisesFragment extends Fragment implements View.OnClickListener {

    public static final int ADD_EXERCISE_REQUEST_CODE = 2;

    ExercisesViewModel exercisesViewModel;
    TextView totalCalories;
    Button deleteAllBtn;
    RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        exercisesViewModel = new ViewModelProvider(this).get(ExercisesViewModel.class);

        View root = inflater.inflate(R.layout.fragment_exercises, container, false);

        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerview);

        ExerciseListAdapter adapter = new ExerciseListAdapter(this.getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));



        exercisesViewModel.getTodaySExercise().observe(getViewLifecycleOwner(), adapter::setExercises);
        totalCalories=(TextView) root.findViewById(R.id.totalCalories);


        deleteAllBtn=(Button) root.findViewById(R.id.deleteExercises);
        deleteAllBtn.setOnClickListener(e->exercisesViewModel.deleteAll());

        Button button = root.findViewById(R.id.addExerciseBtn);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(),AddExerciseActivity.class);
            startActivityForResult(intent,ADD_EXERCISE_REQUEST_CODE);
        });

        return root;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void onClick(View v) {
        int pos = recyclerView.indexOfChild(v);
        Toast.makeText(
                getActivity().getApplicationContext(),
                "aiapasat"+pos,
                Toast.LENGTH_LONG).show();

    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == ADD_EXERCISE_REQUEST_CODE && resultCode == RESULT_OK){
            Exercise exercise = new Exercise(data.getStringExtra(AddExerciseActivity.EXTRA_REPLY));
            exercisesViewModel.insert(exercise);



        } else {
            Toast.makeText(
                    getActivity().getApplicationContext(),
                    R.string.fat,
                    Toast.LENGTH_LONG).show();
        }
    }

}

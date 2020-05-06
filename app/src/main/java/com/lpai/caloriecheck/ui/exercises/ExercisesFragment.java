package com.lpai.caloriecheck.ui.exercises;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.lpai.caloriecheck.R;
import static android.app.Activity.RESULT_OK;

public class ExercisesFragment extends Fragment implements  ExerciseListAdapter.DeleteItemListener  {

    private static final int ADD_EXERCISE_REQUEST_CODE = 2;
    private ExercisesViewModel exercisesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        exercisesViewModel = new ViewModelProvider(this).get(ExercisesViewModel.class);

        View root = inflater.inflate(R.layout.fragment_exercises, container, false);

        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.recyclerview);

        ExerciseListAdapter adapter = new ExerciseListAdapter(this.getActivity(),this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        exercisesViewModel.getTodaySExercise().observe(getViewLifecycleOwner(), adapter::setExercises);

        Button addExerciseBtn = root.findViewById(R.id.addExerciseBtn);
        addExerciseBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(),AddExerciseActivity.class);
            startActivityForResult(intent,ADD_EXERCISE_REQUEST_CODE);
        });

        return root;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == ADD_EXERCISE_REQUEST_CODE && resultCode == RESULT_OK){
            assert data != null;
            Exercise exercise = new Exercise(data.getStringExtra(AddExerciseActivity.EXTRA_REPLY));
            exercisesViewModel.insert(exercise);
        }
    }

    @Override
    public void onDeletePressed(long id) {
        exercisesViewModel.deleteExercise(id);

    }

//    @Override
//    public void onEditPressed(long id) {
//        exercisesViewModel.deleteExercise(id);
//    }
}

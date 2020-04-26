package com.lpai.caloriecheck.ui.exercises;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.lpai.caloriecheck.R;

import java.util.List;

public class ExerciseListAdapter extends RecyclerView.Adapter<ExerciseListAdapter.ExerciseViewHolder>{
    private final LayoutInflater inflater;
    private List<Exercise> exercises; // Cached copy of exercises
    Context context;
    ExerciseListAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.context=context;
    }
    public View.OnClickListener clickListener;

    @Override
    public ExerciseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.exercise_item, parent, false);
        return new ExerciseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ExerciseViewHolder holder, int position) {
        if (exercises != null) {
            Exercise exerciseItem = exercises.get(position);
            holder.txtName.setText(exerciseItem.name);

            holder.itemView.setOnClickListener(v ->{
                long id=exerciseItem.exerciseId;
                Bundle bundle = new Bundle();
                bundle.putString("exerciseName",exerciseItem.name);
//                bundle.putLong("exerciseId",exerciseItem.exerciseId);
                Navigation.findNavController(holder.itemView).navigate(R.id.action_navigation_exercises_to_exerciseScreen,bundle);

            });
        } else {
            holder.txtName.setText("ERROR");

        }
    }

    void setExercises(List<Exercise> exercisesToSet){
        exercises = exercisesToSet;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mExercises has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (exercises != null)
            return exercises.size();
        else return 0;
    }

    class ExerciseViewHolder extends RecyclerView.ViewHolder  {
        TextView txtName;



        private ExerciseViewHolder(View itemView) {
            super(itemView);
            txtName= itemView.findViewById(R.id.name);
        }


    }
}
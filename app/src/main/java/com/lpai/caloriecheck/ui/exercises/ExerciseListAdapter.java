package com.lpai.caloriecheck.ui.exercises;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

//AICI O SA SETEZI NAVIGAREA CATRE UPDATE SAU DELET PT UN ANUME FOOD DIN DYLY_FOOD
            holder.itemView.setOnClickListener(v -> System.out.println(exerciseItem.exerciseId));
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
//            itemView.setOnClickListener(this);
        }

    }
}
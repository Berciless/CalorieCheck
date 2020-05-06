package com.lpai.caloriecheck.ui.exercises;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.lpai.caloriecheck.R;
import com.lpai.caloriecheck.ui.ExerciseScreen.ExerciseScreen;

import java.util.List;


public class ExerciseListAdapter extends RecyclerView.Adapter<ExerciseListAdapter.ExerciseViewHolder>{
    private DeleteItemListener deleteListener;
//    private EditItemListener editListener;
    private final LayoutInflater inflater;
    private List<Exercise> exercises; // Cached copy of exercises
    Context context;
    ExerciseListAdapter(Context context, DeleteItemListener deleteListener) {
        inflater = LayoutInflater.from(context);
        this.context=context;
        this.deleteListener = deleteListener;
//        this.editListener = editListener;
    }

    @Override
    public ExerciseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.exercise_item, parent, false);
        return new ExerciseViewHolder(itemView,deleteListener);
    }

    @Override
    public void onBindViewHolder(ExerciseViewHolder holder, int position) {
        if (exercises != null) {
            Exercise exerciseItem = exercises.get(position);
            holder.txtName.setText(exerciseItem.name);
            holder.itemView.setOnClickListener(v ->
                    {
                        Intent intent = new Intent(context, ExerciseScreen.class);
                        Bundle extras = new Bundle();
                        extras.putLong("exerciseId",exerciseItem.exerciseId);
                        extras.putString("exerciseName",exerciseItem.name);
                        intent.putExtras(extras);
                        context.startActivity(intent);
                    });
        } else {
            holder.txtName.setText("ERROR");

        }
    }



    class ExerciseViewHolder extends RecyclerView.ViewHolder  {
        TextView txtName;
        ImageButton deleteExerciseBtn;
//        ImageButton editExerciseBtn;
        DeleteItemListener deleteItemListener;
//        EditItemListener editItemListener;

        private ExerciseViewHolder(View itemView,DeleteItemListener deleteItemListener) {
            super(itemView);
            txtName= itemView.findViewById(R.id.name);
            deleteExerciseBtn = itemView.findViewById(R.id.delete_exercise);
//            editExerciseBtn= itemView.findViewById(R.id.edit_exercise);
//            this.editItemListener = editItemListener;
//            editExerciseBtn.setOnClickListener(
//                    v->editListener.onEditPressed(exercises.get(getAdapterPosition()).exerciseId));
            this.deleteItemListener =deleteItemListener;
            deleteExerciseBtn.setOnClickListener(
                    v->deleteListener.onDeletePressed(exercises.get(getAdapterPosition()).exerciseId));

        }
    }

    public interface DeleteItemListener{
        void onDeletePressed(long id);
    }

//    public interface  EditItemListener{
//        void onEditPressed(long id);
//    }

    void setExercises(List<Exercise> exercisesToSet){
        exercises = exercisesToSet;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (exercises != null)
            return exercises.size();
        else return 0;
    }
}
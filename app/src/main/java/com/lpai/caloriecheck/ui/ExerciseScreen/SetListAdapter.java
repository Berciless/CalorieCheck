package com.lpai.caloriecheck.ui.ExerciseScreen;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lpai.caloriecheck.R;
import com.lpai.caloriecheck.ui.Database.SetsRepository;
import com.lpai.caloriecheck.ui.exercises.Exercise;

import java.util.List;

public class SetListAdapter extends RecyclerView.Adapter<SetListAdapter.SetViewHolder> {

    private DeleteItemListener listener;
    private final LayoutInflater inflater;
    private List<ExerciseSet> sets; // Cached copy of exercises
    Context context;

    SetListAdapter(Context context, DeleteItemListener listener) {
        inflater = LayoutInflater.from(context);
        this.context=context;
        this.listener=listener;
    }


    @Override
    public SetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.set_item, parent, false);
        return new SetViewHolder(itemView,listener);
    }

    @Override
    public void onBindViewHolder(SetViewHolder holder, int position) {
        if (sets != null) {
            ExerciseSet setItem = sets.get(position);
            holder.txtReps.setText(String.valueOf(setItem.reps));
            holder.txtWeight.setText(String.valueOf(setItem.weight));
            holder.txtDate.setText(setItem.date);
//            holder.deleteSetBtn.setOnClickListener(v ->
//                    listener.onDeletePressed(setItem.setId)
//            );
        } else {
            holder.txtReps.setText("ERROR");

        }
    }



    class SetViewHolder extends RecyclerView.ViewHolder  {
        TextView txtReps;
        TextView txtWeight;
        TextView txtDate;
        ImageButton deleteSetBtn;
        DeleteItemListener deleteItemListener;

        public SetViewHolder(View itemView, DeleteItemListener deleteItemListener) {
            super(itemView);
            deleteSetBtn =itemView.findViewById(R.id.delete_set);
            txtReps= itemView.findViewById(R.id.reps);
            txtWeight= itemView.findViewById(R.id.weight);
            txtDate = itemView.findViewById(R.id.set_date);
            this.deleteItemListener=deleteItemListener;
            deleteSetBtn.setOnClickListener(v->listener.onDeletePressed(sets.get(getAdapterPosition()).setId));
        }

        public void onClick(View v) {
            listener.onDeletePressed(sets.get(getAdapterPosition()).setId);
        }
    }


    public interface DeleteItemListener{
        void onDeletePressed(long id);
    }



    void setSets(List<ExerciseSet> setsToSet){
        sets = setsToSet;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mExercises has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (sets != null)
            return sets.size();
        else return 0;
    }
}

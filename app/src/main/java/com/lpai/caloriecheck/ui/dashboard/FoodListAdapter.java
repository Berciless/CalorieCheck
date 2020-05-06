package com.lpai.caloriecheck.ui.dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lpai.caloriecheck.R;

import java.util.List;

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.FoodViewHolder> {
    private final LayoutInflater inflater;
    private List<Food> foods; // Cached copy of foods
    private DeleteFoodListener deleteListener;

    FoodListAdapter(Context context,DeleteFoodListener deleteListener) {
        this.deleteListener=deleteListener;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.food_item, parent, false);
        return new FoodViewHolder(itemView,deleteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        if (foods != null) {
            Food foodItem = foods.get(position);
            holder.txtName.setText(foodItem.name);
            holder.txtCalories.setText(String.valueOf(foodItem.calories));
            holder.txtProteins.setText(String.valueOf(foodItem.proteins));
            holder.txtCarbs.setText(String.valueOf(foodItem.carbs));
            holder.txtFat.setText(String.valueOf(foodItem.fat));
            holder.itemView.setOnClickListener(v -> System.out.println(foodItem.foodId));
        } else {
            holder.txtName.setText(String.valueOf("ERROR"));
            holder.txtCalories.setText(String.valueOf("ERROR"));
            holder.txtProteins.setText(String.valueOf("ERROR"));
            holder.txtCarbs.setText(String.valueOf("ERROR"));
            holder.txtFat.setText(String.valueOf("ERROR"));
        }
    }

    public interface DeleteFoodListener{
        void onDeletePressed(long id);
    }

    void setFoods(List<Food> foodsToSet){
        foods = foodsToSet;
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        if (foods != null) {
            return foods.size();
        }
        else return 0;
    }

    class FoodViewHolder extends RecyclerView.ViewHolder  {
        TextView txtName;
        TextView txtCalories;
        TextView txtProteins;
        TextView txtCarbs;
        TextView txtFat;
        ImageButton deleteFoodBtn;
        DeleteFoodListener deleteFoodListener;


        private FoodViewHolder(View itemView,DeleteFoodListener deleteFoodListener) {
            super(itemView);
            this.deleteFoodListener=deleteFoodListener;
            deleteFoodBtn = itemView.findViewById(R.id.delete_food_btn);
            deleteFoodBtn.setOnClickListener(
                    v->deleteListener.onDeletePressed(foods.get(getAdapterPosition()).foodId)
            );
            txtName= itemView.findViewById(R.id.name);
            txtCalories= itemView.findViewById(R.id.calories);
            txtProteins= itemView.findViewById(R.id.proteins);
            txtCarbs= itemView.findViewById(R.id.carbs);
            txtFat = itemView.findViewById(R.id.fat);
        }

    }
}
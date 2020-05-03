package com.lpai.caloriecheck.ui.dashboard;

        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.AdapterView;
        import android.widget.TextView;
        import android.widget.Toast;

        import androidx.recyclerview.widget.RecyclerView;

        import com.lpai.caloriecheck.R;

        import java.util.List;

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.FoodViewHolder> {
    private final LayoutInflater inflater;
    private List<Food> foods; // Cached copy of foods
    Context context;
    FoodListAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.context=context;
    }
    public View.OnClickListener clickListener;

    @Override
    public FoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.food_item, parent, false);
        return new FoodViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FoodViewHolder holder, int position) {
        if (foods != null) {
            Food foodItem = foods.get(position);
            holder.txtName.setText(foodItem.name);
            holder.txtCalories.setText(String.valueOf(foodItem.calories));
            holder.txtProteins.setText(String.valueOf(foodItem.proteins));
            holder.txtCarbs.setText(String.valueOf(foodItem.carbs));
            holder.txtFat.setText(String.valueOf(foodItem.fat));
//AICI O SA SETEZI NAVIGAREA CATRE UPDATE SAU DELET PT UN ANUME FOOD DIN DYLY_FOOD
            holder.itemView.setOnClickListener(v -> System.out.println(foodItem.foodId));
        } else {
            holder.txtName.setText("ERROR");
            holder.txtCalories.setText(String.valueOf("ERROR"));
            holder.txtProteins.setText(String.valueOf("ERROR"));
            holder.txtCarbs.setText(String.valueOf("ERROR"));
            holder.txtFat.setText(String.valueOf("ERROR"));
        }
    }

    void setFoods(List<Food> foodsToSet){
        foods = foodsToSet;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mFoods has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (foods != null)
            return foods.size();
        else return 0;
    }

    class FoodViewHolder extends RecyclerView.ViewHolder  {
        TextView txtName;
        TextView txtCalories;
        TextView txtProteins;
        TextView txtCarbs;
        TextView txtFat;


        private FoodViewHolder(View itemView) {
            super(itemView);
            txtName= itemView.findViewById(R.id.name);
            txtCalories= itemView.findViewById(R.id.calories);
            txtProteins= itemView.findViewById(R.id.proteins);
            txtCarbs= itemView.findViewById(R.id.carbs);
            txtFat = itemView.findViewById(R.id.fat);
        }

    }
}
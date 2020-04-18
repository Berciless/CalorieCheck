package com.lpai.caloriecheck.ui.dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.lpai.caloriecheck.R;
import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<FoodItemModel> implements View.OnClickListener{

    private ArrayList<FoodItemModel> foodList;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
        TextView txtCalories;
        TextView txtProteins;
        TextView txtCarbs;
        TextView txtFat;


    }

    public CustomAdapter(ArrayList<FoodItemModel> data, Context context) {
        super(context, R.layout.food_item, data);
        this.foodList = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        FoodItemModel foodItem= getItem(position);
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        FoodItemModel foodItem = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.food_item, parent, false);
            viewHolder.txtName = convertView.findViewById(R.id.name);
            viewHolder.txtCalories = convertView.findViewById(R.id.calories);
            viewHolder.txtProteins = convertView.findViewById(R.id.proteins);
            viewHolder.txtCarbs = convertView.findViewById(R.id.carbs);
            viewHolder.txtFat = convertView.findViewById(R.id.fat);

//            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
//            result=convertView;
        }

//        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
//        result.startAnimation(animation);
//        lastPosition = position;

        viewHolder.txtName.setText(foodItem.name);
        viewHolder.txtCalories.setText(String.valueOf(foodItem.calories));
        viewHolder.txtProteins.setText(String.valueOf(foodItem.proteins));
        viewHolder.txtCarbs.setText(String.valueOf(foodItem.carbs));
        viewHolder.txtFat.setText(String.valueOf(foodItem.fat));
        // Return the completed view to render on screen
        return convertView;
    }
}
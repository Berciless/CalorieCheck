package com.lpai.caloriecheck.ui.dashboard;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.ListFragment;
import androidx.lifecycle.ViewModelProvider;
import com.lpai.caloriecheck.R;

import java.util.ArrayList;


public class DashboardFragment extends ListFragment implements AdapterView.OnItemClickListener{

    ArrayList<FoodItemModel> data;
    ListView listView;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        final TextView textView = root.findViewById(R.id.text_exercises);

        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        data = new ArrayList<>(); data.add(new FoodItemModel("Apple Pie", "Android 1.0", "1", "September 23, 2008"));
        data.add(new FoodItemModel("Banana Bread", "Android 1.1", "2", "February 9, 2009"));
        data.add(new FoodItemModel("Cupcake", "Android 1.5", "3", "April 27, 2009"));
        data.add(new FoodItemModel("Donut", "Android 1.6", "4", "September 15, 2009"));
        data.add(new FoodItemModel("Eclair", "Android 2.0", "5", "October 26, 2009"));
        data.add(new FoodItemModel("Froyo", "Android 2.2", "8", "May 20, 2010"));
        data.add(new FoodItemModel("Gingerbread", "Android 2.3", "9", "December 6, 2010"));
        data.add(new FoodItemModel("Honeycomb", "Android 3.0", "11", "February 22, 2011"));
        data.add(new FoodItemModel("Ice Cream Sandwich", "Android 4.0", "14", "October 18, 2011"));
        data.add(new FoodItemModel("Jelly Bean", "Android 4.2", "16", "July 9, 2012"));
        data.add(new FoodItemModel("Kitkat", "Android 4.4", "19", "October 31, 2013"));
        data.add(new FoodItemModel("Lollipop", "Android 5.0", "21", "November 12, 2014"));
        data.add(new FoodItemModel("Marshmallow", "Android 6.0", "23", "October 5, 2015"));

        CustomAdapter adapter = new CustomAdapter(data, getActivity());
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);

    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String mess=data.get(position).name;
        Toast.makeText(getActivity(), "Item: " + mess, Toast.LENGTH_SHORT).show();
    }

}

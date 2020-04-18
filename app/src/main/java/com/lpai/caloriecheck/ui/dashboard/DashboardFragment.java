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

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        final TextView textView = root.findViewById(R.id.text_exercises);
        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        data = new ArrayList<>();
        data.add(new FoodItemModel(new MacroRatio("Apple Pie",20,30,40),100));
        data.add(new FoodItemModel(new MacroRatio("Apple Pie",20,30,40,560),100));
        data.add(new FoodItemModel(new MacroRatio(20,30,40,560),100));
        data.add(new FoodItemModel(new MacroRatio(20,30,40),100));
        data.add(new FoodItemModel(new MacroRatio("Apple Pie",20,30,40,560),100));
        data.add(new FoodItemModel(new MacroRatio("Apple Pie",20,30,40,560),100));


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

package com.example.hungdo.myrecipe.Dish.Adapters;

/**
 * Created by HungDo on 12/3/2015.
 */

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hungdo.myrecipe.Dish.APIs.RecipeObj;
import com.example.hungdo.myrecipe.R;

import java.util.List;

//Custom List Adapter for the Nutrition List View

public class CustomNutritionAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<RecipeObj> recipeItems;

    public CustomNutritionAdapter(Activity activity, List<RecipeObj> recipeItems) {
        this.activity = activity;
        this.recipeItems = recipeItems;
    }

    @Override
    public int getCount() {
        return recipeItems.size();
    }

    @Override
    public Object getItem(int location) {
        return recipeItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row_nutrition, null);


        TextView item = (TextView) convertView.findViewById(R.id.item);
        TextView brand = (TextView) convertView.findViewById(R.id.brand);
        TextView calories = (TextView) convertView.findViewById(R.id.calories);
        TextView fat = (TextView) convertView.findViewById(R.id.fat);


        // getting nutrition data for the row
        RecipeObj r = recipeItems.get(position);

        // Item name
        item.setText(r.getItemName());

        // Brand Name
        brand.setText("Brand: " + r.getBrandName());

        // Calories
        calories.setText("Calories: " + r.getCalories());

        // Total Fat
        fat.setText("Total Fat: " + r.getTotalFat());

        return convertView;
    }

}

package com.example.hungdo.myrecipe.Dish.Adapters;


import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.hungdo.myrecipe.Dish.Volley.AppController;
import com.example.hungdo.myrecipe.Dish.APIs.RecipeObj;
import com.example.hungdo.myrecipe.R;

//Custom List Adapter for the Recipe List View

public class CustomRecipeAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<RecipeObj> recipeObjItems;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CustomRecipeAdapter(Activity activity, List<RecipeObj> recipeObjItems) {
        this.activity = activity;
        this.recipeObjItems = recipeObjItems;
    }

    @Override
    public int getCount() {
        return recipeObjItems.size();
    }

    @Override
    public Object getItem(int location) {
        return recipeObjItems.get(location);
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
            convertView = inflater.inflate(R.layout.list_row_recipe, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.thumbnail);


        //Title
        TextView title = (TextView) convertView.findViewById(R.id.title);
        title.setTypeface(null, Typeface.BOLD);

        //Publisher
        TextView publisher = (TextView) convertView.findViewById(R.id.publisher);

        //Get position of the recipe
        RecipeObj recipe = recipeObjItems.get(position);

        //Show thumbnail
        thumbNail.setImageUrl(recipe.getThumbnailUrl(), imageLoader);

        //Set title
        title.setText(recipe.getTitle());
        //Set publisher
        publisher.setText("By: " + recipe.getPublisher());

        return convertView;
    }

}
package com.example.hungdo.myrecipe.Dish.Adapters;

/**
 * Created by HungDo on 12/5/2015.
 */

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
import com.example.hungdo.myrecipe.Dish.SQLite.RecipeData;
import com.example.hungdo.myrecipe.R;


//Custom List Adapter for the Favorites List View

public class CustomFavAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<RecipeData> receipDataItems;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CustomFavAdapter(Activity activity, List<RecipeData> receipDataItems) {
        this.activity = activity;
        this.receipDataItems = receipDataItems;
    }

    @Override
    public int getCount() {
        return receipDataItems.size();
    }

    @Override
    public Object getItem(int location) {
        return receipDataItems.get(location);
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
            convertView = inflater.inflate(R.layout.list_row_fav, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.thumbnail);

        //Title
        TextView title = (TextView) convertView.findViewById(R.id.favtitle);
         title.setTypeface(null, Typeface.BOLD);
        //Publisher
        TextView publisher = (TextView) convertView.findViewById(R.id.favpublisher);

        //Get the recipe position
        RecipeData recipe = receipDataItems.get(position);

        //Set title
        title.setText(recipe.getTitle());

        //Load thumbnails
        thumbNail.setImageUrl(recipe.getThumbnail(), imageLoader);

        //Set publisher
        publisher.setText("By:" + recipe.getPublisher());

        return convertView;
    }

}
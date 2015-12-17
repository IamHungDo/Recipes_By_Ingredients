package com.example.hungdo.myrecipe.Dish.Content;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.hungdo.myrecipe.Dish.Adapters.CustomFavAdapter;
import com.example.hungdo.myrecipe.Dish.SQLite.DatabaseHandler;
import com.example.hungdo.myrecipe.Dish.SQLite.RecipeData;
import com.example.hungdo.myrecipe.Dish.MainActivity;
import com.example.hungdo.myrecipe.R;

import java.util.ArrayList;
import java.util.List;


public class Favorites extends AppCompatActivity {
    DatabaseHandler db = new DatabaseHandler(this);

    private ListView listView;
    private CustomFavAdapter adapter;
    List<RecipeData> recipeDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        //Creates List View + Adapter
        listView = (ListView) findViewById(R.id.list1);
        adapter = new CustomFavAdapter(this, recipeDataList);
        listView.setAdapter(adapter);


        //Creates list and set it equal to the database list.
        List<RecipeData> favoriteRecipes = db.getAllRecipes();

        //Set the adapter items by retrieving data from the database
        for (RecipeData cn : favoriteRecipes) {

            cn.setTitle(cn.getTitle());
            cn.setPublisher(cn.getPublisher());
            cn.setThumbnail(cn.getThumbnail());
            recipeDataList.add(cn);
        }


        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //Action Bar
        switch (item.getItemId()) {
            case R.id.action_about:
                Intent aIntent = new Intent(this, About.class);
                startActivity(aIntent);
                return true;
            case R.id.action_home:
                Intent hIntent = new Intent(this, MainActivity.class);
                startActivity(hIntent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

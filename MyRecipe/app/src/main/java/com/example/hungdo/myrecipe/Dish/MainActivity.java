package com.example.hungdo.myrecipe.Dish;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.example.hungdo.myrecipe.Dish.Content.About;
import com.example.hungdo.myrecipe.Dish.Content.Favorites;
import com.example.hungdo.myrecipe.Dish.Content.Preferences;
import com.example.hungdo.myrecipe.Dish.Content.IngredientBank;
import com.example.hungdo.myrecipe.Dish.APIs.Nutrition;
import com.example.hungdo.myrecipe.Dish.Content.GoogleMaps.MapsActivity;
import com.example.hungdo.myrecipe.Dish.InitialLaunch.LicenseAgreement;
import com.example.hungdo.myrecipe.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new LicenseAgreement(this).show();


        final ImageButton mapButton = (ImageButton) findViewById(R.id.FindStore);
        final ImageButton getRecipe = (ImageButton) findViewById(R.id.getRecipe);
        final ImageButton preferences = (ImageButton) findViewById(R.id.Preferences);
        final ImageButton nutrition = (ImageButton) findViewById(R.id.Nutrition);
        final ImageButton favourites = (ImageButton) findViewById(R.id.Favorites);


        //Go to Google Maps
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);

                startActivity(intent);
            }
        });
        //Go to find recipe
        getRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, IngredientBank.class);

                startActivity(intent);
            }
        });

        //Go to preferences
        preferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Preferences.class);

                startActivity(intent);
            }
        });

        //Go the nutrition search
        nutrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Nutrition.class);

                startActivity(intent);
            }
        });

        //Go to favorites
        favourites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Favorites.class);

                startActivity(intent);
            }
        });

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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            Intent aIntent = new Intent(this, About.class);
            startActivity(aIntent);
            return true;
        }

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

package com.example.hungdo.myrecipe.Dish.APIs;

/**
 * Created by HungDo on 12/2/2015.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.hungdo.myrecipe.Dish.Content.About;
import com.example.hungdo.myrecipe.Dish.MainActivity;
import com.example.hungdo.myrecipe.R;

//Nutrition Recipe Search

public class Nutrition extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "nimitharamesh.recipeappcustomlistviewvolley";
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_search);
        btn = (Button) findViewById(R.id.button);
    }

    //When the button is clicked, send the edit text intent to the NutritionRequest page
    public void sendMessage(View view) {
        Intent intent = new Intent(this, NutritionRequest.class);
        EditText et = (EditText) findViewById(R.id.editText);
        String message = et.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //Action Bar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


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
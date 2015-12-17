package com.example.hungdo.myrecipe.Dish.APIs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.hungdo.myrecipe.Dish.Adapters.CustomRecipeAdapter;
import com.example.hungdo.myrecipe.Dish.Volley.AppController;
import com.example.hungdo.myrecipe.Dish.Content.IngredientBank;
import com.example.hungdo.myrecipe.Dish.Content.About;
import com.example.hungdo.myrecipe.Dish.MainActivity;
import com.example.hungdo.myrecipe.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HungDo on 11/3/15.
 */
public class RecipeRequest extends AppCompatActivity {

    private static final String TAG = RecipeRequest.class.getSimpleName();
    private List<RecipeObj> recipeObjList = new ArrayList<>();
    private ListView listView;
    private CustomRecipeAdapter adapter;
    public final static String EXTRA_MESSAGE = "com.example.hungdo.recipeappvolley.BackEnd";

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        listView = (ListView) findViewById(R.id.list);
        adapter = new CustomRecipeAdapter(this, recipeObjList);

        //When this item in the List View is clicked, send an object intent to the next page
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(final AdapterView<?> adapter, View v, final int position, long itemId) {
                        RecipeObj recipe = (RecipeObj) adapter.getItemAtPosition(position);
                        Intent intent = new Intent(RecipeRequest.this, ExpandedView.class);
                        intent.putExtra("RecipeRequest", recipe);
                        startActivity(intent);
                    }
                }
        );


        listView.setAdapter(adapter);

        // Create JSON request to the Food2Fork API to get recipes
        final JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, URL(0), null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray arrProducts = response.getJSONArray("recipes");
                            for (int i = 0; i < arrProducts.length(); i++) {
                                JSONObject productItem = (JSONObject) arrProducts.get(i);
                                RecipeObj recipeObj = new RecipeObj();
                                recipeObj.setTitle(productItem.getString("title"));
                                recipeObj.setPublisher(productItem.getString("publisher"));
                                recipeObj.setThumbnailUrl(productItem.getString("image_url"));
                                recipeObj.setSourceUrl(productItem.getString("f2f_url"));
                                recipeObj.setRecipe_id(productItem.getString("recipe_id"));
                                recipeObjList.add(recipeObj);

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        adapter.notifyDataSetChanged();
                    }

                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error != null) Log.e("RecipeRequest", error.getMessage());

                    }
                });
        //Add to the singleton requestQ
        AppController.getInstance().addToRequestQueue(jsObjRequest);


        // Create JSON request to the Edamam API to get recipes
        final JsonObjectRequest recipeReq = new JsonObjectRequest(URL(1), null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        //Log.v("Response: ", response.toString());

                        // Parsing json
                        try {
                            JSONArray arrProducts = response.getJSONArray("hits");
                            for (int i = 0; i < arrProducts.length(); i++) {

                                JSONObject productItem = (JSONObject) arrProducts.get(i);
                                JSONObject responseRecipe = productItem.getJSONObject("recipe");
                                //JSONObject obj = (JSONObject) arrProducts.get(i);
                                RecipeObj recipe = new RecipeObj();
                                recipe.setTitle(responseRecipe.getString("label"));
                                recipe.setThumbnailUrl(responseRecipe.getString("image"));
                                recipe.setSourceUrl(responseRecipe.getString("url"));
                                recipe.setPublisher(responseRecipe.getString("source"));
                                // adding recipe to recipes array
                                recipeObjList.add(recipe);


                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());

            }
        });
        //Add to the singleton requestQ
        AppController.getInstance().addToRequestQueue(recipeReq);

    }

    //Returns a URL based on what API. Return F2f link if it's 0. Return Edamam if it's 1
    public String URL(int i) {
        Intent intent1 = getIntent();
        String message = intent1.getStringExtra(IngredientBank.EXTRA_MESSAGE);
        String message1 = intent1.getStringExtra(IngredientBank.EXTRA_MESSAGE1);
        if (i == 0) {
            return "http://food2fork.com/api/search?key=cd8b28381215c2ef73c05160cc4efa56&q=" + message1 + "%20";
        } else {
            return "https://api.edamam.com/search?q=" + message + "&app_id=d0d4ba70&app_key=3fff29c8a8f98bf97fac9af3330e00dd";
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
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



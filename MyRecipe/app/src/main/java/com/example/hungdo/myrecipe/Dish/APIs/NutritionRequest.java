package com.example.hungdo.myrecipe.Dish.APIs;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.hungdo.myrecipe.Dish.Adapters.CustomNutritionAdapter;
import com.example.hungdo.myrecipe.Dish.Volley.AppController;
import com.example.hungdo.myrecipe.Dish.Content.About;
import com.example.hungdo.myrecipe.Dish.MainActivity;
import com.example.hungdo.myrecipe.R;

import java.util.ArrayList;
import java.util.List;

public class NutritionRequest extends AppCompatActivity {
    // Log tag
    private static final String TAG = NutritionRequest.class.getSimpleName();

    // Recipes json url
    private ProgressDialog pDialog;
    private final List<RecipeObj> NutritionRequest = new ArrayList<RecipeObj>();
    private ListView listView;
    private CustomNutritionAdapter adapter;


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition);

        //Create Listview + Adapter
        listView = (ListView) findViewById(R.id.list);
        adapter = new CustomNutritionAdapter(this, NutritionRequest);
        listView.setAdapter(adapter);


        //JSON request to the Nutritionix API
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Method.GET, URL(), null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray arrProducts = response.getJSONArray("hits");
                            for (int i = 0; i < arrProducts.length(); i++) {
                                JSONObject productItem = (JSONObject) arrProducts.get(i);
                                JSONObject nutrition = productItem.getJSONObject("fields");
                                RecipeObj recipeObj = new RecipeObj();
                                recipeObj.setItemName(nutrition.getString("item_name"));
                                recipeObj.setBrandName(nutrition.getString("brand_name"));
                                recipeObj.setCalories(nutrition.getString("nf_calories"));
                                recipeObj.setTotalFat(nutrition.getString("nf_total_fat"));

                                NutritionRequest.add(recipeObj);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        adapter.notifyDataSetChanged();
                    }


                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(TAG, "Error: " + error.getMessage());
                        hidePDialog();

                    }
                });
                //Add to the singleton requestQ
        AppController.getInstance().addToRequestQueue(jsObjRequest);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    //Tie all the necessary  strings together and return the full url
    public String URL() {
        Intent intent = getIntent();
        String message = intent.getStringExtra(Nutrition.EXTRA_MESSAGE);
        return "https://api.nutritionix.com/v1_1/search/" + message + "?fields=item_name%2Citem_id%2Cbrand_name%2Cnf_calories%2Cnf_total_fat&appId=cb52127f&appKey=f8fdea6d4dfa6bed20322b038b965eda";
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


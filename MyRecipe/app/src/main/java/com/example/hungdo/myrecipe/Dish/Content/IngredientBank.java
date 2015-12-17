package com.example.hungdo.myrecipe.Dish.Content;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.example.hungdo.myrecipe.Dish.APIs.RecipeRequest;
import com.example.hungdo.myrecipe.Dish.MainActivity;
import com.example.hungdo.myrecipe.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class IngredientBank extends ListActivity {
    Button btn;
    private static final String SHARED_PREFS_NAME = "MY_SHARED_PREF";
    public final static String EXTRA_MESSAGE = "com.example.hungdo.myrecipe";
    public final static String EXTRA_MESSAGE1= "com.example.hungdo.myrecipe";
    // Items entered by the user is stored in this ArrayList variable
    ArrayList list = new ArrayList();

    // Declaring an ArrayAdapter to set items to ListView
    ArrayAdapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btn = (Button) findViewById(R.id.button);
        setContentView(R.layout.activity_ingredient_bank);

        Button btn = (Button) findViewById(R.id.btnAdd);

        Button btnDel = (Button) findViewById(R.id.btnDel);

        list = getArray();

        // Defining the ArrayAdapter to set items to ListView
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);

        // Defining a click event listener for the button "Add"
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                EditText edit = (EditText) findViewById(R.id.txtItem);
                list.add(edit.getText().toString());
                edit.setText("");
                adapter.notifyDataSetChanged();
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });

        // Defining a click event listener for the button "Delete"
        btnDel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Deletes last item in array
                int i = list.size();
                adapter.remove(list.get(i-1));
                adapter.notifyDataSetChanged();
            }
        });


        // Setting the adapter to the ListView
        setListAdapter(adapter);
    }
    //TODO the search is buggy. We need to fix this later
    public void sendMessage(View view) {

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences((getBaseContext()));
        boolean veg = pref.getBoolean("veg", false);
        boolean glutenFree = pref.getBoolean("gluten_free", false);

        if (veg == true) {
            if(list.size()==1) {
                Intent intent = new Intent(this, RecipeRequest.class);
                String message = list.get(0).toString()+",vegetarian";
                String message1 = "vegetarian%20"+list.get(0).toString();
                intent.putExtra(EXTRA_MESSAGE, message);
                intent.putExtra(EXTRA_MESSAGE1, message1);
                startActivity(intent);
            } else if(list.size()==2) {
                Intent intent = new Intent(this, RecipeRequest.class);
                String message = list.get(0).toString() +","+ list.get(1).toString()+",vegetarian";
                String message1 = "vegetarian%20"+list.get(0).toString() +"%20"+ list.get(1).toString();
                intent.putExtra(EXTRA_MESSAGE, message);
                intent.putExtra(EXTRA_MESSAGE1, message1);
                startActivity(intent);
            } else if(list.size()==3) {
                Intent intent = new Intent(this, RecipeRequest.class);
                String message = list.get(0).toString() +","+ list.get(1).toString()+","+ list.get(2).toString()+",vegetarian";
                String message1 = "vegetarian%20"+list.get(0).toString() +"%20"+ list.get(1).toString()+"%20"+ list.get(2).toString();
                intent.putExtra(EXTRA_MESSAGE, message);
                intent.putExtra(EXTRA_MESSAGE1, message1);
                startActivity(intent);
            }
        } else if (glutenFree == true) {
            if(list.size()==1) {
                Intent intent = new Intent(this, RecipeRequest.class);
                String message = list.get(0).toString()+",gluten free";
                String message1 = "gluten free%20"+list.get(0).toString();
                intent.putExtra(EXTRA_MESSAGE, message);
                intent.putExtra(EXTRA_MESSAGE1, message1);
                startActivity(intent);
            } else if(list.size()==2) {
                Intent intent = new Intent(this, RecipeRequest.class);
                String message = list.get(0).toString() +","+ list.get(1).toString()+",gluten free";
                String message1 = "gluten free%20"+list.get(0).toString() +"%20"+ list.get(1).toString();
                intent.putExtra(EXTRA_MESSAGE, message);
                intent.putExtra(EXTRA_MESSAGE1, message1);
                startActivity(intent);
            } else if(list.size()==3) {
                Intent intent = new Intent(this, RecipeRequest.class);
                String message = list.get(0).toString() + "," + list.get(1).toString() + "," + list.get(2).toString() + ",gluten free";
                String message1 = "gluten free%20"+list.get(0).toString() +"%20"+ list.get(1).toString()+"%20"+ list.get(2).toString();
                intent.putExtra(EXTRA_MESSAGE, message);
                intent.putExtra(EXTRA_MESSAGE1, message1);
                startActivity(intent);
//            adapter.notifyDataSetChanged();
            }
        } else {
            if(list.size()==1) {
                Intent intent = new Intent(this, RecipeRequest.class);
                String message = list.get(0).toString();
                intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);
            } else if(list.size()==2) {
                Intent intent = new Intent(this, RecipeRequest.class);
                String message = list.get(0).toString() +","+ list.get(1).toString();
                intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);
            } else if(list.size()==3) {
                Intent intent = new Intent(this, RecipeRequest.class);
                String message = list.get(0).toString() + "," + list.get(1).toString() + "," + list.get(2).toString();
                intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);
            }
        }
    }

//    public void onResume(){
//        super.onResume();
//
//        if (list.contains("vegetarian")){
//            int i = list.size();
//            adapter.remove(list.get(i-1));
//            adapter.notifyDataSetChanged();
//        }
//
//        if (list.contains("gluten-free")){
//            int i = list.size();
//            adapter.remove(list.get(i-1));
//            adapter.notifyDataSetChanged();
//        }
//
//    }


    public boolean saveArray() {
        SharedPreferences sp = this.getSharedPreferences(SHARED_PREFS_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor mEdit1 = sp.edit();
        Set<String> set = new HashSet<String>();
        set.addAll(list);
        mEdit1.putStringSet("list", set);
        return mEdit1.commit();
    }

    public ArrayList getArray() {
        SharedPreferences sp = this.getSharedPreferences(SHARED_PREFS_NAME, Activity.MODE_PRIVATE);

        //NOTE: if shared preference is null, the method return empty Hashset and not null
        Set<String> set = sp.getStringSet("list", new HashSet<String>());

        return new ArrayList<String>(set);
    }

    public void onStop() {
        saveArray();
        super.onStop();
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



        switch(item.getItemId()){
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
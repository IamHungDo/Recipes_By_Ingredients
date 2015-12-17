package com.example.hungdo.myrecipe.Dish.APIs;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.hungdo.myrecipe.Dish.SQLite.RecipeData;
import com.example.hungdo.myrecipe.Dish.Volley.AppController;
import com.example.hungdo.myrecipe.Dish.Content.About;
import com.example.hungdo.myrecipe.Dish.MainActivity;
import com.example.hungdo.myrecipe.R;
import com.example.hungdo.myrecipe.Dish.SQLite.DatabaseHandler;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;

import java.io.InputStream;
import java.util.List;

import io.fabric.sdk.android.Fabric;

/**
 * Created by HungDo on 11/25/15.
 */


public class ExpandedView extends AppCompatActivity {
    DatabaseHandler db = new DatabaseHandler(this);
    public final static String EXTRA_MESSAGE2 = "com.example.hungdo.myrecipe";
    public final static String EXTRA_MESSAGE3 = "com.example.hungdo.myrecipe";
    private static final String TWITTER_KEY = "O6yacmX2GxvBu1BKWgwjvuBqU";
    private static final String TWITTER_SECRET = "KRLVrLwu87GxFwDeheA6EVCwmcgbhQomMF88qSUJxxOyV2Cg4s";
    public static String PACKAGE_NAME;
    private String link = "www.placeholder.com";
    private ImageButton shareButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_expand);

        TextView title = (TextView) findViewById(R.id.title);
        TextView publisher = (TextView) findViewById(R.id.publisher);
        ImageButton webview = (ImageButton) findViewById(R.id.webview1);
        ImageButton img = (ImageButton) findViewById(R.id.recipe_id);


        //Grab object intent from recipe request
        Intent intent = getIntent();
        final RecipeObj recipe = (RecipeObj) intent.getSerializableExtra("RecipeRequest");
        //Set the text views equal to the recipe obj getters
        title.setText(recipe.getTitle());
        publisher.setText(recipe.getPublisher());
        link = recipe.getSourceUrl();
        //When this webview button is clicked, go to link with webview
        webview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ExpandedView.this, WebViewActivity.class);
                String message = recipe.getSourceUrl();
                intent1.putExtra(EXTRA_MESSAGE2, message);
                startActivity(intent1);
            }
        });

        //Show thumbnail
        new DownloadImageTask((ImageView) findViewById(R.id.imageView))
                .execute(recipe.getThumbnailUrl());




        //When the favortie button is clicked, prompts the user to add to favorites. Yes or No
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(ExpandedView.this);

                // Setting Dialog Title
                alertDialog.setTitle("             Add to Favorites?");
                // Setting Dialog Message
                alertDialog.setMessage("This will be added to your favorites list");
                // Setting Icon to Dialog
                alertDialog.setIcon(R.drawable.heart);

                // When YES is clicked, add the recipe to the SqLite DATABASE
                alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // User pressed YES button. Write Logic Here
                        Toast.makeText(getApplicationContext(), "Added to Favorites",
                                Toast.LENGTH_SHORT).show();

                        Log.d("Insert: ", "Inserting ..");
                        db.addRecipe(new RecipeData(recipe.getTitle(), recipe.getPublisher(), recipe.getThumbnailUrl()));
                        Log.d("Reading: ", "Reading all favoriteRecipes..");
                        List<RecipeData> favoriteRecipes = db.getAllRecipes();
                        for (RecipeData cn : favoriteRecipes) {
                            String log = "Id: " + cn.getID() + ",Name: " + cn.getTitle() + " ,Pub: " + cn.getPublisher() + ",Thumbnail: " + cn.getThumbnail();
                            // Writing Recipes to log
                            Log.d("Name: ", log);
                        }
                        // db.removeAll();
                    }
                });

                // When NO is clicked, return back to the screen
                alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // User pressed No button. Write Logic Here
                    }
                });


                // Showing Alert Message
                alertDialog.show();

            }
        });
        //This button corresponds to tweeting a recipe on Twitter
        shareButton = (ImageButton) findViewById(R.id.share_button);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(ExpandedView.this, About.class);
                String message = recipe.getSourceUrl();
                intent2.putExtra(EXTRA_MESSAGE3, message);
                startActivity(intent2);
                tweetComposer();
            }
        });

    }



    //This line of code tweets the content of which you choose.
    private void tweetComposer() {
        PACKAGE_NAME = getApplicationContext().getPackageName();

        TweetComposer.Builder builder = new TweetComposer.Builder(this)
                .text("Check out this recipe: " + link);
        builder.show();
    }

    //This block of code uses BitmapFactory to display the pictures using the HTTP links.
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //Action bar
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

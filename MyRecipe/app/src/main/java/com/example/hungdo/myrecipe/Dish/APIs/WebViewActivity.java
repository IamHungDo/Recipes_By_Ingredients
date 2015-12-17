package com.example.hungdo.myrecipe.Dish.APIs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import com.example.hungdo.myrecipe.Dish.Content.About;
import com.example.hungdo.myrecipe.Dish.Content.IngredientBank;
import com.example.hungdo.myrecipe.Dish.MainActivity;
import com.example.hungdo.myrecipe.R;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends AppCompatActivity {

    private WebView webView;

    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.webview);
        webView = (WebView) findViewById(R.id.webView1);
        webView.getSettings().setJavaScriptEnabled(true);


        //Grab intent from the ExpandedView class and load webview using that link
        Intent intent1 = getIntent();
        String message = intent1.getStringExtra(ExpandedView.EXTRA_MESSAGE2);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(message);

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
        //Action bar
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



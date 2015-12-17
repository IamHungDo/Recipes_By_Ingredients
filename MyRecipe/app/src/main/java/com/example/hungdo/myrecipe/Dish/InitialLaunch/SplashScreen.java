package com.example.hungdo.myrecipe.Dish.InitialLaunch;

/**
 * Created by sahithi on 10/12/2015.
 * A splash screen is displayed on the application, while the application is loading. Added a small animation
 * to the activity. The logo of the application pops up in the center from the bottom.
 */


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.hungdo.myrecipe.Dish.MainActivity;
import com.example.hungdo.myrecipe.R;


public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        StartAnimations();

            /*
             * Showing splash screen with a timer. will showcase the logo and animation
             * until the app is loading
             */
        Thread timerThread = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //This method will be executed once the timer is over
                    // Start your app main activity
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        };
        timerThread.start();
    }

    // animation class to start the animation
    private void StartAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.aplha);
        anim.reset();
        LinearLayout l = (LinearLayout) findViewById(R.id.lin_lay);
        l.clearAnimation();
        l.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        ImageView iv = (ImageView) findViewById(R.id.logo);
        iv.clearAnimation();
        iv.startAnimation(anim);

    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }

}
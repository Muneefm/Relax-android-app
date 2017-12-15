package com.mnf.relax;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mnf.relax.Misc.Config;
import com.mnf.relax.Misc.PreferensHandler;

public class LaunchActivity extends AppCompatActivity {

    RelativeLayout relativeLayout;
    TextView tvWelcome,subTitle,secondSubTitle;
    PreferensHandler pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        pref = new PreferensHandler(getApplicationContext());
        relativeLayout = findViewById(R.id.container_relative);
        tvWelcome = findViewById(R.id.tv_welcome);
        subTitle = findViewById(R.id.tv_subtitle);
        secondSubTitle = findViewById(R.id.tv_sub_subtitle);
        Typeface font =Typeface.createFromAsset(getAssets(), "fonts/Lobster-Regular.ttf");
        Typeface fontLob =Typeface.createFromAsset(getAssets(), "fonts/Lobster-Regular.ttf");
        tvWelcome.setTypeface(font);
        subTitle.setTypeface(fontLob);
        secondSubTitle.setTypeface(fontLob);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);




        AnimationDrawable animationDrawable = (AnimationDrawable) relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();



         AlphaAnimation fadeIn = new AlphaAnimation(0.0f , 1.0f ) ;
        tvWelcome.startAnimation(fadeIn);
        fadeIn.setDuration(5200);
        fadeIn.setFillAfter(true);

        AlphaAnimation fadeInNew = new AlphaAnimation(0.0f , 0.8f ) ;
        subTitle.startAnimation(fadeInNew);
        secondSubTitle.startAnimation(fadeInNew);
        fadeInNew.setDuration(7200);
        fadeInNew.setFillAfter(true);
        fadeInNew.setStartOffset(2200);


        /* AlphaAnimation fadeOut = new AlphaAnimation( 1.0f , 0.0f ) ;
        tvWelcome.startAnimation(fadeOut);
        subTitle.startAnimation(fadeOut);
        secondSubTitle.startAnimation(fadeOut);
        fadeOut.setDuration(1200);
        fadeOut.setFillAfter(true);
        fadeOut.setStartOffset(4200+fadeIn.getStartOffset());
*/


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(LaunchActivity.this, MainActivity.class);
                startActivity(i);
                // close this activity
                finish();
            }
        }, 15200);
        /*Animation a = AnimationUtils.loadAnimation(this, R.anim.tv_anim);
        a.reset();
        tvWelcome.clearAnimation();
        tvWelcome.startAnimation(a);
*/

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.removeCallbacksAndMessages(null);
                Intent i = new Intent(LaunchActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

}

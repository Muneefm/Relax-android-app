package com.mnf.relax;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;

public class CreditScreen extends AppCompatActivity {

    TextView tvOne,tvTwo,tvThree,tvBottom,url, tvICone,tvICtwo,tvICthree;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_screen);
  /*      Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        tvOne = findViewById(R.id.tv_one);
        tvTwo = findViewById(R.id.tv_two);
        tvBottom = findViewById(R.id.tv_bottom);
        url = findViewById(R.id.tv_three);

        tvICone = findViewById(R.id.tv_ic_one);
        tvICtwo = findViewById(R.id.tv_ic_two);
        tvICthree = findViewById(R.id.tv_ic_three);


        Typeface font =Typeface.createFromAsset(getAssets(), "fonts/BerkshireSwash-Regular.ttf");
        Typeface fontLob =Typeface.createFromAsset(getAssets(), "fonts/Lobster-Regular.ttf");
        Typeface fontPaci =Typeface.createFromAsset(getAssets(), "fonts/Pacifico-Regular.ttf");

        tvOne.setTypeface(font);
        tvTwo.setTypeface(fontLob);
        tvBottom.setTypeface(fontPaci);

        tvICone.setTypeface(font);
        tvICtwo.setTypeface(fontLob);



        AlphaAnimation fadeIn = new AlphaAnimation(0.0f , 1.0f ) ;
        tvOne.startAnimation(fadeIn);
        fadeIn.setDuration(1000);
        fadeIn.setFillAfter(true);

        AlphaAnimation fadeInTwo = new AlphaAnimation(0.0f , 1.0f ) ;
        tvTwo.startAnimation(fadeInTwo);
        fadeInTwo.setDuration(1000);
        fadeInTwo.setFillAfter(true);
        fadeInTwo.setStartOffset(500);

        AlphaAnimation fadeInurl = new AlphaAnimation(0.0f , 1.0f ) ;
        url.startAnimation(fadeInurl);
        fadeInurl.setDuration(1000);
        fadeInurl.setFillAfter(true);
        fadeInurl.setStartOffset(1000);


        AlphaAnimation fadeInIc = new AlphaAnimation(0.0f , 1.0f ) ;
        tvICone.startAnimation(fadeInIc);
        fadeInIc.setDuration(1000);
        fadeInIc.setFillAfter(true);


        AlphaAnimation fadeInIcTwo = new AlphaAnimation(0.0f , 1.0f ) ;
        tvICtwo.startAnimation(fadeInIcTwo);
        fadeInIcTwo.setDuration(1000);
        fadeInIcTwo.setFillAfter(true);
        fadeInIcTwo.setStartOffset(500);

        AlphaAnimation fadeInIcurl = new AlphaAnimation(0.0f , 1.0f ) ;
        tvICthree.startAnimation(fadeInIcurl);
        fadeInIcurl.setDuration(1000);
        fadeInIcurl.setFillAfter(true);
        fadeInIcurl.setStartOffset(1000);



    }

}

package com.mnf.relax;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class CreditScreen extends AppCompatActivity {

    TextView tvOne,tvTwo,tvThree,tvBottom;
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


        Typeface font =Typeface.createFromAsset(getAssets(), "fonts/BerkshireSwash-Regular.ttf");
        Typeface fontLob =Typeface.createFromAsset(getAssets(), "fonts/Lobster-Regular.ttf");
        Typeface fontPaci =Typeface.createFromAsset(getAssets(), "fonts/Pacifico-Regular.ttf");

        tvOne.setTypeface(font);
        tvTwo.setTypeface(fontLob);
        tvBottom.setTypeface(fontPaci);


    }

}

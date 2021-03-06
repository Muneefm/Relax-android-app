package com.mnf.relax;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;
import com.codemybrainsout.ratingdialog.RatingDialog;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mnf.relax.Adapters.GridAdapter;
import com.mnf.relax.Misc.Config;
import com.mnf.relax.Misc.GridSpacingItemDecoration;
import com.mnf.relax.Misc.PreferensHandler;
import com.mnf.relax.Misc.RecyclerTouchListener;
import com.mnf.relax.Models.Item;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import angtrim.com.fivestarslibrary.FiveStarsDialog;
import angtrim.com.fivestarslibrary.NegativeReviewListener;
import angtrim.com.fivestarslibrary.ReviewListener;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener, BillingProcessor.IBillingHandler  {
    RelativeLayout rlLayout;
    RecyclerView recyclerView;
    GridAdapter adapter;
    RecyclerView.OnItemTouchListener listener;
    List<Item> itemsList;
    MediaPlayer player;
    MediaPlayer mediaPlayerBeach,mediaPlayerBirds,mediaPlayerBrown;
    MediaPlayer[] mediaPlayers;
    PreferensHandler pref;
    String manufacturer;
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;
    Context c;
    TextView tvTop;
    boolean doubleBackToExitPressedOnce;
    String model;
    TextView tvRelax,tvProd,tvRandom;
    CardView cvRelax,cvProd,cvRandom;
    boolean onRelax = false,onProd = false,onRand = false;
    Context acitivtyContext;
    int[] randomOne = {0,13},randomTwo = {4,6},randomThree={};
    ArrayList list = new ArrayList();
    int[] prev_selection;
    LinearLayout aboutRl;
    BillingProcessor bp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref = new PreferensHandler(getApplicationContext());
        c= getApplicationContext();
        acitivtyContext = this;
        showDialog();
        if(Config.isFirstTimeUser()){
            pref.setisFirstTimeUser(false);
            Intent i = new Intent(MainActivity.this, LaunchActivity.class);
            startActivity(i);
            finish();
        }
        bp = new BillingProcessor(this, Config.RSA_PLAYSTORE, this);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        tvTop = findViewById(R.id.tv_top);
        tvRelax = findViewById(R.id.relax_tv);
        tvProd = findViewById(R.id.productivity_tv);
        tvRandom = findViewById(R.id.rando_tv);
        aboutRl = findViewById(R.id.about_container);

        Typeface fontPaci =Typeface.createFromAsset(getAssets(), "fonts/Pacifico-Regular.ttf");
        Typeface fontPlayball =Typeface.createFromAsset(getAssets(), "fonts/Amaranth-Regular.ttf");

        tvTop.setTypeface(fontPaci);

        tvRelax.setTypeface(fontPlayball);
        tvProd.setTypeface(fontPlayball);
        tvRandom.setTypeface(fontPlayball);



        mediaPlayers = new  MediaPlayer[9];
        c = getApplicationContext();
         rlLayout  = findViewById(R.id.container);
         recyclerView = findViewById(R.id.recycler_view);

         if(!Config.isUserPaid()) {
            /* Log.e("TAG","user is not paid ads showing");
             mAdView = findViewById(R.id.adView);
             AdRequest adRequest = new AdRequest.Builder().build();
             mAdView.loadAd(adRequest);
             mAdView.setAdListener(new AdListener() {
                 @Override
                 public void onAdLoaded() {
                     // Code to be executed when an ad finishes loading.
                 }

                 @Override
                 public void onAdFailedToLoad(int errorCode) {
                     // Code to be executed when an ad request fails.
                 }

                 @Override
                 public void onAdOpened() {
                     // Code to be executed when an ad opens an overlay that
                     // covers the screen.
                 }

                 @Override
                 public void onAdLeftApplication() {
                     // Code to be executed when the user has left the app.
                 }

                 @Override
                 public void onAdClosed() {
                     // Code to be executed when when the user is about to return
                     // to the app after tapping on an ad.
                 }
             });*/
          /*       mInterstitialAd = new InterstitialAd(c);
                 mInterstitialAd.setAdUnitId("ca-app-pub-7269223551241818/1581642854");
                 mInterstitialAd.loadAd(new AdRequest.Builder().build());
             mInterstitialAd.setAdListener(new AdListener() {
                 @Override
                 public void onAdLoaded() {
                     // Code to be executed when an ad finishes loading.
                 }

                 @Override
                 public void onAdFailedToLoad(int errorCode) {
                     // Code to be executed when an ad request fails.
                 }

                 @Override
                 public void onAdOpened() {
                     // Code to be executed when the ad is displayed.
                 }

                 @Override
                 public void onAdLeftApplication() {
                     // Code to be executed when the user has left the app.
                 }

                 @Override
                 public void onAdClosed() {
                     // Code to be executed when when the interstitial ad is closed.
                 }
             });*/

             Log.e("TAG","user is  not paid");

         }else{
             Log.e("TAG","user is  paid ads skiping");

         }

         cvProd = findViewById(R.id.preset_two);
        cvRandom = findViewById(R.id.preset_three);
        cvRelax = findViewById(R.id.preset_one);

        cvProd.setOnClickListener(this);
        cvRandom.setOnClickListener(this);
        cvRelax.setOnClickListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_main);
        FloatingActionButton fabTwo = (FloatingActionButton) findViewById(R.id.fab_main_two);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            /*    Intent i = new Intent(MainActivity.this, CreditScreen.class);
                startActivity(i);*/
                stopAllMedia();
            }
        });
        fabTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            /*    Intent i = new Intent(MainActivity.this, CreditScreen.class);
                startActivity(i);*/
                stopAllMedia();
            }
        });

        aboutRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, CreditScreen.class);
                startActivity(i);
            }
        });

        if(new Config().hasNavBar(this.getWindowManager())){
           // fab.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT));
      /*     int margin =  Config.dpToPx(getResources().getDimensionPixelOffset(R.dimen.fab_margin),c);
          CoordinatorLayout.LayoutParams params =  new CoordinatorLayout.LayoutParams(CoordinatorLayout.LayoutParams.WRAP_CONTENT, CoordinatorLayout.LayoutParams.WRAP_CONTENT);
                    params.setMargins(margin,margin,margin,margin);
                    fab.setLayoutParams(params);*/
                    fab.setVisibility(View.VISIBLE);
                    fabTwo.setVisibility(View.GONE);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0,0,0,100);
            aboutRl.setLayoutParams(layoutParams);
            Log.e("MainActivity","Device has navigation bar");
        }else{
            Log.e("MainActivity","device does not have navigation bar");
            fab.setVisibility(View.GONE);
            fabTwo.setVisibility(View.VISIBLE);
        }



        itemsList = new ArrayList<>();
        itemsList.add(new Item(0,R.raw.beach,R.drawable.beach,false, false));
        itemsList.add(new Item(1,R.raw.birds,R.drawable.bird,false, false));
        itemsList.add(new Item(2,R.raw.fire,R.drawable.fire,false, false));
        itemsList.add(new Item(3,R.raw.sheep,R.drawable.sheep,false, true));
        itemsList.add(new Item(4,R.raw.river,R.drawable.river,false, false));
        itemsList.add(new Item(5,R.raw.drops,R.drawable.drop,false, false));
        itemsList.add(new Item(6,R.raw.forest,R.drawable.forest,false, false));
        itemsList.add(new Item(7,R.raw.leaves,R.drawable.leaves,false, true));
        itemsList.add(new Item(8,R.raw.pinknoise,R.drawable.pinknoise,true, true));
        itemsList.add(new Item(9,R.raw.brownnoise,R.drawable.brownnoise,false, true));
        itemsList.add(new Item(10,R.raw.rain,R.drawable.rain,false, false));
        itemsList.add(new Item(11,R.raw.thunder,R.drawable.thunder,true, true));
        itemsList.add(new Item(12,R.raw.cat_purr,R.drawable.catpurr,false, true));
        itemsList.add(new Item(13,R.raw.windchimes,R.drawable.windchimes,false, false));//Bitmap icon = BitmapFactory.decodeResource(c.getResources(),);
        list.add(randomOne);

        /* itemsList = new ArrayList<>();
       itemsList.add(new Item(0,R.raw.beach,BitmapFactory.decodeResource(c.getResources(),R.drawable.beach)));
        itemsList.add(new Item(1,R.raw.birds,BitmapFactory.decodeResource(c.getResources(),R.drawable.bird)));
        itemsList.add(new Item(2,R.raw.fire,BitmapFactory.decodeResource(c.getResources(),R.drawable.fire)));
        itemsList.add(new Item(3,R.raw.sheep,BitmapFactory.decodeResource(c.getResources(),R.drawable.sheep)));
        itemsList.add(new Item(4,R.raw.river,BitmapFactory.decodeResource(c.getResources(),R.drawable.river)));
        itemsList.add(new Item(5,R.raw.drops,BitmapFactory.decodeResource(c.getResources(),R.drawable.drop)));
        itemsList.add(new Item(6,R.raw.forest,BitmapFactory.decodeResource(c.getResources(),R.drawable.forest)));
        itemsList.add(new Item(7,R.raw.leaves,BitmapFactory.decodeResource(c.getResources(),R.drawable.leaves)));
        itemsList.add(new Item(8,R.raw.pinknoise,BitmapFactory.decodeResource(c.getResources(),R.drawable.pinknoise)));
        itemsList.add(new Item(9,R.raw.brownnoise,BitmapFactory.decodeResource(c.getResources(),R.drawable.brownnoise)));
        itemsList.add(new Item(10,R.raw.rain,BitmapFactory.decodeResource(c.getResources(),R.drawable.rain)));
        itemsList.add(new Item(11,R.raw.thunder,BitmapFactory.decodeResource(c.getResources(),R.drawable.thunder)));
        itemsList.add(new Item(12,R.raw.cat_purr,BitmapFactory.decodeResource(c.getResources(),R.drawable.catpurr)));
        itemsList.add(new Item(13,R.raw.windchimes,BitmapFactory.decodeResource(c.getResources(),R.drawable.windchimes)));
*/

        AnimationDrawable animationDrawable = (AnimationDrawable) rlLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();




        final GridLayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, Config.dpToPx(1,getApplicationContext()), true));
        //recyclerView.addItemDecoration(new MarginDecoration(this));
        recyclerView.setHasFixedSize(true);
        adapter = new GridAdapter(this,itemsList);
        recyclerView.setNestedScrollingEnabled(false);

        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        recyclerView.setAdapter(adapter);
        addRecycleTouchListener(itemsList,recyclerView);


    }
    public void addRecycleTouchListener(final List<Item> items, RecyclerView recyclerView){
        Log.e("TAG","on addRecycleTouchListener = ");

        listener = new RecyclerTouchListener(c, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position)  {
               Config.inCreaseUserClicks();
                Log.e("Bill","on addRecycleTouchListener  ");
                if(itemsList.get(position).getisLocked()){
                    // Pop the purchase dialogue
                    if(!pref.getisPaidUser()){

                        bp.purchase(MainActivity.this, Config.UNLOCK_PRODUCT_ID);
                        // onProductPurchased("",  null);
                    }
                }
               /* if(!Config.isUserPaid()&&Config.isClicksLimitExhausted()){
                    if (mInterstitialAd.isLoaded()) {
                        mInterstitialAd.show();
                    } else {
                        Log.d("TAG", "The interstitial wasn't loaded yet.");
                    }
                    mInterstitialAd.loadAd(new AdRequest.Builder().build());

                }*/

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        });
        recyclerView.addOnItemTouchListener(listener);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public void onDestroy() {
        if (bp != null) {
            bp.release();
        }
        super.onDestroy();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void startPLay(int item) throws IOException {
        //Log.e("TAG","start play");
        if(mediaPlayers[item]!=null){
            if(mediaPlayers[item].isPlaying()){
                mediaPlayers[item].stop();
                mediaPlayers[item].release();
                mediaPlayers[item] = null;
                Log.e("TAG","stop ");
            }
        }else {
            mediaPlayers[item] = MediaPlayer.create(c, itemsList.get(item).getPlay());
            mediaPlayers[item].setLooping(true);
            mediaPlayers[item].start();
            Log.e("TAG","first play ");
        }

        /*switch (item){
            case 0:
                    mediaPlayerBeach = MediaPlayer.create(c, R.raw.beach);
                    mediaPlayerBeach.setLooping(true);
                    mediaPlayerBeach.start();

                break;
            case 1:

                    mediaPlayerBirds = MediaPlayer.create(c, R.raw.birds);
                    mediaPlayerBirds.setLooping(true);
                    mediaPlayerBirds.start();

                break;
            case 2:

                    mediaPlayerBrown = MediaPlayer.create(c, R.raw.brownnoise);
                    mediaPlayerBrown.setLooping(true);
                    mediaPlayerBrown.start();

                break;
        }*/

    }

    @Override
    public void onBackPressed() {



        if (doubleBackToExitPressedOnce) {
            System.exit(0);
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
    private void showDialog() {
          manufacturer = Build.MANUFACTURER;
         model = Build.MODEL;

        final RatingDialog ratingDialog = new RatingDialog.Builder(this)
                .session(3)
                .threshold(4)
                .ratingBarColor(R.color.pink500)
                .positiveButtonTextColor(R.color.teal800)
                .playstoreUrl("https://play.google.com/store/apps/details?id=com.mnf.relax")
                .onRatingBarFormSumbit(new RatingDialog.Builder.RatingDialogFormListener() {
                    @Override
                    public void onFormSubmitted(String feedback) {
                        Log.e("rating","Feedback:" + feedback);
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        if(manufacturer==null){
                            manufacturer = "unknown device";
                        }
                        if(model==null){
                            model = "unknown model";
                        }
                        DatabaseReference myRef = database.getReference("RatingFeedbacks").child(""+manufacturer+" - "+model);

                        myRef.setValue("Feedback = "+feedback);
                    }
                })
                .build();


        ratingDialog.show();

    }


    @Override
    public void onClick(View view) {
        stopAllMedia();
        switch (view.getId()){
            case R.id.preset_one:
                clickRelax();
                break;
            case R.id.preset_two:
                clickProd();
                break;
            case R.id.preset_three:
                clickRandom();
                break;
            default:
                break;

        }
    }
    public void stopAllMedia(){
      /*  Log.e("stop","stopAllFunction Call");
      adapter.stopAllMedia();


      adapter = new GridAdapter(acitivtyContext,itemsList);
      recyclerView.setAdapter(adapter);*/
        Log.e("MainActivity", "stopAllMedia ");

      adapter.stopAllMedia();
        for (int i=0;i<itemsList.size();i++){
            Log.e("MainActivity", "inside loop i =  "+i);

           /* if (player != null) {
                if (player.isPlaying()) {*/
                    Log.e("MainActivity", "is playing for i "+i);

                    /*player.stop();
                    player.release();*/
                    recyclerView.findViewHolderForAdapterPosition(i).itemView.findViewById(R.id.icon_med).setAlpha(0.3f);
                    SeekBar bar = (SeekBar) recyclerView.findViewHolderForAdapterPosition(i).itemView.findViewById(R.id.volume_seek);
                    bar.setAlpha(0.3f);
         /*           bar.setProgress(0);
                }
            }
*/

        }

    }

    public void clickRelax(){
        if(recyclerView!=null){
                    if(!onRelax){
                        onRelax = true;
                    }else{
                        onRelax = false;
                    }

                    int[] relaxIds = Config.getRelaxIDs();

                    for(int ids: relaxIds){
                        Log.e("MainActivity","relax ids = "+ids);

                        recyclerView.findViewHolderForAdapterPosition(ids).itemView.findViewById(R.id.icon_med).performClick();
                    }


                    Log.e("preset","on handler perform click relax");
                }


    }

    public void clickProd(){
        if(recyclerView!=null){

                    if(!onProd){
                       // cvProd.setCardBackgroundColor(getResources().getColor(R.color.grey800));
                        onProd = true;
                    }else{
                       // cvProd.setCardBackgroundColor(getResources().getColor(android.R.color.transparent));
                        onProd = false;

                    }
                    recyclerView.findViewHolderForAdapterPosition(2).itemView.findViewById(R.id.icon_med).performClick();
                    recyclerView.findViewHolderForAdapterPosition(5).itemView.findViewById(R.id.icon_med).performClick();
                    recyclerView.findViewHolderForAdapterPosition(13).itemView.findViewById(R.id.icon_med).performClick();

                    Log.e("preset","on handler perform Prod");
        }


    }
    public void clickRandom(){
        if(recyclerView!=null){

                    if(!onRand){
                       // cvRandom.setCardBackgroundColor(getResources().getColor(R.color.grey800));
                        onRand = true;
                    }else{
                       // cvRandom.setCardBackgroundColor(getResources().getColor(android.R.color.transparent));
                        onRand = false;

                    }



            int[] randomIDs  = Config.getRandomIDs();

            for(int ids: randomIDs){
                Log.e("MainActivity","relax ids = "+ids);
                recyclerView.findViewHolderForAdapterPosition(ids).itemView.findViewById(R.id.icon_med).performClick();
            }
                    Log.e("preset","on handler perform Random");
        }
    }

    @Override
    public void onProductPurchased(@NonNull String productId, @Nullable TransactionDetails details) {
        Log.v("Bill","User has made the purchase of prod "+productId);
        if(pref!=null){
            pref.setisPaidUser(true);
            if(recyclerView != null){
                recyclerView.getAdapter().notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onPurchaseHistoryRestored() {
        Log.e("TAG","onPurchaseHistoryRestored ");
    }

    @Override
    public void onBillingError(int errorCode, @Nullable Throwable error) {
        Log.e("TAG","onBillingError ");

    }

    @Override
    public void onBillingInitialized() {
        Log.e("TAG","onBillingInitialized ");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!bp.handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}

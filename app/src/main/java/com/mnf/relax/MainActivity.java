package com.mnf.relax;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.mnf.relax.Adapters.GridAdapter;
import com.mnf.relax.Misc.Config;
import com.mnf.relax.Misc.GridSpacingItemDecoration;
import com.mnf.relax.Misc.RecyclerTouchListener;
import com.mnf.relax.Models.Item;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RelativeLayout rlLayout;
    RecyclerView recyclerView;
    GridAdapter adapter;
    RecyclerView.OnItemTouchListener listener;
    Context c;
    List<Item> itemsList;
    MediaPlayer player;
    MediaPlayer mediaPlayerBeach,mediaPlayerBirds,mediaPlayerBrown;
    MediaPlayer[] mediaPlayers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        mediaPlayers = new  MediaPlayer[9];
        c = getApplicationContext();
         rlLayout  = findViewById(R.id.container);
         recyclerView = findViewById(R.id.recycler_view);
         itemsList = new ArrayList<>();
         itemsList.add(new Item(0,R.raw.beach,R.drawable.beach));
        itemsList.add(new Item(1,R.raw.birds,R.drawable.bird));
        itemsList.add(new Item(2,R.raw.brownnoise,R.drawable.brownnoise));
        itemsList.add(new Item(3,R.raw.cat_purr,R.drawable.catpurr));
        itemsList.add(new Item(4,R.raw.drops,R.drawable.drop));
        itemsList.add(new Item(5,R.raw.fire,R.drawable.fire));
        itemsList.add(new Item(6,R.raw.forest,R.drawable.forest));
        itemsList.add(new Item(7,R.raw.leaves,R.drawable.leaves));
        itemsList.add(new Item(8,R.raw.pinknoise,R.drawable.pinknoise));
        itemsList.add(new Item(9,R.raw.rain,R.drawable.rain));
        itemsList.add(new Item(10,R.raw.river,R.drawable.river));
        itemsList.add(new Item(11,R.raw.sheep,R.drawable.sheep));
        itemsList.add(new Item(12,R.raw.thunder,R.drawable.thumb));
        itemsList.add(new Item(13,R.raw.windchimes,R.drawable.windchimes));


        AnimationDrawable animationDrawable = (AnimationDrawable) rlLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();
        final GridLayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, Config.dpToPx(1,getApplicationContext()), true));
        //recyclerView.addItemDecoration(new MarginDecoration(this));
        recyclerView.setHasFixedSize(true);
        adapter = new GridAdapter(getApplicationContext(),itemsList);
        recyclerView.setAdapter(adapter);
        addRecycleTouchListener(itemsList,recyclerView);


    }
    public void addRecycleTouchListener(final List<Item> items, RecyclerView recyclerView){
        Log.e("TAG","on addRecycleTouchListener = ");

        listener = new RecyclerTouchListener(c, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position)  {
                /*Log.e("TAG","on item click pos = "+position);
                try {
                    startPLay(position);
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("TAG","player error = "+e.getMessage());

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
}

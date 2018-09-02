package com.mnf.relax.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mnf.relax.LaunchActivity;
import com.mnf.relax.MainActivity;
import com.mnf.relax.Misc.Config;
import com.mnf.relax.Misc.PreferensHandler;
import com.mnf.relax.Models.Item;
import com.mnf.relax.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

/**
 * Created by muneef on 17/11/17.
 */

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder>{

    Context c;
    List<Item> mDataset;
    MediaPlayer[] mediaPlayers;
    private int mSelectedItemPosition = -1;
    PreferensHandler pref;


    public GridAdapter(Context context,List<Item> models){
        this.c = context;
        this.mDataset = models;
        this.mediaPlayers = new  MediaPlayer[mDataset.size()];
        pref = new PreferensHandler(c);

    }

    public boolean stopAllMedia(){
        for(int i = 0;i < mediaPlayers.length; i++){
            if (mediaPlayers[i] != null) {
                if (mediaPlayers[i].isPlaying()) {
                    Log.e("GridAdapter","stopAllMedia already playing stoping = "+i);

                    mediaPlayers[i].stop();
                    mediaPlayers[i].release();
                    mediaPlayers[i] = null;
                    Log.e("GridAdapter", "stopAllMedia stop ");
                    /*bar.setAlpha(0.3f);
                    imView.setAlpha(0.3f);
                    bar.setProgress(0);*/

                }
            }
        }



        return true;
    }


    public MediaPlayer[] getMediaPlayers(){
        return this.mediaPlayers;
    }





    public class ViewHolder extends RecyclerView.ViewHolder {

        public CardView cv;
        public ImageView imIcon, lockIcon;
        public SeekBar seekBar;
        // TagView tagGroup;




        public ViewHolder(View view) {
            super(view);
            Log.e("ViewHolder","on gridadapter viewHolder "+getAdapterPosition());
            lockIcon = view.findViewById(R.id.lock_img);
            imIcon = view.findViewById(R.id.icon_med);
            seekBar = view.findViewById(R.id.volume_seek);
            cv = view.findViewById(R.id.myCardView);
            int width  =  Config.getScreenDimensions("width",(MainActivity) c);
            width = width-100;
            Log.e("TAG","widtj of the screen is  = "+width);
            int singleItemWidth = width/2;
            cv.setLayoutParams(new RelativeLayout.LayoutParams(singleItemWidth, singleItemWidth));

            Log.e("TAG","seekbar width  new = "+(singleItemWidth-(singleItemWidth * 1/4)));

           // seekBar.setLayoutParams(new RelativeLayout.LayoutParams(width-(width * 1/4), 100));
            //LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new LinearLayout.LayoutParams(singleItemWidth/2,singleItemWidth/2))
            imIcon.setLayoutParams(new LinearLayout.LayoutParams(singleItemWidth/2,singleItemWidth/2));
            seekBar.setLayoutParams(new LinearLayout.LayoutParams(singleItemWidth-(singleItemWidth * 1/4), 100));

            imIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("TAG","click position is ="+getAdapterPosition());
                    if(mDataset.get(getAdapterPosition()).getisLocked() && (!pref.getisPaidUser())){
                        Toast.makeText(c, "Please purchase pro pack to play this",Toast.LENGTH_LONG).show();
                        return;
                    }
                    try {
                        startPLay(getAdapterPosition(),seekBar,imIcon);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });



            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    changeVolume(getAdapterPosition(),(float) i/10);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });

            //getAdapterPosition();


        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.player_item, parent, false);


        return new ViewHolder(itemView);    }

    @Override
    public void onBindViewHolder( ViewHolder holder,  int position) {

         Item item = mDataset.get(position);
        //Picasso.with(c).load(item.getImage()).into(holder.imIcon);

        holder.imIcon.setImageBitmap(BitmapFactory.decodeResource(c.getResources(),item.getImage()));
        if(mDataset.get(position).getisLocked() && (!pref.getisPaidUser())){
            holder.lockIcon.setVisibility(View.VISIBLE);
        }else{
            holder.lockIcon.setVisibility(View.GONE);
        }


        //holder.imIcon.setImageDrawable(c.getResources().getDrawable(item.getImage()));
           // holder.imIcon.setImageResource(item.getImage());
        //holder.imIcon.setIMa




      /*  switch (position){
            case 0:
                holder.imIcon.setImageDrawable(c.getResources().getDrawable(R.drawable.beach));
                break;
                default:
                    holder.imIcon.setImageDrawable(null);
                    break;

        }
*/


        /*holder.imIcon.setImageDrawable(c.getResources().getDrawable(mDataset.get(position).getImage()));
        holder.imIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("TAG","click position is ="+position);
                try {
                    startPLay(position,holder.seekBar,holder.imIcon);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        holder.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            changeVolume(position,(float) i/10);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
*/
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }


    @Override
    public int getItemCount() {
        return mDataset.size();
    }


    public void startPLay(int item,SeekBar bar,ImageView imView) throws IOException {
        Log.e("preset","start play item = "+item);
        if (mediaPlayers[item] != null) {
            if (mediaPlayers[item].isPlaying()) {
                Log.e("preset","already playing stoping = "+item);

                mediaPlayers[item].stop();
                mediaPlayers[item].release();
                mediaPlayers[item] = null;
                Log.e("TAG", "stop ");
                bar.setAlpha(0.3f);
                imView.setAlpha(0.3f);
                bar.setProgress(0);

            }
        } else {
            mediaPlayers[item] = MediaPlayer.create(c, mDataset.get(item).getPlay());
            mediaPlayers[item].setLooping(true);
            mediaPlayers[item].start();
            mediaPlayers[item].setVolume(0.5f,0.5f);
            bar.setAlpha(1f);
            bar.setProgress(5);
            imView.setAlpha(1f);
            Log.e("TAG", "first play ");
        }
    }
    public void changeVolume(int position,float volume){

            if(mediaPlayers[position] != null){
                if (mediaPlayers[position].isPlaying()) {
                    Log.e("TAG","volume came "+volume);
                   // Log.e("TAG","current volume before "+mediaPlayers[position].)
                    mediaPlayers[position].setVolume(volume,volume);
                }
            }
    }

}

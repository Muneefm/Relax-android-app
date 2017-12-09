package com.mnf.relax.Adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.media.Image;
import android.media.MediaPlayer;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.mnf.relax.Models.Item;
import com.mnf.relax.R;

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


    public GridAdapter(Context context,List<Item> models){
        this.c = context;
        this.mDataset = models;
        this.mediaPlayers = new  MediaPlayer[mDataset.size()];

    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        public CardView cv;
        public ImageView imIcon;
        public SeekBar seekBar;
        // TagView tagGroup;
        public ViewHolder(View view) {
            super(view);
            imIcon = view.findViewById(R.id.icon_med);
            seekBar = view.findViewById(R.id.volume_seek);

            imIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("TAG","click position is ="+getAdapterPosition());
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

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.player_item, parent, false);


        return new ViewHolder(itemView);    }

    @Override
    public void onBindViewHolder( ViewHolder holder,  int position) {


        holder.imIcon.setImageDrawable(c.getResources().getDrawable(mDataset.get(position).getImage()));


        Log.e("TAG","onBindViewHolder position = "+position);
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
        //Log.e("TAG","start play");
        if (mediaPlayers[item] != null) {
            if (mediaPlayers[item].isPlaying()) {
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

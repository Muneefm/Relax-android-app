package com.mnf.relax.Models;

import android.graphics.Bitmap;

/**
 * Created by muneef on 22/11/17.
 */

public class Item {
    int play;
    int image;
    int id;
    boolean isPlaying;


    public Item(int id, int play,int image,boolean isPlaying){
        this.image = image;
        this.id = id;
        this.play = play;
        this.isPlaying = isPlaying;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public void setPlay(int play) {
        this.play = play;
    }

    public int getPlay() {
        return play;
    }


    public void setisPlaying(boolean isplay) {
        this.isPlaying = isplay;
    }

    public boolean getisPLaying() {
        return isPlaying;
    }





}

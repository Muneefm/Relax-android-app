package com.mnf.relax.Models;

import android.graphics.Bitmap;

/**
 * Created by muneef on 22/11/17.
 */

public class Item {
    int play;
    Bitmap image;
    int id;


    public Item(int id, int play,Bitmap image){
        this.image = image;
        this.id = id;
        this.play = play;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setPlay(int play) {
        this.play = play;
    }

    public int getPlay() {
        return play;
    }
}

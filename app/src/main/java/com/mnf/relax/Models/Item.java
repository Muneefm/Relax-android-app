package com.mnf.relax.Models;

/**
 * Created by muneef on 22/11/17.
 */

public class Item {
    int play;
    int image;
    int id;


    public Item(int id, int play,int image){
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
}

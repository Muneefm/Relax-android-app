package com.mnf.relax.Misc;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;

import com.mnf.relax.AppController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by muneef on 17/11/17.
 */

public class Config {
    static int prevRandom=100;
    static int prevRandomRelax = 100;
    static int relaxI = 0;
    public static int dpToPx(int dp, Context c) {

        Resources r = c.getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    public static boolean isUserPaid(){
        PreferensHandler pref = new PreferensHandler(AppController.getInstance());
        return pref.getisPaidUser();
    }
    public static boolean isFirstTimeUser(){
        PreferensHandler pref = new PreferensHandler(AppController.getInstance());
        return pref.getisFirstTimeUser();
    }

    public static void inCreaseUserClicks(){
        Log.e("TAG","increasing user clicks current  - "+getCurrentClicks());

        PreferensHandler pref = new PreferensHandler(AppController.getInstance());
         pref.increaseClick();
    }

    public static int getCurrentClicks(){
        PreferensHandler pref = new PreferensHandler(AppController.getInstance());
        return pref.getClicks();
    }
    public static void clearUserClicks(){
        PreferensHandler pref = new PreferensHandler(AppController.getInstance());
         pref.clearClicks();
    }
    public static boolean isClicksLimitExhausted(){
        if(getCurrentClicks()>=5){
            clearUserClicks();
            Log.e("TAG","click limit exhausted");
            return true;
        }
        Log.e("TAG","click limit not exhausted current clicks - "+getCurrentClicks());
        return false;
    }

    public static int getScreenDimensions(String dimen, Activity act){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        act.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        if(dimen.equals("height"))
        return displayMetrics.heightPixels;
        else
       return displayMetrics.widthPixels;

    }

    public static int[] getRelaxIDs(){

        int[] randomOne = {0,13},
                randomTwo = {4,6},
                randomThree={2,5,10,11};

        List<int[]> relaxList = new ArrayList<>();
        relaxList.add(randomOne);
        relaxList.add(randomTwo);
        relaxList.add(randomThree);
       /* int ran = getRandomBetween(0,3);
        if(prevRandomRelax != 100) {
            while (ran == prevRandom) {
                ran = getRandomBetween(0, 3);
            }
        }
        prevRandom = ran;*/
       if(relaxI >= relaxList.size()){
           relaxI = 0;
       }
       Log.e("Config","getRelaxIDs  i = "+relaxI);
        return  relaxList.get(relaxI++);

    }

    public static int[] getRandomIDs(){
        int[] randomOne = {0,1,4},
                randomTwo = {8,4,2,6},
                randomThree={2,5,10,11},
                randomFour = {8,4,2,6},
                randomFive = {3,6,2,0,1};


                List<int[]> relaxList = new ArrayList<>();
        relaxList.add(randomOne);
        relaxList.add(randomTwo);
        relaxList.add(randomThree);
        relaxList.add(randomFive);
        relaxList.add(randomFour);
        int ran = getRandomBetween(0,relaxList.size());
        if(prevRandom != 100) {
            while (ran == prevRandom) {
                ran = getRandomBetween(0, relaxList.size());
            }
        }
        prevRandom = ran;
        return  relaxList.get(ran);

    }

    public static int getRandomBetween(int first, int last){
        Random r = new Random();
        return   r.nextInt(last - first) + first;
    }



}

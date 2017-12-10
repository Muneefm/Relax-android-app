package com.mnf.relax.Misc;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by muneef on 11/12/17.
 */

public class PreferensHandler {


    SharedPreferences pref;

    SharedPreferences.Editor editor;
    Context c;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "settings_pref";
    final String click_count = "clicks";
    final String paid_user = "paid_user";
    final String first_time_user = "first_user";




    @SuppressLint("CommitPrefEdits")
    public PreferensHandler(Context context) {
        this.c = context;
        pref = c.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }



    public void increaseClick(){
        Log.e("TAG","increaseClick current clicks = "+getClicks());
        editor.putInt(click_count,getClicks()+1 );
        editor.commit();
    }

    public int getClicks(){
        Log.e("TAG","getClicks current clicks = "+pref.getInt(click_count, 0));
        return pref.getInt(click_count, 0);
    }
    public void clearClicks(){
        Log.e("TAG","clearClicks current clicks = "+getClicks());
        editor.putInt(click_count,0 );
        editor.commit();
    }


    public void setisPaidUser(boolean var){
        editor.putBoolean(paid_user,var );
        editor.commit();
    }

    public boolean getisPaidUser(){
        return pref.getBoolean(paid_user, false);
    }

    public void setisFirstTimeUser(boolean var){
        editor.putBoolean(first_time_user,var );
        editor.commit();
    }

    public boolean getisFirstTimeUser(){
        return pref.getBoolean(first_time_user, true);
    }


}

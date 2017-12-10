package com.mnf.relax.Misc;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

import com.mnf.relax.AppController;

/**
 * Created by muneef on 17/11/17.
 */

public class Config {
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
}

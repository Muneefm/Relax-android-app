package com.mnf.relax;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.google.android.gms.ads.MobileAds;
import com.mnf.relax.Misc.PreferensHandler;

/**
 * Created by muneef on 10/12/17.
 */

public class AppController extends Application {

    static Context context;
    PreferensHandler pref;
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("TAg","appcontroller ");
        MobileAds.initialize(this, "ca-app-pub-7269223551241818~1769329201");
        context = this;
        pref = new PreferensHandler(getInstance());
        pref.setisPaidUser(true);

    }

    public static Context getInstance(){
        return context;
    }
}

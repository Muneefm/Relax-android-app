package com.mnf.relax;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.google.android.gms.ads.MobileAds;

/**
 * Created by muneef on 10/12/17.
 */

public class AppController extends Application {

    static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("TAg","appcontroller ");
        MobileAds.initialize(this, "ca-app-pub-7269223551241818~1769329201");
        context = this;

    }

    public static Context getInstance(){
        return context;
    }
}

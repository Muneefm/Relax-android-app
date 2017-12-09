package com.mnf.relax.Misc;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

/**
 * Created by muneef on 17/11/17.
 */

public class Config {
    public static int dpToPx(int dp, Context c) {

        Resources r = c.getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

}

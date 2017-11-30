package com.rz.librarycore;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * Created by Rz Rasel on 2017-08-14.
 */

public class UnitConversion {
    public static float getDpToPixel(Context argContext, float argDpValue) {
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        float pixelValue = argDpValue * (displayMetrics.densityDpi / 160f);
        return pixelValue;
        //Math.round(px)
    }

    public static float getDpToSp(Context argContext, float argDpValue) {
        float spValue = getDpToPixel(argContext, argDpValue) / (float) getSpToPixel(argContext, argDpValue);
        return spValue;
    }

    public static float getPixelToDp(Context argContext, float argPixelValue) {
        Resources resources = argContext.getResources();
        float dpValue = argPixelValue * resources.getDisplayMetrics().density;
        return dpValue;
    }

    public static float getPixelToSp(Context argContext, float argPixelValue) {
        float scaledDensity = argContext.getResources().getDisplayMetrics().scaledDensity;
        return argPixelValue / scaledDensity;
    }

    public static float getSpToPixel(Context argContext, float argSpValue) {
        float pixelValue = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, argSpValue, argContext.getResources().getDisplayMetrics());
        return pixelValue;
    }
    /*
    public static float getPixelToDp(Context argContext, int argPixelValue) {
        Resources resources = argContext.getResources();
        /-*int dpValue = Math.round(argPixelValue / (resources.getDisplayMetrics().densityDpi / 160f));
        return dpValue;*-/
    float dpValue = argPixelValue / (resources.getDisplayMetrics().densityDpi / 160f);
        return dpValue;
    }
    public static float getDipToPixelTemp(Context argContext, float argDipValue) {
        DisplayMetrics displayMetrics = argContext.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, argDipValue, displayMetrics);
    }

    public int getDpToPixel(Context argContext, float argDpValue) {
        DisplayMetrics displayMetrics = argContext.getResources().getDisplayMetrics();
        return Math.round(argDpValue * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }
    private int convertPxToDp(int px) {
        return Math.round(px / (Resources.getSystem().getDisplayMetrics().xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }
    */
}
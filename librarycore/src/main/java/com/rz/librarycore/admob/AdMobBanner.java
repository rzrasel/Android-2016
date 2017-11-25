package com.rz.librarycore.admob;

import android.app.Activity;
import android.content.Context;
import android.provider.Settings;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.rz.librarycore.cryption.MD5Builder;

/**
 * Created by Rz Rasel 2017-11-20.
 */

public class AdMobBanner {
    private Activity activity;
    private Context context;
    private AdView adView;
    private static AdMobBanner instance = null;

    public static AdMobBanner getInstance(Activity argActivity, Context argContext) {
        if (instance == null) {
            instance = new AdMobBanner(argActivity, argContext);
        }
        return instance;
    }

    public AdMobBanner(Activity argActivity, Context argContext) {
        this.activity = argActivity;
        this.context = argContext;
    }

    /**
     * Does some thing in old style.
     *
     * @deprecated use {@link #new()} instead.
     */
    @Deprecated
    public void onInit(int argViewId) {
        String androidId = Settings.Secure.getString(activity.getContentResolver(), Settings.Secure.ANDROID_ID);
        String testDeviceId = MD5Builder.md5(androidId).toUpperCase();
        //adView = (AdView) activity.findViewById(R.id.adView);
        //adView = argViewId;
        /*AdRequest adRequest = new AdRequest.Builder().build();
        adRequest.addTestDevice(deviceId);*/
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice(testDeviceId)
                .build();
        adView.loadAd(adRequest);
    }

    public void onInit(AdView argAdView) {
        String androidId = Settings.Secure.getString(activity.getContentResolver(), Settings.Secure.ANDROID_ID);
        String testDeviceId = MD5Builder.md5(androidId).toUpperCase();
        //adView = (AdView) activity.findViewById(R.id.adView);
        this.adView = argAdView;
        /*AdRequest adRequest = new AdRequest.Builder().build();
        adRequest.addTestDevice(deviceId);*/
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice(testDeviceId)
                .build();
        adView.loadAd(adRequest);
    }
}

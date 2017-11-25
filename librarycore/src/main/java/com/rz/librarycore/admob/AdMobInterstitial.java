package com.rz.librarycore.admob;

import android.app.Activity;
import android.content.Context;
import android.provider.Settings;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.rz.librarycore.cryption.MD5Builder;

/**
 * Created by Rz Rasel 2017-11-20.
 */

public class AdMobInterstitial {
    private Activity activity;
    private Context context;
    private EventHandlerListener eventHandlerListener;
    public InterstitialAd interstitialAd;
    private static AdMobInterstitial instance = null;
    private AdRequest adRequest;

    public static AdMobInterstitial getInstance(Activity argActivity, Context argContext) {
        if (instance == null) {
            instance = new AdMobInterstitial(argActivity, argContext);
        }
        return instance;
    }

    public AdMobInterstitial(Activity argActivity, Context argContext) {
        this.activity = argActivity;
        this.context = argContext;
    }

    public void onInit(String argAdUnitID) {
        if (argAdUnitID.isEmpty()) {
            argAdUnitID = "ca-app-pub-3940256099942544/1033173712";
        }
        //mInterstitialAd.loadAd(new AdRequest.Builder().build());
        String androidId = Settings.Secure.getString(activity.getContentResolver(), Settings.Secure.ANDROID_ID);
        String testDeviceId = MD5Builder.md5(androidId).toUpperCase();
        interstitialAd = new InterstitialAd(context);
        interstitialAd.setAdUnitId(argAdUnitID);
        adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice(testDeviceId)
                .build();
        interstitialAd.loadAd(adRequest);
        interstitialAd.setAdListener(adListener);
    }

    public void onSetEvent(EventHandlerListener argEventHandlerListener) {
        eventHandlerListener = argEventHandlerListener;
    }

    public void onShow() {
        if (interstitialAd.isLoaded()) {
            interstitialAd.show();
        } else {
            System.out.println("The interstitial wasn't loaded yet.");
        }
    }

    private AdListener adListener = new AdListener() {
        @Override
        public void onAdLoaded() {
            // Code to be executed when an ad finishes loading.
        }

        @Override
        public void onAdFailedToLoad(int errorCode) {
            // Code to be executed when an ad request fails.
        }

        @Override
        public void onAdOpened() {
            // Code to be executed when the ad is displayed.
        }

        @Override
        public void onAdLeftApplication() {
            // Code to be executed when the user has left the app.
        }

        @Override
        public void onAdClosed() {
            interstitialAd.loadAd(adRequest);
            if (eventHandlerListener != null) {
                eventHandlerListener.onAdClosed();
            }
        }
    };

    public interface EventHandlerListener {
        public void onAdClosed();
    }
}

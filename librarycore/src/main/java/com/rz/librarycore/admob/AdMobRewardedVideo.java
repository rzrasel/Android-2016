package com.rz.librarycore.admob;

import android.app.Activity;
import android.content.Context;
import android.provider.Settings;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.rz.librarycore.cryption.MD5Builder;

/**
 * Created by Rz Rasel on 2017-11-23.
 */

public class AdMobRewardedVideo {
    private Activity activity;
    private Context context;
    private EventHandlerListener eventHandlerListener;
    public RewardedVideoAd rewardedVideoAd;
    private static AdMobRewardedVideo instance = null;
    private AdRequest adRequest;
    private String adUnitID;

    public static AdMobRewardedVideo getInstance(Activity argActivity, Context argContext) {
        if (instance == null) {
            instance = new AdMobRewardedVideo(argActivity, argContext);
        }
        return instance;
    }

    public AdMobRewardedVideo(Activity argActivity, Context argContext) {
        this.activity = argActivity;
        this.context = argContext;
    }

    public void onInit(String argAdUnitID) {
        if (argAdUnitID.isEmpty()) {
            argAdUnitID = "ca-app-pub-3940256099942544/5224354917";
        }
        this.adUnitID = argAdUnitID;
        String androidId = Settings.Secure.getString(activity.getContentResolver(), Settings.Secure.ANDROID_ID);
        String testDeviceId = MD5Builder.md5(androidId).toUpperCase();
        rewardedVideoAd = MobileAds.getRewardedVideoAdInstance(context);
        //rewardedVideoAd.setUserId(argAdUnitID);
        adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice(testDeviceId)
                .build();
        //rewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917", new AdRequest.Builder().build());
        rewardedVideoAd.setRewardedVideoAdListener(rewardedVideoAdListener);
        onLoad();
    }

    private void onLoad() {
        rewardedVideoAd.loadAd(adUnitID, adRequest);
    }

    public void onSetEvent(EventHandlerListener argEventHandlerListener) {
        eventHandlerListener = argEventHandlerListener;
    }

    public void onShow() {
        if (rewardedVideoAd.isLoaded()) {
            rewardedVideoAd.show();
        } else {
            System.out.println("The rewarded video wasn't loaded yet.");
        }
    }

    RewardedVideoAdListener rewardedVideoAdListener = new RewardedVideoAdListener() {
        @Override
        public void onRewarded(RewardItem reward) {
            //Toast.makeText(this, "onRewarded! currency: " + reward.getType() + "  amount: " + reward.getAmount(), Toast.LENGTH_SHORT).show();
            // Reward the user.
        }

        @Override
        public void onRewardedVideoAdLeftApplication() {
            //Toast.makeText(this, "onRewardedVideoAdLeftApplication", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRewardedVideoAdClosed() {
            //Toast.makeText(this, "onRewardedVideoAdClosed", Toast.LENGTH_SHORT).show();
            onLoad();
            //System.out.println("Closed________");
            //interstitialAd.loadAd(adRequest);
            if (eventHandlerListener != null) {
                eventHandlerListener.onAdClosed();
            }
        }

        @Override
        public void onRewardedVideoAdFailedToLoad(int errorCode) {
            //Toast.makeText(this, "onRewardedVideoAdFailedToLoad", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRewardedVideoAdLoaded() {
            //Toast.makeText(this, "onRewardedVideoAdLoaded", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRewardedVideoAdOpened() {
            //Toast.makeText(this, "onRewardedVideoAdOpened", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRewardedVideoStarted() {
            //Toast.makeText(this, "onRewardedVideoStarted", Toast.LENGTH_SHORT).show();
        }
    };

    public void onResume() {
        rewardedVideoAd.resume(context);
        onLoad();
        //super.onResume();
    }

    public void onPause() {
        rewardedVideoAd.pause(context);
        //super.onPause();
    }

    public void onDestroy() {
        rewardedVideoAd.destroy(context);
        //super.onDestroy();
    }

    public interface EventHandlerListener {
        public void onAdClosed();
    }
}

package me.apphive.doordekhaetui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.android.gms.ads.AdView;
import com.rz.librarycore.admob.AdMobBanner;
import com.rz.librarycore.admob.AdMobInterstitial;
import com.rz.librarycore.admob.ServiceAdMobInterstitial;

import me.apphive.doordekhaetui.R;
import me.apphive.doordekhaetui.service.ServiceAdaptChannelURL;
import me.apphive.doordekhaetui.service.ServiceAdaptHostingURL;

public class ActSplash extends AppCompatActivity {
    private Activity activity;
    private Context context;
    public static AdMobInterstitial adMobInterstitial;
    private View idIncludeFooterView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_splash);
        activity = this;
        context = this;
        idIncludeFooterView = (View) findViewById(R.id.idIncLayFooter);
        new AdMobIntegration();
        //onUpdateVolume();
        Intent intentActivity = new Intent(this, ActDashboard.class);
        startActivity(intentActivity);
        this.finish();
    }

    //|――――|―――――――――――――――――――――――――――――――|
    public class AdMobIntegration {
        //|――――|―――――――――――――――――――――――――――――――|
        public AdMobIntegration() {
            //adMobBanner = new AdMobBanner(activity, context);
            AdMobBanner adMobBanner = new AdMobBanner(activity, context);
            adMobBanner.onInit((AdView) idIncludeFooterView.findViewById(R.id.adView));
            //adMobInterstitial = new AdMobInterstitial(activity, context);
            adMobInterstitial = AdMobInterstitial.getInstance(activity, context);
            adMobInterstitial.onInit("");
            onStartAdmobService();
            onInitService();
        }

        //|――――|―――――――――――――――――――――――――――――――|
        private void onStartAdmobService() {
            /*getPackageManager().setComponentEnabledSetting(
                new ComponentName("me.apphive.gpulview.activity", "me.apphive.gpulview.activity.MainActivity-Red"),
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);*/
            /*String aliasName = "ActSplash";
            ComponentName componentName = new ComponentName(context, context.getPackageName() + "." + aliasName);
            context.getPackageManager().setComponentEnabledSetting(componentName,
                    PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                    PackageManager.DONT_KILL_APP);*/
            ServiceAdMobInterstitial.eventHandlerListener = new ServiceAdMobInterstitial.EventHandlerListener() {
                @Override
                public void onShowListener() {
                    adMobInterstitial.onShow();
                }
            };
            ServiceAdMobInterstitial.repeatedSeconds = 5 * 60;
            Intent inetntService = new Intent(context, ServiceAdMobInterstitial.class);
            startService(inetntService);
        }

        //|――――|―――――――――――――――――――――――――――――――|
        private void onInitService() {
            //|――――|―――――――――――――――――――――――――――――――|
            Intent serviceIntent = null;
            //|――――|―――――――――――――――――――――――――――――――|
            ServiceAdaptHostingURL.context = context;
            serviceIntent = new Intent(context, ServiceAdaptHostingURL.class);
            startService(serviceIntent);
            //|――――|―――――――――――――――――――――――――――――――|
            ServiceAdaptChannelURL.context = context;
            serviceIntent = new Intent(context, ServiceAdaptChannelURL.class);
            startService(serviceIntent);
            //|――――|―――――――――――――――――――――――――――――――|
        }

        //|――――|―――――――――――――――――――――――――――――――|
    }

    //|――――|―――――――――――――――――――――――――――――――|
    public void onUpdateVolume() {
        AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int currentVolume = audio.getStreamVolume(AudioManager.STREAM_MUSIC);
        int maxVolume = audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        float percent = 0.7f;
        percent = 0.4f;
        int seventyVolume = (int) (maxVolume * percent);
        audio.setStreamVolume(AudioManager.STREAM_MUSIC, seventyVolume, 0);
        audio.setStreamVolume(AudioManager.STREAM_RING, seventyVolume, 0);

        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(getPackageName(), 0);
            String version = pInfo.versionName;
            System.out.println("VERSION_CODE: " + pInfo.versionCode);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
    //|――――|―――――――――――――――――――――――――――――――|
}
/*
this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
this.setVolumeControlStream(AudioManager.STREAM_RING);
this.setVolumeControlStream(AudioManager.STREAM_ALARM);
this.setVolumeControlStream(AudioManager.STREAM_NOTIFICATION);
this.setVolumeControlStream(AudioManager.STREAM_SYSTEM);
this.setVolumeControlStream(AudioManager.STREAM_VOICECALL);
https://stackoverflow.com/questions/4593552/how-do-you-get-set-media-volume-not-ringtone-volume-in-android
https://www.android-examples.com/set-ringer-volume-in-android-using-seekbar/
mobilemode.setStreamVolume(AudioManager.STREAM_RING,audioManager.getStreamMaxVolume(AudioManager.STREAM_RING),0);
https://stackoverflow.com/questions/40925722/how-to-increase-and-decrease-the-volume-programmatically-in-android
*/
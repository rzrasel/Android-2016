package me.apphive.doordekhaetui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;

import com.google.android.gms.ads.AdView;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.rz.librarycore.AppUtils;
import com.rz.librarycore.admob.AdMobBanner;
import com.rz.librarycore.admob.AdMobInterstitial;
import com.rz.librarycore.admob.AdMobRewardedVideo;
import com.rz.librarycore.admob.ServiceAdMobInterstitial;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import me.apphive.doordekhaetui.R;
import me.apphive.doordekhaetui.adapter.AdapterULDetails;
import me.apphive.doordekhaetui.application.AppApplication;
import me.apphive.doordekhaetui.constants.APPConstants;
import me.apphive.doordekhaetui.model.ModelDynamic;
import me.apphive.doordekhaetui.model.ModelItemList;
import me.apphive.doordekhaetui.model.ModelULDetails;
import me.apphive.doordekhaetui.receiver.ReceiverHostResponse;

import static me.apphive.doordekhaetui.model.ModelItemList.tubeTvChannelList;

public class ActDashboard extends AppCompatActivity {
    //|------------------------------------------------------------|
    private Activity activity;
    private Context context;
    private IntentFilter intentFilter;
    //|------------------------------------------------------------|
    private AdapterULDetails adapterULDetails;
    private ArrayList<ModelULDetails> modelDetailsItems = new ArrayList<ModelULDetails>();
    private ReceiverHostResponse receiverHostResponse;
    //|------------------------------------------------------------|
    private GridView sysGridThumbnail;
    public static AdMobInterstitial adMobInterstitial;
    public static AdMobRewardedVideo adMobRewardedVideo;
    //private View idIncludeFooterView;
    private TextView sysTvAppVersion;
    private String youtubeVideoId = "O7EnOIx5OWo";
    private FirebaseAnalytics firebaseAnalytics;

    //|------------------------------------------------------------|
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //|------------------------------------------------------------|
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_dashboard);
        //|------------------------------------------------------------|
        activity = this;
        context = this;
        //|------------------------------------------------------------|
        //idIncludeFooterView = (View) findViewById(R.id.idIncLayFooter);
        sysGridThumbnail = (GridView) findViewById(R.id.sysGridThumbnail);
        sysTvAppVersion = (TextView) findViewById(R.id.sysTvAppVersion);
        sysTvAppVersion.setText("Version: " + AppUtils.getAppVersion(context));
        //|------------------------------------------------------------|
        /*ModelULDetails modelULDetails;

        for (int i = 1; i < 300; i++) {
            String j = "";
            j = i + "";
            if (i < 10) {
                j = "00" + i;
            } else if (i < 100) {
                j = "0" + i;
            }
            modelULDetails = new ModelULDetails();
            modelULDetails.setTitle("Test - " + j);
            modelDetailsItems.add(modelULDetails);
        }*/
        //|------------------------------------------------------------|
        adapterULDetails = new AdapterULDetails(context, 0, modelDetailsItems);
        sysGridThumbnail.setAdapter(adapterULDetails);
        //|------------------------------------------------------------|
        /*sysGridThumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View argView) {
                //
            }
        });*/
        sysGridThumbnail.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> argParent, View argView, int argPosition, long argId) {
                //Toast.makeText(context, "Position: " + argPosition, Toast.LENGTH_LONG).show();
                youtubeVideoId = modelDetailsItems.get(argPosition).getStreamUrl();
                //Toast.makeText(context, "CHANNEL: " + youtubeVideoId, Toast.LENGTH_LONG).show();
                onPlayYouTubeStandalone(youtubeVideoId);
                //adMobInterstitial.onShow();
                Handler mHandler = new Handler(getMainLooper());
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //adMobRewardedVideo.onShow();
                    }
                });
            }
        });
        //|------------------------------------------------------------|
        /*AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");*/
        /*AdView mAdView;
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);*/
        new AdMobIntegration();
        //https://developers.google.com/admob/android/interstitial
        //|------------------------------------------------------------|
        //DisplaySize displaySize = new DisplaySize(context);
        //Toast.makeText(context, "Position: " + DisplaySize.SCREEN.WIDTH + " - " + DisplaySize.SCREEN.HEIGHT, Toast.LENGTH_LONG).show();
        //|------------------------------------------------------------|
        firebaseAnalytics = FirebaseAnalytics.getInstance(context);
        //|------------------------------------------------------------|
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, randomIndex() + "");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "The Hunger Games");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "books");
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
        //|------------------------------------------------------------|
        //Sets whether analytics collection is enabled for this app on this device.
        firebaseAnalytics.setAnalyticsCollectionEnabled(true);
        //Sets the minimum engagement time required before starting a session. The default value is 10000 (10 seconds). Let's make it 20 seconds just for the fun
        firebaseAnalytics.setMinimumSessionDuration(20000);
        //Sets the duration of inactivity that terminates the current session. The default value is 1800000 (30 minutes).
        firebaseAnalytics.setSessionTimeoutDuration(500);
        //Sets the user ID property.
        firebaseAnalytics.setUserId(String.valueOf(randomIndex()));
        //Sets a user property to a given value.
        firebaseAnalytics.setUserProperty("Food", "The Hunger Games");
        //|------------------------------------------------------------|
        AppApplication.getInstance().trackEvent("Cat-Screen", "Act-Screen", "Lbl-Screen");
        //|------------------------------------------------------------|
    }

    @Override
    public void onConfigurationChanged(Configuration argNewConfig) {
        super.onConfigurationChanged(argNewConfig);

        // Checks the orientation of the screen
        if (argNewConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //Toast.makeText(context, "ORIENTATION_LANDSCAPE", Toast.LENGTH_LONG).show();

        } else if (argNewConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            //Toast.makeText(context, "ORIENTATION_LANDSCAPE", Toast.LENGTH_LONG).show();
        }
        //Toast.makeText(context, "Position: " + DisplaySize.SCREEN.WIDTH + " - " + DisplaySize.SCREEN.HEIGHT, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        //ServiceAdMobInterstitial.isPaused = false;
        adMobRewardedVideo.onResume();
        //|----|------------------------------------------------------------|
        String strFilter = "";
        strFilter = getPackageName().toLowerCase() + ".getChannelList".toLowerCase();
        //|----|------------------------------------------------------------|*/
        //IntentFilter intentFilter = new IntentFilter();
        //intentFilter.addAction(strFilter);
        //intentFilter.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        registerReceiver(receiverHostResponse, intentFilter);
        super.onResume();
    }

    @Override
    protected void onPause() {
        //ServiceAdMobInterstitial.isPaused = true;
        adMobRewardedVideo.onPause();
        unregisterReceiver(receiverHostResponse);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        stopService(new Intent(context, ServiceAdMobInterstitial.class));
        adMobRewardedVideo.onDestroy();
        APPConstants.BROADCAST.IS_RECEIVED_RESPONSE_CHANNEL_INDEXING = false;
        super.onDestroy();
    }

    public class AdMobIntegration {

        public AdMobIntegration() {
            //adMobBanner = new AdMobBanner(activity, context);
            AdMobBanner adMobBanner = new AdMobBanner(activity, context);
            adMobBanner.onInit((AdView) findViewById(R.id.adView));
            //adMobInterstitial = new AdMobInterstitial(activity, context);
            adMobInterstitial = AdMobInterstitial.getInstance(activity, context);
            adMobInterstitial.onInit("");
            adMobInterstitial.onSetEvent(new AdMobInterstitial.EventHandlerListener() {
                @Override
                public void onAdClosed() {
                    onPlayYouTubeStandalone(youtubeVideoId);
                }
            });
            ////////////////
            adMobRewardedVideo = AdMobRewardedVideo.getInstance(activity, context);
            adMobRewardedVideo.onInit("");
            adMobRewardedVideo.onSetEvent(new AdMobRewardedVideo.EventHandlerListener() {
                @Override
                public void onAdClosed() {
                    onPlayYouTubeStandalone(youtubeVideoId);
                }
            });
            ////////////////
            //https://developers.google.com/admob/android/rewarded-video
            ////////////////
            onInitBroadcastReceiver();
        }

        //|――――|―――――――――――――――――――――――――――――――|
        private void onInitBroadcastReceiver() {
            //|――――|―――――――――――――――――――――――――――――――|
            String strIntentFilterHostIndexing = APPConstants.READONLY.RECEIVER_INTENT_FILTER.URL_RESPONSE_HOST_INDEXING;
            intentFilter = new IntentFilter();
            intentFilter.addAction(APPConstants.READONLY.RECEIVER_INTENT_FILTER.URL_RESPONSE_HOST_INDEXING);
            intentFilter.addAction(APPConstants.READONLY.RECEIVER_INTENT_FILTER.URL_RESPONSE_CHANNEL_INDEXING);
            receiverHostResponse = new ReceiverHostResponse(new ReceiverHostResponse.OnListenerHandler() {
                @Override
                public void onReceive(Context argContext, Intent argIntent, String argIntentAction) {
                    if (argIntentAction.equalsIgnoreCase(APPConstants.READONLY.RECEIVER_INTENT_FILTER.URL_RESPONSE_CHANNEL_INDEXING)) {
                        APPConstants.BROADCAST.IS_RECEIVED_RESPONSE_CHANNEL_INDEXING = true;
                        System.out.println("EQUALS: " + argIntentAction);
                        //System.out.println(argIntentAction);
                        System.out.println("VALUES: " + ModelItemList.hashMapHostURLIndexing.toString());
                        System.out.println("VALUES_YOUTUBE: " + tubeTvChannelList.toString());
                        if (tubeTvChannelList.size() > 0) {
                            modelDetailsItems.clear();
                            for (ModelDynamic items : ModelItemList.tubeTvChannelList) {
                                //ModelDynamic channelItem;
                                HashMap<String, ?> channelItem = items.getDynamicModelList();
                                //ModelDynamic channelItem = items.getDynamicModelList();
                                ModelULDetails modelULDetails = new ModelULDetails();
                                modelULDetails.setTitle(channelItem.get("channel_name").toString());
                                modelULDetails.setDescription(channelItem.get("channel_desc").toString());
                                modelULDetails.setChannelLogo(channelItem.get("channel_logo").toString());
                                modelULDetails.setStreamUrl(channelItem.get("channel_stream_url").toString());
                                modelDetailsItems.add(modelULDetails);
                                System.out.println("VALUES_ITEM: " + channelItem.get("channel_name"));
                            }
                            adapterULDetails.notifyDataSetChanged();
                        }
                    }
                }
            });
            //|――――|―――――――――――――――――――――――――――――――|
        }

        //|――――|―――――――――――――――――――――――――――――――|
    }

    private void onPlayYouTubeStandalone(String argYoutubeVideoId) {
        String gpDevKeyYoutube = getResources().getString(R.string.google_developer_key_youtube);
        //Intent intentYoutube = YouTubeStandalonePlayer.createVideoIntent(activity, gpDevKeyYoutube, "Gr5j5nryfqM");
        boolean autoplay = true;
        boolean lightboxMode = false;
        int startTimeMillis = 0;
        Intent intentYoutube = null;
        intentYoutube = YouTubeStandalonePlayer.createVideoIntent(activity, gpDevKeyYoutube, argYoutubeVideoId, startTimeMillis, autoplay, lightboxMode);
        startActivity(intentYoutube);
    }

    private int randomIndex() {
        int min = 0;
        int max = 999999;
        Random rand = new Random();
        return min + rand.nextInt((max - min) + 1);
    }
}
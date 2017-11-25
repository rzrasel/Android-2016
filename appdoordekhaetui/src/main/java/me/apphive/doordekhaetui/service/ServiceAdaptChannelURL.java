package me.apphive.doordekhaetui.service;

/**
 * Created by Rz Rasel on 2017-11-05.
 */

import android.app.Activity;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

import com.rz.librarycore.AppUtils;
import com.rz.librarycore.admob.ServiceAdMobInterstitial;
import com.rz.librarycore.certificate.CertificateSHA1Fingerprint;
import com.rz.librarycore.iconnect.NetConnDetect;
import com.rz.librarycore.log.LogWriter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import me.apphive.doordekhaetui.ApiHTTPClientCall;
import me.apphive.doordekhaetui.constants.APPConstants;
import me.apphive.doordekhaetui.model.ModelDynamic;
import me.apphive.doordekhaetui.model.ModelItemList;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

public class ServiceAdaptChannelURL extends IntentService {
    //|――――|―――――――――――――――――――――――――――――――|
    public Activity activity;
    public static Context context;
    //|――――|―――――――――――――――――――――――――――――――|
    private CertificateSHA1Fingerprint certSHA1 = CertificateSHA1Fingerprint.getInstance();
    private NetConnDetect netConnDetect = NetConnDetect.getInstance();

    //|――――|―――――――――――――――――――――――――――――――|
    private static boolean isReadURLData = true;
    private static boolean isStatusReadFinishedURLData = false;
    private static boolean isResponseCodeOk = false;
    private static boolean isCheckedResponse = false;
    private static boolean isStatusReadingURLData = false;

    //|――――|―――――――――――――――――――――――――――――――|
    public ServiceAdaptChannelURL() {
        super("ServiceAdaptChannelURL");
    }

    //|――――|―――――――――――――――――――――――――――――――|
    public void onCreate() {
        super.onCreate();
    }
    //|――――|―――――――――――――――――――――――――――――――|

    @Override
    protected void onHandleIntent(Intent argIntent) {
        while (isReadURLData) {
            if (isStatusReadFinishedURLData) {
                //isReadURLData = true;
                //return;
                if (APPConstants.BROADCAST.IS_RECEIVED_RESPONSE_CHANNEL_INDEXING) {
                    stopSelf();
                    return;
                } else {
                    Intent broadcastIntent = new Intent();
                    //broadcastIntent.setAction(APPConstants.READONLY.RECEIVER_INTENT_FILTER.READ_START_URL);
                    broadcastIntent.setAction(APPConstants.READONLY.RECEIVER_INTENT_FILTER.URL_RESPONSE_CHANNEL_INDEXING);
                    //broadcastIntent.setAction("filter13213132131");
                    broadcastIntent.putExtra("data", "Broadcast Data");
                    sendBroadcast(broadcastIntent);
                }
            }
            if (APPConstants.URL_READ_COMPLETE.IS_RESPONSE_READ_HOST_INDEXING && ModelItemList.hashMapHostURLIndexing.size() > 0) {
                ModelDynamic modelDynamic;
                modelDynamic = ModelItemList.hashMapHostURLIndexing.get("domain_url_list");
                /*LogWriter.Log("PRINT-----------------: " + ModelItemList.initDomainInfoList.size());
                LogWriter.Log("PRINT-----------------: " + ModelItemList.initDomainInfoList.toString());
                LogWriter.Log("PRINT-----------------: " + modelDynamic.getDynamicModelList().get("url_list_url").toString());*/
                netConnDetect = NetConnDetect.getInstance();
                int interstitialTime = 5 * 1000;
                if (netConnDetect.isConnected(context) && !isStatusReadFinishedURLData) {
                    String hostURL = modelDynamic.getDynamicModelList().get("url_list_url").toString();
                    interstitialTime = Integer.valueOf(modelDynamic.getDynamicModelList().get("interstitial_time").toString());
                    ServiceAdMobInterstitial.repeatedSeconds = interstitialTime;
                    if (!isResponseCodeOk && !isCheckedResponse) {
                        isResponseCodeOk = AppUtils.isURLAvailable(hostURL);
                        isCheckedResponse = true;
                    } else if (!isResponseCodeOk) {
                        isResponseCodeOk = AppUtils.isURLAvailable(hostURL);
                        LogWriter.Log("INVALID_URL: " + hostURL);
                    }
                    if (isResponseCodeOk) {
                        if (!isStatusReadingURLData) {
                            isStatusReadingURLData = true;
                            onExecuteOkHTTPString(hostURL);
                            //isReadURLData = false;
                        }
                    } else {
                        LogWriter.Log("TRY_FOR_READ_URL");
                    }
                } else {
                    if (!isStatusReadFinishedURLData) {
                        LogWriter.Log("PLEASE_CHECK_YOUR_INTERNET_CONNECTION");
                        isCheckedResponse = false;
                    }
                    //LogWriter.Log("RUN_WHILE_LOOP");
                    SystemClock.sleep(10);
                }
            }
        }
    }

    //|――――|―――――――――――――――――――――――――――――――|
    private void onExecuteOkHTTPString(String argRequestURL) {
        RequestBody requestBody = new FormBody.Builder()
                .add("auth_key", certSHA1.getAuthKey(context))
                .add("package_name", context.getPackageName() + "")
                .add("app_version", AppUtils.getAppVersion(context))
                .build();
        System.out.println("REQUEST_BODY: " + certSHA1.getAuthKey(context));
        try {
            OkHttpClient client = new OkHttpClient();
            String response = ApiHTTPClientCall.POST(
                    client,
                    argRequestURL,
                    requestBody);

            //Log.d("Response", response);
            LogWriter.Log("RESPONSE: " + response);
            onJSONParse(response);
            isStatusReadFinishedURLData = true;
        } catch (IOException e) {
            //e.printStackTrace();
            LogWriter.Log("ERROR: " + e.toString());
            isStatusReadingURLData = false;
        }
    }

    //|――――|―――――――――――――――――――――――――――――――|
    private void onJSONParse(String argStrJSONData) {
        if (!isValidJSON(argStrJSONData)) {
            return;
        }
        try {
            ModelItemList.hashMapChannelIndexing.clear();
            //HashMap<String, Object> itemList = new HashMap<>();
            JSONObject jsonObject = new JSONObject(argStrJSONData);
            JSONArray jsonArray = jsonObject.getJSONArray("channel_list");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjChannelList = jsonArray.getJSONObject(i);
                HashMap<String, Object> itemListChannelTmp = new HashMap<>();
                ModelDynamic itemListChannel;
                itemListChannelTmp.put("channel_id", jsonObjChannelList.getString("channel_id"));
                itemListChannelTmp.put("channel_name", jsonObjChannelList.getString("channel_name"));
                itemListChannelTmp.put("channel_desc", jsonObjChannelList.getString("channel_desc"));
                String channelType = jsonObjChannelList.getString("channel_type");
                String channelLan = jsonObjChannelList.getString("channel_lang");
                itemListChannelTmp.put("channel_type", channelType);
                itemListChannelTmp.put("channel_lang", channelLan);
                itemListChannelTmp.put("channel_stream_url", jsonObjChannelList.getString("channel_stream_url"));
                itemListChannelTmp.put("channel_logo", jsonObjChannelList.getString("channel_logo"));
                itemListChannelTmp.put("channel_viewers", jsonObjChannelList.getString("channel_viewers"));
                itemListChannelTmp.put("alive_user", jsonObjChannelList.getString("alive_user"));

                if (channelType.equalsIgnoreCase("youtube")) {
                    if (!channelLan.substring(0, 2).equalsIgnoreCase("x-") && !channelLan.substring(0, 2).equalsIgnoreCase("ww") && !channelLan.substring(0, 3).equalsIgnoreCase("mov"))
                        ModelItemList.tubeTvChannelList.add(new ModelDynamic(itemListChannelTmp));
                    else
                        ModelItemList.tubeTvMovieList.add(new ModelDynamic(itemListChannelTmp));
                } else if (channelType.equals("iptv")) {
                    ModelItemList.ipTvChannelList.add(new ModelDynamic(itemListChannelTmp));
                }
            }
        } catch (JSONException e) {
            //e.printStackTrace();
            LogWriter.Log("JSON ERROR:- " + e.getMessage());
        }
    }

    //|――――|―――――――――――――――――――――――――――――――|
    public boolean isValidJSON(String argJsonString) {
        try {
            new JSONObject(argJsonString);
        } catch (JSONException e) {
            // edited, to include @Arthur's comment
            // e.g. in case JSONArray is valid as well...
            try {
                new JSONArray(argJsonString);
            } catch (JSONException ex) {
                return false;
            }
        }
        return true;
    }
    //|――――|―――――――――――――――――――――――――――――――|
}
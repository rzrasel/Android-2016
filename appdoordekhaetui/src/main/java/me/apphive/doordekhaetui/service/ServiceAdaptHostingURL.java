package me.apphive.doordekhaetui.service;

import android.app.Activity;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

import com.rz.librarycore.AppUtils;
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

/**
 * Created by Rz Rasel on 2017-08-30.
 */

public class ServiceAdaptHostingURL extends IntentService {
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

    public ServiceAdaptHostingURL() {
        super("ServiceReadStartURL");
    }

    //|――――|―――――――――――――――――――――――――――――――|
    public void onCreate() {
        super.onCreate();
    }
    //|――――|―――――――――――――――――――――――――――――――|

    @Override
    protected void onHandleIntent(Intent argIntent) {
        isReadURLData = true;
        while (isReadURLData) {
            if (isStatusReadFinishedURLData) {
                //isReadURLData = true;
                //return;
                if (APPConstants.BROADCAST.IS_RECEIVED_RESPONSE_HOST_INDEXING) {
                    stopSelf();
                    return;
                } else {
                    Intent broadcastIntent = new Intent();
                    //broadcastIntent.setAction(APPConstants.READONLY.RECEIVER_INTENT_FILTER.READ_START_URL);
                    broadcastIntent.setAction(APPConstants.READONLY.RECEIVER_INTENT_FILTER.URL_RESPONSE_HOST_INDEXING);
                    //broadcastIntent.setAction("filter13213132131");
                    broadcastIntent.putExtra("data", "Broadcast Data");
                    sendBroadcast(broadcastIntent);
                }
            }
            if (netConnDetect.isConnected(context) && !isStatusReadFinishedURLData) {
                String localURL = "http://192.168.0.102/and-app-api/app-tv-bangla-url.php";
                localURL = "http://jagoron24.com/app-tv-bangla-url.php";
                if (!isResponseCodeOk && !isCheckedResponse) {
                    isResponseCodeOk = AppUtils.isURLAvailable(localURL);
                    isCheckedResponse = true;
                } else if (!isResponseCodeOk) {
                    isResponseCodeOk = AppUtils.isURLAvailable(localURL);
                    LogWriter.Log("INVALID_URL: " + localURL);
                    /*stopSelf();
                    return;*/
                }
                if (isResponseCodeOk) {
                    if (!isStatusReadingURLData) {
                        isStatusReadingURLData = true;
                        onExecuteOkHTTPString(localURL);
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
            String status = "0";
            HashMap<String, Object> itemList = new HashMap<>();
            ModelItemList.hashMapHostURLIndexing.clear();
            JSONObject jsonObject = new JSONObject(argStrJSONData);
            status = jsonObject.getString("status");


            JSONObject jsonObjAppData = jsonObject.getJSONObject("app_data");
            JSONObject jsonObjServerURL = jsonObjAppData.getJSONObject("server_url");

            String domainRoot = jsonObjServerURL.getString("root_url");
            String domainPathSegment = jsonObjServerURL.getString("base_url");
            String domainURLList = jsonObjServerURL.getString("url_list_url");
            String domainWriter = jsonObjServerURL.getString("url_write_to_file");
            String domainBase = domainRoot + "/" + domainPathSegment;
            itemList.put("root_url", domainRoot);
            itemList.put("base_url", domainBase);
            itemList.put("url_list_url", domainBase + "/" + domainURLList);
            itemList.put("url_write_to_file", domainBase + "/" + domainWriter);
            int interstitialTime = 5 * 1000;
            JSONObject jsonObjAdMobInfo = jsonObjAppData.getJSONObject("app_admob_info");
            try {
                //JSONObject interstitialTime = jsonObjAdMobInfo.getJSONObject("interstitial_time");
                interstitialTime = Integer.valueOf(String.valueOf(jsonObjAdMobInfo.getString("interstitial_time")));
            } catch (NumberFormatException e) {
                LogWriter.Log(e.getMessage());
            } catch (Exception e) {
                LogWriter.Log(e.getMessage());
            }
            itemList.put("interstitial_time", interstitialTime);
            ModelItemList.hashMapHostURLIndexing.put("domain_base_info", new ModelDynamic(itemList));
            ModelItemList.hashMapHostURLIndexing.put("domain_url_list", new ModelDynamic(itemList));
            APPConstants.URL_READ_COMPLETE.IS_RESPONSE_READ_HOST_INDEXING = true;
            LogWriter.Log("PRINT-----------------: " + ModelItemList.hashMapHostURLIndexing.get("domain_base_info").getDynamicModelList().toString());

            /*
            serverURLSession.URL_LISTS_URL = jsonObjServerURL.getString("url_list_url");
            serverURLSession.URL_WRITE_TO_FILE = jsonObjServerURL.getString("url_write_to_file");
            ServerURLSession.SERVER_BASE = serverURLSession.SERVER_ROOT + "/" + serverURLSession.SERVER_BASE;
            ServerURLSession.URL_LISTS_URL = serverURLSession.SERVER_BASE + "/" + serverURLSession.URL_LISTS_URL;
            ServerURLSession.URL_WRITE_TO_FILE = serverURLSession.SERVER_BASE + "/" + serverURLSession.URL_WRITE_TO_FILE;
            //LogWriter.Log(">>>>>>>>>>>>>>>>>>JSON_DATA:- " + jsonObjServerURL);
            //|----|------------------------------------------------------------|
            JSONObject jsonObjAPPInfo = jsonObjAppData.getJSONObject("app_info");
            UserSession.underUpdateVersion = jsonObjAPPInfo.getString("under_app_version").toString();*/
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
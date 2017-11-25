package me.apphive.doordekhaetui;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Rz Rasel on 2017-08-30.
 */

public class ApiHTTPClientCall {
    //|――――|―――――――――――――――――――――――――――――――|
    //|――――|OkHttpClient GET NETWORK REQUEST――――――――――――|
    public static String GET(OkHttpClient argOkHttpClient, HttpUrl argHttpUrl) throws IOException {
        Request request = new Request.Builder()
                .url(argHttpUrl)
                .build();
        Response response = argOkHttpClient.newCall(request).execute();
        return response.body().string();
    }

    //|――――|―――――――――――――――――――――――――――――――|
    //|――――|OkHttpClient POST NETWORK REQUEST――――――――――――|
    //public static String POST(OkHttpClient argOkHttpClient, HttpUrl argHttpUrl, RequestBody argBody) throws IOException {
    public static String POST(OkHttpClient argOkHttpClient, String argHttpUrl, RequestBody argBody) throws IOException {
        Request request = new Request.Builder()
                .url(argHttpUrl)
                .post(argBody)
                .build();
        Response response = argOkHttpClient.newCall(request).execute();
        return response.body().string();
    }
}
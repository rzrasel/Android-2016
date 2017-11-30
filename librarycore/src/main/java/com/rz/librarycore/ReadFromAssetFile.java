package com.rz.librarycore;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by developer on 7/30/17.
 */

public class ReadFromAssetFile {
    //|----|------------------------------------------------------------|----|
    Activity activity;
    private Context context;
    private OnListenerHandler onListenerHandler;
    Handler handler = new Handler();

    //|----|------------------------------------------------------------|----|
    public ReadFromAssetFile(Activity argActivity, Context argContext) {
        activity = argActivity;
        context = argContext.getApplicationContext();
    }

    //|----|------------------------------------------------------------|----|
    public void onReadAssetTexFile(final OnListenerHandler argOnListenerHandler, final String argFilePathStr, boolean argIsUIThread) {
        if(argIsUIThread) {
            activity.runOnUiThread(new Runnable() {
                public void run() {
                    if (argOnListenerHandler != null) {
                        String strFileData;
                        strFileData = readAssetTexFile(argFilePathStr);
                        argOnListenerHandler.onReadFinished(strFileData);
                    }
                }
            });
            return;
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (argOnListenerHandler != null) {
                    String strFileData;
                    strFileData = readAssetTexFile(argFilePathStr);
                    argOnListenerHandler.onReadFinished(strFileData);
                }
            }
        }, 0);
    }

    //|----|------------------------------------------------------------|----|
    private String readAssetTexFile(String argFilePathStr) {
        //InputStream is = getAssets().open("aaa.html");
            /*AssetManager am = context.getAssets();
            InputStream is = am.open("test.txt");*/
        //AssetManager assetManager = argContext.getAssets();
        try {
            //InputStream inputStream = assetManager.open(argFilePathStr);
            InputStream inputStream = context.getAssets().open(argFilePathStr);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String fileLines = bufferedReader.readLine();
            while (fileLines != null) {
                stringBuilder.append(fileLines); // process line
                fileLines = bufferedReader.readLine();
            }
            inputStream.close();
            inputStreamReader.close();
            bufferedReader.close();
            //System.out.println("---------------------------:\n" + stringBuilder.toString() + "");
            return stringBuilder.toString() + "";
        } catch (IOException e) {
            //e.printStackTrace();
        }
        return "";
    }
    //|----|------------------------------------------------------------|----|
    public interface OnListenerHandler {
        public void onReadFinished(String argFileData);
    }
}
/*
//|----|------------------------------------------------------------|----|
//|----|------------|READ FROM ASSET FILE CLASS USAGES|----|
ReadFromAssetFile readFromAssetFile = new ReadFromAssetFile(activity, context);
readFromAssetFile.onReadAssetTexFile(new ReadFromAssetFile.OnListenerHandler() {
    @Override
    public void onReadFinished(String argFileData) {
        System.out.println("---------------------------------------->" + argFileData);
        libDialog.setBodyMessage(argFileData);
        libDialog.show();
    }
}, "testme.txt", true);
//|----|------------------------------------------------------------|----|
*/
/*
https://stackoverflow.com/questions/11603117/how-to-read-a-inputstream-with-utf-8
https://stackoverflow.com/questions/3152422/webview-load-html-from-assets-directory

AssetManager am = Resources.getSystem().getAssets();
*/
package com.rz.librarycore;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Rz Rasel on 2017-08-21.
 */

public class GetPlayStoreVersion extends AsyncTask<String, String, String> {
    private OnEventListenerHandler onEventListenerHandler = null;

    //AsyncTask<Void, String, String>
    public GetPlayStoreVersion(OnEventListenerHandler argOnEventListenerHandler) {
        onEventListenerHandler = argOnEventListenerHandler;
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected String doInBackground(String... argParams) {
        String retVal = null;
        String storeURL = argParams[0];
        StringBuilder stringBuilder = new StringBuilder();

        try {
            storeURL = "https://play.google.com/store/apps/details?id=" + storeURL + "&hl=en";
            System.out.println("PLAY_STORE_URL: " + storeURL);
            //"https://play.google.com/store/apps/details?id=com.stackexchange.marvin&hl=en"
            URL playStoreURL = new URL(storeURL);
            Reader reader = new InputStreamReader(playStoreURL.openConnection().getInputStream(), "UTF-8");
            while (true) {
                int ch = reader.read();
                if (ch < 0) {
                    break;
                }
                stringBuilder.append((char) ch);
            }
            String parts[] = stringBuilder.toString().split("softwareVersion");
            retVal = parts[1].substring(parts[1].indexOf('>') + 1, parts[1].indexOf('<')).trim();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return retVal;
    }

    @Override
    protected void onPostExecute(String argResult) {
        System.out.println("PLAY_STORE_VERSION: " + argResult);
        if (onEventListenerHandler != null) {
            onEventListenerHandler.onPostExecute(argResult);
        }
    }

    public interface OnEventListenerHandler {
        public void onPostExecute(String argResult);
    }
}
/*
new GetPlayStoreVersion(new GetPlayStoreVersion.OnEventListenerHandler() {
    @Override
    public void onPostExecute(String argResult) {
        System.out.println("------------------------------------------> " + argResult);
    }
}).execute("com.lilbardapps.sexershobkisoaz");
*/
package com.rz.librarycore.http;

import android.content.Context;
import android.os.AsyncTask;

import java.util.HashMap;

/**
 * Created by Rz Rasel on 2016-11-25.
 */

public class PowerFeedHTTPAsyncTask {
    private HTTPMethod httpMethod = HTTPMethod.GET;
    private boolean isAllowRedirects = true;
    private int readTimeout = 0;
    private int connectTimeout = 0;
    private boolean isUseCaches = false;
    private HashMap<String, String> urlHeaders = new HashMap<String, String>();
    private HashMap<String, String> urlRequestParameters = new HashMap<String, String>();
    private OnFeedHTTPEventListenerHandler onEventListenerHandler;
    private String strParentRequestURL = "";

    public PowerFeedHTTPAsyncTask() {
    }

    public PowerFeedHTTPAsyncTask(OnFeedHTTPEventListenerHandler argOnFeedHTTPEventListenerHandler) {
        this.onEventListenerHandler = argOnFeedHTTPEventListenerHandler;
    }

    public PowerFeedHTTPAsyncTask setHTTPMethod(HTTPMethod argHttpMethod) {
        this.httpMethod = argHttpMethod;
        return this;
    }

    public PowerFeedHTTPAsyncTask setUrlHeader(HashMap<String, String> argUrlHeaderList) {
        this.urlHeaders = argUrlHeaderList;
        return this;
    }

    public PowerFeedHTTPAsyncTask setURLParameters(HashMap<String, String> argPostParameter) {
        this.urlRequestParameters = argPostParameter;
        return this;
    }

    public PowerFeedHTTPAsyncTask setConnectTimeout(int argConnectTimeout) {
        this.readTimeout = argConnectTimeout;
        return this;
    }

    public PowerFeedHTTPAsyncTask setUseCaches(boolean argIsUseCaches) {
        this.isUseCaches = argIsUseCaches;
        return this;
    }

    public PowerFeedHTTPAsyncTask setIsAllowRedirects(boolean argIsAllowRedirects) {
        this.isAllowRedirects = argIsAllowRedirects;
        return this;
    }

    public void onExecute(Context argContext, String argRequestURL) {
        this.strParentRequestURL = argRequestURL;
        new PowerAsyncTask().execute(strParentRequestURL);
    }

    public class PowerAsyncTask extends AsyncTask<String, Integer, Object> {
        private PowerHTTPConnection powerHTTPConnection = null;

        public PowerAsyncTask() {
        }

        @Override
        protected void onPreExecute() {
            if (onEventListenerHandler != null) {
                onEventListenerHandler.onPreExecute();
            }
        }

        @Override
        protected Object doInBackground(String... argParams) {
            Object retVal = null;
            String strRequestURL = argParams[0].toString();
            powerHTTPConnection = new PowerHTTPConnection();
            powerHTTPConnection
                    .onPrepareConnection(strRequestURL, httpMethod, isAllowRedirects)
                    .setConnectTimeout(connectTimeout)
                    .setReadTimeout(readTimeout)
                    .setUseCaches(isUseCaches)
                    .setUrlHeader(urlHeaders)
                    .setURLParameters(urlRequestParameters);
            retVal = powerHTTPConnection.onRunConnection();
            //LogWriter.Log(retVal);
            if (onEventListenerHandler != null) {
                retVal = onEventListenerHandler.doInBackground(retVal);
            }
            return retVal;
        }

        @Override
        protected void onPostExecute(Object argResult) {
            //LogWriter.Log(argResult);
            if (onEventListenerHandler != null) {
                onEventListenerHandler.onPostExecute(argResult);
            }
        }

        @Override
        protected void onProgressUpdate(Integer... argProgressValue) {
            if (onEventListenerHandler != null) {
                onEventListenerHandler.onProgressUpdate(argProgressValue);
            }
        }

        @Override
        protected void onCancelled() {
        }
    }
}

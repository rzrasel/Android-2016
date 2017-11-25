package com.rz.librarycore.http;

import android.os.AsyncTask;

/**
 * Created by Rz Rasel on 2016-11-25.
 */

public class PowerAsyncTask<T> extends AsyncTask<T, Integer, T> {
    private OnAsyncTaskEventListenerHandler onEventListenerHandler;

    public PowerAsyncTask() {
    }

    public PowerAsyncTask(OnAsyncTaskEventListenerHandler argOnAsyncTaskEventListenerHandler) {
        this.onEventListenerHandler = argOnAsyncTaskEventListenerHandler;
    }

    @Override
    protected void onPreExecute() {
        if (onEventListenerHandler != null) {
            onEventListenerHandler.onPreExecute();
        }
    }

    @Override
    protected T doInBackground(T... argURLParams) {
        T retVal = null;
        if (onEventListenerHandler != null) {
            onEventListenerHandler.doInBackground(argURLParams);
        }
        return retVal;
    }

    @Override
    protected void onPostExecute(T argResult) {
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

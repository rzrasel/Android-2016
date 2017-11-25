package com.rz.librarycore.http;

/**
 * Created by Rz Rasel on 2016-11-25.
 */

public interface OnAsyncTaskEventListenerHandler<T> {
    public void onPreExecute();

    public T doInBackground(T... argURLParams);

    public void onPostExecute(T argResult);

    public void onProgressUpdate(Integer... argProgressValue);

    public void onCancelled();
}

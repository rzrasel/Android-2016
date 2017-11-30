package me.apphive.doordekhaetui.service;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Rz Rasel on 7/18/2016.
 */
public class MInstanceIdService extends FirebaseInstanceIdService {
    private static final String TAG = "MInstanceIdService";

    @Override
    public void onTokenRefresh() {
        //Getting registration token
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        //Displaying token on logcat
        Log.d(TAG, "REFRESHED_TOKEN: " + refreshedToken);
        registerToken(refreshedToken);
    }
    private void registerToken(String argToken)
    {
    }
}
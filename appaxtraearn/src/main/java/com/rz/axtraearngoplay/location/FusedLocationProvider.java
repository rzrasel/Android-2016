package com.rz.axtraearngoplay.location;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

/**
 * Created by Rz Rasel on 2018-01-02.
 */

public class FusedLocationProvider {
    private Activity activity;
    private Context context;
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private static final long INTERVAL = 1000 * 10;
    private static final long FASTEST_INTERVAL = 1000 * 5;
    LocationRequest locationRequest;
    GoogleApiClient googleApiClient;
    Location currentLocation;
    String lastUpdateTime;

    private void onLocationRequest() {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(INTERVAL);
        locationRequest.setFastestInterval(FASTEST_INTERVAL);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    public void onCreateLocationRequest() {
        if (!isGooglePlayServicesAvailable()) {
            System.out.println("Google play services not found");
            return;
        }
        onLocationRequest();
        googleApiClient = new GoogleApiClient.Builder(context)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(connectionCallbacks)
                //.addOnConnectionFailedListener(this)
                .build();
    }

    private boolean isGooglePlayServicesAvailable() {
        //int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int status = googleApiAvailability.isGooglePlayServicesAvailable(context);
        if (ConnectionResult.SUCCESS == status) {
            return true;
        } else {
            //GooglePlayServicesUtil.getErrorDialog(status, activity, 0).show();
            if (googleApiAvailability.isUserResolvableError(status)) {
                googleApiAvailability.getErrorDialog(activity, status, PLAY_SERVICES_RESOLUTION_REQUEST).show();
            }
            return false;
        }
    }

    GoogleApiClient.ConnectionCallbacks connectionCallbacks = new GoogleApiClient.ConnectionCallbacks() {
        @Override
        public void onConnected(@Nullable Bundle bundle) {
            startLocationUpdates();
        }

        @Override
        public void onConnectionSuspended(int i) {
            //
        }

        protected void startLocationUpdates() {
            //PendingResult<Status> pendingResult = LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, activity);
            //Log.d(TAG, "Location update started ..............: ");
            System.out.println("Location update started ..............: ");
        }
    };
}

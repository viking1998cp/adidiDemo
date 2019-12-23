package manhthang.adididemo.Manager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

/**
 * Created by Manh thang
 */

public class GoogleApiModel implements LocationListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 0;
    private static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = 0;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private final Context activity;

    public GoogleApiClient getmGoogleApiClient() {
        return mGoogleApiClient;
    }

    public LocationRequest getmLocationRequest() {
        return mLocationRequest;
    }


    public GoogleApiModel(Context activity) {
        this.activity = activity;
        try {
            mGoogleApiClient = new GoogleApiClient.Builder(activity)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API).build();
            mLocationRequest = new LocationRequest();
            mLocationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
            mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
            mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("MissingPermission")
    public Location getLastLocation() {
        try {
            if (!this.mGoogleApiClient.isConnected()) {
                connect();
            }
            return LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return null;
    }

    public boolean checkGPSProvider() {
        try {
            //activity.LOCATION_SERVICE = "location"
            LocationManager service = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
            return service.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void close() {
        try {
            if (this.mGoogleApiClient != null && this.mGoogleApiClient.isConnected()) {
                this.mGoogleApiClient.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void connect() {
        try {
            if (this.mGoogleApiClient != null && !this.mGoogleApiClient.isConnected()) {
                this.mGoogleApiClient.connect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onConnected(Bundle bundle) {
        if (this.activity instanceof GoogleApiClient.ConnectionCallbacks) {
            ((GoogleApiClient.ConnectionCallbacks) this.activity).onConnected(bundle);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        if (this.activity instanceof LocationListener) {
            if (this.activity instanceof GoogleApiClient.ConnectionCallbacks) {
                ((GoogleApiClient.ConnectionCallbacks) this.activity).onConnectionSuspended(i);
            }
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if (this.activity instanceof GoogleApiClient.OnConnectionFailedListener) {
            ((GoogleApiClient.OnConnectionFailedListener) this.activity).onConnectionFailed(connectionResult);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        if (this.activity instanceof LocationListener) {
            ((LocationListener) this.activity).onLocationChanged(location);
        }
    }
}

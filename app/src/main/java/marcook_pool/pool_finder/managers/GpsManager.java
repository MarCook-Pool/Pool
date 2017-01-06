package marcook_pool.pool_finder.managers;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

/**
 * Created by Carson on 28/11/2016.
 */

public class GpsManager extends Service implements LocationListener {

    private final String TAG = "GpsManager";

    // The minimum distance to change Updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters

    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60; // 1 minute

    //flags for able to get gps
    private boolean mIsGpsEnabled = false;
    private boolean mIsNetworkEnabled = false;

    private Location mLocation;
    private double mLatitude;
    private double mLongitude;

    private Context mContext;
    private android.location.LocationManager mLocationManager;

    public GpsManager(Context context) {
        mContext = context;
        mLocationManager = (android.location.LocationManager) mContext.getSystemService(LOCATION_SERVICE);
        mIsGpsEnabled = mLocationManager.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER);
        mIsNetworkEnabled = mLocationManager.isProviderEnabled(android.location.LocationManager.NETWORK_PROVIDER);
    }

    public Location getLocation() {
        try {
            if (canGetLocation()) {
                // First get location from Network Provider
                if (mIsNetworkEnabled) {
                    mLocationManager.requestLocationUpdates
                            (android.location.LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                    if (mLocationManager != null) {
                        mLocation = mLocationManager.getLastKnownLocation(android.location.LocationManager.NETWORK_PROVIDER);
                        if (mLocation != null) {
                            mLatitude = mLocation.getLatitude();
                            mLongitude = mLocation.getLongitude();
                        }
                    }
                }
                // if GPS Enabled get lat/long using GPS Services
                if (mIsGpsEnabled) {
                    if (mLocation == null && ContextCompat.checkSelfPermission(mContext, //checks if GPS Permission had, if not GET IN ACTIVITY
                            Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        mLocationManager.requestLocationUpdates
                                (android.location.LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                        if (mLocationManager != null) {
                            mLocation = mLocationManager.getLastKnownLocation(android.location.LocationManager.GPS_PROVIDER);
                            if (mLocation != null) {
                                mLatitude = mLocation.getLatitude();
                                mLongitude = mLocation.getLongitude();
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return mLocation;
    }

    public void promptTurnOnGps() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);

        alertDialog.setTitle("GPS is settings")
                .setMessage("GPS is not enabled. Do you want to go to settings menu?");

        // Setting Icon to Dialog
        //alertDialog.setIcon(R.drawable.delete);

        // On pressing Settings button
        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                mContext.startActivity(intent);
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).show();
    }

    public void stopUsingGPS() {
        if (mLocationManager != null && ContextCompat.checkSelfPermission(mContext, //checks if GPS Permission had
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mLocationManager.removeUpdates(GpsManager.this);
        }
    }

    public String getCoordinates() {
        getLocation();
        return "Lat: " + getLatitude() + " Long: " + getLongitude();
    }

    private String getLatitude() {
        return Location.convert(mLatitude, 1);
    }

    private String getLongitude() {
        return Location.convert(mLongitude, 1);
    }

    public boolean canGetLocation() {
        return mIsNetworkEnabled || mIsGpsEnabled;
    }

    public boolean haveGpsPermission() {
        return ContextCompat.checkSelfPermission(mContext,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    public float getDistance(float latA, float longA, float latB, float longB){
        Location locationA = new Location("point A");
        locationA.setLatitude(latA);
        locationA.setLongitude(longA);
        Location locationB = new Location("point B");
        locationB.setLatitude(latB);
        locationB.setLongitude(longB);
        return locationA.distanceTo(locationB);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}

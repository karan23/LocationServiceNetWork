package com.example.gpslocationservice;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class MyLocationService extends Service{

	private LocationManager mLocationManager;
	private double lat=0,lon=0;
	
	@Override
    public void onCreate() {
        mLocationManager = (LocationManager)getSystemService(LOCATION_SERVICE);
        
        startLocationListener();
     }
	
	
	@Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);
        // We want this service to continue running until it is explicitly
        // stopped, so return sticky.
        return START_STICKY;
    }
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	
	private LocationListener MyLocationListener = new LocationListener() {
		
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub
			mHandler.sendEmptyMessage(0);
		}
		
		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			mHandler.sendEmptyMessage(0);
		}
		
		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub
			mHandler.sendEmptyMessage(0);
		}
		
		@Override
		public void onLocationChanged(Location location) {
			// TODO Auto-generated method stub
			updateLocation(location);
		}
	};
	
	/**
     * Class for clients to access.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with
     * IPC.
     */
    public class LocalBinder extends Binder {
        MyLocationService getService() {
            return MyLocationService.this;
        }
    }
	
	public void updateLocation(Location loc) {
		
		lat = loc.getLatitude(); 
  	    lon = loc.getLongitude();
  	    
  	  mHandler.sendEmptyMessage(0);
	}
	
	
	Handler mHandler = new Handler() {
		@Override 
	    public void handleMessage(Message msg) { 
			Toast.makeText(getApplicationContext(), lat + ", " + lon, Toast.LENGTH_SHORT).show();
			Log.v("here","it is::" + lat + "," + lon);
		}
	};
		
	@Override
    public void onDestroy() {
		stopLocationListener();
		super.onDestroy();
	}
	
	public void startLocationListener() {
		
		mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 60000, 0, MyLocationListener); // Get location updates
		
	}
	
	public void stopLocationListener() {
		mLocationManager.removeUpdates(MyLocationListener);
	}
}

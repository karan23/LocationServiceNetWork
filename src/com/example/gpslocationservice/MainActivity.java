package com.example.gpslocationservice;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;


public class MainActivity extends Activity {

	private MyLocationService mBoundService;
	
	boolean mIsBound = false;
	//static Context context;
	private Intent servIntent;
    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        
      }

    	
	@Override
	protected void onStart() {
		
		servIntent = new Intent(MainActivity.this, 
	            MyLocationService.class);
		startService(servIntent);
		super.onStart();
	}
	
	@Override
	protected void onResume() {
		
		servIntent = new Intent(MainActivity.this, 
	            MyLocationService.class);
		startService(servIntent);
		super.onResume();
	}
	
	@Override
	protected void onPause() {
		
		stopService(servIntent);
		super.onPause();
	}
	
	
	
	
}

package com.example.mymapapp;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;


public class MainActivity extends Activity {
	
	private static final int GPS_ERRORDIALOG_REQUEST = 9001;
	GoogleMap mMap;
	
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
        if (servicesOK()) {
        	setContentView(R.layout.map_activity);
        	Toast.makeText(this, "Google service are available", Toast.LENGTH_SHORT).show();
        	mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        	mMap.setMyLocationEnabled(true);
        }else{
        	setContentView(R.layout.activity_main);
        }
        
    }

    public boolean servicesOK()
	{
		int isAvailables = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
		if (isAvailables == ConnectionResult.SUCCESS){
			return true;
		}else if(GooglePlayServicesUtil.isUserRecoverableError(isAvailables)){
			Dialog dialog = GooglePlayServicesUtil.getErrorDialog(isAvailables, this, GPS_ERRORDIALOG_REQUEST);
			dialog.show();
		}
		else{
			Toast.makeText(this, "Google service not available", Toast.LENGTH_SHORT).show();
		}
		return false;
	}
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()) {
		case R.id.mapTypeNone:
			mMap.setMapType(GoogleMap.MAP_TYPE_NONE);
			break;
		case R.id.mapTypeNormal:
			mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
			break;
		case R.id.mapTypeSatellite:
			mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
			break;
			
		case R.id.mapTypeTerrain:
			mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
			break;
		case R.id.mapTypeHybrid:
			mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
			break;
		

		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

   
    
}

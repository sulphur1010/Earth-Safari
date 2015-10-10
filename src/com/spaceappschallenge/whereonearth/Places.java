package com.spaceappschallenge.whereonearth;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.tomtom.lbs.sdk.TTMapView;
import com.tomtom.lbs.sdk.util.SDKContext;

public class Places extends Activity {
	
	
	private TTMapView mapView;	
	EditText log, lad;
	double logValue , latValue;
	ProgressDialog progressDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.places);
		 SDKContext.setDeveloperKey("fm7d5js3zjeuyuwtgm4xvzrt");
		 log = (EditText)findViewById(R.id.edit_log);
		 lad = (EditText)findViewById(R.id.edit_lat);
		
				
	}
	
	public void clickGetMap(View view){
		
		
		
		 logValue = Double.parseDouble(log.getText().toString());
		 latValue = Double.parseDouble(log.getText().toString());
		
		 // Map in Seattle Washington.
        mapView = new TTMapView(getApplicationContext(), latValue , logValue,10); 
	    // Fetch the Container for the Map
        LinearLayout parentView = (LinearLayout)findViewById(R.id.parentMapLayout);
        
        // And add it to the frame.
		parentView.addView(mapView);
		
		// Traffic ?
				mapView.setTraffic(true);
				mapView.setShowGPSLocation(true);
				
			// this is to move to the current location
				mapView.setFollowMe(true);
				
				
				
		/***
		 * TRY TO ADD TRAFFIC AND YOUR OWN LOCATION!
		 * 
		 
		// This is our location image
	    Bitmap gpsLocationBitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.location);
	    
	    // We create the Marker for our location. It has our own view.
	    ourLocation = new TTMarker(new BitmapDrawable(getResources(),gpsLocationBitmap),new PointDouble(0.333,1.0),null);
	    		    
	    
	    //Set it in the Map.
	    mapView.setLocationMarker( ourLocation );
	    
	    */
		
		
	}
	
	
	
	

}

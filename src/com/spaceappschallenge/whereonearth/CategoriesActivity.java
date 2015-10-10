package com.spaceappschallenge.whereonearth;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CategoriesActivity extends Activity {
	Button natural, manmade;
	Intent pageMover;
	String result, searchQuery, httpRequestUrl;
	EditText searchBox;
	Handler handler;
	ArrayList<PostObject> postObjectArray;
	PostObject postObject;
	ProgressDialog progressDialog;
	
	
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_categories);
		natural = (Button)findViewById(R.id.nutural_button);
		manmade = (Button)findViewById(R.id.manmade_button);
		searchBox  = (EditText)findViewById(R.id.searchBox);
		
		
	}

	
	public void NaturalClick(View view) {
		
		pageMover = new Intent (CategoriesActivity.this, Question.class);
		startActivity(pageMover);
	}
	
	public void ManmadeClick(View view) {
		pageMover = new Intent (CategoriesActivity.this, ManMadeCatActivity.class);
		startActivity(pageMover);
	}
	
	
	
	public void clickSearch(View view){
		
		
		 ConnectivityManager connMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
	      NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
	      if (networkInfo != null && networkInfo.isConnected())
	      		{	   	  
	    	     
			searchQuery  = searchBox.getText().toString();
			Intent intent = new Intent(CategoriesActivity.this, SearchResult.class);
			intent.putExtra("search", searchQuery);
			startActivity(intent);
			
		        						
	      		}
	      else {
			        // display error when Internet is not available
	    	  Toast.makeText(CategoriesActivity.this, "Internet is not available", Toast.LENGTH_LONG).show();
	    	  
		
	}
			
	}			
	
	
	
	public void clickPlaces(View view){
		
		
		 final LocationManager manager = (LocationManager) getSystemService( Context.LOCATION_SERVICE );

		    if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
		        buildAlertMessageNoGps();
		    }
		    else{
		    	
		    	 Intent intent = new Intent(CategoriesActivity.this, Places.class);
        		 startActivity(intent);
		    	}
		    	
		 }

		  
		
		
		

	
	private void buildAlertMessageNoGps() {
	    final AlertDialog.Builder builder = new AlertDialog.Builder(this);
	    builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
	           .setCancelable(false)
	           .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
	               public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
	                  
	            	   startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
	                   
	               }
	           })
	           .setNegativeButton("No", new DialogInterface.OnClickListener() {
	               public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
	                    dialog.cancel();
	                    
	                    
	               }
	           });
	    final AlertDialog alert = builder.create();
	    alert.show();
	}
	
	public void  clickTutorial(View view){
		Intent tutorialIntent = new Intent(CategoriesActivity.this, GridViewTutorial.class);
		startActivity(tutorialIntent);
		
	}
	
	public void clickRemote(View view){
		
		Intent remoteIntent = new Intent(CategoriesActivity.this, RemoteSensing.class);
		startActivity(remoteIntent);
		
		 
	}
			
		
	
	
}

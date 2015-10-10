package com.spaceappschallenge.whereonearth;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewEachTutotial extends Activity {
	int pictureId;
	String details;
	TextView textDetails;
	ImageView picture;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.view_each);
		
		details = getIntent().getExtras().getString("details");
		pictureId = getIntent().getExtras().getInt("picsid");
		
		textDetails = (TextView)findViewById(R.id.tutorial_details);
		picture = (ImageView)findViewById(R.id.tutorial_image);
		textDetails.setText(details);

		picture.setImageResource(pictureId);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}

package com.spaceappschallenge.whereonearth;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class Hint extends Activity {
	
	TextView details;
	String sentDetails;
	
  @Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	setContentView(R.layout.hint_activity);
	
	sentDetails = getIntent().getExtras().getString("hint");	
	  
	details = (TextView)findViewById(R.id.txtHint);
	details.setText(sentDetails);
  }
  
}

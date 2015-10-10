package com.spaceappschallenge.whereonearth;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class Result extends Activity {
	
	ArrayList<String> result;

	TextView wrong, showResult, finishTime, comment;
	int milisec, minutes, seconds, score;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.result_activity);
		result = new ArrayList<String>();
		result = getIntent().getExtras().getStringArrayList("key");
		score = getIntent().getExtras().getInt("score");
		minutes = getIntent().getExtras().getInt("minutes");
		seconds = getIntent().getExtras().getInt("seconds");
		milisec = getIntent().getExtras().getInt("milisec");
		finishTime = (TextView)findViewById(R.id.result_time_used);
		showResult = (TextView)findViewById(R.id.result_scrore);
//		comment =(TextView)findViewById(R.id.comment);
		showResult.setText("you scored "+Integer.toString(score));
		finishTime.setText(String.format("%d :%02d: %02d", minutes,seconds,milisec));
		
	}
	
	
	
	public void onClickShare(View view){
		Intent sharingIntent = new Intent(Intent.ACTION_SEND);
		sharingIntent.setType("text/plain");
		sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Sarafi Eart Application:");
		sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Just Played: Safari Earth\n Score: " + score + "\nTime Used: "+ finishTime.getText().toString() + "\nShared From Safari Earth App");
		startActivity(Intent.createChooser(sharingIntent, "Share using"));			
	}
	
	public void onClickNext(View view){
		Intent sharingIntent = new Intent(Result.this, Question.class);
		startActivity(sharingIntent);		
		finish();
	}
	
	public void onClickBack(View view){	
		finish();
	}



		
//		if there us tim eto add history to the questions
		
		
		
////		SAVE OR STORE THE DATA INSIDE THE SQLITE DATABASE
////		Beginning of storing into the database system
//		String time =  finishTime.getText().toString();
////		convert score to string
//		String scoreToString = Integer.toString(score);
//	
//	
//		DatabaseManager entry = new DatabaseManager(this);
//		entry.open();
//		entry.createRecords("Name: "+name,"Score: "+ scoreToString,"Time Used: "+ time);
//		entry.close();
//	
////	  End of the Database Class
//		
//		
	
}







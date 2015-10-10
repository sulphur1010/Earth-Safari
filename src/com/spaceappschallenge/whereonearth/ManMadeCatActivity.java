package com.spaceappschallenge.whereonearth;

import java.util.ArrayList;
import java.util.Locale;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.speech.tts.TextToSpeech;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class ManMadeCatActivity extends Activity {
	ArrayList<String> resultArray;
	 int [] imagesLand = {
			 R.drawable.logo, 
			 R.drawable.land1,
			 R.drawable.land2,
			 R.drawable.land3,
			 R.drawable.land4,
			 R.drawable.land5,
			 R.drawable.land6,
			 R.drawable.land7,
			 R.drawable.land8,
			 R.drawable.land9,
			 R.drawable.land10,
			 R.drawable.land11,
			 R.drawable.land12,
			 R.drawable.land13,
			 R.drawable.land14,
			 R.drawable.land15,
			 R.drawable.land16,
			 R.drawable.land17,
			 R.drawable.land18,
			 R.drawable.land19,
			 R.drawable.land20,
			 R.drawable.land21,
			 R.drawable.land22,
			 R.drawable.land23,
			 R.drawable.land24,
			 R.drawable.land25,
			 R.drawable.land26,
			 R.drawable.land27,
			 R.drawable.land28,
			 R.drawable.land29,
			 R.drawable.land30,
			
		};
	 
	
	 
	
	
	
	DatabaseManager database;
	Thread thread;
	Handler handler;
	long start, stop, timeResult;
	int milisec, minutes, seconds;
	TextView showTime, questionView;
	RadioButton radio1, radio2, radio3, radio4;
	RadioGroup radioGroup;
	int questionCounter = 0, score = 0;
	ImageView imageView;
	Drawable imageSource;
	Button nextButton;
	TextToSpeech speak;
	MediaPlayer click;
	String TABLE_NAME  = "land";
	
	
	
Runnable upDateTime = new Runnable() {
		
		@Override
		public void run() {
			
			showTime = (TextView)findViewById(R.id.showTime);
	    	
	    	stop = System.currentTimeMillis();
	    	timeResult = stop-start;
	        milisec = (int)timeResult;
	        seconds = milisec/1000;
	        minutes= seconds /60;
	    	milisec = milisec % 100;
	    	seconds = seconds & 60;
	    	showTime.setText(String.format("%d :%02d: %02d", minutes,seconds,milisec));
	    	if(minutes==20)
	    	{
	    	AlertDialog.Builder builder = new AlertDialog.Builder(ManMadeCatActivity.this);
			builder.setMessage("Your Stipulated Time has been Exhausted!!");
			builder.setTitle("Time Up");
			builder.setCancelable(false);
			
			builder.setPositiveButton("Okay", new DialogInterface.OnClickListener()
			{ 			
				public void onClick(DialogInterface dialog, int id) {
							
				Intent intent = new Intent(ManMadeCatActivity.this, Result.class);
				startActivity(intent);
				finish();
							}
			});
		
			builder.create().show();
			}
			
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.question_activity);
		handler = new Handler();
		database = new DatabaseManager(this);
		resultArray = new ArrayList<String>();
		database.open();
		nextButton = (Button)findViewById(R.id.btnNext);
		
		speak = new TextToSpeech(ManMadeCatActivity.this, new TextToSpeech.OnInitListener() {
			
			@Override
			public void onInit(int status) {
				 
				if(status != TextToSpeech.ERROR)
				{speak.setLanguage(Locale.ENGLISH);	}
				
			 }
		} );
		//this will call the timer to start
		start();
		pressNext(nextButton);
		
	}
	
	
	public void start(){
		
		
		thread = new Thread(){
	 		public void run() {
			try {	
				 start = System.currentTimeMillis();				
				while(true)
				{
					sleep(100);
					handler.post(upDateTime);	
				}
						
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
								
			
			};
		     	}; // end of the thread
		     	
		     	thread.start(); 
		     
	
	
	
	}
	
	

	public void setOptions(long id) {
		questionView = (TextView)findViewById(R.id.question);
		radio1 = (RadioButton)findViewById(R.id.radio1);
		radio2 = (RadioButton)findViewById(R.id.radio2);
		radio3 = (RadioButton)findViewById(R.id.radio3);
		radio4 = (RadioButton)findViewById(R.id.radio4);
		imageView = (ImageView)findViewById(R.id.nasa_image);
		
	// this section is setting the question snad the image from the database	
		questionView.setText(questionCounter+". " +database.getQuestion(id, TABLE_NAME));
		radio1.setText("A. "+database.getOptiona(id,TABLE_NAME ));
		radio2.setText("B. "+database.getOptionb(id, TABLE_NAME));
		radio3.setText("C. "+database.getOptionc(id, TABLE_NAME));
		radio4.setText("D. "+database.getOptiond(id, TABLE_NAME));	
		int num = (int)id;
		imageView.setImageResource(imagesLand[num]);
		
	 }
	
	
	public Bitmap convertArrayToBmp(byte[] array){
		Bitmap bitmap = BitmapFactory.decodeByteArray(array, 0, array.length);
		return bitmap;
		
	}
	
	public void pressNext(View view){
	
		//CODE TO SCROLL BACK TO THE TOP EACH TIME THE NEXT BUTTON IS CLICKED
	    ScrollView scrollViewLayout = (ScrollView)findViewById(R.id.scroll_view);
		scrollViewLayout.scrollTo(1,2);
		click= MediaPlayer.create(ManMadeCatActivity.this, R.raw.click);
		click.start();
		
		resetRadioGroup();
		
		{
			
			if(speak != null){
				
				speak.stop();
				speak.shutdown();
			}
			
			if(checkIfComplete()){
				
				onCompleted();
	
			}
			else{
		
				questionCounter++;
				setOptions(questionCounter);
			}
		}
		
		// check the result of the question
		checkAnswer(questionCounter);
		
		
	}
	

	
	

     public void clickHint(View view){
   	  long id = (long)(questionCounter);
   	  String details = database.getHint(id, TABLE_NAME);
   	  Intent detailsIntent = new Intent(ManMadeCatActivity.this,Hint.class );
   	  detailsIntent.putExtra("hint", details);
   	  startActivity(detailsIntent);
     }


	
	
	
	 public void clickSpeak(View view){
		 String speakingText = questionView.getText().toString() + "\n " +
				 				radio1.getText().toString() +"\n " +
				 				radio2.getText().toString() +"\n " +
				 				radio3.getText().toString() +"\n " +
				 				radio4.getText().toString() ;
		 
	 if(speakingText != null)
		 {
			speak.speak(speakingText, TextToSpeech.QUEUE_FLUSH, null);
		}
		 
		 
	 }
	 
	 
	 
	 public void resetRadioGroup(){
			radioGroup = (RadioGroup)findViewById(R.id.radioGroup1);
			radioGroup.clearCheck();
			
		}	
	 
	 
	 public void checkAnswer(long id ){
	 
	  switch (database.getCorrect(id, TABLE_NAME)) {
			case 'A':
			case 'a':
				{if (radio1.isChecked()) score++;
				  else 
					  resultArray.add(Integer.toString(questionCounter)+". "+database.getQuestion(id, TABLE_NAME)+"\ncorrect answer = "+" "+ radio1.getText());
				}                   
				break;
			case 'B':
			case 'b':
				{if (radio2.isChecked()) score++;
				else
					resultArray.add(Integer.toString(questionCounter)+". "+database.getQuestion(id, TABLE_NAME)+"\ncorrect answer = "+" "+ radio2.getText());}	
				break;
			case 'C':
			case 'c':
			    {if (radio3.isChecked())  score++;
			    else
			    	resultArray.add(Integer.toString(questionCounter)+". "+database.getQuestion(id, TABLE_NAME)+"\ncorrect answer = "+" "+ radio3.getText());}
			    break;
			case 'D':
			case 'd':
			    { if (radio4.isChecked()) score++;
			    else
			    	resultArray.add(Integer.toString(questionCounter)+". "+database.getQuestion(id, TABLE_NAME)+"\ncorrect answer = "+" "+ radio4.getText());}
				break;
				default:
				break;
			}
	  
	 }
	 
	 
	   public boolean checkIfComplete(){
		   
		   
		   if (questionCounter == 10) return true;
		   else 
			   return false;
		   
	   }
	   
	   
	   public void onCompleted(){
		   
		   nextButton.setText("Finish");
			Toast.makeText(this," Completed ", Toast.LENGTH_LONG  ).show();
			Bundle sendBundle = new Bundle();
			
			
			sendBundle.putStringArrayList("key", resultArray);
			
//			add time to the result
			
			sendBundle.putInt("score", score);
			sendBundle.putInt("minutes",  minutes);
			sendBundle.putInt("seconds", seconds);
			sendBundle.putInt("milisec", milisec);
			
			Intent intentToResult = new Intent(ManMadeCatActivity.this, Result.class);
			intentToResult.putExtras(sendBundle);
			startActivity(intentToResult);
			finish();
		   
	   }
	 
	 
	 
	 
	 
	 
	 

}

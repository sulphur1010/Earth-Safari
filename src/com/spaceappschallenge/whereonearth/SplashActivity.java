package com.spaceappschallenge.whereonearth;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Window;
import android.widget.TextView;

public class SplashActivity extends Activity {
	TextView loading;
	
	
	
	Handler handler;
	Runnable runnable = new Runnable() {
		
		@Override
		public void run() {
			loading.setVisibility(1);
			loading.setText("S");
			
		}
	};
	
Runnable runnable2 = new Runnable() {
		
		@Override
		public void run() {
			loading.setText(loading.getText().toString() + "e");
			
		}
	};
	
Runnable runnable3 = new Runnable() {
		
		@Override
		public void run() {
			loading.setText(loading.getText().toString() + "a");
			
		}
	};
	
Runnable runnable4 = new Runnable() {
		
		@Override
		public void run() {
			loading.setText(loading.getText().toString() + "r");
			
		}
	};
	
Runnable runnable5= new Runnable() {
		
		@Override
		public void run() {
			loading.setText(loading.getText().toString() + "c");
		}
	};
	
Runnable runnable6 = new Runnable() {
		
		@Override
		public void run() {
			loading.setText(loading.getText().toString() + "h");
			
		}
	};
	
Runnable runnable7 = new Runnable() {
		
		@Override
		public void run() {
			loading.setText(loading.getText().toString() + "i");
		}
	};
	
Runnable runnable8 = new Runnable() {
		
		@Override
		public void run() {
			loading.setText(loading.getText().toString() + "n");
		}
	};
	
Runnable runnable9 = new Runnable() {
		
		@Override
		public void run() {
			loading.setText(loading.getText().toString() + "g");
		}
	};
	
Runnable runnable10 = new Runnable() {
		
		@Override
		public void run() {
			loading.setText(loading.getText().toString() + " ");
		}
	};
	
Runnable runnable11= new Runnable() {
		
		@Override
		public void run() {
			loading.setText(loading.getText().toString() + "T");
		}
	};
	
Runnable runnable12 = new Runnable() {
		
		@Override
		public void run() {
			loading.setText(loading.getText().toString() + "h");
			
		}
	};
	
Runnable runnable13 = new Runnable() {
		
		@Override
		public void run() {
			loading.setText(loading.getText().toString() + "e");
		}
	};
	
Runnable runnable14 = new Runnable() {
		
		@Override
		public void run() {
			loading.setText(loading.getText().toString() + " ");
		}
	};
	
Runnable runnable15 = new Runnable() {
		
		@Override
		public void run() {
			loading.setText(loading.getText().toString() + "E");
		}
	};
	
Runnable runnable16 = new Runnable() {
		
		@Override
		public void run() {
			loading.setText(loading.getText().toString() + "a");
		}
	};
	
Runnable runnable17 = new Runnable() {
		
		@Override
		public void run() {
			loading.setText(loading.getText().toString() + "r");
		}
	};
	
Runnable runnable18 = new Runnable() {
		
		@Override
		public void run() {
			loading.setText(loading.getText().toString() + "t");
		}
	};
	
Runnable runnable19 = new Runnable() {
		
		@Override
		public void run() {
			loading.setText(loading.getText().toString() + "h");
		}
	};


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_splash);
		handler = new Handler();
		loading =  (TextView)findViewById(R.id.loading);
		
		Thread timer = new Thread() {
			public void run() {
				try{
					sleep(200);
					handler.post(runnable);
					sleep(200);
					handler.post(runnable2);
					sleep(200);
					handler.post(runnable3);
					sleep(200);	
					handler.post(runnable4);
					sleep(200);	
					handler.post(runnable5);
					sleep(200);	
					handler.post(runnable6);
					sleep(200);	
					handler.post(runnable7);
					sleep(200);
					handler.post(runnable8);
					sleep(200);	
					handler.post(runnable9);
					sleep(200);	
					handler.post(runnable10);
					sleep(200);	
					handler.post(runnable11);
					sleep(200);	
					handler.post(runnable12);
					sleep(200);
					handler.post(runnable13);
					sleep(200);	
					handler.post(runnable14);
					sleep(200);	
					handler.post(runnable15);
					sleep(200);	
					handler.post(runnable16);
					sleep(200);	
					handler.post(runnable17);
					sleep(200);
					handler.post(runnable18);
					sleep(200);	
					handler.post(runnable19);
					
					}
		 catch (InterruptedException e) {
			 e.printStackTrace();
			
		} finally {
			Intent mainA = new Intent(SplashActivity.this, CategoriesActivity.class);
			startActivity(mainA);
			finish();
			
		}
	}
		};
	timer.start();
	}

}

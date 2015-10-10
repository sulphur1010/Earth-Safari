package com.spaceappschallenge.whereonearth;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class SearchResult extends Activity {
	
	String result, searchQuery, httpRequestUrl;
	Handler handler;
	ArrayList<PostObject> postObjectArray;
	PostObject postObject;
	ProgressDialog progressDialog;
	ListView resultList;
	
Runnable runnable = new Runnable() {
		
		@Override
		public void run() {
			
			
			resultList.setAdapter(new PostAdapter(SearchResult.this, postObjectArray) );
			 if ( progressDialog.isShowing()) progressDialog.dismiss();
    		
    		
			
		}
	};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.search_result);
		handler = new Handler();
		searchQuery = getIntent().getExtras().getString("search");
		 postObjectArray =  new ArrayList<PostObject>();
		 progressDialog = new ProgressDialog(this);
		 progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		 progressDialog.setMessage("Searching..");
		 progressDialog.setCancelable(true);
		 resultList = (ListView)findViewById(R.id.searchlist);
		 // this will call the search funtion
		 searchFuntion();
	}
	
	
	public void searchFuntion(){
		
		 progressDialog.show();
			
			
			httpRequestUrl  = "http://data.nasa.gov/api/get_search_results/?search=" + searchQuery;
		
			Thread thread = new Thread(){
					
					@Override
					public void run() {
						            	  
							HttpRequestClass request = new HttpRequestClass(SearchResult.this, httpRequestUrl);
							result = request.getResult();
							
							
							JSONObject generalJsonObject;
							JSONArray postJsonArray;
			       			
							try {
									
									generalJsonObject = new JSONObject(result);
									postJsonArray  =  new JSONArray();
									postJsonArray =  generalJsonObject.getJSONArray("posts");
									
									
									for( int i = 0 ; i < postJsonArray.length() ; i++ )
			   						{	
			   						// create a instant of JSONObject to get each object details from jSONArray
			   						JSONObject postJsonObject  = postJsonArray.getJSONObject(i);
			   						//Assign the details of each JSON Object to an object with the same attribute
			   						PostObject object  = new PostObject();
			   						object.setSlug(postJsonObject.getString("slug")) ;
			   						object.setTitle(postJsonObject.getString("title_plain")) ;
			   						object.setUrl(postJsonObject.getString("url")) ;
			   						object.setContent(postJsonObject.getString("content")) ;
			   						object.setExcerpt(postJsonObject.getString("excerpt")) ;
			   						object.setDate(postJsonObject.getString("date"));
			   						object.setModified(postJsonObject.getString("modified"));
			   						
			   						//add the object to an arrayList of the type object;
			   						postObjectArray.add(object);
			   						}
								} 
							 
							 catch (JSONException e) {handler.post(runnable);}
							
							
							
							
							
							
			       			handler.post(runnable);
					 }
							
				};
		
				thread.start();
		
		
	}

}

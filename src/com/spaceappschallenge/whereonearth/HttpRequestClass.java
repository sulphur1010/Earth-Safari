package com.spaceappschallenge.whereonearth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.widget.Toast;

public class HttpRequestClass {
	
	String result;
	BufferedReader reader;
	HttpResponse response;
	HttpGet httpGet;
	HttpClient client;
	String line = null;
	Context context;
	String url;
	
	
	public HttpRequestClass(Context context, String url){
		this.context = context;
		this.url = url;
	}
	
	
	
	public String getResult(){
		
	 client = new DefaultHttpClient();
	 httpGet = new HttpGet(url);
		
	
	try {
		
			response = client.execute(httpGet);
			reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer stringBuffer = new StringBuffer("");
		
			while((line = reader.readLine()) != null)
			{ stringBuffer.append(line);}
			
			reader.close();
			result = stringBuffer.toString();
			
		
		
		} catch (ClientProtocolException e) {
			
			Toast.makeText(this.context, "Unable To Connect To Nasa ", Toast.LENGTH_LONG).show();
		} 
		  catch (IOException e) { Toast.makeText(this.context, "Unable To Connect To Nasa", Toast.LENGTH_LONG).show();}
	  return result;
		
		}
		

}

package com.spaceappschallenge.whereonearth;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class PostAdapter  extends BaseAdapter {
	Context context;
	ArrayList<PostObject> postObjectArray;
	LayoutInflater layoutInflter;
	Holder holder;
	
	
	
	public  PostAdapter(Context context, ArrayList<PostObject> postObjectArrayPassed){
	this.context = context;
	
	this.postObjectArray = postObjectArrayPassed;
	
		
	}

	@Override
	public int getCount() {
		return this.postObjectArray.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		if(convertView == null){
			convertView = LayoutInflater.from(context).inflate(R.layout.single_layout, parent, false);
			
			holder = new Holder();
			
			holder.content = (TextView) convertView.findViewById(R.id.search_er);
			holder.slug = (TextView) convertView.findViewById(R.id.slug);
			holder.date = (TextView) convertView.findViewById(R.id.search_date);
			holder.modified = (TextView) convertView.findViewById(R.id.search_modified);
			holder.title = (TextView) convertView.findViewById(R.id.search_content);
			holder.linkButton = (Button)convertView.findViewById(R.id.search_link_button);
			 convertView.setTag(holder);		
		    }
		else {
			
			holder = (Holder)convertView.getTag();
		    }
		
		
		PostObject oneObject = postObjectArray.get(position);
		// the whole SharedOblect class object was assign so that the whole object can be passed to another view with intent
		
		holder.content.setText(oneObject.getExcerpt());
		holder.slug.setText(oneObject.getSlug());
		holder.date.setText(oneObject.getDate());
		holder.modified.setText(oneObject.getModified());
		holder.title.setText(oneObject.getTitle());
		holder.url = oneObject.getUrl();
		
		holder.linkButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(holder.url));
				context.startActivity(browserIntent);
				
			}
		});
		
		return convertView;
	}
	
	
	
	
	
	
	// this class will hold all the  component in the list view
	class Holder
	{
		 
		TextView content, title, date, modified,slug;
		Button linkButton;
		String url;
   }






	
		
	}

	
	



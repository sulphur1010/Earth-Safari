package com.spaceappschallenge.whereonearth;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;



public class GridTutorialAdapter extends BaseAdapter implements OnClickListener {

	Context context;
	ArrayList<TutorialObject> tutorialObjectArray;
	Holder holder;
	
	public GridTutorialAdapter(Context contextPassed,  ArrayList<TutorialObject> passedObjectArray){
		
		this.context = contextPassed;
		this.tutorialObjectArray = passedObjectArray;
	}
	@Override
	public int getCount() {
		
		return tutorialObjectArray.size();
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
			convertView = LayoutInflater.from(context).inflate(R.layout.single_grid, parent, false);
			
			holder = new Holder();
			holder.details = (TextView) convertView.findViewById(R.id.grid_details);
			holder.image_tutorial = (ImageView)convertView.findViewById(R.id.grid_image);
			
			 convertView.setTag(holder);		
		    }
		else {
			
			holder = (Holder)convertView.getTag();
		    }
		
		convertView.setOnClickListener(this);
		
		TutorialObject oneObject = tutorialObjectArray.get(position);
		holder.object = oneObject;
		holder.details.setText(oneObject.getDetails());
		holder.image_tutorial.setImageResource(oneObject.getPictureId());
		return convertView;
	}
	
	
	
	
	
	
	// this class will hold all the  component in the list view
	class Holder
	{
		 
		TextView details;
		ImageView image_tutorial;
		TutorialObject object;
		
   }






	@Override
	public void onClick(View v) {
		
		Holder holder = (Holder)v.getTag();
		Intent intent = new Intent(context, ViewEachTutotial.class);
		intent.putExtra("details", holder.object.getDetails());
		intent.putExtra("picsid",holder.object.getPictureId());
		
		context.startActivity(intent);
		
	}

}




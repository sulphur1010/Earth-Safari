package com.spaceappschallenge.whereonearth;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.GridView;

public class GridViewTutorial extends Activity {
	ArrayList<TutorialObject>  pictureGrid;
	DatabaseManager database;
	GridView gridViewTutorial;
	String TABLE_NAME = "natural_hazard";
	
	
	 int [] images = {
			 R.drawable.logo, 
			 R.drawable.image1,
			 R.drawable.image2,
			 R.drawable.image3,
			 R.drawable.image4,
			 R.drawable.image5,
			 R.drawable.image6,
			 R.drawable.image7,
			 R.drawable.image8,
			 R.drawable.image9,
			 R.drawable.image10,
			 R.drawable.image11,
			 R.drawable.image12,
			 R.drawable.image13,
			 R.drawable.image14,
			 R.drawable.image15,
			 R.drawable.image16,
			 R.drawable.image17,
			 R.drawable.image18,
			 R.drawable.image19,
			 R.drawable.image20,
			 R.drawable.image21,
			 R.drawable.image22,
			 R.drawable.image23,
			 R.drawable.image24,
			 R.drawable.image25,
			 R.drawable.image26,
			 R.drawable.image27,
			 R.drawable.image28,
			 R.drawable.image29,
			 R.drawable.image30,
			
		};
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.grid_tutorial);
		pictureGrid = new ArrayList<TutorialObject>();
		database = new DatabaseManager(this);
		database.open();
		gridViewTutorial = (GridView)findViewById(R.id.gridView_tutorial);
		
		for(int i = 1 ; i <=30 ;i++){
			TutorialObject tutorialObject = new TutorialObject();
			tutorialObject.setDetials(database.getHint((long)i, TABLE_NAME));
			tutorialObject.setPictureId(images[i]);
			pictureGrid.add(tutorialObject);
		}
		
		gridViewTutorial.setAdapter(new GridTutorialAdapter(this, pictureGrid));
		
		
		
	}
	
	

}

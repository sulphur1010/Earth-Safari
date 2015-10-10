package com.spaceappschallenge.whereonearth;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;



public class DatabaseManager {
	
//	DECLATION OF ALL THE VARIABLES AND CONSTANT THAT WILL BE USED TO CREATE THE TABLE
	
	private static final String DATABASE_NAME = "orion"; 
	private static final String DATABASE_TABLE_NATURAL = "natural_hazard"; // enter table name over her
	private static final String DATABASE_TABLE_LAND = "land";
	private static final String DATABASE_TABLE_ = "remote_sensing";
	private static final int DATABASE_VERSION = 1;
	
	
//	DECLARATION OF ALL THE COLUMN REQUIRED TO BE CREATED
	
	public static final String KEY_ROWID = "id";
	public static final String KEY_QUESION = "question";
	public static final String KEY_OPTIONA = "option_a";
	public static final String KEY_OPTIONB = "option_b";
	public static final String KEY_OPTIONC = "option_c";
	public static final String KEY_OPTIOND = "option_d";
	public static final String KEY_CORRECT = "correct_option";
	public static final String KEY_HINT = "hints";
	public static final String KEY_IMAGE = "image";
	
				
	
	private DatabaseHelper mDbHelper; 
	private SQLiteDatabase ourDatabase;
	private final Context ourContext;
	
//	THIS IS THE ACTUAL CLASS USED TO CREATE THE DATABASE AND TABLE, IT IS NESTED IN THIS CLASS
	
//		Beginning if this class
	
	
            public class DatabaseHelper extends SQLiteAssetHelper {	    
	         public DatabaseHelper(Context context) {
	        super(context, DATABASE_NAME, null, DATABASE_VERSION);  
	       }
	

	}
	
//	End of this class
	
	//	Constructor of this external class
	
	
	public DatabaseManager(Context context){
		ourContext = context;		
		
	}
	
//	constructor terminated
	
//	open the database for access
	
	public  DatabaseManager open() throws SQLException {
		mDbHelper = new DatabaseHelper(ourContext);
		ourDatabase = mDbHelper.getWritableDatabase();
		return this;
		}
	
	
//	close the database after creating the values for security purposes
	
	public void close() {
		mDbHelper.close();
		}
	
//	Return all data via the cursor to the calling function
	
	public Cursor getAllData(String TABLE_NAME) throws SQLException{
		
		String[] columns = {KEY_ROWID, KEY_QUESION, KEY_OPTIONA, KEY_OPTIONB, KEY_OPTIONC, KEY_OPTIOND, KEY_CORRECT, KEY_HINT, KEY_IMAGE};
		Cursor c = ourDatabase.query(TABLE_NAME, columns, null, null, null, null, null);		
		return c ;
		}
		

			

	
	 public Cursor fetchSingleQuestion(long rowId, String TABLE_NAME) throws SQLException
	 {	String[] columns = {KEY_ROWID, KEY_QUESION, KEY_OPTIONA, KEY_OPTIONB, KEY_OPTIONC, KEY_OPTIOND, KEY_CORRECT, KEY_HINT, KEY_IMAGE};
	 	Cursor c =  ourDatabase.query(true, TABLE_NAME, columns , KEY_ROWID + "=" +  rowId, null, null, null, null, null);
	 	if (c != null) { c.moveToFirst(); }
	 	return c;
	 }
	 
	 
	//This session of code msy or may not be written here. i mean in this side of the database 
	 // this will get the string result of author
	public String getQuestion(Long rowId, String TABLE_NAME) {
		Cursor c2 = fetchSingleQuestion(rowId,TABLE_NAME);
		int rowIndex = c2.getColumnIndex(KEY_QUESION);
		return c2.getString(rowIndex);
	}

	public String getOptiona(Long rowId , String TABLE_NAME) {
		Cursor c2 = fetchSingleQuestion(rowId, TABLE_NAME);
		int rowIndex = c2.getColumnIndex(KEY_OPTIONA);
		return c2.getString(rowIndex);
	}

	public String getOptionb(Long rowId, String TABLE_NAME) {
		Cursor c2 = fetchSingleQuestion(rowId, TABLE_NAME);
		int rowIndex = c2.getColumnIndex(KEY_OPTIONB);
		return c2.getString(rowIndex);
	}

	public String getOptionc(Long rowId ,String TABLE_NAME) {
		Cursor c2 = fetchSingleQuestion(rowId, TABLE_NAME);
		int rowIndex = c2.getColumnIndex(KEY_OPTIONC);
		return c2.getString(rowIndex);
	}
	
	public String getOptiond(Long rowId, String TABLE_NAME) {
		Cursor c2 = fetchSingleQuestion(rowId, TABLE_NAME);
		int rowIndex = c2.getColumnIndex(KEY_OPTIOND);
		return c2.getString(rowIndex);
	}
	
	public char getCorrect(Long rowId, String TABLE_NAME) {
		Cursor c2 = fetchSingleQuestion(rowId, TABLE_NAME);
		int rowIndex = c2.getColumnIndex(KEY_CORRECT);
	   	return  c2.getString(rowIndex).toCharArray()[0];
	}
	
	public String getHint(Long rowId, String TABLE_NAME) {
		Cursor c2 = fetchSingleQuestion(rowId, TABLE_NAME);
		int rowIndex = c2.getColumnIndex(KEY_HINT);
		return c2.getString(rowIndex);
	}
	
	public byte[] getImage(Long rowId, String TABLE_NAME) {
		Cursor c2 = fetchSingleQuestion(rowId, TABLE_NAME);
		int rowIndex = c2.getColumnIndex(KEY_IMAGE);
		return  c2.getBlob(rowIndex);
		
	}
		
	

}    



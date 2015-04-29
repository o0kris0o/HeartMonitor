package cs.ecl500.heartmonitor;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;




import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Editable;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.Toast;

public class DataBaseControl {
	// All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "UserInfo.db";
 
    // Database Tables
    private static final String TABLE_USERS = "users";
    private static final String TABLE_BP = "bloodpressure";
    private static final String TABLE_WEIGHT = "weight";
    private static final String TABLE_EXERCISE = "exercise";
 
    // Database Table Column names
    private static final String KEY_ID = "dataId";
    private static final String KEY_SYSTOLIC = "systolic";
    private static final String KEY_DIASTOLIC = "diastolic";
    private static final String KEY_HEARTRATE = "heartrate";
    private static final String KEY_DATESTORED = "datestored";
    private static final String KEY_NAME = "name";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_ENAME = "ename";
    private static final String KEY_EADDRESS = "eaddress";
    private static final String KEY_EPHONE = "ephone";
    private static final String KEY_WEIGHT = "weight";
    private static final String KEY_TYPE = "type";
    private static final String KEY_DURATION = "duration";
    private static final String KEY_INTENSITY = "intensity";
    //Helper variables
    private DbHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;
	
	private class DbHelper extends SQLiteOpenHelper{
	 
		public DbHelper(Context context) {
		 super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}
	 
		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			String CREATE_BP_TABLE = "CREATE TABLE " + TABLE_BP + "("
	                + KEY_ID + " INTEGER PRIMARY KEY,"
	                + KEY_NAME + " TEXT,"
	        		+ KEY_SYSTOLIC + " INTEGER,"
	                + KEY_DIASTOLIC + " INTEGER," 
	        		+ KEY_HEARTRATE + " INTEGER,"  
	                + KEY_DATESTORED + " TEXT" + ")";
			String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USERS + "("
	                + KEY_ID + " INT PRIMARY KEY,"
	                + KEY_NAME + " TEXT,"
	        		+ KEY_ADDRESS + " TEXT,"
	                + KEY_PHONE + " TEXT," 
	        		+ KEY_ENAME + " TEXT,"  
	                + KEY_EADDRESS + " TEXT,"
	                + KEY_EPHONE + " TEXT" + ")";
			String CREATE_WEIGHT_TABLE = "CREATE TABLE " + TABLE_WEIGHT + "("
	                + KEY_ID + " INT PRIMARY KEY,"
	                + KEY_NAME + " TEXT,"
	        		+ KEY_WEIGHT + " INTEGER,"
	                + KEY_DATESTORED + " TEXT" + ")";
			String CREATE_EXERCISE_TABLE = "CREATE TABLE " + TABLE_EXERCISE + "("
	                + KEY_ID + " INT PRIMARY KEY,"
	                + KEY_NAME + " TEXT,"
	        		+ KEY_DATESTORED + " TEXT,"
	                + KEY_TYPE + " TEXT," 
	        		+ KEY_DURATION + " INTEGER,"  
	                + KEY_INTENSITY + " TEXT"+ ")";
	        db.execSQL(CREATE_BP_TABLE);
	        db.execSQL(CREATE_USER_TABLE);
	        db.execSQL(CREATE_WEIGHT_TABLE);
	        db.execSQL(CREATE_EXERCISE_TABLE);
		}
	
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_BP);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_WEIGHT);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXERCISE);
			onCreate(db);
		}

	}
	
	public DataBaseControl(){
		ourContext = null;
	}
	
	public DataBaseControl(Context x) {
		ourContext = x;
		Log.v("ERROR0", "index=DBContorl constructor");
	}
	
	public void close() {
		ourHelper.close();
		Log.v("ERROR0", "index=DBCONTROL close");
	}

	public DataBaseControl Open() {
		ourHelper = new DbHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		Log.v("ERROR0", "index=DBCONTROL open");
		return this;
	}


	public void registerNewUser(String name, String add, String number,
			String ename, String eadd, String enumber) {
		// TODO Auto-generated method stub
		//ourDatabase.delete(TABLE_USERS, null, null);
		ContentValues cv = new ContentValues();
		cv.put(KEY_NAME, name);
		cv.put(KEY_ADDRESS, add);
		cv.put(KEY_PHONE, number);
		cv.put(KEY_ENAME, ename);
		cv.put(KEY_EADDRESS, eadd);
		cv.put(KEY_EPHONE, enumber);
		ourDatabase.insert(TABLE_USERS, null, cv);
	}

	public boolean findUser(String string) {
		// TODO Auto-generated method stub
		boolean flag = false;
		String[] columns = new String[]{"name"};
		String[] temp = new String[]{string};
		Cursor c = ourDatabase.query(TABLE_USERS, columns, "name=?", temp, null, null, null);
		
		 if (c != null){
	            c.moveToFirst();
		 }
		 
		 int numberOfRows = c.getCount();
		 if(numberOfRows > 0){
			 flag = true;
			 
		 }
		return flag;
	}

	public void saveBPData(String name, int v1, int v2, int v3, Date dt) {
		// TODO Auto-generated method stub
		ContentValues values = new ContentValues();
		values.put(KEY_NAME, name);
        values.put(KEY_SYSTOLIC, v1); // Systolic value
        values.put(KEY_DIASTOLIC, v2); // Diastolic value
        values.put(KEY_HEARTRATE, v3); // Heart rate value
        values.put(KEY_DATESTORED, dt.toString()); // Date value
 
        // Inserting Row
        ourDatabase.insert(TABLE_BP, null, values);
	}

	public void saveWeightInfo(String name, int w, Date dt) {
		// TODO Auto-generated method stub
		ContentValues values = new ContentValues();
		values.put(KEY_NAME, name);
        values.put(KEY_WEIGHT, w); 
        values.put(KEY_DATESTORED, dt.toString()); // Date value
 
        // Inserting Row
        ourDatabase.insert(TABLE_WEIGHT, null, values);
	}

	public void saveExerciseDate(String name, Date dt, String string, int v1,
			String string2) {
		// TODO Auto-generated method stub
		ContentValues values = new ContentValues();
		values.put(KEY_NAME, name);
        values.put(KEY_DATESTORED, dt.toString()); // Date value
        values.put(KEY_TYPE, string); 
        values.put(KEY_DURATION, v1); 
        values.put(KEY_INTENSITY, string2); 
 
        // Inserting Row
        ourDatabase.insert(TABLE_EXERCISE, null, values);
		
	}

	public Integer[] getSP(String name) {
		// TODO Auto-generated method stub
		ourDatabase = ourHelper.getReadableDatabase();
		Integer[] temp = null;
		int i;
		 Cursor cursor = ourDatabase.query(TABLE_BP, new String[] {
	                KEY_SYSTOLIC }, KEY_NAME + "=?",
	                new String[] { name }, null, null, null, null);
		 if (cursor.moveToFirst()) {
			 temp = new Integer[cursor.getCount()];
			 cursor.getString(0);
			 i = 0;
			 //Log.v("DB", "CCount =" + i);
	            do {
	            	//Log.v("DB", "Value" + cursor.getString(0));
	            	temp[i] = Integer.parseInt(cursor.getString(0));
	            	//Log.v("DB", "Item=" + temp[i]);
	            	i++;
	            } while (cursor.moveToNext());
	     }else{Log.v("DB", "BAD QUERY!");}
	        
		return temp;
	}

	public Integer[] getDP(String name) {
		ourDatabase = ourHelper.getReadableDatabase();
		Integer[] temp = null;
		int i;
		 Cursor cursor = ourDatabase.query(TABLE_BP, new String[] {
				 KEY_DIASTOLIC }, KEY_NAME + "=?",
	                new String[] { name }, null, null, null, null);
		 if (cursor.moveToFirst()) {
			 temp = new Integer[cursor.getCount()];
			 cursor.getString(0);
			 i = 0;
			 //Log.v("DB", "CCount =" + i);
	            do {
	            	//Log.v("DB", "Value" + cursor.getString(0));
	            	temp[i] = Integer.parseInt(cursor.getString(0));
	            	//Log.v("DB", "Item=" + temp[i]);
	            	i++;
	            } while (cursor.moveToNext());
	     }else{Log.v("DB", "BAD QUERY!");}
	        
		return temp;
	}

	public Integer[] getHR(String name) {
		ourDatabase = ourHelper.getReadableDatabase();
		Integer[] temp = null;
		int i;
		 Cursor cursor = ourDatabase.query(TABLE_BP, new String[] {
				 KEY_HEARTRATE }, KEY_NAME + "=?",
	                new String[] { name }, null, null, null, null);
		 if (cursor.moveToFirst()) {
			 temp = new Integer[cursor.getCount()];
			 cursor.getString(0);
			 i = 0;
			// Log.v("DB", "CCount =" + i);
	            do {
	            	//Log.v("DB", "Value" + cursor.getString(0));
	            	temp[i] = Integer.parseInt(cursor.getString(0));
	            	//Log.v("DB", "Item=" + temp[i]);
	            	i++;
	            } while (cursor.moveToNext());
	     }else{Log.v("DB", "BAD QUERY!");}
	        
		return temp;
	}

	public Integer[] getWeight(String name) {
		ourDatabase = ourHelper.getReadableDatabase();
		Integer[] temp = null;
		int i;
		 Cursor cursor = ourDatabase.query(TABLE_WEIGHT, new String[] {
				 KEY_WEIGHT }, KEY_NAME + "=?",
	                new String[] { name }, null, null, null, null);
		 if (cursor.moveToFirst()) {
			 temp = new Integer[cursor.getCount()];
			 cursor.getString(0);
			 i = 0;
			// Log.v("DB", "CCount =" + i);
	            do {
	            	//Log.v("DB", "Value" + cursor.getString(0));
	            	temp[i] = Integer.parseInt(cursor.getString(0));
	            	//Log.v("DB", "Item=" + temp[i]);
	            	i++;
	            } while (cursor.moveToNext());
	     }else{Log.v("DB", "BAD QUERY!");}
	        
		return temp;
	}

	public String[] getDates(String name) {
		ourDatabase = ourHelper.getReadableDatabase();
		Log.v("DB", "Before getCount()");
		String[] temp = null;
		int i;
		 Cursor cursor = ourDatabase.query(TABLE_BP, new String[] {
				 KEY_DATESTORED }, KEY_NAME + "=?",
	                new String[] { name }, null, null, null, null);
		 if (cursor.moveToFirst()) {
			 Log.v("DB", "Before getCount()");
			 temp = new String[cursor.getCount()];
			// cursor.getString(0);
			 i = 0;
			// Log.v("DB", "CCount =" + i);
	            do {
	            	//Log.v("DB", "Value" + cursor.getString(0));
	            	temp[i] = cursor.getString(0);
	            	//Log.v("DB", "Item=" + temp[i]);
	            	i++;
	            } while (cursor.moveToNext());
	     }else{Log.v("DB", "BAD QUERY!");}
	        
		return temp;
	}

	public void dropT() {
		// TODO Auto-generated method stub
	
	}

	


	
}

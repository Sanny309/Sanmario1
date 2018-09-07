package com.example.sanny.sanmario;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class DatabaseHelper extends SQLiteOpenHelper  {


    private static final String TAG="DatabaseHelper";
    private static final String TABLE_NAME="gps";
    private  static final String COL1="ID";
    final private static String COL2="USER_ID";
    private static final String COL3="latitude";
    private static final String COL4 ="longitude";
    private static String COL5="isupdate";
    DatabaseHelper databaseHelper;



    public DatabaseHelper(Context context) {
        super(context,TABLE_NAME,null,6);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

//        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +COL2+",INTEGER"+ COL3+",INTEGER"+
//                COL4+",INTEGER"+
//                COL5+"ISUPDATE)";
        String createTable = "create table gps(id integer primary key autoincrement, user_id integer,latitude varchar(255),longitude varchar(255),isupdated integer)";

        db.execSQL(createTable);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP  TABLE IF  EXISTS "+TABLE_NAME);
        onCreate(db);


    }
    public boolean addData(String item ,String item1,String item2) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, item);
        contentValues.put(COL3,item1);
        contentValues.put(COL4,item2);

        Log.d(TAG, "addData: Adding " + item + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /**
     * Delete from database
     * @param userid
     * @param latitude
     * @param longitude
     * @param isupdate
     */
    public void deleteName(int userid, String latitude,String longitude , String isupdate){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL1 + " = '" + userid + "'" +
                " AND " + COL2 + " = '" + latitude + "'"+
                " AND " + COL3 + " = '" + longitude + "'"+
                 "AND" + COL4 +  " = '" + isupdate + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + latitude + " from database.");
        db.execSQL(query);
    }
    /**
     * Returns only the ID that matches the name passed in

     * @param isupdate
     * @return
     */
    public Cursor getItemID( String isupdate){

        SQLiteDatabase db=getWritableDatabase();
        String query = "SELECT  FROM"+TABLE_NAME  +"WHERE "+COL5+"="+isupdate+"";

        Cursor  cursor = db.rawQuery(query,null);

        return cursor;
    }

    public boolean updateData(String isupdated , String USER_ID , String latitude , String longitude) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2,USER_ID);
        contentValues.put(COL5,isupdated);
        contentValues.put(COL4,latitude);
        contentValues.put(COL4,longitude);
       db.update(TABLE_NAME,contentValues,"USER_ID=?",new String[]{USER_ID});
       return true;

    }

    public Integer deleteData (String USER_ID) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "isupdate = 1",new String[] {USER_ID});
    }


}

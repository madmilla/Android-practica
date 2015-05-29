package com.example.madmilla.week3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by madmilla on 28-5-15.
 */
public class DBHandler extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "verjaardag.db";
    private static final String TABLE_BDAY = "verjaardagen";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DATE = "date";

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " +
                TABLE_BDAY + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_NAME
                + " TEXT," + COLUMN_DATE + " TEXT" + ")";
        db.execSQL(CREATE_PRODUCTS_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BDAY);
        onCreate(db);
    }

    public void addVerjaardag(Verjaardag verjaardag){

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, verjaardag.getName());
        values.put(COLUMN_DATE, verjaardag.getDate());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_BDAY, null, values);
        db.close();
    }

    public Verjaardag findName(String name){
        String query = "Select * FROM " + TABLE_BDAY + " WHERE " + COLUMN_NAME + " \""  + name + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Verjaardag verjaardag = new Verjaardag();

        if(cursor.moveToFirst()){
            cursor.moveToFirst();
            verjaardag.setID(Integer.parseInt(cursor.getString(0)));
            verjaardag.setName(cursor.getString(1));
            verjaardag.setDate(cursor.getString(2));
        }else{
            verjaardag = null;
        }
        db.close();
        return verjaardag;


    }


    public boolean deleteVerjaardag(String name){
        boolean  result =false;
        String query = "Select * FROM " + TABLE_BDAY + " WHERE " + COLUMN_NAME + " = \"" + name + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor  = db.rawQuery(query, null);
        Verjaardag verjaardag = new Verjaardag();

        if(cursor.moveToFirst()){
            verjaardag.setID(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_BDAY, COLUMN_ID + " =? ", new String[]{String.valueOf(verjaardag.getID())});
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }
}

package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context,"Userdata.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create Table UserInfo(name TEXT primary key, " +
                "phone TEXT, date_of_birth TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop Table if exists UserInfo");
    }
    public Boolean insert(String name, String phone, String date_of_birth){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("phone",phone);
        contentValues.put("date_of_birth",date_of_birth);
        long result = DB.insert("UserInfo",null,contentValues);
        return  result != -1;
    }

    public Boolean update(String name, String phone, String date_of_birth){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("phone",phone);
        contentValues.put("date_of_birth",date_of_birth);
        long result = DB.update("UserInfo",contentValues,"phone = ?", new String[] {phone});
        return  result != -1;
    }

    public Boolean delete( String name, String phone, String date_of_birth){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("phone",phone);
        contentValues.put("date_of_birth",date_of_birth);
        // long result = DB.delete("UserInfo","phone = ?" ,null);
        long result = DB.delete("UserInfo","phone = ?" ,null);
        DB.execSQL("DELETE FROM " + "UserInfo"+ " WHERE "+"phone"+"='"+phone+"'");
        return  result != -1;
    }

    public Cursor getData(){
        SQLiteDatabase DB = this.getWritableDatabase();
        return DB.rawQuery("Select * from UserInfo",null);
    }
}

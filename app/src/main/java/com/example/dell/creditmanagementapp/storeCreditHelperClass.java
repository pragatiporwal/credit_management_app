package com.example.dell.creditmanagementapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class storeCreditHelperClass extends SQLiteOpenHelper {
    public static final String database_name="creditManagement1.db";
    public static final String table_name="store_credit";
    public static final String col1="to_name";
    public static final String col2="from_name";
    public static final String col3="credit";
    public storeCreditHelperClass(Context context) {
        super(context, database_name, null, 1);
       // SQLiteDatabase db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+table_name+" (TO_NAME VARCHAR(30),FROM_NAME VARCHAR(30),CREDIT VARCHAR(20))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+table_name);
        onCreate(sqLiteDatabase);
    }
    public boolean insertData(String to_name,String from_name,String credit)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(col1,to_name);
        contentValues.put(col2,from_name);
        contentValues.put(col3,credit);
        long result=db.insert(table_name,null,contentValues);
        if (result==-1)
        {
            return false;
        }
        else
            return true;
    }
    public int delete_data(String To_name)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        return  db.delete(table_name,"TO_NAME = ?",new String[] {To_name});
    }
}

package com.example.dell.creditmanagementapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelperClass extends SQLiteOpenHelper {
    public static final String database_name="creditManagement2.db";
    public static final String table_name="people_info";
    public static final String col1="id";
    public static final String col2="name";
    public static final String col3="email";
    public static final String col4="current_credit";
    public DatabaseHelperClass(Context context) {
        super(context, database_name, null, 1);


    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("create table "+table_name+" (NAME VARCHAR(30),EMAIL VARCHAR(50),CURRENT_CREDIT VARCHAR(20),primary key(NAME,EMAIL))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+table_name);

        onCreate(sqLiteDatabase);
    }
    public boolean insertData(String name,String email,String credit)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(col2,name);
        contentValues.put(col3,email);
        contentValues.put(col4,credit);
        long result=db.insert(table_name,null,contentValues);
        if (result==-1)
        {
            return false;
        }
        else
            return true;
    }
    public Cursor getAllData()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+table_name,null);
        return res;
    }
    public int delete_data(String name)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        return  db.delete(table_name,"name = ?",new String[] {name});
    }
}

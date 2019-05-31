package com.example.dell.creditmanagementapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class view_all_credit extends AppCompatActivity {

    //DatabaseHelperClass mydb;
    LinearLayout ll;
    storeCreditHelperClass mydb1;
    ArrayList<String> al;
    ListView list_View;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_credit);
        Intent intent=getIntent();
        String name=intent.getStringExtra("name");
        //ll=(LinearLayout)findViewById(R.id.ll);
        al=new ArrayList<String>();
        //Toast.makeText(this, ""+name, Toast.LENGTH_SHORT).show();
        mydb1=new storeCreditHelperClass(this);
        //mydb=new DatabaseHelperClass(this);

        //int deleterow=mydb1.delete_data("1");
        //if(deleterow>0)
        //    Toast.makeText(this, "delete "+deleterow, Toast.LENGTH_SHORT).show();
        //else
         //   Toast.makeText(this, "not deleted", Toast.LENGTH_SHORT).show();
        SQLiteDatabase db1 = openOrCreateDatabase("creditManagement1.db", MODE_PRIVATE, null);
        String q1 = "create table if not exists store_credit(TO_NAME VARCHAR(30),FROM_NAME VARCHAR(30),CREDIT VARCHAR(20))";
        db1.execSQL(q1);
        list_View=(ListView)findViewById(R.id.listView);
        q1 = "Select * from store_credit where TO_NAME='" + name + "'";
        Cursor c = db1.rawQuery(q1, null);

        if (c.moveToFirst()) {

            do {



                al.add(c.getString(0)+"       "+c.getString(1)+"                  "+c.getString(2));
                //al.add(c.getString(1));
                //al.add(c.getString(2));

                //tv.setTextSize(20);
                //tv.setText(c.getString(0));
                //tv.setText(c.getString(1));
                //tv.setText(c.getString(2));
                //tv.setText("");
                //tv.setTextColor(50);
               // ll.addView(tv);

                //Toast.makeText(this, ""+c.getString(0), Toast.LENGTH_SHORT).show();
                //Toast.makeText(this, ""+c.getString(1), Toast.LENGTH_SHORT).show();
                //Toast.makeText(this, ""+c.getString(2), Toast.LENGTH_SHORT).show();
                //al.add(c.getString(0) + "  " + c.getString(1) + "  " + c.getString(2) + "  " + c.getString(3) + "  " + c.getString(4));
            } while (c.moveToNext());
            list_View = (ListView)findViewById(R.id.listView);
            //list_View.setBackgroundColor(0xFFEC407A);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.name_list,al);
            list_View.setAdapter(adapter);
        } else {
            //tv.setText("Sorry there is no train with such name or no .Please try again with different train no. or name");
            Toast.makeText(this, "Sorry there are no credit with such name", Toast.LENGTH_SHORT).show();
        }
        db1.close();
    }
}

package com.example.dell.creditmanagementapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class transferCredit extends AppCompatActivity {

    Spinner sp;
    DatabaseHelperClass mydb;
    storeCreditHelperClass storecreditdb;
    ArrayList<String> al;
    LinearLayout ll;

    EditText toname,credit;
    Button send;
    String sselect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_credit);
        sp=(Spinner)findViewById(R.id.spinner);
        Intent intent=getIntent();
        String name=intent.getStringExtra("name");
        ll=(LinearLayout) findViewById(R.id.ll);
        TextView tv=new TextView(this);
        tv.setText(name);
        tv.setTextSize(25);
        tv.setTextColor(0xFFEC407A);
        tv.setPadding(10,10,10,10);
        ll.addView(tv);
        mydb=new DatabaseHelperClass(this);
        storecreditdb=new storeCreditHelperClass(this);
        al=new ArrayList<String>();
        Cursor res=mydb.getAllData();
        if(res.getCount()==0)
        {
            showMessage("Error","No data found");
            return;
        }
       // StringBuffer buffer=new StringBuffer();
        //al.add("--select--");
        while (res.moveToNext())
        {
            //Toast.makeText(this, ""+res.getString(1), Toast.LENGTH_SHORT).show();
            //Toast.makeText(this, ""+res.getString(2), Toast.LENGTH_SHORT).show();
            String str=(String) res.getString(0);
            al.add(str);

            // buffer.append("ID "+res.getString(0)+" \n ");
            //buffer.append("name "+res.getString(1)+" \n ");
            //buffer.append("email "+res.getString(2)+" \n ");
            //buffer.append("credit "+res.getString(3)+" \n \n");
        }
        ArrayAdapter<String> ad=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,al);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(ad);
        sp.setBackgroundColor(0xFFEC407A);
        sp.setScrollBarSize(10);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sp.setSelection(i);
                sselect=adapterView.getItemAtPosition(i).toString();
                //Toast.makeText(transferCredit.this, ""+sselect, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //showMessage("Data",buffer.toString());

        final String toname=sselect;
        final String fromname=name;
        credit=(EditText)findViewById(R.id.etcredit);
        send=(Button)findViewById(R.id.btnsubmit);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(transferCredit.this, ""+sselect+" "+fromname+" "+credit.getText().toString(), Toast.LENGTH_SHORT).show();
                boolean isInserted=storecreditdb.insertData(sselect,fromname,credit.getText().toString());
                if(isInserted==true)
                    Toast.makeText(transferCredit.this, "Credited successfully", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(transferCredit.this, "Not Inserted", Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

}

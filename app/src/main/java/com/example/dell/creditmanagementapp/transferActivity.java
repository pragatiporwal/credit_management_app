package com.example.dell.creditmanagementapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class transferActivity extends AppCompatActivity {

    DatabaseHelperClass mydb;
    LinearLayout ll;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        ll=(LinearLayout)findViewById(R.id.ll);
        mydb=new DatabaseHelperClass(this);

        Cursor res=mydb.getAllData();
        if(res.getCount()==0)
        {
            showMessage("Error","No data found");
            return;
        }
        StringBuffer buffer=new StringBuffer();
        while (res.moveToNext())
        {
            //Toast.makeText(this, ""+res.getString(1), Toast.LENGTH_SHORT).show();
            //Toast.makeText(this, ""+res.getString(2), Toast.LENGTH_SHORT).show();
            final TextView tv=new TextView(this);
            tv.setText(res.getString(0));
            tv.setTextSize(20);
            tv.setTextColor(0xFFEC407A);
            tv.setPadding(10,10,10,10);
            ll.addView(tv);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent in=new Intent(transferActivity.this,transferCredit.class);
                    in.putExtra("name",tv.getText().toString());
                    startActivity(in);
                    Toast.makeText(transferActivity.this, ""+tv.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            });
           // buffer.append("ID "+res.getString(0)+" \n ");
            //buffer.append("name "+res.getString(1)+" \n ");
            //buffer.append("email "+res.getString(2)+" \n ");
            //buffer.append("credit "+res.getString(3)+" \n \n");
        }
        //showMessage("Data",buffer.toString());
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

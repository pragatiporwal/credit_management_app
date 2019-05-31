package com.example.dell.creditmanagementapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class insertData extends AppCompatActivity {
    EditText name,email,credit;
    DatabaseHelperClass mydb;
    Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);
        name=(EditText)findViewById(R.id.etname);
        email=(EditText)findViewById(R.id.etemail);
        credit=(EditText)findViewById(R.id.etcredit);
        add=(Button)findViewById(R.id.btnadd);

        mydb= new DatabaseHelperClass(this);
        /*int deleterow=mydb.delete_data(name.getText().toString());
        if (deleterow>0)
            Toast.makeText(this, "data deleted", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "data not deleted", Toast.LENGTH_SHORT).show();*/
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sname=name.getText().toString().trim();
                String semail=email.getText().toString().trim();
                String scredit=credit.getText().toString().trim();
                boolean isInserted=false;
                //int deleterow=mydb.delete_data(name.getText().toString());
                //if (deleterow>0)
                //    Toast.makeText(insertData.this, "data deleted"+deleterow, Toast.LENGTH_SHORT).show();
                //else
                 //   Toast.makeText(insertData.this, "data not deleted", Toast.LENGTH_SHORT).show();
               // Toast.makeText(insertData.this, ""+sname+" "+semail+" "+scredit, Toast.LENGTH_SHORT).show();
                if(!sname.isEmpty() && !semail.isEmpty() && !scredit.isEmpty())
                    isInserted=mydb.insertData(sname,semail,scredit);
                if(isInserted==true)
                    Toast.makeText(insertData.this, "Inserted successfully", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(insertData.this, "Not Inserted", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

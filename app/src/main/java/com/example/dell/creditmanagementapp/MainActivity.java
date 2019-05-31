package com.example.dell.creditmanagementapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button add,transfer,view;
    DatabaseHelperClass mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add=(Button)findViewById(R.id.btnadd);
        transfer=(Button)findViewById(R.id.btntransfer);
        view=(Button)findViewById(R.id.btnview);
        mydb= new DatabaseHelperClass(this);
        boolean isInserted=mydb.insertData("ram","ram@gmail.com","100");
        boolean isInserted1=mydb.insertData("shyam","shyam@gmail.com","200");
        boolean isInserted2=mydb.insertData("hari","hari@gmail.com","300");
        boolean isInserted3=mydb.insertData("poonam","poonam@gmail.com","400");
        boolean isInserted4=mydb.insertData("yashi","yashi@gmail.com","500");
        boolean isInserted5=mydb.insertData("shailly","shailly@gmail.com","600");
        boolean isInserted6=mydb.insertData("melee","melee@gmail.com","700");
        boolean isInserted7=mydb.insertData("anshika","anshika@gmail.com","800");
        boolean isInserted8=mydb.insertData("milan","milan@gmail.com","900");
        boolean isInserted9=mydb.insertData("tannu","tannu@gmail.com","1000");

        if(isInserted9==true) {
            //    Toast.makeText(MainActivity.this, "Inserted successfully", Toast.LENGTH_SHORT).show();
        }
        else {
            //Toast.makeText(MainActivity.this, "Not Inserted", Toast.LENGTH_SHORT).show();
        }
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent in=new Intent(MainActivity.this,insertData.class);
                startActivity(in);
            }
        });

        transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(MainActivity.this,transferActivity.class);
                startActivity(in);
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in =new Intent(MainActivity.this,view_all.class);
                startActivity(in);
            }
        });
    }
}

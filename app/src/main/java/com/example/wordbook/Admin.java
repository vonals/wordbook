package com.example.wordbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class Admin extends AppCompatActivity implements View.OnClickListener{

    private DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Button buttonBack=findViewById(R.id.Admin_back);
        buttonBack.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){

        Button buttonAdd=(Button)findViewById(R.id.Admin_add);
        buttonAdd.setOnClickListener(this);
        Button buttonNew=(Button)findViewById(R.id.Admin_new);
        buttonNew.setOnClickListener(this);
        switch (v.getId()){
            case R.id.Admin_back:
                Intent intent=new Intent(Admin.this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.Admin_add:
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                ContentValues values=new ContentValues();
                values.put("name","cat");
                values.put("sentence","I love cat!");
                db.insert("CAT",null,values);
                Log.d("min","add success");
                break;
            case R.id.Admin_new:
                SQLiteDatabase db1=dbHelper.getWritableDatabase();
                Cursor cursor=db1.query("CAT",null,null,null,null,null,null);
                if(cursor.moveToFirst()){
                    do{
                        String name=cursor.getString(cursor.getColumnIndex("name"));
                        String sentence=cursor.getString(cursor.getColumnIndex("sentence"));
                        Log.d("min","name"+name);
                        Log.d("min","sentence"+sentence);
                    }while(cursor.moveToNext());
                }
                break;
        }
    }



}

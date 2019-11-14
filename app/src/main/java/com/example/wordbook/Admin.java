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
        Button buttonAdd=findViewById(R.id.Admin_add);
        buttonAdd.setOnClickListener(this);
        Button buttonNew=findViewById(R.id.Admin_new);
        buttonNew.setOnClickListener(this);
        dbHelper=new DatabaseHelper(this,"CatShop.db",null,9);


    }
    @Override
    public void onClick(View v){

        switch (v.getId()){
            case R.id.Admin_back:
                Intent intent=new Intent(Admin.this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.Admin_add:
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                ContentValues values=new ContentValues();
                values.put("name","cat");
                values.put("meaning","猫");
                values.put("sentence","I love cat!");
                db.insert("CAT",null,values);
                Log.d("mint","add success");
                break;
            case R.id.Admin_new:
                SQLiteDatabase db1=dbHelper.getWritableDatabase();
                Cursor cursor=db1.query("CAT",null,null,null,null,null,null);
                if(cursor.moveToFirst()){
                    do{
                        String name=cursor.getString(cursor.getColumnIndex("name"));
                        String meaning=cursor.getString(cursor.getColumnIndex("meaning"));
                        String sentence=cursor.getString(cursor.getColumnIndex("sentence"));
                        Log.d("mint","name:"+name);
                        Log.d("mint","meaning:"+meaning);
                        Log.d("mint","sentence:"+sentence);
                    }while(cursor.moveToNext());
                }
                cursor.close();
                break;
        }
    }



}

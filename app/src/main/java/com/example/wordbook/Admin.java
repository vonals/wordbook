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
import android.widget.EditText;
import android.widget.Toast;

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
        Button buttonDelete=findViewById(R.id.Admin_delete);
        buttonDelete.setOnClickListener(this);

        Button buttonEdit=findViewById(R.id.Admin_edit);
        buttonEdit.setOnClickListener(this);

//        Button buttonNew=findViewById(R.id.Admin_search);
//        buttonNew.setOnClickListener(this);
        dbHelper=new DatabaseHelper(this,"CatShop.db",null,9);


    }
    @Override
    public void onClick(View v){
        EditText editText0=(EditText)findViewById(R.id.word_zero);
        EditText editText1=(EditText)findViewById(R.id.word_name);
        EditText editText2=(EditText)findViewById(R.id.word_meaning);
        EditText editText3=(EditText)findViewById(R.id.word_centence);
        switch (v.getId()){
            //返回查询主界面
            case R.id.Admin_back:
                Intent intent=new Intent(Admin.this,MainActivity.class);
                startActivity(intent);
                break;
            //添加词条
            case R.id.Admin_add:
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                ContentValues values=new ContentValues();

                values.put("name",editText1.getText().toString());
                values.put("meaning",editText2.getText().toString());
                values.put("sentence",editText3.getText().toString());
                db.insert("CAT",null,values);
                Log.d("mint","add success");
                Toast.makeText(Admin.this,"Add success",Toast.LENGTH_SHORT).show();
                break;
            //更新词条（未来应该是搜索的）
            case R.id.Admin_edit:
                SQLiteDatabase db1=dbHelper.getWritableDatabase();
                ContentValues values1=new ContentValues();

                if(!editText0.getText().toString().equals("")){
                    String str0=editText0.getText().toString();
                    String str1=editText1.getText().toString();
                    String str2=editText2.getText().toString();
                    String str3=editText3.getText().toString();

                if(!str1.equals("")){
                    values1.put("name",str1);
                    Log.d("mint","有???");
                }
                if(!str2.equals("")){
                    values1.put("meaning",str2);

                }
               if(!str3.equals("")){
                   values1.put("sentence",str3);

               }
               db1.update("CAT",values1,"name=?",new String[]{str0});
                Log.d("mint","有"+str0+"   "+str1+"    ");
                }
                Toast.makeText(Admin.this,"Update success",Toast.LENGTH_SHORT).show();
                break;
                //找名字删
            case R.id.Admin_delete:
                SQLiteDatabase db2=dbHelper.getWritableDatabase();
                if(!editText0.getText().toString().equals("")){
                    String str=editText0.getText().toString();
                    db2.delete("Cat","name=?",new String[]{str});
                    Toast.makeText(Admin.this,"Delete success",Toast.LENGTH_SHORT).show();
                    Log.d("mint","有删");
                }

                break;
        }
    }



}

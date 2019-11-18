package com.example.wordbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private List<Cat> cats=new ArrayList<>();
    private DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
        setContentView(R.layout.activity_main);
        //横屏判断
        final Configuration mConfiguration = this.getResources().getConfiguration();
        final int ori=mConfiguration.orientation;
        dbHelper=new DatabaseHelper(this,"CatShop.db",null,9);
        //临时测试按钮
        Button buttonS=(Button)findViewById(R.id.left_fragment_search);
        buttonS.setOnClickListener(this);
        EditText editText = (EditText) findViewById(R.id.left_fragment_text);
        editText.setText("search");
        //初始化list数组
        initCat();
        //设置数组响应器
        final CatAdapter adapter=new CatAdapter(MainActivity.this,R.layout.cat_item,cats);
        //listview操作部分
        ListView listView=(ListView)findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cat cat = cats.get(position);
                if(ori==mConfiguration.ORIENTATION_LANDSCAPE){

                    TextView textView=(TextView)findViewById(R.id.right_fragment_text);
                    textView.setText(cat.getName());
                }
                Toast.makeText(MainActivity.this, cat.getName(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void initCat(){

        for(int i=0;i<1;i++){
            SQLiteDatabase db1=dbHelper.getWritableDatabase();
            Cursor cursor=db1.query("CAT",null,null,null,null,null,null);
            if(cursor.moveToFirst()){
                do{
                    String name=cursor.getString(cursor.getColumnIndex("name"));
                    String meaning=cursor.getString(cursor.getColumnIndex("meaning"));
                    String sentence=cursor.getString(cursor.getColumnIndex("sentence"));
//                    Log.d("mint","name:"+name);
//                    Log.d("mint","meaning:"+meaning);
//                    Log.d("mint","sentence:"+sentence);
                    Cat C=new Cat(name,R.drawable.ic_launcher_foreground,meaning,sentence);
                    cats.add(C);
                }while(cursor.moveToNext());
            }
            final CatAdapter adapter=new CatAdapter(MainActivity.this,R.layout.cat_item,cats);
            //listview操作部分
            ListView listView=(ListView)findViewById(R.id.list);
            listView.setAdapter(adapter);
            cursor.close();
        }
    }

    //生成菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            //点击菜单按钮时切更改操作界面
            case R.id.mnue_Admin:
                Log.d("msg1","so?");
                Intent intent=new Intent(MainActivity.this,Admin.class);
                startActivity(intent);
                break;
                //拉长菜单1用
            case R.id.mnue_Create:

                Log.d("min","创建内");
                break;
            //帮助
            case R.id.mnue_Help:
                AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("Help");
                dialog.setMessage("这是个词典，你个傻卵");
                dialog.setCancelable(false);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.setNegativeButton("CANCLE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
                break;

        }
        return false;
    }


    //TRY


    //点击事件
    @Override
    public void onClick(View v){

        switch (v.getId()){

            case R.id.left_fragment_search:
                cats.clear();
                EditText editText=(EditText)findViewById(R.id.left_fragment_text);
                String str=editText.getText().toString();
                SQLiteDatabase db1=dbHelper.getWritableDatabase();
                Cursor cursor=db1.query("CAT",new String[]{"name,meaning,sentence"},"name like ?",new String[]{"%"+str+"%"},null,null,null);
               if(cursor.moveToFirst()){
                   do{
                       String name=cursor.getString(cursor.getColumnIndex("name"));
                       String meaning=cursor.getString(cursor.getColumnIndex("meaning"));
                       String sentence=cursor.getString(cursor.getColumnIndex("sentence"));

                       Cat C = new Cat(name, R.drawable.ic_launcher_foreground, meaning, sentence);
                       cats.add(C);
                       Log.d("mint", "name:" + name);
                       Log.d("mint", "meaning:" + meaning);
                       Log.d("mint", "sentence:" + sentence);
                   }while(cursor.moveToNext());
               }
                final CatAdapter adapter=new CatAdapter(MainActivity.this,R.layout.cat_item,cats);
                //listview操作部分
                ListView listView=(ListView)findViewById(R.id.list);
//                listView.setAdapter(null);
                listView.setAdapter(adapter);
                cursor.close();
                break;
        }
    }
}

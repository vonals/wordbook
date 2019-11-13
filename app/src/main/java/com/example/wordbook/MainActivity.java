package com.example.wordbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private List<Cat> cats=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
        setContentView(R.layout.activity_main);
        //横屏判断
        final Configuration mConfiguration = this.getResources().getConfiguration();
        final int ori=mConfiguration.orientation;
        initCat();
        final CatAdapter adapter=new CatAdapter(MainActivity.this,R.layout.cat_item,cats);
//        ArrayAdapter<String> adapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,data);
        ListView listView=(ListView)findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cat cat = cats.get(position);
                if(ori==mConfiguration.ORIENTATION_LANDSCAPE){
                    //找到操作的碎片
//                    RightFragment rightFragment=(RightFragment)getSupportFragmentManager().findFragmentById(R.id.left_fragment);
                    TextView textView=(TextView)findViewById(R.id.right_fragment_text);
                    textView.setText(cat.getName());
                }
                Toast.makeText(MainActivity.this, cat.getName(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void initCat(){

        for(int i=0;i<4;i++){
            Cat C=new Cat("Cat",R.drawable.ic_launcher_foreground,"猫","I have a cat.");
            cats.add(C);
            Cat D=new Cat("Dog",R.drawable.ic_launcher_foreground,"狗","She is my angle.");
            cats.add(D);
        }
    }

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

    @Override
    public void onClick(View v){
        switch (v.getId()){

        }
    }
}

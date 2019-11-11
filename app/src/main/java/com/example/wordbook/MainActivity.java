package com.example.wordbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
        initCat();
        CatAdapter adapter=new CatAdapter(MainActivity.this,R.layout.cat_item,cats);
//        ArrayAdapter<String> adapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,data);
        ListView listView=(ListView)findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cat cat = cats.get(position);
                Toast.makeText(MainActivity.this, cat.getName(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void initCat(){

        for(int i=0;i<1;i++){
            Cat Q=new Cat("Q",R.drawable.ic_launcher_foreground);
            cats.add(Q);
            Cat P=new Cat("P",R.drawable.ic_launcher_foreground);
            cats.add(P);
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
        }
        return false;
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){

        }
    }
}

package com.example.wordbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private List<Cat> cats=new ArrayList<>();
    private String[] data = {"暹罗猫", "布偶猫", "折耳猫", "短毛猫", "波斯猫", "蓝猫", "森林猫", "孟买猫","缅因猫","埃及猫","伯曼猫","缅甸猫","新加坡猫","美国短尾猫","巴厘猫"};
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
        }
    }

    @Override
    public void onClick(View v){

    }
}

package com.example.wordbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class Admin extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Button buttonBack=findViewById(R.id.Admin_bt2);
        buttonBack.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.Admin_bt2:
                Intent intent=new Intent(Admin.this,MainActivity.class);
                startActivity(intent);
                break;
        }
    }



}

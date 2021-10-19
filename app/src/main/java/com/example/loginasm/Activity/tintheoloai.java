package com.example.loginasm.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.loginasm.R;

public class tintheoloai extends AppCompatActivity {


    String diachi_rss;
    ListView lv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tintheoloai);

        diachi_rss=getIntent().getExtras().getString("diachi_rss");

        Toast.makeText(getApplicationContext(), diachi_rss, Toast.LENGTH_SHORT).show();
        lv2 = findViewById(R.id.listView2);
        MyAsyncTask gandulieu=new MyAsyncTask(tintheoloai.this,diachi_rss);
        gandulieu.execute();

    }
}
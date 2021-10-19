package com.example.loginasm.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.loginasm.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openChucNang1(View view) {
        Intent intent = new Intent(MainActivity.this, MenuActivity.class);
        startActivity(intent);
    }

    public void openChucNang2(View view) {
        Intent intent = new Intent(MainActivity.this, MapActivity.class);
        startActivity(intent);
    }

    public void openChucNang3(View view) {
        Intent intent = new Intent(MainActivity.this, DocBaoActivity.class);
        startActivity(intent);
    }

    public void openChucNang4(View view) {
        Intent intent = new Intent(MainActivity.this, MusicActivity.class);
        startActivity(intent);
    }
}
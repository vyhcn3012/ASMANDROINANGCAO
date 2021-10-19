package com.example.loginasm.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;

import com.example.loginasm.R;

public class MenuActivity extends AppCompatActivity {

    ImageView imgCaNhan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        imgCaNhan = findViewById(R.id.imageViewCaNhan);


    }

    private void showMenu(){
        PopupMenu popupMenu = new PopupMenu(this, imgCaNhan);
        popupMenu.getMenuInflater().inflate(R.menu.menu_popop, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()){
                    case R.id.menuDangxuat:
                        SharedPreferences sharedPreferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("remember", "false");
                        editor.apply();

                        finish();

                        break;
                }
                return false;
            }
        });
        popupMenu.show();
    }

    public void openMenu(View view) {
        showMenu();
    }

    public void quanlySV(View view){
        Intent intent = new Intent(MenuActivity.this, ThemSV.class);
        startActivity(intent);
    }

    public void themlop(View view){
        Intent intent = new Intent(MenuActivity.this, ThemLopActivity.class);
        startActivity(intent);
    }

    public void danhsach(View view){
        Intent intent = new Intent(MenuActivity.this, XemDanhSachLopActivity.class);
        startActivity(intent);
    }
}
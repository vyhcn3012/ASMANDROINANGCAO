package com.example.loginasm.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.loginasm.R;

public class DocBaoActivity extends AppCompatActivity {

    String [] ten_loai={"Trang Chủ","Tin tức trong ngày","Bóng đá","Du lich"};

    String [] rss_loai={"https://www.24h.com.vn/upload/rss/trangchu24h.rss",
            "https://www.24h.com.vn/upload/rss/tintuctrongngay.rss",
            "https://www.24h.com.vn/upload/rss/bongda.rss",
            "https://www.24h.com.vn/upload/rss/dulich.rss"
    };

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_bao);
        lv = findViewById(R.id.listView);
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,ten_loai);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getApplicationContext(),tintheoloai.class);
                intent.putExtra("diachi_rss", rss_loai[i]);
                startActivity(intent);
            }
        });
    }
}
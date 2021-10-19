package com.example.loginasm.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.example.loginasm.Adapter.TinTheoLoaiAdapter;
import com.example.loginasm.Model.Item;
import com.example.loginasm.R;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MyAsyncTask extends AsyncTask<Void,Void,Void> {

    ArrayList<Item> items=new ArrayList<Item>();
    String chuoi="";
    Context c;
    String diachi_rss;
    ProgressDialog dialog;

    MyAsyncTask(Context c, String diachi_rss)
    {
        this.diachi_rss=diachi_rss;
        this.c=c;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url=new URL(diachi_rss);
            URLConnection connection=url.openConnection();

            InputStream is=connection.getInputStream();
            items=(ArrayList<Item>) MySaxParser.xmlParser(is);
            for(int i=0;i<items.size();i++)
            {
                chuoi+=items.get(i).getTitle()+" ";
                chuoi+=items.get(i).getPubdate()+ "\n";
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.d("dulieu",e.toString());
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        // TODO Auto-generated method stub
        super.onPreExecute();
        dialog=ProgressDialog.show(c, "cho chut", "doc tren mang nen ton ti thoi gian");

    }

    @Override
    protected void onPostExecute(Void result) {
        // TODO Auto-generated method stub
        Log.d("dulieu", chuoi);
        try{
            TinTheoLoaiAdapter adapter=new TinTheoLoaiAdapter(c, R.layout.layout_tintheoloai_itemlistview,items);
            ((tintheoloai)c).lv2.setAdapter(adapter);

            ((tintheoloai)c).lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String link=items.get(position).getLink();
                    //Toast.makeText(getApplicationContext(),link, Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(c,XemTinActivity.class);
                    intent.putExtra("link", link);
                    c.startActivity(intent);
                }
            });
        }catch(Exception e)
        {
            Log.d("title","adapter khong duoc");
        }
        super.onPostExecute(result);
        dialog.dismiss();
    }
}

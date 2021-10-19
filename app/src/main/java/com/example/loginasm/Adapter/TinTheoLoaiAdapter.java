package com.example.loginasm.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.loginasm.Model.Item;
import com.example.loginasm.R;

import java.util.ArrayList;

public class TinTheoLoaiAdapter extends ArrayAdapter<Item> {
    Context context;
    ArrayList<Item> items;

    public TinTheoLoaiAdapter(Context context, int textViewResourceId, ArrayList<Item> objects) {
        super(context, textViewResourceId, objects);
        // TODO Auto-generated constructor stub
        this.context = context;
        this.items = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inf = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowview = inf.inflate(R.layout.layout_tintheoloai_itemlistview, parent, false);
        TextView tv_title = (TextView) rowview.findViewById(R.id.title);
        TextView tv_description = (TextView) rowview.findViewById(R.id.description);
        TextView tv_pubdate = (TextView) rowview.findViewById(R.id.pubdate);

        tv_title.setText(items.get(position).getTitle().toString());
        tv_description.setText(items.get(position).getDescription().toString());
        tv_pubdate.setText(items.get(position).getPubdate().toString());

        return rowview;
    }

    @Override
    public int getCount() {
        return items.size();
    }
}

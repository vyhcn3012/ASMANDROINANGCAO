package com.example.loginasm.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.loginasm.Model.LopHoc;
import com.example.loginasm.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterThemLop extends BaseAdapter {

    private Context context;
    private  int layout;
    private ArrayList<LopHoc> lopHocArrayList;

    public AdapterThemLop(Context context, int layout, List<LopHoc> foodsList) {
        this.context = context;
        this.layout = layout;
        this.lopHocArrayList = (ArrayList<LopHoc>) foodsList;
    }

    @Override
    public int getCount() {
        return lopHocArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return lopHocArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        ImageView imageView;
        TextView txtName, txtPrice;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = new ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.txtName = (TextView) row.findViewById(R.id.textViewMaLop);
            holder.txtPrice = (TextView) row.findViewById(R.id.textViewTenLop);

            row.setTag(holder);
        }
        else {
            holder = (ViewHolder) row.getTag();
        }

        LopHoc produsts = lopHocArrayList.get(position);

        holder.txtName.setText(produsts.getMaLop());
        holder.txtPrice.setText(produsts.getTenLop());

        return row;
    }
}

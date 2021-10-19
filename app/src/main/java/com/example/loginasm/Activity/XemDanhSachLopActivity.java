package com.example.loginasm.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.loginasm.Adapter.AdapterThemLop;
import com.example.loginasm.DAO.ClassDAO;
import com.example.loginasm.Model.LopHoc;
import com.example.loginasm.R;

import java.util.ArrayList;

public class XemDanhSachLopActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<LopHoc> list;
    AdapterThemLop adapter = null;
    ClassDAO classDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_danh_sach_lop);

        classDAO = new ClassDAO(this);

        listView = (ListView) findViewById(R.id.listviewXemDanhSachLop);
        list = new ArrayList<>();
        adapter = new AdapterThemLop(this, R.layout.class_items, list);
        listView.setAdapter(adapter);

        // get all data from sqlite
        Cursor cursor = classDAO.getData("SELECT * FROM lophoc");
        list.clear();
        while (cursor.moveToNext()) {
            String maLop = cursor.getString(0);
            String tenLop = cursor.getString(1);

            list.add(new LopHoc(maLop, tenLop));
        }
        adapter.notifyDataSetChanged();

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                CharSequence[] item = {"Update","Delete"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(XemDanhSachLopActivity.this);

                dialog.setTitle("Choose an action");
                dialog.setItems(item, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(which == 0){
                            Cursor c = classDAO.getData("SELECT maLop FROM lophoc");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            showDialogUpdate(XemDanhSachLopActivity.this, arrID.get(position));
                        }else{
                            Toast.makeText(XemDanhSachLopActivity.this, "Delete", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.show();
                return true;
            }
        });
    }
    private void showDialogUpdate(Activity activity, final int position){

        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.update_class_activity);
        dialog.setTitle("Update");

        final EditText edtName = (EditText) dialog.findViewById(R.id.editTextUpdateName);
        final EditText edtPrice = (EditText) dialog.findViewById(R.id.editTextUpdatePrice);
        Button btnUpdate = (Button) dialog.findViewById(R.id.buttonUpdate);

        // set width for dialog
        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels * 0.95);
        // set height for dialog
        int height = (int) (activity.getResources().getDisplayMetrics().heightPixels * 0.7);
        dialog.getWindow().setLayout(width, height);
        dialog.show();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    classDAO.updateData(
                            edtName.getText().toString().trim(),
                            edtPrice.getText().toString().trim()
                    );
                    dialog.dismiss();
                    Toast.makeText(activity, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
                }catch (Exception ex){
                    Log.e("Update error: ", ex.getMessage());
                }
                updateClassList();
            }
        });
    }

    private void updateClassList(){
        Cursor cursor = classDAO.getData("SELECT * FROM lophoc");
        list.clear();
        while (cursor.moveToNext()) {
            String maLop = cursor.getString(0);
            String tenLop = cursor.getString(1);

            list.add(new LopHoc(maLop, tenLop));
        }
        adapter.notifyDataSetChanged();
    }

}
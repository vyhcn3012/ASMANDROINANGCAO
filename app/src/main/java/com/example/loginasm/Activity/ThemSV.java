package com.example.loginasm.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.loginasm.Adapter.AdapterThemLop;
import com.example.loginasm.DAO.ClassDAO;
import com.example.loginasm.DAO.SinhVienDAO;
import com.example.loginasm.Database.SQLiteHelper;
import com.example.loginasm.Model.LopHoc;
import com.example.loginasm.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public class ThemSV extends AppCompatActivity {

    Spinner spinner;
    EditText edtName, edtPrice;
    Button btnChoose, btnAdd, btnList;
    ImageView imgView;
    String selectedBrandId = null;

    final int REQUEST_CODE_GALLERY = 999;

    public static SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_sv2);

        init();

        List<LopHoc> brands = (new ClassDAO(this)).get();
        AdapterThemLop adapter = new AdapterThemLop(this, R.layout.class_items, brands);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedBrandId = ((LopHoc)adapter.getItem(i)).getMaLop();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                selectedBrandId = null;
            }
        });

        sqLiteHelper = new SQLiteHelper(this, "ProdustDB.sqlite", null, 3);

        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS PRODUST (Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " name VARCHAR," +
                " price VARCHAR," +
                " image BLOG)");

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(
                        ThemSV.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    sqLiteHelper.insertData(edtName.getText().toString().trim(),
                            edtPrice.getText().toString().trim(),
                            imgViewToByte(imgView)
                    );
                    Toast.makeText(ThemSV.this, "Add successfully!", Toast.LENGTH_SHORT).show();
                    edtName.setText("");
                    edtPrice.setText("");
                    imgView.setImageResource(R.mipmap.ic_launcher);
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThemSV.this, ProdustList.class);
                startActivity(intent);
            }
        });
    }

    public static byte[] imgViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == REQUEST_CODE_GALLERY){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }
            else {
                Toast.makeText(this, "YOU DON'T HAVE PERMISSION TO ACCESS FILE", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void init(){
        edtName = findViewById(R.id.editTextname);
        edtPrice = findViewById(R.id.editTextPrice);
        btnChoose = findViewById(R.id.buttonChooseImage);
        btnAdd = findViewById(R.id.buttonAdd);
        btnList = findViewById(R.id.buttonList);
        imgView = findViewById(R.id.imageView);
        spinner = findViewById(R.id.spinnerLop);
    }
}
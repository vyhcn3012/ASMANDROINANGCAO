package com.example.loginasm.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginasm.DAO.ClassDAO;
import com.example.loginasm.DAO.LoginDAO;
import com.example.loginasm.R;

public class ThemLopActivity extends AppCompatActivity {

    EditText edtMaLop, edttenLop;
    Button btnThemLop, btnXoaTrang;
    public static ClassDAO classDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_lop);
        AnhXa();

        btnThemLop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String maLop = edtMaLop.getText().toString().trim();
                String tenLop = edttenLop.getText().toString().trim();

                try{
                    classDAO.insertData(maLop, tenLop);
                    Toast.makeText(ThemLopActivity.this, "Add successfully!", Toast.LENGTH_SHORT).show();
                    edtMaLop.setText("");
                    edttenLop.setText("");
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
    }

    public void AnhXa(){
        classDAO = new ClassDAO(this);
        edtMaLop = findViewById(R.id.editTextMaLop);
        edttenLop = findViewById(R.id.editTextTenLop);
        btnThemLop = findViewById(R.id.buttonLuuLop);
        btnXoaTrang = findViewById(R.id.buttonXoaTrang);
    }
}
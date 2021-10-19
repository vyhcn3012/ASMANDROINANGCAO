package com.example.loginasm.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginasm.DAO.LoginDAO;
import com.example.loginasm.R;

public class Login_UI extends AppCompatActivity {

    EditText edtLGUser, edtLGPass;
    CheckBox cbSave;
    Button btnLogin, btnSign;
    LoginDAO loginDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_ui);

        AnhXa();

        SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
        String checkbox = preferences.getString("remember", "");
        if(checkbox.equals("true")){
            Intent intent = new Intent(Login_UI.this, MenuActivity.class);
            startActivity(intent);
        }else if(checkbox.equals("false")){
            Toast.makeText(this, "Vui lòng đăng nhập!", Toast.LENGTH_SHORT).show();
        }

        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_UI.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        loginDAO = new LoginDAO(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = edtLGUser.getText().toString();
                String pass = edtLGPass.getText().toString();

                if(user.equals("")||pass.equals(""))
                    Toast.makeText(Login_UI.this, "Vui lòng nhập tài khoản hoặc mật khẩu!", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = loginDAO.checkusernamepassword(user, pass);
                    if(checkuserpass==true){
                        Toast.makeText(Login_UI.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(getApplicationContext(), MenuActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(Login_UI.this, "Đăng nhập thất bại!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        cbSave.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked()){

                    SharedPreferences sharedPreferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("remember", "true");
                    editor.apply();
                    Toast.makeText(Login_UI.this, "Đã lưu!", Toast.LENGTH_LONG).show();

                }else if(!buttonView.isChecked()){

                    SharedPreferences sharedPreferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("remember", "false");
                    editor.apply();
                    Toast.makeText(Login_UI.this, "Bỏ lưu!", Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    public void openMenu(){
        Intent intent = new Intent(Login_UI.this, MenuActivity.class);
        startActivity(intent);
    }

    public void AnhXa(){
        edtLGUser = findViewById(R.id.editTextLGUsername);
        edtLGPass = findViewById(R.id.editTextLGPass);
        btnLogin = findViewById(R.id.buttonLogin);
        btnSign = findViewById(R.id.buttonSignUp);
        cbSave = findViewById(R.id.checkboxSave);
    }
}
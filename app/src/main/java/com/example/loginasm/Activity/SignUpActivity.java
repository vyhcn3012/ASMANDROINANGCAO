package com.example.loginasm.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginasm.DAO.LoginDAO;
import com.example.loginasm.R;

public class SignUpActivity extends AppCompatActivity {

    EditText edtRGUser, edtRGPass, edtRGPassAgain;
    Button btnRGSig;
    LoginDAO loginDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        AnhXa();

        loginDAO = new LoginDAO(this);

        btnRGSig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = edtRGUser.getText().toString();
                String pass = edtRGPass.getText().toString();
                String repass = edtRGPassAgain.getText().toString();

                if(user.equals("")||pass.equals("")||repass.equals(""))
                    Toast.makeText(SignUpActivity.this, "Vui lòng nhập tài khoản hoặc mật khẩu!", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        Boolean checkuser = loginDAO.checkusername(user);
                        if(checkuser==false){
                            Boolean insert = loginDAO.insertData(user, pass);
                            if(insert==true){
                                Toast.makeText(SignUpActivity.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),Login_UI.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(SignUpActivity.this, "Đăng ký thất bai!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(SignUpActivity.this, "Tài khoản này đã có! Đăng ký một tài khoản khác!", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(SignUpActivity.this, "Mật khẩu không giống nhau!", Toast.LENGTH_SHORT).show();
                    }
                } }
        });

    }

    public void AnhXa(){
        edtRGUser = findViewById(R.id.editTextUsername);
        edtRGPass = findViewById(R.id.editTextPassword);
        edtRGPassAgain = findViewById(R.id.editTextPassagain);
        btnRGSig = findViewById(R.id.buttonRegister);
    }

    public void openLogin(){
        Intent intent = new Intent(getApplicationContext(), Login_UI.class);
        startActivity(intent);
    }
}
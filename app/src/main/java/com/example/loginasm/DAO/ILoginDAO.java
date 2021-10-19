package com.example.loginasm.DAO;

import android.database.sqlite.SQLiteOpenHelper;

import com.example.loginasm.Model.Login;

import java.util.List;

public interface ILoginDAO{

    Boolean insertData(String username, String password);
    Boolean checkusername(String username);
    Boolean checkusernamepassword(String username, String password);

}

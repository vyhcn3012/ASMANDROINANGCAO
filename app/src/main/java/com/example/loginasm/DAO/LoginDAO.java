package com.example.loginasm.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.loginasm.Database.Mydatabase;

public class LoginDAO implements ILoginDAO{

    Mydatabase mydatabase;

    public LoginDAO(Context context){
        mydatabase = new Mydatabase(context);
    }

    public Boolean insertData(String username, String password){
        SQLiteDatabase MyDB = mydatabase.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = MyDB.insert("userss", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean checkusername(String username) {
        SQLiteDatabase MyDB = mydatabase.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from userss where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase MyDB = mydatabase.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from userss where username = ? and password = ?", new String[] {username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
}

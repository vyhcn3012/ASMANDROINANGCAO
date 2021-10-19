package com.example.loginasm.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Mydatabase extends SQLiteOpenHelper {
    public Mydatabase(@Nullable Context context) {
        super(context, "ClassDBBBBB", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String sql = "create Table lophoc(maLop TEXT primary key NOT NULL, tenLop TEXT)";
        db.execSQL(sql);


//        sql = "create Table sinhvien(id TEXT primary key AUTOINCREMENT, tenSV TEXT, ngaySinh TEXT, image BLOD)";
//        db.execSQL(sql);

    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
        super.onConfigure(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists userss");
        db.execSQL("drop Table if exists class");
        db.execSQL("drop Table if exists sinhvien");
    }
}

package com.example.loginasm.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.example.loginasm.Database.Mydatabase;
import com.example.loginasm.Model.LopHoc;

import java.util.ArrayList;
import java.util.List;

public class ClassDAO implements IClassDAO {

    private static Mydatabase dbHelper;
    private SQLiteDatabase db;

    public ClassDAO(Context context){
        dbHelper = new Mydatabase(context);
        db = dbHelper.getWritableDatabase();
    }

    @Override
    public boolean insertData(String maLop, String tenLop) {
        SQLiteDatabase MyDB = dbHelper.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("maLop", maLop);
        contentValues.put("tenLop", tenLop);
        long result = MyDB.insert("lophoc", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    @Override
    public void updateData(String maLop, String tenLop) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        String sql = "UPDATE lophoc SET tenLop = ? WHERE maLop = ?";
        SQLiteStatement sqLiteStatement = database.compileStatement(sql);

        sqLiteStatement.bindString(1, maLop);
        sqLiteStatement.bindString(2, tenLop);

        sqLiteStatement.execute();
        database.close();
    }

    @Override
    public void deleteData(String maLop) {
    }

    public Cursor getData(String sql){
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    @Override
    public List<LopHoc> get() {
        List<LopHoc> list = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM lophoc", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String classId = cursor.getString(0);
            String className = cursor.getString(1);
            LopHoc lopHoc = new LopHoc(classId, className);
            list.add(lopHoc);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
}

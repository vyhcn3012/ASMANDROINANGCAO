package com.example.loginasm.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.example.loginasm.Database.Mydatabase;
import com.example.loginasm.Model.LopHoc;

import java.util.List;

public class SinhVienDAO implements ISinhVienDAO {

    private static Mydatabase mydatabase;

    public SinhVienDAO(Context context){
        mydatabase = new Mydatabase(context);
    }


    public void insertData(String tenSV, String ngaySinh, byte[] image){
        SQLiteDatabase database = mydatabase.getWritableDatabase();
        String sql = "INSERT INTO sinhvien VALUES (NULL, ?, ?, ?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, tenSV);
        statement.bindString(2, ngaySinh);
        statement.bindBlob(3, image);

        statement.executeInsert();
    }

    public void updateData(String tenSV, String ngaySinh, byte[] image, int id) {
        SQLiteDatabase database = mydatabase.getWritableDatabase();

        String sql = "UPDATE sinhvien SET tenSV = ?, ngaySinh = ?, image = ? WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);

        statement.bindString(1, tenSV);
        statement.bindString(2, ngaySinh);
        statement.bindBlob(3, image);
        statement.bindDouble(4, (double)id);

        statement.execute();
        database.close();
    }

    public  void deleteData(int id) {
        SQLiteDatabase database = mydatabase.getWritableDatabase();

        String sql = "DELETE FROM sinhvien WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, (double)id);

        statement.execute();
        database.close();
    }

    public Cursor getData(String sql){
        SQLiteDatabase database = mydatabase.getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    @Override
    public List<LopHoc> get() {
        return null;
    }
}

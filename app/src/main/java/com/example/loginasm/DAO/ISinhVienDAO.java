package com.example.loginasm.DAO;

import android.database.Cursor;

import com.example.loginasm.Model.LopHoc;

import java.util.List;

public interface ISinhVienDAO {
    public void insertData(String tenSV, String ngaySinh, byte[] image);

    public void updateData(String tenSV, String ngaySinh, byte[] image, int id);

    public void deleteData(int id);

    public Cursor getData(String sql);

    List<LopHoc> get();
}

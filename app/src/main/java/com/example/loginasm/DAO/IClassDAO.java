package com.example.loginasm.DAO;

import android.database.Cursor;

import com.example.loginasm.Model.LopHoc;

import java.util.List;

public interface IClassDAO {
    public boolean insertData(String maLop, String tenLop);

    public void updateData(String maLop, String tenLop);

    public void deleteData(String maLop);

    public Cursor getData(String sql);

    List<LopHoc> get();
}

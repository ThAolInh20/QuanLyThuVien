package haui.edu.libmanagerhaui.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "PNLIB";
    private static final int DB_VERSION = 1;

    static final String CREATE_TABLE_THU_THU =
            "CREATE TABLE ThuThu (" +
                    "maTT TEXT PRIMARY KEY, " +
                    "hoTen TEXT NOT NULL, " +
                    "matKhau TEXT NOT NULL)";
    //
    static final String CREATE_TABLE_THANH_VIEN =
            "CREATE TABLE ThanhVien (" +
                    "maTV INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "hoTen TEXT NOT NULL, " +
                    "namSinh TEXT NOT NULL)";
    //
    static final String CREATE_TABLE_LOAI_SACH =
            "CREATE TABLE LoaiSach (" +
                    "maLoai INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "tenLoai TEXT NOT NULL)";
    //
    static final String CREATE_TABLE_SACH =
            "CREATE TABLE Sach (" +
                    "maSach INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "tenSach TEXT NOT NULL, " +
                    "tacGia TEXT NOT NULL, " +
                    "giaThue INTEGER NOT NULL, " +
                    "maLoai INTEGER REFERENCES LoaiSach(maLoai))";
    //
    static final String CREATE_TABLE_PHIEU_MUON =
            "CREATE TABLE PhieuMuon (" +
                    "maPM     INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    "maTT     TEXT    REFERENCES ThuThu (maTT), "+
                    "maTV     TEXT    REFERENCES ThanhVien (maTV), "+
                    "maSach   INTEGER REFERENCES Sach (maSach), "+
                    "tienThue INTEGER NOT NULL, "+
                    "ngay     DATE    NOT NULL, "+
                    "traSach  INTEGER NOT NULL )";
    //


    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Tạo bảng Thủ thư
        db.execSQL(CREATE_TABLE_THU_THU);
        //Tạo bảng Thành viên
        db.execSQL(CREATE_TABLE_THANH_VIEN);
        //Tạo bảng Loại sách
        db.execSQL(CREATE_TABLE_LOAI_SACH);
        //Tạo bảng Sách
        db.execSQL(CREATE_TABLE_SACH);
        //Tạo bảng Phiếu mượn
        db.execSQL(CREATE_TABLE_PHIEU_MUON);
        //Insert data
        db.execSQL(Data_SQLite.INSERT_THUTHU);
        db.execSQL(Data_SQLite.INSERT_THANHVIEN);
        db.execSQL(Data_SQLite.INSERT_LOAI_SACH);
        db.execSQL(Data_SQLite.INSERT_SACH);
        db.execSQL(Data_SQLite.INSERT_PHIEU_MUON);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String dropTableThuThu = "drop table if exists ThuThu";
        db.execSQL(dropTableThuThu);
        String dropTableThanhVien = "drop table if exists ThanhVien";
        db.execSQL(dropTableThanhVien);
        String dropTableLoaiSach = "drop table if exists LoaiSach";
        db.execSQL(dropTableLoaiSach);
        String dropTableSach = "drop table if exists Sach";
        db.execSQL(dropTableSach);
        String dropTablePhieuMuon = "drop table if exists PhieuMuon";
        db.execSQL(dropTablePhieuMuon);

        onCreate(db);
    }
}

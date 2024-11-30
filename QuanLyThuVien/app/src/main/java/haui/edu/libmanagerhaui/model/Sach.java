package haui.edu.libmanagerhaui.model;

public class Sach {
    private int maSach;
    private String tenSach;
    private String tacGia;
    private int giaThue;
    private int maLoai;

    public Sach() {
    }

    public Sach(int maSach, String tenSach, String tacGia, int giaThue, int maLoai) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.giaThue = giaThue;
        this.maLoai = maLoai;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public int getGiaThue() {
        return giaThue;
    }

    public void setGiaThue(int giaThue) {
        this.giaThue = giaThue;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }
}


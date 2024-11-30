package haui.edu.libmanagerhaui.model;

public class ThanhVien {
    private int maTV;
    private String hoTen;
    private String namSinh;

    public ThanhVien() {
    }

    public ThanhVien(int maTV, String hoTen, String namSinh) {
        this.maTV = maTV;
        this.hoTen = hoTen;
        this.namSinh = namSinh;
    }

    public int getMaTV() {
        return maTV;
    }

    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }

    @Override
    public String toString() {
        return "ThanhVien{" +
                "maTV=" + maTV +
                ", hoTen='" + hoTen + '\'' +
                ", namSinh='" + namSinh + '\'' +
                '}';
    }
}

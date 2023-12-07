package management.model;

import java.sql.Date;

public class MayTinh {
    private String maMay, phong;
    private int baoHanh, thoiGianDung;
    private boolean coSan, trangThai;
    private Date ngayMua;

    public MayTinh(String maMay, String phong, int baoHanh, int thoiGianDung, boolean coSan, boolean trangThai, Date ngayMua) {
        this.maMay = maMay;
        this.phong = phong;
        this.baoHanh = baoHanh;
        this.thoiGianDung = thoiGianDung;
        this.coSan = coSan;
        this.trangThai = trangThai;
        this.ngayMua = ngayMua;
    }

    public MayTinh() {
    }

    public String getMaMay() {
        return maMay;
    }

    public void setMaMay(String maMay) {
        this.maMay = maMay;
    }

    public String getPhong() {
        return phong;
    }

    public void setPhong(String phong) {
        this.phong = phong;
    }

    public int getBaoHanh() {
        return baoHanh;
    }

    public void setBaoHanh(int baoHanh) {
        this.baoHanh = baoHanh;
    }

    public int getThoiGianDung() {
        return thoiGianDung;
    }

    public void setThoiGianDung(int thoiGianDung) {
        this.thoiGianDung = thoiGianDung;
    }

    public boolean isCoSan() {
        return coSan;
    }

    public void setCoSan(boolean coSan) {
        this.coSan = coSan;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public Date getNgayMua() {
        return ngayMua;
    }

    public void setNgayMua(Date ngayMua) {
        this.ngayMua = ngayMua;
    }
}

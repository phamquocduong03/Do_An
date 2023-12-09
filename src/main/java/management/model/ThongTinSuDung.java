package management.model;

import java.time.LocalDateTime;

public class ThongTinSuDung {
    private String maMay, username, sdt;
    private LocalDateTime tgBatDau, tgKetThuc;
    private boolean dangSuDung;

    public ThongTinSuDung(String maMay, String username, String sdt, LocalDateTime tgBatDau, LocalDateTime tgKetThuc, boolean dangSuDung) {
        this.maMay = maMay;
        this.username = username;
        this.sdt = sdt;
        this.tgBatDau = tgBatDau;
        this.tgKetThuc = tgKetThuc;
        this.dangSuDung = dangSuDung;
    }

    public ThongTinSuDung() {
    }

    public String getMaMay() {
        return maMay;
    }

    public void setMaMay(String maMay) {
        this.maMay = maMay;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public LocalDateTime getTgBatDau() {
        return tgBatDau;
    }

    public void setTgBatDau(LocalDateTime tgBatDau) {
        this.tgBatDau = tgBatDau;
    }

    public LocalDateTime getTgKetThuc() {
        return tgKetThuc;
    }

    public void setTgKetThuc(LocalDateTime tgKetThuc) {
        this.tgKetThuc = tgKetThuc;
    }

    public boolean isDangSuDung() {
        return dangSuDung;
    }

    public void setDangSuDung(boolean dangSuDung) {
        this.dangSuDung = dangSuDung;
    }
}

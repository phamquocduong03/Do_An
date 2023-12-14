package management.model;

public class TaiKhoan {
    private String username, sdt, password, hangThanhVien;
    private boolean role, dangSuDung;
    private int soPhutDaDung;
    private double soTien;

    public TaiKhoan(String username, String sdt, String password, String hangThanhVien, boolean role, boolean dangSuDung, int soPhutDaDung, double soTien) {
        this.username = username;
        this.sdt = sdt;
        this.password = password;
        this.hangThanhVien = hangThanhVien;
        this.role = role;
        this.dangSuDung = dangSuDung;
        this.soPhutDaDung = soPhutDaDung;
        this.soTien = soTien;
    }

    public TaiKhoan() {
        super();
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHangThanhVien() {
        return hangThanhVien;
    }

    public void setHangThanhVien(String hangThanhVien) {
        this.hangThanhVien = hangThanhVien;
    }

    public boolean isRole() {
        return role;
    }

    public void setRole(boolean role) {
        this.role = role;
    }

    public boolean isDangSuDung() {
        return dangSuDung;
    }

    public void setDangSuDung(boolean dangSuDung) {
        this.dangSuDung = dangSuDung;
    }

    public int getSoPhutDaDung() {
        return soPhutDaDung;
    }

    public void setSoPhutDaDung(int soPhutDaDung) {
        this.soPhutDaDung = soPhutDaDung;
    }

    public double getSoTien() {
        return soTien;
    }

    public void setSoTien(double soTien) {
        this.soTien = soTien;
    }


}

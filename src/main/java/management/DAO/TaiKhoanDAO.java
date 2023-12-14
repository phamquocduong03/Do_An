package management.DAO;

import management.config.DatabaseConfig;
import management.model.TaiKhoan;
import management.model.ThongTinSuDung;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaiKhoanDAO {

    public List<TaiKhoan> getAll() throws Exception {
        List<TaiKhoan> lstTaiKhoan = new ArrayList<>();
        String sql = "SELECT * FROM TAIKHOAN";
        try (
                Connection con = DatabaseConfig.openConnection();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                )
        {
            while (rs.next()) {
                TaiKhoan tk = new TaiKhoan();
                tk.setUsername(rs.getString("USERNAME"));
                tk.setSdt(rs.getString("SDT"));
                tk.setPassword(rs.getString("PASSWORD"));
                tk.setRole(rs.getBoolean("ROLE"));
                tk.setHangThanhVien(rs.getString("HANGTHANHVIEN"));
                tk.setSoPhutDaDung(rs.getInt("SOPHUTDADUNG"));
                tk.setSoTien(rs.getDouble("SOTIENCONLAI"));
                tk.setDangSuDung(rs.getBoolean("DANGSUDUNG"));

                lstTaiKhoan.add(tk);
            }
            return lstTaiKhoan;
        }
    }

    public boolean insert(TaiKhoan tk) throws Exception {
        String sql = "INSERT INTO TAIKHOAN(USERNAME, SDT, PASSWORD, ROLE, HANGTHANHVIEN, SOPHUTDADUNG, SOTIENCONLAI, DANGSUDUNG) " +
                     "VALUES (?, ?, ?, 0, NULL, 0, 0, 0)";
        try (
                Connection con = DatabaseConfig.openConnection();
                PreparedStatement pstm = con.prepareStatement(sql);
                )
        {
            pstm.setString(1, tk.getUsername());
            pstm.setString(2, tk.getSdt());
            pstm.setString(3, tk.getPassword());

            return pstm.executeUpdate() > 0;
        }
    }

    public TaiKhoan findByUsernameAndPassword(String username, String password) throws Exception {
        String sql = "SELECT * FROM TAIKHOAN WHERE USERNAME = ? AND PASSWORD = ?";
        try (
                Connection con = DatabaseConfig.openConnection();
                PreparedStatement pstm = con.prepareStatement(sql);
                )
        {
            pstm.setString(1, username);
            pstm.setString(2, password);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                TaiKhoan tk = new TaiKhoan();
                tk.setUsername(rs.getString("USERNAME"));
                tk.setSdt(rs.getString("SDT"));
                tk.setPassword(rs.getString("PASSWORD"));
                tk.setRole(rs.getBoolean("ROLE"));
                tk.setHangThanhVien(rs.getString("HANGTHANHVIEN"));
                tk.setSoPhutDaDung(rs.getInt("SOPHUTDADUNG"));
                tk.setSoTien(rs.getDouble("SOTIENCONLAI"));
                tk.setDangSuDung(rs.getBoolean("DANGSUDUNG"));
                return tk;
            }

            return null;
        }
    }

    public TaiKhoan findByUsernameAndSdt(String username, String sdt) throws Exception {
        String sql = "SELECT * FROM TAIKHOAN WHERE USERNAME = ? AND SDT = ?";
        try (
                Connection con = DatabaseConfig.openConnection();
                PreparedStatement pstm = con.prepareStatement(sql);
                )
        {
            pstm.setString(1, username);
            pstm.setString(2, sdt);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                TaiKhoan tk = new TaiKhoan();
                tk.setUsername(rs.getString("USERNAME"));
                tk.setSdt(rs.getString("SDT"));
                tk.setPassword(rs.getString("PASSWORD"));
                tk.setRole(rs.getBoolean("ROLE"));
                tk.setHangThanhVien(rs.getString("HANGTHANHVIEN"));
                tk.setSoPhutDaDung(rs.getInt("SOPHUTDADUNG"));
                tk.setSoTien(rs.getDouble("SOTIENCONLAI"));
                tk.setDangSuDung(rs.getBoolean("DANGSUDUNG"));
                return tk;
            }

            return null;
        }
    }

    public boolean update(TaiKhoan tk) throws Exception {
        String sql = "UPDATE TAIKHOAN SET PASSWORD = ?, ROLE = ?, HANGTHANHVIEN = ?, SOPHUTDADUNG = ?, SOTIENCONLAI = ?, DANGSUDUNG = ? WHERE USERNAME = ?, SDT = ?";
        try (
                Connection con = DatabaseConfig.openConnection();
                PreparedStatement pstm = con.prepareStatement(sql);
                )
        {
            pstm.setString(7, tk.getUsername());
            pstm.setString(8, tk.getSdt());
            pstm.setString(1, tk.getPassword());
            pstm.setBoolean(2, tk.isRole());
            pstm.setString(3, tk.getHangThanhVien());
            pstm.setInt(4, tk.getSoPhutDaDung());
            pstm.setDouble(5, tk.getSoTien());
            pstm.setBoolean(7, tk.isDangSuDung());

            return pstm.executeUpdate() > 0;
        }
    }

    public boolean updatePassword(TaiKhoan tk) throws Exception {
        String sql = "UPDATE TAIKHOAN SET PASSWORD = ? WHERE USERNAME = ? AND SDT = ?";
        try (
                Connection con = DatabaseConfig.openConnection();
                PreparedStatement pstm = con.prepareStatement(sql);
        )
        {
            pstm.setString(2, tk.getUsername());
            pstm.setString(3, tk.getSdt());
            pstm.setString(1, tk.getPassword());

            return pstm.executeUpdate() > 0;
        }
    }


    public TaiKhoan getByUsernameAndSDT(String username, String sdt) throws Exception {
        TaiKhoan taiKhoan = null;
        String sql = "SELECT * FROM TAIKHOAN WHERE USERNAME = ? AND SDT = ?";

        try (
                Connection con = DatabaseConfig.openConnection();
                PreparedStatement pstm = con.prepareStatement(sql);
        ) {
            pstm.setString(1, username);
            pstm.setString(2, sdt);

            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    taiKhoan = new TaiKhoan();
                    taiKhoan.setUsername(rs.getString("USERNAME"));
                    taiKhoan.setSdt(rs.getString("SDT"));
                    taiKhoan.setSoTien(rs.getInt("SOTIENCONLAI"));
                    // Lấy thông tin khác từ bảng TAIKHOAN
                    // ...
                }
            }
        }

        return taiKhoan;
    }


    public void updateDangSuDungByUsernameAndSDT(String username, String sdt, boolean dangSuDung) throws Exception {
        String sql = "UPDATE TAIKHOAN SET DANGSUDUNG = ? WHERE USERNAME = ? AND SDT = ?";
        try (
                Connection con = DatabaseConfig.openConnection();
                PreparedStatement pstm = con.prepareStatement(sql);
        ) {
            pstm.setBoolean(1, dangSuDung);
            pstm.setString(2, username);
            pstm.setString(3, sdt);

            int rowsAffected = pstm.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Đã cập nhật giá trị DANGSUDUNG trong bảng TAIKHOAN.");
            } else {
                System.out.println("Không có dữ liệu nào được cập nhật trong bảng TAIKHOAN.");
            }
        }
    }

    public void updateTaiKhoan(TaiKhoan taiKhoan) {
        String sql = "UPDATE TAIKHOAN SET SOTIENCONLAI = ? WHERE USERNAME = ?";

        try (
                Connection con = DatabaseConfig.openConnection();
                PreparedStatement pstm = con.prepareStatement(sql);
        ) {
            pstm.setInt(1, (int) taiKhoan.getSoTien());
            pstm.setString(2, taiKhoan.getUsername());

            int rowsUpdated = pstm.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Tài khoản đã được cập nhật thành công.");
            } else {
                System.out.println("Không có dữ liệu nào được cập nhật.");
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật tài khoản:");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}




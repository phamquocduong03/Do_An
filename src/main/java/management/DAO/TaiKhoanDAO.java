package management.DAO;

import management.config.DatabaseConfig;
import management.model.TaiKhoan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
                tk.setSoTien(rs.getDouble("SOTIEN"));
                tk.setDangSuDung(rs.getBoolean("DANGSUDUNG"));

                lstTaiKhoan.add(tk);
            }
            return lstTaiKhoan;
        }
    }

    public boolean insert(TaiKhoan tk) throws Exception {
        String sql = "INSERT INTO TAIKHOAN(USERNAME, SDT, PASSWORD, ROLE, HANGTHANHVIEN, SOPHUTDADUNG, SOTIEN, DANGSUDUNG) " +
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
                tk.setSoTien(rs.getDouble("SOTIEN"));
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
                tk.setSoTien(rs.getDouble("SOTIEN"));
                tk.setDangSuDung(rs.getBoolean("DANGSUDUNG"));
                return tk;
            }

            return null;
        }
    }

    public boolean update(TaiKhoan tk) throws Exception {
        String sql = "UPDATE TAIKHOAN SET PASSWORD = ?, ROLE = ?, HANGTHANHVIEN = ?, SOPHUTDADUNG = ?, SOTIEN = ?, DANGSUDUNG = ? WHERE USERNAME = ?, SDT = ?";
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
}

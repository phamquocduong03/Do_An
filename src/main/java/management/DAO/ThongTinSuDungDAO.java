package management.DAO;

import management.config.DatabaseConfig;
import management.model.ThongTinSuDung;


import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class ThongTinSuDungDAO {
    public void addThongTinSuDung(ThongTinSuDung thongTinSuDung) throws Exception {
        String sql = "INSERT INTO THONGTINSUDUNG (TAIKHOAN, SDT, MAY, TGBATDAU, TGKETTHUC, DANGSUDUNG) VALUES (?, ?, ?, ?, ?, ?)";

        try (
                Connection con = DatabaseConfig.openConnection();
                PreparedStatement pstm = con.prepareStatement(sql);
        ) {
            pstm.setString(1, thongTinSuDung.getUsername());
            pstm.setString(2, thongTinSuDung.getSdt());
            pstm.setString(3, thongTinSuDung.getMaMay());

            Timestamp tgBatDauTimestamp = Timestamp.valueOf(thongTinSuDung.getTgBatDau());
            Timestamp tgKetThucTimestamp = Timestamp.valueOf(thongTinSuDung.getTgKetThuc());

            pstm.setTimestamp(4, tgBatDauTimestamp);
            pstm.setTimestamp(5, tgKetThucTimestamp);
            pstm.setBoolean(6, thongTinSuDung.isDangSuDung());

            int rowsAffected = pstm.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Dữ liệu đã được thêm vào bảng THONGTINSUDUNG.");
            } else {
                System.out.println("Không có dữ liệu nào được thêm vào bảng THONGTINSUDUNG.");
            }
        }
    }


    public ThongTinSuDung getByMaMay(String maMay) throws Exception {
        ThongTinSuDung thongTinSuDung = null;
        String sql = "SELECT * FROM THONGTINSUDUNG WHERE MAY = ?";

        try (
                Connection con = DatabaseConfig.openConnection();
                PreparedStatement pstm = con.prepareStatement(sql);
        ) {
            pstm.setString(1, maMay);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    thongTinSuDung = new ThongTinSuDung();
                    thongTinSuDung.setMaMay(rs.getString("MAY"));
                    thongTinSuDung.setUsername(rs.getString("TAIKHOAN"));
                    thongTinSuDung.setSdt(rs.getString("SDT"));
                    thongTinSuDung.setDangSuDung(rs.getBoolean("DANGSUDUNG"));
                    // Thêm các cột cần lấy thông tin vào đây
                }
            }
        }

        return thongTinSuDung;
    }


    public ThongTinSuDung getByMaMayAndDangSuDung(String maMay, boolean dangSuDung) throws Exception {
        String sql = "SELECT * FROM THONGTINSUDUNG WHERE MAY = ? AND DANGSUDUNG = ?";
        try (
                Connection con = DatabaseConfig.openConnection();
                PreparedStatement pstm = con.prepareStatement(sql);
        ) {
            pstm.setString(1, maMay);
            pstm.setBoolean(2, dangSuDung);

            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    ThongTinSuDung thongTinSuDung = new ThongTinSuDung();
                    thongTinSuDung.setMaMay(rs.getString("MAY"));
                    thongTinSuDung.setUsername(rs.getString("TAIKHOAN"));
                    thongTinSuDung.setSdt(rs.getString("SDT"));
                    thongTinSuDung.setTgBatDau(rs.getTimestamp("TGBATDAU").toLocalDateTime());
                    thongTinSuDung.setTgKetThuc(rs.getTimestamp("TGKETTHUC").toLocalDateTime());
                    thongTinSuDung.setDangSuDung(rs.getBoolean("DANGSUDUNG"));
                    return thongTinSuDung;
                }
            }
        }
        return null;
    }


    public void updateThongTinSuDung(ThongTinSuDung thongTinSuDung) throws Exception {
        String sql = "UPDATE THONGTINSUDUNG SET DANGSUDUNG = ? WHERE TAIKHOAN = ? AND SDT = ?";

        try (
                Connection con = DatabaseConfig.openConnection();
                PreparedStatement pstm = con.prepareStatement(sql);
        ) {
            pstm.setBoolean(1, false); // Set giá trị DANGSUDUNG về False
            pstm.setString(2, thongTinSuDung.getUsername());
            pstm.setString(3, thongTinSuDung.getSdt());

            int rowsAffected = pstm.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Đã cập nhật thông tin sử dụng.");
            } else {
                System.out.println("Không có thông tin sử dụng nào được cập nhật.");
            }
        }
    }

}




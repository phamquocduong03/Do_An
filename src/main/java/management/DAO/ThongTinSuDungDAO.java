package management.DAO;

import management.config.DatabaseConfig;
import management.model.ThongTinSuDung;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ThongTinSuDungDAO {
    public void addThongTinSuDung(ThongTinSuDung thongTinSuDung) throws Exception {
        String sql = "INSERT INTO THONGTINSUDUNG (TAIKHOAN, SDT, MAY) VALUES (?, ?, ?)";

        try (
                Connection con = DatabaseConfig.openConnection();
                PreparedStatement pstm = con.prepareStatement(sql);
        ) {
            pstm.setString(2, thongTinSuDung.getUsername());
            pstm.setString(3, thongTinSuDung.getSdt());
            pstm.setString(1, thongTinSuDung.getMaMay());

            int rowsAffected = pstm.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Dữ liệu đã được thêm vào bảng THONGTINSUDUNG.");
            } else {
                System.out.println("Không có dữ liệu nào được thêm vào bảng THONGTINSUDUNG.");
            }
        }
    }

}

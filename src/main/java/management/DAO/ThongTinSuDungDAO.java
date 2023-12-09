package management.DAO;

import management.config.DatabaseConfig;
import management.model.ThongTinSuDung;

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

}

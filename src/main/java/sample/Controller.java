package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Controller {



    private static final String URL = "jdbc:sqlserver://DUONG:1433;databaseName=QuanLyTiemNet;user=DUONG10112003;password=@Duong10112003";

    public static String getUrl(){
        return URL;
    }
   /* public void initialize() {
        try {
            // Thiết lập kết nối
            Connection conn = DriverManager.getConnection(URL);
            if (conn != null) {
                System.out.println("Kết nối thành công!");
                // Thực hiện truy vấn
                ResultSet resultSet = conn.createStatement().executeQuery("SELECT MAMAY, BAOHANH FROM MAYTINH");

                // Đổ dữ liệu từ ResultSet vào TableView
                while (resultSet.next()) {
                    String maMay = resultSet.getString("MAMAY");
                    String baoHanh = resultSet.getString("BAOHANH");

                    System.out.println("Ma May: " + maMay + ", Bao Hanh: " + baoHanh);
                    // Tạo một đối tượng DataModel từ dữ liệu truy vấn
                    DataModel data = new DataModel(maMay, baoHanh);
                    // Thêm vào TableView
                    tableView.getItems().add(data);
                }

                mayTinhColumn.setCellValueFactory(new PropertyValueFactory<>("maMay"));
                baoHanhColumn.setCellValueFactory(new PropertyValueFactory<>("baoHanh"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }*/





}




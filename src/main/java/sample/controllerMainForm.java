package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import management.DAO.MayTinhDAO;
import management.DAO.TaiKhoanDAO;
import management.DAO.ThongTinSuDungDAO;
import management.model.MayTinh;
import management.model.TaiKhoan;
import management.model.ThongTinSuDung;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

public class controllerMainForm implements Initializable {

    @FXML
    private Button PT1_Active_Btn;
    @FXML
    private AnchorPane PT1_Active_Pane;

    @FXML
    private TextField PT1_Active_text_Field_SDT;

    @FXML
    private TextField PT1_Active_text_Field_name;

    @FXML
    private Button PT1_active;

    @FXML
    private Button PT1_cp1_Btn;

    @FXML
    private AnchorPane PT1_cp1_Pane;

    @FXML
    private Button PT1_cp2_Btn;

    @FXML
    private AnchorPane PT1_cp2_Pane;

    @FXML
    private Button PT2_cp1_Btn;

    @FXML
    private AnchorPane PT2_cp1_Pane;

    @FXML
    private Button PT2_cp2_Btn;

    @FXML
    private AnchorPane PT2_cp2_Pane;

    @FXML
    private Button VIP_cp1_Btn;

    @FXML
    private AnchorPane VIP_cp1_Pane;

    @FXML
    private Button VIP_cp2_Btn;

    @FXML
    private AnchorPane VIP_cp2_pane;

    @FXML
    private AnchorPane optionsPanelPT1;

    @FXML
    private AnchorPane optionsPanelPT2;

    @FXML
    private AnchorPane optionsPanelRed_PT1;

    @FXML
    private AnchorPane optionsPanelRed_PT2;

    @FXML
    private AnchorPane optionsPanelRed_VIP;

    @FXML
    private AnchorPane optionsPanelVIP;

    private Button selectedComputer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Đặt trạng thái hiển thị cho máy
        MayTinh mt1 = null;
        MayTinh mt2 = null;

        try {
            // Lấy thông tin của máy tính từ database dựa trên MAMAY
            mt1 = MayTinhDAO.findByMamay("MAY001");
            mt2 = MayTinhDAO.findByMamay("MAY002");
            // Cập nhật trạng thái của AnchorPane tương ứng với dữ liệu lấy được từ database

            setButtonStatus(PT1_cp1_Btn, mt1 != null && !mt1.isCoSan());
            setButtonStatus(PT1_cp2_Btn, mt2 != null && !mt2.isCoSan());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Cài đặt trạng thái cho các nút khác tương tự

        PT1_cp1_Btn.setOnAction(this::handleButtonAction_PT1);
        if (mt1 != null) {
            PT1_cp1_Btn.setUserData(mt1);
        }
        PT1_cp2_Btn.setOnAction(this::handleButtonAction_PT1);

        PT2_cp1_Btn.setOnAction(this::handleButtonAction_PT2);
        PT2_cp2_Btn.setOnAction(this::handleButtonAction_PT2);

        VIP_cp1_Btn.setOnAction(this::handleButtonAction_VIP);
        VIP_cp2_Btn.setOnAction(this::handleButtonAction_VIP);

    }



    private void setButtonStatus(Button button, boolean isUsed) {
        if (isUsed) {
            button.setText("Onl");
            button.setStyle("-fx-background-color: #00FF00;"); // Màu Xanh
        } else {
            button.setText("Off");
            button.setStyle("-fx-background-color: #FF0000;"); // Màu Đỏ
        }
    }

    public void handleButtonAction_PT1(ActionEvent event) {
        Button clickedButton = (Button)event.getSource();
        // Kiểm tra nếu nút có màu xanh
        if (clickedButton.getStyle().contains("background-color: #00FF00;")) {
            // Cập nhật máy tính hiện tại được chọn
            selectedComputer = clickedButton;
            System.out.println(selectedComputer.getUserData());
            // Hiển thị optionsPanel nếu nút có màu xanh
            optionsPanelPT1.setVisible(true);
            //optionsPanelVIP.setVisible(true);
            optionsPanelPT1.setLayoutX(clickedButton.getLayoutX());
            optionsPanelPT1.setLayoutY(clickedButton.getLayoutY() + clickedButton.getHeight());
        } else {
            selectedComputer = clickedButton;
            // Ẩn optionsPanel nếu nút không có màu xanh
            optionsPanelRed_PT1.setVisible(true);
            optionsPanelRed_PT1.setLayoutX(clickedButton.getLayoutX());
            optionsPanelRed_PT1.setLayoutY(clickedButton.getLayoutY() + clickedButton.getHeight());
        }
    }


    @FXML
    private void handlePT1ActiveButtonAction(ActionEvent event) {
        PT1_Active_Pane.setVisible(true);
    }

    @FXML
    private void handleSaveAction(ActionEvent event) {
        String username = PT1_Active_text_Field_name.getText();
        String sdt = PT1_Active_text_Field_SDT.getText();
        MayTinh mt1 = (MayTinh) selectedComputer.getUserData();
        String may = mt1.getMaMay();

        // Thực hiện truy vấn cơ sở dữ liệu thông qua TaiKhoanDAO để kiểm tra dữ liệu nhập vào
        TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();
        try {
            List<TaiKhoan> lstTaiKhoan = taiKhoanDAO.getAll();
            boolean matchFound = false;
            for (TaiKhoan taiKhoan : lstTaiKhoan) {
                if (taiKhoan.getUsername().equals(username) && taiKhoan.getSdt().equals(sdt)) {
                    matchFound = true;
                    // Tạo một đối tượng ThongTinSuDung từ dữ liệu nhập vào từ các TextField
                    ThongTinSuDung thongTinSuDung = new ThongTinSuDung();
                    thongTinSuDung.setMaMay(may); // Lấy ID hoặc mã máy tính từ button đã chọn trước đó
                    thongTinSuDung.setUsername(username);
                    thongTinSuDung.setSdt(sdt);

                    // Khai báo 1 biến kiểu LocalDateTime và khởi tạo 1 giá trị ngẫu nhiên cho nó
                    // Khi insert 1 dòng mới vào bảng THONGTINSUDUNG thì dùng giá trị này làm TGBATDAU và TGKETTHUC
                    // Điều này không ảnh hưởng gì đến tính chính xác của dữ liệu vì trigger sẽ tự động tính lại TGBATDAU và TGKETTHUC
                    LocalDateTime l = LocalDateTime.now();

                    thongTinSuDung.setTgBatDau(l);
                    thongTinSuDung.setTgKetThuc(l);
                    thongTinSuDung.setDangSuDung(true);

                    // Sử dụng một DAO mới để thêm dữ liệu mới vào bảng THONGTINSUDUNG
                    ThongTinSuDungDAO thongTinSuDungDAO = new ThongTinSuDungDAO();
                    try {
                        thongTinSuDungDAO.addThongTinSuDung(thongTinSuDung);
                    } catch (Exception e) {
                        e.printStackTrace();
                        // Xử lý khi có lỗi xảy ra trong quá trình thêm dữ liệu mới vào bảng THONGTINSUDUNG
                    }
                    break;
                }
            }
            if (!matchFound) {
                System.out.println("Error line 203");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý khi có lỗi xảy ra trong quá trình truy xuất cơ sở dữ liệu
        }
    }


    public void handleButtonAction_PT2(ActionEvent event) {
        Button clickedButton = (Button)event.getSource();
        // Kiểm tra nếu nút có màu xanh
        if (clickedButton.getStyle().contains("background-color: #00FF00;")) {
            // Cập nhật máy tính hiện tại được chọn
            selectedComputer = clickedButton;
            // Hiển thị optionsPanel nếu nút có màu xanh
            optionsPanelPT2.setVisible(true);
            //optionsPanelVIP.setVisible(true);
            optionsPanelPT2.setLayoutX(clickedButton.getLayoutX());
            optionsPanelPT2.setLayoutY(clickedButton.getLayoutY() + clickedButton.getHeight());
        } else {
            // Ẩn optionsPanel nếu nút không có màu xanh
            optionsPanelRed_PT2.setVisible(true);
            optionsPanelRed_PT2.setLayoutX(clickedButton.getLayoutX());
            optionsPanelRed_PT2.setLayoutY(clickedButton.getLayoutY() + clickedButton.getHeight());
        }
    }


    public void handleButtonAction_VIP(ActionEvent event) {
        Button clickedButton = (Button)event.getSource();
        // Kiểm tra nếu nút có màu xanh
        if (clickedButton.getStyle().contains("background-color: #00FF00;")) {
            // Cập nhật máy tính hiện tại được chọn
            selectedComputer = clickedButton;
            // Hiển thị optionsPanel nếu nút có màu xanh
            optionsPanelVIP.setVisible(true);
            //optionsPanelVIP.setVisible(true);
            optionsPanelVIP.setLayoutX(clickedButton.getLayoutX());
            optionsPanelVIP.setLayoutY(clickedButton.getLayoutY() + clickedButton.getHeight());
        } else {
            // Ẩn optionsPanel nếu nút không có màu xanh
            optionsPanelRed_VIP.setVisible(true);
            optionsPanelRed_VIP.setLayoutX(clickedButton.getLayoutX());
            optionsPanelRed_VIP.setLayoutY(clickedButton.getLayoutY() + clickedButton.getHeight());
        }
    }

    public void onRootPaneClicked(MouseEvent event) {
        // Kiểm tra xem click có nằm ngoài optionsPanel không
        if (!optionsPanelPT1.getBoundsInParent().contains(event.getX(), event.getY())) {
            optionsPanelPT1.setVisible(false);
        }
        if (!optionsPanelPT2.getBoundsInParent().contains(event.getX(), event.getY())) {
            optionsPanelPT2.setVisible(false);
        }
        if (!optionsPanelRed_PT1.getBoundsInParent().contains(event.getX(), event.getY())) {
            optionsPanelRed_PT1.setVisible(false);
        }
        if (!optionsPanelRed_PT2.getBoundsInParent().contains(event.getX(), event.getY())) {
            optionsPanelRed_PT2.setVisible(false);
        }
        if (!optionsPanelVIP.getBoundsInParent().contains(event.getX(), event.getY())) {
            optionsPanelVIP.setVisible(false);
        }

        if (!optionsPanelRed_VIP.getBoundsInParent().contains(event.getX(), event.getY())) {
            optionsPanelRed_VIP.setVisible(false);
        }

    }

    public void loadDataToAnchorPane(String maMay, AnchorPane anchorPane) {
        try {
//            Connection conn = DriverManager.getConnection(controllerConnect.getUrl());
//            String query = "SELECT MAMAY, PHONG FROM MAYTINH WHERE MAMAY = ?";
//
//            PreparedStatement preparedStatement = conn.prepareStatement(query);
//            preparedStatement.setString(1, maMay);
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                String retrievedMaMay = resultSet.getString("MAMAY");
//                String phong = resultSet.getString("PHONG");
//
//                controllerComputerInfo computerInfo = new controllerComputerInfo();
//                computerInfo.setMaMay(retrievedMaMay);
//                computerInfo.setPhong(phong);
//
//                anchorPane.setUserData(computerInfo);
//            }
//
//            conn.close();
            MayTinhDAO dao = new MayTinhDAO();
            MayTinh mt = dao.findByMamay(maMay);
            if (mt != null) {
                anchorPane.setUserData(mt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

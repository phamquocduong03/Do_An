package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import management.DAO.MayTinhDAO;
import management.model.MayTinh;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.net.URL;
import java.util.ResourceBundle;

public class controllerMainForm implements Initializable {

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
        setButtonStatus(PT1_cp1_Btn, false); // Giả sử máy tính 1 đang được sử dụng
        setButtonStatus(PT1_cp2_Btn, false); // Giả sử máy tính 2 không được sử dụng
        setButtonStatus(PT2_cp1_Btn, false);
        setButtonStatus(PT2_cp2_Btn, false);
        setButtonStatus(VIP_cp1_Btn, false);
        setButtonStatus(VIP_cp2_Btn, false);

        // Cài đặt trạng thái cho các nút khác tương tự

        PT1_cp1_Btn.setOnAction(this::handleButtonAction_PT1);
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
            // Hiển thị optionsPanel nếu nút có màu xanh
            optionsPanelPT1.setVisible(true);
            //optionsPanelVIP.setVisible(true);
            optionsPanelPT1.setLayoutX(clickedButton.getLayoutX());
            optionsPanelPT1.setLayoutY(clickedButton.getLayoutY() + clickedButton.getHeight());
        } else {
            // Ẩn optionsPanel nếu nút không có màu xanh
            optionsPanelRed_PT1.setVisible(true);
            optionsPanelRed_PT1.setLayoutX(clickedButton.getLayoutX());
            optionsPanelRed_PT1.setLayoutY(clickedButton.getLayoutY() + clickedButton.getHeight());
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

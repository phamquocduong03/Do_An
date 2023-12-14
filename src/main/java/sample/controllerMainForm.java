package sample;

import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalUnit;
import java.time.temporal.ChronoUnit;
import com.sun.source.tree.IfTree;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
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
//import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class controllerMainForm implements Initializable {
    @FXML
    private TextField PT2_Active_text_Field_SDT;

    @FXML
    private TextField PT2_Active_text_Field_name;
    @FXML
    private Button PT1_Active_Btn;

    @FXML
    private Button PT1_Active_Btn1;

    @FXML
    private AnchorPane PT1_Active_Pane;

    @FXML
    private TextField PT1_Active_text_Field_SDT;


    @FXML
    private TextField PT1_Active_text_Field_name;

    @FXML
    private Button PT1_Ok_Recharge_Btn;

    @FXML
    private Button PT1_Recharge_Btn;

    @FXML
    private Button PT1_TurnOff_Btn;

    @FXML
    private TextField PT1_Type_Money;

    @FXML
    private AnchorPane PT1_Type_Money_Pane;

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
    private AnchorPane PT2_Active_Pane;

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

    @FXML
    private Text timer;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    private Button selectedComputer;

    private MayTinh selectedMT;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Đặt trạng thái hiển thị cho máy
        MayTinh mt1 = null;
        MayTinh mt2 = null;
        MayTinh mt4 = null;

        try {
            // Lấy thông tin của máy tính từ database dựa trên MAMAY
            mt1 = MayTinhDAO.findByMamay("MAY001");
            mt2 = MayTinhDAO.findByMamay("MAY002");
            mt4 = MayTinhDAO.findByMamay("MAY004");
            // Cập nhật trạng thái của AnchorPane tương ứng với dữ liệu lấy được từ database

            setButtonStatus(PT1_cp1_Btn, mt1 != null && !mt1.isCoSan(), mt1);
            setButtonStatus(PT1_cp2_Btn, mt2 != null && !mt2.isCoSan(), mt2);
            setButtonStatus_PT2(PT2_cp1_Btn, mt4 != null && !mt4.isCoSan(), mt4);
        } catch (Exception e) {
            e.printStackTrace();
        }

        timer.setText(String.valueOf(LocalDateTime.now()));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }

    Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(1),
                e-> {
                    LocalDateTime now = LocalDateTime.now();
                    LocalDateTime truncatedNow = now.truncatedTo(ChronoUnit.SECONDS);

                    LocalDateTime dt1 = LocalDateTime.of(2023, Month.DECEMBER, 14, 22, 20, 50);
                    LocalDateTime truncatedDt1 = dt1.truncatedTo(ChronoUnit.SECONDS);
                    if (truncatedNow.isEqual(truncatedDt1)) {
                        System.out.println("ALARM");
                    }
                    timer.setText(LocalDateTime.now().format(formatter));
    }));

    public void setButtonStatus(Button button, boolean isUsed, MayTinh mayTinh) {
        if (isUsed) {
            button.setText("Onl");
            button.setStyle("-fx-background-color: #00FF00;"); // Màu Xanh
        } else {
            button.setText("Off");
            button.setStyle("-fx-background-color: #FF0000;"); // Màu Đỏ
        }

        button.setOnAction(e -> {
            selectedComputer = button;
            selectedMT = mayTinh; // Cập nhật thông tin máy tính được chọn
            if (isUsed) {
                optionsPanelPT1.setVisible(true);
                optionsPanelPT1.setLayoutX(button.getLayoutX());
                optionsPanelPT1.setLayoutY(button.getLayoutY() + button.getHeight());
            } else {
                optionsPanelRed_PT1.setVisible(true);
                optionsPanelRed_PT1.setLayoutX(button.getLayoutX());
                optionsPanelRed_PT1.setLayoutY(button.getLayoutY() + button.getHeight());
            }
        });
    }

    public void setButtonStatus_PT2(Button button, boolean isUsed, MayTinh mayTinh) {
        if (isUsed) {
            button.setText("Onl");
            button.setStyle("-fx-background-color: #00FF00;"); // Màu Xanh
        } else {
            button.setText("Off");
            button.setStyle("-fx-background-color: #FF0000;"); // Màu Đỏ
        }

        button.setOnAction(e -> {
            selectedComputer = button;
            selectedMT = mayTinh; // Cập nhật thông tin máy tính được chọn
            if (isUsed) {
                optionsPanelPT2.setVisible(true);
                optionsPanelPT2.setLayoutX(button.getLayoutX());
                optionsPanelPT2.setLayoutY(button.getLayoutY() + button.getHeight());
            } else {
                optionsPanelRed_PT2.setVisible(true);
                optionsPanelRed_PT2.setLayoutX(button.getLayoutX());
                optionsPanelRed_PT2.setLayoutY(button.getLayoutY() + button.getHeight());
            }
        });
    }

    public void handlePT1ActiveButtonAction(ActionEvent event) {
        PT1_Active_Pane.setVisible(true);}

    public void handlePT2ActiveButtonAction(ActionEvent event) {
        PT2_Active_Pane.setVisible(true);}

    public void handleSaveActionPT1(ActionEvent event) {

        String username = PT1_Active_text_Field_name.getText();
        String sdt = PT1_Active_text_Field_SDT.getText();


        if (selectedMT != null) {
            String maMay = selectedMT.getMaMay();

            TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();
            try {
                List<TaiKhoan> lstTaiKhoan = taiKhoanDAO.getAll();
                boolean matchFound = false;

                for (TaiKhoan taiKhoan : lstTaiKhoan) {
                    if (taiKhoan.getUsername().equals(username) && taiKhoan.getSdt().equals(sdt)) {
                        matchFound = true;

                        ThongTinSuDung thongTinSuDung = new ThongTinSuDung();
                        thongTinSuDung.setMaMay(maMay);
                        thongTinSuDung.setUsername(username);
                        thongTinSuDung.setSdt(sdt);
                        LocalDateTime now = LocalDateTime.now();

                        thongTinSuDung.setTgBatDau(now);
                        thongTinSuDung.setTgKetThuc(now);

                        thongTinSuDung.setDangSuDung(true);

                        ThongTinSuDungDAO thongTinSuDungDAO = new ThongTinSuDungDAO();
                        try {
                            thongTinSuDungDAO.addThongTinSuDung(thongTinSuDung);
                            System.out.println("Đã thêm thông tin sử dụng vào CSDL.");
                            setButtonStatus(selectedComputer, true,selectedMT);
                        } catch (Exception e) {
                            System.err.println("Lỗi khi thêm thông tin sử dụng vào CSDL:");
                            e.printStackTrace();
                            // Xử lý khi có lỗi xảy ra trong quá trình thêm dữ liệu mới vào bảng THONGTINSUDUNG
                        }
                        break;
                    }
                }

                if (!matchFound) {
                    System.out.println("Không tìm thấy trùng khớp thông tin người dùng trong CSDL.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                // Xử lý khi có lỗi xảy ra trong quá trình truy xuất cơ sở dữ liệu
            }
        } else {
            System.out.println("Không có máy tính nào được chọn.");
        }
    }




    public void handleSaveActionPT2(ActionEvent event) {

        String username2 = PT2_Active_text_Field_name.getText();
        String sdt2 = PT2_Active_text_Field_SDT.getText();

        if (selectedMT != null) {
            String maMay = selectedMT.getMaMay();

            TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();
            try {
                List<TaiKhoan> lstTaiKhoan = taiKhoanDAO.getAll();
                boolean matchFound = false;

                for (TaiKhoan taiKhoan : lstTaiKhoan) {
                    if (taiKhoan.getUsername().equals(username2) && taiKhoan.getSdt().equals(sdt2)) {
                        matchFound = true;

                        ThongTinSuDung thongTinSuDung = new ThongTinSuDung();
                        thongTinSuDung.setMaMay(maMay);
                        thongTinSuDung.setUsername(username2);
                        thongTinSuDung.setSdt(sdt2);
                        LocalDateTime now = LocalDateTime.now();

                        thongTinSuDung.setTgBatDau(now);
                        thongTinSuDung.setTgKetThuc(now);

                        thongTinSuDung.setDangSuDung(true);

                        ThongTinSuDungDAO thongTinSuDungDAO = new ThongTinSuDungDAO();
                        try {
                            thongTinSuDungDAO.addThongTinSuDung(thongTinSuDung);
                            System.out.println("Đã thêm thông tin sử dụng vào CSDL.");
                            setButtonStatus(selectedComputer, true,selectedMT);
                        } catch (Exception e) {
                            System.err.println("Lỗi khi thêm thông tin sử dụng vào CSDL:");
                            e.printStackTrace();
                            // Xử lý khi có lỗi xảy ra trong quá trình thêm dữ liệu mới vào bảng THONGTINSUDUNG
                        }
                        break;
                    }
                }

                if (!matchFound) {
                    System.out.println("Không tìm thấy trùng khớp thông tin người dùng trong CSDL.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                // Xử lý khi có lỗi xảy ra trong quá trình truy xuất cơ sở dữ liệu
            }
        } else {
            System.out.println("Không có máy tính nào được chọn.");
        }


    }

    public void handleTurnOffAction(ActionEvent event) {
        if (selectedMT != null) {
            String maMay = selectedMT.getMaMay();

            // Lấy thông tin từ THONGTINSUDUNG dựa trên mã máy và DANGSUDUNG là true
            ThongTinSuDungDAO thongTinSuDungDAO = new ThongTinSuDungDAO();
            try {
                ThongTinSuDung thongTinSuDung = thongTinSuDungDAO.getByMaMayAndDangSuDung(maMay, true);
                if (thongTinSuDung != null) {
                    // Đối chiếu với thông tin trong TAIKHOAN
                    TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();
                    TaiKhoan taiKhoan = taiKhoanDAO.getByUsernameAndSDT(thongTinSuDung.getUsername(), thongTinSuDung.getSdt());
                    taiKhoanDAO.updateDangSuDungByUsernameAndSDT(thongTinSuDung.getUsername(), thongTinSuDung.getSdt(), false);
                    setButtonStatus(selectedComputer, false,selectedMT);

                    // Hiển thị thông báo với giá trị SOTIEN từ TAIKHOAN
                    //int soTien = (int) taiKhoan.getSoTien();

                    // Hiển thị thông báo với giá trị soTien, ví dụ sử dụng Alert
                    // AlertType.CONFIRMATION có thể thay bằng kiểu thông báo phù hợp
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText("Tắt máy thành công !");
                    alert.showAndWait();
                }
            } catch (Exception e) {
                e.printStackTrace();
                // Xử lý khi có lỗi xảy ra trong quá trình truy xuất cơ sở dữ liệu
            }
        } else {
            System.out.println("Không có máy tính nào được chọn.");
        }
    }

    public void handleTurnOffActionPT2(ActionEvent event) {
        if (selectedMT != null) {
            String maMay = selectedMT.getMaMay();

            // Lấy thông tin từ THONGTINSUDUNG dựa trên mã máy và DANGSUDUNG là true
            ThongTinSuDungDAO thongTinSuDungDAO = new ThongTinSuDungDAO();
            try {
                ThongTinSuDung thongTinSuDung = thongTinSuDungDAO.getByMaMayAndDangSuDung(maMay, true);
                if (thongTinSuDung != null) {
                    // Đối chiếu với thông tin trong TAIKHOAN
                    TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();
                    TaiKhoan taiKhoan = taiKhoanDAO.getByUsernameAndSDT(thongTinSuDung.getUsername(), thongTinSuDung.getSdt());
                    taiKhoanDAO.updateDangSuDungByUsernameAndSDT(thongTinSuDung.getUsername(), thongTinSuDung.getSdt(), false);
                    setButtonStatus(selectedComputer, false,selectedMT);

                    // Hiển thị thông báo với giá trị SOTIEN từ TAIKHOAN


                    int soTien = (int) taiKhoan.getSoTien();

                    // Hiển thị thông báo với giá trị soTien, ví dụ sử dụng Alert
                    // AlertType.CONFIRMATION có thể thay bằng kiểu thông báo phù hợp
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText("Số tiền trong tài khoản của bạn là: " + soTien);
                    alert.showAndWait();
                }
            } catch (Exception e) {
                e.printStackTrace();
                // Xử lý khi có lỗi xảy ra trong quá trình truy xuất cơ sở dữ liệu
            }
        } else {
            System.out.println("Không có máy tính nào được chọn.");
        }
    }

    public void handleRechargeAction(ActionEvent event) {
        // Hiển thị TextField khi nhấn nút Recharge
        PT1_Type_Money_Pane.setVisible(true); // Giả sử PT1_Type_Money_Pane là AnchorPane chứa TextField PT1_Type_Money
    }

    public void handleOKAction(ActionEvent event) {
        if (selectedMT != null) {
            String maMay = selectedMT.getMaMay();

            // Lấy thông tin từ THONGTINSUDUNG dựa trên mã máy và DANGSUDUNG là true
            ThongTinSuDungDAO thongTinSuDungDAO = new ThongTinSuDungDAO();
            try {
                ThongTinSuDung thongTinSuDung = thongTinSuDungDAO.getByMaMayAndDangSuDung(maMay, true);
                // Lấy số tiền từ TextField PT1_Type_Money và chuyển đổi sang kiểu int
                int rechargeAmount = Integer.parseInt(PT1_Type_Money.getText());

                // Lấy thông tin tài khoản từ table MAYTINH
                TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();
                TaiKhoan taiKhoan = taiKhoanDAO.getByUsernameAndSDT(thongTinSuDung.getUsername(), thongTinSuDung.getSdt());

                // Cộng giá trị mới vào cột SOTIEN của tài khoản
                int newBalance = (int) (taiKhoan.getSoTien() + rechargeAmount);
                taiKhoan.setSoTien(newBalance);
                taiKhoanDAO.updateTaiKhoan(taiKhoan);

                // Hiển thị thông báo với giá trị mới của cột SOTIEN
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText("Số tiền trong tài khoản của bạn sau khi nạp là: " + newBalance);
                alert.showAndWait();

                // Ẩn TextField sau khi xử lý xong
                PT1_Type_Money_Pane.setVisible(false);
            } catch (NumberFormatException e) {
                // Xử lý nếu người dùng nhập không đúng định dạng số
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lỗi");
                alert.setHeaderText("Vui lòng nhập số tiền hợp lệ.");
                alert.showAndWait();
            } catch (Exception e) {
                e.printStackTrace();
                // Xử lý khi có lỗi xảy ra trong quá trình truy xuất cơ sở dữ liệu
            }
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

        if (!PT1_Active_Pane.getBoundsInParent().contains(event.getX(), event.getY())) {
            PT1_Active_Pane.setVisible(false);
        }
        if (!PT1_Type_Money_Pane.getBoundsInParent().contains(event.getX(), event.getY())) {
            PT1_Type_Money_Pane.setVisible(false);
        }

    }

}

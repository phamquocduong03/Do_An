package sample;


import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import javafx.event.ActionEvent;
import javafx.util.Pair;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class controllerLogin implements Initializable {
    @FXML
    private Button bp_backBtn;

    @FXML
    private Button fb_backBtn;

    @FXML
    private Button fp_proceedBtn;

    @FXML
    private AnchorPane fp_questionForm;

    @FXML
    private TextField fp_sdt;

    @FXML
    private TextField fp_username;

    @FXML
    private Button np_changePasswordBtn;

    @FXML
    private PasswordField np_confirmPassword;

    @FXML
    private AnchorPane np_newPassForm;

    @FXML
    private PasswordField np_newPassword;

    @FXML
    private Hyperlink si_forgotPass;

    @FXML
    private Button si_loginBtn;

    @FXML
    private AnchorPane si_loginForm;

    @FXML
    private PasswordField si_password;

    @FXML
    private TextField si_username;

    @FXML
    private Button side_AlreadyHave;

    @FXML
    private Button side_CreateBtn;

    @FXML
    private AnchorPane side_form;

    @FXML
    private PasswordField su_password;

    @FXML
    private TextField su_sdt;

    @FXML
    private Button su_signupBtn;

    @FXML
    private AnchorPane su_signupForm;

    @FXML
    private TextField su_username;


    public void backToLoginForm(){
        si_loginForm.setVisible(true);
        fp_questionForm.setVisible(false);

    }

    public void backToQuestionForm(){
        fp_questionForm.setVisible(true);
        np_newPassForm.setVisible(false);


    }

    public void switchForgotPass(){
        fp_questionForm.setVisible(true);
        si_loginForm.setVisible(false);

    }




    /*public void (){
        si_loginForm.setVisible(true);
        np_newPassForm.setVisible(false);
    }*/

    public void switchForm(ActionEvent event) {
        TranslateTransition slider = new TranslateTransition();

        if(event.getSource() == side_CreateBtn)
        {
            slider.setNode(side_form);
            slider.setToX(400);
            slider.setDuration(Duration.seconds(.5));

            slider.setOnFinished((ActionEvent e) -> {
                side_AlreadyHave.setVisible(true);
                side_CreateBtn.setVisible(false);

                fp_questionForm.setVisible(false);
                si_loginForm.setVisible(true);
                np_newPassForm.setVisible(false);
            });
            slider.play();
        } else if (event.getSource() == side_AlreadyHave){
            slider.setNode(side_form);
            slider.setToX(0);
            slider.setDuration(Duration.seconds(.5));

            slider.setOnFinished( (ActionEvent e) ->{
                side_AlreadyHave.setVisible(false);
                side_CreateBtn.setVisible(true);


                fp_questionForm.setVisible(false);
                si_loginForm.setVisible(true);
                np_newPassForm.setVisible(false);
            });
            slider.play();
        }
    }

    public void handleLoginButtonAction(ActionEvent event) {
        String username = si_username.getText();
        String password = si_password.getText();

        String url = controllerConnect.getUrl();
        // Thực hiện truy vấn SQL để kiểm tra thông tin đăng nhập
        // Ví dụ: sử dụng PreparedStatement để tránh các vấn đề bảo mật liên quan đến SQL injection
        String query = "SELECT * FROM TAIKHOAN WHERE USERNAME = ? AND PASSWORD = ?";

        try (Connection conn = DriverManager.getConnection(controllerConnect.getUrl());
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Nếu có kết quả từ cơ sở dữ liệu, thông báo đăng nhập thành công
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Đăng nhập thành công!");
                alert.showAndWait();

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/mainForm.fxml"));
                    Parent root = loader.load();

                    // Lấy controller của mainForm.fxml
                    controllerMainForm controller = loader.getController();

                    // Hiển thị mainForm.fxml trong cửa sổ hiện tại
                    Stage stage = (Stage) si_username.getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            } else {
                // Nếu không có kết quả, thông báo đăng nhập thất bại
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lỗi");
                alert.setHeaderText(null);
                alert.setContentText("Đăng nhập không thành công. Vui lòng kiểm tra lại thông tin đăng nhập.");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            // Xử lý các ngoại lệ khi kết nối hoặc thực thi truy vấn
            e.printStackTrace();
        }
    }


    public void handleSignupButtonAction(ActionEvent event) {
        // Xử lý khi nút Đăng kí được nhấn
        // Lấy dữ liệu từ các trường nhập liệu (TextField, PasswordField, v.v.)
        String username = su_username.getText();
        String password = su_password.getText();
        String phoneNumber =su_sdt.getText();

        // Thực hiện việc chèn dữ liệu vào cơ sở dữ liệu
        boolean isSuccess = insertDataIntoDatabase(username, password, phoneNumber);


        if (isSuccess) {
            // Hiển thị hộp thoại thông báo khi đăng ký thành công
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Đăng ký thành công!");
            alert.showAndWait();
        }
    }

    private boolean insertDataIntoDatabase(String username, String password, String phoneNumber) {
        boolean success = false;
        try {
            Connection conn = DriverManager.getConnection(controllerConnect.getUrl());
            String query = "INSERT INTO TAIKHOAN (USERNAME, PASSWORD, SDT) VALUES (?, ?, ?)";

            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, phoneNumber);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                // Thêm dữ liệu thành công
                success = true;
                //System.out.println("Dữ liệu đã được thêm vào cơ sở dữ liệu.");
            } else {
                // Thêm dữ liệu không thành công
                System.out.println("Không thể thêm dữ liệu vào cơ sở dữ liệu.");
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }



    public void handleProceedButtonAction(ActionEvent event) {
        String username = fp_username.getText();
        String sdt = fp_sdt.getText();

        // Thực hiện truy vấn để kiểm tra thông tin đăng nhập
        if (checkLogin(username, sdt)) {
            // Nếu thông tin đăng nhập đúng, chuyển sang AnchorPane np_newPassForm
            fp_questionForm.setVisible(false);
            np_newPassForm.setVisible(true);
        } else {
            // Nếu thông tin đăng nhập không đúng, có thể hiển thị một thông báo lỗi
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Thông tin đăng nhập không đúng. Vui lòng kiểm tra lại.");
            alert.showAndWait();
        }
    }

    private boolean checkLogin(String username, String sdt) {
        try {
            Connection conn = DriverManager.getConnection(controllerConnect.getUrl());
            String query = "SELECT * FROM TAIKHOAN WHERE USERNAME = ? AND SDT = ?";

            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, sdt);

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next(); // Trả về true nếu có kết quả từ cơ sở dữ liệu, ngược lại trả về false

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(controllerConnect.getUrl());
    }

    private void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private Pair<String, String> getUsernameAndSdt(String username, String sdt) {
        Connection conn = null;
        try {
            conn = getConnection();
            String query = "SELECT USERNAME, SDT FROM TAIKHOAN WHERE USERNAME = ? AND SDT = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, sdt);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String retrievedUsername = resultSet.getString("USERNAME");
                String retrievedSdt = resultSet.getString("SDT");
                return new Pair<>(retrievedUsername, retrievedSdt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }
        return null;
    }
//Hiển thị thông báo việc cập nhật mật khẩu thành công hay không
    private void showSuccessDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thành công");
        alert.setHeaderText(null);
        alert.setContentText("Mật khẩu đã được cập nhật thành công!");
        alert.showAndWait();
    }
    private void showFailureDialog(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Lỗi");
        alert.setHeaderText(null);
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }

//

    public void handleChangePasswordButtonAction(ActionEvent event) {
        String newPassword = np_newPassword.getText();
        String confirmPassword = np_confirmPassword.getText();

        // Kiểm tra mật khẩu mới và xác nhận mật khẩu
        if (!newPassword.equals(confirmPassword)) {
            showFailureDialog("Cập nhật mật khẩu không thành công");
            // Hiển thị thông báo lỗi nếu mật khẩu không khớp
            // ...
        } else {
            String username = fp_username.getText();
            String sdt = fp_sdt.getText();

            Pair<String, String> usernameAndSdt = getUsernameAndSdt(username, sdt);
            if (usernameAndSdt != null) {
                String retrievedUsername = usernameAndSdt.getKey();
                String retrievedSdt = usernameAndSdt.getValue();

                boolean updateSuccess = updatePasswordInDatabase(retrievedUsername, retrievedSdt, newPassword);
                if (updateSuccess) {
                    // Chuyển sang giao diện đăng nhập
                    showSuccessDialog();
                    switchToLoginForm();

                }
            } else {

                System.out.println("Lỗi");
                // Hiển thị thông báo lỗi nếu thông tin đăng nhập không hợp lệ
                // ...
            }
        }
    }

    private boolean updatePasswordInDatabase(String username, String retrievedSdt, String newPassword) {
        Connection conn = null;
        try {
            conn = getConnection();
            String query = "UPDATE TAIKHOAN SET PASSWORD = ? WHERE USERNAME = ? AND SDT = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, retrievedSdt);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0; // Trả về true nếu có dòng dữ liệu được cập nhật, ngược lại trả về false
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeConnection(conn);
        }
    }

    private void switchToLoginForm() {
        np_newPassForm.setVisible(false);
        si_loginForm.setVisible(true);

    }





    @Override
    public void initialize(URL url, ResourceBundle rb){

    }


}

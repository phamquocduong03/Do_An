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

    public static String getUrl() {
        return URL;


    }
}




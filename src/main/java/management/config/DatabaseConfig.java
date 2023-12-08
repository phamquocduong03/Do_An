package management.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConfig {

    public static Connection openConnection() throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String connectionURL = "jdbc:sqlserver://DUONG:1433;databaseName=QuanLyTiemNet";
        String username = "DUONG10112003";
        String password = "@Duong10112003";
        Connection con = DriverManager.getConnection(connectionURL, username, password);
        return con;
    }

}

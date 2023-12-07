package management.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConfig {

    public static Connection openConnection() throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String connectionURL = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyTiemNet";
        String username = "sa";
        String password = "123";
        Connection con = DriverManager.getConnection(connectionURL, username, password);
        return con;
    }

}

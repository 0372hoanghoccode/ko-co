package connectionSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=QLNS";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "123456789";

    public static Connection getConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("Không tìm thấy driver!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Không kết nối được đến cơ sở dữ liệu!");
            e.printStackTrace();
        }
        return null;
    }

    public static void closeConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println("Không đóng được kết nối!");
                e.printStackTrace();
            }
        }
    }
}

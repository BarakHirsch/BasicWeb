package Dal;

import java.sql.*;

public class DBUtil {

    public static Connection getConnection() {

        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            String urlCn = "jdbc:derby://localhost:1527/my_DB";
            return DriverManager.getConnection(urlCn, "root", "root");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
}

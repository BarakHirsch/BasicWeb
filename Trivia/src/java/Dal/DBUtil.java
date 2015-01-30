package Dal;

import java.sql.*;

public class DBUtil {

    public static Connection getConnection() {

        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            String urlCn = "jdbc:derby://localhost:1527/TriviaDB";
            return DriverManager.getConnection(urlCn, "trivia", "trivia");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
}

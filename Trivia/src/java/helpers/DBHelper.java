package helpers;

import java.sql.*;

public class DBHelper {

    public static void go() throws ClassNotFoundException, SQLException{
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        String urlCn = "jdbc:derby://localhost:1527/QuestionsDB";
        Connection cn = DriverManager.getConnection(urlCn, "baviran", "1234");
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery("select * from Questions");
        while (rs.next()) {
            System.out.println(rs.getString(1) + ":" + rs.getString(2));
        }
        cn.close();
    }
}

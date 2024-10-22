package util;

import java.sql.*;

public class JDBCConnection  {

    public Connection dbConnect() {
        Connection con= null;
        String url ="jdbc:postgresql://localhost:5432/demo";
        String username ="postgres";
        String password ="root";

        try {
            con = DriverManager.getConnection(url, username, password);
            System.out.println("Connection Established");
            return con;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        finally {
//            con.close();
//        }
    }
}

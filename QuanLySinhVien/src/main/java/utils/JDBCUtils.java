package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {

    public static Connection getConnection(){
        Connection c = null;
        String url = "jdbc:mysql://127.0.0.1:3306/quanlysinhvien";
        String username = "root";
        String password = "12345678";

        try{
            c = DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return c;
    }

    public static void closeConnection(Connection c){
        if(c!=null){
            try{
                c.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}

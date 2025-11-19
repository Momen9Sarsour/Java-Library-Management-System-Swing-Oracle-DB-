package finalexam;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class Database {

    static final String url = "jdbc:oracle:thin:@localhost:1521:orcl";
    static final String username = "library";
    static final String password = "1123";
    static Connection conn = null;

    public static Connection connDB() {
        try {
            Class.forName ("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
        return conn;
    }

}




/*
    static final String url = "jdbc:oracle:thin:@localhost:1521:orcl";
    static final String username = "library";
    static final String password = "l123";
    static Connection conn = null;

    public static Connection connDB() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
        return conn;
    }
*/
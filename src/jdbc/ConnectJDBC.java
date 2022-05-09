package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectJDBC {

    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/hb-03-one-to-many-uni?useSSL=false&serverTimeZone=UTC";
        String user = "hbstudent";
        String pass = "hbstudent";

        try {
            System.out.println("connecting to databsae: " + jdbcURL);

            Connection conn = DriverManager.getConnection(jdbcURL, user, pass);

            System.out.println("connection sucessful");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
